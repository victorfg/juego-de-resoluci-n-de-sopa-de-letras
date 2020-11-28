package sopaDeLetras.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sopaDeLetras.helpers.DatabaseHelper;
import sopaDeLetras.models.Game;
import sopaDeLetras.models.Word;



public class DAOWord implements Actions {
	private Word word;
	
  	public DAOWord() {
	}
	
	@Override
	public int insert(Object obj) {
		Transaction transaction = null;
		int resultId = -1;
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {		  
			  transaction = session.beginTransaction();
		      
				session.save((Word) obj);
				resultId = ((Word)obj).getId();
		      
		      transaction.commit();	      
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return resultId;
		}        		
        return resultId;
	}
	

	@SuppressWarnings("unchecked")
	public List<Word> all() {
		Transaction transaction = null;
		List<Word> listOfWord = null;
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {
	
			transaction = session.beginTransaction();

			Query query = session.createQuery("from Word");
			listOfWord = query.getResultList();
			
		
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfWord;
	}
	
	
	//not implemented for this dao model
	@Override
	public Boolean delete(Object obj) {
		return null;
	}
	
	@Override
	public Boolean update(Object obj) {
		return null;
	}

	@Override
	public Word select(int id) {
			 return null; 	
	}
}
