import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WithdrawServlet")
public class WithdrawlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num= Integer.parseInt(request.getParameter("num"));
		int amount = Integer.parseInt(request.getParameter("balance"));
		
		
		PrintWriter out=response.getWriter();
		
		response.setContentType("text/html");
		String driver="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,"system","admin");
			  String query = "update account set balance = balance - ? where num=? and balance >= ?";
		        PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, amount );
		        ps.setInt(2, num);
		        ps.setInt(3, amount);
		        int count = ps.executeUpdate();
		        if (count > 0)
		           out.println("Withdrawl successful");
		        else
		            out.println("Error: Insufficient balance or invalid account number");

		        }
		
		catch(Exception e)
		{
			
			out.println("<h2>Exception:"+ e.getMessage()+"<h2>");
		}
		
		
		
		
		
		
		
		
		
		
		
	}

}