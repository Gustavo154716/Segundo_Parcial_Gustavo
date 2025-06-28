package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ui.components.Button;
import ui.components.Panel;
import ui.components.TextField;
import ui.components.SimpleTablePanel;

public class UserManagementWindow extends javax.swing.JFrame {

    private Panel mainPanel;
    private JPanel formPanel;

    private TextField usernameField;
    private TextField passwordField;

    private Button saveButton;
    private Button clearButton;

    private SimpleTablePanel userTablePanel;
    private JTable userTable;

    // Lista simulada de usuarios (nombre + contraseña oculta)
    private List<String[]> usersData;

    public UserManagementWindow() {
        super("Gestión de Usuarios");
        this.usersData = new ArrayList<>();
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUi();
    }

    private void initUi() {
        mainPanel = new Panel();
        mainPanel.setLayout(new BorderLayout(10, 10)); // espacios entre componentes

        // Columnas para la tabla
        String[] columns = { "Usuario", "Contraseña" };
        // Agregamos algunos usuarios de ejemplo
        usersData.add(new String[] { "Maria", "*****" });
        usersData.add(new String[] { "Juan", "*****" });

        // Convertimos la lista a matriz para llenar la tabla
        Object[][] tableData = usersData.toArray(new Object[0][]);
        userTablePanel = new SimpleTablePanel(columns, tableData);
        userTable = userTablePanel.getTable();

        JScrollPane scrollPane = new JScrollPane(userTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel para el formulario con FlowLayout (alineados y visibles)
        formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        usernameField = new TextField("Nombre de usuario");
        passwordField = new TextField("Contraseña");

        saveButton = new Button("Guardar usuario");
        clearButton = new Button("Limpiar formulario");

        // Agregar campos y botones al formulario
        formPanel.add(usernameField);
        formPanel.add(passwordField);
        formPanel.add(saveButton);
        formPanel.add(clearButton);

        mainPanel.add(formPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Eventos botones
        saveButton.addActionListener(e -> saveUser());
        clearButton.addActionListener(e -> clearForm());
    }

    private void saveUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validación simple (no dejar campos vacíos ni con placeholders)
        if (username.isEmpty() || username.equals("Nombre de usuario") ||
            password.isEmpty() || password.equals("Contraseña")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingresa usuario y contraseña válidos.");
            return;
        }

        // Agregar nuevo usuario a la lista simulada (mostrar contraseña oculta)
        usersData.add(new String[] { username, "*****" });

        // Actualizar la tabla con el nuevo usuario
        updateTable();

        javax.swing.JOptionPane.showMessageDialog(this, "Usuario guardado correctamente.");

        // Limpiar formulario para próximo ingreso
        clearForm();
    }

    private void clearForm() {
        usernameField.setText("Nombre de usuario");
        passwordField.setText("Contraseña");
    }

    private void updateTable() {
        Object[][] data = usersData.toArray(new Object[0][]);
        userTable.setModel(new DefaultTableModel(data, new String[] { "Usuario", "Contraseña" }));
    }

    // Getters para acceder desde UserManagement y conectar lógica
    public Button getSaveButton() {
        return saveButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }
}
