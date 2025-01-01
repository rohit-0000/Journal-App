package com.springbootlearn.journalApp.service_3;

import com.springbootlearn.journalApp.entity_2.JournalEntry;
import com.springbootlearn.journalApp.entity_2.User;
import com.springbootlearn.journalApp.repository_4.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class Userservice {
    @Autowired
    private UserRepository userRepository;
    private static  final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();



    public void saveUser(User user)
    {
         userRepository.save(user);
    }
    public void saveNewUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    public List<User> getAll()
    {
        return userRepository.findAll();
    }
    public Optional<User> getById(ObjectId id)
    {
        return userRepository.findById(id);
    }
    public void deleteById(ObjectId id)
    {
        userRepository.deleteById(id);
    }
    public User findByUserName(String userName)
    {
        return userRepository.findByUserName(userName);
    }

    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }
}