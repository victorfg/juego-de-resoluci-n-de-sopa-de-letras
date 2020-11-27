package sopaDeLetras.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="words")
public class Word {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	@Column(name="value")
	protected String value;
	
	public Word() {
		
	}
	
	public Word(String value) {		
		super();
		this.id = id;
		this.value = value;
	}
	
	public Word(int id, String value) {		
		super();
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
