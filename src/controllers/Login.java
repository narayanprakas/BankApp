package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import p1.Model;
public class Login extends HttpServlet
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String custid = request.getParameter("custid");
		String pwd = request.getParameter("pwd");
		
		try 
		{
			Model m = new Model();
			m.setCustid(custid);
			m.setPwd(pwd);
			boolean login = m.login();
			
			HttpSession session = request.getSession(true);
			if(login==true)
			{
				String accno=m.getAccno();
				session.setAttribute("accno", accno);
				response.sendRedirect("/BankAppp/home.jsp");
			}
			else
			{
				response.sendRedirect("/BankAppp/loginFail.jsp");
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
