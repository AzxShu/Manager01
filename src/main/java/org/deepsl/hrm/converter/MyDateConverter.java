package org.deepsl.hrm.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateConverter implements Converter<Date, String> {

    @Override
    public String convert(Date date) {
        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = bf.format(date);
        return format;
    }
}
