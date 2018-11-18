package coffee_management.service;

import java.sql.SQLException;
import java.util.List;

import coffee_management.dao.SaleDao;
import coffee_management.dao.SaleDaoImpl;
import coffee_management.dto.Sale;

public class OutputService {
	private SaleDao saleDao;

	public OutputService() {
		saleDao = new SaleDaoImpl();
	}

	public List<Sale> outputOrder(boolean isSale) throws SQLException {
		return saleDao.selectSaleRank(isSale);
	}
}
