package com.expasoft.ExpaWebForum.Controller;

import com.expasoft.ExpaWebForum.Entity.Template.NewUserForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateEmailForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateUserNameForm;
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
    private ResponseEntity<?> saveUser(@RequestBody NewUserForm newUserForm) {
        return userService.save(newUserForm);
    }

    @PatchMapping("updateUsername")
    private ResponseEntity<?> updateUsername(@RequestBody UpdateUserNameForm updateUsernameForm) {
        return userService.updateUsername(updateUsernameForm);
    }

    @PatchMapping("updateEmail")
    private ResponseEntity<?> updateEmail(@RequestBody UpdateEmailForm updateUsernameForm) {
        return userService.updateEmail(updateUsernameForm);
    }

    @DeleteMapping("delete")
    private int deleteUser(@RequestBody UuidRequestForm uuidRequestForm) {
        return userService.delete(uuidRequestForm);
    }
}
