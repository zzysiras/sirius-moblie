package com.cesaba.siriusmobliemain.service;

import com.cesaba.siriusmobliemain.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileInfo save(MultipartFile file) throws IOException;

    void delete(String id);
}
