package dk.aau.sw3.p32operate.business.mappers;

import dk.aau.sw3.p32operate.business.mappers.interfaces.IUserMapper;
import dk.aau.sw3.p32operate.business.models.User;
import dk.aau.sw3.p32operate.dataAccess.entities.UserEntity;

public class UserMapper implements IUserMapper {
    @Override
    public User mapToUser(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRole(),
                userEntity.getEmail(),
                userEntity.getPhoneNumber()
        );
    }
}
