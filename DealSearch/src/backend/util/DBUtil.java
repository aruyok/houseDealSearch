package backend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static DBUtil util = new DBUtil();

	public static DBUtil getUtil() {
		return util;
	}

	private DBUtil() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		String url = "jdbc:mysql://127.0.0.1:3306/happy?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
		Connection con = null;

		try {
			con = DriverManager.getConnection(url, "ssafy", "ssafy");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	public void close(ResultSet rset, Statement pstmt, Connection con) {
		try {
			if (rset != null)
				rset.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
