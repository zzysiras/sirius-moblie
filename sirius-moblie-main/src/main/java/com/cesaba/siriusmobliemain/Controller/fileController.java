package com.cesaba.siriusmobliemain.Controller;

import com.cesaba.siriusmobliemain.annotation.LogAnnotation;
import com.cesaba.siriusmobliemain.entity.FileInfo;
import com.cesaba.siriusmobliemain.mapper.FileMapper;
import com.cesaba.siriusmobliemain.service.FileService;
import com.cesaba.siriusmobliemain.tables.PageTableHandler;
import com.cesaba.siriusmobliemain.tables.PageTableRequest;
import com.cesaba.siriusmobliemain.tables.PagesTableResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Api(tags = "文件")
@RestController
@RequestMapping("/files")
public class fileController {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Value("${files.path}")
    private String filesPath;


    @Autowired
    private FileService fileService;

    @Autowired
    private FileMapper fileMapper;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "文件上传")
    public  FileInfo uploadFile(MultipartFile file) throws IOException{
        System.out.println(file);
        return fileService.save(file);
    }

    @LogAnnotation
    @GetMapping("/download/{id}")
    @ApiOperation(value = "文件下载")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public String downloadFile(@PathVariable String id, HttpServletRequest request, HttpServletResponse response)throws UnsupportedEncodingException {

        FileInfo fileInfo = fileMapper.getById(id);
        String filename = fileInfo.getUrl();
        String fileUrl = "/statics" + filename;               //不用输入前边的网络协议名http:// localhost:8081 ..........
        System.out.println(fileUrl);
        return fileUrl;
        /*if (filename!=null){
            File file = new File(filesPath, filename);
            System.out.println(file);
            System.out.println(filename);
            System.out.println(fileUrl);
            response.setHeader("content-type","application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }System.out.println("Download the file successfully!");
            }catch (Exception e){
                System.out.println("Download the file failed!");
            }
            finally {
                if (bis != null){
                    try{
                        bis.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if (fis != null){
                    try {
                        fis.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

        }*/


    }

    @GetMapping
    @ApiOperation(value = "文件查询")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public PagesTableResponse listFiles(PageTableRequest request){
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return fileMapper.count(request.getParams()); }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<FileInfo> list(PageTableRequest request) {
                List<FileInfo> list = fileMapper.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);

    }

    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "文件删除")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public void delete(@PathVariable String id){fileService.delete(id);}

}
