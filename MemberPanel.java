package lms;

import javax.swing.*;
import java.awt.*;

public class MemberPanel extends JPanel {
    ProfilePanel profilePanel;
    MoreOptionsPanel optionsPanel;
    OptionsButton optionsButton;
    MessageDisplayPanel messageRequests;
    JScrollPane scrolls;

    public MemberPanel() {
        setLayout(null);
        setBackground(new Color(254, 253, 209));

        optionsPanel = new MoreOptionsPanel();
        optionsPanel.set_member_options();

        optionsButton = new OptionsButton(optionsPanel);

        messageRequests = new MessageDisplayPanel(1);

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

    public void setMemberAccount(String username) {
        CheckMemberCredentials checkMemberCredentials = new CheckMemberCredentials(username);
        AcquireMemberCredentials acquireMemberCredentials = new AcquireMemberCredentials(username);
        acquireMemberCredentials.getMemberData();
        profilePanel = new ProfilePanel(acquireMemberCredentials.member);
        profilePanel.setBounds(150, 35, 700, 215);
        add(profilePanel);
    }
}
