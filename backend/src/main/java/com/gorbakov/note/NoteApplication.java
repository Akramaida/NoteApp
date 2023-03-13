package com.gorbakov.note;

import com.gorbakov.note.model.Note;
import com.gorbakov.note.repo.NoteRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Base64;

@SpringBootApplication
@Slf4j
public class NoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteApplication.class, args);
	}

	//First note when starting the application
	@Bean
	public CommandLineRunner init (NoteRepo repo) {
		return args -> {
			if(repo.findById(1L).isEmpty()) {
				//We encode the note and save it in the database in Base64 format
				String stringDescription = "Нажмите Login в правом верхнем углу чтобы войти. Подойдет несуществующий Email.";
				String stringTitle = "First Note. Инструкция:";
				String encodeDescription = Base64.getEncoder().encodeToString(stringDescription.getBytes());
				repo.save(new Note(0L, stringTitle, encodeDescription));
				log.info("First note has been created");
			}
		};
	}

}
