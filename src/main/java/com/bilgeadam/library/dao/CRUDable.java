package com.bilgeadam.library.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.SelectionQuery;

import com.bilgeadam.library.hibernateutil.HibernateSession;

/* 
 * This code is written by Eralp Nitelik.
 * 
 * This interface implements all CRUD operations as default methods.
 * 
 * Entity class name extension must be specified in "getNameAddition()"!!!
 * Naming of entity and dao classes should be done as follows;
 * Entity.class, EntityDao.class
 * getNameAddition() should return "Dao"!
 * 
 * Entity id name must be specified in "getIdName()"!!! 
 * getIdName() should return "oid"!
 * 
 * Modify the first two methods according to your class name structure.
 */
 

public interface CRUDable<T>
{
	private String getNameAddition()
	{
		// Enter class extension name for dao classes here.
		return "Dao";
	}
	private String getIdName()
	{
		// Enter name of id thats being used in your entity classes here.
		return "oid";
	}
	
	default void create(T entity)
	{
		try
		{
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Created.");
			session.close();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	default void update(long oid, T newEntity)
	{
		try
		{
			boolean found = false;
			T entityToUpdate = find(oid);
			if(entityToUpdate != null)
			{
				found = true;
				
				// id name starts with uppercase methods
				String idNameInMethod = getIdName().substring(0,1).toUpperCase() + getIdName().substring(1);
				
				Method[] methods = entityToUpdate.getClass().getDeclaredMethods();
				
				for (Method getter : methods)
				{
					String methodName = getter.getName();
					if ((methodName.startsWith("get") || methodName.startsWith("is")) && !methodName.contains(idNameInMethod))
					{
						if (methodName.startsWith("get"))
						{
							methodName = methodName.replaceFirst("get", "");
						}
						else
						{
							methodName = methodName.replaceFirst("is", "");
						}
						
						for (Method setter : methods)
						{
							String methodName2 = setter.getName();
							if (methodName2.startsWith("set") && methodName2.contains(methodName))
							{
								// old.setter(new.getter());
								setter.invoke(entityToUpdate, getter.invoke(newEntity));
								break;
							}
						}
					}
				}
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(entityToUpdate);
				session.getTransaction().commit();
				System.out.println("Updated.");
				session.close();
			}
			if (!found)
			{
				System.out.println("No entry found, create first.");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	default void delete(long oid)
	{
		try
		{
			T entityToDelete = find(oid);
			if(entityToDelete != null)
			{
				Session session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(entityToDelete);
				session.getTransaction().commit();
				System.out.println("Deleted.");
				session.close();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	default T find(long oid)
	{
		String daoName = this.getClass().getSimpleName();
		String className = daoName.replaceFirst(getNameAddition(), "");
		
		Session session = databaseConnectionHibernate();
		
		String hql = "SELECT xxx FROM " + className + " AS xxx WHERE xxx." + getIdName() + " = :key";
		
		@SuppressWarnings("unchecked")
		SelectionQuery<T> query = (SelectionQuery<T>) session.createSelectionQuery(hql);
		
		query.setParameter("key", oid);
		T entity = (T) query.getSingleResult();
		
		session.close();
		return entity;
	}
	
	default ArrayList<T> listAll()
	{
		String daoName = this.getClass().getSimpleName();
		String className = daoName.replaceFirst(getNameAddition(), "");
		
		Session session = databaseConnectionHibernate();
		
		String hql = "SELECT xxx FROM " + className + " AS xxx WHERE xxx." + getIdName() + " >= :key";
		
		@SuppressWarnings("unchecked")
		SelectionQuery<T> query = (SelectionQuery<T>) session.createSelectionQuery(hql);
		long oid = 1L;
		query.setParameter("key", oid);
		
		ArrayList<T> entityList = (ArrayList<T>) query.getResultList();
		
		session.close();
		return entityList;
	}
	
	
	default Session databaseConnectionHibernate()
	{
		return HibernateSession.getSessionfactory().openSession();
	}
}
