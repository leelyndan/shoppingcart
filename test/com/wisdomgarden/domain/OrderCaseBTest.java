package com.wisdomgarden.domain;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.wisdomgarden.shoppingcart.CouponsCollection;
import com.wisdomgarden.shoppingcart.ShoppingCart;
import com.wisdomgarden.utils.DateUtils;

public class OrderCaseBTest
{
    
    @Test
    public void testCalculateTotalMoney_caseB()
    {
        
        CouponsCollection.getInstance().getCouponList().add(null);
        // 购物车
        ShoppingCart order = new ShoppingCart();
        // 产品列表
        List<Cargo> cargoList = new ArrayList<Cargo>();
        // 所购产品
        Cargo cargo = new Cargo();
        cargo.setCargoName("ipad");
        cargo.setPrice(2000.0);
        cargo.setNumber(2);
        Category category = new Category();
        cargo.setCategory(category);
        // 加入列表
        cargoList.add(cargo);
        // 加入购物车
        order.setCargoList(cargoList);
        // 设置当前时间
        order.setCurrentDate(DateUtils.parse2Date("2011.11.11"));
        // 结算 order.calculateTotalMoney()
        Assert.assertEquals(4000.00, order.calculateTotalMoney());
    }
    
}
