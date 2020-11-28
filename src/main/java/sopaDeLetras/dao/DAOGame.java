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
import sopaDeLetras.models.Word;

public class DAOGame implements Actions {
	private Game game;
	
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
	
	public DAOGame() {
	}
	
	@Override
	public int insert(Object obj) {
		Transaction transaction = null;
		int gameId = -1;
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();	      
			session.save((Game) obj);
			gameId = ((Game)obj).getId();
	      
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
	
	        session.update((Game) obj);
	      
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
	
	public Boolean closeOldSessionsByUser(String ldap_user) {
		Transaction transaction = null;
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();	      
			
			String queryString="UPDATE Game SET end_date = :end_date WHERE ldap_user = :ldap_user  AND end_date is null";
			Query query = session.createQuery(queryString);
			query.setParameter("ldap_user", ldap_user);
			query.setParameter("end_date", Date.valueOf(LocalDate.now()));
			query.executeUpdate();	
	      
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
	public Game select(int id) {
		Transaction transaction = null;
		Game game = null;
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

		    game = session.get( Game.class, id );

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}   
	    return game;
	}

	@SuppressWarnings("unchecked")
	public List<Game> all() {
		Transaction transaction = null;
		List<Game> listOfgame = null;
		try (Session session = DatabaseHelper.getSessionFactory().openSession()) {
	
			transaction = session.beginTransaction();

			Query query = session.createQuery("from game");
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
