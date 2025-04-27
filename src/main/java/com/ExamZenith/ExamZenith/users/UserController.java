package com.ExamZenith.ExamZenith.users;


import com.ExamZenith.ExamZenith.users.model.UserRequest;
import com.ExamZenith.ExamZenith.users.persistence.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.ExamZenith.ExamZenith.security.AuthorizationConstants.ADMIN;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize(ADMIN)
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody @Valid UserRequest request){
            userService.createUser(request);
    }

    //FIX SECURITY
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }



}
