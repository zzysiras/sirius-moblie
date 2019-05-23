package com.cesaba.siriusmobliemain.Controller;


import com.cesaba.siriusmobliemain.annotation.LogAnnotation;
import com.cesaba.siriusmobliemain.dto.NoticeReadVO;
import com.cesaba.siriusmobliemain.dto.NoticeVo;
import com.cesaba.siriusmobliemain.entity.Notice;
import com.cesaba.siriusmobliemain.entity.User;
import com.cesaba.siriusmobliemain.mapper.NoticeMapper;
import com.cesaba.siriusmobliemain.tables.PageTableHandler;
import com.cesaba.siriusmobliemain.tables.PageTableHandler.ListHandler;
import com.cesaba.siriusmobliemain.tables.PageTableHandler.CountHandler;
import com.cesaba.siriusmobliemain.tables.PageTableRequest;
import com.cesaba.siriusmobliemain.tables.PagesTableResponse;
import com.cesaba.siriusmobliemain.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "公告")
@RestController
@RequestMapping("/notices")
public class noticeController {

    @Autowired
    private NoticeMapper noticeMapper;

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @LogAnnotation
    @ApiOperation("添加公告")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:query')")
    public Notice saveNotice(@RequestBody Notice notice){
        noticeMapper.save(notice);
        return notice;
    }

    @GetMapping("/{id}")
    public Notice get(@PathVariable Long id){return noticeMapper.getById(id);}

    @ApiOperation(value = "读取公告")
    @GetMapping(params = "id")
    public NoticeVo readNotice(Long id){
        NoticeVo vo = new NoticeVo();

        Notice notice = noticeMapper.getById(id);
        if (notice ==null || notice.getStatus() == Notice.Status.DRAFT){
            return vo;
        }

        vo.setNotice(notice);

        noticeMapper.saveReadRecord(notice.getId(), UserUtil.getLoginUser().getId());//记录阅读记录

        List<User> users = noticeMapper.listReadUsers(id);
        vo.setUsers(users);

        return vo;

    }

    @LogAnnotation
    @ApiOperation(value = "更新草稿")
    @PutMapping  //更新请求
    public Notice updateNotice(@RequestBody Notice notice){
        Notice no = noticeMapper.getById(notice.getId());
        if (no.getStatus() == Notice.Status.PUBLISH){
            //发布状态无法更新只能删除
            throw new IllegalArgumentException("发布状态无法更新只能删除");
        }
        noticeMapper.update(notice);
        return notice;
    }

    @GetMapping
    public PagesTableResponse listNotice(PageTableRequest request){
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return noticeMapper.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Notice> list(PageTableRequest request) {
                return noticeMapper.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);

    }

    @LogAnnotation
    @ApiOperation(value = "删除公告")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        noticeMapper.delete(id);
    }

    @GetMapping("/count-unread")
    public Integer countUnread(){
        User user = UserUtil.getLoginUser();
        return noticeMapper.countUnread(user.getId());
    }

    @LogAnnotation
    @ApiOperation(value = "发布公告")
    @GetMapping("/published")
    public PagesTableResponse listNoticeReadVO(PageTableRequest request){
        request.getParams().put("userId", UserUtil.getLoginUser().getId());
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return noticeMapper.countNotice(request.getParams());}
            }, new ListHandler() {

            @Override
            public List<NoticeReadVO> list(PageTableRequest request){
                return noticeMapper.listNotice(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }


}




