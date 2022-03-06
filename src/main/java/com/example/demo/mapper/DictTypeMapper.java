package com.example.demo.mapper;

import com.example.demo.model.DictPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictTypeMapper {

    List<DictPO> queryAllByTypeName(@Param("typeName") String typeName);
}
