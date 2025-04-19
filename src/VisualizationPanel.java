package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisualizationPanel extends JPanel {
    public VisualizationPanel(String unit,String birth,MainFrame frame){
        setLayout(new FlowLayout(FlowLayout.LEFT,180,30));

        JLabel test = new JLabel(unit + birth);
        JButton toMain = new JButton("메인으로 돌아가기");

        toMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.switchFromVisualizationToMain();
            }
        });


    }
}
