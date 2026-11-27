package dk.aau.sw3.p32operate.business.mappers.interfaces;

import dk.aau.sw3.p32operate.business.models.User;
import dk.aau.sw3.p32operate.dataAccess.entities.UserEntity;

public interface IUserMapper {
    User mapToUser(UserEntity userEntity);
}
