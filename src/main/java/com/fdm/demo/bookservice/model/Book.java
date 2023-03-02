package com.fdm.demo.bookservice.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class Book {

	private Integer id;
	@NotNull(message = "Title cannot be null")
	private String title;

	@Size(min = 2, message = "Author should have at least two characters")
	private String author;
	private String topic;
	@Past
	private LocalDate publishedDate;

	public Book() {
	}

	public Book(Integer id, String title, String author, String topic, LocalDate publishedDate) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.topic = topic;
		this.publishedDate = publishedDate;
	}

	/*
	 * Getters and Setters
	 */

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", topic=" + topic + ", publishedDate="
				+ publishedDate + "]";
	}
}
