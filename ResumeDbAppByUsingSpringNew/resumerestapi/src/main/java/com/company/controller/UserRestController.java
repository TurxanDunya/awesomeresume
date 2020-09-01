package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.entity.User;
import com.company.dto.UserDTO;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
//    @Qualifier("userDao1")
    private UserServiceInter userService;

    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> getUsers(
            //these are filters. You can search by name, surname or age. Whatever you want!
            @RequestParam(name="name", required = false) String name,
            @RequestParam(name="surname", required = false) String surname,
            @RequestParam(name="age", required = false) Integer age
    ){
        List<User> users = userService.getAll(name, surname, age);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i<users.size(); i++){
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }
//        return ResponseEntity.status(HttpStatus.OK).body("ewfewfwewec");
        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
    }

    @GetMapping("/foo")
    public ResponseEntity<ResponseDTO> foo(
            @RequestParam(name="name", required = false) String name,
            @RequestParam(name="surname", required = false) String surname,
            @RequestParam(name="age", required = false) Integer age
    ){
        List<User> users = userService.getAll(name, surname, age);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i<users.size(); i++){
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }
//        return ResponseEntity.status(HttpStatus.OK).body("ewfewfwewec");
        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable("id") int id){
        User user = userService.getById(id);
        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user)));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id){
        User user = userService.getById(id);
        userService.removeUser(id);

        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user), "Successfully Deleted"));
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPassword(userDto.getPassword());
        userService.addUser(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        return ResponseEntity.ok(ResponseDTO.of(userDTO, "Successfully added"));
    }
}



















