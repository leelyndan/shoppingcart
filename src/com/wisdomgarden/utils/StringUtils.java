package com.wisdomgarden.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @Description 字符处理类
 * @author qiani 2014-7-29下午01:45:05
 */
public class StringUtils
{
    public static List<String> getGroupResult(String source, String regex)
    {
        List<String> stringList = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find())
        {
            for (int index = 0; index <= matcher.groupCount(); index++)
            {
                stringList.add(matcher.group(index));
                
            }
            
        }
        return stringList;
    }
    
    public static boolean isEmpty(String source)
    {
        return source == null || source.isEmpty();
    }
    
}
