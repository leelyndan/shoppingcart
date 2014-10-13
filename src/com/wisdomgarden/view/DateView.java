package com.wisdomgarden.view;

import com.wisdomgarden.shoppingcart.ShoppingCart;
import com.wisdomgarden.utils.DateUtils;
import com.wisdomgarden.utils.IOUtils;
/**
 * 
 * @Description  �������������
 * @author qiani
 * 2014-7-29����01:47:30
 */
public class DateView extends AbstractView
{
    private String dateString;

    @Override
    public void printTipInfo()
    {
        IOUtils.print("please input current date(format:yyyy.mm.dd),end with enter key: ");
    }

    @Override
    protected void saveOrder(ShoppingCart order)
    {
        order.setCurrentDate(DateUtils.parse2Date(dateString));
        setSuccessor(new CouponsView());
    }

    @Override
    protected void input()
    {
        dateString = IOUtils
                .input("\\d{4}\\.([0][1-9]|[1][012])\\.([0-2][0-9]|3[12])");

    }

}
