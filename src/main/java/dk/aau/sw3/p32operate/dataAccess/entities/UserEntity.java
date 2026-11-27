package dk.aau.sw3.p32operate.dataAccess.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Setter
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank
    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Setter
    @Column(name = "role", nullable = false)
    private String role;

    @NotBlank
    @Setter
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Setter
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
}
