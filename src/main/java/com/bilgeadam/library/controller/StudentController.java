package com.bilgeadam.library.controller;

import java.util.Scanner;

import com.bilgeadam.library.entity.LibraryUser;
import com.bilgeadam.library.service.BookService;
import com.bilgeadam.library.service.RentalsService;

public class StudentController
{
	private Scanner numericIn = new Scanner(System.in);

	private RentalsService rentalsService = new RentalsService();
	private BookService bookService = new BookService();

	public void studentMenu(LibraryUser student)
	{
		while (true)
		{
			int selection;
			
			do
			{
				System.out.println();
				System.out.println("1- List all books.");
				System.out.println("2- Rent a book.");
				System.out.println("3- Return a book.");
				System.out.println("4- List current rentals.");
				System.out.println("5- List old rentals.");
				System.out.println("6- Log Out.");
				selection = numericIn.nextInt();
			}
			while (!(1 <= selection && selection <= 6));
			
			if (selection == 6)
			{
				break;
			}
			
			switch (selection)
			{
			case 1:
				bookService.listBooks();
				break;
			case 2:
				rentalsService.rentABook(student);
				break;
			case 3:
				rentalsService.returnABook(student);
				break;
			case 4:
				rentalsService.listCurrentRentals(student);
				break;
			case 5:
				rentalsService.listOldRentals(student);
				break;
			default:
				break;
			}
		}

	}

}
