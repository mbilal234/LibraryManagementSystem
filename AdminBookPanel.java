package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class AdminBookPanel extends RoundedPanel implements ActionListener {
    JButton issueButton, returnButton, removeButton;
    JPanel bookDetails;
    JLabel bookCover, title, tag1, tag2, tag3, authorLabel, isbnLabel;
    Book book;

    public AdminBookPanel(Book book) throws IOException {
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

        ImageIcon icon = new ImageIcon("issue.png");
        Image img = icon.getImage().getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
        issueButton = new JButton(new ImageIcon(img));
        issueButton.setBackground(new Color(171, 102, 41));
        issueButton.addActionListener(this);

        icon = new ImageIcon("return.png");
        img = icon.getImage().getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
        returnButton = new JButton(new ImageIcon(img));
        returnButton.setBackground(new Color(171, 102, 41));
        returnButton.addActionListener(this);

        icon = new ImageIcon("remove.png");
        img = icon.getImage().getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
        removeButton = new JButton(new ImageIcon(img));
        removeButton.setBackground(new Color(171, 102, 41));
        removeButton.addActionListener(this);


        gp.setHorizontalGroup(gp.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(title).addGroup(gp.createSequentialGroup().addComponent(tag1).
                addComponent(tag2).addComponent(tag3)).addComponent(authorLabel).addGroup(gp.createSequentialGroup().addComponent(isbnLabel)).
                addGroup(gp.createSequentialGroup().addComponent(issueButton).addComponent(returnButton).addComponent(removeButton)));

        gp.setVerticalGroup(
                gp.createSequentialGroup().addComponent(title).addGroup(gp.createParallelGroup().addComponent(tag1).addComponent(tag2).addComponent(tag3)).
                        addComponent(authorLabel).addComponent(isbnLabel).addGroup(gp.createParallelGroup().addComponent(issueButton).addGap(20,20,20).
                                addComponent(returnButton).addGap(20,20,20).addComponent(removeButton)));
        add(bookDetails);

        setMinimumSize(new Dimension(400, 160));
        setPreferredSize(new Dimension(400, 160));
        setMaximumSize(new Dimension(400, 160));

        setOpaque(false);
    }

    public void remove_book(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM books WHERE isbn = ?");
            preparedStatement.setInt(1, Integer.parseInt(book.getIsbn()));
            preparedStatement.execute();
            JOptionPane.showMessageDialog(this, "Book Removed");
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void check_user(String username){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from members");
            boolean member_present = false;
            while (rs.next()){
                if (rs.getString(1).equals(username)){
                    member_present = true;
                }
            }
            if (member_present){
                issue_book(username);
            }else{
                JOptionPane.showMessageDialog(this, "Username does not exist.", "Invalid Username", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void issue_book(String username){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from books");
            String book_name = "";
            String book_genre = "";
            while (rs.next()){
                if (rs.getInt(1)==Integer.parseInt(book.getIsbn())){
                    book_name = rs.getString(2);
                    book_genre = rs.getString(6);
                }
            }
            String update = "";
            String update_2 = "";
            ResultSet rs2 = stm.executeQuery("select * from members");
            boolean cannot_issue = false;
            while (rs2.next()){
                if (rs2.getString(1).equals(username)){
                    if (rs2.getString(16).length()==0){
                        update = "UPDATE members SET book_issued_1 = ? WHERE username = ?";
                        update_2 = "UPDATE members SET book_issued_1_genre = ? WHERE username = ?";
                    }else{
                        if (rs2.getString(18).length()==0){
                            update = "UPDATE members SET book_issued_2 = ? WHERE username = ?";
                            update_2 = "UPDATE members SET book_issued_2_genre = ? WHERE username = ?";
                        }else{
                            if (rs2.getString(20).length()==0){
                                update = "UPDATE members SET book_issued_3 = ? WHERE username = ?";
                                update_2 = "UPDATE members SET book_issued_3_genre = ? WHERE username = ?";
                            }else{
                                cannot_issue = true;
                            }
                        }
                    }
                }
            }
            if (!cannot_issue){
                PreparedStatement preparedStatement = con.prepareStatement("UPDATE books SET issued_by = ? WHERE isbn = ?");
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, Integer.parseInt(book.getIsbn()));
                preparedStatement.execute();
                PreparedStatement preparedStatement2 = con.prepareStatement(update);
                preparedStatement2.setString(1, book_name);
                preparedStatement2.setString(2, username);
                preparedStatement2.execute();
                PreparedStatement preparedStatement3 = con.prepareStatement(update_2);
                preparedStatement3.setString(1, book_genre);
                preparedStatement3.setString(2, username);
                preparedStatement3.execute();
                JOptionPane.showMessageDialog(this, "Book has been issued.");
            }else{
                JOptionPane.showMessageDialog(this, "User cannot issue more than 3 books.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void return_book(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from books");
            String member = "";
            while (rs.next()){
                if (rs.getInt(1)==Integer.parseInt(book.getIsbn())){
                    member = rs.getString(8);
                }
            }
            String book_name = "";
            ResultSet rs1 = stm.executeQuery("select * from books");
            while (rs1.next()){
                if (rs1.getInt(1)==Integer.parseInt(book.getIsbn())){
                    book_name = rs1.getString(2);
                }
            }
            String update = "";
            String update_2 = "";
            ResultSet rs2 = stm.executeQuery("select * from members");
            while (rs2.next()){
                if (rs2.getString(1).equals(member)){
                    if (rs2.getString(16).equals(book_name)){
                        update = "UPDATE members SET book_issued_1 = ? WHERE username = ?";
                        update_2 = "UPDATE members SET book_issued_1_genre = ? WHERE username = ?";
                    }else{
                        if (rs2.getString(18).equals(book_name)){
                            update = "UPDATE members SET book_issued_2 = ? WHERE username = ?";
                            update_2 = "UPDATE members SET book_issued_2_genre = ? WHERE username = ?";
                        }else{
                            if (rs2.getString(20).equals(book_name)){
                                update = "UPDATE members SET book_issued_3 = ? WHERE username = ?";
                                update_2 = "UPDATE members SET book_issued_3_genre = ? WHERE username = ?";
                            }
                        }
                    }
                }
            }
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE books SET issued_by = ? WHERE isbn = ?");
            preparedStatement.setString(1, "No One");
            preparedStatement.setInt(2, Integer.parseInt(book.getIsbn()));
            preparedStatement.execute();
            PreparedStatement preparedStatement1 = con.prepareStatement(update);
            preparedStatement1.setString(1, "");
            preparedStatement1.setString(2, member);
            preparedStatement1.execute();
            PreparedStatement preparedStatement2 = con.prepareStatement(update_2);
            preparedStatement2.setString(1, "");
            preparedStatement2.setString(2, member);
            preparedStatement2.execute();
            con.close();
            JOptionPane.showMessageDialog(this, "Book has been returned.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(issueButton)) {
            if (book.getIssued_by().equals("No One")) {
                String s = (String)JOptionPane.showInputDialog(this, "Enter Member Username:\n", "Issue Book",
                        JOptionPane.QUESTION_MESSAGE, null, null, "");
                check_user(s);
            }
        }
        if (e.getSource().equals(returnButton)) {
            if (!book.getIssued_by().equals("No One")) {
                return_book();
            }
        }
        if (e.getSource().equals(removeButton)) {
            if (book.getIssued_by().equals("No One")) {
                int option = JOptionPane.showConfirmDialog(this,"Confirm Removal?",
                        "Remove Book", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option==JOptionPane.YES_OPTION) remove_book();
            }
            else {
                JOptionPane.showMessageDialog(this, "Book is currently issued", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}