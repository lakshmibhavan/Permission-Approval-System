import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class welcome extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		System.out.println("service called...");
		//to send the response to the browser
		PrintWriter out = res.getWriter();

		//set the contentType as html using res object
		res.setContentType("text/html");

		//capture the user data coming from html form
		String uname = (String)req.getParameter("uname");
		
		out.println("<center><h1>Welcome to:</h1>" + uname);
		out.println("<html><body>");
		out.println("<center><h1><a href= EmployeePermission.html>RAISE PERMISSION</a></h1><br>");
		out.println("<center><h1><a href= viewpermission.html>VIEW PERMISSION</a></h1>");
		out.println("<a href= LogoutServlet>Logout</a>");
		out.println("</body></html>");
		
	}
}
