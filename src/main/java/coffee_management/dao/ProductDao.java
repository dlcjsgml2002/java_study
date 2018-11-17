package coffee_management.dao;

import java.sql.SQLException;
import java.util.List;

import coffee_management.dto.Product;

public interface ProductDao {
	List<Product> selectProductByAll() throws SQLException;

	Product selectProductByCode(Product pdt) throws SQLException;
}
