package com.duc.memorandum.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.texen.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.duc.memorandum.constant.ExceptionValue;
import com.duc.memorandum.dao.FileMapper;
import com.duc.memorandum.entity.FileVO;
import com.duc.memorandum.exception.memorandumException;
import com.duc.memorandum.service.FileService;
import com.duc.memorandum.util.AliyunOSSUpload;

import sun.misc.BASE64Decoder;

//import Decoder.BASE64Decoder;

@Service
public class FileServiceImpl implements FileService {
	/**
	 * 图片最大大小：5M
	 */
	public static final long IMAGE_MAX_SIZE = 1024 * 1000 * 10; // 5M
	/**
	 * ZIP包最大大小：10M
	 */
	public static final long ZIP_MAX_SIZE = 1024 * 1000 * 10; // 10M
	/**
	 * 视频最大大小：10M
	 */
	public static final long VIDEO_MAX_SIZE = 1024 * 1000 * 10; // 10M

	@Resource
	private FileMapper fileMapper;

	@Override
	public FileVO addFile(MultipartHttpServletRequest request,String type,HttpServletResponse response) throws Exception {
		FileVO fileVO = null;
		Iterator<String> itr = request.getFileNames();
		while (itr.hasNext()) {
			// 创建上传文件对象
			MultipartFile file = request.getFile(itr.next());
			if (!file.isEmpty()) {
				String name = ""; // 文件名称，将自动生成
				String suffix = "";// 文件后缀，由文件流中获取，如果是图片，最终都将保存为png格式
				Long size = new Long(0); // 原始文件大小
				String fileRootPath = System.getProperty("webapp.root")+ "/upload/";
				name = String.valueOf(new Date().getTime());
				String oldSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				String originalName = file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf("."));
				size = file.getSize();
				String filePath = "";

				if (StringUtils.isNotBlank(oldSuffix)) {
					boolean isImage = false;
					if (oldSuffix.toLowerCase().equals("png")
							|| oldSuffix.toLowerCase().equals("jpg")
							|| oldSuffix.toLowerCase().equals("jpeg")) {
						suffix = "png";
						filePath = fileRootPath + suffix + "/" + name + "/"+ name + "_1." + oldSuffix;
						isImage = true;
					} else {
						suffix = oldSuffix;
						filePath = fileRootPath + suffix + "/" + name + "/"	+ name + "_1." + suffix;
					}
					// 对文件大小进行限制，图片5M以内，zip包10M以内，视频文件200M以内
					if (suffix.toLowerCase().equals("png")) {
						if (size > IMAGE_MAX_SIZE) {// 如果图片文件超出限制大小

							throw new memorandumException(
									ExceptionValue.FILE_LARGER_EXCEPTION,
									ExceptionValue.IMAGE_LARGER_EXCEPTION_MSG);
						}
					} else if (suffix.toLowerCase().equals("zip")) {
						if (size > ZIP_MAX_SIZE) {// 如果ZIP包文件超出限制大小
							throw new memorandumException(
									ExceptionValue.FILE_LARGER_EXCEPTION,
									ExceptionValue.ZIP_LARGER_EXCEPTION_MSG);
						}
					} else if (suffix.toLowerCase().equals("mp4")) {
						if (size > VIDEO_MAX_SIZE) {// 如果视频文件超出限制大小
							throw new memorandumException(
									ExceptionValue.FILE_LARGER_EXCEPTION,
									ExceptionValue.VIDEO_LARGER_EXCEPTION_MSG);
						}
					} else {
						throw new memorandumException(
								ExceptionValue.PARAM_ERROR_EXCEPTION,
								ExceptionValue.FILE_UNACCEPTED_EXCEPTION_MSG);
					}

					// 转存文件
					FileUtil.mkdir(filePath);
					File newFile = new File(filePath);
					file.transferTo(newFile);

					// 如果是图片，则还需要对图片进行PNG转换，并生成缩略图
					if (isImage) {
						if (!oldSuffix.toLowerCase().equals("png")) { // 如果原始图片不是PNG，还要生成原始图片对应的原始大小的PNG图片
							// 在原始图片的基础上，生成各种缩略图
							BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filePath));
//							ImageUtil.doCompress(filePath,bufferedImage.getWidth(),bufferedImage.getHeight(), 1, name + "_1", true);
						}
//						ImageUtil.doCompress(filePath,  50,  50, 1, name + "_50_50", true);
//						ImageUtil.doCompress(filePath, 100, 100, 1, name + "_100_100", true);
//						ImageUtil.doCompress(filePath, 200, 200, 1, name + "_200_200", true);
//						ImageUtil.doCompress(filePath, 300, 300, 1, name + "_300_300", true);
//						ImageUtil.doCompress(filePath, 500, 500, 1, name + "_500_500", true);
					}
					// 文件上传完成后，保存文件对象到数据库
				    fileVO =new FileVO();
				    fileVO.setName(name);
				    fileVO.setOldName(originalName);
				    fileVO.setSuffix(suffix);
				    fileVO.setType("1");
				    fileVO.setUserID(1L);
				    fileMapper.addFile(fileVO);
				}
			}
		}
		return fileVO;
	}
	
	/* (non-Javadoc)
	 * @see com.duc.memorandum.service.FileService#addQr(java.lang.String, java.lang.Long)
	 */


	

	
	@Override
	public FileVO addAliyunFile(MultipartFile multipartFile) throws Exception {
		 FileVO fileVO=null;
//		 Iterator<String> itr = request.getFileNames();
//		// 创建上传文件对象
//	     MultipartFile multipartFile = request.getFile(itr.next());
	     String oldSuffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
//	     String originalName = multipartFile.getOriginalFilename().substring(0,multipartFile.getOriginalFilename().indexOf("."));
//	     CommonsMultipartFile cf=(CommonsMultipartFile)multipartFile;
//       DiskFileItem diskFileItem=(DiskFileItem)cf.getFileItem();
         InputStream fileContent=multipartFile.getInputStream();
         String fileName=multipartFile.getName();
         fileName=UUID.randomUUID().toString().replace("_", "");
         //根节点
         String endpoint="oss-cn-beijing.aliyuncs.com";
         //空间名称
		 String bucketName="dongxiaoyan";
		 //账号ID
		 String accessKeyID="LTAIUZrjxkJFddft";
		 //密码
		 String accessKeySecret="c4YMIYCvaPuulGpfuORf7FQAKJMdmI";
		 //ip地址
		 String fileServiceIpAddress="dongxiaoyan.oss-cn-beijing.aliyuncs.com/";
		 //初始化ossClient
		 OSSClient ossClient=new OSSClient(endpoint, accessKeyID, accessKeySecret);
		 //设置上传文件保存目录
		 Calendar calendar=Calendar.getInstance();
		 String filePath=calendar.get(Calendar.YEAR)+"/"+
		 (calendar.get(Calendar.MONTH)+1)+"/"+(calendar.get(Calendar.DAY_OF_MONTH))+"/";	
		 //自定义上传目录
		 String remonteFileKey=filePath+fileName+"."+oldSuffix;;
		 //上传文件 
		 ossClient.putObject(bucketName, remonteFileKey, fileContent);
		 //关闭 ossclient
		 ossClient.shutdown();
		 //	关闭IO流
		 fileContent.close();
		 //创建返回值
		 String resopose="http://"+fileServiceIpAddress+remonteFileKey;
			// 文件上传完成后，保存文件对象到数据库
		    fileVO =new FileVO();
		    fileVO.setName(fileName);
		    fileVO.setOldName(fileName);
		    fileVO.setSuffix(fileName);
		    fileVO.setType("1");
		    fileVO.setUserID(1L);
		    fileVO.setURL(resopose);
		    fileMapper.addFile(fileVO);
		return fileVO;
	}

	@Override
	public FileVO addAliyun( String  share)throws Exception {
		    FileVO fileVO=null;
			 //根节点
	         String endpoint="oss-cn-beijing.aliyuncs.com";
	         //空间名称
			 String bucketName="dongxiaoyan";
			 //账号ID
			 String accessKeyID="LTAIUZrjxkJFddft";
			 //密码
			 String accessKeySecret="c4YMIYCvaPuulGpfuORf7FQAKJMdmI";
			 //ip地址
			 String fileServiceIpAddress="dongxiaoyan.oss-cn-beijing.aliyuncs.com/";
		    //初始化ossClient
		    OSSClient ossClient=new OSSClient(endpoint, accessKeyID, accessKeySecret);
            InputStream inputStream;
            String[] shartimg = new String[2];
           if(!share.isEmpty()){ //base64所在字段
             shartimg = share.split(",");
             int index = shartimg[0].indexOf("/");
             int endindex = shartimg[0].indexOf(";");
             SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
             String date = sdf.format(new Date());
             String endName  = shartimg[0].substring(index+1,endindex);//获取文件后缀
             String newFileName = date +"."+ endName;// 新文件名
             try {
//                byte[] bytes =null;
//                		new BASE64Decoder().decodeBuffer(shartimg[1]);  //将字符串转换为byte数组
                BASE64Decoder decoder = new BASE64Decoder();
                inputStream= new ByteArrayInputStream( decoder.decodeBuffer(shartimg[1]));
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(inputStream.available());  
                //设置上传文件保存目录
       		     Calendar calendar=Calendar.getInstance();
       		     //上传文件 
           	         String filePath=calendar.get(Calendar.YEAR)+"/"+
           			 (calendar.get(Calendar.MONTH)+1)+"/"+(calendar.get(Calendar.DAY_OF_MONTH))+"/";	
           			 //自定义上传目录
           			 String remonteFileKey=filePath+newFileName;
                     ossClient.putObject(bucketName,remonteFileKey, inputStream, metadata);
                	 //关闭 ossclient
            		 ossClient.shutdown();
            		 //创建返回值
            		 String resopose="http://"+fileServiceIpAddress+remonteFileKey;
            			// 文件上传完成后，保存文件对象到数据库
            		    fileVO =new FileVO();
            		    fileVO.setName(endName);
            		    fileVO.setOldName(newFileName);
            		    fileVO.setSuffix("png");
            		    fileVO.setType("1");
            		    fileVO.setUserID(1L);
            		    fileVO.setURL(resopose);
            		    fileMapper.addFile(fileVO);
             } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           }
       	return fileVO;
	}
    
	
	
	
	
	
	
	@Override
	public FileVO addAliyunFenpian(MultipartFile file) throws Exception {
		FileVO fileVO=null;
		Date date = new Date();
		String beginTime = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
		 // 创建一个可重用固定线程数的线程池。若同一时间线程数大于10，则多余线程会放入队列中依次执行
		 ExecutorService executorService = Executors.newFixedThreadPool(10);
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
         String time = sdf.format(new Date());
		// 完整的文件名
		String key = time+file.getOriginalFilename();
		// 创建OSSClient实例
		//从自己的配置文件里面取值
		 //根节点
         String endpoint="oss-cn-beijing.aliyuncs.com";
         //空间名称
		 String bucketName="dongxiaoyan";
		 //账号ID
		 String accessKeyID="LTAIUZrjxkJFddft";
		 //密码
		 String accessKeySecret="c4YMIYCvaPuulGpfuORf7FQAKJMdmI";
		 //ip地址
		 String fileServiceIpAddress="http://dongxiaoyan.oss-cn-beijing.aliyuncs.com/";
		 OSSClient client = new OSSClient(endpoint, accessKeyID, accessKeySecret);//根据阿里云用户配置创建连接对象实例
		 try {
			String uploadId = AliyunOSSUpload.claimUploadId(bucketName, key);// key是文件名 By berton
			// 设置每块为 5M(除最后一个分块以外，其他的分块大小都要大于5MB)
			final long partSize = 5 * 1024 * 1024L;
			// 计算分块数目
			long fileLength = file.getSize();// 文件大小
			int partCount = (int) (fileLength / partSize);// 文件大小%分块大小
			if (fileLength % partSize != 0) {
				partCount++;
			}
			// 分块 号码的范围是1~10000。如果超出这个范围，OSS将返回InvalidArgument的错误码。
			if (partCount > 10000) {
				throw new RuntimeException("文件过大(分块大小不能超过10000)");
			} else {
				System.out.println("一共分了 " + partCount + " 块");
			}
			/**
			 * 将分好的文件块加入到list集合中
			 */
			for (int i = 0; i < partCount; i++) {
				// 起始point
				long startPos = i * partSize;
				// 判断当前partSize的长度 是否最后一块
				long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
				// 线程执行。将分好的文件块加入到list集合中()
				executorService
						.execute(new AliyunOSSUpload(file, startPos, curPartSize, i + 1, uploadId, key, bucketName));
			}
			/**
			 * 等待所有分片完毕
			 */
			// 关闭线程池（线程池不马上关闭），执行以前提交的任务，但不接受新任务。
			executorService.shutdown();
			// 如果关闭后所有任务都已完成，则返回 true。
			while (!executorService.isTerminated()) {
				try {
					// 用于等待子线程结束，再继续执行下面的代码
					executorService.awaitTermination(5, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			/**
			 * partETags(上传块的ETag与块编号（PartNumber）的组合) 如果校验与之前计算的分块大小不同，则抛出异常
			 */
			System.out.println(AliyunOSSUpload.partETags.size() + " -----   " + partCount);
			if (AliyunOSSUpload.partETags.size() != partCount) {
				throw new IllegalStateException("OSS分块大小与文件所计算的分块大小不一致");
			} else {
				System.out.println("将要上传的文件名  " + key + "\n");
			}
 
			/*
			 * 列出文件所有的分块清单并打印到日志中，该方法仅仅作为输出使用
			 */
			AliyunOSSUpload.listAllParts(uploadId);
			/*
			 * 完成分块上传
			 */
			AliyunOSSUpload.completeMultipartUpload(uploadId);
			
			// 文件上传完成后，保存文件对象到数据库
		    fileVO =new FileVO();
		    fileVO.setName(key);
		    fileVO.setOldName(key);
		    fileVO.setSuffix("mp4");
		    fileVO.setType("1");
		    fileVO.setUserID(1L);
		    fileVO.setURL(fileServiceIpAddress+key);
		    fileMapper.addFile(fileVO);
			
			// 返回上传文件的URL地址
			 System.out.println(fileServiceIpAddress+key);
			 Date date2 = new Date();
			String endTime = date2.getHours() + ":" + date2.getMinutes() + ":" + date2.getSeconds();
			System.out.println(beginTime + "========" + endTime);
		} catch (Exception e) {
			System.out.println("上传失败！");
		} finally {
			AliyunOSSUpload.partETags.clear();
			if (client != null) {
				client.shutdown();
			}
		}
		
		return fileVO;
	}	
	
	
	
	

}
