package lms;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

public final class Employee extends Person{
    private String jobTitle;
    File picAddress;

    public Employee(Image profilePic, String name, String username, String password, String gender, String email, String phone, int age, String jobTitle) {
        super(profilePic, name, username, password, gender, email, phone, age);
        this.jobTitle = jobTitle;
    }

    public Employee(File profilePic, String name, String username, String password, String gender, String email, String phone, int age, String jobTitle) {
        super(new ImageIcon(String.valueOf(profilePic)).getImage().getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH), name, username, password, gender, email, phone, age);
        this.jobTitle = jobTitle;
        this.picAddress = profilePic;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void add_employee_to_database(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            String sql = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, getUsername());
            preparedStmt.setString(2, getName());
            preparedStmt.setString(3, getPassword());
            preparedStmt.setString(4,getGender());
            preparedStmt.setInt(5, getAge());
            preparedStmt.setString(6, getJobTitle());
            preparedStmt.setString(7, getEmail());
            preparedStmt.setString(8, getPhone());
            FileInputStream profile_file = new FileInputStream("pic.png");
            preparedStmt.setBlob(9, profile_file);
            preparedStmt.execute();
            String sql_1 = "select * from attendance";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql_1);
            String sql_2 = "UPDATE attendance SET attendances = ? WHERE dates = ?";
            PreparedStatement preparedStatement1 = con.prepareStatement(sql_2);
            while (rs.next()){
                String update = rs.getString(2)+","+getUsername()+": P";
                preparedStatement1.setString(1, update);
                preparedStatement1.setString(2, rs.getString(1));
            }
            preparedStatement1.execute();
            if (getJobTitle().equals("Librarian") || getJobTitle().equals("Admin")) {
                String sql_3 = "INSERT INTO admin VALUES (?, ?, ?)";
                PreparedStatement preparedStmt_3 = con.prepareStatement(sql_3);
                preparedStmt_3.setString(1, getUsername());
                preparedStmt_3.setString(2, getPassword());
                preparedStmt_3.setBlob(3, profile_file);
                preparedStmt_3.execute();
            }
            con.close();
            UpdateEmployeeProfilePic updateEmployeeProfilePic = new UpdateEmployeeProfilePic();
            updateEmployeeProfilePic.convert_file_to_blob(picAddress,getUsername());
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "New Employee Added.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
