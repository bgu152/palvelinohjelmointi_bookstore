package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;


@Controller
public class BookController {
	
	@Autowired
	private BookRepository brepository;
	
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
	@RequestMapping(value ="/add")
	public String addStudent(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	//Save new book
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Book book) {
		brepository.save(book);
		return "redirect:booklist";		
	}
	
	@GetMapping("/index")
	public String showBooks() {
		return "index";
	}

}
