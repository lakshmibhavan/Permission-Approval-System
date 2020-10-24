

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

/**
 * Servlet implementation class LeaderLoginCheckServlet
 */
@WebServlet("/LeaderLoginCheckServlet")
public class LeaderLoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
			System.out.println("service called...");
			PrintWriter out = res.getWriter();
			res.setContentType("text/html");
			String uname = req.getParameter("uname");
			String pwd = req.getParameter("pwd");
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
					RequestDispatcher rd = req.getRequestDispatcher("/LeaderServlet");
					rd.forward(req,res);
					
				}else{
					out.println("Invalid username/password<br>");
					RequestDispatcher rd = req.getRequestDispatcher("Leaderlogin.html");
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


   