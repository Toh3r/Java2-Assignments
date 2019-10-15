package assignment6_Persistance;

//Import java packages
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

//Create the servlets URL
@WebServlet("/football")
public class WebShopServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	//Create header and footer
	static String PAGE_HEADER = "<html><head><title>Sport Shop</title><body>";
	static String PAGE_FOOTER = "</body></html>";
	
	//Create instance of entitymanager
	@PersistenceUnit(unitName= "JPA4")
	private EntityManagerFactory entityManagerFactory;
	
	//Create instance of usertransaction
	@Resource
	private UserTransaction userTransaction;
	
	//Create doGet method
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		
		response.setContentType("text/html");
		
		//Create new print writer
		PrintWriter out = response.getWriter();
		//Print page header
		out.println(PAGE_HEADER);
		
		//Print form to web page
		out.println("<body>" + "<h2>Football Addition - Managers ONLY</h2>" +
		"<form method=\"get\">" +
		"Football brand: <input type=\"text\" name=\"football_brand\" size=\"25\">" + "<p></p>"+
		"Football price: <input type=\"number\" name=\"football_price\" size=\"5\">" + "<p></p>"+
		"Football color: <input type=\"text\" name=\"football_color\" size=\"25\">" + "<p></p>"+
		"<p><input type=\"submit\" value=\"Submit\">" +
		"<input type=\"reset\" value=\"Reset\">" +
		"</form>");
		
		//Declare variables from users form input
		String footballBrand = request.getParameter("football_brand");
		String footballPriceStr = request.getParameter("football_price");
		String footballColor = request.getParameter("football_color");
		
		//If all fields contain characters
		if(footballColor!= null && footballColor.length() > 0 && footballPriceStr!= null && footballPriceStr.length() > 0 && footballBrand!= null && footballBrand.length() > 0){
			
			//Create football and add attributes
			Equipment football = new Equipment();
			football.setBrand(footballBrand);
			football.setPrice(Integer.valueOf(footballPriceStr));
			football.setColor(footballColor);
			
			try { 
				//create user transaction and entity manager
				userTransaction.begin();
				EntityManager entityManager= entityManagerFactory.createEntityManager();
				
				//Print details to webpage
				out.println("Adding football to database...<p>");
				out.println("Football Details:<p>");
				out.println("Football Brand: "+ footballBrand + "<p>");
				out.println("Football Price: $"+ footballPriceStr +  "<p>");
				out.println("Football Color: "+ footballColor + "<p>");
				
				//Add football to database
				entityManager.persist(football);
				//Close entity manager
				entityManager.close();
				userTransaction.commit();
				//Print to webpage
				out.println("Update complete.");
				}
				catch(Exception e) {
				out.println("Error: " + e + "<p>");//Print if exception thrown
				}
			}
			
			//Print search form to webpage
			out.println("<body>" + "<h2>Football Search </h2>" +
			"<form method=\"get\">" +
			"Football brand: <input type=\"text\" name=\"brand\" size=\"25\">" + "<p></p>"+
			"Football price: <input type=\"number\" name=\"price\" size=\"5\">" + "<p></p>"+
			"Football color: <input type=\"text\" name=\"color\" size=\"25\">" + "<p></p>"+
			"<p><input type=\"submit\" value=\"Submit\">" +
			"<input type=\"reset\" value=\"Reset\">" +
			"</form>");
			
			//Declare user inputs as variavles
			String brand = request.getParameter("brand");
			String priceStr = request.getParameter("price");
			String color = request.getParameter("color");
			
			//If all fields contain characters
			if(brand!= null && brand.length() > 0 && color!= null && color.length() > 0 && priceStr!= null && priceStr.length() > 0){
				//Create new entity manager
				EntityManager entityManager= entityManagerFactory.createEntityManager();
				
				//Create new JPQL query 
				TypedQuery<Equipment> query = entityManager.createQuery
						("Select c from Equipment c where c.brand = '"+ brand 
						+"' And c.color = '" + color + "' And c.price = '" + priceStr+ "'", Equipment.class);
				
				//Place query results in list
				List<Equipment> results = (List<Equipment>)query.getResultList( );
				
				//Close entity manager
				entityManager.close();
				//Use enhanced for loop to traverse list
				for(Equipment c: (results))
					//Print results to webpage
						out.println(c.getBrand() + ", $" + c.getPrice() + ", " + c.getColor() + "<p>");
			}
			
			//If brand and color contain characters
			else if(brand!= null && brand.length() > 0 && color!= null && color.length() > 0){
				//Create new entity manager
				EntityManager entityManager= entityManagerFactory.createEntityManager();

				TypedQuery<Equipment> query = entityManager.createQuery
						("Select c from Equipment c where c.brand = '"+ brand +"' And c.color = '" + color + "'", Equipment.class);
				List<Equipment> results = (List<Equipment>)query.getResultList( );

				entityManager.close();

				for(Equipment c: (results))
						out.println(c.getBrand() + ", $" + c.getPrice() + ", " + c.getColor() + "<p>");
			}
			
			//If brand and price contain characters
			else if(brand!= null && brand.length() > 0 && priceStr!= null && priceStr.length() > 0){
				EntityManager entityManager= entityManagerFactory.createEntityManager();
				
				TypedQuery<Equipment> query = entityManager.createQuery
						("Select c from Equipment c where c.brand = '"+ brand +"' And c.price = '" + priceStr + "'", Equipment.class);
				List<Equipment> results = (List<Equipment>)query.getResultList( );
				
				entityManager.close();
				
				for(Equipment c: (results))
						out.println(c.getBrand() + ", $" + c.getPrice() + ", " + c.getColor() + "<p>");
			}
			
			//If color and price contain characters
			else if(color!= null && color.length() > 0 && priceStr!= null && priceStr.length() > 0){
				EntityManager entityManager= entityManagerFactory.createEntityManager();
				
				TypedQuery<Equipment> query = entityManager.createQuery
						("Select c from Equipment c where c.color = '"+ color +"' And c.price = '" + priceStr + "'", Equipment.class);
				List<Equipment> results = (List<Equipment>)query.getResultList( );
				
				entityManager.close();
				
				for(Equipment c: (results))
						out.println(c.getBrand() + ", $" + c.getPrice() + ", " + c.getColor() + "<p>");
			}
			
			//If only color contains characters
			else if(color!= null && color.length() > 0){
				EntityManager entityManager= entityManagerFactory.createEntityManager();
				
				TypedQuery<Equipment> query = entityManager.createQuery("Select c from Equipment c where c.color = '"+ color +"'", Equipment.class);
				List<Equipment> results = (List<Equipment>)query.getResultList( );
				
				entityManager.close();
				
				for(Equipment c: (results))
						out.println(c.getBrand() + ", $" + c.getPrice() + ", " + c.getColor() + "<p>");
			}
			
			//If only price contains characters
			else if(priceStr!= null && priceStr.length() > 0){
				EntityManager entityManager= entityManagerFactory.createEntityManager();
				
				TypedQuery<Equipment> query = entityManager.createQuery("Select c from Equipment c where c.price = '"+ priceStr +"'", Equipment.class);
				List<Equipment> results = (List<Equipment>)query.getResultList( );
				
				entityManager.close();
				
				for(Equipment c: (results))
						out.println(c.getBrand() + ", $" + c.getPrice() + ", " + c.getColor() + "<p>");
			}
			
			//If only brand contains characters
			else if(brand!= null && brand.length() > 0){
				EntityManager entityManager= entityManagerFactory.createEntityManager();
				
				TypedQuery<Equipment> query = entityManager.createQuery("Select c from Equipment c where c.brand = '"+ brand +"'", Equipment.class);
				List<Equipment> results = (List<Equipment>)query.getResultList( );
				
				entityManager.close();
				
				for(Equipment c: (results))
						out.println(c.getBrand() + ", $" + c.getPrice() + ", " + c.getColor() + "<p>");
			}
			
			//Print to webpage
			response.getWriter().println("<p>Thank for shopping");
			//Print page footer
			out.println(PAGE_FOOTER);
			//Close print writer
			out.close();
		}
	}