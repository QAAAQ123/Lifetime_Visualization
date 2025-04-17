package src;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel{
    public MainPanel(){

        //레이아웃 설정
        setLayout(new FlowLayout(FlowLayout.CENTER,200,180));

        //버튼 추가
        this.add(new JButton("나이 시각화"));
        this.add(new JButton("환경 설정"));
    }
}
