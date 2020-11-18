package sopaDeLetras;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class DAOResult implements Actions {
	private Result result;
	
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
	
	public DAOResult() {
	}
	
	@Override
	public Boolean insert(Object obj) {
		// TODO Auto-generated method stub

	
		try {
			  Session session = sessionFactory.openSession();			  
		      session.beginTransaction();
		      
		      session.save((Result) obj);
		      
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
	
		      session.update((Result) obj);
		      
		      session.getTransaction().commit();
		      session.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 return false;
		}        		
	    return true;
	}

	@Override
	public Result select(int id) {
		// TODO Auto-generated method stub
		try {
		      Session session = sessionFactory.openSession();
		      session.beginTransaction();

		      Result result = session.get( Result.class, id );

		      session.getTransaction().commit();
		      session.close();
		      return result;
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
			CriteriaQuery<Result> resultQuery = builder.createQuery(Result.class);
			List<Result> results = session.createQuery(resultQuery).getResultList();
			session.close();
			return results;
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
