package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import p1.Model;

public class Transfer extends HttpServlet 
{

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			HttpSession session = request.getSession();
			String accno=(String) session.getAttribute("accno");

			String tamt = request.getParameter("tamt");

			Model m = new Model();
			m.setAccno(accno);
			boolean status = m.transfer(tamt);
			
			if(status==true)
			{
				response.sendRedirect("/BankAppp/transferSuccess.jsp");
			}
			else
			{
				response.sendRedirect("/BankAppp/transferFail.jsp");
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
