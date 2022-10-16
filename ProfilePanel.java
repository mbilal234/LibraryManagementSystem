package lms;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends RoundedPanel {
    JLabel nameLabel, statLabel, profilePic;
    JButton editProfile;

    public ProfilePanel(String name, String status, Image pic){
        super(null, 10, new Color(171, 102, 41));
        setOpaque(false);

        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        nameLabel.setForeground(new Color(253, 255, 247));
        nameLabel.setOpaque(false);
        nameLabel.setBounds(230, 35, 450, 35);

        statLabel = new JLabel(status);
        statLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        statLabel.setForeground(new Color(253, 255, 247));
        statLabel.setOpaque(false);
        statLabel.setBounds(230, 80, 450, 25);

        try{
            Image newimg = pic.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(newimg);
            profilePic = new JLabel(imageIcon);
            profilePic.setBounds(50, 30, 150, 150);
        }catch (Exception e){
            System.out.println("Warr gaye");
        }

//        ImageIcon icon = new ImageIcon("editicon.png");
//        Image editimg = icon.getImage().getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
//        ImageIcon editicon = new ImageIcon(editimg);
//        editProfile = new JButton(editicon);
//        editProfile.setBounds(660, 175, 30, 30);

        add(profilePic);
        add(nameLabel);
        add(statLabel);
//        add(editProfile);

        setMinimumSize(new Dimension(700, 215));
        setPreferredSize(new Dimension(700, 215));
        setMaximumSize(new Dimension(700, 215));
    }

    public ProfilePanel(Person person){
        this(person.getName(), (person instanceof Member)?"Member":((Employee) person).getJobTitle(), person.getProfilePic());
    }
}
