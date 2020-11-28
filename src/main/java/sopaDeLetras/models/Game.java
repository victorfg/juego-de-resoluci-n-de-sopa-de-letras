package sopaDeLetras.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="games")
public class Game {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	@Column(name="ldap_user")
	private String ldap_user;
	
	@Column(name="board", length = 65535, columnDefinition="TEXT")
	@Type(type="text")
	private String board;
	
	@Column(name="completed", nullable = true)
	private String completed;
	
	@Column(name="start_date")
	private Date start_date;
	
	@Column(name="end_date", nullable = true)
	private Date end_date;

	
	public Game() {
		
	}
	
	public Game(String ldap_user, String board, Date start_date) {
		this.ldap_user = ldap_user;
		this.board = board;
		this.completed = null;
		this.start_date = start_date;
		this.end_date = null;
	}
	
	public Game(int id, String ldap_user, String board, String completed, Date start_date, Date end_date) {
		this.id = id;
		this.ldap_user = ldap_user;
		this.board = board;
		this.completed = completed;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLdap_user() {
		return this.ldap_user;
	}

	public void setLdap_user(String ldap_user) {
		this.ldap_user = ldap_user;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
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
      return "Result [id=" + id + ", ldap_user=" + ldap_user + ", table=" + table + ", start_date=" + start_date +", end_date=" + end_date +"]";
   }
	
}
