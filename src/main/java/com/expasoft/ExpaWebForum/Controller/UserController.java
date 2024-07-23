package com.expasoft.ExpaWebForum.Controller;

import com.expasoft.ExpaWebForum.Entity.DTO.UserDTO;
import com.expasoft.ExpaWebForum.Entity.Template.NewUserForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateEmailForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateUserNameForm;
import com.expasoft.ExpaWebForum.Entity.Template.UuidRequestForm;
import com.expasoft.ExpaWebForum.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/v3/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("all")
    private Set<UserDTO> getAllUser() {
        return userService.getAll();
    }

    @GetMapping(value = "{userId}")
    private ResponseEntity<?> getUser(@PathVariable("userId") UUID id) {
        return ResponseEntity.of(
                userService.getOne(id));
    }

    @PostMapping("new")
    private Optional<UserDTO> saveUser(@RequestBody NewUserForm newUserForm) {
        return userService.save(
                newUserForm);
    }

    @PatchMapping("updateUsername")
    private Optional<UserDTO> updateUsername(@RequestBody UpdateUserNameForm updateUsernameForm) {
        return userService.updateUsername(
                updateUsernameForm);
    }

    @PatchMapping("updateEmail")
    private Optional<UserDTO> updateEmail(@RequestBody UpdateEmailForm updateUsernameForm) {
        return userService.updateEmail(
                updateUsernameForm);
    }

    @DeleteMapping("delete")
    private int deleteUser(@RequestBody UuidRequestForm uuidRequestForm) {
        return userService.delete(
                uuidRequestForm);
    }
}
