package sopaDeLetras.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import sopaDeLetras.helpers.DatabaseHelper;
import sopaDeLetras.models.Game;
import sopaDeLetras.models.GameWord;
import sopaDeLetras.models.Word;

public class DAOGameWord implements Actions {
	private GameWord gameWord;
	
   private static SessionFactory sessionFactory = null;

   @BeforeClass
   public static void setUp() throws Exception
   {
      sessionFactory = new Configuration().configure().buildSessionFactory();

   }

   @AfterClass
   public static void tearDown() throws Exception
   {
      sessionFactory.close();
   }
	
	public DAOGameWord() {
	}
	
	@Override
	public int insert(Object obj) {
		Transaction transaction = null;
		int gameId = -1;
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();	      
			session.save((GameWord) obj);
			gameId = ((GameWord)obj).getId();
	      
	        transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return gameId;
		}      		
	    return gameId;
	}
	@Override
	public Boolean update(Object obj) {
		Transaction transaction = null;
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();	      
	
	        session.update((GameWord) obj);
	      
	        transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}      		
	    return true;
	}

	@Override
	public GameWord select(int id) {
		Transaction transaction = null;
		GameWord gameWord = null;
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			gameWord = session.get( GameWord.class, id );

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}   
	    return gameWord;
	}

	@SuppressWarnings("unchecked")
	public List<Game> all() {
		Transaction transaction = null;
		List<Game> listOfgame = null;
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {
	
			transaction = session.beginTransaction();

			Query query = session.createQuery("from GameWord");
			listOfgame = query.getResultList();			
		
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfgame;
	}
	
	
	//not implemented for this dao model
	@Override
	public Boolean delete(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
