package util;

import datamodel.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TodoDBUtil {
	static SessionFactory sessionFactory = null;

	   public static SessionFactory getSessionFactory() {
	      if (sessionFactory != null) {
	         return sessionFactory;
	      }
	      Configuration configuration = new Configuration().configure();
	      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	      sessionFactory = configuration.buildSessionFactory(builder.build());
	      return sessionFactory;
	   }
	   
	   public static List<Todo> listTodos() {
		      List<Todo> resultList = new ArrayList<Todo>();

		      Session session = getSessionFactory().openSession();
		      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

		      try {
		         tx = session.beginTransaction();
		         List<?> todos = session.createQuery("FROM TodoList_Gangisetti").list();
		         for (Iterator<?> iterator = todos.iterator(); iterator.hasNext();) {
		        	 Todo todo = (Todo) iterator.next();
		            resultList.add(todo);
		         }
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx != null)
		            tx.rollback();
		         e.printStackTrace();
		      } finally {
		         session.close();
		      }
		      return resultList;
		   }
	   public static void createTodo(String todo, boolean done) {
		      Session session = getSessionFactory().openSession();
		      Transaction tx = null;
		      try {
		         tx = session.beginTransaction();
		         session.save(new Todo(todo, Boolean.valueOf(done)));
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx != null)
		            tx.rollback();
		         e.printStackTrace();
		      } finally {
		         session.close();
		      }
		   }
}
