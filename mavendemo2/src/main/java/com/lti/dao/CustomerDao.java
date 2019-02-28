package com.lti.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.Properties;

import com.lti.model.Customer;

// data access object(dao)
public class CustomerDao {

	// public void add(int i ,String name , String email) {
	public void add(Customer customer) {

		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			Properties p = new Properties();
			p.load(this.getClass().getClassLoader().getResourceAsStream("prod-db.properties"));

			Class.forName(p.getProperty("driverClassName"));
			conn = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"),
					p.getProperty("password"));
		} catch (ClassNotFoundException | IOException e) {
			try {
				throw new SQLException(e.getMessage());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String sql = "insert into customer values (? ,? , ?)";

			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, customer.getId());
				stmt.setString(2, customer.getName());
				stmt.setString(3, customer.getEmail());
				stmt.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				stmt.close();
			} catch (Exception e) {
			}

			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
