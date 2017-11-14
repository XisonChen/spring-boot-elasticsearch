/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.xison.repository;

import com.xison.model.Novel;

import com.xison.repository.base.CustomRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * NovelRepository
 *
 * @author chenjj2
 * @version V1.0
 * @since 2017-11-14 10:47
 */
@Repository
public interface NovelRepository  extends ElasticsearchRepository<Novel, Long>, CustomRepository {
}
