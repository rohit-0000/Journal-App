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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userControllers {
    @Autowired
    private Userservice userservice;

    @GetMapping
    public List<User> getAllUser()
    {
        return userservice.getAll();
    }

    @PostMapping
    public void CreateNewUser(@RequestBody User user)
    {
        userservice.saveUser(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user ,@PathVariable String userName)
    {
        User UserInDb = userservice.findByUserName(userName);
        if(UserInDb !=null)
        {
            UserInDb.setUserName(user.getUserName());
            UserInDb.setPassword(user.getPassword());
            userservice.saveUser(UserInDb);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

//{
//        "userName":"vineet01",
//        "password":"Vineet2004"
//}