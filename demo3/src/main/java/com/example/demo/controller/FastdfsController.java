package com.example.demo.controller;

import com.example.demo.config.FastDFSUtils;
import com.example.demo.service.PhotoService;
import com.example.demo.util.MultToFile;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static net.coobird.thumbnailator.Thumbnails.*;

@RestController
public class FastdfsController {
    @Autowired
    PhotoService photoService;


    @Value("${fastdfs.nginx.host}")
    String nginxHost;

    @PostMapping("/upload1")
    public String uploadFile1(@RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
        List<String> SuccessList = new ArrayList<>();
        List<String> FailList = new ArrayList<>();
        for (int i = 0; i < multipartFiles.length; i++) {
            String fileId = FastDFSUtils.upload(multipartFiles[i]);
            String url = nginxHost + fileId;
            SuccessList.add(url);
            photoService.addPhoto(url);
            System.out.println(url);
        }
        String result="上传成功："+SuccessList.toString()+"上传失败:"+FailList.toString();
        return result;
    }

    @PostMapping("/upload2")
    public String uploadFile2(@RequestParam("file") MultipartFile multipartFile) {
        try {
            String fieldId = FastDFSUtils.upload(multipartFile);
            String url = nginxHost + fieldId;
            photoService.addPhoto(url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传收失败";
        }
    }

    @PostMapping("/upload3")
    public String uploadFile3(@RequestParam("file") MultipartFile multipartFile) {
        try {
            File file1 = MultToFile.multpartfileTofile(multipartFile);
            Thumbnails.of(file1)
                    .scale(0.5)
                    .outputQuality(0.5f)
                    .toFile(file1);
            MultipartFile cMultiFile = new MockMultipartFile("file", file1.getName(), "image/jpeg", new FileInputStream(file1));
            String fieldId = FastDFSUtils.upload(cMultiFile);
            String url = nginxHost + fieldId;
            photoService.addPhoto(url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传收失败";
        }
    }

    @PostMapping("/upload4")
    public String uploadFile4(@RequestParam("file") MultipartFile[] multipartFile) {
        List<String> SuccessList=new ArrayList<>();
        List<String> FailList=new ArrayList<>();
                for(int i = 0; i < multipartFile.length; i++) {
                    try {
                        File file1 = MultToFile.multpartfileTofile(multipartFile[i]);
                        Thumbnails.of(file1)
                                .scale(0.5)
                                .outputQuality(0.5f)
                                .toFile(file1);
                        MultipartFile cMultiFile = new MockMultipartFile("file", file1.getName(), "image/jpeg", new FileInputStream(file1));
                        String fieldId = FastDFSUtils.upload(cMultiFile);
                        String url = nginxHost + fieldId;
                        SuccessList.add(url);
                        photoService.addPhoto(url);
                    } catch (Exception e) {
                        FailList.add(multipartFile[i].getOriginalFilename());
                        e.printStackTrace();
                    }
                }
                    String result="上传成功："+SuccessList.toString()+"\n"+"上传失败:"+FailList.toString();
                    return result;
        }
    }


