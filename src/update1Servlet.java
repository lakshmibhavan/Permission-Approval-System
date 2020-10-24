

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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update1Servlet")
public class update1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		//capture the user data coming from html form
		String uname=(String)request.getAttribute("uname");
		 String Enter_Password=request.getParameter("pwd");
		 try{
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-S3RV3MH:1521:xe","system","manager");
			  String vsql="update RegisterUser set Enter_Password=? where Employee_Name= ?";
			  PreparedStatement pstmt=con.prepareStatement(vsql);
			 pstmt.setString(2,Enter_Password);
			 pstmt.setString(1,uname);
			  int n=pstmt.executeUpdate();
		 if(n==1)
		  {
			      out.println("<h1>Password Updated Successfully</h1>");
			       out.println("<a href= EmployeeLogin.html>Login Page</a>");
		  }
	  }catch(Exception e)
	  {
		   out.println(e.getMessage());
	  }

}
}
