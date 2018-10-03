package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import p1.Model;

public class ResetPwd extends HttpServlet
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			HttpSession session = request.getSession();
			String email = (String) session.getAttribute("email");

			String pwd = request.getParameter("npwd");

			Model m = new Model();
			m.setEmail(email);
			m.setPwd(pwd);
			boolean status = m.resetPwd();
			
			if(status==true)
			{
				response.sendRedirect("/BankAppp/resetPwdSuccess.jsp");
			}
			else
			{
				response.sendRedirect("/BankAppp/resetPwdFail.jsp");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
