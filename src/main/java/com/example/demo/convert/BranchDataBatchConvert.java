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
 * 批次字段转换器
 */
public class BranchDataBatchConvert implements Converter<String> {

    private DictTypeService typeService = applicationContext.getBean(DictTypeService.class);
    private Map<String, String> map = typeService.queryDictListToMap("批次");

    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 导入时转换，获dictName对应的dictNo
     *
     * @param cellData            单元格数据
     * @param contentProperty     内容属性
     * @param globalConfiguration 全局配置信息
     * @return 转换后的 批次dictNo
     */
    @Override
    public String convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        for (String key : map.keySet()) {
            if (map.get(key).equals(cellData.getStringValue())) {
                return key;
            }
        }
        return null;
    }

    /**
     * 导出时转换，获dictNo对应的dictName
     *
     * @param value               转换前的dictNo
     * @param contentProperty     内容属性
     * @param globalConfiguration 全局配置信息
     * @return 转换后的批次 dictName
     */
    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (map.containsKey(value)) {
            return new WriteCellData<>(map.get(value));
        }
        return new WriteCellData<>();
    }
}
