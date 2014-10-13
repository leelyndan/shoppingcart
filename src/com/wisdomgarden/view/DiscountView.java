package com.wisdomgarden.view;

import java.util.ArrayList;
import java.util.List;

import com.wisdomgarden.domain.Category;
import com.wisdomgarden.domain.DiscountInfo;
import com.wisdomgarden.shoppingcart.DiscountCollection;
import com.wisdomgarden.shoppingcart.ProductWarehouse;
import com.wisdomgarden.shoppingcart.ShoppingCart;
import com.wisdomgarden.utils.DateUtils;
import com.wisdomgarden.utils.IOUtils;
import com.wisdomgarden.utils.StringUtils;

/**
 * 
 * @Description 促销信息输入
 * @author qiani 2014-7-29下午01:48:00
 */
public class DiscountView extends AbstractView
{
    private static final String REGEX =
        "(\\d{4}\\.([0][1-9]|[1][012])\\.([0-2][0-9]|3[01]))\\s*\\|\\s*([01]\\.\\d+)\\|\\s*(.+)";
    
    private List<String> resultList = new ArrayList<String>();
    
    @Override
    public void printTipInfo()
    {
        IOUtils.print("please input discount(like:2014.06.23|0.7|电子),end with 'Enter': ");
        
    }
    
    @Override
    protected void saveOrder(ShoppingCart order)
    {
        DiscountInfo discountInfo = new DiscountInfo();
        for (String discount : resultList)
        {
            if (!discount.contains("|"))
            {
                return;
                
            }
            String[] discountArray = discount.split("\\|");
            discountInfo.setDate(DateUtils.parse2Date(discountArray[0]));
            discountInfo.setDiscountPercent(Double.valueOf(discountArray[1]));
            Category category = new Category();
            category.setCatetoryName(discountArray[2]);
            category.setDiscountInfo(discountInfo);
            discountInfo.getCategoryList().add(category);
        }
        DiscountCollection.getInstance().getDiscountInfoList().add(discountInfo);
        setSuccessor(new CargoListView());
    }
    
    @Override
    protected void input()
    {
        inputMultipleLine(REGEX);
        
    }
    
    public void inputMultipleLine(String regex)
    {
        
        while (true)
        {
            String input = IOUtils.input();
            if (input.isEmpty())
            {
                return;
            }
            if (!IOUtils.multipleInputValid(input, regex))
            {
                IOUtils.print("input invalid,please input again.");
                inputMultipleLine(regex);
                
            }
            List<String> groupResult = StringUtils.getGroupResult(input, REGEX);
            if (!ProductWarehouse.getInstance().getParentCategoryMap().containsKey(groupResult.get(5)))
            {
                IOUtils.print("the category is not exist,please input again.");
                inputMultipleLine(regex);
            }
            resultList.add(input);
        }
        
    }
}
