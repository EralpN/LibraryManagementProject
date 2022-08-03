package com.bilgeadam.library.controller;

import java.util.Scanner;

import com.bilgeadam.library.service.AuthorService;
import com.bilgeadam.library.service.BookService;
import com.bilgeadam.library.service.LibraryUserService;
import com.bilgeadam.library.service.RentalsService;

public class AdminController
{
	private Scanner numericIn = new Scanner(System.in);

	private AuthorService authorService = new AuthorService();
	private BookService bookService = new BookService();
	private LibraryUserService libraryUserService = new LibraryUserService();
	private RentalsService rentalsService = new RentalsService();
	
	public void adminMenu()
	{
		while (true)
		{
			int selection;

			do
			{
				System.out.println();
				System.out.println("1- List all authors.");
				System.out.println("2- Add a author.");
				System.out.println("3- Update a author.");
				System.out.println("4- Delete a author.");

				System.out.println("5- List all books.");
				System.out.println("6- Add a book.");
				System.out.println("7- Update a book.");
				System.out.println("8- Delete a book.");

				System.out.println("9- List all users.");
				System.out.println("10- Add a user.");
				System.out.println("11- Update a user.");
				System.out.println("12- Delete a user.");

				System.out.println("13- List all rentals.");
				System.out.println("14- List active rentals.");
				System.out.println("15- Add a rental.");
				System.out.println("16- Update a rental.");
				System.out.println("17- Delete a rental.");

				System.out.println("18- Log Out.");
				selection = numericIn.nextInt();
			}
			while (!(1 <= selection && selection <= 18));

			if (selection == 18)
			{
				System.out.println("Logging out.");
				break;
			}

			switch (selection)
			{
			case 1:
				authorService.listAuthors();
				break;
			case 2:
				authorService.addAuthor();
				break;
			case 3:
				authorService.updateAuthor();
				break;
			case 4:
				authorService.deleteAuthor();
				break;
			case 5:
				bookService.listBooks();
				break;
			case 6:
				bookService.addBook();
				break;
			case 7:
				bookService.updateBook();
				break;
			case 8:
				bookService.deleteBook();
				break;
			case 9:
				libraryUserService.listLibraryUsers();
				break;
			case 10:
				libraryUserService.addLibraryUser();
				break;
			case 11:
				libraryUserService.updateLibraryUser();
				break;
			case 12:
				libraryUserService.deleteLibraryUser();
				break;
			case 13:
				rentalsService.listRentals();
				break;
			case 14:
				rentalsService.listActiveRentals();
				break;
			case 15:
				rentalsService.addRental();
				break;
			case 16:
				rentalsService.updateRental();
				break;
			case 17:
				rentalsService.deleteRental();
				break;
			default:
				break;
			}
		}
	}

}
