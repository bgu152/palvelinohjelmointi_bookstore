package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("/index")
	public String showBooks() {
		return "index";		
	}

}
