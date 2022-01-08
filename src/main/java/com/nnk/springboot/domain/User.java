package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "FullName is mandatory")
    @Column(name = "full_name")
    private String fullName;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "Role is mandatory")
    private String role;

}
