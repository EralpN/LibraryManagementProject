package com.bilgeadam.library.test;

import java.util.Date;

import com.bilgeadam.library.dao.AuthorDao;
import com.bilgeadam.library.dao.BookDao;
import com.bilgeadam.library.dao.LibraryUserDao;
import com.bilgeadam.library.dao.RentalsDao;
import com.bilgeadam.library.dao.UserDetailDao;
import com.bilgeadam.library.entity.AccessLevel;
import com.bilgeadam.library.entity.Author;
import com.bilgeadam.library.entity.Book;
import com.bilgeadam.library.entity.LibraryUser;
import com.bilgeadam.library.entity.Rentals;
import com.bilgeadam.library.entity.UserDetail;

public class CreateDBTablesFillDBForTesting
{
	/*
	 * This class only exists to test Database connectivity.
	 * 
	 * You can use this class to create Database tables.
	 * 
	 * You can use this class to fill database with some random information.
	 */
	
	
	
	public static void main(String[] args)
	{
		AuthorDao authorDao = new AuthorDao();
		BookDao bookDao = new BookDao();
		LibraryUserDao libUserDao = new LibraryUserDao();
		UserDetailDao userDetailDao = new UserDetailDao();
		RentalsDao rentalsDao = new RentalsDao();
		
		Author author1 = new Author("John", "Doe");
		Author author2 = new Author("Carmel ", "Irving");
		Author author3 = new Author("Ricardo ", "Herman");
		Author author4 = new Author("Rohan", "Hamilton");
		Author author5 = new Author("Vladimir", "Cook");
		Author author6 = new Author("Betsy", "Beasley");
		
		Book book1 = new Book("The Butcher in the Dusk");
		book1.setAuthor(author1);
		Book book2 = new Book("The Crystal in the Dark");
		book2.setAuthor(author1);
		Book book3 = new Book("The Prey in the Forest");
		book3.setAuthor(author1);
		Book book4 = new Book("Isle of Destiny");
		book4.setAuthor(author2);
		Book book5 = new Book("The Descendant of Dawn");
		book5.setAuthor(author2);
		Book book6 = new Book("One Bride, Two Bride");
		book6.setAuthor(author3);
		Book book7 = new Book("Trap the Devil");
		book7.setAuthor(author4);
		Book book8 = new Book("The Yellow Veil");
		book8.setAuthor(author5);
		Book book9 = new Book("Out of Time");
		book9.setAuthor(author6);
		
		UserDetail user1detail = new UserDetail("Eralp", "Nitelik");
		UserDetail user2detail = new UserDetail("Meghan", "Campbell");
		UserDetail user3detail = new UserDetail("Frida", "Herbert");
		UserDetail user4detail = new UserDetail("Jonas", "Cooley");
		
		LibraryUser user1 = new LibraryUser();
		user1.setAccessLevel(AccessLevel.ADMIN);
		user1.setUserDetail(user1detail);
		user1.setUsername("admin");
		user1.setPassword("admin");
		
		LibraryUser user2 = new LibraryUser();
		user2.setAccessLevel(AccessLevel.STUDENT);
		user2.setUserDetail(user2detail);
		user2.setUsername("student1");
		user2.setPassword("11111");
		
		LibraryUser user3 = new LibraryUser();
		user3.setAccessLevel(AccessLevel.STUDENT);
		user3.setUserDetail(user3detail);
		user3.setUsername("student2");
		user3.setPassword("22222");
		
		LibraryUser user4 = new LibraryUser();
		user4.setAccessLevel(AccessLevel.STUDENT);
		user4.setUserDetail(user4detail);
		user4.setUsername("student3");
		user4.setPassword("33333");
		
		Rentals rent1 = new Rentals();
		rent1.setLibraryUser(user2);
		rent1.setBook(book1);
		book1.setAwayForRental(true);
		rent1.setBorrowDate(new Date(System.currentTimeMillis()));
		rent1.setBorrowDuration(8);
		
		Rentals rent2 = new Rentals();
		rent2.setLibraryUser(user2);
		rent2.setBook(book2);
		book2.setAwayForRental(true);
		rent2.setBorrowDate(new Date(System.currentTimeMillis()));
		rent2.setBorrowDuration(7);
		
		authorDao.create(author1);
		authorDao.create(author2);
		authorDao.create(author3);
		authorDao.create(author4);
		authorDao.create(author5);
		authorDao.create(author6);
		
		bookDao.create(book1);
		bookDao.create(book2);
		bookDao.create(book3);
		bookDao.create(book4);
		bookDao.create(book5);
		bookDao.create(book6);
		bookDao.create(book7);
		bookDao.create(book8);
		bookDao.create(book9);
		
		userDetailDao.create(user1detail);
		userDetailDao.create(user2detail);
		userDetailDao.create(user3detail);
		userDetailDao.create(user4detail);
		
		libUserDao.create(user1);
		libUserDao.create(user2);
		libUserDao.create(user3);
		libUserDao.create(user4);
		
		rentalsDao.create(rent1);
		rentalsDao.create(rent2);
		
		bookDao.listAll();
		authorDao.listAll();
		libUserDao.listAll();
		rentalsDao.listAll();
		
//		bookDao.update(2, book1);
//		bookDao.update(3, book1);
//		bookDao.update(4, book1);
//		bookDao.update(5, book1);
//		bookDao.update(6, book1);
//		bookDao.update(7, book1);
//		bookDao.update(8, book1);
//		bookDao.update(9, book1);
//		
//		bookDao.listAll();
//		
//		rentalsDao.delete(1);
//		rentalsDao.delete(2);
//		
//		libUserDao.delete(1);
//		libUserDao.delete(2);
//		libUserDao.delete(3);
//		libUserDao.delete(4);
//		
//		userDetailDao.delete(1);
//		userDetailDao.delete(2);
//		userDetailDao.delete(3);
//		userDetailDao.delete(4);
//		
//		bookDao.delete(1);
//		bookDao.delete(2);
//		bookDao.delete(3);
//		bookDao.delete(4);
//		bookDao.delete(5);
//		bookDao.delete(6);
//		bookDao.delete(7);
//		bookDao.delete(8);
//		bookDao.delete(9);
//		
//		authorDao.delete(1);
//		authorDao.delete(2);
//		authorDao.delete(3);
//		authorDao.delete(4);
//		authorDao.delete(5);
//		authorDao.delete(6);	
	}

}
