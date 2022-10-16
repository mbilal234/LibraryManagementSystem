package lms;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AcquireMemberCredentials {
    private String username, password, name, book_issued_1, book_issued_1_genre, book_issued_2, book_issued_2_genre, book_issued_3, book_issued_3_genre, gender, email, phone;
    private int age;
    private Image profile_pic;
    Member member;

    AcquireMemberCredentials(String username){
        this.username = username;
    }

    public void getMemberData(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from members");
            while (rs.next()){
                if (rs.getString(1).equals(username)){
                    this.password = rs.getString(2);
                    this.name = rs.getString(3);
                    this.book_issued_1 = rs.getString(6);
                    this.book_issued_1_genre = rs.getString(7);
                    this.book_issued_1 = rs.getString(8);
                    this.book_issued_1 = rs.getString(9);
                    this.book_issued_1 = rs.getString(10);
                    this.book_issued_1 = rs.getString(11);
                    this.gender = rs.getString(22);
                    this.email = rs.getString(23);
                    this.phone = rs.getString(24);
                    this.age = rs.getInt(4);
                    ImageIcon profile = new ImageIcon(rs.getBlob(5).getBytes(1l, (int)rs.getBlob(5).length()));
                    this.profile_pic = profile.getImage().getScaledInstance(120, 170, Image.SCALE_DEFAULT);
                    this.member = new Member(this.profile_pic, this.name, this.username, this.password, this.gender, this.email, this.phone, this.age, this.book_issued_1, this.book_issued_1_genre, this.book_issued_2, this.book_issued_2_genre, this.book_issued_3, this.book_issued_3_genre);
                }
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBook_issued_1() {
        return book_issued_1;
    }

    public void setBook_issued_1(String book_issued_1) {
        this.book_issued_1 = book_issued_1;
    }

    public String getBook_issued_1_genre() {
        return book_issued_1_genre;
    }

    public void setBook_issued_1_genre(String book_issued_1_genre) {
        this.book_issued_1_genre = book_issued_1_genre;
    }

    public String getBook_issued_2() {
        return book_issued_2;
    }

    public void setBook_issued_2(String book_issued_2) {
        this.book_issued_2 = book_issued_2;
    }

    public String getBook_issued_2_genre() {
        return book_issued_2_genre;
    }

    public void setBook_issued_2_genre(String book_issued_2_genre) {
        this.book_issued_2_genre = book_issued_2_genre;
    }

    public String getBook_issued_3() {
        return book_issued_3;
    }

    public void setBook_issued_3(String book_issued_3) {
        this.book_issued_3 = book_issued_3;
    }

    public String getBook_issued_3_genre() {
        return book_issued_3_genre;
    }

    public void setBook_issued_3_genre(String book_issued_3_genre) {
        this.book_issued_3_genre = book_issued_3_genre;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Image getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(Image profile_pic) {
        this.profile_pic = profile_pic;
    }
}
