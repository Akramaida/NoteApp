package com.gorbakov.note.controller;

import com.gorbakov.note.dto.NoteDto;
import com.gorbakov.note.exception.ProjectException;
import com.gorbakov.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<String> createNote(@RequestBody NoteDto noteDto){
        try {
            return status(CREATED).body(noteService.createNote(noteDto));
        }catch (RuntimeException e){
            return status(FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/{keyword}")
    @ResponseStatus(OK)
    public List<NoteDto> getNotesByKeyword(@PathVariable String keyword){
        return noteService.getNotesByKeyword(keyword);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<NoteDto> getAllNotes(){
       return noteService.getAllNotes();
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public ResponseEntity<String> editNote(@PathVariable Long id, @RequestBody NoteDto noteDto){
        return status(OK).body(noteService.editNote(id, noteDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public ResponseEntity<String> deleteNote(@PathVariable Long id){
       return status(OK).body(noteService.deleteNote(id));
    }
}
