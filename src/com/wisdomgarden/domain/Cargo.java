package com.wisdomgarden.domain;
/**
 * 
 * @Description ²úÆ·
 * @author qiani
 * 2014-7-29ÏÂÎç01:40:02
 */
public class Cargo
{
    private String cargoName;
    private Integer number;
    private Double price;
    private Category category;

    public String getCargoName()
    {
        return cargoName;
    }

    public void setCargoName(String cargoName)
    {
        this.cargoName = cargoName;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number = number;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Double calculateAmount()
    {
        return this.category.getStrategy().calculatePrice(this);
    }

}
