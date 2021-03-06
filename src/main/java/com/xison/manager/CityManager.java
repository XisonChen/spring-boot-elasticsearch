package com.xison.manager;

import com.xison.model.City;

import java.util.List;

/**
 * created by Xison Chen
 * on 2017/10/30-1:14
 */
public interface CityManager {
    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    Long saveCity(City city);

    /**
     * 根据关键词，function score query 权重分分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);


}
