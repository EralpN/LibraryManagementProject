package com.bilgeadam.library.service;

import java.util.Scanner;

import com.bilgeadam.library.dao.UserDetailDao;
import com.bilgeadam.library.entity.UserDetail;

public class UserDetailService
{
	private Scanner stringIn = new Scanner(System.in);
	
	private UserDetailDao userDetailDao = new UserDetailDao();
	
	
	protected void addUserDetail(UserDetail userDetail)
	{
		userDetailDao.create(userDetail);
	}
	
	protected void updateUserDetail(long oid)
	{
		userDetailDao.update(oid, createUserDetail());
	}
	
	protected void deleteUserDetail(long oid)
	{
		userDetailDao.delete(oid);
	}
	
	protected UserDetail createUserDetail()
	{
		String firstName;
		String lastName;
		do
		{
			System.out.println("Enter user first name.");
			firstName = stringIn.nextLine();
		}
		while(!(firstName.length() > 0));
		
		do
		{
			System.out.println("Enter user last name.");
			lastName = stringIn.nextLine();
		}
		while(!(lastName.length() > 0));
		
		return new UserDetail(firstName, lastName);
	}
}
