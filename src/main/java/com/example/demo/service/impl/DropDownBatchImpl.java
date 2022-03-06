package com.example.demo.service.impl;

import com.example.demo.service.DictTypeService;
import com.example.demo.service.DropDownSetInterface;

import java.util.List;

import static com.example.demo.ExcelImportExportDemoApplication.applicationContext;

/**
 * 批次字段，动态下拉框数据实现类
 */
public class DropDownBatchImpl implements DropDownSetInterface {

    private DictTypeService typeService = applicationContext.getBean(DictTypeService.class);
    //批次 类型名称为：批次
    private List<String> list = typeService.queryDictToListToString("批次");

    @Override
    public String[] getSource() {
        return list.toArray(new String[list.size()]);
    }
}
