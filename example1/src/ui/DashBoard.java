package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import ui.UserManagementWindow;
import ui.config.Config;
import ui.components.Button;
import ui.components.List;
import ui.components.Panel;
import ui.components.SimpleTablePanel;

public class DashBoard extends JFrame {
    private String title;
    private Dimension dimension;
    private Panel westPanel, centerPanel;

    public DashBoard(String title) {
        super(title);
        this.title = title;
        this.dimension = new Dimension(Config.WIDTH, Config.HEIGHT);
        this.initConfig();
        this.initUi();
        this.setVisible(true);  // <-- Aquí debe ir después de initUi()
    }

    private void initConfig() {
        this.setSize(this.dimension);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // NO poner setVisible(true) aquí
    }

    private void initUi() {
        createContainer();
        createList();
        createTable();
        createButtons();
        this.revalidate();
        this.repaint();
    }

    private void createButtons() {
        Button openUserManagement = new Button("Gestión de usuarios");
        openUserManagement.addActionListener(e -> {
            new UserManagementWindow().setVisible(true);
        });
        westPanel.add(openUserManagement, BorderLayout.SOUTH);
    }

    private void createContainer() {
        this.westPanel = new Panel();
        this.westPanel.setLayout(new BorderLayout());
        // En lugar de setSize usa setPreferredSize para el westPanel
        this.westPanel.setPreferredSize(new Dimension(200, 300));

        this.centerPanel = new Panel();
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.setPreferredSize(new Dimension(600, 400));

        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void createList() {
        List<String> list = new List<>();
        list.addItem("Maria");
        list.addItem("Juan");
        list.addItem("Lucas");
        list.addItem("John");
        westPanel.add(list, BorderLayout.CENTER);
    }

    private void createTable() {
        String[] columns = { "ID", "Parcial 1", "Parcial 2", "Parcial 3", "Final", "Laboratorio", "Promedio final" };
        Object[][] data = {
                { "1", "8.5", "9.0", "7.5", "8.0", "9.5" },
                { "2", "7.0", "6.5", "8.0", "7.5", "8.0" },
                { "3", "9.0", "8.5", "9.5", "10.0", "9.0" },
                { "4", "6.0", "7.0", "6.5", "7.5", "6.0" },
                { "5", "8.0", "8.5", "9.0", "8.5", "9.0" }
        };
        SimpleTablePanel tablePanel = new SimpleTablePanel(columns, data);
        // No es necesario setSize aquí
        centerPanel.add(tablePanel, BorderLayout.CENTER);
    }
}
