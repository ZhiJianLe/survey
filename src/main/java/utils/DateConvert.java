package utils;

import org.springframework.core.convert.converter.Converter;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LZJ on 2022/1/4 16:49
 * 对日期进行转换
 */
public class DateConvert implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        //定义一个日期格式化对象
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //进行转化
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
