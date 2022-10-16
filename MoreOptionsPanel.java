package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoreOptionsPanel extends JPanel implements ActionListener {
    JButton home, employees, members, catalogue, logout, login, member_home;

    public MoreOptionsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(171, 102, 41));
        setBounds(-120, 30, 120, 670);

        login = new JButton("LogIn");
        login.addActionListener(this);

        home = new JButton("Home");
        home.addActionListener(this);

        employees = new JButton("Employees");
        employees.addActionListener(this);

        members = new JButton("Members");
        members.addActionListener(this);

        member_home = new JButton("Home");
        member_home.addActionListener(this);

        logout = new JButton("LogOut");
        logout.addActionListener(this);

        catalogue = new JButton("Catalogue");
        catalogue.addActionListener(this);

        setButtons(new JButton[]{home, employees, members, logout, login,  catalogue, member_home});
    }

    public void setButtons(JButton[] buttons) {
        for (JButton button : buttons){
            button.setForeground(new Color(254, 253, 209));
            button.setBackground(new Color(171, 102, 41));
            button.setFont(new Font("Arial", Font.BOLD, 15));
            button.setFocusable(false);
            button.setPreferredSize(new Dimension(120, 30));
            button.setMaximumSize(new Dimension(120, 30));
        }
    }

    public void set_admin_options() {
        removeAll();
        updateUI();
        add(home);
        add(employees);
        add(members);
        add(catalogue);
        add(logout);
    }

    public void set_member_options() {
        removeAll();
        updateUI();
        add(member_home);
        add(catalogue);
        add(logout);
    }

    public void set_login_options() {
        removeAll();
        updateUI();
        add(login);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(login) || e.getSource().equals(logout)) {
            App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            f3.open_login_page();
        }
        if(e.getSource().equals(catalogue)) {
            App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            f3.open_search_panel();
        }
        if (e.getSource().equals(home)) {
            App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            f3.open_admin_account();
        }
        if (e.getSource().equals(members)) {
            App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            f3.view_members();
        }
        if (e.getSource().equals(employees)) {
            App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            f3.view_employees();
        }
        if (e.getSource().equals(member_home)) {
            App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            f3.open_member_account();
        }
    }
}
