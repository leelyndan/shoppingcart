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
		// ��ʼ���ۿ���Ϣ
		initDiscountInfo("2011.11.11", "����");
		initDiscountInfo("2011.11.11", "ʳƷ");

		// ��ʼ���Ż�ȯ��Ϣ
		initCoupons();
	}

	private void initCoupons()
	{
		// �Ż�ȯ
		Coupons coupons = new Coupons();
		// ��Ч����
		coupons.setDate(DateUtils.parse2Date("2016.12.12"));
		// ���ѽ������
		coupons.setOriginalAmount(2000.0);
		// �Żݽ��
		coupons.setDiscountAmount(200.0);
		CouponsCollection.getInstance().getCouponList().add(coupons);
	}

	private void initDiscountInfo(String date, String CatetoryName)
	{
		// �ۿ���Ϣ
		DiscountInfo discountInfo = new DiscountInfo();
		// ��������
		discountInfo.setDate(DateUtils.parse2Date(date));
		// �ۿ�
		discountInfo.setDiscountPercent(0.5);
		List<Category> categoryList = new ArrayList<Category>();
		// ���۲�ƷƷ��
		Category category = new Category();
		category.setCatetoryName(CatetoryName);
		category.setDiscountInfo(discountInfo);
		categoryList.add(category);
		discountInfo.setCategoryList(categoryList);
		// �����ۿ���Ϣ�������ж��
		DiscountCollection.getInstance().getDiscountInfoList()
				.add(discountInfo);
	}

	private Cargo initCargo(String CargoName, double Price, int number,
			DiscountInfo discountInfo, String catetoryName)
	{
		// ������Ʒ
		Cargo cargo = new Cargo();
		// ������Ʒ����
		cargo.setCargoName(CargoName);
		// ������Ʒ����
		cargo.setPrice(Price);
		// ������Ʒ����
		cargo.setNumber(number);
		Category category = new Category();
		// ������ƷƷ��
		category.setDiscountInfo(discountInfo);
		// ������ƷƷ������
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
		// ���ﳵ
		ShoppingCart order = new ShoppingCart();
		// ��Ʒ�б�
		List<Cargo> cargoList = new ArrayList<Cargo>();
		// ������Ʒ
		Cargo cargo = initCargo("ipad", 2000.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
		// �����б�
		cargoList.add(cargo);
		// ���빺�ﳵ
		order.setCargoList(cargoList);
		// ���õ�ǰʱ��
		order.setCurrentDate(DateUtils.parse2Date("2011.11.11"));
		// ���� order.calculateTotalMoney()
		Assert.assertEquals(1800.00, order.calculateTotalMoney());
	}

	@Test
	public void testCalculateTotalMoney_caseA_two()
	{
		ShoppingCart order = new ShoppingCart();
		List<Cargo> cargoList = new ArrayList<Cargo>();
		Cargo cargo = initCargo("ipad", 2000.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
		;
		cargoList.add(cargo);
		Cargo cargoTomato = initCargo("������", 200.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(1), "ʳƷ");
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
				.getInstance().getDiscountInfoList().get(0), "����");
		cargoList.add(cargo);

		Cargo cargoPapers = initCargo("�ͽ�ֽ", 200.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
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
		Cargo cargo = initCargo("����", 1000.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
		cargoList.add(cargo);

		Cargo cargoPapers = initCargo("�ͽ�ֽ", 200.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
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
		Cargo cargo = initCargo("����", 1000.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
		cargoList.add(cargo);

		Cargo cargoPapers = initCargo("�ͽ�ֽ", 200.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
		cargoList.add(cargoPapers);

		Cargo cargoIpad = initCargo("ipad", 2000.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
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
		Cargo cargo = initCargo("����", 1000.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
		cargoList.add(cargo);

		Cargo cargoPapers = initCargo("�ͽ�ֽ", 200.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
		cargoList.add(cargoPapers);

		Cargo cargoIpad = initCargo("ipad", 2000.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
		cargoList.add(cargoIpad);

		Cargo cargoTomato = initCargo("������", 200.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(1), "ʳƷ");
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
		Cargo cargo = initCargo("����", 1000.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
		cargoList.add(cargo);

		Cargo cargoPapers = initCargo("�ͽ�ֽ", 200.0, 1, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
		cargoList.add(cargoPapers);

		Cargo cargoIpad = initCargo("ipad", 2000.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(0), "����");
		cargoList.add(cargoIpad);

		Cargo cargoTomato = initCargo("������", 200.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(1), "ʳƷ");
		cargoList.add(cargoTomato);

		Cargo cargoHappy = initCargo("����", 200.0, 2, DiscountCollection
				.getInstance().getDiscountInfoList().get(1), "����");
		cargoList.add(cargoHappy);
		order.setCargoList(cargoList);
		order.setCurrentDate(DateUtils.parse2Date("2011.11.11"));
		Assert.assertEquals(3600.00, order.calculateTotalMoney());
	}

	@After
	public void after()
	{
		// ����ۿ���Ϣ
		DiscountCollection.getInstance().getDiscountInfoList().clear();

		// ����Ż�ȯ��Ϣ
		CouponsCollection.getInstance().getCouponList().clear();
	}
}
