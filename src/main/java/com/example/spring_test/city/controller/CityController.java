package com.example.spring_test.city.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spring_test.common.dto.Response;
import com.example.spring_test.city.entity.CityEntity;
import com.example.spring_test.city.service.CityMybatisPlusService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping("/city")
public class CityController {

    @Resource
    private CityMybatisPlusService cityMybatisPlusService;

    @GetMapping("/list")
    public Response<IPage<CityEntity>> listCities(
            @RequestParam(value = "countryCode", required = false) String countryCode
            , @RequestParam(value = "pageNo", defaultValue = "1") int pageNo
            , @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        try {
            IPage<CityEntity> page = new Page<>(pageNo, pageSize);

            LambdaQueryWrapper<CityEntity> query = Wrappers.lambdaQuery();
            if (StringUtils.isNotBlank(countryCode)) {
                query.eq(CityEntity::getCountryCode, countryCode);
            }

            IPage<CityEntity> cityPage = cityMybatisPlusService.page(page, query);

            return Response.success(cityPage);
        } catch (Throwable t) {
            log.error("listCitiesError", t);
            return Response.failed(t);
        }
    }
}
