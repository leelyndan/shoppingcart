package com.wisdomgarden.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 
 * @Description уш©шпео╒
 * @author qiani
 * 2014-7-29обнГ01:41:08
 */
public class DiscountInfo
{

    private Date date;
    private Double discountPercent;
    private List<Category> categoryList = new ArrayList<Category>();

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Double getDiscountPercent()
    {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent)
    {
        this.discountPercent = discountPercent;
    }

    public List<Category> getCategoryList()
    {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList)
    {
        this.categoryList = categoryList;
    }

}
