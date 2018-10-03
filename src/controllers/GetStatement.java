package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import p1.Model;
public class GetStatement extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			HttpSession session = request.getSession();
			String accno=(String) session.getAttribute("accno");

			Model m = new Model();
			m.setAccno(accno);
			ArrayList al = m.getStatement();
			session.setAttribute("statement", al);
			
			response.sendRedirect("/BankAppp/getStatement.jsp");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}


	}

}
