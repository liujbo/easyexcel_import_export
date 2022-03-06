package com.example.demo.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.example.demo.service.DictTypeService;

import java.util.Map;

import static com.example.demo.ExcelImportExportDemoApplication.applicationContext;

/**
 * 频度字段转换器
 */
public class BranchDataFrequencyConvert implements Converter<String> {

    private final DictTypeService typeService = applicationContext.getBean(DictTypeService.class);
    private final Map<String, String> map = typeService.queryDictListToMap("频度");

    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 导入时转换
     *
     * @param cellData            单元格数据
     * @param contentProperty     内容属性
     * @param globalConfiguration 全局配置
     * @return 转换后的值
     */
    @Override
    public String convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        for (String key : map.keySet()) {
            if (map.get(key).equals(cellData.getStringValue())) {
                return key;
            }
        }
        return null;
    }

    /**
     * 导出时转换
     *
     * @param value               数据值
     * @param contentProperty     内容属性
     * @param globalConfiguration 全局配置
     * @return 转后的数据值
     */
    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        if (map.containsKey(value)) {
            return new WriteCellData<>(map.get(value));
        }
        return new WriteCellData<>();
    }
}
