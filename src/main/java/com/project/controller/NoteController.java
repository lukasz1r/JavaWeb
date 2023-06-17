package com.project.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.data.CategoryData;
import com.project.data.NoteData;
import com.project.data.SharedData;
import com.project.data.UserData;
import com.project.repository.CategoryRepository;
import com.project.repository.NoteRepository;
import com.project.repository.SharedRepository;
import com.project.repository.UserRepository;

import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class NoteController {

     @Autowired
     NoteRepository noteRepo;

     @Autowired
     CategoryRepository categoryRepo;

     @Autowired
     UserRepository userRepo;

     @Autowired
     SharedRepository sharedRepo;

     @GetMapping("notes/")
     public String getAllUserNotes(Authentication authentication, Model model) {
          UserData user = userRepo.findByEmail(authentication.getName());
          ArrayList<NoteData> notes = noteRepo.findAllUsers(user.getId());
          model.addAttribute("notes", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          for (NoteData n : notes) {
               System.out.println(n.getRemindDate());
          }
          return "userNotes";
     }

     @GetMapping("/noteDelete/{id}")
     public String deleteNote(@PathVariable("id") int id) {
          noteRepo.deleteById(id);
          return "redirect:/notes/";
     }

     @PostMapping("/editNote/{id}")
     public String editNoteUser(@PathVariable("id") int id, NoteData editedNote) {
          NoteData note = noteRepo.findById(id);
          note.setTitle(editedNote.getTitle());
          note.setNote(editedNote.getNote());
          note.setDate(editedNote.getDate());
          note.setCategoryId(editedNote.getCategoryId());
          noteRepo.save(note);
          return "redirect:/notes/";
     }

     @GetMapping("/editNote/{id}")
     public String editNoteForm(@PathVariable("id") int id, Model model) {
          NoteData note = noteRepo.findById(id);
          model.addAttribute("note", note);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "editNote";
     }

     @GetMapping("/title-asc")
     public String sortNotesByTitleAsc(Model model, HttpSession session)
     {
          ArrayList<NoteData> notes = noteRepo.orderedByTitleAsc((long) session.getAttribute("id"));
          model.addAttribute("notes", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/userNotes";
     }

     @GetMapping("/title-desc")
     public String sortNotesByTitleDesc(Model model, HttpSession session)
     {
          ArrayList<NoteData> notes = noteRepo.orderedByTitleDesc((long) session.getAttribute("id"));
          model.addAttribute("notes", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/userNotes";
     }

     @GetMapping("/date-asc")
     public String sortNotesByDateAsc(Model model, HttpSession session)
     {
          ArrayList<NoteData> notes = noteRepo.orderedByDateAsc((long) session.getAttribute("id"));
          model.addAttribute("notes", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/userNotes";
     }

     @GetMapping("/date-desc")
     public String sortNotesByDateDesc(Model model, HttpSession session)
     {
          ArrayList<NoteData> notes = noteRepo.orderedByDateDesc((long) session.getAttribute("id"));
          model.addAttribute("notes", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/userNotes";
     }

     @GetMapping("/category-desc")
     public String sortNotesByCategoryDesc(Model model, HttpSession session)
     {
          ArrayList<NoteData> notes = noteRepo.orderedByNameDesc((long) session.getAttribute("id"));
          model.addAttribute("notes", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/userNotes";
     }

     @GetMapping("/category-asc")
     public String sortNotesByCategoryAsc(Model model, HttpSession session)
     {
          ArrayList<NoteData> notes = noteRepo.orderedByNameAsc((long) session.getAttribute("id"));
          model.addAttribute("notes", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/userNotes";
     }

     @GetMapping("/popular-asc")
     public String sortByPopularAsc(Model model) {
          ArrayList<NoteData> notes = noteRepo.orderedByPopularCategoryAsc();
          model.addAttribute("notes", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/index";
     }

     @GetMapping("/dat-desc")
     public String sortByDateDesc(Model model) {
          ArrayList<NoteData> notes = noteRepo.orderedByDatDesc();
          model.addAttribute("notes", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/index";
     }

     @GetMapping("/dat-asc")
     public String sortByDateAsc(Model model) {
          ArrayList<NoteData> notes = noteRepo.orderedByDatAsc();
          model.addAttribute("notes", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/index";
     }

     @GetMapping("/popular-desc")
     public String sortByPopularDesc(Model model) {
          ArrayList<NoteData> notes = noteRepo.orderedByPopularCategoryDesc();
          model.addAttribute("notes", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/index";
     }

     @PostMapping("/category/{id}")
     public String redirectToHome(@PathVariable("id") int id) {
          return "redirect:/home/category/" + id;
     }

     @GetMapping("/home/category/{id}")
     public String showNotesByCategory(@PathVariable("id") int id, Model model) {
          ArrayList<NoteData> notes = noteRepo.findAllByCategory(id);
          model.addAttribute("notesCategory", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/index";
     }

     @PostMapping("/notesCategory/{id}")
     public String redirectToNotes(@PathVariable("id") int id) {
          return "redirect:/notes/category/" + id;
     }

     @GetMapping("/notes/category/{id}")
     public String showNotesByCategoryUser(@PathVariable("id") int id, Model model, HttpSession session) {
          ArrayList<NoteData> notes = noteRepo.findAllByCategory(id, (long) session.getAttribute("id"));
          model.addAttribute("notesCategory", notes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/userNotes";
     }

     @GetMapping("/share/{id}")
     public String shareNoteToUser(@RequestParam("userEmail") String email, @PathVariable("id") Long noteId) {
          UserData user = userRepo.findByEmail(email);    
          sharedRepo.addSharedNote(user.getId(), noteId);
          return "redirect:/notes/";
     }

     @GetMapping("/sharedNotes/")
     public String sharedNotes(HttpSession session, Model model) {
          ArrayList<NoteData> sharedNotes = noteRepo.getSharedNotes((long) session.getAttribute("id"));
          model.addAttribute("sharedNotes", sharedNotes);
          ArrayList<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/sharedNotes";
     }

     @GetMapping("/sharedDelete/{id}")
     public String sharedDelete(@PathVariable("id") Long id, Model model, HttpSession session) {
          sharedRepo.deleteById(id);
          return "redirect:/sharedNotes/";
     }

     @GetMapping("/shareNoteByKey/{key}")
     public String shareNote(@PathVariable("key") String shareKey , @RequestParam("id") int id, Model model) {
          // Przetwarzanie logiki dla udostępnienia notatki
          // Na przykład, pobieranie notatki o określonym ID i przekazanie jej do widoku

          NoteData note = noteRepo.findById(id);
          model.addAttribute("note", note);
          model.addAttribute("shareKey", shareKey);

          return "shareNote"; // Zwraca nazwę widoku, który będzie wyświetlany dla udostępnionej notatki
     }

     @GetMapping("/sharedNote/{id}")
     public String shareNote(@PathVariable("id") int id,  Model model) {
          // Przetwarzanie logiki dla udostępnienia notatki
          // Na przykład, pobieranie notatki o określonym ID i przekazanie jej do widoku

          NoteData note = noteRepo.findById(id);
          String category = noteRepo.getNoteCategory(id);
          model.addAttribute("category", category);
          model.addAttribute("note", note);

          String shareKey = UUID.randomUUID().toString();
          model.addAttribute("shareKey", shareKey);

          return "shareNote"; // Zwraca nazwę widoku, który będzie wyświetlany dla udostępnionej notatki
     }

}
