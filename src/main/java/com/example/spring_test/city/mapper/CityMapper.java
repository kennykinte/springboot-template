package com.example.spring_test.city.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.spring_test.city.entity.CityEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper extends BaseMapper<CityEntity> {
}
