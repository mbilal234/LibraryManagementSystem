package lms;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateMemberProfilePic extends Component implements BlobConverter{
    File profile;

    public void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            profile = new File(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    public void convert_file_to_blob(File file, String username){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "abdularham123");
            String update = "UPDATE member SET member_profile_pic = ? WHERE username = ?";
            CheckMemberCredentials memberCredentials = new CheckMemberCredentials(username);
            FileInputStream new_profile = new FileInputStream(file);
            PreparedStatement preparedstmt2 = con.prepareStatement(update);
            preparedstmt2.setBlob(5, new_profile);
            preparedstmt2.setString(1, memberCredentials.getUsername());
            preparedstmt2.executeUpdate();
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Invalid File.",
                    "Upload Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

