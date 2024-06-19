package com.expasoft.ExpaWebForum.Service;

import com.expasoft.ExpaWebForum.Entity.DTO.UserDTO;
import com.expasoft.ExpaWebForum.Entity.Template.NewUserForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateEmailForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateUserNameForm;
import com.expasoft.ExpaWebForum.Entity.Template.UuidRequestForm;
import com.expasoft.ExpaWebForum.Entity.UserEntity;
import com.expasoft.ExpaWebForum.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper mapper;
    private final UserRepository rep;

    public ResponseEntity<UserDTO> getOne(UUID id) {
        UserEntity user = rep.findById(id).orElseThrow();

        return ResponseEntity.ofNullable(
                mapper.map(user, UserDTO.class)
        );
    }

    public ResponseEntity<?> getAll() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<UserEntity> save(NewUserForm newUserForm) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(newUserForm.getUsername());
        newUser.setEmail(newUserForm.getEmail());
        newUser.setPassword(newUserForm.getPassword());

        return ResponseEntity.ofNullable(
                rep.save(newUser)
        );
    }

    public ResponseEntity<UserEntity> updateUsername(UpdateUserNameForm updateUserNameForm) {
        UserEntity user = rep.findById(updateUserNameForm.getUser_id()).orElseThrow();
        user.setUsername(updateUserNameForm.getUsername());

        return ResponseEntity.ofNullable(
                rep.save(user)
        );
    }

    public ResponseEntity<?> updateEmail(UpdateEmailForm updateUsernameForm) {
        UserEntity user = rep.findById(updateUsernameForm.getUser_id()).orElseThrow();
        user.setEmail(updateUsernameForm.getEmail());

        return ResponseEntity.ofNullable(
                rep.save(user)
        );
    }

    public int delete(UuidRequestForm uuidRequestForm) {
        try {
            rep.deleteById(uuidRequestForm.id());
            return 1;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return -1;
        }
    }

}
