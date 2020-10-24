import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginCheckServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		System.out.println("service called...");
		//to send the response to the browser
		PrintWriter out = res.getWriter();

		//set the contentType as html using res object
		res.setContentType("text/html");

		//capture the user data coming from html form
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");

		//connect with db
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-S3RV3MH:1521:xe","system","manager");

			String vsql = "select * from RegisterUser where Employee_Name=? and Enter_Password=?";

			PreparedStatement pstmt = con.prepareStatement(vsql);
			pstmt.setString(1,uname);
			pstmt.setString(2,pwd);
			ResultSet rs = pstmt.executeQuery();
				
if( rs.next() ){
				req.setAttribute("uname",uname);
				RequestDispatcher rd = req.getRequestDispatcher("/welcome");
				rd.forward(req,res);
				
			}else{
				out.println("Invalid username/password<br>");
				RequestDispatcher rd = req.getRequestDispatcher("EmployeeLogin.html");
				rd.include(req,res);
				out.println("<a href='PwdChange.html?uname="+uname+"'>forgot password</a>");
			}

			out.println("</body>");
			out.println("<html>");
			con.close();
		}catch(Exception e){
			res.sendError(500,"Our application is failed due to:" + e);
		}
	}
}

