package com.example.bookstore;
//Ingen aning hur det här borde funka

import static org.assertj.core.api.Assertions.assertThat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import com.example.bookstore.web.BookController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SmokeTesting {
	@Autowired
	private BookController bcontroller;
	
	@Test
	public void contextLoads() {
		assertThat(bcontroller).isNotNull();
	}
}
