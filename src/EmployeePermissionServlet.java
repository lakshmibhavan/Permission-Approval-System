

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class EmployeePermissionServlet
 */
@WebServlet("/EmployeePermissionServlet")
public class EmployeePermissionServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub  
			  
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();
			
			String PERMISSION_ID=request.getParameter("pid");          
			String STARTDATE=request.getParameter("date1");  
			String ENDDATE=request.getParameter("date2");  
			String SUBJECT=request.getParameter("subject");  
			String REASON_DESC=request.getParameter("permission"); 
			String STATUS =request.getParameter("status");
			String STATUS1 = request.getParameter("status1");
			String FINAL_STATUS = request.getParameter("status2");
			          
			try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@DESKTOP-S3RV3MH:1521:xe","system","manager");  
			  
			PreparedStatement ps=con.prepareStatement(  
			"insert into employeepermission(PERMISSION_ID,STARTDATE,ENDDATE,SUBJECT,REASON_DESC,STATUS,STATUS1,FINAL_STATUS) values(?,?,?,?,?,?,?,?)"); 
			ps.setInt(1, Integer.parseInt(PERMISSION_ID));  
			ps.setString(2,STARTDATE);  
			ps.setString(3,ENDDATE);  
			ps.setString(4,SUBJECT);  
			ps.setString(5,REASON_DESC);
			ps.setString(6, STATUS);
			ps.setString(7, STATUS1);
			ps.setString(8, FINAL_STATUS);
			int i=ps.executeUpdate();  
			if(i>0)  
			out.print("<h1>Request for Permission sent!.<br>");  
			out.println("<a href= LogoutServlet>Logout</a>");
			
			          
			}catch (Exception e2) {System.out.println(e2);}  
			          
			out.close();  
			}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}  
			  
			}  
