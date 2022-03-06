package com.example.demo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.model.BranchDataPO;

import java.util.ArrayList;
import java.util.List;

/**
 * 导入文件监听器
 */
public class BranchDataListener extends AnalysisEventListener<BranchDataPO> {

    private List<BranchDataPO> dataList = new ArrayList<>();

    public BranchDataListener() {
    }

    @Override
    public void invoke(BranchDataPO branchDataPO, AnalysisContext analysisContext) {
        if (branchDataPO.getBranchNo() == null){
            return;
        }
        dataList.add(branchDataPO);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<BranchDataPO> getDataList() {
        return dataList;
    }

    public void setDataList(List<BranchDataPO> dataList) {
        this.dataList = dataList;
    }
}
