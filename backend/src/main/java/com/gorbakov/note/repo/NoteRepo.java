package com.gorbakov.note.repo;

import com.gorbakov.note.dto.NoteDto;
import com.gorbakov.note.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {
}
