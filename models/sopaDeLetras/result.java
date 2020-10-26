package sopaDeLetras;

import java.sql.Date;

public class result {
	private int id;
	private int ldap_id;
	private String word;
	private String table;
	private Date start_date;
	private Date end_date;
	
	public result() {
		
	}
	
	public result(int id, int ldap_id, String word, String table, Date start_date, Date end_date) {
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
	
}
