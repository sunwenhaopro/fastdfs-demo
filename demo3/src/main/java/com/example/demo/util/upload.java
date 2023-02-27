package com.example.demo.util;

import com.example.demo.config.FastDFSUtils;
import com.example.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class upload {
/**
 *@author CtrlCver
 *@data 2023/2/26
 *@description:  批量上传一个文件夹里的文件
 */
@Resource
    PhotoService photoService;
@Value("${fastdfs.nginx.host}")
String nginxHost;
    public String upload() throws IOException {
        List<String> urlList=new ArrayList<>();
        File file = new File("D:\\images");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file1 = files[i];
            MultipartFile cMultiFile = new MockMultipartFile("file", file1.getName(), "image/jpeg", new FileInputStream(file1));
            String fileId = FastDFSUtils.upload(cMultiFile);
            String url = nginxHost + fileId;
            urlList.add(url);
            photoService.addPhoto(url);
        }
       return urlList.toString();
    }
}
