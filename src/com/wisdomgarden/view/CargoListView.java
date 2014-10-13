package com.wisdomgarden.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wisdomgarden.domain.Cargo;
import com.wisdomgarden.domain.Category;
import com.wisdomgarden.domain.DiscountInfo;
import com.wisdomgarden.shoppingcart.DiscountCollection;
import com.wisdomgarden.shoppingcart.ProductWarehouse;
import com.wisdomgarden.shoppingcart.ShoppingCart;
import com.wisdomgarden.utils.CollectionUtils;
import com.wisdomgarden.utils.IOUtils;
import com.wisdomgarden.utils.StringUtils;

/**
 * 
 * @Description 所 购 产品输入
 * @author qiani 2014-7-29下午01:46:03
 */
public class CargoListView extends AbstractView
{
    private static final String CARGO_INPUT_REGEX = "(\\d+)\\s*\\*(.+)\\s*:\\s*((\\d+)(\\.\\d+)?)";
    
    private List<String> inputList;
    
    @Override
    public void printTipInfo()
    {
        IOUtils.print("please input cargo(format:number * cargon : price), end with '/': ");
        
    }
    
    @Override
    protected void saveOrder(ShoppingCart order)
    {
        for (String cargo : inputList)
        {
            fillOrderCargoList(order, cargo);
        }
        setSuccessor(new DateView());
    }
    
    private void fillOrderCargoList(ShoppingCart order, String cargo)
    {
        
        Cargo cargoInfo = new Cargo();
        List<String> groupResult = StringUtils.getGroupResult(cargo, "(.+)\\*(.+):(.+)");
        if (CollectionUtils.isEmpty(groupResult))
        {
            return;
        }
        cargoInfo.setCargoName(groupResult.get(2));
        cargoInfo.setNumber(Integer.valueOf(groupResult.get(1)));
        cargoInfo.setCargoName(groupResult.get(2));
        cargoInfo.setPrice(Double.valueOf(groupResult.get(3)));
        setCargoCategory(order, cargoInfo);
        order.getCargoList().add(cargoInfo);
        
    }
    
    private void setCargoCategory(ShoppingCart order, Cargo cargoInfo)
    {
        Category category = constructCategory(order, cargoInfo);
        cargoInfo.setCategory(category);
    }
    
    /**
     * 设置当前产品对应的产品品类以及折扣信息
     * 
     * @param order
     * @param cargoInfo
     * @return Category
     */
    private Category constructCategory(ShoppingCart order, Cargo cargoInfo)
    {
        Category category = new Category();
        for (Entry<String, List<String>> entry : ProductWarehouse.getInstance().getParentCategoryMap().entrySet())
        {
            if (entry.getValue().contains(cargoInfo.getCargoName()))
            {
                String categoryName = entry.getKey();
                category.setCatetoryName(categoryName);
                List<DiscountInfo> discountList = DiscountCollection.getInstance().getDiscountInfoList();
                for (DiscountInfo discountInfo : discountList)
                {
                    for (Category categoryTemp : discountInfo.getCategoryList())
                    {
                        if (categoryTemp.getCatetoryName().equals(categoryName))
                        {
                            category.setDiscountInfo(discountInfo);
                            break;
                        }
                    }
                }
                
            }
        }
        return category;
    }
    
    @Override
    protected void input()
    {
        inputList = inputMultipleLine(CARGO_INPUT_REGEX);
        
    }
    
    public static List<String> inputMultipleLine(String regex)
    {
        List<String> resultList = new ArrayList<String>();
        
        Map<String, List<String>> productMap = ProductWarehouse.getInstance().getParentCategoryMap();
        while (true)
        {
            String input = IOUtils.input();
            if (!IOUtils.multipleInputValid(input, regex))
            {
                IOUtils.print("input invalid,please input again.");
                return inputMultipleLine(regex);
                
            }
            List<String> groupResult = StringUtils.getGroupResult(input, CARGO_INPUT_REGEX);
            
            if (CollectionUtils.isEmpty(groupResult))
            {
                return resultList;
            }
            boolean flag = false;
            for (Entry<String, List<String>> entry : productMap.entrySet())
            {
                
                if (entry.getValue().contains(groupResult.get(2)))
                {
                    flag = true;
                }
                
            }
            if (!flag)
            {
                IOUtils.print("the cargo is not exist,please input again.");
                return inputMultipleLine(regex);
            }
            if (!"/".equals(input))
            {
                resultList.add(input);
            }
            else
            {
                return resultList;
            }
            
        }
        
    }
    
}
