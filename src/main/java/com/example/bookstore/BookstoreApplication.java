package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.User;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.UserRepository;
import com.example.bookstore.domain.User;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		
		log.info("saving a couple of categories");
		crepository.save(new Category("Action and Adventure"));
		crepository.save(new Category("Science Fiction"));
		crepository.save(new Category("Literary fiction"));
		
		log.info("saving a couple of users");
		urepository.save(new User("user","$2a$12$dX5EYDIrfbAFkroqRwhdaOi64Ez/h3dni3l9stAFBAHMcEHeN/x9u" ,"USER"));
		urepository.save(new User("admin", "$2a$12$V9rlWIEerl93vJ.rHHrGdOlG.eWmlRNi/OJ9mdw4j5OWpiVZP6m3a", "ADMIN"));
		
		return(args) -> {
		log.info("saving a couple of books");
		brepository.save(new Book("Vendetta", "Jan Guillou", 2006, "9789164201478", 6.90, crepository.findByName("Action and Adventure").get(0)));
		brepository.save(new Book("Pesten", "Albert Camus", 2021, "9789100187934", 7.50, crepository.findByName("Literary fiction").get(0)));
		brepository.save(new Book("Idioten", "Fjodor Dostojevskij", 2012, "9789174292312", 5.60, crepository.findByName("Literary fiction").get(0)));
		
		log.info("loggin all books");
		for (Book book : brepository.findAll() ) {
			log.info(book.toString());
		}
		};
	}

}
