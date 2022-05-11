package com.duc.memorandum.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.aliyun.oss.OSSClient;

//import jxl.common.Logger;

/**
 * 图片上传到阿里云
 * @author EDZ
 *
 */
public class fileUploadUtil {
//	private static final Logger logger = Logger.getLogger(fileUploadUtil.class);
	
	
	/**
	 * 上传图片到阿里云
	 * @param fileContent
	 * @return
	 */
	public static String uploadFileOss(InputStream fileContent){
		     String fileName=UUID.randomUUID().toString().replace("_", "");
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
			 String remonteFileKey=filePath+fileName+".png";
			 //上传文件 
			 ossClient.putObject(bucketName, remonteFileKey, fileContent);
			 //关闭 ossclient
			 ossClient.shutdown();
			 //	关闭IO流
			 try {
				fileContent.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //创建返回值
			 String resopose="http://"+fileServiceIpAddress+remonteFileKey;
		
		return resopose;
	 
 }	
	
	
	/**
	 * 将BufferedImage转换为InputStream
	 * @param image
	 * @return
	 */
	public static InputStream bufferedImageToInputStream(BufferedImage image){
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    try {
	        ImageIO.write(image, "png", os);
	        InputStream input = new ByteArrayInputStream(os.toByteArray());
	        return input;
	    } catch (IOException e) {
	    	System.out.println(e);
//	        logger.error("提示:",e);
	    }
	    return null;
	}
	
	
	/**
	 * 检查图片是否存在
	 */
	public static boolean getRource(String source) {  
        try {  
            URL url = new URL(source);
            URLConnection uc = url.openConnection();
            InputStream in = uc.getInputStream();
            if (source.equalsIgnoreCase(uc.getURL().toString())) 
            in.close();  
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
    }


}
