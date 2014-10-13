package com.wisdomgarden.shoppingcart;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wisdomgarden.utils.FileUtils;
import com.wisdomgarden.utils.StringUtils;

/**
 * 
 * @Description ²Ö¿âÊý¾Ý
 * @author qiani 2014-7-29ÏÂÎç01:42:31
 */
public class ProductWarehouse
{
    
    private static ProductWarehouse instance;
    
    private Map<String, List<String>> parentCategoryMap;
    
    private ProductWarehouse()
    {
        parentCategoryMap = getParentCategory(FileUtils.readFile("cargo.dat"));
        
    }
    
    public static ProductWarehouse getInstance()
    {
        
        if (instance == null)
        {
            instance = new ProductWarehouse();
        }
        return instance;
    }
    
    private static Map<String, List<String>> getParentCategory(String result)
    {
        
        Map<String, List<String>> parentCategory = new HashMap<String, List<String>>();
        if (StringUtils.isEmpty(result))
        {
            return Collections.emptyMap();
        }
        else
        {
            
            String[] lines = result.split("\\n");
            for (String string : lines)
            {
                List<String> groupResult = StringUtils.getGroupResult(string, "(.+):(.+)");
                
                parentCategory.put(groupResult.get(1), Arrays.asList(groupResult.get(2).split(",")));
                
            }
            
        }
        return parentCategory;
    }
    
    public Map<String, List<String>> getParentCategoryMap()
    {
        return parentCategoryMap;
    }
    
    public static void main(String[] args)
    {
        getParentCategory("");
    }
    
}
