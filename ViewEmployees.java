package lms;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

public class ViewEmployees extends JPanel implements ActionListener {
    JLabel title, name, username, phoneNumber, email, jobTitle;
    JPanel employeesPanel, header;
    JScrollPane scrolls;
    JTextField searchbar;
    JButton searchButton, addEmployeesButton;
    MoreOptionsPanel optionsPanel;
    OptionsButton optionsButton;

    public ViewEmployees() throws IOException, FontFormatException {
        setLayout(null);
        setBackground(new Color(254, 253, 209));

        optionsPanel = new MoreOptionsPanel();
        optionsPanel.set_admin_options();
        optionsButton = new OptionsButton(optionsPanel);

        title = new JLabel("EMPLOYEES");
        Font f = Font.createFont(Font.TRUETYPE_FONT, new File("impact.TTF")).deriveFont(Font.BOLD, 52f);
        title.setFont(f);
        title.setForeground(new Color(124, 59, 12));
        title.setBounds(375, 25, 300, 100);
        title.setOpaque(false);

        employeesPanel = new JPanel();
        employeesPanel.setLayout(new BoxLayout(employeesPanel, BoxLayout.Y_AXIS));
        employeesPanel.setBounds(150, 160, 700, 430);
        employeesPanel.setBackground(new Color(253, 255, 247));

        name = new JLabel("NAME");
        name.setBounds(5, 2, 150, 30);
        username = new JLabel("USERNAME");
        username.setBounds(160, 2, 150, 30);
        phoneNumber = new JLabel("PHONE NUMBER");
        phoneNumber.setBounds(315, 2, 150, 30);
        email = new JLabel("EMAIL ADDRESS");
        email.setBounds(470, 2, 150, 30);
        jobTitle = new JLabel(String.valueOf("JOB TITLE"));
        jobTitle.setBounds(625, 2, 100, 30);

        header = new JPanel();
        header.setLayout(null);
        header.setMinimumSize(new Dimension(850, 35));
        header.setPreferredSize(new Dimension(850, 35));
        header.setMaximumSize(new Dimension(850, 35));
        header.add(name);
        header.add(username);
        header.add(phoneNumber);
        header.add(email);
        header.add(jobTitle);
        employeesPanel.add(header);

        scrolls = new JScrollPane(employeesPanel);
        scrolls.setBounds(150, 160, 700, 430);
        scrolls.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrolls.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolls.getVerticalScrollBar().setUnitIncrement(16);

        searchbar = new JTextField("Enter Username");
        searchbar.setForeground(Color.GRAY);
        searchbar.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (searchbar.getText().equals("Enter Username")) {
                    searchbar.setText("");
                    searchbar.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (searchbar.getText().isEmpty()) {
                    searchbar.setForeground(Color.GRAY);
                    searchbar.setText("Enter Username");
                }
            }
        });
        searchbar.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                try {
                    updateSearch();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            public void removeUpdate(DocumentEvent e) {
                try {
                    updateSearch();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            public void insertUpdate(DocumentEvent e) {
                try {
                    updateSearch();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            public void updateSearch() throws IOException {
                employeesPanel.removeAll();
                employeesPanel.updateUI();
                employeesPanel.add(header);
                DynamicSearch data = new DynamicSearch();
                data.searchEmployees(searchbar.getText());
                for (Employee employee : data.employees) {
                    EmployeeDisplay employeeDisplay = new EmployeeDisplay(employee);
                    employeesPanel.setAlignmentX(CENTER_ALIGNMENT);
                    employeesPanel.add(Box.createRigidArea(new Dimension(700, 10)));
                    employeesPanel.add(employeeDisplay);
                }
            }
        });
        searchbar.setBounds(240, 120, 300, 30);

        searchButton = new JButton("Search");
        searchButton.setBounds(545, 120, 80, 30);
        searchButton.setForeground(new Color(254, 253, 209));
        searchButton.setBackground(new Color(116, 67, 37));
        addEmployeesButton = new JButton("Add Employee");
        addEmployeesButton.setBounds(630, 120, 120, 30);
        addEmployeesButton.setForeground(new Color(254, 253, 209));
        addEmployeesButton.setBackground(new Color(116, 67, 37));
        addEmployeesButton.addActionListener(this);

        add(optionsPanel);
        add(optionsButton);
        add(title);
        add(searchbar);
        add(searchButton);
        add(addEmployeesButton);
        add(scrolls);

        setBounds(0, 0, 1000, 700);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(searchButton)) {

        }
        if (e.getSource().equals(addEmployeesButton)) {
            App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            f3.add_employee();
        }
    }
}