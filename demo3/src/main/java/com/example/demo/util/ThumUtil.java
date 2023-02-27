package com.example.demo.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ThumUtil {
    private static void generateFixedSizeImage(){
        try {
            Thumbnails.of("").size(80,80).toFile("data/newmeinv.jpg");
        } catch (IOException e) {
            System.out.println("原因: " + e.getMessage());
        }
    }

    /**
     * 对原图加水印,然后顺时针旋转90度,最后压缩为80%保存
     */
    private static void generateRotationWatermark(){
        try {
            Thumbnails.of("C:\\Users\\c1861\\Desktop\\1.jpg").
                    size(6000,6000). // 缩放大小
                    rotate(0). // 顺时针旋转90度
                    watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("C:\\Users\\c1861\\Desktop\\shuiyin.png")),1). //水印位于右下角,半透明
                    outputQuality(1.0). // 图片压缩80%质量
                    toFile("C:\\Users\\c1861\\Desktop\\6.jpg");
        } catch (IOException e) {
            System.out.println("原因: " + e.getMessage());
        }
    }

    /**
     * 转换图片格式,将流写入到输出流
     */
    private static void generateOutputstream(){
        try(OutputStream outputStream = new FileOutputStream("data/2016010208_outputstream.png")) { //自动关闭流
            Thumbnails.of("data/2016010208.jpg").
                    size(500,500).
                    outputFormat("png"). // 转换格式
                    toOutputStream(outputStream); // 写入输出流
        } catch (IOException e) {
            System.out.println("原因: " + e.getMessage());
        }
    }

    /**
     * 按比例缩放图片
     */
    private static void generateScale(){
        try {
            Thumbnails.of("data/2016010208.jpg").
                    //scalingMode(ScalingMode.BICUBIC).
                            scale(0.8). // 图片缩放80%, 不能和size()一起使用
                    outputQuality(0.8). // 图片质量压缩80%
                    toFile("data/2016010208_scale.jpg");
        } catch (IOException e) {
            System.out.println("原因: " + e.getMessage());
        }
    }

    /**
     * 生成缩略图到指定的目录
     */
    private static void generateThumbnail2Directory(){
        try {
            Thumbnails.of("data/2016010208.jpg","data/meinv.jpg").
                    //scalingMode(ScalingMode.BICUBIC).
                            scale(0.8). // 图片缩放80%, 不能和size()一起使用
                    toFiles(new File("data/new/"), Rename.NO_CHANGE);//指定的目录一定要存在,否则报错
        } catch (IOException e) {
            System.out.println("原因: " + e.getMessage());
        }
    }

    /**
     * 将指定目录下所有图片生成缩略图
     */
    private static void generateDirectoryThumbnail(){
        try {
            Thumbnails.of(new File("D:\\test2").listFiles()).
                    //scalingMode(ScalingMode.BICUBIC).
                            scale(0.5). // 图片缩放80%, 不能和size()一起使用
                    toFiles(new File("D:\\images"), Rename.SUFFIX_HYPHEN_THUMBNAIL);//指定的目录一定要存在,否则报错
        } catch (IOException e) {
            System.out.println("原因: " + e.getMessage());
        }
    }
}
