package com.xison.repository.base;

import com.xison.model.Novel;

/**
 * created by Xison Chen
 * on 2017/11/14-23:55
 */
public interface CustomRepository {

    /**
     * save novel
     *
     * @return
     */
    String createNovel(Novel novel);
}
