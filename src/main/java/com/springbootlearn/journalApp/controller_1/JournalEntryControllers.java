package com.springbootlearn.journalApp.controller_1;

import com.springbootlearn.journalApp.entity_2.JournalEntry;
import com.springbootlearn.journalApp.entity_2.User;
import com.springbootlearn.journalApp.service_3.JournalEntryservice;
import com.springbootlearn.journalApp.service_3.Userservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllers {
    @Autowired
    private JournalEntryservice journalEntryservice;
    @Autowired
    private Userservice userservice;

//    @GetMapping()
//    public ResponseEntity<?> getAll(){
//        if(journalEntryservice.getAll().size()==0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(journalEntryservice.getAll(),HttpStatus.OK);
//    }



//    @GetMapping("{userName}")
//    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
//        if(userservice.findByUserName(userName)==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User user = userservice.findByUserName(userName);
        List<JournalEntry> all=user.getJournalEntries();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PostMapping("{userName}")
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry ){
        try {
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            myEntry.setDate(LocalDateTime.now());
            journalEntryservice.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
     public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {// localhost:8080/journal/id/2
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user =userservice.findByUserName(userName);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());

        if(!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry1 = journalEntryservice.getById(myId);
            if (journalEntry1.isPresent()) {
                return new ResponseEntity<>(journalEntry1.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {// localhost:8080/journal/id/2
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();

        boolean removed=journalEntryservice.deleteById(myId,userName);
        if(removed)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId,@RequestBody JournalEntry myEntry) {// localhost:8080/journal/id/2

//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//        String userName=authentication.getName();

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();

        User user = userservice.findByUserName(userName);
        boolean found = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
        if (found) {
            JournalEntry old = journalEntryservice.getById(myId).orElse(null);
            if (old != null) {
                old.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
                old.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
                journalEntryservice.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

//{
//        "title" : "vineet journal",
//        "content" : "vineet first journal"
//}
