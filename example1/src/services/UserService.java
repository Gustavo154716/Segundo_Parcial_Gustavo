package services;

import java.util.ArrayList;
import java.util.List;

import bussines.model.Credential;

public class UserService {
    private List<Credential> users;

    public UserService() {
        this.users = new ArrayList<>();
        // Si quieres, carga datos de prueba:
        this.users.add(new Credential("admin", "admin123"));
        this.users.add(new Credential("test", "1234"));
    }

    public void addUser(Credential cred) {
        this.users.add(cred);
    }

    public List<Credential> getAllUsers() {
        return this.users;
    }
}
