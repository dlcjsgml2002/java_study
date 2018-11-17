package coffee_management.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {
	public static Connection getConnection() throws SQLException {
		return MyDataSource.getInstance().getDataSource().getConnection();
	}
}
