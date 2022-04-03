package backend.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backend.dto.User;
import backend.util.DBUtil;

public class UserDaoImpl implements UserDao {

	private static UserDao impl = null;
	private DBUtil util = DBUtil.getUtil();

	public static UserDao getUserDao() {
		if (impl == null) {
			impl = new UserDaoImpl();
		}

		return impl;
	}

	private UserDaoImpl() {
	}

	@Override
	public User login(String id, String password) throws SQLException {
		User info = null;

		String sql = "select * from member where id=? and pw=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			con = util.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				String name = rset.getString("name");
				String email = rset.getString("email");
				String tel = rset.getString("phone_num");
				info = new User(id, password, name, email, tel);
			}
		} finally {
			util.close(rset, pstmt, con);
		}

		return info;
	}

	@Override
	public void join(User user) throws SQLException {
		String sql = "insert into member (id, pw, name, email, phone_num) values (?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = util.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getTel());

			pstmt.executeUpdate();
		} finally {
			util.close(null, pstmt, con);
		}

	}

	@Override
	public void update(User user) throws SQLException {
		String sql = "update member set pw=?, name=?, email=?, phone_num=? where id=?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = util.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getTel());
			pstmt.setString(5, user.getId());

			pstmt.executeUpdate();
		} finally {
			util.close(null, pstmt, con);
		}
	}

	@Override
	public void delete(String id) throws SQLException {
		String sql = "delete from member where id=?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = util.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			pstmt.executeUpdate();
		} finally {
			util.close(null, pstmt, con);
		}
	}

	@Override
	public int checkId(String id) throws SQLException {
		String sql = "select count(id) from member where id = ?";
		
		int cnt = 1;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = util.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			util.close(rs, pstmt, con);
		}
		
		return cnt;
	}

}