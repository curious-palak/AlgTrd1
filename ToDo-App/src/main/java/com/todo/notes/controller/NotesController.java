package com.todo.notes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todo.notes.model.Notes;
import com.todo.notes.service.INotesService;
import com.todo.users.model.User;

@RestController
public class NotesController {

	@Autowired
	INotesService notesService;

/*	@RequestMapping(value = "notes", method = RequestMethod.POST)*/
	@PostMapping(value="notes")
	public ResponseEntity<String> notesAdd(@RequestBody Notes notes, HttpServletRequest req) {

		User user= (User) req.getSession().getAttribute("userId");
		notesService.createNote(notes,user);
		return new ResponseEntity<>("Added note successfully..", HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteNotes/{noteId}")
	public ResponseEntity<String> notesDelete(@PathVariable("noteId") int noteId) {

		notesService.deleteNotes(noteId);
		return new ResponseEntity<String>("Note deleted..", HttpStatus.OK);
	}

	@RequestMapping(value = "updateNotes/{noteId}", method=RequestMethod.PUT)
	public ResponseEntity<?> notesUpdate(@PathVariable("noteId") int noteId, @RequestBody Notes notes) {
		//System.out.println(noteId);
		
		notesService.updateNotes(noteId, notes);
		return new ResponseEntity<>("Notes updated successfully.", HttpStatus.OK);

	}
	
	@RequestMapping(value = "getNotes/{noteId}", method = RequestMethod.GET)
	public ResponseEntity<Notes> getUser(@PathVariable("noteId") int noteId) {

		Notes notes=notesService.getNotesById(noteId);
		return new ResponseEntity<Notes>(notes, HttpStatus.OK);
	}
}