package com.example.demo.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.demo.handler.BranchDataCellWriteHandler;
import com.example.demo.listener.BranchDataListener;
import com.example.demo.mapper.BranchDataMapper;
import com.example.demo.model.BranchDataPO;
import com.example.demo.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class BranchDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BranchDataService.class);

    @Autowired
    private BranchDataMapper branchDataMapper;


    @Transactional(rollbackFor = Exception.class)
    public void importData(MultipartFile file) throws Exception {
        //监听器
        BranchDataListener branchDataListener = new BranchDataListener();
        ExcelReader excelReader = EasyExcel.read(file.getInputStream())
                .head(BranchDataPO.class)
                .registerReadListener(branchDataListener)
                .ignoreEmptyRow(true)
                .build();
        excelReader.readAll();
        excelReader.finish();

        List<BranchDataPO> branchDataPOList = branchDataListener.getDataList();
        branchDataMapper.batchAdd(branchDataPOList);
    }

    /**
     * 导出文件
     *
     * @param response 响应
     * @throws Exception
     */
    public void exportData(HttpServletResponse response) throws Exception {
        List<BranchDataPO> branchDataPOList = branchDataMapper.query();
        //组装字段下拉数据集合
        Map<Integer, String[]> map = ExcelUtil.getDropDownMap(BranchDataPO.class);

        ExcelWriter excelWriter = ExcelUtil.getExcelWriterBuilder(response, "导出文件", BranchDataPO.class)
                .registerWriteHandler(new BranchDataCellWriteHandler(map))
                .build();
        WriteSheet sheet = EasyExcel.writerSheet("报表报送时间").build();
        excelWriter.write(branchDataPOList, sheet);
        excelWriter.finish();
    }

    /**
     * 下载模板
     *
     * @param response 响应
     * @throws Exception 异常
     */
    public void exportTemplate(HttpServletResponse response) throws Exception {
        List<BranchDataPO> templateData = new ArrayList<>();
        //模板参考数据
        templateData.add(new BranchDataPO("10001", "北京市分行", "10001", "北京市分行", "1", "1", new Date(), 11));

        //组装字段下拉数据集合
        Map<Integer, String[]> map = ExcelUtil.getDropDownMap(BranchDataPO.class);

        ExcelWriter excelWriter = ExcelUtil.getExcelWriterBuilder(response, "模板文件", BranchDataPO.class)
                .registerWriteHandler(new BranchDataCellWriteHandler(map))
                .build();
        WriteSheet sheet = EasyExcel.writerSheet("报表报送时间").build();
        excelWriter.write(templateData, sheet);
        excelWriter.finish();
    }
}
