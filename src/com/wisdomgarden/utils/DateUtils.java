package com.wisdomgarden.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @Description 时间处理类
 * @author qiani
 * 2014-7-29下午01:43:40
 */
public class DateUtils
{
    public static Date parse2Date(String inputedDate)
    {
        try
        {
            return new SimpleDateFormat("yyyy.MM.dd").parse(inputedDate);
        }
        catch (ParseException e)
        {
            return new Date();
        }

    }
}
