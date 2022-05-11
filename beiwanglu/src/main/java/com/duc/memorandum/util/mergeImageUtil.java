package com.duc.memorandum.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
  
  

@Service
public class mergeImageUtil {  
  
    private Font font = new Font("Helvetica",Font.BOLD, 35);// 添加字体的属性设置  
  
    private Graphics2D g = null; 
    
    private Graphics2D f = null;  
  
    private int fontsize = 20;  
  
    private int x = 0;  
    
    private int y = 0;  
    
    /** 
     * 导入本地图片到缓冲区 
     */  
    public  BufferedImage loadImageLocal(String imgName) {  
        try {  
            return ImageIO.read(new File(imgName));  
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }  
        return null;  
    }  
  
    /** 
     * 导入网络图片到缓冲区 
     */  
    public BufferedImage loadImageUrl(String imgName) {  
        try {  
            URL url = new URL(imgName);  
            return ImageIO.read(url);  
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }  
        return null;  
    }
    
    /** 
     * 生成新图片到本地 
     */  
    public void writeImageLocal(String newImage, BufferedImage img) {  
        if (newImage != null && img != null) {  
            try {  
                File outputfile = new File(newImage);  
                ImageIO.write(img, "jpg", outputfile);  
            } catch (IOException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
    }  
    /** 
     * 设定文字的字体等 
     */  
    public void setFont(String fontStyle, int fontSize) {  
        this.fontsize = fontSize;  
        this.font = new Font(fontStyle, Font.HANGING_BASELINE, fontSize);  
    }  
  
    /** 
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本） 
     */  
    public BufferedImage modifyImage(BufferedImage img, Object content, int x, int y) {  
  
        try {  
            int w = img.getWidth();  
            int h = img.getHeight();  
            g = img.createGraphics();  
            g.setBackground(Color.WHITE);  
            g.setColor(Color.WHITE);//设置字体颜色  
            if (this.font != null) 
               g.setFont(this.font); 
            // 验证输出位置的纵坐标和横坐标  
            if (x >= h || y >= w) {  
                this.x = h - this.fontsize + 2;  
                this.y = w;  
            } else {  
                this.x = x;  
                this.y = y;  
            }  
            if (content != null) {  
                g.drawString(content.toString(), this.x, this.y);  
            }  
            g.dispose();  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
        return img;  
    }  
  
    /** 
     * 修改图片,返回修改后的图片缓冲区（输出多个文本段） xory：true表示将内容在一行中输出；false表示将内容多行输出 
     */  
    public BufferedImage modifyImage(BufferedImage img, Object[] contentArr, int x, int y, boolean xory) {  
        try {  
            int w = img.getWidth();  
            int h = img.getHeight();  
            g = img.createGraphics();  
            g.setBackground(Color.WHITE);  
            g.setColor(Color.RED);  
            if (this.font != null)  
                g.setFont(this.font);  
            // 验证输出位置的纵坐标和横坐标  
            if (x >= h || y >= w) {  
                this.x = h - this.fontsize + 2;  
                this.y = w;  
            } else {  
                this.x = x;  
                this.y = y;  
            }  
            if (contentArr != null) {  
                int arrlen = contentArr.length;  
                if (xory) {  
                    for (int i = 0; i < arrlen; i++) {  
                        g.drawString(contentArr[i].toString(), this.x, this.y);  
                        this.x += contentArr[i].toString().length()  
                                * this.fontsize / 2 + 5;// 重新计算文本输出位置  
                    }  
                } else {  
                    for (int i = 0; i < arrlen; i++) {  
                        g.drawString(contentArr[i].toString(), this.x, this.y);  
                        this.y += this.fontsize + 2;// 重新计算文本输出位置  
                    }  
                }  
            }  
            g.dispose();  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
  
        return img;  
    }  

    //修改指定图片到目标图片
    public  BufferedImage modifyImagetogeter(BufferedImage c,BufferedImage b, BufferedImage d) {  
        try {  
            int w = b.getWidth();  
            int h = b.getHeight();  
                f= d.createGraphics();  
//                f.drawImage(c, 10,40, 100,100, null); 
                f.dispose(); 
            g = d.createGraphics();  
//            g.drawImage(b,164,395, 195,195, null);  
            //左右 上下
//            x - x 坐标。
//            y - y 坐标。
//            width - 矩形的宽度。
//            height - 矩形的高度。
            g.drawImage(b,170,740,300,300, null); 
            g.dispose();  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
        return d;  
    }  

  
    public static void main(String[] args) throws FileNotFoundException, IOException {  
    	 mergeImageUtil tt = new mergeImageUtil();  
         //背景图片合并
         BufferedImage qr = tt.loadImageLocal("D:\\3.png");
         //给背景图片添加文字 
         BufferedImage beijing = tt.loadImageLocal("D:\\beijing.jpg");  
         
         BufferedImage beijing1 =tt.modifyImage(beijing,"曹原邀你一起团砍",120,50);
         
         
          //往图片上写文件  
         tt.writeImageLocal("D:\\33333.jpg", tt.modifyImagetogeter(null,qr,beijing1));  

    }  
  
}  