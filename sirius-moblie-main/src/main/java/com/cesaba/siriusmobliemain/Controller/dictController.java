package com.cesaba.siriusmobliemain.Controller;


import com.cesaba.siriusmobliemain.entity.Dict;
import com.cesaba.siriusmobliemain.mapper.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dicts")
public class dictController {

    @Autowired
    private DictMapper dictMapper;

    @GetMapping(params = "type")
    public List<Dict> listByType(String type) {
        return dictMapper.listByType(type);
    }


}
