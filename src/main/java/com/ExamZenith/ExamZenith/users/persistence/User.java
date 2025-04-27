package com.ExamZenith.ExamZenith.users.persistence;


import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "exam_zenith")
@SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(generator = "user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            schema = "exam_zenith",
            joinColumns = @JoinColumn(name = ("user_id")),
            inverseJoinColumns = @JoinColumn(name = ("role_id"))
    )
    private Set<Role> roles = new HashSet<>();



}
