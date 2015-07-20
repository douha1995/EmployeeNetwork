import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import sef.domain.EmployeeEntity;

public class Main {
   private static SessionFactory sessionFactory = null; 
   
   public static void main(String[] args) {
	   try 
       {
        // Create the SessionFactory from hibernate.cfg.xml
        sessionFactory = new Configuration().configure().buildSessionFactory();
        } 
        catch (Throwable ex)
        {
          // Make sure you log the exception, as it might be swallowed
          System.err.println("Initial SessionFactory creation failed." + ex);
          throw new ExceptionInInitializerError(ex);
        }
	   
	   Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      Integer employeeID = null;
	      try{
	         tx = session.beginTransaction();
	         EmployeeEntity employee = new EmployeeEntity();
	         employee.setFirstName("FirstName-1");
	         employee.setLastName("LastName-1");
	         employee.setMiddleInitial("middle1");
	         employee.setLevel("level-1");
	         employee.setWorkForce("workforce-1");
	         employee.setEnterpriseID("Enterprise-1");

	         employeeID = (Integer) session.save(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   
	   
   }
}