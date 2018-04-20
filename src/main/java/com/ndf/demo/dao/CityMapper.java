package com.ndf.demo.dao;

import com.ndf.demo.domain.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by lin on 4/20/18.
 */
@Mapper
public interface CityMapper {

    @Select("SELECT * FROM city WHERE city_name = #{cityName}")
    @Results({
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name")
    })
    City findByName(@Param("cityName") String cityName);

    @Select("SELECT * FROM city WHERE id = #{id}")
    @Results({
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name")
    })
    City findById(@Param("id") int id);

    @Select("SELECT * FROM city LIMIT #{offset}, #{pageSize}")
    @Results({
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name")
    })
    List<City> queryForPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Insert("INSERT INTO city(province_id, city_name, description) VALUES(#{provinceId}, #{cityName}, #{description})")
    void insert(City city);

    @Update("UPDATE city SET province_id=#{provinceId}, city_name=#{cityName}, description=#{description} WHERE id =#{id}")
    void update(City city);

    @Delete("DELETE FROM city WHERE id =#{id}")
    void delete(int id);

}
