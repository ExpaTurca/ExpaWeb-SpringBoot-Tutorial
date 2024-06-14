package com.expasoft.ExpaWebForum.Controller;

import com.expasoft.ExpaWebForum.Entity.DTO.UserDTO;
import com.expasoft.ExpaWebForum.Entity.Template.RegisterForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateForm;
import com.expasoft.ExpaWebForum.Entity.Template.UuidRequestForm;
import com.expasoft.ExpaWebForum.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v3/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "{userId}")
    private ResponseEntity<?> getUser(@PathVariable("userId") UUID id) {
        return userService.getOne(id);
    }

    @PostMapping("new")
    private ResponseEntity<?> saveUser(@RequestBody RegisterForm registerForm) {
        System.out.println("form: " + registerForm);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(registerForm.getUsername());
        userDTO.setEmail(registerForm.getEmail());
        userDTO.setPassword(registerForm.getPassword());
        return userService.save(userDTO);
    }

    @PatchMapping("update/{id}")
    private ResponseEntity<?> updateUser(@RequestParam UUID id, @RequestBody UpdateForm updateForm) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(updateForm.getUsername());
        userDTO.setEmail(updateForm.getEmail());
        return userService.update(id, userDTO);
    }

    @DeleteMapping("delete")
    private int deleteUser(@RequestBody UuidRequestForm uuidRequestForm) {
        return userService.delete(uuidRequestForm);
    }
}
