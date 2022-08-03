package com.bilgeadam.library.entity;

public enum AccessLevel
{
	STUDENT(false), ADMIN(true);
	
	private boolean access;
	
	AccessLevel (boolean access)
	{
		this.access = access;
	}

	public boolean canAccess()
	{
		return this.access;
	}
}
