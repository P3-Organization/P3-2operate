package dk.aau.sw3.p32operate.business.services;

import dk.aau.sw3.p32operate.business.services.interfaces.IService;

import dk.aau.sw3.p32operate.dataAccess.repositories.interfaces.IRepository;
import dk.aau.sw3.p32operate.business.models.MyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService implements IService {

    private final IRepository _repository;

    @Autowired
    public MyService(IRepository repository) {
        this._repository = repository;
    }


    public MyModel getUserData(int id) {
        String username = _repository.findUserById(id);
        if (username == null) { return null; }

        MyModel user = createUserModel(username);
        return user;
    }

    public MyModel createUserModel(String name) {
        return new MyModel(name);
    }
}
