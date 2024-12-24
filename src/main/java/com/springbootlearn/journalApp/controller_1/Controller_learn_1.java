package com.springbootlearn.journalApp.controller_1;

import com.springbootlearn.journalApp.entity_2.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/learn1")
public class Controller_learn_1 {
    public Map<ObjectId,JournalEntry> journalEntries=new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){ //localhost:8080/journal
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){//localhost:8080/journal
        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }
    @GetMapping("id/{myId}")
     public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {// localhost:8080/journal/id/2
         return journalEntries.get(myId);
     }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable ObjectId myId) {// localhost:8080/journal/id/2
        return journalEntries.remove(myId);
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId myId,@RequestBody JournalEntry myEntry) {// localhost:8080/journal/id/2
        return journalEntries.put(myId,myEntry);
    }
}
// controllers: special type of classes which handles http request in rest api.
//@RequestMapping("..") : it add the mapping on whole class i.e localhost:8080/journal/.....
//Selecting "raw" and "JSON" in the body of a POST request in Postman indicates that the request body will contain data in JSON format, allowing the server to parse and process the incoming data accurately. This ensures that the data is transmitted and received in a structured manner, following the JSON format conventions.
//@RequestBody :- IT'S LIKE SAYING, "HEY SPRING. PLEASE TAKE THE Data FROM THE REQUEST AND TURN IT INTO A JAVA OBJECT THAT CAN USE IN MY CODE."
//@PathVariable :- // localhost:8080/journal/id/2 "here /2 is path variable"
//


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

