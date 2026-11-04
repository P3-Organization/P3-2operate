package dk.aau.sw3.p32operate.dataAccess.repositories;

import dk.aau.sw3.p32operate.dataAccess.repositories.interfaces.IRepository;

import java.util.Dictionary;
import java.util.Hashtable;
import org.springframework.stereotype.Repository;

@Repository
public class MyRepository implements IRepository {
    private Dictionary<Integer, String> users = new Hashtable<Integer, String>() {{
        put(1, "Harry");
        put(2, "Mikkel");
        put(3, "Heini");
    }};

    public String findUserById(int id) {
        String result = users.get(id);
        if (result == null) {
            // Could throw an exception or something, but for simplicity's sake, we'll just return null
            return null;
        } else {
            return result;
        }
    }

}
