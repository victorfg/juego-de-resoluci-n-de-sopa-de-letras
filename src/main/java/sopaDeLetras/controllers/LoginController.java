package sopaDeLetras.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    response.setContentType("application/json");
	    response.setCharacterEncoding("utf-8");
	    PrintWriter out = response.getWriter();
	    JSONObject json = new JSONObject();
		try
		  {
			  Hashtable ldapEnv = new Hashtable();
			  ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			  ldapEnv.put(Context.PROVIDER_URL, "ldap://localhost:10389");
			  ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			  ldapEnv.put(Context.SECURITY_PRINCIPAL , "uid="+username+",ou=system");
			  ldapEnv.put(Context.SECURITY_CREDENTIALS, password);
			  System.out.println(" autenticando:");		  
			  
			    try {
			        // Create initial context
			        DirContext ctx = new InitialDirContext(ldapEnv);

			        // Close the context when we're done
			        request.getRequestDispatcher("/game.jsp").forward(request, response);
			        ctx.close();

		        } catch (NamingException e) {
		        	request.setAttribute("message", "Credenciales incorrectas");
		            request.getRequestDispatcher("/index.jsp").forward(request, response);
		        }
		  }
		  catch (Exception e) {
			// TODO: handle exception
		}
		response.getWriter().print(json.toJSONString());
	}

}
