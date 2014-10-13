package com.wisdomgarden.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import com.wisdomgarden.domain.Coupons;
/**
 * 
 * @Description ÓÅ»ÝÈ¯µ¥Àý
 * @author qiani
 * 2014-7-29ÏÂÎç01:41:38
 */
public class CouponsCollection
{
    private static CouponsCollection instance;
    private List<Coupons> couponList = new ArrayList<Coupons>();

    private CouponsCollection()
    {
    }

    public static CouponsCollection getInstance()
    {

        if (instance == null)
        {
            instance = new CouponsCollection();
        }
        return instance;
    }

    public List<Coupons> getCouponList()
    {
        return couponList;
    }

    public void setCouponList(List<Coupons> couponList)
    {
        this.couponList = couponList;
    }

}
