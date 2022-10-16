package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberDisplay extends JPanel implements ActionListener {
    JButton removeButton, viewButton;
    Member member;

    public MemberDisplay(Member member) {
        this.member = member;
        setLayout(null);
        setOpaque(false);
        setMinimumSize(new Dimension(850, 35));
        setPreferredSize(new Dimension(850, 35));
        setMaximumSize(new Dimension(850, 35));

        JLabel name = new JLabel(member.getName());
        name.setBounds(5,2,150, 30);
        JLabel username = new JLabel(member.getUsername());
        username.setBounds(160, 2,150, 30);
        JLabel phoneNumber = new JLabel(member.getPhone());
        phoneNumber.setBounds(315, 2, 150, 30);
        JLabel email = new JLabel(member.getEmail());
        email.setBounds(470, 2, 150, 30);

        int booksIssued = 0;
        if (!member.getBook1().equals("")) booksIssued += 1;
        if (!member.getBook2().equals("")) booksIssued += 1;
        if (!member.getBook3().equals("")) booksIssued += 1;

        JLabel books = new JLabel(String.valueOf(booksIssued));
        books.setBounds(625, 2, 100, 30);

        ImageIcon icon = new ImageIcon("remove.png");
        Image img = icon.getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        removeButton = new JButton(new ImageIcon(img));
        removeButton.setBounds(730, 2, 20, 20);
        removeButton.setBackground(new Color(136, 85, 61));
        removeButton.addActionListener(this);

        icon = new ImageIcon("view.png");
        img = icon.getImage().getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        viewButton = new JButton(new ImageIcon(img));
        viewButton.setBounds(765, 2, 30, 30);
        viewButton.setBackground(new Color(136, 85, 61));
        viewButton.addActionListener(this);


        add(name);
        add(username);
        add(phoneNumber);
        add(email);
        add(books);
        add(removeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(removeButton)) {
            new RemoveMember().check_username(member.getUsername());
        }
        if (e.getSource().equals(viewButton)) {

        }
    }
}
