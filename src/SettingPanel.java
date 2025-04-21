package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingPanel extends JPanel {
    public SettingPanel(MainFrame frame){
        setLayout(new FlowLayout(FlowLayout.CENTER,200,30));

        JComboBox<String> language = new JComboBox<>(new String[] {"한국어","English"});
        JComboBox<String> shape = new JComboBox<>(new String[] {"⚫","⬛"});
        JButton savingBtn = new JButton("설정 저장하기");
        JButton toMainBtn = new JButton("메인으로");

        JPanel langPanel = new JPanel(new FlowLayout());
        langPanel.add(new JLabel("언어 설정"));
        langPanel.add(language);

        JPanel shapePanel = new JPanel(new FlowLayout());
        shapePanel.add(new JLabel("시각화 도형 설정"));
        shapePanel.add(shape);

        add(langPanel);
        add(shapePanel);
        add(savingBtn);
        add(toMainBtn);

        toMainBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.switchFromSettingToMain();
            }
        });

        setVisible(true);
    }
}
