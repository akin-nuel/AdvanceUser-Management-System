package com.example.AdvanceUser.Service;

import com.example.AdvanceUser.Exception.UserNotFoundException;
import com.example.AdvanceUser.Model.AdvanceUser;
import com.example.AdvanceUser.Repository.AdvanceUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;

@AllArgsConstructor
@Service
public class UserService {


    @Autowired
    private AdvanceUserRepository userRepository;
    @CacheEvict(value = "allUsers", allEntries = true)
    public AdvanceUser saveUser(AdvanceUser advanceUser){

        return userRepository.save(advanceUser);
    }



    public Map<String, Boolean> SaveAllUser(List<AdvanceUser> users){
        Map<String, Boolean> response = new HashMap<>();
        for(AdvanceUser user: users){
            response.put(user.getFullName() + "user added successfully" , true);
        }
        return response;
    }

    @Cacheable("allUsers")
    public List<AdvanceUser> findAllUsers(){
        return userRepository.findAll();
    }

    @Cacheable(value = "singleUsers", key = "#id")
    public AdvanceUser getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = {"singleUsers", "allUsers"}, allEntries = true)
    public String deleteUser(Long id){
//        userRepository.delete(advanceUser);
//        return "Successfully Deleted";
        AdvanceUser user = getUserById(id);
        userRepository.delete(user);
        return "Successfully deleted";
    }

//    @CacheEvict(value = {"singleUsers", "allUsers"}, key = "#id")
    public String updateUser(Long id, AdvanceUser advanceUser){
        AdvanceUser toUpdate = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found"));

        toUpdate.setFullName(advanceUser.getFullName());
        toUpdate.setEmail(advanceUser.getEmail());
        toUpdate.setGender(advanceUser.getGender());
        toUpdate.setPhoneNumber(advanceUser.getPhoneNumber());
        userRepository.save(toUpdate);

        return "Update Successful";
    }


    public List<String> userList(String fullName){
        List<String> user = userRepository.findAdvanceUserByFullName(fullName);

        List<String> compare = (List<String>) user.stream().sorted((fullName1, fullName2)-> fullName1.compareToIgnoreCase(fullName2));

        return compare;
    }

    public List<String> userListDescend(String fullName){
        List<String> user = userRepository.findAdvanceUserByFullName(fullName);

        List<String> compare = (List<String>) user.stream().sorted((fullName1, fullName2) -> fullName2.compareToIgnoreCase(fullName1));

        return compare;
    }


//    public List<String> userListSex(){
//        List<String> users = userRepository.findAllByGender();
//
//        Predicate predicate = users.stream().filter();
//    }

//    public List<String> UserComparatorAscending(AdvanceUser advanceUser+369){
//        List<AdvanceUser> user = userRepository.findAll()
//        .stream()
//        .sorted((o1, o2)-> o2
//        );
//
//
//    }

}
