package backend.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import backend.dto.Deal;
import backend.util.DBUtil;

public class DealDaoImpl implements DealDao {
	private static DealDaoImpl impl = new DealDaoImpl();
	private DealDaoImpl() {
		
	}
	public static DealDaoImpl getDealImpl() {
		return impl;
	}
	
	DBUtil util = DBUtil.getUtil();
	
	@Override
	public List<Deal> getSearch(String method, String searchfor) throws SQLException {
		List<Deal> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = null;
		
		if (method.equals("dong")) {
			sql = "select * " + 
					"from deal_apart " + 
					"join apart " + 
					"using (apart_num) " + 
					"join dongcode " + 
					"using (dong_code) " + 
					"where dongcode.dong_name = ?"; //테이블 수정
			
			try {
				con = util.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, searchfor);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					String dongName = rset.getString("dong_name");
					String aptName = rset.getString("a_name");
					int dealPrice = Integer.parseInt(rset.getString("a_price").replace(",", ""));
					double area = rset.getDouble("area");
					int dealMonth = rset.getInt("trade_month");
					int dealDay = rset.getInt("trade_day");

					list.add(new Deal(dongName, aptName, dealPrice, area, dealMonth, dealDay));
				}

			} finally {
				util.close(rset, pstmt, con);
			}
		} else if (method.equals("apt")) {
			sql = "select * " + 
					"from deal_apart " + 
					"join apart " + 
					"using (apart_num) " + 
					"join dongcode " +
					"using (dong_code) " +
					"where a_name = ?"; //테이블 수정

			try {
				con = util.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, searchfor);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					String dongName = rset.getString("dong_name");
					String aptName = rset.getString("a_name");
					int dealPrice = Integer.parseInt(rset.getString("a_price").replace(",", ""));
					double area = rset.getDouble("area");
					int dealMonth = rset.getInt("trade_month");
					int dealDay = rset.getInt("trade_day");

					list.add(new Deal(dongName, aptName, dealPrice, area, dealMonth, dealDay));
				}

			} finally {
				util.close(rset, pstmt, con);
			}
		}
		return list;
	}
}