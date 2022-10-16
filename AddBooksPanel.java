package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddBooksPanel extends JPanel implements ActionListener {
    RoundedPanel fieldsPanel;
    JLabel title, author, publisher, genres, damageLabel, isbnLabel, bookCoverLabel;
    JTextField titleField, authorField, publisherField, genreField, damageField, isbnField;
    JButton backButton, uploadcover, add, cancel;
    File profile;

    public AddBooksPanel() {
        setLayout(null);
        setBounds(0, 0, 1000, 700);
        setBackground(new Color(254, 253, 209));

        fieldsPanel = new RoundedPanel(null, 10, new Color(171, 102, 41));
        fieldsPanel.setBounds(150, 150, 700, 400);

        title = new JLabel("Title:");
        title.setBounds(100, 20, 150, 25);
        author = new JLabel("Author:");
        author.setBounds(100, 60, 150, 25);
        publisher = new JLabel("Publisher:");
        publisher.setBounds(100, 100, 150, 25);
        genres = new JLabel("Phone Number:");
        genres.setBounds(100, 140, 150, 25);
        damageLabel = new JLabel("Damage Level:");
        damageLabel.setBounds(100, 180, 150, 25);
        isbnLabel = new JLabel("ISBN-10 Number:");
        isbnLabel.setBounds(100, 220, 150, 25);
        bookCoverLabel = new JLabel("Upload Cover:");
        bookCoverLabel.setBounds(100, 260, 150, 25);

        for (JLabel label : new JLabel[]{title, author, publisher, genres, damageLabel, isbnLabel, bookCoverLabel}) {
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setForeground(new Color(254, 253, 209));
            label.setOpaque(false);
        }

        titleField = new JTextField();
        titleField.setBounds(250, 20, 350,25);
        authorField = new JTextField();
        authorField.setBounds(250, 60, 350,25);
        publisherField = new JTextField();
        publisherField.setBounds(250, 100, 350,25);
        genreField = new JTextField();
        genreField.setBounds(250, 140, 350,25);
        damageField = new JTextField();
        damageField.setBounds(250, 180, 350,25);
        isbnField = new JTextField();
        isbnField.setBounds(250, 220, 350,25);

        add = new JButton("Add");
        add.setBounds(200, 320, 100, 30);
        add.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.setBounds(400, 320, 100, 30);
        cancel.addActionListener(this);

        uploadcover = new JButton("Upload");
        uploadcover.setBounds(250, 260, 350, 25);
        uploadcover.addActionListener(this);

        for (Component component: new Component[]{title, author, publisher, genres, damageLabel, isbnLabel, bookCoverLabel,
            titleField, authorField, publisherField, genreField, damageField, isbnField, add, cancel, uploadcover}) {
            fieldsPanel.add(component);
        }

        backButton = new JButton("<<< Back");
        backButton.setBounds(40, 40, 100, 30);
        backButton.addActionListener(this);

        add(backButton);
        add(fieldsPanel);
    }

    public void resetFields() {
        for (JTextField jTextField: new JTextField[]{titleField, authorField, publisherField, genreField, damageField, isbnField}) {
            jTextField.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NullPointerException{
        if (e.getSource().equals(backButton) || e.getSource().equals(cancel)) {
            App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            f3.open_search_panel();
            resetFields();
        }
        if (e.getSource().equals(uploadcover)) {
            try {
//                UpdateMemberProfilePic pic = new UpdateMemberProfilePic();
//                pic.chooseFile();
//                if (!pic.profile.equals(null)) profile = pic.profile;
            } catch (NullPointerException exception){}
        }
        if (e.getSource().equals(add)) {
            Book book = new Book(null,titleField.getText(),isbnField.getText(),authorField.getText(),publisherField.getText(),
                    genreField.getText(), Double.parseDouble(damageField.getText()), "No One");
        }
    }
}