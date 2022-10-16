package lms;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MessageDisplayPanel extends JPanel {
    ArrayList<String> from_users;
    ArrayList<String> requests;
    ArrayList<String> to_users;
    ArrayList<String> messages;

    public MessageDisplayPanel(int control) {
        setBackground(new Color(116, 67, 37));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),"MESSAGE REQUESTS", TitledBorder.LEFT, TitledBorder.ABOVE_TOP,new Font("Arial", Font.BOLD, 20), new Color(253, 255, 247)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(150, 300, 700, 200);

        fetchRequests();
        fetchMessages();

        if(control == 1) {
            if(to_users.isEmpty()) {
                JLabel noMessages = new JLabel("No new messages...");
                noMessages.setLayout(new BoxLayout(noMessages, BoxLayout.Y_AXIS));
                noMessages.setFont(new Font("Arial", Font.PLAIN, 18));
                noMessages.setForeground(new Color(254, 253, 209));
                noMessages.setAlignmentX(CENTER_ALIGNMENT);
                noMessages.setOpaque(false);
                add(noMessages);
            }
            else {
                for (int i = 0; i < to_users.size(); i++) {
                    //add(MessageLine(to_users.get(i), messages.get(i)));
                }
            }
        }
        else if (control == 0) {
            if(from_users.isEmpty()) {
                JLabel noMessages = new JLabel("No new messages...");
                noMessages.setLayout(new BoxLayout(noMessages, BoxLayout.Y_AXIS));
                noMessages.setFont(new Font("Arial", Font.PLAIN, 18));
                noMessages.setForeground(new Color(254, 253, 209));
                noMessages.setAlignmentX(CENTER_ALIGNMENT);
                noMessages.setOpaque(false);
                add(noMessages);
            }
            else {
                for (int i = 0; i < from_users.size(); i++) {
                    //add(MessageLine(from_users.get(i), requests.get(i)));
                }
            }
        }
    }

    public void fetchMessages() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from messages");

            to_users = new ArrayList<>();
            messages = new ArrayList<>();
            while (rs.next()){
                messages.add(rs.getString(1));
                to_users.add(rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void fetchRequests() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from requests");

            from_users = new ArrayList<>();
            requests = new ArrayList<>();
            while (rs.next()){
                requests.add(rs.getString(1));
                from_users.add(rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JPanel MessageLine(String user, String request) {
        JPanel line = new JPanel();
        GroupLayout gp = new GroupLayout(line);
        gp.setAutoCreateGaps(true);
        gp.setAutoCreateContainerGaps(true);
        line.setLayout(gp);
        line.setOpaque(false);

        JLabel userLabel = new JLabel(user);
        JLabel requestLabel = new JLabel(request);

        gp.setHorizontalGroup(gp.createSequentialGroup().addComponent(userLabel).addComponent(requestLabel));
        gp.setHorizontalGroup(gp.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(userLabel).addComponent(requestLabel));

        return line;
    }
}
