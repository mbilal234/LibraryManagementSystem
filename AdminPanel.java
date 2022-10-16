package lms;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {
    ProfilePanel profilePanel;
    MoreOptionsPanel optionsPanel;
    OptionsButton optionsButton;
    MessageDisplayPanel messageRequests;
    JScrollPane scrolls;

    public AdminPanel() {
        setLayout(null);
        setBackground(new Color(254, 253, 209));

        optionsPanel = new MoreOptionsPanel();
        optionsPanel.set_admin_options();

        optionsButton = new OptionsButton(optionsPanel);

        messageRequests = new MessageDisplayPanel(0);

        scrolls = new JScrollPane(messageRequests);
        scrolls.setSize(700, 200);
        scrolls.setLocation(150,300);
        scrolls.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrolls.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolls.getVerticalScrollBar().setUnitIncrement(16);

        add(optionsButton);
        add(optionsPanel);
        add(scrolls);

        setBounds(0, 0, 1000, 700);
    }

    public void setAdminAccount(String username) {
        AdminCredentials adminCredentials = new AdminCredentials(username);
        profilePanel = new ProfilePanel(adminCredentials.getAdmin_name(), "Administrator", adminCredentials.getProfilePic());
        profilePanel.setBounds(150, 35, 700, 215);
        add(profilePanel);
    }
}
