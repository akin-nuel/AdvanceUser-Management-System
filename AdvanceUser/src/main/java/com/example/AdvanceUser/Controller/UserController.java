package com.example.AdvanceUser.Controller;

import com.example.AdvanceUser.Model.AdvanceUser;
import com.example.AdvanceUser.Model.AdvancedUserResource;
import com.example.AdvanceUser.Service.UserService;
import com.jayway.jsonpath.Criteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v3")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<AdvanceUser> saveUser(@RequestBody @Valid AdvanceUser advanceUser){
        return new ResponseEntity<>(userService.saveUser(advanceUser), HttpStatus.CREATED);
    }

    @PostMapping("/all-users")
    public Map<String, Boolean> saveAllUsers(@Valid @RequestBody List<AdvanceUser> advanceUsers){
        return userService.SaveAllUser(advanceUsers);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdvanceUser>> getAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id ){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("users/{id}")
    public AdvanceUser getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, AdvanceUser advanceUser){
        return userService.updateUser(id, advanceUser);
    }

//    @Transactional
//    public String boundTogether(UserController user){
//        user.saveUser(new AdvanceUser("shola kola","sh@gmail.com", "male", "08055688901" ));
//        user.getAllUsers();
//
//    }

    @GetMapping("user/{id}")
    public ResponseEntity<AdvancedUserResource> getUserResource( @PathVariable long id ){
        AdvancedUserResource advancedUserResource = new AdvancedUserResource();
        AdvanceUser user = getUserById(id);
        advancedUserResource.setAdvancedUser(user);
        Link idLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(UserController.class).getUserById(id)).withRel("idLink");
        Link updateLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(UserController.class).updateUser(id, user)).withRel("updateLink");
        Link deleteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).deleteUser(id)).withRel("deleteLink");
        advancedUserResource.add(idLink, updateLink, deleteLink);
        return new ResponseEntity<>(advancedUserResource, HttpStatus.OK);

    }


}
