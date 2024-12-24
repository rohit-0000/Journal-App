package com.springbootlearn.journalApp.repository_4;

import com.springbootlearn.journalApp.entity_2.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

//    Optional<JournalEntry> findAllById(ObjectId myId);
}
// mongoReposetry provide a interface which perform CRUD operation