package coffee_management.service;

import java.sql.SQLException;

import coffee_management.dao.ProductDao;
import coffee_management.dao.ProductDaoImpl;
import coffee_management.dao.SaleDao;
import coffee_management.dao.SaleDaoImpl;
import coffee_management.dto.Product;
import coffee_management.dto.Sale;

public class CoffeeManagementService {
	private SaleDao saleDao;
	private ProductDao pdtDao;

	public CoffeeManagementService() {
		saleDao = new SaleDaoImpl();
		pdtDao = new ProductDaoImpl();
	}

	public int registerSale(Sale sale) throws SQLException {
		return saleDao.insertSale(sale);
	}

	public Product searchProduct(Product product) throws SQLException {
		return pdtDao.selectProductByCode(product);
	}
}
