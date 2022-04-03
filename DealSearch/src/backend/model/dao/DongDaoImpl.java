package backend.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.util.DBUtil;

public class DongDaoImpl implements DongDao {
	private static DongDaoImpl dd = new DongDaoImpl();
	private static Map<String, List<String>> guList = new HashMap<>();
	
	private DongDaoImpl() {
		
	}
	
	public static DongDaoImpl getDong() {
		return dd;
	}
	
	DBUtil util = DBUtil.getUtil();

	@Override
	public List<String> getDong(String gu) throws SQLException {
		if (guList.containsKey(gu)) {
			return guList.get(gu);
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> list = new ArrayList<>();

		String sql = "select * from dongcode where sigu_name = ?"; // 수정 부탁드립니당

		try {
			con = util.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gu);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				String dong = rset.getString("dong_name");
				list.add(dong);
			}
		} finally {
			util.close(rset, pstmt, con);
		}
		Collections.sort(list);
		guList.put(gu, list);
		return list;
		
	}
	

}
