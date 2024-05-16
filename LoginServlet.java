import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		PrintWriter out= response.getWriter();
		response.setContentType("text/html");
		
	 String Uname = request.getParameter("uname");
	 String pwd = request.getParameter("pwd");
	 
	 
	    String driver="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		
		try {
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,"system","admin");
			
			
			String query="select*from register where Uname=? and password=?  ";
			PreparedStatement ps= con.prepareStatement(query);
			 ps.setString(1, Uname);
			 ps.setString(2, pwd);
			 ResultSet rs=ps.executeQuery();
			 if(rs.next())
			 {
				RequestDispatcher rd = request.getRequestDispatcher("Success.html");
				rd.forward(request, response);
			 }
			 
			 else {
				 out.println("<h4 style='color:red'>Login Failed/Try again</h4>");
					RequestDispatcher  rd = request.getRequestDispatcher("Login.html");
					rd.include(request,response);
				
			 
			 }
			 
	}
	catch(Exception e)
	{
		out.println("<h4 style='color:red'> Exception:"+e.getMessage()+"</h4>");
	}
	}
	}