package com.gorbakov.note.dto;

import lombok.Data;

@Data
public class NoteDto {
    private Long id;
    private String title;
    private String description;
}
