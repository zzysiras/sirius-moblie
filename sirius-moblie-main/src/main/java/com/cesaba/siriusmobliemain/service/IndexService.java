package com.cesaba.siriusmobliemain.service;

import com.cesaba.siriusmobliemain.entity.Index;
import com.cesaba.siriusmobliemain.entity.Video;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IndexService {

    Index setdemo(String name ,MultipartFile file) throws IOException;

    Video saveVideo(MultipartFile file) throws IOException;

    void deleteVideo(Long id);
}
