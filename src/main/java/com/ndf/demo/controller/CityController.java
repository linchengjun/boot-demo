package com.ndf.demo.controller;

import com.ndf.demo.common.Response;
import com.ndf.demo.dao.CityMapper;
import com.ndf.demo.domain.City;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lin on 4/20/18.
 */
@RestController
public class CityController {

    @Autowired
    private CityMapper cityMapper;

    @RequestMapping(value = "/api/city/queryByName", method = RequestMethod.GET)
    public Response<City> queryByName(@RequestParam("city_name") String cityName){
        if(StringUtils.isBlank(cityName)){
            return new Response<>();
        }
        City city = cityMapper.findByName(cityName);
        return new Response<>(city);
    }

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public Response<City> findOneCity(@PathVariable("id") Integer id) {
        if(id == null){
            return null;
        }
        City city = cityMapper.findById(id);
        return new Response<>(city);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public Response<Boolean> createCity(@RequestBody City city) {
        cityMapper.insert(city);
        return new Response<>(true);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.PUT)
    public Response<Boolean> modifyCity(@RequestBody City city) {
        cityMapper.update(city);
        return new Response<>(true);
    }

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.DELETE)
    public Response<Boolean> deleteCity(@PathVariable("id") Integer id) {
        if(id == null){
            return new Response<>(false);
        }
        cityMapper.delete(id);
        return new Response<>(true);
    }

}
