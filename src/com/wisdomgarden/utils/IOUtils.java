package com.wisdomgarden.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description I/O处理类
 * @author qiani 2014-7-29下午01:44:15
 */
public class IOUtils
{
    
    public static Scanner scanner = new Scanner(System.in);
    
    public static String input()
    {
        return scanner.nextLine();
        
    }
    
    public static String input(String regex)
    {
        String nextLine = scanner.nextLine();
        if (!nextLine.matches(regex))
        {
            print("input invalid,please input again.");
            return input(regex);
        }
        return nextLine;
        
    }
    
    public static List<String> inputMultipleLine()
    {
        List<String> resultList = new ArrayList<String>();
        
        while (true)
        {
            String input = input();
            if (!"/".equals(input))
            {
                resultList.add(input);
            }
            else
            {
                return resultList;
            }
            
        }
        
    }
    
    public static List<String> inputMultipleLine(String regex)
    {
        List<String> resultList = new ArrayList<String>();
        
        while (true)
        {
            String input = IOUtils.input();
            if (!multipleInputValid(input, regex))
            {
                print("input invalid,please input again.");
                return inputMultipleLine(regex);
                
            }
            if (!"/".equals(input))
            {
                resultList.add(input);
            }
            else
            {
                return resultList;
            }
            
        }
        
    }
    
    public static boolean multipleInputValid(String input, String regex)
    {
        return input.matches(regex);
    }
    
    public static void print(String info)
    {
        System.out.println(info);
        
    }
    
}
