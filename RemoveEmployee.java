package lms;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RemoveEmployee extends Component {

    public void check_username(String username){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from employees");
            boolean username_correct = false;
            while(rs.next()){
                if (rs.getString(1).equals(username)){
                    username_correct = true;
                }
            }
            if (username_correct){
                int result = JOptionPane.showConfirmDialog(this,"Confirm Removal?", "Remove Employee",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    remove_employee(username);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Employee does not Exist!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            con.close();
        }catch(Exception e){
            System.out.println();
        }
    }

    public void remove_employee(String username){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            String sql = "DELETE FROM employees WHERE username = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.execute();
            String sql_2 = "select * from attendance";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql_2);
            String sql_3 = "UPDATE attendance SET attendances = ? WHERE dates = ?";
            PreparedStatement preparedStatement1 = con.prepareStatement(sql_3);
            while (rs.next()){
                String update="";
                if (rs.getString(2).contains(","+username+": P")){
                    update = rs.getString(2).replace(","+username+": P", "");
                }else if (rs.getString(2).contains(","+username+": A")){
                    update = rs.getString(2).replace(","+username+": A", "");
                }
                preparedStatement1.setString(1, update);
                preparedStatement1.setString(2, rs.getString(1));
            }
            preparedStatement1.execute();
            con.close();
            JOptionPane.showMessageDialog(this, "Employee has been removed.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

