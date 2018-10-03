package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import p1.Model;

/**
 * Servlet implementation class ChangePwd
 */
public class ChangePwd extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			HttpSession session = request.getSession();
			String accno=(String) session.getAttribute("accno");

			String pwd=request.getParameter("npwd");

			Model m = new Model();
			m.setAccno(accno);
			m.setPwd(pwd);
			boolean status = m.changePwd();
			if(status==true)
			{
				response.sendRedirect("/BankAppp/changePwdSuccess.jsp");
			}
			else
			{
				response.sendRedirect("/BankAppp/changePwdFail.jsp");		
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
