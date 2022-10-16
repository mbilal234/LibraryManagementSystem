package lms;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class SearchWindowPanel extends JPanel implements FocusListener, ActionListener {
    int control = 0;
    JPanel search, bookDisplay;
    JScrollPane scrolls;
    JTextField searchbar;
    JButton searchButton, addBooks;
    MoreOptionsPanel optionsPanel;
    OptionsButton optionsButton;

    public SearchWindowPanel() {
        setLayout(null);
        setBounds(0, 0, 1000, 700);

        optionsPanel = new MoreOptionsPanel();

        optionsButton = new OptionsButton(optionsPanel);

        ImageIcon add = new ImageIcon("add.png");
        Image addImg = add.getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
        addBooks = new JButton(new ImageIcon(addImg));
        addBooks.setBackground(new Color(254, 253, 209));
        addBooks.setBounds(960, 0, 25, 25);
        addBooks.addActionListener(this);

        search = new JPanel();
        search.setBounds(0,0,1000,80);
        search.setBackground(new Color(136, 85, 61));

        bookDisplay = new JPanel();
        bookDisplay.setLayout(new BoxLayout(bookDisplay, BoxLayout.Y_AXIS));
        bookDisplay.setLocation(0,80);
        bookDisplay.setSize(985, 600);
        bookDisplay.setBackground(new Color(253, 255, 247));

        scrolls = new JScrollPane(bookDisplay);
        scrolls.setSize(985, 585);
        scrolls.setLocation(0,80);
        scrolls.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrolls.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolls.getVerticalScrollBar().setUnitIncrement(16);

        searchbar = new JTextField("Title, Author, ISBN, or Genre");
        searchbar.setForeground(Color.GRAY);
        searchbar.setPreferredSize(new Dimension(300, 28));
        searchbar.addFocusListener(new FocusListener() {
                                        public void focusGained(FocusEvent e) {
                                            if (searchbar.getText().equals("Title, Author, ISBN, or Genre")) {
                                                searchbar.setText("");
                                                searchbar.setForeground(Color.BLACK);
                                            }
                                        }
                                        public void focusLost(FocusEvent e) {
                                            if (searchbar.getText().isEmpty()) {
                                                searchbar.setForeground(Color.GRAY);
                                                searchbar.setText("Title, Author, ISBN, or Genre");
                                            }
                                        }
                                    });
        searchbar.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                try {
                    updateSearch();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            public void removeUpdate(DocumentEvent e) {
                try {
                    updateSearch();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            public void insertUpdate(DocumentEvent e) {
                try {
                    updateSearch();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            public void updateSearch() throws IOException {
                bookDisplay.removeAll();
                bookDisplay.updateUI();
                DynamicSearch data = new DynamicSearch();
                data.fetch_books(searchbar.getText());
                for (Book book : data.books) {
                    if (control == 0) {
                        AdminBookPanel bookPanel = new AdminBookPanel(book);
                        bookPanel.setAlignmentX(CENTER_ALIGNMENT);
                        bookDisplay.add(Box.createRigidArea(new Dimension(400, 20)));
                        bookDisplay.add(bookPanel);
                    } else {
                        BookPanel bookPanel = new BookPanel(book);
                        bookPanel.setAlignmentX(CENTER_ALIGNMENT);
                        bookDisplay.add(Box.createRigidArea(new Dimension(400, 20)));
                        bookDisplay.add(bookPanel);
                    }
                }
            }
        });
        search.add(Box.createRigidArea(new Dimension(1000, 15)));
        search.add(searchbar);

        searchButton = new JButton("Search");
        search.add(searchButton);
    }

    public void setAdminBookSearch() {
        removeAll();
        updateUI();
        control = 0;
        optionsPanel.set_admin_options();
        add(addBooks);
        add(optionsPanel);
        add(optionsButton);
        add(search);
        add(scrolls);
    }

    public void setMemberBookSearch() {
        removeAll();
        updateUI();
        control = 1;
        optionsPanel.set_member_options();
        add(optionsPanel);
        add(optionsButton);
        add(search);
        add(scrolls);
    }

    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addBooks)) {
            App f3 = (App) SwingUtilities.getAncestorOfClass(JFrame.class, this);
            f3.add_books();
        }
    }
}
