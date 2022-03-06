package com.example.demo.controller;

import com.example.demo.service.BranchDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/branch")
public class BranchDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BranchDataController.class);

    @Autowired
    private BranchDataService branchDataService;

    //导入
    @PostMapping("/import")
    public void importData(@RequestParam("file") MultipartFile file) {
        try {
            branchDataService.importData(file);
        } catch (Exception e) {
            LOGGER.error("导入文件失败，错误信息：" + e);
        }
    }

    @GetMapping("/export")
    public void exportData(HttpServletResponse response) {
        try {
            branchDataService.exportData(response);
        } catch (Exception e) {
            LOGGER.error("导出文件失败，错误信息：" + e);
        }
    }

    @GetMapping("/template")
    public void exportTemplate(HttpServletResponse response) {
        try {
            branchDataService.exportTemplate(response);
        } catch (Exception e) {
            LOGGER.error("下载模板文件失败，错误信息：" + e);
        }
    }
}
