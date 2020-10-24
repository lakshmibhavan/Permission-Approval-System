

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register implements Servlet {

    /**
     * Default constructor. 
     */
    public Register() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub  
			  
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();  
			          
			String Employee_Name=request.getParameter("fname");  
			String Employee_Id=request.getParameter("lname");  
			String Phone_Number=request.getParameter("phone");  
			String Enter_Password=request.getParameter("pwd"); 
			String Confirm_Password=request.getParameter("pass1"); 
			String Email=request.getParameter("email"); 
			          
			try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@DESKTOP-S3RV3MH:1521:xe","system","manager");  
			  
			PreparedStatement ps=con.prepareStatement(  
			"insert into RegisterUser values(?,?,?,?,?,?)");  
			  
			ps.setString(1,Employee_Name);  
			ps.setInt(2,Integer.parseInt(Employee_Id));  
			ps.setString(3,Phone_Number);  
			ps.setString(4,Enter_Password);  
			ps.setString(5,Confirm_Password);  
			ps.setString(6,Email);  
			          
			int i=ps.executeUpdate();  
			if(i>0)  
			out.print("You are successfully registered...");  
			out.println("Go to <a href= HomePage.html>Home Page</a>");
			      
			          
			}catch (Exception e2) {System.out.println(e2);}  
			          
			out.close();  
			}  
			  
			}  
