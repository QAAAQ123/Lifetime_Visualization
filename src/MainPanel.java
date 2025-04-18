package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainPanel extends JPanel{
    public MainPanel(MainFrame frame){

        //레이아웃 설정
        setLayout(new FlowLayout(FlowLayout.CENTER,20,18));

        //버튼 추가
        JButton lifeTimeBtn = new JButton("나이 시각화");
        JButton settingBtn = new JButton("환경설정");
        add(lifeTimeBtn);
        add(settingBtn);

        lifeTimeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.switchFromMainPanelToInputDataPanel();
            }
        });
    }
}
