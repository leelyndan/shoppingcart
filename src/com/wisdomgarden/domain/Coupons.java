package com.wisdomgarden.domain;

import java.util.Date;
/**
 * 
 * @Description ÓÅ»ÝÈ¯
 * @author qiani
 * 2014-7-29ÏÂÎç01:40:45
 */
public class Coupons
{

    private Date date;
    private Double originalAmount;
    private Double discountAmount;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Double getOriginalAmount()
    {
        return originalAmount;
    }

    public void setOriginalAmount(Double originalAmount)
    {
        this.originalAmount = originalAmount;
    }

    public Double getDiscountAmount()
    {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount)
    {
        this.discountAmount = discountAmount;
    }

}
