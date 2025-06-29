package services;

import bussines.model.Credential;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UserService {

    private final List<Credential> listaDeUsuarios;

    public UserService() {
        this.listaDeUsuarios = new ArrayList<>();
        addUser(new Credential("admin", "admin123"));
        addUser(new Credential("estudiante", "clave456"));
    }

    public void addUser(Credential nuevoUsuario) {
        Objects.requireNonNull(nuevoUsuario, "El usuario a añadir no puede ser nulo.");
        this.listaDeUsuarios.add(nuevoUsuario);
    }

    public List<Credential> getAllUsers() {
        return Collections.unmodifiableList(this.listaDeUsuarios);
    }

    public boolean userExists(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return this.listaDeUsuarios.stream()
                   .anyMatch(user -> user.getUserName().equalsIgnoreCase(username.trim()));
    }
}