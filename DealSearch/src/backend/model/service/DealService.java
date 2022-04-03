package backend.model.service;

import java.sql.SQLException;
import java.util.List;

import backend.dto.Deal;

public interface DealService {
	public List<Deal> getSearch(String dong, String apt) throws SQLException;
}
