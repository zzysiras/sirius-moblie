package com.cesaba.siriusmobliemain.service.impl;


import com.cesaba.siriusmobliemain.entity.Index;
import com.cesaba.siriusmobliemain.entity.Video;
import com.cesaba.siriusmobliemain.mapper.IndexMapper;
import com.cesaba.siriusmobliemain.service.IndexService;
import com.cesaba.siriusmobliemain.utils.FileUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class IndexServiceImpl implements IndexService{

    @Autowired
    private IndexMapper indexMapper;

    @Autowired
    private IndexService indexService;

    @Value("${files.path}")
    private String filesPath;

    @Override
    public Index setdemo(String name ,MultipartFile file) throws IOException {
        String fileOrigName = file.getOriginalFilename();
        if (!fileOrigName.contains(".")){
            throw new IllegalArgumentException("缺少文件后缀");
        }
        String md5 = FileUtil.fileMd5(file.getInputStream());
        Index index = indexMapper.selectByName(name);


        fileOrigName = fileOrigName.substring(fileOrigName.lastIndexOf("."));
        String pathname = FileUtil.getPath() + md5 + fileOrigName;
        String fullPath = filesPath + pathname;
        FileUtil.saveFile(file, fullPath);



        index = new Index();
        index.setName(name);
        index.setPath(fullPath);
        index.setImgUrl(pathname);
        index.setWords("");

        indexMapper.set(index);

        return index;

    }

    @Override
    public Video saveVideo(MultipartFile file) throws IOException{
        String fileOrigName = file.getOriginalFilename();
        if (!fileOrigName.contains(".")){
            throw new IllegalArgumentException("缺少文件后缀");
        }
        String md5 = FileUtil.fileMd5(file.getInputStream());
        //fileOrigName = fileOrigName.substring(fileOrigName.lastIndexOf("."));
        String pathname = FileUtil.getPath() + md5 + fileOrigName;
        String fullPath = filesPath + pathname;
        FileUtil.saveFile(file, fullPath);



        Video video = new Video();
        video.setVideoname(fileOrigName);
        video.setPath(fullPath);
        video.setUrl(pathname);

        indexMapper.saveVideo(video);

        return video;


    }

    @Override
    public void deleteVideo(Long id){
        Video video = indexMapper.getVideoById(id);
        if (video!=null){
            String fullPath = video.getPath();
            FileUtil.deleteFile(fullPath);

            indexMapper.deleteVideo(id);
        }
    }


}
