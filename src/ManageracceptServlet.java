

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManageracceptServlet
 */
@WebServlet("/ManageracceptServlet")
public class ManageracceptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		System.out.println("service called...");
		//to send the response to the browser
		PrintWriter out = res.getWriter();

		//set the contentType as html using res object
		res.setContentType("text/html");
		int PERMISSION_ID = Integer.parseInt(req.getParameter("pid"));
		String STATUS1 = req.getParameter("status1");
		String FINAL_STATUS = req.getParameter("status2");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-S3RV3MH:1521:xe","system","manager");

			String vsql = "update employeepermission set STATUS1=?,FINAL_STATUS=? where PERMISSION_ID = ?";

			PreparedStatement pstmt = con.prepareStatement(vsql);
			pstmt.setString(1, STATUS1);
			pstmt.setString(2, FINAL_STATUS);
			pstmt.setInt(3,PERMISSION_ID);
			int i=pstmt.executeUpdate();
			if(i>0) {
				out.println("status is updated succesfully");
				out.println("<a href= Logout2Servlet>Logout</a>");

				
			}
			
		}
		catch(Exception e)
		{
			res.sendError(500,"Our application is failed due to:" + e);
		}
		}

}
