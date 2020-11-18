package sopaDeLetras;

import java.sql.Date;
import javax.persistence.Embeddable;

@Embeddable
public class Result {
	private int id;
	private int ldap_id;
	private String word;
	private String table;
	private Date start_date;
	private Date end_date;
	
	public Result() {
		
	}
	
	public Result(int id, int ldap_id, String word, String table, Date start_date, Date end_date) {
		this.id = id;
		this.ldap_id = ldap_id;
		this.word = word;
		this.table = table;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLdap_id() {
		return ldap_id;
	}

	public void setLdap_id(int ldap_id) {
		this.ldap_id = ldap_id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
   @Override
   public String toString()
   {
      return "Result [id=" + id + ", ldap_id=" + ldap_id + ", word=" + word + ", table=" + table + ", start_date=" + start_date +", end_date=" + end_date +"]";
   }
	
}
