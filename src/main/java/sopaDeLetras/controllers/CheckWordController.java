package sopaDeLetras.controllers;
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

import sopaDeLetras.dao.DAOGameWord;
import sopaDeLetras.dao.DAOWord;
import sopaDeLetras.models.GameWord;
import sopaDeLetras.models.Word;
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
		response.setContentType("application/json");
	    response.setCharacterEncoding("utf-8");
	    PrintWriter out = response.getWriter();
	    if(request.getParameter("game_id")==null || request.getParameter("word")==null || request.getParameter("ldap_user")==null) {
	    	JSONObject json = new JSONObject();
	    	json.put("error", "ldap_user,gameId and ldap_user params  cannot be null");				
	    	response.getWriter().print(json.toJSONString());
	    }
		int gameId = Integer.parseInt(request.getParameter("game_id"));
		String word = request.getParameter("word");
		Word selWord = null;
		String ldapUser = request.getParameter("ldap_user");
		
		Boolean validated = false;
		Boolean finished = false;
		List<Word> words = new DAOWord().all();
		for(Word wordItem: words) {
			if(wordItem.getValue().equals(word)) {
				GameWord gameWord = new DAOGameWord().selectByWord(wordItem.getId(),gameId);
				gameWord.setCompleted(true);
				validated = (new DAOGameWord()).update(gameWord);
				break;
			}			
		}
		if(validated) {
			finished = (new DAOGameWord()).checkIfGameEnded(gameId);
		}
		JSONObject json = new JSONObject();
		json.put("wordValidated", validated);
		json.put("gameFinished",finished) ;
		response.getWriter().print(json.toJSONString());	
	}
}