package com.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.contact.model.User;

public class UserDAOImpl implements UserDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public UserDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int save(User user) {
		String sql = "INSERT INTO user (name, address, phone, email) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, user.getName(), user.getAddress(), user.getPhone(), user.getEmail());
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE user SET name=?, address=?, phone=?, email=? WHERE id=?";
		return jdbcTemplate.update(sql, user.getName(), user.getAddress(), user.getPhone(), user.getEmail(),
				user.getId());
	}

	@Override
	public User getUserById(int id) {
		final int uid = id;
		String sql = "SELECT * FROM user WHERE id=" + uid;
	    ResultSetExtractor<User> extractor = new ResultSetExtractor<User>() {

			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next())
				{
					String name = rs.getString("name");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					String email = rs.getString("email");
					return new User(uid, name, address, phone, email);
				}
				return null;
			}
	    	
	    }; 
	    return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM user WHERE id=?";
	    return jdbcTemplate.update(sql, id);
	}

	@Override
	public List<User> getUsers() {
		String sql = "SELECT * FROM user";
		RowMapper<User> rowMapper = new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				
				return new User(id, name, address, phone, email);
			}
			
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

}
