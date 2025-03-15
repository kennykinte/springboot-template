package com.example.spring_test.city.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_test.city.entity.CityEntity;
import com.example.spring_test.city.mapper.CityMapper;
import org.springframework.stereotype.Service;

@Service
public class CityMybatisPlusService extends ServiceImpl<CityMapper, CityEntity> {
}
