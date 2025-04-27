package com.ExamZenith.ExamZenith.users;

import com.ExamZenith.ExamZenith.errors.NotFoundException;
import com.ExamZenith.ExamZenith.users.persistence.Role;
import com.ExamZenith.ExamZenith.users.persistence.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRole(Long id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Role with id:" + id + " not found")
        );
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    public Set<Role> getRolesByIds(Set<Long> ids) {
        return ids.stream()
                .map(this::getRoleById)
                .collect(Collectors.toSet());
    }
}
