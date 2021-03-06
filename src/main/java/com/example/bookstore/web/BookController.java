package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class BookController {
	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	//Login page
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/booklist")
	public String booklist(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "booklist";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	//Add new book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value ="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories",crepository.findAll());
		return "addbook";
	}
	
	//Save new book (the one that was just added)
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Book book) {
		brepository.save(book);
		return "redirect:booklist";
	}
	
	//Edit book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value ="/edit/{id}", method=RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", brepository.findById(bookId));
		model.addAttribute("categories",crepository.findAll());
		return "editbook";
	}
	
	//Save new book (the one that was just edited)
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/edit/saveedited", method = RequestMethod.POST)
	public String saveEdited(Book book) {
		log.info("Saving edited book");
//		brepository.deleteById(book.getId());
		System.out.println("bookId" + book.getId());
		brepository.save(book);
		return "redirect:../booklist";
	}
	
	@GetMapping("/index")
	public String showBooks() {
		return "index";
	}

}
