package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import bussines.UserManagement;
import ui.components.Button;
import ui.config.Config;
import ui.components.List;
import ui.components.Panel;
import ui.components.SimpleTablePanel;

public class DashBoard extends JFrame {

   
    private static final String FRAME_TITLE = "DashBoard Principal";
    private static final String BUTTON_USER_MANAGEMENT_TEXT = "Gestión de Usuarios";
    private static final String WEST_PANEL_LIST_ITEM_1 = "Maria";
    private static final String WEST_PANEL_LIST_ITEM_2 = "Juan";
    private static final String WEST_PANEL_LIST_ITEM_3 = "Lucas";
    private static final String WEST_PANEL_LIST_ITEM_4 = "John";
    private static final String[] TABLE_COLUMNS = { "ID", "Parcial 1 ", "Parcial 2", "Parcial 3", "Final", "Laboratorio", "Promedio final" };
    private static final Object[][] TABLE_DATA = {
        { "1", "8.5", "9.0", "7.5", "8.0", "9.5" },
        { "2", "7.0", "6.5", "8.0", "7.5", "8.0" },
        { "3", "9.0", "8.5", "9.5", "10.0", "9.0" },
        { "4", "6.0", "7.0", "6.5", "7.5", "6.0" },
        { "5", "8.0", "8.5", "9.0", "8.5", "9.0" }
    };

    private Panel westPanel;
    private Panel centerPanel;

    public DashBoard() {
        super(FRAME_TITLE);
        initializeFrame();
        createAndAddPanels();
        populateWestPanel();
        populateCenterPanel();
    }

    private void initializeFrame() {
        setSize(new Dimension(Config.WIDTH, Config.HEIGHT));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createAndAddPanels() {
        westPanel = new Panel();
        westPanel.setLayout(new BorderLayout());
        westPanel.setPreferredSize(new Dimension(200, 0));

        centerPanel = new Panel();
        centerPanel.setLayout(new BorderLayout());

        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void populateWestPanel() {
        List<String> itemList = createListPanel();
        westPanel.add(itemList, BorderLayout.CENTER);

        Button userManagementButton = createButton(BUTTON_USER_MANAGEMENT_TEXT, new UserManagementButtonListener());
        westPanel.add(userManagementButton, BorderLayout.SOUTH);
    }

    private List<String> createListPanel() {
        List<String> list = new List<>();
        list.addItem(WEST_PANEL_LIST_ITEM_1);
        list.addItem(WEST_PANEL_LIST_ITEM_2);
        list.addItem(WEST_PANEL_LIST_ITEM_3);
        list.addItem(WEST_PANEL_LIST_ITEM_4);
        return list;
    }

    private Button createButton(String text, ActionListener listener) {
        Button button = new Button(text);
        button.addActionListener(listener);
        return button;
    }

    private void populateCenterPanel() {
        SimpleTablePanel tablePanel = createTablePanel();
        centerPanel.add(tablePanel, BorderLayout.CENTER);
    }

    private SimpleTablePanel createTablePanel() {
        SimpleTablePanel tablePanel = new SimpleTablePanel(TABLE_COLUMNS, TABLE_DATA);
        tablePanel.setSize(600, 300);
        tablePanel.setVisible(true);
        return tablePanel;
    }

   
    private class UserManagementButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           
            new UserManagement();
        }
    }
}