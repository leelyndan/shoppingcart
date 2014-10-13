package com.wisdomgarden.view;

import com.wisdomgarden.shoppingcart.ShoppingCart;

public abstract class AbstractView
{

    protected AbstractView successor;

    protected abstract void saveOrder(ShoppingCart order);

    protected abstract void printTipInfo();

    protected abstract void input();

    protected void setSuccessor(AbstractView successor)
    {
        this.successor = successor;
    }

    public void requestHandler(ShoppingCart order)
    {
        printTipInfo();
        input();
        saveOrder(order);
        this.successor.requestHandler(order);
    }

}
