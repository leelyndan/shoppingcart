package com.wisdomgarden;

import com.wisdomgarden.shoppingcart.ShoppingCart;
import com.wisdomgarden.view.DiscountView;
/**
 * 
 * @Description 程序入口
 * @author qiani
 * 2014-7-29下午01:38:02
 */
public class Client
{
    public static void main(String[] args)
    {
        new DiscountView().requestHandler(new ShoppingCart());
    }
}
