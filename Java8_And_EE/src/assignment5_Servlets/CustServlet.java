package assignment5_Servlets;
//Import packages
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

//Create the servlets URL
@WebServlet("/customer")

//Create servlet class
public class CustServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//Create doGet method 
	@Override
	protected void doGet (HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException
	{	
		
		response.setContentType("text/html");
		
		//Create new printwriter
		PrintWriter out = response.getWriter();
		
		//Declare html, create page title
		out.println("<html>" + "<head><title>Bank Customer</title></head>");
		
		//Create web form using html to receive user input
		out.println("<body bgcolor=\"#ffffff\">" +  
			"<h2>Enter your details</h2>" + 
			"<form method=\"get\" action=\"http://localhost:8080/Prog_II/customer\">" + 
			"<p>Name:</p>" +
			"<input type=\"text\" name=\"name\" size=\"25\">" +
			"<p>Date Of Birth:</p>" +
			"<input type=\"text\" name=\"dob\" size=\"25\">" +
			"<p>Address:</p>" +
			"<input type=\"text\" name=\"address\" size=\"25\">" +
			"<p></p>" + 
			"<input type=\"submit\" value=\"Submit\">" + 
			"<input type=\"reset\" value=\"Reset\">" + 
			"</form>");
		
		//Get user variables from form
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		
		//Set id as name + date of birth
		String id = name+dob;
		
		//If name and dob have been entered user
		if (name != null && name.length() > 0 && dob != null && dob.length() > 0){
			
			//If address has been entered by user
			if(address != null && address.length() > 0){
				
				//if customer is already registered
				if ((String)request.getSession().getAttribute(id) != null){
					
					//Update address of registered user
					request.getSession().setAttribute(id , address);
					out.println("<p>" + name + " you have requested an address change..." + "</p>");
					out.println("<p>" + "Your new registered address is: " + address + "</p>");
					
				} else {//If customer is registered 
					
					//Register user
					request.getSession().setAttribute(id , address);
					out.println("<p>" + "Thank you for setting an account with us " + name + "</p>");
					out.println("<p>" + "Your user id is " + id + "</p>");
				} 
				
			} else {//If no address enetered in form
				
				//if session variable exists
				if((String)request.getSession().getAttribute(id) != null){
					
					//Print balance for user
					out.println("<p>" + name + ", Your new balance is $4" + "</p>");
					
				} else {//If session variable does not exist
					
					//Print out user is not registeres
					out.println("<p>" + "Id not registered on the system" + "</p>");
				}	
			}
			
		} else {//If name or dob not entered
			//prompt user to login again
			out.println("<p>" + "Incomplete login details, please re-enter data" + "</p>");
		}
		
		//Close html
		out.println("</body></html>");
		
		//close printwriter
		out.close();
	}
}
