package com.xison.manager.impl;

import com.xison.controller.param.NovelQueryParam;
import com.xison.manager.NovelManager;
import com.xison.model.Novel;
import com.xison.repository.NovelRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

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
    public Long addNovel(Novel novel) {
        Novel entity = novelRepository.save(novel);
        return entity.getId();
    }

    @Override
    public Novel getById(Long id) {
        Optional<Novel> optional = novelRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Novel> searchNovels(NovelQueryParam param) {
        Pageable pageable = new PageRequest(param.getPageNo(), param.getPageSize());

        String author = param.getAuthor();
        String title = param.getTitle();
        Long ltePrice = param.getLtePrice();
        Long gtePrice = param.getGtePrice();
        Integer lteWordCount = param.getLteWordCount();
        Integer gteWordCount = param.getGteWordCount();
        String ltePublishDate = param.getLtePublishDate();
        String gtePublishDate = param.getGtePublishDate();

        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
        long ltePublishTime = StringUtils.isNotBlank(ltePublishDate) ?
            format.parseDateTime(ltePublishDate).getMillis() :
            0;
        long gtePublishTime = StringUtils.isNotBlank(gtePublishDate) ?
            format.parseDateTime(gtePublishDate).getMillis() :
            0;

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

        if (StringUtils.isNotBlank(ltePublishDate) && StringUtils.isBlank(gtePublishDate)) {
            boolQuery.must(QueryBuilders.rangeQuery("publishDate").lte(ltePublishTime));
        }
        if (StringUtils.isBlank(ltePublishDate) && StringUtils.isNotBlank(gtePublishDate)) {
            boolQuery.must(QueryBuilders.rangeQuery("publishDate").gte(gtePublishTime));
        }
        if (StringUtils.isNotBlank(ltePublishDate) && StringUtils.isNotBlank(gtePublishDate)) {
            boolQuery.must(QueryBuilders.rangeQuery("publishDate").lte(ltePublishTime).gte(gtePublishTime));
        }

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(boolQuery).build();

        LOGGER.info("DSL:{}", searchQuery.getQuery().toString());

        Page<Novel> searchResult = novelRepository.search(searchQuery);

        return searchResult.getContent();
    }
}