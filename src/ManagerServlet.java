

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

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		System.out.println("service called...");
		//to send the response to the browser
		PrintWriter out = res.getWriter();

		//set the contentType as html using res object
		res.setContentType("text/html");

		//connect with db
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-S3RV3MH:1521:xe","system","manager");

			String vsql = "select * from employeepermission";

			PreparedStatement pstmt = con.prepareStatement(vsql);
			ResultSet rs = pstmt.executeQuery();
				
			 out.println("PERMISSION_ID\tSTARTDATE\t\tENDDATE\t\tSUBJECT\t\tREASON_DESC\t\t\t\tSTATUS\t\t\t\tSTATUS1\t\t\tFINAL_STATUS<br>");
			   out.println("-------------\t------------\t\t-------\t\t------------\t\t-------------------------------<br>");
			   while(rs.next())
			    {
			      out.println(rs.getInt(1)+"\t\t\t\t\t\t"+rs.getString(2)+"\t\t\t\t\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t\t"+rs.getString(6)+"\t\t\t\t\t"+rs.getString(7)+"\t\t\t\t\t\t"+rs.getString(8)+"\t\t\t\t"+"<a href =Accept.html>Accept</a>"+"\t\t\t\t\t\t"+"<a href =Reject.html>Reject</a><br>");
			    }
			   out.println("<a href= Logout2Servlet>Logout</a>");
		}catch(Exception e){
			res.sendError(500,"Our application is failed due to:" + e);
		}
	}
}