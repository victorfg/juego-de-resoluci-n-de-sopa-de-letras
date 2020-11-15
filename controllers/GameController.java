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
		// TODO Auto-generated method stub
		  response.setContentType("application/json");
		    response.setCharacterEncoding("utf-8");
		    PrintWriter out = response.getWriter();
		    
			// Estas dos variables son las que se tienen que enviar en el get request
			ArrayList<String> words = getWordsFromDB(4);
			//ArrayList<word> words = getWordsFromDB(4);
			ArrayList<char[]> table = generateTable(words);
			
			JSONObject json = new JSONObject();
			json.put("gameID", new Random().nextInt());
			JSONArray aWords = new JSONArray();
			aWords.addAll(words);
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

		    response.getWriter().print(json.toJSONString());			
	}
	
	private ArrayList<String> getWordsFromDB(int quantity) {
		//private ArrayList<word> getWordsFromDB(int quantity) {
		
		 DAOWord wordDB = new DAOWord();
		 ArrayList<String> words = new ArrayList<>();
		 List<?> word = wordDB.all();
		
		for(int i = 0; i < quantity; i ++) {
			int size = word.size();
			int randomNumber = (int) Math.random() * ((size - 0) + 1) + 0;
			words.add((String) word.get(randomNumber));
			word.remove(randomNumber);
		}
		
		/*			 	 
		 ArrayList<word> words = new ArrayList<>();
		 List<?> word = wordDB.all();
		
		for(int i = 0; i < quantity; i ++) {
			int size = word.size();
			int randomNumber = (int) Math.random() * ((size - 0) + 1) + 0;
			words.add((word) word.get(randomNumber));
			word.remove(randomNumber);
		}
		*/
		
		return words;
	}
	
	private ArrayList<char[]> generateTable(ArrayList<String> words){
		//private char[][] generateTable(ArrayList<word> words){
		int tableLength = 8;
		char[][] table = new char[tableLength][tableLength];
		
		for(int i = 0; i < tableLength; i++) {
			for(int j = 0; j < tableLength; j ++) {
					table[i][j] = ' ';
			}
		}
		
		for(int i = 0; i < words.size(); i++) {
			String word = words.get(i).toString();

			Direction direction = generateDirection();
			
			int minPos = 0;
			int maxPos = 7;
			
			int wordSize = word.length();
			
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
			
			int initialVerPos = (int) Math.random() * ((maxPos - minPos) + 1) + minPos;
			int initialHorPos = (int) Math.random() * ((maxPos - minPos) + 1) + minPos;
			
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
		int randomNumber = (int) (Math.random() * 4);
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