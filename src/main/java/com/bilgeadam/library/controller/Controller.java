package com.bilgeadam.library.controller;

import java.util.HashMap;
import java.util.Scanner;

import com.bilgeadam.library.entity.LibraryUser;
import com.bilgeadam.library.hibernateutil.HibernateSession;
import com.bilgeadam.library.service.LibraryUserService;

public class Controller
{
	private Scanner stringIn = new Scanner(System.in);
	private Scanner numericIn = new Scanner(System.in);

	private LibraryUserService libService = new LibraryUserService();
	private String username;
	
	public static void startLibraryManager()
	{
		Controller con = new Controller();
		while (true)
		{
			con.menu();
		}
	}
	
	private void menu()
	{
		logIn();
		
		if (isAdmin())
		{
			AdminController acon = new AdminController();
			acon.adminMenu();
		}
		else
		{
			StudentController scon = new StudentController();
			scon.studentMenu(userMap().get(username));
		}
	}

	private HashMap<String, LibraryUser> userMap()
	{
		return libService.userMap();
	}
	
	private boolean isAdmin()
	{
		return userMap().get(username).getAccessLevel().canAccess();
	}
	
	private void logIn()
	{
		boolean loggedIn = false;
		do
		{
			loggedIn = checkInfo();
		}
		while (!loggedIn);
	}
	
	private boolean checkInfo()
	{
		welcome();
		
		System.out.println("Enter your username.");
		username = stringIn.nextLine();

		if (!userMap().containsKey(username))
		{
			System.out.println("User not found.");
			return false;
		}

		String password;
		do
		{
			System.out.println("Please enter your password correctly.(-1 to exit)");
			password = stringIn.nextLine();
			if (password.equals("-1"))
			{
				return false;
			}
		}
		while (!password.equals(userMap().get(username).getPassword()));

		return true;
	}
	
	private void welcome()
	{
		HibernateSession.getSessionfactory();
		
		System.out.println("Welcome to library manager.");
		
		int selection;
		do
		{
			System.out.println("1- Log In.");
			System.out.println("2- Exit.");
			selection = numericIn.nextInt();
		}
		while(!(selection == 1 || selection == 2));
		
		if (selection == 2)
		{
			System.out.println("Thanks for using library manager.");
			System.out.println("Bye bye.");
			System.exit(0);
		}
	}
}
