//package com.company.controller;
//
//import com.company.dto.ResponseDTO;
//import com.company.dto.UserDTO;
//import com.company.entity.User;
//import com.company.service.inter.UserServiceInter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class UserSkillRestController {
//
//    @Autowired
////    @Qualifier("userDao1")
//    private UserSkillServiceInter userService;
//
//    @GetMapping("/users/{id}/skills")
//    public ResponseEntity<ResponseDTO> getUsers(
//            @PathVariable(name="id") Integer id;
//    ){
//       //hemin user id-nin skillerini goturub return ededeyik
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity<ResponseDTO> getUser(@PathVariable("id") int id){
//        User user = userService.getById(id);
//        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user)));
//    }
//
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id){
//        User user = userService.getById(id);
//        userService.removeUser(id);
//
//        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user), "Successfully Deleted"));
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDto){
//        User user = new User();
//        user.setName(userDto.getName());
//        user.setSurname(userDto.getSurname());
//        user.setPassword(userDto.getPassword());
//        userService.addUser(user);
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setSurname(user.getSurname());
//        return ResponseEntity.ok(ResponseDTO.of(userDTO, "Successfully added"));
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
