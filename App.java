package lms;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class App extends JFrame {
    int statControl;
    String adminName, memberName;
    SearchWindowPanel searchPage;
    AddBooksPanel addBooksPanel;
    LoginWindowPanel loginPage;
    AdminPanel adminPanel;
    MemberPanel memberPanel;
    ViewMembers viewMembers;
    AddMemberPanel addMemberPanel;
    ViewEmployees viewEmployees;
    AddEmployeePanel addEmployeePanel;

    public App() throws IOException, FontFormatException {
        this.setTitle("Library Management System");
        this.setPreferredSize(new Dimension(1000, 700));
        this.setBounds(0,0, 1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        ImageIcon logo = new ImageIcon("logo.png");
        setIconImage(logo.getImage());

        adminPanel = new AdminPanel();
        this.add(adminPanel);
        adminPanel.setVisible(false);

        memberPanel = new MemberPanel();
        this.add(memberPanel);
        memberPanel.setVisible(false);

        searchPage = new SearchWindowPanel();
        this.add(searchPage);
        searchPage.setVisible(false);

        addBooksPanel = new AddBooksPanel();
        this.add(addBooksPanel);
        addBooksPanel.setVisible(false);

        viewMembers = new ViewMembers();
        this.add(viewMembers);
        viewMembers.setVisible(false);

        addMemberPanel = new AddMemberPanel();
        this.add(addMemberPanel);
        addMemberPanel.setVisible(false);

        viewEmployees = new ViewEmployees();
        this.add(viewEmployees);
        viewEmployees.setVisible(false);

        addEmployeePanel = new AddEmployeePanel();
        this.add(addEmployeePanel);
        addEmployeePanel.setVisible(false);

        loginPage = new LoginWindowPanel();
        this.add(loginPage);

        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        try {

            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        App app = new App();
    }

    public void open_admin_account() {
        adminPanel.setVisible(true);
        adminPanel.setAdminAccount(adminName);
        loginPage.setVisible(false);
        searchPage.setAdminBookSearch();
        searchPage.setVisible(false);
        addBooksPanel.setVisible(false);
        viewMembers.setVisible(false);
        addMemberPanel.resetFields();
        addMemberPanel.setVisible(false);
        viewEmployees.setVisible(false);
        addEmployeePanel.resetFields();
        addEmployeePanel.setVisible(false);
        memberPanel.setVisible(false);
        repaint();
    }

    public void open_member_account() {
        adminPanel.setVisible(false);
        loginPage.setVisible(false);
        searchPage.setMemberBookSearch();
        searchPage.setVisible(false);
        addBooksPanel.setVisible(false);
        viewMembers.setVisible(false);
        addMemberPanel.resetFields();
        addMemberPanel.setVisible(false);
        viewEmployees.setVisible(false);
        addEmployeePanel.resetFields();
        addEmployeePanel.setVisible(false);
        memberPanel.setMemberAccount(memberName);
        memberPanel.setVisible(true);
        repaint();
    }

    public void open_login_page() {
        loginPage.username_login.setText("");
        loginPage.password_login.setText("");
        loginPage.setVisible(true);
        adminPanel.setVisible(false);
        searchPage.setVisible(false);
        addBooksPanel.setVisible(false);
        viewMembers.setVisible(false);
        addMemberPanel.resetFields();
        addMemberPanel.setVisible(false);
        viewEmployees.setVisible(false);
        addEmployeePanel.resetFields();
        addEmployeePanel.setVisible(false);
        memberPanel.setVisible(false);
        repaint();
    }

    public void open_search_panel() {
        loginPage.setVisible(false);
        adminPanel.setVisible(false);
        viewMembers.setVisible(false);
        searchPage.setVisible(true);
        addBooksPanel.setVisible(false);
        addMemberPanel.resetFields();
        addMemberPanel.setVisible(false);
        viewEmployees.setVisible(false);
        addEmployeePanel.resetFields();
        addEmployeePanel.setVisible(false);
        memberPanel.setVisible(false);
        repaint();
    }

    public void view_members() {
        viewMembers.setVisible(true);
        loginPage.setVisible(false);
        adminPanel.setVisible(false);
        searchPage.setVisible(false);
        addBooksPanel.setVisible(false);
        addMemberPanel.resetFields();
        addMemberPanel.setVisible(false);
        viewEmployees.setVisible(false);
        addEmployeePanel.resetFields();
        addEmployeePanel.setVisible(false);
        memberPanel.setVisible(false);
        repaint();
    }

    public void add_member() {
        viewMembers.setVisible(false);
        loginPage.setVisible(false);
        adminPanel.setVisible(false);
        searchPage.setVisible(false);
        addBooksPanel.setVisible(false);
        addMemberPanel.resetFields();
        addMemberPanel.setVisible(true);
        viewEmployees.setVisible(false);
        addEmployeePanel.resetFields();
        addEmployeePanel.setVisible(false);
        memberPanel.setVisible(false);
        repaint();
    }

    public void view_employees() {
        viewMembers.setVisible(false);
        loginPage.setVisible(false);
        adminPanel.setVisible(false);
        searchPage.setVisible(false);
        addBooksPanel.setVisible(false);
        addMemberPanel.resetFields();
        addMemberPanel.setVisible(false);
        viewEmployees.setVisible(true);
        addEmployeePanel.resetFields();
        addEmployeePanel.setVisible(false);
        memberPanel.setVisible(false);
        repaint();
    }

    public void add_employee() {
        viewMembers.setVisible(false);
        loginPage.setVisible(false);
        adminPanel.setVisible(false);
        searchPage.setVisible(false);
        addBooksPanel.setVisible(false);
        addMemberPanel.resetFields();
        addMemberPanel.setVisible(false);
        viewEmployees.setVisible(false);
        addEmployeePanel.resetFields();
        addEmployeePanel.setVisible(true);
        memberPanel.setVisible(false);
        repaint();
    }

    public void add_books() {
        viewMembers.setVisible(false);
        loginPage.setVisible(false);
        adminPanel.setVisible(false);
        searchPage.setVisible(false);
        addBooksPanel.setVisible(true);
        addMemberPanel.resetFields();
        addMemberPanel.setVisible(false);
        viewEmployees.setVisible(false);
        addEmployeePanel.resetFields();
        addEmployeePanel.setVisible(false);
        memberPanel.setVisible(false);
        repaint();
    }
}
