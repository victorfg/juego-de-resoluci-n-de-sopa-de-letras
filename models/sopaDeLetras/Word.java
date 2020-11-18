package sopaDeLetras;

import javax.persistence.Embeddable;

@Embeddable
public class Word {
	private int id;
	private String value;
	
	public Word() {
		
	}
	
	public Word(int id, String value) {		
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
	
   @Override
   public String toString()
   {
      return "Result [id=" + id + ", value=" + value + "]";
   }
	
	/*public static List getAllLetters() {
	    
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
	}*/
}
