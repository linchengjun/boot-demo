package com.ndf.demo;

import com.ndf.demo.dao.CityMapper;
import com.ndf.demo.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootDemoApplicationTests {

	@Autowired
	private CityMapper cityMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findByCityName(){
		String cityName = "温岭市";
		City city =  cityMapper.findByName(cityName);
		System.out.println(city);
	}

	@Test
	public void queryForPage(){
		int offset = 0;
		int pageSize = 10;
		List<City> cityList = cityMapper.queryForPage(offset, pageSize);
		System.out.println(cityList);
	}

	@Test
	public void insert(){
		City city = new City();
		city.setProvinceId(2);
		city.setCityName("深圳市");
		city.setDescription("深圳奋斗");

		cityMapper.insert(city);
	}

	@Test
	public void update(){
		City city = new City(2, "广州市", "广州很热");
		city.setId(2);

		cityMapper.update(city);
	}

	@Test
	public void delete(){
		int id = 2;
		cityMapper.delete(id);
	}

}
