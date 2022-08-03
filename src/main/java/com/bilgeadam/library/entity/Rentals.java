package com.bilgeadam.library.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "rentals")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Rentals
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long oid;
	
	@OneToOne
	@JoinColumn(name = "library_user_oid", referencedColumnName = "oid")
	private LibraryUser libraryUser;
	
	@OneToOne
	@JoinColumn(name = "book_oid", referencedColumnName = "oid")
	private Book book;

	@Temporal(TemporalType.DATE)
	@Column(name = "borrow_date")
	private Date borrowDate;

	@Column(name = "borrow_duration_days")
	private int borrowDuration;

	@Column(name = "book_returned")
	private boolean returned;
	
	public Rentals(LibraryUser libraryUser, Book book, Date borrowDate, int borrowDuration)
	{
		super();
		this.libraryUser = libraryUser;
		this.book = book;
		this.borrowDate = borrowDate;
		this.borrowDuration = borrowDuration;
		this.returned = false;
	}
}
