package com.xison.manager.impl;

import com.xison.controller.param.NovelQueryParam;
import com.xison.manager.NovelManager;
import com.xison.model.Novel;
import com.xison.repository.NovelRepository;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * NovelManagerImpl
 *
 * @author chenjj2
 * @version V1.0
 * @since 2017-11-14 11:15
 */
@Service
public class NovelManagerImpl implements NovelManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(NovelManagerImpl.class);

    @Resource
    private NovelRepository novelRepository;

    @Override
    public Long saveNovel(Novel novel) {
        Novel entity = novelRepository.save(novel);
        return entity.getId();
    }

    @Override
    public String indexNovel(Novel novel) {
        return novelRepository.createNovel(novel);
    }

    @Override
    public void deleteNovel(Novel novel) {
        novelRepository.delete(novel);
    }

    @Override
    public Novel getById(Long id) {
        Optional<Novel> optional = novelRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Page<Novel> novelsByAuthor(String author, Integer pageNo, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);

        if (StringUtils.isBlank(author)) {
            return novelRepository.findAll(pageable);
        }

        QueryBuilder authorQuery = QueryBuilders.matchQuery("author", author);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(authorQuery).withPageable(pageable).build();

//        return novelRepository.search(authorQuery, pageable);

        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
        return novelRepository.search(searchQuery);
    }

    @Override
    public Page<Novel> novelsByTitleAndSortByPrice(String title, Integer pageNo, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);

        if (StringUtils.isBlank(title)) {
            return Page.empty();
        }
        QueryBuilder authorQuery = QueryBuilders.matchQuery("title", title);
        SortBuilder sortBuilder = SortBuilders.fieldSort("price");
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(authorQuery).withSort(sortBuilder).withPageable(pageable).build();
        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());
        return novelRepository.search(searchQuery);
//        return novelRepository.search(authorQuery, pageable);
    }

    @Override
    public Page<Novel> novelsByPrices(Long ltePrice, Long gtePrice, Integer pageNo, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize, Sort.Direction.ASC, "price");
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (Objects.nonNull(ltePrice) && Objects.isNull(gtePrice)) {
            boolQuery.filter(QueryBuilders.rangeQuery("price").lte(ltePrice));
        }
        if (Objects.isNull(ltePrice) && Objects.nonNull(gtePrice)) {
            boolQuery.filter(QueryBuilders.rangeQuery("price").gte(gtePrice));
        }
        if (Objects.nonNull(ltePrice) && Objects.nonNull(gtePrice)) {
            boolQuery.filter(QueryBuilders.rangeQuery("price").lte(ltePrice).gte(gtePrice));
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(boolQuery).build();

        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());

       return novelRepository.search(searchQuery);
    }


    public List<Novel> searchNovels(NovelQueryParam param) {
        Pageable pageable = new PageRequest(param.getPageNo(), param.getPageSize());

        String author = param.getAuthor();
        String title = param.getTitle();
        Long ltePrice = param.getLtePrice();
        Long gtePrice = param.getGtePrice();
        Integer lteWordCount = param.getLteWordCount();
        Integer gteWordCount = param.getGteWordCount();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(author)) {
            boolQuery.should(QueryBuilders.matchQuery("author", author));
        }
        if (StringUtils.isNotBlank(title)) {
            boolQuery.should(QueryBuilders.matchQuery("title", title));
        }
        if (Objects.nonNull(ltePrice) && Objects.isNull(gtePrice)) {
            boolQuery.filter(QueryBuilders.rangeQuery("price").lte(ltePrice));
        }
        if (Objects.isNull(ltePrice) && Objects.nonNull(gtePrice)) {
            boolQuery.filter(QueryBuilders.rangeQuery("price").gte(gtePrice));
        }
        if (Objects.nonNull(ltePrice) && Objects.nonNull(gtePrice)) {
            boolQuery.filter(QueryBuilders.rangeQuery("price").lte(ltePrice).gte(gtePrice));
        }

        if (Objects.nonNull(lteWordCount) && Objects.isNull(gteWordCount)) {
            boolQuery.filter(QueryBuilders.rangeQuery("wordCount").lte(lteWordCount));
        }
        if (Objects.isNull(lteWordCount) && Objects.nonNull(gteWordCount)) {
            boolQuery.filter(QueryBuilders.rangeQuery("wordCount").gte(gteWordCount));
        }
        if (Objects.nonNull(lteWordCount) && Objects.nonNull(gteWordCount)) {
            boolQuery.filter(QueryBuilders.rangeQuery("wordCount").lte(lteWordCount).gte(gteWordCount));
        }

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(boolQuery).build();

        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());

        Page<Novel> searchResult = novelRepository.search(searchQuery);

        return searchResult.getContent();
    }


}