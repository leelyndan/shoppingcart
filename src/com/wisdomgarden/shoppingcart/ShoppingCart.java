package com.wisdomgarden.shoppingcart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wisdomgarden.discount.DiscountStrategy;
import com.wisdomgarden.domain.Cargo;
import com.wisdomgarden.domain.Category;
import com.wisdomgarden.domain.Coupons;
import com.wisdomgarden.domain.DiscountInfo;
import com.wisdomgarden.utils.CollectionUtils;
/**
 * 
 * @Description ¹ºÎï³µ
 * @author qiani
 * 2014-7-29ÏÂÎç01:42:54
 */
public class ShoppingCart
{
    private List<Cargo> cargoList = new ArrayList<Cargo>();
    private Date currentDate = new Date();
    private Double totalAmount;

    public List<Cargo> getCargoList()
    {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList)
    {
        this.cargoList = cargoList;
    }

    public Date getCurrentDate()
    {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate)
    {
        this.currentDate = currentDate;
    }

    public Double getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public Double calculateTotalMoney()
    {
        Double sum = new Double(0);
        if (cargoList == null || cargoList.isEmpty())
        {
            setTotalAmount(sum);
            return sum;
        }
        setDiscountStrategy();

        sum = summaryCargoMoney();
        if (getCoupon() == null)
        {
            setTotalAmount(sum);
            return sum;
        }
        return calculateCouponsMoney(sum);

    }

    private Coupons getCoupon()
    {
        List<Coupons> list = CouponsCollection.getInstance().getCouponList();
        if (!CollectionUtils.isEmpty(list))
        {

            return list.get(list.size() - 1);
        }
        return new Coupons();
    }

    private Double calculateCouponsMoney(Double sum)
    {

        if (isCoupons(sum))
        {
            setTotalAmount(sum);
            return sum - getCoupon().getDiscountAmount();
        }
        setTotalAmount(sum);
        return sum;
    }

    private boolean isCoupons(Double sum)
    {
        Coupons coupons = getCoupon();
        return (coupons.getDate() != null)
                && (getCurrentDate().before(coupons.getDate()) || getCurrentDate()
                        .equals(coupons.getDate()))
                && sum >= coupons.getOriginalAmount();
    }

    private Double summaryCargoMoney()
    {
        Double sum = new Double(0);
        for (Cargo cargo : cargoList)
        {

            sum += cargo.calculateAmount();
        }
        return sum;
    }

    private void setDiscountStrategy()
    {

        for (Cargo cargo : cargoList)
        {
            for (DiscountInfo discountInfo : DiscountCollection.getInstance()
                    .getDiscountInfoList())
            {
                if (discountInfo == null)
                {
                    continue;
                }
                for (Category categoryInfo : discountInfo.getCategoryList())
                {
                    String discountCatetory = categoryInfo.getCatetoryName();
                    String cargoCatetory = cargo.getCategory()
                            .getCatetoryName();
                    DiscountInfo discount = cargo.getCategory()
                            .getDiscountInfo();
                    if (isDiscount(discount, discountCatetory, cargoCatetory))
                    {
                        cargo.getCategory().setStrategy(new DiscountStrategy());
                    }
                }

            }

        }
    }

    private boolean isDiscount(DiscountInfo discountInfo,
            String discountCatetory, String cargoCatetory)
    {
        return discountCatetory.equals(cargoCatetory)
                && getCurrentDate().equals(discountInfo.getDate());
    }
}
