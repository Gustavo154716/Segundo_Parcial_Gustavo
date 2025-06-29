package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import bussines.interfaces.HandleUserManagement;
import bussines.model.Credential;
import ui.components.Button;
import ui.components.PasswordField;
import ui.components.SimpleTablePanel;
import ui.components.TextField;

public class UserManagementWindow extends JFrame {

    private SimpleTablePanel panelDeLaTabla;
    private TextField campoUsuario;
    private PasswordField campoPassword;
    private Button botonGuardar;
    private Button botonLimpiar;

    private HandleUserManagement callback;

    public UserManagementWindow() {
        super("Gestión de Usuarios");

        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setContentPane(panelPrincipal);

        String[] nombresDeColumnas = { "Nombre de Usuario" };
        Object[][] datosInicialesVacios = new Object[0][1];
        this.panelDeLaTabla = new SimpleTablePanel(nombresDeColumnas, datosInicialesVacios);
        this.panelDeLaTabla.setBorder(BorderFactory.createTitledBorder("Usuarios Registrados"));
        panelPrincipal.add(this.panelDeLaTabla, BorderLayout.CENTER);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Agregar Nuevo Usuario"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Usuario:"), gbc);
        
        this.campoUsuario = new TextField("Ingrese el nombre de usuario");
        
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
        panelFormulario.add(this.campoUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelFormulario.add(new JLabel("Contraseña:"), gbc);

        this.campoPassword = new PasswordField("Ingrese la contraseña");
        
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1;
        panelFormulario.add(this.campoPassword, gbc);
        
        this.botonGuardar = new Button("Guardar Usuario");
        this.botonLimpiar = new Button("Limpiar Formulario");
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(this.botonGuardar);
        panelBotones.add(this.botonLimpiar);
        
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panelFormulario.add(panelBotones, gbc);

        panelPrincipal.add(panelFormulario, BorderLayout.SOUTH);

        this.botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (callback != null) {
                    String usuario = new String(campoUsuario.getText()).trim();
                    String password = new String(campoPassword.getPassword());
                    Credential datos = new Credential(usuario, password);
                    callback.onSaveUser(datos);
                }
            }
        });
        
        this.botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });
    }

    public void setOnSaveCallback(HandleUserManagement callback) {
        this.callback = callback;
    }
    
    public void limpiarFormulario() {
        this.campoUsuario.setText("");
        this.campoPassword.setText("");
    }
    
    public void mostrarMensaje(String titulo, String mensaje, int tipoDeMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipoDeMensaje);
    }
    
    public void actualizarTabla(List<Credential> listaDeUsuarios) {
        DefaultTableModel modelo = this.panelDeLaTabla.getModel();
        modelo.setRowCount(0);
        
        for (Credential usuario : listaDeUsuarios) {
            Object[] fila = { usuario.getUserName() };
            modelo.addRow(fila);
        }
    }
}