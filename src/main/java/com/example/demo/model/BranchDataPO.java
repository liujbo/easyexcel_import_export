package com.example.demo.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.example.demo.annotation.DropDownSetField;
import com.example.demo.convert.BranchDataBatchConvert;
import com.example.demo.convert.BranchDataFrequencyConvert;
import com.example.demo.service.impl.DropDownBatchImpl;
import com.example.demo.service.impl.DropDownFrequencyImpl;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class BranchDataPO implements Serializable {

    @ExcelIgnore
    private Long id;

    @ExcelProperty(value = "报送分行机构号")
    @ColumnWidth(value = 20)
    private String branchNo;

    @ExcelProperty(value = "报送分行")
    @ColumnWidth(value = 30)
    private String branchName;

    @ExcelProperty(value = "所属一级分行机构号")
    @ColumnWidth(value = 25)
    private String belongBranchNo;

    @ExcelProperty(value = "所属一级分行")
    @ColumnWidth(value = 30)
    private String belongBranchName;

    @ExcelProperty(value = "频度", converter = BranchDataFrequencyConvert.class)
    @DropDownSetField(sourceClass = DropDownFrequencyImpl.class)
    @ColumnWidth(value = 10)
    private String frequency;

    @ExcelProperty(value = "批次", converter = BranchDataBatchConvert.class)
    @DropDownSetField(sourceClass = DropDownBatchImpl.class)
    @ColumnWidth(value = 10)
    private String batch;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @ExcelProperty(value = "报送时间")
    @DateTimeFormat(value = "yyyy-MM-dd HH:mm")
    @ColumnWidth(value = 20)
    private Date reportDate;

    @ExcelProperty(value = "报表张数")
    @ColumnWidth(value = 15)
    private Integer reportNum;

    public BranchDataPO() {
    }

    public BranchDataPO(String branchNo, String branchName, String belongBranchNo, String belongBranchName, String frequency, String batch, Date reportDate, Integer reportNum) {
        this.branchNo = branchNo;
        this.branchName = branchName;
        this.belongBranchNo = belongBranchNo;
        this.belongBranchName = belongBranchName;
        this.frequency = frequency;
        this.batch = batch;
        this.reportDate = reportDate;
        this.reportNum = reportNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBelongBranchNo() {
        return belongBranchNo;
    }

    public void setBelongBranchNo(String belongBranchNo) {
        this.belongBranchNo = belongBranchNo;
    }

    public String getBelongBranchName() {
        return belongBranchName;
    }

    public void setBelongBranchName(String belongBranchName) {
        this.belongBranchName = belongBranchName;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }
}
