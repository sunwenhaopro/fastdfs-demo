package com.example.demo.service.impl;

import com.example.demo.mapper.PhotoMapper;
import com.example.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoMapper photoMapper;

    public int addPhoto(String url){
       return photoMapper.addPhoto(url);
    }
}
