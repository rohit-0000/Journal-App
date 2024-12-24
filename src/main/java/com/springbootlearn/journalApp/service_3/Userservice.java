package com.springbootlearn.journalApp.service_3;

import com.springbootlearn.journalApp.entity_2.User;
import com.springbootlearn.journalApp.repository_4.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class Userservice {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user)
    {
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
}