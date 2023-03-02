package com.fdm.demo.bookservice.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdm.demo.bookservice.exception.BookNotFoundException;
import com.fdm.demo.bookservice.model.Book;
import com.fdm.demo.bookservice.service.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public List<Book> getBooks() {
		return bookService.getBooks();
	}
	
	
	@GetMapping("/{id}")
	public Book getBook(@PathVariable Integer id) {

		Book book = bookService.getBook(id);
		if (book == null)
			throw new BookNotFoundException("id-" + id);
	
		return book;

	}

	// ResponseEntity<Object> @Valid 
	@PostMapping
	public  ResponseEntity<Object>  createBook(@RequestBody @Valid  Book book) {
		bookService.createBook(book);
		// return the URI of the created resource to the client, because the
		// client does not know the id
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId())
			.toUri();
		// .created sets the HTTP Status to 201 Created (this is what we want as
		// we are creating a resource)
		return ResponseEntity.created(location).build();
	}
	

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Integer id) {
		Book deletedBook = bookService.deleteBook(id);

		if (deletedBook == null)
			throw new BookNotFoundException("id-" + id);
	}

}
