package backend.model.service;

import java.sql.SQLException;
import java.util.List;

import backend.dto.Deal;
import backend.model.dao.DealDao;
import backend.model.dao.DealDaoImpl;

public class DealServiceImpl implements DealService {
	private static DealServiceImpl impl = new DealServiceImpl();
	
	public static DealServiceImpl getDealServiceImpl() {
		return impl;
	}
	
	private DealServiceImpl() {
		
	}
	
	DealDao dao = DealDaoImpl.getDealImpl();

	@Override
	public List<Deal> getSearch(String dong, String apt) throws SQLException {
		return dao.getSearch(dong, apt);
	}

}
