package bussines;

import bussines.interfaces.HandleUserManagement;
import bussines.model.Credential;
import services.UserService;
import ui.UserManagementWindow;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.List;
import java.util.Objects;

public class UserManagement implements HandleUserManagement {

    private UserManagementWindow ventana;
    private final UserService servicioDeUsuarios;

    private static final String ERROR_VALIDATION_TITLE = "Error de Validación";
    private static final String ERROR_VALIDATION_MESSAGE = "Por favor, complete todos los campos.";
    private static final String WARNING_USER_EXISTS_TITLE = "Advertencia";
    private static final String WARNING_USER_EXISTS_MESSAGE = "Ya existe un usuario con ese nombre. Se guardará de todos modos.";
    private static final String SUCCESS_SAVE_TITLE = "Éxito";
    private static final String SUCCESS_SAVE_MESSAGE = "Usuario guardado correctamente.";

    public UserManagement() {
        this.servicioDeUsuarios = new UserService();
        
        SwingUtilities.invokeLater(() -> {
            this.ventana = new UserManagementWindow();
            this.ventana.setOnSaveCallback(this);
            actualizarListaDeUsuariosEnLaVista();
            this.ventana.setVisible(true);
        });
        
        this.ventana = null;
    }

    @Override
    public void onSaveUser(Credential datosDelUsuario) {
        Objects.requireNonNull(datosDelUsuario, "Los datos del usuario no pueden ser nulos.");

        String usuario = datosDelUsuario.getUserName().trim();
        String password = datosDelUsuario.getPassword();

        if (usuario.isEmpty() || password.isEmpty()) {
            ventana.mostrarMensaje(ERROR_VALIDATION_TITLE, ERROR_VALIDATION_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (this.servicioDeUsuarios.userExists(usuario)) {
             ventana.mostrarMensaje(WARNING_USER_EXISTS_TITLE, WARNING_USER_EXISTS_MESSAGE, JOptionPane.WARNING_MESSAGE);
        }

        this.servicioDeUsuarios.addUser(new Credential(usuario, password));
        
        actualizarListaDeUsuariosEnLaVista();
        ventana.limpiarFormulario();
        ventana.mostrarMensaje(SUCCESS_SAVE_TITLE, SUCCESS_SAVE_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void actualizarListaDeUsuariosEnLaVista() {
        List<Credential> usuarios = this.servicioDeUsuarios.getAllUsers();
        if (this.ventana != null) {
            this.ventana.actualizarTabla(usuarios);
        }
    }

    @Override
    public void onDeleteUser(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onDeleteUser'");
    }
}