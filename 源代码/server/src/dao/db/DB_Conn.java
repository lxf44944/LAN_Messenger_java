package dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Conn {

	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static String user = "king";
	public static String password = "king";
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private DB_Conn() {

	}

	private static DB_Conn dbConn = new DB_Conn();

	public static DB_Conn getDbConn() {
		return dbConn;
	}

	private static Connection connection;

	public Connection getConn() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

	public void close(ResultSet resultSet, PreparedStatement preparedStatement,
			Statement statement, Connection connection) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			
		}

	}
}
