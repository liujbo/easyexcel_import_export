package com.example.demo.mapper;

import com.example.demo.model.BranchDataPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BranchDataMapper {

    List<BranchDataPO> query();

    void batchAdd(List<BranchDataPO>  branchDataPOList);
}
