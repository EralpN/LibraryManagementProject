package com.bilgeadam.library.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.bilgeadam.library.dao.BookDao;
import com.bilgeadam.library.dao.LibraryUserDao;
import com.bilgeadam.library.dao.RentalsDao;
import com.bilgeadam.library.entity.Book;
import com.bilgeadam.library.entity.LibraryUser;
import com.bilgeadam.library.entity.Rentals;

public class RentalsService
{
	private Scanner numericIn = new Scanner(System.in);

	private RentalsDao rentalsDao = new RentalsDao();
	private BookDao bookDao = new BookDao();
	
	public void addRental()
	{
		rentalsDao.create(createRental());
	}

	public void updateRental()
	{
		listRentals();
		System.out.println();
		System.out.println("Choose which rental to update.");
		int selection = numericIn.nextInt();

		Rentals rental = createRental();

		rentalsDao.update(selection, rental);
	}

	public void deleteRental()
	{
		ArrayList<Rentals> rentals = listRentals();
		System.out.println();
		System.out.println("Choose which rental to delete.");
		int selection = numericIn.nextInt();
		rentals.stream().filter(r -> r.getOid() == selection).collect(Collectors.toList()).get(0).getBook().setAwayForRental(false);
		rentalsDao.delete(selection);
	}

	public ArrayList<Rentals> listRentals()
	{
		ArrayList<Rentals> rentals = rentalsDao.listAll();
		for (Rentals rental : rentals)
		{
			System.out.println(rental.getOid() + " - " + rental.getLibraryUser().getUserDetail().getFirstName() + " "
					+ rental.getLibraryUser().getUserDetail().getLastName() + " - Book: " + rental.getBook().getTitle()
					+ " - " + rental.getBorrowDate() + " - Days borrowed: " + rental.getBorrowDuration());
		}
		return rentals;
	}

	public ArrayList<Rentals> listActiveRentals()
	{
		ArrayList<Rentals> rentals = rentalsDao.listAll();
		for (Rentals rental : rentals)
		{
			if (!rental.isReturned())
			{
				System.out.println(rental.getOid() + " - " + rental.getLibraryUser().getUserDetail().getFirstName()
						+ " " + rental.getLibraryUser().getUserDetail().getLastName() + " - Book: "
						+ rental.getBook().getTitle() + " - " + rental.getBorrowDate() + " - Days borrowed: "
						+ rental.getBorrowDuration());
			}
		}
		return rentals;
	}

	public void listCurrentRentals(LibraryUser user)
	{
		ArrayList<Rentals> rentals = rentalsDao.listAll();
		for (Rentals rental : rentals)
		{
			if (rental.getLibraryUser().getOid() == user.getOid() && !rental.isReturned())
			{
				System.out.println(rental.getOid() + " - " + rental.getLibraryUser().getUserDetail().getFirstName()
						+ " " + rental.getLibraryUser().getUserDetail().getLastName() + " - Book: "
						+ rental.getBook().getTitle() + " - " + rental.getBorrowDate() + " - Days borrowed: "
						+ rental.getBorrowDuration());
			}
		}
	}

	public void listOldRentals(LibraryUser user)
	{
		ArrayList<Rentals> rentals = rentalsDao.listAll();
		for (Rentals rental : rentals)
		{
			if (rental.getLibraryUser().getOid() == user.getOid() && rental.isReturned())
			{
				System.out.println(rental.getOid() + " - " + rental.getLibraryUser().getUserDetail().getFirstName()
						+ " " + rental.getLibraryUser().getUserDetail().getLastName() + " - Book: "
						+ rental.getBook().getTitle() + " - " + rental.getBorrowDate() + " - Days borrowed: "
						+ rental.getBorrowDuration());
			}
		}
	}

	public void rentABook(LibraryUser user)
	{
		Date date = Date.valueOf(LocalDate.now());
		int days;

		do
		{
			System.out.println();
			System.out.println("How long is the rent duration (days)");
			days = numericIn.nextInt();
		}
		while (!(days > 0));

		rentalsDao.create(new Rentals(user, chooseFromBooks(), date, days));
	}

	public void returnABook(LibraryUser user)
	{
		ArrayList<Rentals> rentals = rentalsDao.listAll();
		ArrayList<Rentals> userRentals = (ArrayList<Rentals>) rentals.stream()
				.filter(r -> r.getLibraryUser().getOid() == user.getOid()).collect(Collectors.toList());

		for (Rentals rental : userRentals)
		{
			if (!rental.isReturned())
			{
				System.out.println(rental.getOid() + " - " + rental.getBook().getTitle() + " - Borrow Date:"
						+ rental.getBorrowDate() + " - Days borrowed:" + rental.getBorrowDuration());
			}
		}
		System.out.println();
		System.out.println("Which book do you want to return?");
		int selection = numericIn.nextInt();
		
		Rentals rental = userRentals.stream().filter(r -> r.getOid() == selection).collect(Collectors.toList()).get(0);
		Book book = rental.getBook();
		
		book.setAwayForRental(false);
		bookDao.update(book.getOid(), book);
		
		rental.setReturned(true);
		rentalsDao.update(rental.getOid(), rental);
		
		System.out.println("Returned.");
	}

	private Rentals createRental()
	{
		Date date = Date.valueOf(LocalDate.now());
		int days;

		do
		{
			System.out.println();
			System.out.println("How long is the rent duration (days)");
			days = numericIn.nextInt();
		}
		while (!(days > 0));

		return new Rentals(chooseFromUsers(), chooseFromBooks(), date, days);

	}

	private LibraryUser chooseFromUsers()
	{
		LibraryUserDao libraryUserDao = new LibraryUserDao();
		ArrayList<LibraryUser> libraryUsers = libraryUserDao.listAll();
		int selection;

		for (LibraryUser libraryUser : libraryUsers)
		{
			System.out.println(libraryUser.getOid() + " - " + libraryUser.getUserDetail().getFirstName() + " "
					+ libraryUser.getUserDetail().getLastName() + " --- " + libraryUser.getAccessLevel()
					+ libraryUser.getUsername() + " " + libraryUser.getPassword().replaceAll(".", "*"));
		}
		System.out.println();
		System.out.println("Choose which user rents a book.");
		selection = numericIn.nextInt();

		return libraryUsers.stream().filter(lU -> lU.getOid() == selection).collect(Collectors.toList()).get(0);
	}

	private Book chooseFromBooks()
	{
		BookDao bookDao = new BookDao();
		ArrayList<Book> books = bookDao.listAll();
		int selection;

		for (Book book : books)
		{
			if (!book.isAwayForRental())
			{
				System.out.println(book.getOid() + "\t" + book.getTitle() + " --- " + book.getAuthor().getFirstName()
						+ " " + book.getAuthor().getLastName());
			}
		}
		System.out.println();
		System.out.println("Choose which book to rent.");
		selection = numericIn.nextInt();

		Book book = books.stream().filter(b -> b.getOid() == selection).collect(Collectors.toList()).get(0);
		book.setAwayForRental(true);
		bookDao.update(book.getOid(), book);
		return book;
	}
}
