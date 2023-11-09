package com.fsrg.model.dto;

import com.fsrg.model.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDTO {
    private Integer id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String country;

    private Role role;
}
