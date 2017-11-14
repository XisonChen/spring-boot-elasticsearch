package com.xison.controller;

import com.xison.controller.param.NovelQueryParam;
import com.xison.manager.NovelManager;
import com.xison.model.Novel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
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
@RequestMapping(value = "/novel")
public class NovelRestController {

    @Resource
    private NovelManager novelManager;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Long newNovel(@RequestBody Novel novel) {
       return novelManager.saveNovel(novel);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createNovel(@RequestBody Novel novel) {
        return novelManager.indexNovel(novel);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteNovel(@RequestBody Novel novel) {
        novelManager.deleteNovel(novel);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Novel getNovelById(@RequestParam(value = "id") Long id) {
        return novelManager.getById(id);
    }

    @RequestMapping(value = "/search/author", method = RequestMethod.POST)
    public Page<Novel> searchByAuthor(@RequestParam(value = "author") String author,
                                      @RequestParam(value = "pageNo") Integer pageNo,
                                      @RequestParam(value = "pageSize")  Integer pageSize) {
        return novelManager.novelsByAuthor(author, pageNo, pageSize);
    }

    @RequestMapping(value = "/search/title", method = RequestMethod.POST)
    public Page<Novel> searchByTitleAndSortByPrice(@RequestParam(value = "title") String title,
                                      @RequestParam(value = "pageNo") Integer pageNo,
                                      @RequestParam(value = "pageSize")  Integer pageSize) {
        return novelManager.novelsByTitleAndSortByPrice(title, pageNo, pageSize);
    }

    @RequestMapping(value = "/search/price", method = RequestMethod.POST)
    public Page<Novel> searchByPrice(@RequestParam(value = "ltePrice") Long ltePrice,
                                     @RequestParam(value = "gtePrice") Long gtePrice,
                                      @RequestParam(value = "pageNo") Integer pageNo,
                                      @RequestParam(value = "pageSize")  Integer pageSize) {
        return novelManager.novelsByPrices(ltePrice, gtePrice, pageNo, pageSize);
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Novel> searchNovels(@RequestBody NovelQueryParam param) {
        return novelManager.searchNovels(param);
    }
}