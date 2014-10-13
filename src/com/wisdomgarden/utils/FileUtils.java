package com.wisdomgarden.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * 
 * @Description 文件处理类
 * @author qiani
 * 2014-7-29下午01:43:58
 */
public class FileUtils
{
    public static String readFile(String fileName)
    {
        File file = new File(fileName);
        StringBuffer content = new StringBuffer();
        BufferedReader in = null;
        if (!file.exists())
        {
            System.err.println("Can't Find " + fileName);
        }

        try
        {
            in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null)
            {
                content.append(str + "\r\n");
            }

        }
        catch (IOException e)
        {
            try
            {
                in.close();
            }
            catch (Exception e2)
            {
            }
        }
        return content.toString();
    }
}
