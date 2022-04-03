package backend.model.service;

import java.sql.SQLException;

import backend.dto.User;
import backend.model.dao.UserDao;
import backend.model.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {

	private static UserService impl = null;
	private UserDao userDao = UserDaoImpl.getUserDao();

	public static UserService getUserService() {
		if (impl == null) {
			impl = new UserServiceImpl();
		}

		return impl;
	}

	private UserServiceImpl() {
	}

	@Override
	public User login(String id, String password) throws SQLException {
		return userDao.login(id, password);
	}

	@Override
	public void join(User user) throws SQLException {
		userDao.join(user);
	}

	@Override
	public void update(User user) throws SQLException {
		userDao.update(user);
	}

	@Override
	public void delete(String id) throws SQLException {
		userDao.delete(id);
	}

	@Override
	public int checkId(String id) throws SQLException {
		return userDao.checkId(id);
	}

}
