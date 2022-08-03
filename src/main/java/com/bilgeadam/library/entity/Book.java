package com.bilgeadam.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "books")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long oid;
	
	@ManyToOne
	@JoinColumn(name = "author_id", referencedColumnName = "oid")
	private Author author;
	
	@Column(name = "book_title")
	private String title;

	@Column(name = "rented")
	boolean awayForRental;
	
	public Book(String title)
	{
		super();
		this.title = title;
		this.awayForRental = false;
	}
}
