package com.lti.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lti.dao.CustomerDao;
import com.lti.model.Customer;

public class RegisterCustomerServlet extends HttpServlet {

    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id= Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String email = request.getParameter("email");
		
		Customer c = new Customer();
		c.setId(id);
		c.setName(name);
		c.setEmail(email);
		
		CustomerDao dao = new CustomerDao();
		dao.add(c);
		response.sendRedirect("confirmation.html");
	}

}
