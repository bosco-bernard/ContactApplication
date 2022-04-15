package com.contact.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.contact.model.User;

class UserDAOTest {
	
	private DriverManagerDataSource dataSource;
	private UserDAO dao;
	
	@BeforeEach
	void setBeforeEach()
	{
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/contactSpringJDBC");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		
		dao = new UserDAOImpl(dataSource);
	}

	@Test
	void testSave() {	
		
		User user = new User("siva", "Nungambakkam", "1234123412", "bill@mail.com");
		int res = dao.save(user);
		assertTrue(res > 0);
	}

	@Test
	void testUpdate() {
		User user = new User(3,"Bill Gates", "usa", "2222222222", "bill@mail.com");
		int res = dao.update(user);
		assertTrue(res > 0);
	}

	@Test
	void testGetUserById() {
		int id = 1;
		User user = dao.getUserById(id);
		if(user != null)
			System.out.println(user);
		assertNotNull(user);
	}

	@Test
	void testDelete() {
		int id = 2;
		int res = dao.delete(id);
		assertTrue(res > 0);
	}

	@Test
	void testGetUsers() {
		List<User> users = dao.getUsers();
		for(User user : users)
			System.out.println(user);
		assertTrue(!users.isEmpty());
	}

}
