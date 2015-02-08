package com.example.mesnotes;

public class NoteEntity {
	public int Id;
	public String Title, Note;

	public NoteEntity(int Id, String Title, String Note) {
		this.Id = Id;
		this.Title=Title;
		this.Note = Note;
	}
}
