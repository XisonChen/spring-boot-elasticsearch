package com.xison.controller;

import com.xison.model.City;
import com.xison.manage.CityManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by Xison Chen
 * on 2017/10/30-1:18
 */
@RestController
public class CityRestController {

    @Resource
    private CityManager cityManager;

    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public Long createCity(@RequestBody City city) {
        return cityManager.saveCity(city);
    }

    @RequestMapping(value = "/api/city/search", method = RequestMethod.GET)
    public List<City> searchCity(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return cityManager.searchCity(pageNumber, pageSize, searchContent);
    }

}
