package com.nnk.springboot.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "Username is mandatory")
    @Column(name = "user_name")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "FullName is mandatory")
    @Column(name = "full_name")
    private String fullName;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "Role is mandatory")
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(fullName, user.fullName) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, fullName, role);
    }
}
