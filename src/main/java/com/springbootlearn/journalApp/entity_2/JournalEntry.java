package com.springbootlearn.journalApp.entity_2;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
//@Getter
//@Setter
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;

//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public ObjectId getId(){
//        return id;
//    }
//    public String getTitle(){
//        return title;
//    }
//    public String getContent(){
//        return content;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public void setId(ObjectId id){
//        this.id=id;
//    }
}
//@Document :- it indicate that a Java class is a MongoDB document , framework for interacting with MongoDB databases using the Spring ecosystem.
//@Id :- indicate the primary key of a document in MongoDB
// if id is same then it will update the data
