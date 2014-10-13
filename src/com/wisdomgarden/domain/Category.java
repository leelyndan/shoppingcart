package com.wisdomgarden.domain;

import com.wisdomgarden.discount.IStrategy;
import com.wisdomgarden.discount.NormalStrategy;
/**
 * 
 * @Description 产品品类
 * @author qiani
 * 2014-7-29下午01:40:17
 */
public class Category
{

    private String catetoryName;
    private DiscountInfo discountInfo;
    private IStrategy strategy = new NormalStrategy();

    public String getCatetoryName()
    {
        return catetoryName;
    }

    public void setCatetoryName(String catetoryName)
    {
        this.catetoryName = catetoryName;
    }

    public DiscountInfo getDiscountInfo()
    {
        return discountInfo == null ? new DiscountInfo() : discountInfo;
    }

    public void setDiscountInfo(DiscountInfo discountInfo)
    {
        this.discountInfo = discountInfo;
    }

    public IStrategy getStrategy()
    {
        return strategy;
    }

    public void setStrategy(IStrategy strategy)
    {

        this.strategy = strategy;
    }

}
