package com.example.demo.service;

import com.example.demo.mapper.DictTypeMapper;
import com.example.demo.model.DictPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DictTypeService {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    /**
     * 转换为dictNo,dictName  map集合
     *
     * @param typeName 类型名称
     * @return dictNo, dictName  map集合
     */
    public Map<String, String> queryDictListToMap(String typeName) {
        List<DictPO> dictPOList = dictTypeMapper.queryAllByTypeName(typeName);
        return dictPOList.stream().collect(Collectors.toMap(DictPO::getDictNo, DictPO::getDictName));
    }

    /**
     * 转换为 dictName list集合
     *
     * @param typeName 类型名称
     * @return dictName list集合
     */
    public List<String> queryDictToListToString(String typeName) {
        List<DictPO> dictPOList = dictTypeMapper.queryAllByTypeName(typeName);
        return dictPOList.stream().sorted(Comparator.comparing(DictPO::getDictNo)).map(DictPO::getDictName).collect(Collectors.toList());
    }

}
