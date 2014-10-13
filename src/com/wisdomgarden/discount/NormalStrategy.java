package com.wisdomgarden.discount;

import com.wisdomgarden.domain.Cargo;
/**
 * 
 * @Description ��ͨ�㷨
 * @author qiani
 * 2014-7-29����01:39:33
 */
public class NormalStrategy implements IStrategy
{

	@Override
	public Double calculatePrice(Cargo cargo)
	{
		if (cargo == null)
		{
			return new Double(0);
		}
		return cargo.getPrice() * cargo.getNumber();
	}
}
