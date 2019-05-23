package com.cesaba.siriusmobliemain.service.impl;


import com.cesaba.siriusmobliemain.entity.FileInfo;
import com.cesaba.siriusmobliemain.mapper.FileMapper;
import com.cesaba.siriusmobliemain.service.FileService;
import com.cesaba.siriusmobliemain.service.SysLogsService;
import com.cesaba.siriusmobliemain.utils.FileUtil;
import com.cesaba.siriusmobliemain.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Value("${files.path}")
    private String filesPath;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private SysLogsService sysLogsService;

    @Override
    public FileInfo save(MultipartFile file) throws IOException{
        String fileOrigName = file.getOriginalFilename();
        String originName = file.getOriginalFilename();
        System.out.println(fileOrigName);
        if (!fileOrigName.contains(".")){
            throw new IllegalArgumentException("缺少文件后缀");
        }
        String md5 = FileUtil.fileMd5(file.getInputStream());
        FileInfo fileInfo = fileMapper.getById(md5);
        if(fileInfo != null){
            fileMapper.update(fileInfo);
            return fileInfo;
        }
        fileOrigName = fileOrigName.substring(fileOrigName.lastIndexOf("."));
        String pathname = FileUtil.getPath() + md5 + fileOrigName;
        String fullPath = filesPath + pathname;
        FileUtil.saveFile(file, fullPath);

        long size = file.getSize();
        String contentType = file.getContentType();

        fileInfo = new FileInfo();
        fileInfo.setOriginName(originName);
        fileInfo.setId(md5);
        fileInfo.setContentType(contentType);
        fileInfo.setSize(size);
        fileInfo.setPath(fullPath);
        fileInfo.setUrl(pathname);
        fileInfo.setType(contentType.startsWith("image/")?1:0);

        String userid = UserUtil.getLoginUser().getId();
        sysLogsService.save(userid, "上传文件", true, originName);
        fileMapper.save(fileInfo);

        return fileInfo;

    }

    @Override
    public void delete(String id){
        FileInfo fileInfo = fileMapper.getById(id);
        if (fileInfo!=null){
            String fullPath = fileInfo.getPath();
            FileUtil.deleteFile(fullPath);
            String userid = UserUtil.getLoginUser().getId();
            sysLogsService.save(userid, "删除文件", true, fileInfo.getOriginName());

            fileMapper.delete(id);
        }
    }
}
