package com.expasoft.ExpaWebForum.Service;

import com.expasoft.ExpaWebForum.Entity.DTO.UserDTO;
import com.expasoft.ExpaWebForum.Entity.Template.NewUserForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateEmailForm;
import com.expasoft.ExpaWebForum.Entity.Template.UpdateUserNameForm;
import com.expasoft.ExpaWebForum.Entity.Template.UuidRequestForm;
import com.expasoft.ExpaWebForum.Entity.UserEntity;
import com.expasoft.ExpaWebForum.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EntityManager entityManager;
    private final ModelMapper mapper;
    private final UserRepository rep;

    public Optional<UserDTO> getOne(UUID id) {
        UserEntity user = rep.findById(id).orElseThrow();

        return Optional.ofNullable(
                mapper.map(user, UserDTO.class)
        );
    }

    public Set<UserDTO> getAll() {
        return rep.findAll().stream().map(
                res -> mapper.map(res, UserDTO.class)
        ).collect(Collectors.toSet());
    }

    @Transactional
    public Optional<UserDTO> save(NewUserForm newUserForm) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(newUserForm.getUsername());
        newUser.setEmail(newUserForm.getEmail());
        newUser.setPassword(newUserForm.getPassword());

        entityManager.persist(newUser);

        return Optional.of(newUser).
                map(m -> mapper.map(m, UserDTO.class));
    }

    public Optional<UserDTO> updateUsername(UpdateUserNameForm updateUserNameForm) {
        UserEntity user = entityManager.find(UserEntity.class, updateUserNameForm.getUser_id());
        user.setUsername(updateUserNameForm.getUsername());
        entityManager.persist(user);
        return Optional.of(user).map(m -> mapper.map(m, UserDTO.class));
    }

    public Optional<UserDTO> updateEmail(UpdateEmailForm updateUsernameForm) {
        UserEntity user = entityManager.find(UserEntity.class, updateUsernameForm.getEmail());
        user.setEmail(updateUsernameForm.getEmail());
        entityManager.persist(user);
        return Optional.of(user).map(m->mapper.map(m,UserDTO.class));
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
