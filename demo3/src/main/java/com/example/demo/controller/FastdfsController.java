package com.example.demo.controller;

import com.example.demo.config.FastDFSUtils;
import com.example.demo.service.PhotoService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

@RestController
public class FastdfsController {
@Autowired
    PhotoService photoService;
@GetMapping("/666")
public int t(){
    return   photoService.addPhoto("666");
}


    @Value("${fastdfs.nginx.host}")
    String nginxHost;

    @GetMapping("/upload")
    public int updateHrUserface() throws IOException {
        File file = new File("D:\\images");
        File [] files = file.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            File file1=files[i];
            MultipartFile cMultiFile = new MockMultipartFile("file", file1.getName(), "image/jpeg", new FileInputStream(file1));
            String fileId = FastDFSUtils.upload(cMultiFile);
            String url = nginxHost + fileId;
            photoService.addPhoto(url);
            System.out.println(url);
        }
        return 1;
    }
}
