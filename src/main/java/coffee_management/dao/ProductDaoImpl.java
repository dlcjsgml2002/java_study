package coffee_management.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coffee_management.dto.Product;
import coffee_management.jdbc.ConnectionProvider;
import coffee_management.jdbc.LogUtil;
import coffee_management.jdbc.MySQLjdbcUtil;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> selectProductByAll() throws SQLException {
		LogUtil.prnLog("selectProductByAll()");

		List<Product> list = new ArrayList<>();
		String sql = "select code, name, from product";

		try (Connection conn = MySQLjdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getProduct(rs));
			}
		}
		return null;
	}

	private Product getProduct(ResultSet rs) throws SQLException {
		String code = rs.getString("code");
		String name = rs.getString("name");

		return new Product(code, name);
	}

	@Override
	public Product selectProductByCode(Product pdt) throws SQLException {
		String sql = "select code, name, from product where code = ?";
		Product product = null;
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, pdt.getCode());
			LogUtil.prnLog(pstmt);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					product = getProduct(rs);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
