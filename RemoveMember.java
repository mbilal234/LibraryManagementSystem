package lms;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RemoveMember extends Component {
    public void check_username(String username){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from members");
            boolean username_correct = false;
            while(rs.next()){
                if (rs.getString(1).equals(username)){
                    username_correct = true;
                }
            }
            if (username_correct){
                int result = JOptionPane.showConfirmDialog(this,"Confirm Removal?", "Remove User",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    remove_member(username);
                }
            }else{
                JOptionPane.showMessageDialog(this, "User does not Exist!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            con.close();
        }catch(Exception e){
            System.out.println();
        }
    }

    public void remove_member(String username){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            String del = "DELETE FROM members WHERE username=?";
            PreparedStatement preparedStmt1 = con.prepareStatement(del);
            preparedStmt1.setString(1, username);
            preparedStmt1.execute();
            con.close();
            JOptionPane.showMessageDialog(this, "Member has been removed.");
        }catch(Exception e){
            System.out.println();
        }
    }
}
