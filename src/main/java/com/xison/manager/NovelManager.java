package com.xison.manager;

import com.xison.controller.param.NovelQueryParam;
import com.xison.model.Novel;

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
    Long addNovel(Novel novel);

    /**
     * 根据id 获取
     * @param id
     * @return
     */
    Novel getById(Long id);

    /**
     * 复杂查询
     * @param param
     * @return
     */
    List<Novel> searchNovels(NovelQueryParam param);
}