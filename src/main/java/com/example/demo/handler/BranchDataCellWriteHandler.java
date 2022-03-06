package com.example.demo.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.Map;

/**
 * 约束处理器
 */
public class BranchDataCellWriteHandler implements SheetWriteHandler {

    private Map<Integer, String[]> map = null;

    public BranchDataCellWriteHandler(Map<Integer, String[]> map) {
        this.map = map;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();
        DataValidationHelper helper = sheet.getDataValidationHelper();
        map.forEach((key, value) -> {
            //下拉列表约束数据
            DataValidationConstraint constraint = helper.createExplicitListConstraint(value);
            //设置下拉单元格的首行 末行 首列 末列
            CellRangeAddressList rangeList = new CellRangeAddressList(1, 65536, key - 1, key - 1);
            //设置约束
            DataValidation validation = helper.createValidation(constraint, rangeList);
            //阻止输入非下拉选项的值
            validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
            validation.setShowErrorBox(true);
            validation.setSuppressDropDownArrow(true);
            validation.createErrorBox("提示", "此值与单元格定义格式不一致");
            sheet.addValidationData(validation);
        });

        CellRangeAddressList addressListDate = new CellRangeAddressList(1, 65536, 6, 6);
        DataValidationConstraint constraintDate = helper.createDateConstraint(DataValidationConstraint.OperatorType.BETWEEN, "Date(1991,1,1)", "Date(9999,12,31)", "yyyy-MM-dd HH:mm");
        DataValidation validationDate = helper.createValidation(constraintDate, addressListDate);
        validationDate.createErrorBox("提示", "请输入[yyyy-MM-dd HH:mm]格式日期，范围：[1991-01-01 00:00,9999-12-31 23:59]");
        validationDate.setShowErrorBox(true);
        sheet.addValidationData(validationDate);

        CellRangeAddressList addressListInteger = new CellRangeAddressList(1, 65536, 7, 7);
        DataValidationConstraint constraintInteger = helper.createIntegerConstraint(DataValidationConstraint.OperatorType.BETWEEN, String.valueOf(0), String.valueOf(999));
        DataValidation validationInteger = helper.createValidation(constraintInteger, addressListInteger);
        validationInteger.createErrorBox("提示", "请输入[0-999]之间的整数");
        validationInteger.setShowErrorBox(true);
        sheet.addValidationData(validationInteger);

    }
}
