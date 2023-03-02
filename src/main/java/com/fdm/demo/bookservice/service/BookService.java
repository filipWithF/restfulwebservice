package com.fdm.demo.bookservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.fdm.demo.bookservice.model.Book;

@Service
public class BookService {

	private static int bookCount = 4;

	private List<Book> books = Stream
			.of(new Book(1, "Leaning Spring", "Vincent Keeanu", "Spring", LocalDate.now()),
					new Book(2, "Core Java", "John Frings", "Java", LocalDate.now().minusDays(100)),
					new Book(3, "FDM Business", "Simon Shaker", "Management", LocalDate.now().minusDays(700)),
					new Book(4, "Master Consult", "Joseph Villian", "Spring", LocalDate.now().minusDays(100)))
			.collect(Collectors.toList());

	public List<Book> getBooks() {
		return books;
	}

	public Book getBook(Integer id) {
		return books.stream().filter(book -> book.getId().equals(id)).findAny().orElse(null);
	}

	public void createBook(Book book) {
		
		if (book.getId()==null) {
			book.setId(++bookCount);
		}
		books.add(book);
	}

	public Book deleteBook(Integer id) {
		Book deletedBook = getBook(id);
		if (deletedBook != null) books.remove(deletedBook);
		return deletedBook;
	}

}
