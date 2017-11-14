package com.xison.manager;

import com.xison.controller.param.NovelQueryParam;
import com.xison.model.Novel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * NovelManager
 *
 * @author chenjj2
 * @version V1.0
 * @since 2017-11-14 10:50
 */
public interface NovelManager {

    /**
     * 新增 或 更新
     * @param novel
     * @return
     */
    Long saveNovel(Novel novel);

    /**
     * 新增
     * @param novel
     * @return
     */
    String indexNovel(Novel novel);

    /**
     * 删除
     * @param novel
     */
    void deleteNovel(Novel novel);

    /**
     * 根据id 获取
     * @param id
     * @return
     */
    Novel getById(Long id);


    /**
     * 根据author query
     * @param author
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Novel> novelsByAuthor(String author, Integer pageNo, Integer pageSize);

    /**
     * 根据title query,并根据price 排序
     * @param title
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Novel> novelsByTitleAndSortByPrice(String title, Integer pageNo, Integer pageSize);

    /**
     * 根据价格区间 query 并根据price 从低到高排序
     * @param ltePrice
     * @param gtePrice
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Novel> novelsByPrices(Long ltePrice, Long gtePrice, Integer pageNo, Integer pageSize);

    /**
     * 综合查询
     * @param param
     * @return
     */
    List<Novel> searchNovels(NovelQueryParam param);
}