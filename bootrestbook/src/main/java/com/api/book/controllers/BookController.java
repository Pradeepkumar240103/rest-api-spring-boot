package com.api.book.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.services.BookService;

@RestController
public class BookController {
    
//	@RequestMapping(value = "/books", method = RequestMethod.GET)
//	@GetMapping("/books")
//	public Book getBooks() {
//		Book book = new Book();
//		book.setId(1);
//		book.setTitle("Java");
//		book.setAuthor("James Gosling");
//		return book;
//	}
	
	@Autowired
	private BookService bookService;
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(){
		
		try {
			List<Book> allBooks = this.bookService.getAllBooks();
			if(allBooks.size()<0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(allBooks);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		
		Book bookById=null;
		try {
			 bookById = this.bookService.getBookById(id);
			if(bookById==null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();			
			}
			return ResponseEntity.of(Optional.of(bookById));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book b = null;
		try {
			b = this.bookService.addBook(book);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBookById(@PathVariable("id") int id) {
		try {
			this.bookService.deleteBookById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
//	
	@PutMapping("/books/{bookid}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bookid") int id) {
		
		try {
			this.bookService.updateBook(book, id);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	

}

