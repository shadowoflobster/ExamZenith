package com.ExamZenith.ExamZenith.users.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserRequest {
    @NotBlank
    @Size(min=5, max=30)
    private String name;
    @NotBlank
    @Size(min=3, max=30)
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;
    @NotEmpty
    private Set<Long> roleIds;

}
