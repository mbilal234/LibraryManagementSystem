package lms;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

public class AddMemberPanel extends JPanel implements ActionListener {
    UtilDateModel model;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    RoundedPanel credentialsPanel;
    JLabel name, username, phoneNumber, email, birthday, gender, profilePic;
    JTextField nameField, usernameField, phoneField, emailField;
    JButton backButton, uploadPic, add, cancel;
    JComboBox gendersComboBox;
    File profile;

    public AddMemberPanel() {
        setLayout(null);
        setBounds(0, 0, 1000, 700);
        setBackground(new Color(254, 253, 209));

        credentialsPanel = new RoundedPanel(null, 10, new Color(171, 102, 41));
        credentialsPanel.setBounds(150, 150, 700, 400);

        name = new JLabel("Name:");
        name.setBounds(100, 20, 150, 25);
        username = new JLabel("Username:");
        username.setBounds(100, 60, 150, 25);
        birthday = new JLabel("D.O.B:");
        birthday.setBounds(100, 100, 150, 25);
        phoneNumber = new JLabel("Phone Number:");
        phoneNumber.setBounds(100, 140, 150, 25);
        email = new JLabel("Email ID:");
        email.setBounds(100, 180, 150, 25);
        gender = new JLabel("Gender:");
        gender.setBounds(100, 220, 150, 25);
        profilePic = new JLabel("Upload Picture:");
        profilePic.setBounds(100, 260, 150, 25);

        nameField = new JTextField();
        nameField.setBounds(250, 20, 350,25);
        usernameField = new JTextField();
        usernameField.setBounds(250, 60, 350,25);
        phoneField = new JTextField();
        phoneField.setBounds(250, 140, 350,25);
        emailField = new JTextField();
        emailField.setBounds(250, 180, 350,25);

        for (JTextField textField : new JTextField[]{nameField, usernameField, phoneField, emailField}) {
            credentialsPanel.add(textField);
        }

        gendersComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        gendersComboBox.setBounds(250, 220, 350, 25);
        credentialsPanel.add(gendersComboBox);

        add = new JButton("Add");
        add.setBounds(200, 320, 100, 30);
        add.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.setBounds(400, 320, 100, 30);
        cancel.addActionListener(this);
        credentialsPanel.add(add);
        credentialsPanel.add(cancel);

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model,p);
        datePicker = new JDatePickerImpl(datePanel, new LabelDateFormatter());
        datePicker.setBounds(250, 100, 350, 25);
        credentialsPanel.add(datePicker);

        for (JLabel label : new JLabel[]{name, username, birthday, phoneNumber, email, gender, profilePic}) {
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setForeground(new Color(254, 253, 209));
            label.setOpaque(false);
            credentialsPanel.add(label);
        }

        uploadPic = new JButton("Upload");
        uploadPic.setBounds(250, 260, 350, 25);
        uploadPic.addActionListener(this);
        credentialsPanel.add(uploadPic);

        backButton = new JButton("<<< Back");
        backButton.setBounds(40, 40, 100, 30);
        backButton.addActionListener(this);

        add(backButton);
        add(credentialsPanel);
    }

    public void resetFields() {
        for (JTextField jTextField: new JTextField[]{nameField, usernameField, phoneField, emailField}) {
            jTextField.setText("");
        }
    }

    public int calculateAge() {
        Date selectedDate = (Date) datePicker.getModel().getValue();
        Period period = Period.between(selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());

        return period.getYears();
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NullPointerException{
        if (e.getSource().equals(backButton) || e.getSource().equals(cancel)) {
            App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            f3.view_members();
        }
        if (e.getSource().equals(uploadPic)) {
            try {
                UpdateMemberProfilePic pic = new UpdateMemberProfilePic();
                pic.chooseFile();
                if (!pic.profile.equals(null)) profile = pic.profile;
            } catch (NullPointerException exception){}
        }
        if (e.getSource().equals(add)) {
            Member member = new Member(profile, nameField.getText(), usernameField.getText(), "SEECS@123", String.valueOf(gendersComboBox.getSelectedItem()),
                    emailField.getText(), phoneField.getText(),calculateAge());
            member.add_member_to_database();

        }
    }
}
