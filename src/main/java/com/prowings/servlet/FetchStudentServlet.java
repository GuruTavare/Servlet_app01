package com.prowings.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.prowings.HibernateUtil1.HibernateUtil1;
import com.prowings.entity.Student;

public class FetchStudentServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">>>>>> Started fetch student servlet");
		int id = Integer.parseInt(req.getParameter("id"));

		System.out.println("Fetching Student details for ID : " + id);

		Session s = HibernateUtil1.getSessionFactory().openSession();
		Student fetchedStudent = s.get(Student.class, id);
		System.out.println(fetchedStudent);

		if (fetchedStudent != null) {
			req.setAttribute("stud", fetchedStudent);
			// Forward the request to the JSP
			req.getRequestDispatcher("/studentDetails.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", new Error("No Student present with given id"));
			req.getRequestDispatcher("/Error1.jsp").forward(req, resp);
		}

		super.doPost(req, resp);
	}

}
