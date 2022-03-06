package com.example.demo.util;

import com.example.demo.annotation.DropDownSetField;
import com.example.demo.service.DropDownSetInterface;

import java.util.Arrays;
import java.util.Objects;

public class ResolveDropAnnotationUtil {

    public static String[] resolve(DropDownSetField dropDownSetField) {
        if (Objects.isNull(dropDownSetField)) {
            return null;
        }

        // 获取固定下拉信息
        String[] source = dropDownSetField.source();
        if (null != source && source.length > 0) {
            return source;
        }

        Class<? extends DropDownSetInterface>[] classes = dropDownSetField.sourceClass();
        if (null != classes && classes.length > 0) {
            try {
                DropDownSetInterface dropDownSetInterface = Arrays.stream(classes).findFirst().get().newInstance();
                String[] dynamicSource = dropDownSetInterface.getSource();
                if (null != dynamicSource && dynamicSource.length > 0) {
                    return dynamicSource;
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
