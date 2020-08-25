package com.example.demo.tryDegu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {
	@Autowired
	private JdbcTemplate jdbc;

	public int insertOne(Degu degu) throws DataAccessException {
		int rowNumber = jdbc.update("INSERT INTO degu(comment,photo) VALUES(?,?)", degu.getComment(), degu.getPhoto());
		return rowNumber;

	}

	public List<Degu> selectMany() throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM degu");
		List<Degu> deguList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			Degu degu = new Degu();
			degu.setId((int) map.get("Id"));
			degu.setComment((String) map.get("comment"));
			degu.setPhoto((String) map.get("photo"));
			degu.setUpdated_at((Date) map.get("updated_at"));
			deguList.add(degu);
			System.out.print(degu);
		}
		return deguList;

	}

	public Degu selectOne(int Id) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM degu  WHERE Id=?", Id);
		Degu degu = new Degu();
		degu.setId((int) map.get("Id"));
		degu.setComment((String) map.get("comment"));
		degu.setPhoto((String) map.get("photo"));

		return degu;
	}

	public int deleteOne(int Id) throws DataAccessException {
		int rowNumber = jdbc.update("DELETE FROM degu WHERE Id=?", Id);
		return rowNumber;

	}

	public int updateOne(Degu degu) throws DataAccessException {
		int rowNumber = jdbc.update("UPDATE degu SET comment=?,photo=? WHERE Id=?", degu.getComment(), degu.getPhoto(),
				degu.getId());
		return rowNumber;

	}

	public List<Degu> selectM(Date Updated_at) throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM degu WHERE updated_at=?", Updated_at);
		List<Degu> deguList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			Degu degu = new Degu();
			degu.setId((int) map.get("Id"));
			degu.setComment((String) map.get("comment"));
			degu.setPhoto((String) map.get("photo"));
			degu.setUpdated_at((Date) map.get("updated_at"));
			deguList.add(degu);
			System.out.print(degu);
		}
		return deguList;

	}

	public List<Degu> selectByYearMonth(int year, int month) throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList(
				"SELECT * FROM degu WHERE DATE_FORMAT(created_at, '%Y%m')=?",
				String.valueOf(year) + String.format("%02d", month));
		List<Degu> deguList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
			Degu degu = new Degu();
			degu.setId((int) map.get("Id"));
			degu.setComment((String) map.get("comment"));
			degu.setPhoto((String) map.get("photo"));
			degu.setUpdated_at((Date) map.get("updated_at"));
			deguList.add(degu);
		}
		return deguList;
	}

	public List<String> selectDistinctYearMonth() throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList(
	"SELECT distinct date_format(created_at"
	+ ",'%Y/%m') as yearmonth FROM degu");
		List<String> deguList = new ArrayList<>();
		for (Map<String, Object> map : getList) {

			deguList.add((String) map.get("yearmonth"));
		}
		return deguList;
	}
}
