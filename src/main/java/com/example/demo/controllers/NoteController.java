package com.example.demo.controllers;

import com.example.demo.data.CategoryData;
import com.example.demo.data.CategoryRepository;
import com.example.demo.data.NoteData;
import com.example.demo.data.NoteRepository;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping("/home")
    public String getAllNotesHomePage(Model model) {
        List<NoteData> notes = noteRepo.orderedByDateDesc();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/index";
    }

    @GetMapping("/notes")
    public String getAllNotesUser(Model model) {
        List<NoteData> notes = noteRepo.findAll();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);

        return "/userNotes";
    }

    @GetMapping("/title-asc")
    public String sortNotesByTitleAsc(Model model)
    {
        List<NoteData> notes = noteRepo.orderedByTitleAsc();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/userNotes";
    }

    @GetMapping("/title-desc")
    public String sortNotesByTitleDesc(Model model)
    {
        List<NoteData> notes = noteRepo.orderedByTitleDesc();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/userNotes";
    }


    @GetMapping("/date-asc")
    public String sortNotesByDateAsc(Model model)
    {
        List<NoteData> notes = noteRepo.orderedByDateAsc();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/userNotes";
    }

    @GetMapping("/date-desc")
    public String sortNotesByDateDesc(Model model)
    {
        List<NoteData> notes = noteRepo.orderedByDateDesc();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/userNotes";
    }

    @GetMapping("/category-desc")
    public String sortNotesByCategoryDesc(Model model)
    {
        List<NoteData> notes = noteRepo.orderedByNameDesc();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/userNotes";
    }

    @GetMapping("/category-asc")
    public String sortNotesByCategoryAsc(Model model)
    {
        List<NoteData> notes = noteRepo.orderedByNameAsc();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/userNotes";
    }

    @GetMapping("/popular-asc")
    public String sortByPopularAsc(Model model) {
        List<NoteData> notes = noteRepo.orderedByPopularCategoryAsc();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/index";
    }

    @GetMapping("/dat-desc")
    public String sortByDateDesc(Model model) {
        List<NoteData> notes = noteRepo.orderedByDateDesc();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/index";
    }

    @GetMapping("/dat-asc")
    public String sortByDateAsc(Model model) {
        List<NoteData> notes = noteRepo.orderedByDateAsc();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/index";
    }

    @GetMapping("/popular-desc")
    public String sortByPopularDesc(Model model) {
        List<NoteData> notes = noteRepo.orderedByPopularCategoryDesc();
        model.addAttribute("notes", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/index";
    }

    @GetMapping("/noteDelete/{id}")
    public String deleteNote(@PathVariable("id") int id) {
        noteRepo.deleteById(id);
        return "redirect:/notes";
    }

    @GetMapping("/editNote/{id}")
    public String editNoteForm(@PathVariable("id") int id, Model model) {
        NoteData note = noteRepo.findById(id);
        model.addAttribute("note", note);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "editNote";
    }

    @PostMapping("/editNote/{id}")
    public String editNoteUser(@PathVariable("id") int id, NoteData editedNote) {
        NoteData note = noteRepo.findById(id);
        note.setTitle(editedNote.getTitle());
        note.setNote(editedNote.getNote());
        note.setDate(editedNote.getDate());
        note.setCategoryId(editedNote.getCategoryId());
        noteRepo.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/shareNoteByKey/{key}")
    public String shareNote(@PathVariable("key") String shareKey , @RequestParam("id") int id, Model model) {
        // Przetwarzanie logiki dla udostępnienia notatki
        // Na przykład, pobieranie notatki o określonym ID i przekazanie jej do widoku

        NoteData note = noteRepo.findById(id);
        model.addAttribute("note", note);
        model.addAttribute("shareKey", shareKey);

        return "share-note"; // Zwraca nazwę widoku, który będzie wyświetlany dla udostępnionej notatki
    }

    @GetMapping("/shareNoteById/{id}")
    public String shareNote(@PathVariable("id") int id,  Model model) {
        // Przetwarzanie logiki dla udostępnienia notatki
        // Na przykład, pobieranie notatki o określonym ID i przekazanie jej do widoku

        NoteData note = noteRepo.findById(id);
        model.addAttribute("note", note);

        String shareKey = UUID.randomUUID().toString();
        model.addAttribute("shareKey", shareKey);

        return "/share-note"; // Zwraca nazwę widoku, który będzie wyświetlany dla udostępnionej notatki
    }


    @PostMapping("/category/{id}")
    public String redirectToHome(@PathVariable("id") int id) {
        return "redirect:/home/category/" + id;
    }

    @GetMapping("/home/category/{id}")
    public String showNotesByCategory(@PathVariable("id") int id, Model model) {
        List<NoteData> notes = noteRepo.findAllByCategory(id);
        model.addAttribute("notesCategory", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/index";
    }

    @PostMapping("/userCategory/{id}")
    public String redirectToNotes(@PathVariable("id") int id) {
        return "redirect:/user/category/" + id;
    }

    @GetMapping("/user/category/{id}")
    public String showNotesByCategoryUser(@PathVariable("id") int id, Model model) {
        List<NoteData> notes = noteRepo.findAllByCategory(id);
        model.addAttribute("notesCategory", notes);
        List<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "/userNotes";
    }
}
