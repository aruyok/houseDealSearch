package backend.model.dao;

import java.sql.SQLException;
import java.util.List;

import backend.dto.Deal;

public interface DealDao {
	public List<Deal> getSearch(String dong, String apt) throws SQLException;
}