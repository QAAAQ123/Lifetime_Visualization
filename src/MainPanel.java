package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class MainPanel extends JPanel{
    private JButton lifeTimeBtn;
    private JButton settingBtn;
    public MainPanel(MainFrame frame){

        //레이아웃 설정
        setLayout(new FlowLayout(FlowLayout.CENTER,20,18));
        SettingPanel setting = new SettingPanel(frame);

        //설정에 따라 한국어 영어 적용
        int[] value = setting.getLangAndShape();

        switch(value[0]){
            case 0:
                lifeTimeBtn = new JButton("나이 시각화");
                settingBtn = new JButton("환경설정");
                break;
            case 1:
                lifeTimeBtn = new JButton("Lifetime Visualization");
                settingBtn = new JButton("setting");
                break;
        }

        //버튼 추가
        add(lifeTimeBtn);
        add(settingBtn);


        lifeTimeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.switchFromMainPanelToInputDataPanel();
            }
        });

        settingBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.switchFromMainToSetting();
            }
        });
    }
}
