package com.cesaba.siriusmobliemain.Controller;


import com.cesaba.siriusmobliemain.annotation.LogAnnotation;
import com.cesaba.siriusmobliemain.entity.Index;
import com.cesaba.siriusmobliemain.entity.Video;
import com.cesaba.siriusmobliemain.mapper.IndexMapper;
import com.cesaba.siriusmobliemain.service.IndexService;
import com.cesaba.siriusmobliemain.tables.PageTableHandler;
import com.cesaba.siriusmobliemain.tables.PageTableRequest;
import com.cesaba.siriusmobliemain.tables.PagesTableResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Api(tags = "首页")
@RestController
@RequestMapping("/demo")
public class indexController {


    @Autowired
    private IndexService indexService;

    @Autowired
    private IndexMapper indexMapper;

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @LogAnnotation
    @ApiOperation(value = "上传图片")
    @PostMapping("/{name}")
    public Index setdemo(@PathVariable("name")String name ,MultipartFile file) throws IOException {
        indexMapper.deteleByName(name);
        return indexService.setdemo(name, file);
    }

    @LogAnnotation
    @ApiOperation(value = "上传视频")
    @PostMapping("/video")
    public Video saveVideo(MultipartFile file) throws IOException{
        return indexService.saveVideo(file);
    }


    @ApiOperation(value = "获取视频列表")
    @GetMapping("/videolist")
    public PagesTableResponse listVideo(PageTableRequest request){
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return indexMapper.countvideo(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<Video> list(PageTableRequest request) {
                return indexMapper.listvideo(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);

    }

    @LogAnnotation
    @GetMapping("/download/video/{id}")
    @ApiOperation(value = "视频下载")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public String downloadFile(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)throws UnsupportedEncodingException {

        Video video = indexMapper.getVideoById(id);
        String filename = video.getUrl();
        String fileUrl = "/statics" + filename;               //不用输入前边的网络协议名http:// localhost:8081 ..........
        System.out.println(fileUrl);
        return fileUrl;
    }

    @LogAnnotation
    @ApiOperation(value = "删除视频")
    @DeleteMapping("/video/{id}")
    public void delete(@PathVariable("id") Long id){
        indexService.deleteVideo(id);
    }

    @GetMapping
    @ApiOperation(value = "获取图片")
    public List<Index> getIndex(){
        return indexMapper.selectAll();
    }

    @GetMapping("/{name}")
    @ApiOperation(value = "根据名称获取图片url")
    public String getIndexByName(@PathVariable("name") String name){

        return "http://localhost:8081/statics" + indexMapper.selectByName(name).getImgUrl();
    }

    @GetMapping("/path/{name}")
    @ApiOperation(value = "根据名称获取资源路径")
    public String getPathByName(@PathVariable("name") String name){
        return indexMapper.selectByName(name).getPath();
    }


}
