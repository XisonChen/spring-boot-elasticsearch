package com.xison.controller;

import com.xison.controller.param.NovelQueryParam;
import com.xison.manager.NovelManager;
import com.xison.model.Novel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * NovelRestController
 *
 * @author chenjj2
 * @version V1.0
 * @since 2017-11-14 10:53
 */
@RestController
public class NovelRestController {

    @Resource
    private NovelManager novelManager;

    @RequestMapping(value = "/novel/add", method = RequestMethod.POST)
    public Long newNovel(@RequestBody Novel novel) {
       return novelManager.addNovel(novel);
    }

    @RequestMapping(value = "/novel/get", method = RequestMethod.POST)
    public Novel getNovelById(@RequestParam(value = "id") Long id) {
        return novelManager.getById(id);
    }

    @RequestMapping(value = "/novel/search", method = RequestMethod.POST)
    public List<Novel> searchNovel(@RequestBody NovelQueryParam param) {
        return novelManager.searchNovels(param);
    }

}