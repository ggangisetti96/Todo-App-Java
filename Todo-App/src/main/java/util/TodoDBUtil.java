package util;

import datamodel.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		         List<?> todos = session.createQuery("FROM Todo where completed = null").list();
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
	   
	   public static List<Todo> listCompleted() {
		      List<Todo> resultList = new ArrayList<Todo>();

		      Session session = getSessionFactory().openSession();
		      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

		      try {
		         tx = session.beginTransaction();
		         List<?> todos = session.createQuery("FROM Todo where completed = 'y' ").list();
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
	   
	   public static void createTodo(String todo, String done) {
		      Session session = getSessionFactory().openSession();
		      Transaction tx = null;
		      try {
		         tx = session.beginTransaction();
		         session.save(new Todo(todo, done));
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx != null)
		            tx.rollback();
		         e.printStackTrace();
		      } finally {
		         session.close();
		      }
		   }
	   
	   public static void markTodo(int id) {
		      Session session = getSessionFactory().openSession();
		      Transaction tx = null;
		      try {
		         tx = session.beginTransaction();
		         session.createQuery("UPDATE Todo SET completed = null, completedAt = null where id=" + id).executeUpdate();
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx != null)
		            tx.rollback();
		         e.printStackTrace();
		      } finally {
		         session.close();
		      }
		   }
	   
	   public static void markComplete(int id) {
		      Session session = getSessionFactory().openSession();
		      Transaction tx = null;
		      try {
		         tx = session.beginTransaction();
		         String completedAt = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
		         session.createQuery("UPDATE Todo SET completed = 'y', completedAt='"  + completedAt + "' "  + "where id=" + id).executeUpdate();
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx != null)
		            tx.rollback();
		         e.printStackTrace();
		      } finally {
		         session.close();
		      }
		   }
	   
	   public static void deleteTodo(int id) {
		      Session session = getSessionFactory().openSession();
		      Transaction tx = null;
		      try {
		         tx = session.beginTransaction();
		         session.createQuery("DELETE FROM Todo where id=" + id).executeUpdate();
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
