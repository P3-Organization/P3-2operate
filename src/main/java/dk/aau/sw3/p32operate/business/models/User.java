package dk.aau.sw3.p32operate.business.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class User {

    private Long id;

    @Setter
    private String username;

    @Setter
    private String password;

    @Setter
    private String role;

    @Setter
    private String email;

    @Setter
    private String phoneNumber;
}
