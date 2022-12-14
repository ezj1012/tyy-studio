package com.tyy.studio.utils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;

public class SystemUtils {

    @SuppressWarnings("unchecked")
    public static <T> T readFile(File file, Class<T> cls) {
        try (FileInputStream fis = new FileInputStream(file)) {
            String string = IOUtils.toString(fis, Charset.forName("utf-8"));
            if (cls == null || cls == String.class) { return (T) string; }
            return JSON.parseObject(string, cls);
        } catch (Exception e) {
            return null;
        }
    }

}
