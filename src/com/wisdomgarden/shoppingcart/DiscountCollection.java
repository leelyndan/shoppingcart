package com.wisdomgarden.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import com.wisdomgarden.domain.DiscountInfo;
/**
 * 
 * @Description ÕÛ¿Ûµ¥Àý
 * @author qiani
 * 2014-7-29ÏÂÎç01:42:06
 */
public class DiscountCollection
{
    private static DiscountCollection instance;
    private List<DiscountInfo> discountInfoList = new ArrayList<DiscountInfo>();

    private DiscountCollection()
    {

    }

    public static DiscountCollection getInstance()
    {
        if (instance == null)
        {

            instance = new DiscountCollection();
        }
        return instance;
    }

    public List<DiscountInfo> getDiscountInfoList()
    {
        return discountInfoList;
    }

    public void setDiscountInfoList(List<DiscountInfo> discountInfoList)
    {
        this.discountInfoList = discountInfoList;
    }

}
