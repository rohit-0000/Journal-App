package com.springbootlearn.journalApp.controller_1;

import com.springbootlearn.journalApp.entity_2.JournalEntry;
import com.springbootlearn.journalApp.entity_2.User;
import com.springbootlearn.journalApp.service_3.JournalEntryservice;
import com.springbootlearn.journalApp.service_3.Userservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllers {
    @Autowired
    private JournalEntryservice journalEntryservice;
    @Autowired
    private Userservice userservice;


    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userservice.findByUserName(userName);
        List<JournalEntry> all=user.getJournalEntries();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry , @PathVariable String userName){
        try {
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
        Optional<JournalEntry> journalEntry1 = journalEntryservice.getById(myId);
        if(journalEntry1.isPresent())
        {
            return new ResponseEntity<>(journalEntry1.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName) {// localhost:8080/journal/id/2
        journalEntryservice.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId,@RequestBody JournalEntry myEntry,@PathVariable String userName) {// localhost:8080/journal/id/2
        JournalEntry old=journalEntryservice.getById(myId).orElse(null);
        if(old!=null)
        {
            old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("")? myEntry.getContent() : old.getContent());
            old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("")? myEntry.getTitle(): old.getTitle());
            journalEntryservice.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

//{
//        "title" : "vineet journal",
//        "content" : "vineet first journal"
//}
