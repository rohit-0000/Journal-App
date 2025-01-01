package com.springbootlearn.journalApp.controller_1;

import com.springbootlearn.journalApp.entity_2.User;
import com.springbootlearn.journalApp.service_3.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class userControllers {
    @Autowired
    private Userservice userservice;

//    @GetMapping
//    public List<User> getAllUser()
//    {
//        return userservice.getAll();
//    } //i will create this in admin

//    @PostMapping
//    public void CreateNewUser(@RequestBody User user)
//    {
////        userservice.saveUser(user);
//        userservice.saveNewUser(user); //here password will be encrypted
//    }

//    @PutMapping("/{userName}")
//    public ResponseEntity<?> updateUser(@RequestBody User user ,@PathVariable String userName) //we will not pass username through path variable

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User UserInDb = userservice.findByUserName(userName);
//        if(UserInDb !=null) //user will come after authentication so can not be null
//        {
            UserInDb.setUserName(user.getUserName());
            UserInDb.setPassword(user.getPassword());
            userservice.saveNewUser(UserInDb);
//        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteByUserName(@PathVariable String userName )
    {
//        if(userservice.findByUserName(userName)!=null)
//        {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        userservice.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

//{
//        "userName":"vineet01",
//        "password":"Vineet2004"
//}