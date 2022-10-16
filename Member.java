package lms;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public final class Member extends Person{
    private String book1, book2, book3, book1_genre, book2_genre, book3_genre;
    File picAddress;

    public Member(Image profilePic, String name, String username, String password, String gender, String email, String phone, int age, String book1, String book2, String book3, String book1_genre, String book2_genre, String book3_genre) {
        super(profilePic, name, username, password, gender, email, phone, age);
        this.book1 = book1;
        this.book2 = book2;
        this.book3 = book3;
        this.book1_genre = book1_genre;
        this.book2_genre = book2_genre;
        this.book3_genre = book3_genre;
    }

    public Member(File profilePic, String name, String username, String password, String gender, String email, String phone, int age){
        super(new ImageIcon(String.valueOf(profilePic)).getImage().getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH),
                name, username, password, gender, email, phone, age);
        this.picAddress = profilePic;

    }

    public void add_member_to_database(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            String sql = "INSERT INTO members VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, getUsername());
            preparedStmt.setString(2, getPassword());
            preparedStmt.setString(3, getName());
            preparedStmt.setInt(4, getAge());
            preparedStmt.setBlob(5, new FileInputStream(new File("pic.png")));
            int i=6;
            while(i<22){
                preparedStmt.setString(i, "");
                i += 1;
            }
            preparedStmt.setString(22,getGender());
            preparedStmt.setString(23, getEmail());
            preparedStmt.setString(24, getPhone());
            preparedStmt.execute();
            con.close();
            UpdateMemberProfilePic updateMemberProfilePic = new UpdateMemberProfilePic();
            updateMemberProfilePic.convert_file_to_blob(picAddress,getUsername());
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "New Member Added.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getBook1() {
        return book1;
    }

    public void setBook1(String book1) {
        this.book1 = book1;
    }

    public String getBook2() {
        return book2;
    }

    public void setBook2(String book2) {
        this.book2 = book2;
    }

    public String getBook3() {
        return book3;
    }

    public void setBook3(String book3) {
        this.book3 = book3;
    }

    public String getBook1_genre() {
        return book1_genre;
    }

    public void setBook1_genre(String book1_genre) {
        this.book1_genre = book1_genre;
    }

    public String getBook2_genre() {
        return book2_genre;
    }

    public void setBook2_genre(String book2_genre) {
        this.book2_genre = book2_genre;
    }

    public String getBook3_genre() {
        return book3_genre;
    }

    public void setBook3_genre(String book3_genre) {
        this.book3_genre = book3_genre;
    }
}
