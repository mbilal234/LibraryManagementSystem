package lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckEmployeeCredentials extends CheckCredentials{
    CheckEmployeeCredentials(String username){
        this.setUsername(username);
    }
    @Override
    public boolean username_in_database() {
        boolean username_in_db = false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from employees");
            while (rs.next()){
                if (rs.getString(1).equals(this.getUsername())){
                    username_in_db = true;
                    this.setPassword(rs.getString(3));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return username_in_db;
    }
}
