package com.wisdomgarden.discount;

import com.wisdomgarden.domain.Cargo;
/**
 * 
 * @Description �ۿ��㷨
 * @author qiani
 * 2014-7-29����01:38:33
 */
public class DiscountStrategy implements IStrategy
{

    @Override
    public Double calculatePrice(Cargo cargo)
    {
        if (cargo == null)
        {
            return new Double(0);
        }
        return cargo.getNumber() * cargo.getPrice()
                * cargo.getCategory().getDiscountInfo().getDiscountPercent();
    }

}
