package com.wisdomgarden.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.wisdomgarden.domain.Coupons;
import com.wisdomgarden.domain.DiscountInfo;
import com.wisdomgarden.shoppingcart.CouponsCollection;
import com.wisdomgarden.shoppingcart.DiscountCollection;
import com.wisdomgarden.shoppingcart.ShoppingCart;
import com.wisdomgarden.utils.CollectionUtils;
import com.wisdomgarden.utils.DateUtils;
import com.wisdomgarden.utils.IOUtils;
import com.wisdomgarden.utils.StringUtils;
/**
 * 
 * @Description 优 惠 券 信 息输入
 * @author qiani
 * 2014-7-29下午01:46:51
 */
public class CouponsView extends AbstractView
{

    private String coupons;

    @Override
    public void printTipInfo()
    {
        IOUtils.print("please input coupons(format:date total-money discount),end with enter key:");

    }

    @Override
    protected void input()
    {
        coupons =input("(\\d{4}\\.([0][1-9]|[1][012])\\.([0-2][0-9]|3[12]))\\s+(\\d+(\\.\\d+)?)\\s+(\\d+(\\.\\d+)?)");

    }

    @Override
    protected void saveOrder(ShoppingCart order)
    {
        fillOrderCoupons(order);
        printOrder(order);
        List<DiscountInfo> discountInfoList = new ArrayList<DiscountInfo>();
        DiscountCollection.getInstance().setDiscountInfoList(discountInfoList);
        List<Coupons> couponList = new ArrayList<Coupons>();
        CouponsCollection.getInstance().setCouponList(couponList);
        new DiscountView().requestHandler(new ShoppingCart());

    }

    private void fillOrderCoupons(ShoppingCart order)
    {
        List<String> couponList = StringUtils.getGroupResult(coupons,
                "(.+)\\s+(.+)\\s+(.+)");
        if (CollectionUtils.isEmpty(couponList))
        {
            return;
        }
        Coupons coupons = new Coupons();
        coupons.setDate(DateUtils.parse2Date(couponList.get(1)));
        coupons.setOriginalAmount(Double.valueOf(couponList.get(2)));
        coupons.setDiscountAmount(Double.valueOf(couponList.get(3)));
        CouponsCollection.getInstance().getCouponList().add(coupons);
    }

    private void printOrder(ShoppingCart order)
    {
        IOUtils.print("******************************");
        IOUtils.print("Total: " + keepTwoDecimal(order.calculateTotalMoney()));

    }

    public Double keepTwoDecimal(Double value)
    {
        BigDecimal bg = new BigDecimal(value);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static String input(String regex)
    {
        String nextLine = IOUtils.scanner.nextLine();
        if (!nextLine.matches(regex) && nextLine.trim().length() > 0)
        {
            IOUtils.print("input invalid,please input again.");
            return input(regex);
        }
        return nextLine;

    }
}
