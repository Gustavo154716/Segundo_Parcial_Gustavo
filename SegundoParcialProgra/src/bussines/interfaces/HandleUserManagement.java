package bussines.interfaces;

import bussines.model.Credential;

public interface HandleUserManagement {
    void onSaveUser(Credential datosDelUsuario);
    void onDeleteUser(String userId); // <<< Este si quieres añadir un nuevo contrato
}