package com.bilgeadam.library.service;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.bilgeadam.library.dao.AuthorDao;
import com.bilgeadam.library.dao.BookDao;
import com.bilgeadam.library.entity.Author;
import com.bilgeadam.library.entity.Book;

public class BookService
{
	private Scanner numericIn = new Scanner(System.in);
	private Scanner stringIn = new Scanner(System.in);

	private BookDao bookDao = new BookDao();

	public void addBook()
	{
		bookDao.create(createBook());
	}

	public void updateBook()
	{
		listBooks();
		System.out.println();
		System.out.println("Choose which book to update.");
		int selection = numericIn.nextInt();

		bookDao.update(selection, createBook());
	}

	public void deleteBook()
	{
		listBooks();
		System.out.println();
		System.out.println("Choose which book to delete.");
		int selection = numericIn.nextInt();

		bookDao.delete(selection);
	}

	public ArrayList<Book> listBooks()
	{
		ArrayList<Book> books = bookDao.listAll();
		for (Book book : books)
		{
			System.out.println(book.getOid() + "\t" + book.getTitle() + " --- " + book.getAuthor().getFirstName() + " "
					+ book.getAuthor().getLastName() + " - Book is rented:" + book.isAwayForRental());
		}
		return books;
	}

	private Book createBook()
	{
		String bookTitle;
		do
		{
			System.out.println("Enter the book title.");
			bookTitle = stringIn.nextLine();
		}
		while (!(bookTitle.length() > 0));

		Book book = new Book(bookTitle);
		book.setAuthor(giveBookAuthor());

		return book;
	}

	private Author giveBookAuthor()
	{
		AuthorDao authorDao = new AuthorDao();
		ArrayList<Author> authors = authorDao.listAll();
		int selection;
		int tempSelection;
		do
		{
			System.out.println("Choose which author to add to this book. (0 to add author)");
			for (Author author : authors)
			{
				System.out.println(author.getOid() + " - " + author.getFirstName() + " " + author.getLastName());	
			}
			tempSelection = numericIn.nextInt();
			
			if (tempSelection == 0)
			{
				AuthorService as = new AuthorService();
				as.addAuthor();
				authors = authorDao.listAll();
			}
		}
		while (!(0 < tempSelection));
		selection = tempSelection;
		
		return authors.stream().filter(a -> a.getOid() == selection).collect(Collectors.toList()).get(0);
	}
}
