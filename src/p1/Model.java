package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

public class Model 
{
	private String name;
	private String accno;
	private String custid;
	private String pwd;
	private String email;
	private String balance;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	
	public Model() throws Exception 
	{
		DriverManager.registerDriver(new OracleDriver());
		con=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "system", "system");
		System.out.println("Connected");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	public boolean login() throws Exception
	{
		pstmt=con.prepareStatement("SELECT * FROM BANK WHERE CUSTID=? AND PWD=?");
		pstmt.setString(1, custid);
		pstmt.setString(2, pwd);
		res=pstmt.executeQuery();
		while(res.next()==true)
		{
			accno=res.getString("ACCNO");
			return true;
		}
		return false;
	}
	
	public boolean checkBalance() throws SQLException
	{
		pstmt=con.prepareStatement("Select BALANCE from BANK where ACCNO=?");
		pstmt.setString(1, accno);
		res=pstmt.executeQuery();
		while(res.next()==true)
		{
			balance=res.getString("BALANCE");
			return true;
		}
		return false;
	}
	
	public boolean changePwd() throws SQLException
	{
		pstmt=con.prepareStatement("update Bank set PWD=? where ACCNO=?");
		pstmt.setString(1, pwd);
		pstmt.setString(2, accno);
		int row=pstmt.executeUpdate();
		if(row==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean transfer(String tamt) throws SQLException
	{
		pstmt=con.prepareStatement("update BANK set BALANCE=BALANCE-? where ACCNO=?");
		pstmt.setString(1, tamt);
		pstmt.setString(2, accno);
		int rows=pstmt.executeUpdate();
		
		pstmt=con.prepareStatement("insert into BANK_STATEMENT values (?, ?)");
		pstmt.setString(1, accno);
		pstmt.setString(2, tamt);
		pstmt.executeUpdate();
		
		if(rows==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public ArrayList getStatement() throws SQLException
	{
		pstmt=con.prepareStatement("Select * from BANK_STATEMENT where ACCNO=?");
		pstmt.setString(1, accno);
		res=pstmt.executeQuery();
		
		ArrayList al = new ArrayList();
		while(res.next()==true)
		{
			al.add(res.getString("AMT"));
		}
		return al;
	}
	
	public boolean resetPwd() throws SQLException
	{
		pstmt=con.prepareStatement("update BANK set PWD=? where EMAIL=?");
		pstmt.setString(1, pwd);
		pstmt.setString(2, email);
		int rows = pstmt.executeUpdate();
		
		if(rows==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
