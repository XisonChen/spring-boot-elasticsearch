package com.xison.repository;

import com.xison.model.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * created by Xison Chen
 * on 2017/10/30-1:16
 */
@Repository
public interface CityRepository extends ElasticsearchRepository<City, Long> {

}
