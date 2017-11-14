package com.xison.repository.base.impl;

import com.xison.model.Novel;
import com.xison.repository.base.CustomRepository;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * created by Xison Chen
 * on 2017/11/15-0:05
 */
@Repository
public class CustomRepositoryImpl implements CustomRepository {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public String createNovel(Novel novel) {
        IndexQuery query = new IndexQuery();
        query.setId(String.valueOf(novel.getId()));
        query.setObject(novel);
        query.setIndexName("book");
        query.setType("novel");
        return elasticsearchTemplate.index(query);
    }
}
