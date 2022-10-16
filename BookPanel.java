package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BookPanel extends RoundedPanel implements ActionListener {
    JButton issue;
    JPanel bookDetails;
    JLabel bookCover, title, tag1, tag2, tag3, authorLabel, isbnLabel;
    Book book;

    public BookPanel(Book book) throws IOException {
        super(30, new Color(254, 253, 209));
        this.book = book;
        setLayout(new FlowLayout());
        bookDetails = new JPanel();
        GroupLayout gp = new GroupLayout(bookDetails);
        gp.setAutoCreateGaps(true);
        gp.setAutoCreateContainerGaps(true);
        bookDetails.setLayout(gp);
        bookDetails.setOpaque(false);

        Image newimg = book.getCover().getScaledInstance(75, 120,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(newimg);
        bookCover = new JLabel(imageIcon);
        add(bookCover);

        title = new JLabel(book.getTitle());
        title.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        title.setForeground(new Color(116, 67, 37));
        Color tagColor = new Color(171, 102, 41);

        String[] genreList = book.getGenre().split(", ");

        tag1 = new JLabel(genreList[0]);
        tag1.setOpaque(true);
        tag1.setBackground(tagColor);
        tag1.setForeground(new Color(253, 255, 247));

        tag2 = new JLabel(genreList[1]);
        tag2.setOpaque(true);
        tag2.setBackground(tagColor);
        tag2.setForeground(new Color(253, 255, 247));

        tag3 = new JLabel(genreList[2]);
        tag3.setOpaque(true);
        tag3.setBackground(tagColor);
        tag3.setForeground(new Color(253, 255, 247));

        authorLabel = new JLabel(book.getAuthor());
        isbnLabel = new JLabel("ISBN 10: " + book.getIsbn());

        issue = new JButton("Issue Book");
        issue.setBounds(20, 0, 10, 10);
        issue.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 12));
        issue.setBackground(new Color(171, 102, 41));
        issue.setForeground(new Color(253, 255, 247));
        issue.addActionListener(this);

        if (!book.getIssued_by().equals("No One")) {
            issue.setEnabled(false);
            issue.setBackground(new Color(116, 67, 37));
        }

        gp.setHorizontalGroup(gp.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(title).addGroup(gp.createSequentialGroup().addComponent(tag1).
                addComponent(tag2).addComponent(tag3)).addComponent(authorLabel).addGroup(gp.createSequentialGroup().addComponent(isbnLabel).addComponent(issue)));

        gp.setVerticalGroup(
                gp.createSequentialGroup().addComponent(title).addGroup(gp.createParallelGroup().addComponent(tag1).addComponent(tag2).addComponent(tag3)).
                        addComponent(authorLabel).addComponent(isbnLabel).addComponent(issue));
        add(bookDetails);

        setMinimumSize(new Dimension(400, 160));
        setPreferredSize(new Dimension(400, 160));
        setMaximumSize(new Dimension(400, 160));

        setOpaque(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(issue)) {
            App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
                PreparedStatement preparedStatement = con.prepareStatement("INSERT into requests VALUES (?, ?)");
                preparedStatement.setString(1, "Wants to issue "+ book.getTitle());
                preparedStatement.setString(2, f3.memberName);
                preparedStatement.execute();
            }catch (Exception err){
                err.printStackTrace();
            }

        }
    }
}
