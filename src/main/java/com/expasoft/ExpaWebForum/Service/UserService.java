package com.expasoft.ExpaWebForum.Service;

import com.expasoft.ExpaWebForum.Entity.DTO.UserDTO;
import com.expasoft.ExpaWebForum.Entity.Template.UuidRequestForm;
import com.expasoft.ExpaWebForum.Entity.UserEntity;
import com.expasoft.ExpaWebForum.Repository.UserRepository;
import com.expasoft.ExpaWebForum.Service.CRUD.ICrud;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService{

    private final ModelMapper mapper;
    private final UserRepository rep;

    public ResponseEntity<?> getOne(UUID id) {
        Optional<UserEntity> user = rep.findById(id);
        return ResponseEntity.ofNullable(
                Optional.ofNullable(mapper.map(user.orElseThrow(), UserDTO.class))
        );
    }

    public ResponseEntity<?> getAll() {
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> save(UserDTO userDTO) {
        return ResponseEntity.of(
                Optional.ofNullable(rep.save(mapper.map(userDTO, UserEntity.class))
                ));
    }

    public ResponseEntity<?> update(UUID id, UserDTO userDTO) {
        return ResponseEntity.noContent().build();
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
