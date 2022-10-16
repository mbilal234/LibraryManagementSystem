package lms;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DynamicSearch {
    ArrayList<Book> books;
    ArrayList<Member> members;
    ArrayList<Employee> employees;

    public void fetch_books(String searchText){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from books");
            books = new ArrayList<Book>();
            while (rs.next() && books.size() <= 20){
                ImageIcon book_cover = new ImageIcon(rs.getBlob(3).getBytes(1l, (int)rs.getBlob(3).length()));
                Image cover = book_cover.getImage().getScaledInstance(120, 170, Image.SCALE_DEFAULT);
                String title = rs.getString(2);
                String isbn = String.valueOf(rs.getInt(1));
                String author = rs.getString(4);
                String publisher = rs.getString(5);
                String genre = rs.getString(6);
                double damage_level = rs.getInt(7);
                String issued_by = rs.getString(8);
                String text = searchText.toLowerCase();
                if (title.toLowerCase().contains(text) || isbn.toLowerCase().contains(text) || author.toLowerCase().contains(text) || genre.toLowerCase().contains(text)) {
                    books.add(new Book(cover, title, isbn, author, publisher, genre, damage_level, issued_by));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchEmployees(String input) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false",
                    "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from employees");
            employees = new ArrayList<>();
            while (rs.next()){
                if(rs.getString(1).toLowerCase().contains(input.toLowerCase()) ||
                        rs.getString(2).toLowerCase().contains(input.toLowerCase())){
                    String username = rs.getString(1);
                    String name = rs.getString(2);
                    String password = rs.getString(3);
                    String gender = rs.getString(4);
                    int age = rs.getInt(5);
                    String jobTitle = rs.getString(6);
                    String email = rs.getString(7);
                    String phoneNumber = rs.getString(8);
                    ImageIcon profile = new ImageIcon(rs.getBlob(9).getBytes(1l,
                            (int)rs.getBlob(3).length()));
                    Image profilePic = profile.getImage().getScaledInstance(120, 170, Image.SCALE_DEFAULT);

                    employees.add(new Employee(profilePic, name, username, password, gender, email, phoneNumber, age,
                            jobTitle));
                }
            }
            con.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchMembers(String input){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from members");
            members = new ArrayList<>();
            while (rs.next()){
                if(rs.getString(1).toLowerCase().contains(input.toLowerCase()) || rs.getString(3).toLowerCase().contains(input.toLowerCase())){
                    String username = rs.getString(1);
                    String password = rs.getString(2);
                    String name = rs.getString(3);
                    int age = rs.getInt(4);
                    ImageIcon profile = new ImageIcon(rs.getBlob(5).getBytes(1l, (int)rs.getBlob(3).length()));
                    Image profilePic = profile.getImage().getScaledInstance(120, 170, Image.SCALE_DEFAULT);
                    String book1 = rs.getString(16);
                    String book1_genres = rs.getString(17);
                    String book2 = rs.getString(18);
                    String book2_genres = rs.getString(19);
                    String book3 = rs.getString(20);
                    String book3_genres = rs.getString(21);
                    String gender = rs.getString(22);
                    String email = rs.getString(23);
                    String phoneNumber = rs.getString(24);
                    members.add(new Member(profilePic, name, username, password, gender, email, phoneNumber, age, book1, book2, book3, book1_genres, book2_genres,book3_genres));
                }
            }
            con.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
