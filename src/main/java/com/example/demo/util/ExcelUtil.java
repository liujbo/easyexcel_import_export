package com.example.demo.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.example.demo.annotation.DropDownSetField;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {

    public static ExcelWriterBuilder getExcelWriterBuilder(HttpServletResponse response, String fileName, Class<?> clazz) throws Exception {
        //表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //内容样式(居左显示)
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);

        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        return EasyExcel.write(getOutputStream(response, fileName), clazz)
                .autoCloseStream(true)
                .excelType(ExcelTypeEnum.XLSX)
                .registerWriteHandler(horizontalCellStyleStrategy);
    }

    private static OutputStream getOutputStream(HttpServletResponse response, String fileName) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        return response.getOutputStream();
    }

    /**
     * 获取下拉字段的枚举值
     *
     * @param clazz 下拉字段所在类
     * @return 下拉字段枚举值map
     */
    public static Map<Integer, String[]> getDropDownMap(Class<?> clazz) {
        //响应字典的下拉数据集合
        Map<Integer, String[]> map = new HashMap<>();
        //获取该类声明的所有字段
        Field[] fields = clazz.getDeclaredFields();
        Field field;
        //循环判断哪些字段有下拉数据集，并获取
        for (int i = 0; i < fields.length; i++) {
            field = fields[i];
            DropDownSetField dropDownSetField = field.getAnnotation(DropDownSetField.class);
            if (null != dropDownSetField) {
                String[] sources = ResolveDropAnnotationUtil.resolve(dropDownSetField);
                if (null != sources && sources.length > 0) {
                    map.put(i, sources);
                }
            }
        }
        return map;
    }
}
