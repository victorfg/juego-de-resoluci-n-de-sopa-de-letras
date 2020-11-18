package sopaDeLetras;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class DAOWord implements Actions {
	private Word word;
	
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
	
	public DAOWord() {
	}
	
	@Override
	public Boolean insert(Object obj) {
		// TODO Auto-generated method stub

	
		try {
			  Session session = sessionFactory.openSession();			  
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
		try {
		      Session session = sessionFactory.openSession();
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
		try {
		      Session session = sessionFactory.openSession();
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
	

	@Override
	public List<?> all() {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.openSession();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Word> wordQuery = builder.createQuery(Word.class);
			List<Word> words = session.createQuery(wordQuery).getResultList();
			session.close();
			return words;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 return null;
		}    
	}
	
	
	//not implemented for this dao model
	@Override
	public Boolean delete(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
