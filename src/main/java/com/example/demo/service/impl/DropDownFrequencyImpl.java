package com.example.demo.service.impl;

import com.example.demo.service.DictTypeService;
import com.example.demo.service.DropDownSetInterface;

import java.util.List;

import static com.example.demo.ExcelImportExportDemoApplication.applicationContext;

/**
 * 频度字段，动态下拉框数据实现类
 */
public class DropDownFrequencyImpl implements DropDownSetInterface {


    private final DictTypeService typeService = applicationContext.getBean(DictTypeService.class);
    //频度 类型名称为：频度
    private final List<String> list = typeService.queryDictToListToString("频度");


    @Override
    public String[] getSource() {
        return list.toArray(new String[list.size()]);
    }
}
