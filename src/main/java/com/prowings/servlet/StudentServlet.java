package com.prowings.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.prowings.HibernateUtil1.HibernateUtil1;
import com.prowings.entity.Student;

public class StudentServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String rollno = req.getParameter("rollno");

		Student s = new Student();
		s.setName(name);
		s.setAddress(address);
		s.setRollNo(Integer.parseInt(rollno));
		System.out.println(s);

		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil1.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(s);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}

		super.doPost(req, resp);
	}

}
