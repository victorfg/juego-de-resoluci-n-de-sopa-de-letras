package sopaDeLetras.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sopaDeLetras.helpers.DatabaseHelper;
import sopaDeLetras.models.Word;



public class DAOWord implements Actions {
	private Word word;
	
  	public DAOWord() {
	}
	
	@Override
	public Boolean insert(Object obj) {
		// TODO Auto-generated method stub	
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {		  
		      session.beginTransaction();
		      
		      session.save((Word) obj);
		      
		      session.getTransaction().commit();
		      session.close();		      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 return false;
		}        		
        return true;
	}
	@Override
	public Boolean update(Object obj) {
		// TODO Auto-generated method stub
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {		
		      session.beginTransaction();	      
	
		      session.update((Word) obj);
		      
		      session.getTransaction().commit();
		      session.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 return false;
		}        		
	    return true;
	}

	@Override
	public Word select(int id) {
		// TODO Auto-generated method stub
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {		
		      session.beginTransaction();

		      Word word = session.get( Word.class, id );

		      session.getTransaction().commit();
		      session.close();
		      return word;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 return null;
		}        	
	}
	

	@SuppressWarnings("unchecked")
	public List<Word> all() {
		Transaction transaction = null;
		List<Word> listOfWord = null;
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			Query query = session.createQuery("from Word");
			listOfWord = query.getResultList();
			
			// commit transaction
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
		// TODO Auto-generated method stub
		return null;
	}
}
