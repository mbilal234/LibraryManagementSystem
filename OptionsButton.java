package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsButton extends JButton implements ActionListener {
    MoreOptionsPanel moreOptionsPanel;
    Timer timer;

    public OptionsButton (MoreOptionsPanel optionsPanel) {
        super("⁞");
        this.moreOptionsPanel = optionsPanel;
        setBounds(0,0,30,30);
        setFocusable(false);
        setBackground(new Color(169, 109, 20));
        setForeground(Color.white);
        setFont(new Font("Arial", Font.BOLD, 15));
        addActionListener(this);
//        timer = new Timer(13, this::actionPerformed);
//        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (moreOptionsPanel.getX() == -120) {
            while (true) {
                moreOptionsPanel.setLocation(moreOptionsPanel.getX()+5 ,moreOptionsPanel.getY());
                if (moreOptionsPanel.getX()==0) break;
            }

        } else if (moreOptionsPanel.getX() == 0) {
            while (true) {
                moreOptionsPanel.setLocation(moreOptionsPanel.getX()-5 ,moreOptionsPanel.getY());
                if (moreOptionsPanel.getX()==-120) break;
            }
        }
    }
}
