package coffee_management;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import coffee_management.dao.ProductDao;
import coffee_management.dao.ProductDaoImpl;
import coffee_management.dto.Product;
import coffee_management.jdbc.LogUtil;

public class ProductDaoTest {
	static ProductDao dao;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println();
		LogUtil.prnLog("Start ProductDaoTest");
		dao = new ProductDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println();
		LogUtil.prnLog("End ProductDaoTest");
		dao = null;
	}

	@Before
	public void setUp() {
		System.out.println();
	}

	@Test
	public void test01selectProductByAll() throws SQLException {
		LogUtil.prnLog("selectProductByAll()");

		List<Product> list = dao.selectProductByAll();
		LogUtil.prnLog(list.toString());
		Assert.assertNotNull(list);
	}

	@Test
	public void test02selectProductByCode() throws SQLException {
		LogUtil.prnLog("selectProductByCode()");

		Product sPdt = new Product("A001");
		Product searchPdt = dao.selectProductByCode(sPdt);
		LogUtil.prnLog(searchPdt.toString());
		Assert.assertNotNull(searchPdt);
	}
}
