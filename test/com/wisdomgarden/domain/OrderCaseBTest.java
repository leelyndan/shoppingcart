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
        // ���ﳵ
        ShoppingCart order = new ShoppingCart();
        // ��Ʒ�б�
        List<Cargo> cargoList = new ArrayList<Cargo>();
        // ������Ʒ
        Cargo cargo = new Cargo();
        cargo.setCargoName("ipad");
        cargo.setPrice(2000.0);
        cargo.setNumber(2);
        Category category = new Category();
        cargo.setCategory(category);
        // �����б�
        cargoList.add(cargo);
        // ���빺�ﳵ
        order.setCargoList(cargoList);
        // ���õ�ǰʱ��
        order.setCurrentDate(DateUtils.parse2Date("2011.11.11"));
        // ���� order.calculateTotalMoney()
        Assert.assertEquals(4000.00, order.calculateTotalMoney());
    }
    
}
