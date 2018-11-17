package coffee_management.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coffee_management.dto.Product;
import coffee_management.dto.Sale;
import coffee_management.dto.SaleDetail;
import coffee_management.jdbc.ConnectionProvider;
import coffee_management.jdbc.LogUtil;
import coffee_management.jdbc.MySQLjdbcUtil;

public class SaleDaoImpl implements SaleDao {

	@Override
	public List<Sale> selectSaleByAll() throws SQLException {
		LogUtil.prnLog("SelectSaleByAll()");

		List<Sale> list = new ArrayList<>();
		String sql = "select no, code, price, saleCnt, marginRate from sale";

		try (Connection conn = MySQLjdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LogUtil.prnLog(pstmt);
			while (rs.next()) {
				list.add(getSale(rs));
			}
		}

		return list;
	}

	private Sale getSale(ResultSet rs) throws SQLException {
		int no = rs.getInt("no");
		Product code = new Product(rs.getString("code"));
		int price = rs.getInt("price");
		int saleCnt = rs.getInt("saleInt");
		int marginRate = rs.getInt("marginRate");

		return new Sale(no, code, price, saleCnt, marginRate);
	}

	@Override
	public int insertSale(Sale sale) throws SQLException {
		LogUtil.prnLog("insertSale");

		String sql = "insert into sale values(?, ?, ?, ?, ?)";
		int res = 0;

		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, sale.getNo());
			pstmt.setString(2, sale.getProduct().getCode());
			pstmt.setInt(3, sale.getPrice());
			pstmt.setInt(4, sale.getSaleCnt());
			pstmt.setInt(5, sale.getMarginRate());

			LogUtil.prnLog(pstmt);

			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public List<Sale> selectSaleRank(boolean isSale) throws SQLException {
		LogUtil.prnLog("selectSaleRank()");

		List<Sale> list = new ArrayList<>();
		String sql = "{call price_rank}";

		try (Connection conn = ConnectionProvider.getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
			cs.setBoolean(1, isSale);
			LogUtil.prnLog(cs.toString());

			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					list.add(getSaleDetail(rs));
				}
			}
		}
		LogUtil.prnLog("selectSaleRank" + list.size());

		return list;
	}

	private Sale getSaleDetail(ResultSet rs) throws SQLException {
		int no = rs.getInt("no");
		Product product = new Product(rs.getString("code"), rs.getString("name"));
		int price = rs.getInt("price");
		int saleCnt = rs.getInt("saleCnt");
		int marginRate = rs.getInt("marginRate");
		int supplyPrice = rs.getInt("supplyPrice");
		int addTax = rs.getInt("addTax");
		int salePrice = rs.getInt("salePrice");
		int marginPrice = rs.getInt("marginPrice");
		int rank = rs.getInt("rank");

		SaleDetail detail = new SaleDetail(supplyPrice, addTax, salePrice, marginPrice, rank);
		Sale sale = new Sale(no, product, price, saleCnt, marginRate, detail);

		LogUtil.prnLog(sale.toString());

		return sale;
	}

}
