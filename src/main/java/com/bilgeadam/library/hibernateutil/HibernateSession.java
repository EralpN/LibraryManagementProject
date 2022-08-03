package com.bilgeadam.library.hibernateutil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgeadam.library.entity.Author;
import com.bilgeadam.library.entity.Book;
import com.bilgeadam.library.entity.LibraryUser;
import com.bilgeadam.library.entity.Rentals;
import com.bilgeadam.library.entity.UserDetail;

public class HibernateSession
{
	private static final SessionFactory sessionFactory = sessionFactory();
	
	private static SessionFactory sessionFactory()
	{
		SessionFactory factory = null;
		try
		{
			Configuration configuration = new Configuration();
			
			configuration.addAnnotatedClass(Author.class);
			configuration.addAnnotatedClass(Book.class);
			configuration.addAnnotatedClass(LibraryUser.class);
			configuration.addAnnotatedClass(UserDetail.class);
			configuration.addAnnotatedClass(Rentals.class);
			
			factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();			
			System.out.println("Success!");
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		return factory;
	}

	public static SessionFactory getSessionfactory()
	{
		return sessionFactory;
	}
}
