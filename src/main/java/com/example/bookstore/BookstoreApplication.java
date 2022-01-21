package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository) {
		return(args) -> {
		log.info("saving a couple of books");
		brepository.save(new Book("Vendetta", "Jan Guillou", 2006, "9789164201478", 6.90));
		brepository.save(new Book("Pesten", "Albert Camus", 2021, "9789100187934", 7.50));
		brepository.save(new Book("Idioten", "Fjodor Dostojevskij", 2012, "9789174292312", 5.60));
		
		log.info("loggin all books");
		for (Book book : brepository.findAll() ) {
			log.info(book.toString());
		}
		};
	}

}
