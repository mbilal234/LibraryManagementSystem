package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

public class LoginWindowPanel extends JPanel implements ActionListener{
    Image show, hide;
    JLabel signIn;
    JPanel credentials, welcomeMessage;
    JTextField username_login;
    JPasswordField password_login;
    JButton loginButton, show_hide;
    boolean password_visible;
    MoreOptionsPanel moreOptionsPanel;
    OptionsButton  optionsButton;

    int x = 50;
    Timer timer;

    public LoginWindowPanel() throws IOException, FontFormatException {
        setLayout(null);
        setBackground(new Color(254, 253, 209));

        moreOptionsPanel = new MoreOptionsPanel();
        moreOptionsPanel.set_login_options();

        optionsButton = new OptionsButton(moreOptionsPanel);

        welcomeMessage = new JPanel(){
            {timer = new Timer(13, this::actionPerformed);
                timer.start();}
            public void paint(Graphics g){
                super.paint(g);
                Graphics2D g2D = (Graphics2D) g;

                String part_1 = "Welcome To Library Management System";

                g2D.setFont(new Font("Arial", Font.BOLD, 60));
                g2D.setPaint(new Color(116, 67, 37));
                g2D.drawString(part_1, x, 50);
            }

            public void actionPerformed(ActionEvent e) {
                x -= 1;
                repaint();
                if (x==-1200){
                    x = 870;
                }
            }
        };
        welcomeMessage.setLayout(null);
        welcomeMessage.setBounds(130, 120, 740, 70);
        welcomeMessage.setOpaque(false);

        signIn = new JLabel("SIGN IN");
        Font f = Font.createFont(Font.TRUETYPE_FONT, new File("impact.TTF")).deriveFont(Font.BOLD, 28f);
        signIn.setFont(f);
        signIn.setBounds(128, 15, 200, 30);
        signIn.setForeground(new Color(254, 253, 209));
        signIn.setOpaque(false);

        credentials = new RoundedPanel(null, 50, new Color(171, 102, 41));
        credentials.setOpaque(false);
        credentials.setBounds(325, 240, 350, 200);

        username_login = new JTextField("Username");
        username_login.setForeground(Color.GRAY);
        username_login.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (username_login.getText().equals("Username")) {
                    username_login.setText("");
                    username_login.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (username_login.getText().isEmpty()) {
                    username_login.setForeground(Color.GRAY);
                    username_login.setText("Username");
                }
            }
        });
        username_login.setBounds(50, 60, 250, 30);


        password_login = new JPasswordField("Password");
        password_login.setForeground(Color.GRAY);
        password_login.setEchoChar((char) 0);
        password_login.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(password_login.getPassword()).equals("Password")) {
                    password_login.setText("");
                    password_login.setForeground(Color.BLACK);
                    password_login.setEchoChar('●');
                }
            }
            public void focusLost(FocusEvent e) {
                if (String.valueOf(password_login.getPassword()).equals("")) {
                    password_login.setForeground(Color.GRAY);
                    password_login.setText("Password");
                    password_login.setEchoChar((char) 0);
                }
            }
        });
        password_login.setBounds(50, 100, 220, 30);
        password_visible = false;

        ImageIcon icon1 = new ImageIcon("Show.PNG");
        show = icon1.getImage().getScaledInstance(28, 30, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon("Hide.PNG");
        hide = icon2.getImage().getScaledInstance(28, 30, Image.SCALE_SMOOTH);

        show_hide = new JButton(new ImageIcon((Image) show));
        show_hide.setBackground(Color.white);
        show_hide.setBounds(272, 100, 28, 30);
        show_hide.addActionListener(this);

        loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.setBounds(50, 140, 250, 30);
        loginButton.setBackground(new Color(124, 59, 12));
        loginButton.setForeground(new Color(254, 253, 209));
        loginButton.addActionListener(this);

        credentials.add(signIn);
        credentials.add(username_login);
        credentials.add(password_login);
        credentials.add(loginButton);
        credentials.add(show_hide);

        add(welcomeMessage);
        add(credentials);
        add(moreOptionsPanel);
        add(optionsButton);

    }

    public void check_login_credentials(){
        AdminCredentials adminCredentials = new AdminCredentials(username_login.getText());
        CheckCredentials checkMemberCredentials = new CheckMemberCredentials(username_login.getText());
        CheckCredentials checkEmployeeCredentials = new CheckEmployeeCredentials(username_login.getText());
        if (username_login.getText().equals(adminCredentials.getAdmin_name())){
            if (String.valueOf(password_login.getPassword()).equals(adminCredentials.getAdmin_key())){
                App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
                f3.statControl = 0;
                f3.adminName = adminCredentials.getAdmin_name();
                f3.open_admin_account();
            }else{
                JOptionPane.showMessageDialog(this, "Incorrect Password",
                        "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            boolean it_is_member = checkMemberCredentials.username_in_database();
            if (it_is_member){
                if (String.valueOf(password_login.getPassword()).equals(checkMemberCredentials.getPassword())){
                    App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
                    f3.statControl = 2;
                    f3.memberName = checkMemberCredentials.getUsername();
                    f3.open_member_account();
                }else{
                    JOptionPane.showMessageDialog(this, "Incorrect Password",
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                boolean it_is_employee = checkEmployeeCredentials.username_in_database();
                if (it_is_employee){
                    if (String.valueOf(password_login.getPassword()).equals(checkEmployeeCredentials.getPassword())){
                        App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
                        f3.statControl = 1;
                        //f3.open_employee_account();
                    }else{
                        JOptionPane.showMessageDialog(this, "Incorrect Password",
                                "Login Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "User Not Found!",
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==show_hide)
        if (password_visible){
            show_hide.setIcon(new ImageIcon((Image) show));
            password_login.setEchoChar('●');
            password_visible = false;
        }else {
            show_hide.setIcon(new ImageIcon((Image) hide));
            password_login.setEchoChar((char) 0);
            password_visible = true;
        }
        if (e.getSource()==loginButton){
            check_login_credentials();
        }
    }
}
