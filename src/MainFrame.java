package src;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    public MainFrame(){
        //초기 설정
        setTitle("Lifetime visualization");
        setSize(800,600);
        setLocation(550,250);
        setResizable(true); //UI에 문제 생길시 프레임 사이즈 고정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //아이콘 설정
        Toolkit kit =Toolkit.getDefaultToolkit();
        Image iconImg = kit.getImage("img/healthy-heart-icon.png");
        this.setIconImage(iconImg);

        //패널 추가
        MainPanel mainPanel = new MainPanel();
        this.add(mainPanel);

        setVisible(true);
    }
}
