package com.springbootlearn.journalApp.controller_1;

import com.springbootlearn.journalApp.entity_2.JournalEntry;
import com.springbootlearn.journalApp.service_3.JournalEntryservice;
import com.springbootlearn.journalApp.service_3.Userservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/learn2")
public class Controller_learn_2 {
    @Autowired
    private JournalEntryservice journalEntryservice;
//    private Userservice.JournalEntryservice journalEntryservice;

    @GetMapping
    public ResponseEntity<?> getAll(){ //localhost:8080/journalNEW
//        return journalEntryservice.getAll();
        List<JournalEntry> all=journalEntryservice.getAll();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){//localhost:8080/journalNEW
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryservice.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
     public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {// localhost:8080/journal/id/2
//        return journalEntryservice.getById(myId).orElse(null);
        Optional<JournalEntry> journalEntry1 = journalEntryservice.getById(myId);
        if(journalEntry1.isPresent())
        {
            return new ResponseEntity<>(journalEntry1.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {// localhost:8080/journal/id/2
        journalEntryservice.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        return true;
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId,@RequestBody JournalEntry myEntry) {// localhost:8080/journal/id/2
        JournalEntry old=journalEntryservice.getById(myId).orElse(null);
        if(old!=null)
        {
            old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("")? myEntry.getContent() : old.getContent());
            old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("")? myEntry.getTitle(): old.getTitle());
            journalEntryservice.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

//        return  old;
    }



}

//controller --> service --> repository
// controllers will only create endpoints. and call service (whole work will done under service)
// controllers: special type of classes which handles http request in rest api.
//@RequestMapping("..") : it add the mapping on whole class i.e localhost:8080/journal/.....
//Selecting "raw" and "JSON" in the body of a POST request in Postman indicates that the request body will contain data in JSON format, allowing the server to parse and process the incoming data accurately. This ensures that the data is transmitted and received in a structured manner, following the JSON format conventions.
//@RequestBody :- IT'S LIKE SAYING, "HEY SPRING. PLEASE TAKE THE Data FROM THE REQUEST AND TURN IT INTO A JAVA OBJECT THAT CAN USE IN MY CODE."
//@PathVariable :- // localhost:8080/journal/id/2 "here /2 is path variable"



// post
//[
//    {
//        "id": 1,
//        "title": "Morning",
//        "content": "Morning was Good"
//    },
//    {
//        "id": 2,
//        "title": "Sad",
//        "content": "I am SAD 😊"
//    },
//    {
//        "id": 3,
//        "title": "Name",
//        "content": "I am ROHIT 😊"
//    }
//]

//       GLAM PALOOZA