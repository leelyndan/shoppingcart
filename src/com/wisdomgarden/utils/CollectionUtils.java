package com.wisdomgarden.utils;

import java.util.List;
/**
 * 
 * @Description 集合处理类
 * @author qiani
 * 2014-7-29下午01:43:18
 */
public class CollectionUtils
{
    public static boolean isEmpty(List<?> list)
    {
        return list == null || list.isEmpty();
    }

}
