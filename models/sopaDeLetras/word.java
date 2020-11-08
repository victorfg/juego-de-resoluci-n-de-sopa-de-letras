package sopaDeLetras;
import java.util.ArrayList;
import java.util.List;

public class word {
	private int id;
	private String value;
	
	public word() {
		
	}
	
	public word(int id, String value) {		
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	public static List getAllLetters() {
	    
	       	List listaLetras = new ArrayList<>();
	        
	       	listaLetras.add("A");
	       	listaLetras.add("C");
	        listaLetras.add("E");
	        listaLetras.add("H");
	        listaLetras.add("Q");
	        listaLetras.add("Q");
	        listaLetras.add("F");
	        listaLetras.add("G");
	        
	        return listaLetras;
	}
}
