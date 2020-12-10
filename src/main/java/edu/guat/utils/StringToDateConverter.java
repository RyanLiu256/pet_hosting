package edu.guat.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义日期类型转换器
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if(source == null){
            throw new RuntimeException("日期参数为空！");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date target = null;
        try {
            target = dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return target;
    }
}
