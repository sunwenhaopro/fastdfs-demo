package com.example.demo;

import com.example.demo.mapper.PhotoMapper;
import com.example.demo.service.PhotoService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class Demo3ApplicationTests {

    @Test
    void contextLoads() throws IOException {
        File file1=new File("D:\\1.jpg");
        Thumbnails.of(file1)
                .scale(0.5)
                .outputQuality(0.5f)
                .toFile(file1);
    }
}
