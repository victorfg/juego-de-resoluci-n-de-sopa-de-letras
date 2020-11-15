import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import sopaDeLetras.DAOWord;
import sopaDeLetras.word;
/**
 * Servlet implementation class GameController
 */
@WebServlet("/checkword")
public class CheckWordController extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckWordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("id");
		String pass = request.getParameter("password");
		if ("edu4java".equals(user) && "eli4java".equals(pass)) {
			response(resp, "login ok");
		} else {
			response(resp, "invalid login");
		}
	}
}