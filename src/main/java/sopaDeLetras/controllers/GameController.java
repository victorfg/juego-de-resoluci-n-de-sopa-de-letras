package sopaDeLetras.controllers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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

import sopaDeLetras.dao.DAOGame;
import sopaDeLetras.dao.DAOGameWord;
import sopaDeLetras.dao.DAOWord;
import sopaDeLetras.helpers.JsonHelper;
import sopaDeLetras.models.Game;
import sopaDeLetras.models.GameWord;
import sopaDeLetras.models.Word;

/**
 * Servlet implementation class GameController
 */
@WebServlet("/game")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private enum Direction {UP, DOWN, LEFT, RIGTH};
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("application/json");
		    response.setCharacterEncoding("utf-8");
		    if(request.getParameter("ldap_user")==null) {
		    	JSONObject json = new JSONObject();
		    	json.put("ldap_user", null);
		    	json.put("error", "ldap_user cannot be null");				
		    	response.getWriter().print(json.toJSONString());
		    }
		    String ldap_user = request.getParameter("ldap_user");
		    PrintWriter out = response.getWriter();
		    //TODO closing old sesions for this user
		    (new DAOGame()).closeOldSessionsByUser(ldap_user);
		    //TODO generating new session for this user		    	
			ArrayList<Word> words = getWordsFromDB(4);
			ArrayList<char[]> table = generateTable(words);			
			//TODO saving new session
			int gameId = 0;
			Game game = new Game(
					ldap_user, JsonHelper.toJSON(table), Date.valueOf(LocalDate.now())
					);
			gameId = (new DAOGame()).insert(game);
			for(Word word: words) {
				GameWord gameWord = new GameWord(
						gameId, word.getId(), false
						);
				(new DAOGameWord()).insert(gameWord);
			}
			//formating result	
			String jsonResult = this.format(gameId, words, table);			
			
		    response.getWriter().print(jsonResult);			
	}
	private String format(int gameId,ArrayList<Word> words,ArrayList<char[]> table) {
		JSONObject json = new JSONObject();
		json.put("gameID", gameId);
		JSONArray aWords = new JSONArray();
		for(Word word: words) {
			aWords.add(word.getValue());
		}
		json.put("words", aWords);
		JSONArray aTable = new JSONArray();
		for(int i = 0; i<table.size();i++) {
			JSONArray tmpArr = new JSONArray();
			for(int b = 0; b<table.get(i).length;b++) {
				tmpArr.add(Character.toString(table.get(i)[b]));				
			}
			aTable.add(tmpArr); 
			//aTable.add((new JSONArray()).addAll(table.get(i))); 
		}		
	    json.put("table", aTable);
	    return json.toJSONString();
	}
	
	private ArrayList<Word> getWordsFromDB(int quantity) {
		 ArrayList<Word> words = new ArrayList<>();
		 List<Word> wordItems = (new DAOWord()).all();
	

		for(int i = 0; i < quantity; i ++) {	
			 int size = wordItems.size() - 1;
			 if(size==0) {
				 return words;
			 }
			int randomNumber = (int) new Random().nextInt(size - 0 + 1) + 0;
			words.add(wordItems.get(randomNumber)); //.getValue()
			wordItems.remove(randomNumber);
		}
		return words;
	}
	
	private ArrayList<char[]> generateTable(ArrayList<Word> words){
		//private char[][] generateTable(ArrayList<word> words){
		int tableLength = 8;
		char[][] table = new char[tableLength][tableLength];
		
		for(int i = 0; i < tableLength; i++) {
			for(int j = 0; j < tableLength; j ++) {
					table[i][j] = ' ';
			}
		}
		
		for(int i = 0; i < words.size(); i++) {
			String word = words.get(i).getValue().toString();

			Direction direction = generateDirection();
			
			int minPos = 0;
			int maxPos = 7;
			
			int wordSize = word.length()-1;
			
			switch(direction) {
			case DOWN:
			case RIGTH:
				maxPos = 7 - wordSize;
				break;
			case UP:
			case LEFT:  
				minPos = 7;
				maxPos = 7 - wordSize;
				
				word = new StringBuilder(word).reverse().toString();
				break;
			}
			
			int initialVerPos = (int) ((minPos>maxPos)?new Random().nextInt(minPos - maxPos + 1) + maxPos:new Random().nextInt(maxPos - minPos + 1) + minPos);
			int initialHorPos = (int) ((minPos>maxPos)?new Random().nextInt(minPos - maxPos + 1) + maxPos:new Random().nextInt(maxPos - minPos + 1) + minPos);

			
			int charPos = 0;
			
			switch(direction) {
				case DOWN:
					charPos = 0;
					for(int pos = initialVerPos; pos<wordSize; pos++) {
						table[pos][initialHorPos] = word.charAt(charPos);
						charPos++;
					}
					break;
				case RIGTH:
					charPos = 0;
					for(int pos = initialHorPos; pos<wordSize; pos++) {
						table[initialVerPos][pos] = word.charAt(charPos);
						charPos++;
					}
					break;
				case UP:
					charPos = 0;
					for(int pos = initialVerPos; charPos<wordSize; pos--) {
						table[pos][initialHorPos] = word.charAt(charPos);
						charPos++;
					}
					break;
				case LEFT:
					charPos = 0;
					for(int pos = initialHorPos; charPos<wordSize; pos--) {
						table[initialVerPos][pos] = word.charAt(charPos);
						charPos++;
					}
					break;
			}
		}
		
		for(int i = 0; i < tableLength; i++) {
			for(int j = 0; j < tableLength; j ++) {
				if(table[i][j] == ' ') {
					table[i][j] = getRandomChar();
				}
			}
		}
		ArrayList<char[]> response = new ArrayList<char[]>();
		/*for(int i=0; i<table.length; i++) {
			response.add(table[0]);
		}*/
		response.addAll(Arrays.asList(table));
		return response;
	}
	
	private Direction generateDirection() {
		int randomNumber = (int) new Random().nextInt(4);
				//(Math.random() * 4);
		Direction direction = null;
		switch(randomNumber) {
			case 0:
				direction = Direction.UP;
				break;
			case 1: 
				direction = Direction.DOWN;
				break;
			case 2: 
				direction = Direction.LEFT;
				break;
			case 3: 
				direction = Direction.RIGTH;
				break;
		}
		return direction;
	}
	
	private char getRandomChar() {
		Random r = new Random();
		String alphabet = "abcdefghijklmnñopqrstuvwxyz";
		
		char randomChar = alphabet.charAt(r.nextInt(alphabet.length()));
		return randomChar;
	}
}