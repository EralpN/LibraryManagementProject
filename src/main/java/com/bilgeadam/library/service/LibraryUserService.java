package com.bilgeadam.library.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.bilgeadam.library.dao.LibraryUserDao;
import com.bilgeadam.library.entity.AccessLevel;
import com.bilgeadam.library.entity.LibraryUser;
import com.bilgeadam.library.entity.UserDetail;

public class LibraryUserService
{
	private Scanner numericIn = new Scanner(System.in);
	private Scanner stringIn = new Scanner(System.in);

	private LibraryUserDao libraryUserDao = new LibraryUserDao();
	private UserDetailService userDetailService = new UserDetailService();

	public void addLibraryUser()
	{
		LibraryUser libraryUser = createLibraryUserDao();
		UserDetail userDetail = userDetailService.createUserDetail();
		libraryUser.setUserDetail(userDetail);
		userDetailService.addUserDetail(libraryUser.getUserDetail());
		libraryUserDao.create(libraryUser);
	}

	public void updateLibraryUser()
	{
		listLibraryUsers();
		System.out.println();
		System.out.println("Choose which user to update.");
		int selection = numericIn.nextInt();

		LibraryUser libUser = createLibraryUserDao();

		int option;
		do
		{
			System.out.println();
			System.out.println("Will you change user details?");
			System.out.println("1- Yes");
			System.out.println("2- No");
			option = numericIn.nextInt();
		}
		while (!(option == 1 && option == 2));

		if (option == 1)
		{
			userDetailService.updateUserDetail(libUser.getUserDetail().getOid());
		}

		libraryUserDao.update(selection, libUser);
	}

	public void deleteLibraryUser()
	{
		ArrayList<LibraryUser> libraryUsers = listLibraryUsers();
		System.out.println();
		System.out.println("Choose which user to delete.");
		int selection = numericIn.nextInt();

		if (libraryUsers.stream().filter(lU -> lU.getOid() == selection).collect(Collectors.toList()).size() > 0)
		{
			long userDetailOid = libraryUsers.stream().filter(lU -> lU.getOid() == selection)
					.collect(Collectors.toList()).get(0).getUserDetail().getOid();
			libraryUserDao.delete(selection);
			userDetailService.deleteUserDetail(userDetailOid);
		}
	}

	public ArrayList<LibraryUser> listLibraryUsers()
	{
		ArrayList<LibraryUser> libraryUsers = libraryUserDao.listAll();
		for (LibraryUser libraryUser : libraryUsers)
		{
			System.out.println(libraryUser.getOid() + " - " + libraryUser.getUserDetail().getFirstName() + " "
					+ libraryUser.getUserDetail().getLastName() + " --- " + libraryUser.getAccessLevel() + " "
					+ libraryUser.getUsername() + " " + libraryUser.getPassword().replaceAll(".", "*"));
		}
		return libraryUsers;
	}

	public HashMap<String, LibraryUser> userMap()
	{
		ArrayList<LibraryUser> libraryUsers = libraryUserDao.listAll();
		HashMap<String, LibraryUser> usersMap = new HashMap<>();
		for (LibraryUser libraryUser : libraryUsers)
		{
			usersMap.putIfAbsent(libraryUser.getUsername(), libraryUser);
		}
		return usersMap;
	}

	private LibraryUser createLibraryUserDao()
	{
		String username;
		String password;
		int tempAccessLevel;
		AccessLevel accessLevel = AccessLevel.STUDENT;

		do
		{
			System.out.println("Enter username.");
			username = stringIn.nextLine();
		}
		while (!(username.length() > 0));

		do
		{
			System.out.println("Enter password.");
			password = stringIn.nextLine();
			System.out.println("Enter password again");
			String tempPassword = stringIn.nextLine();
			if (!password.equals(tempPassword))
			{
				System.out.println("Passwords do not match.");
				password = "";
			}

		}
		while (!(password.length() > 0));

		do
		{
			System.out.println("Enter user access level;");
			System.out.println("1 - Student");
			System.out.println("2 - Admin");
			tempAccessLevel = numericIn.nextInt();
		}
		while (!(tempAccessLevel == 1 || tempAccessLevel == 2));

		if (tempAccessLevel == 2)
		{
			accessLevel = AccessLevel.ADMIN;
		}

		return new LibraryUser(accessLevel, username, password);
	}

}
