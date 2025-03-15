package com.example.spring_test.city.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("city")
public class CityEntity {

    @TableId("ID")
    private Integer id;

    @TableField("Name")
    private String name;

    @TableField("CountryCode")
    private String countryCode;

    @TableField("District")
    private String district;

    @TableField("Population")
    private Integer population;
}
