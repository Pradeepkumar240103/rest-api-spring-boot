package com.api.book.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.dao.BookRepository;
import com.api.book.entities.Author;
import com.api.book.entities.Book;

@Component
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		List<Book> books=(List<Book>)bookRepository.findAll();
		return books;
	}
	
	public Book getBookById(int id) {
		Book byId = bookRepository.findById(id);
		return byId;
	}
	
	public Book addBook(Book book) {
		bookRepository.save(book);
		return book;
	}
	
	public void deleteBookById(int id) {
		bookRepository.deleteById(id);
	}
	
//	public void updateBook(Book book,int id) {
//		book.setId(id);
//		bookRepository.save(book);
//	}
	public Book updateBook(Book updatedBook, int id) {
	    Book existingBook = bookRepository.findById(id);

	  
	    existingBook.setTitle(updatedBook.getTitle());

	  
	    Author updatedAuthor = updatedBook.getAuthor();
	    Author author=null;
	    if (updatedAuthor != null) {
	        author = existingBook.getAuthor();
	    }
	        if (author == null) {
	            author = new Author();
	        }
	        author.setFirstName(updatedAuthor.getFirstName());
	        author.setLastName(updatedAuthor.getLastName());
	        author.setLanguage(updatedAuthor.getLanguage());
	        existingBook.setAuthor(author);

	    return bookRepository.save(existingBook);  
	}


}

