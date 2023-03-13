package com.gorbakov.note.exception;

public class ProjectException extends RuntimeException {
    public ProjectException(String eMessage, Exception exception) { super(eMessage, exception); }

    public ProjectException(String eMessage){ super(eMessage);}
}
