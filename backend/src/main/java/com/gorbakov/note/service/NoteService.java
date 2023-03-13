package com.gorbakov.note.service;

import com.gorbakov.note.dto.NoteDto;
import com.gorbakov.note.exception.ProjectException;
import com.gorbakov.note.model.Note;
import com.gorbakov.note.repo.NoteRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteService {
    private final NoteRepo noteRepo;
    @Transactional
    public String createNote(NoteDto noteDto) {
        Note note = new Note();
        noteRepo.save(mapDtoToNote(note, noteDto));
        log.info("Overwrite dto to note");
        return "Note created successfully";
    }

    public String editNote(Long id, NoteDto noteDto) {
        var note = noteRepo.findById(id)
                .orElseThrow(() -> new ProjectException("Cannot find Note by id " + id));

        noteRepo.save(mapDtoToNote(note, noteDto));
        log.info("Note with " + id + " edited successfully");
        return "Note with id " + id + " has been edited";

    }

    public String deleteNote(Long id) {
        noteRepo.deleteById(id);
        log.info("Note with " + id + " deleted successfully");
        return "Note with id " + id + " has been deleted";

    }

    public NoteDto getNoteById(Long id) {
       var note = noteRepo.findById(id)
                .orElseThrow(() -> new ProjectException("Cannot find Note by id " + id));
       return mapNoteToDto(note);
    }

    private Note mapDtoToNote(Note note, NoteDto noteDto) {
        //We encode the note and save it in the database in Base64 format
        String stringDescription = noteDto.getDescription();
        String encodeDescription = Base64.getEncoder().encodeToString(stringDescription.getBytes());
        note.setId(noteDto.getId());
        note.setDescription(encodeDescription);
        note.setTitle(noteDto.getTitle());
        return note;
    }

    private NoteDto mapNoteToDto(Note note) {
        NoteDto dto = new NoteDto();
        //We decode the note and return it in original format
        byte[] decodedBytes = Base64.getDecoder().decode(note.getDescription());
        String decodedString = new String(decodedBytes);
        dto.setDescription(decodedString);
        dto.setId(note.getId());
        dto.setTitle(note.getTitle());
        return dto;
    }

    public List<NoteDto> getAllNotes() {
        return noteRepo.findAll().stream().map(this::mapNoteToDto).toList();
    }
    List<NoteDto> noteDtoList = new ArrayList<>();
    public List<NoteDto> getNotesByKeyword(String keyword) {
        if(!Objects.equals(keyword, "")) {
            var notes = noteRepo.findAll();
            return notes.stream().filter(note -> note.getTitle().equals(keyword)).map(this::mapNoteToDto).toList();
        }
        return noteDtoList;
    }
}
