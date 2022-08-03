package com.bilgeadam.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "library_user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LibraryUser
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long oid;
	
	@Column(name = "access_level")
	@Enumerated(EnumType.STRING)
	private AccessLevel accessLevel;
	
	@OneToOne
	@JoinColumn(name = "user_detail", referencedColumnName = "oid")
	private UserDetail userDetail;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	public LibraryUser(AccessLevel accessLevel, String username, String password)
	{
		super();
		this.accessLevel = accessLevel;
		this.username = username;
		this.password = password;
	}
}
