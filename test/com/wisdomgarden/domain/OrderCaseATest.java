package com.wisdomgarden.domain;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wisdomgarden.shoppingcart.CouponsCollection;
import com.wisdomgarden.shoppingcart.DiscountCollection;
import com.wisdomgarden.shoppingcart.ShoppingCart;
import com.wisdomgarden.utils.DateUtils;
public class OrderCaseATest
{

	@Before
	public void before()
	{
		// 初始化折扣信息
		initDiscountInfo("2011.11.11", "电子");
		initDiscountInfo("2011.11.11", "食品");

		// 初始化优惠券信息
		initCoupons();
	}

	private void initCoupons()
	{
		// 优惠券
		Coupons coupons = new Coupons();
		// 有效日期
		coupons.setDate(DateUtils.parse2Date("2016.12.12"));
		// 消费金额上限
		coupons.setOriginalAmount(2000.0);
		// 优惠金额
		coupons.setDiscountAmount(200.0);
		CouponsCollection.getInstance().getCouponList().add(coupons);
	}

	private void initDiscountInfo(String date, String CatetoryName)
	{
		// 折扣信息
		DiscountInfo discountInfo = new DiscountInfo();
		// 促销日期
		discountInfo.setDate(DateUtils.parse2Date(date));
		// 折扣
		discountInfo.setDiscountPercent(0.5);
		List<Category> categoryList = new ArrayList<Category>();
		// 打折产品品类
		Category category = new Category();
		category.setCatetoryName(CatetoryName);
		category.setDiscountInfo(discountInfo);
		categoryList.add(category);
		discountInfo.setCategoryList(categoryList);
		// 保存折扣信息，可以有多个
		DiscountCollection.getInstance().getDiscountInfoList()
				.add(discountInfo);
	}

	private Cargo initCargo(String CargoName, double Price, int number,
			DiscountInfo discountInfo, String catetoryName)
	{
		// 所购产品
		Cargo cargo = new Cargo();
		// 所购产品名称
		cargo.setCargoName(CargoName);
		// 所购产品单价
		cargo.setPrice(Price);
		// 所购产品数量
		cargo.setNumber(number);
		Category category = new Category();
		// 所属产品品类
		category.setDiscountInfo(discountInfo);
		// 所属产品品类名称
		category.setCatetoryName(catetoryName);
		cargo.setCategory(category);
		return cargo;
	}

	@Test
	public void testCalculateTotalMoney_empty()
	{
		ShoppingCart order = new ShoppingCart();
		List<Cargo> cargoList = new ArrayList<Cargo>();
		Cargo cargo = new Cargo();
		Category category = new Category();
		cargo.setCategory(category);
		order.setCargoList(cargoList);
		Assert.assertEquals(0.00, order.calculateTotalMoney());
	}

	@Test
	public void testCalculateTotalMoney_caseA_one()
	{
		// 购物车
		ShoppingCart order = new ShoppingCart();
		// 产品列表
		List<Cargo> cargoList = new ArrayList<Cargo>();
		// 所购产品
		Cargo cargo = initCargo("ipad", 2000.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "电子");
		// 加入列表
		cargoList.add(cargo);
		// 加入购物车
		order.setCargoList(cargoList);
		// 设置当前时间
		order.setCurrentDate(DateUtils.parse2Date("2011.11.11"));
		// 结算 order.calculateTotalMoney()
		Assert.assertEquals(1800.00, order.calculateTotalMoney());
	}

	@Test
	public void testCalculateTotalMoney_caseA_two()
	{
		ShoppingCart order = new ShoppingCart();
		List<Cargo> cargoList = new ArrayList<Cargo>();
		Cargo cargo = initCargo("ipad", 2000.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "电子");
		;
		cargoList.add(cargo);
		Cargo cargoTomato = initCargo("西红柿", 200.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(1), "食品");
		cargoList.add(cargoTomato);
		order.setCargoList(cargoList);

		order.setCurrentDate(DateUtils.parse2Date("2011.11.11"));
		Assert.assertEquals(2000.00, order.calculateTotalMoney());
	}

	@Test
	public void testCalculateTotalMoney_caseA_three()
	{
		ShoppingCart order = new ShoppingCart();
		List<Cargo> cargoList = new ArrayList<Cargo>();
		Cargo cargo = initCargo("ipad", 2000.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "电子");
		cargoList.add(cargo);

		Cargo cargoPapers = initCargo("餐巾纸", 200.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "其他");
		cargoList.add(cargoPapers);

		order.setCargoList(cargoList);

		order.setCurrentDate(DateUtils.parse2Date("2011.11.11"));
		Assert.assertEquals(2000.00, order.calculateTotalMoney());
	}

	@Test
	public void testCalculateTotalMoney_caseA_four()
	{
		ShoppingCart order = new ShoppingCart();
		List<Cargo> cargoList = new ArrayList<Cargo>();
		Cargo cargo = initCargo("短袖", 1000.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "服饰");
		cargoList.add(cargo);

		Cargo cargoPapers = initCargo("餐巾纸", 200.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "其他");
		cargoList.add(cargoPapers);

		order.setCargoList(cargoList);

		order.setCurrentDate(DateUtils.parse2Date("2011.11.11"));
		Assert.assertEquals(1200.00, order.calculateTotalMoney());
	}

	@Test
	public void testCalculateTotalMoney_caseA_five()
	{
		ShoppingCart order = new ShoppingCart();
		List<Cargo> cargoList = new ArrayList<Cargo>();
		Cargo cargo = initCargo("短袖", 1000.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "服饰");
		cargoList.add(cargo);

		Cargo cargoPapers = initCargo("餐巾纸", 200.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "其他");
		cargoList.add(cargoPapers);

		Cargo cargoIpad = initCargo("ipad", 2000.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "电子");
		cargoList.add(cargoIpad);

		order.setCargoList(cargoList);

		order.setCurrentDate(DateUtils.parse2Date("2011.11.11"));
		Assert.assertEquals(3000.00, order.calculateTotalMoney());
	}

	@Test
	public void testCalculateTotalMoney_caseA_six()
	{
		ShoppingCart order = new ShoppingCart();
		List<Cargo> cargoList = new ArrayList<Cargo>();
		Cargo cargo = initCargo("短袖", 1000.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "服饰");
		cargoList.add(cargo);

		Cargo cargoPapers = initCargo("餐巾纸", 200.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "其他");
		cargoList.add(cargoPapers);

		Cargo cargoIpad = initCargo("ipad", 2000.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "电子");
		cargoList.add(cargoIpad);

		Cargo cargoTomato = initCargo("西红柿", 200.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(1), "食品");
		cargoList.add(cargoTomato);

		order.setCargoList(cargoList);

		order.setCurrentDate(DateUtils.parse2Date("2011.11.11"));
		Assert.assertEquals(3200.00, order.calculateTotalMoney());
	}

	@Test
	public void testCalculateTotalMoney_caseA_seven()
	{
		ShoppingCart order = new ShoppingCart();
		List<Cargo> cargoList = new ArrayList<Cargo>();
		Cargo cargo = initCargo("短袖", 1000.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "服饰");
		cargoList.add(cargo);

		Cargo cargoPapers = initCargo("餐巾纸", 200.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "其他");
		cargoList.add(cargoPapers);

		Cargo cargoIpad = initCargo("ipad", 2000.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "电子");
		cargoList.add(cargoIpad);

		Cargo cargoTomato = initCargo("西红柿", 200.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(1), "食品");
		cargoList.add(cargoTomato);

		Cargo cargoHappy = initCargo("快乐", 200.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(1), "修养");
		cargoList.add(cargoHappy);
		order.setCargoList(cargoList);
		order.setCurrentDate(DateUtils.parse2Date("2011.11.11"));
		Assert.assertEquals(3600.00, order.calculateTotalMoney());
	}

	@After
	public void after()
	{
		// 清空折扣信息
		DiscountCollection.getInstance().getDiscountInfoList().clear();

		// 清空优惠券信息
		CouponsCollection.getInstance().getCouponList().clear();
	}
}
