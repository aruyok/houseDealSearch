package backend.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface DongDao {
	public List<String> getDong(String gu) throws SQLException;

}
