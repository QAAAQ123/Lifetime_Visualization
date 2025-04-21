package src;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    JPanel currentPanel;

    public MainFrame(){
        //초기 설정
        setTitle("Lifetime visualization");
        setSize(500,600);
        setLocationRelativeTo(null);
        setResizable(true); //UI에 문제 생길시 프레임 사이즈 고정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //아이콘 설정
        Toolkit kit =Toolkit.getDefaultToolkit();
        Image iconImg = kit.getImage("img/healthy-heart-icon.png");
        this.setIconImage(iconImg);

        //메인 패널 추가
        currentPanel = new MainPanel(this);
        this.add(currentPanel);

        setVisible(true);
    }

    //mouseEventAdapter 익명 클래스 안에 들어가는 패널 변경 함수

    public void switchFromMainPanelToInputDataPanel(){
        remove(currentPanel);
        currentPanel = new InputDataPanel(this);
        add(currentPanel);
        revalidate();
        repaint();
    }

    public void switchFromInputDataToMainPanel(){
        remove(currentPanel);
        currentPanel = new MainPanel(this);
        add(currentPanel);
        revalidate();
        repaint();
    }

    public void switchFromInputDataToDataCheck(DataCheckPanel dataCheckPanel) {
        remove(currentPanel);
        currentPanel = dataCheckPanel;
        add(currentPanel);
        revalidate();
        repaint();
    }

    public void switchFromDataCheckToInputData(){
        remove(currentPanel);
        currentPanel = new InputDataPanel(this);
        add(currentPanel);
        revalidate();
        repaint();
    }

    public void switchFromVisualizationToMain() {
        remove(currentPanel);
        currentPanel = new MainPanel(this);
        add(currentPanel);
        setSize(500,600);
        setLocationRelativeTo(null);
        revalidate();
        repaint();
    }

    public void switchFromMainToSetting(){
        remove(currentPanel);
        currentPanel = new SettingPanel(this);
        add(currentPanel);
        revalidate();
        repaint();
    }

    public void switchFromSettingToMain(){
        remove(currentPanel);
        currentPanel = new MainPanel(this);
        add(currentPanel);
        revalidate();
        repaint();
    }

    public void switchFromDataCheckToVisualization(VisualizationPanel visualizationPanel) {
        remove(currentPanel);
        currentPanel = visualizationPanel;
        add(currentPanel);
        revalidate();
        repaint();
    }
}
