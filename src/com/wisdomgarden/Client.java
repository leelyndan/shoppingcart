package com.wisdomgarden;

import com.wisdomgarden.shoppingcart.ShoppingCart;
import com.wisdomgarden.view.DiscountView;
/**
 * 
 * @Description �������
 * @author qiani
 * 2014-7-29����01:38:02
 */
public class Client
{
    public static void main(String[] args)
    {
        new DiscountView().requestHandler(new ShoppingCart());
    }
}
