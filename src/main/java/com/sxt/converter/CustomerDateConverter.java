package com.sxt.converter;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

public class CustomerDateConverter implements Converter<String, Date> {


    @Override
    public Date convert(String s) {
        try {
            return DateUtils.parseDate(s,"yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
