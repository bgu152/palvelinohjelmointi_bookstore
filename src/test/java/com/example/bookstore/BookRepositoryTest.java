package com.example.bookstore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private UserRepository urepository;
	
	@Test
	public void findBook() {
		List<Book> books = brepository.findByTitle("Idioten");
		Book book = books.get(0);
		assertThat(book).isNotNull();
	}
	
	
	@Test
	public void saveNewBook() {
		Book book = new Book("For whom the Bell Tolls", "Hemingway", 1930, "92834720938", 33.20,crepository.findByName("Literary fiction").get(0));
		brepository.save(book);
		List<Book> books = brepository.findByTitle("For whom the Bell Tolls");
		assertThat(brepository.findById(book.getId())).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		List<Book> books = brepository.findByTitle("Idioten");
		Book book = books.get(0);
		brepository.delete(book);
		List<Book> newBooks = brepository.findByTitle("Idioten");
		assertThat(newBooks).hasSize(0);		
	}
	
	@Test
	public void findCategory() {
		List<Category> categories= crepository.findByName("Science Fiction");
		Category category = categories.get(0);
		assertThat(category).isNotNull();
	}
	@Test
	public void saveNewCategory() {
		Category category = new Category("Young Adult Fiction");
		crepository.save(category);
		Category newCategory= crepository.findByName("Young Adult Fiction").get(0);
		assertThat(newCategory).isNotNull();		
	}
	@Test
	public void deleteCategory() {
		List<Category> categories = crepository.findByName("Science Fiction");
		Category category = categories.get(0);
		crepository.delete(category);
		List<Category> newCategories = crepository.findByName("Science Fiction");
		assertThat(newCategories).hasSize(0);
	}
	@Test
	public void findUser() {
		User basicUser = urepository.findByUsername("user");
		assertThat(basicUser).isNotNull();
	}
	
	@Test
	public void saveNewUser() {
		User newUser = new User("erik", "$2a$12$9pq2zwULAzKOUkr8tePEf.1H81Keas3kTd71tx2CIx0NNE0Avn7YC", "ADMIN");
		urepository.save(newUser);
		assertThat(urepository.findByUsername("erik")).isNotNull();
	}
	
	@Test
	public void deleteNewUser() {
		User newUser = new User("erkki", "$2a$12$9pq2zwULAzKOUkr8tePEf.1H81Keas3kTd71tx2CIx0NNE0Avn7YC", "ADMIN");
		urepository.save(newUser);
		urepository.delete(newUser);
		assertThat(urepository.findByUsername("erkki")).isNull();
		
	}
	
}
