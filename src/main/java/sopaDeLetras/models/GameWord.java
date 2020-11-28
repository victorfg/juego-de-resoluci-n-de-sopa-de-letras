package sopaDeLetras.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="games_words")
public class GameWord {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	@Column(name="game_id")
	private int game_id;
	
	@Column(name="word_id")
	private int word_id;
	
	@Column(name="completed")
	private Boolean completed;
	
	public GameWord() {
		
	}
	
	public GameWord(int game_id, int word_id, Boolean completed) {
		this.game_id = game_id;
		this.word_id = word_id;
		this.completed = completed;
	}
	
	public GameWord(int id, int game_id, int word_id, Boolean completed) {
		this.id = id;
		this.game_id = game_id;
		this.word_id = word_id;
		this.completed = completed;
	}

	public int getId() {
		return id;
	}

	public void set(int id) {
		this.id = id;
	}
	
	public int getWordId() {
		return word_id;
	}

	public void setWordId(int word_id) {
		this.id = word_id;
	}
	
	public int getGameId() {
		return game_id;
	}

	public void setGameId(int game_id) {
		this.game_id = game_id;
	}
	
	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
}
