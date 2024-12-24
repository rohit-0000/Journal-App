package com.springbootlearn.journalApp.service_3;

import com.springbootlearn.journalApp.entity_2.JournalEntry;
import com.springbootlearn.journalApp.entity_2.User;
import com.springbootlearn.journalApp.repository_4.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class JournalEntryservice {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private Userservice userservice;
    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }
    @Transactional //it will make sure that whole funtion is excecuted or nothing is executed (isolation and atomicity)
    public void saveEntry(JournalEntry journalEntry, String userName)
    {
        try {
            User user = userservice.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            user.setUserName(null); // it is just to check @Transactional
            userservice.saveUser(user);
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry"+e);
        }
    }
    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getById(ObjectId myId)
    {
        return journalEntryRepository.findById(myId);
    }
    public void deleteById(ObjectId myId)
    {
        journalEntryRepository.deleteById(myId);
    }
    public void deleteById(ObjectId myId,String userName)
    {
        User user=userservice.findByUserName(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(myId));
        userservice.saveUser(user);
        journalEntryRepository.deleteById(myId);
    }
}
// service call repository
// controller --> service --> repository