package com.bilgeadam.library.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.bilgeadam.library.dao.AuthorDao;
import com.bilgeadam.library.entity.Author;

public class AuthorService
{
	private Scanner numericIn = new Scanner(System.in);
	private Scanner stringIn = new Scanner(System.in);

	private AuthorDao authorDao = new AuthorDao();

	public void addAuthor()
	{
		authorDao.create(createAuthor());
	}

	public void updateAuthor()
	{
		listAuthors();
		System.out.println();
		System.out.println("Choose which author to update.");
		int selection = numericIn.nextInt();

		authorDao.update(selection, createAuthor());
	}

	public void deleteAuthor()
	{
		listAuthors();
		System.out.println();
		System.out.println("Choose which author to delete.");
		int selection = numericIn.nextInt();

		authorDao.delete(selection);
	}

	public ArrayList<Author> listAuthors()
	{
		ArrayList<Author> authors = authorDao.listAll();
		for (Author author : authors)
		{
			System.out.println(author.getOid() + " - " + author.getFirstName() + " " + author.getLastName());
		}
		return authors;
	}

	private Author createAuthor()
	{
		String firstName;
		String lastName;
		do
		{
			System.out.println("Enter author first name.");
			firstName = stringIn.nextLine();
		}
		while (!(firstName.length() > 0));

		do
		{
			System.out.println("Enter author last name.");
			lastName = stringIn.nextLine();
		}
		while (!(lastName.length() > 0));

		return new Author(firstName, lastName);
	}
}
