package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//기준 단위 선택하기-JComboBox
//생년월일 입력 받기-외부 라이브러리
//2개 데이터 VisualizationPanel로 넘기기
public class InputDataPanel extends JPanel {

    public InputDataPanel(MainFrame frame){
        setLayout(new FlowLayout(FlowLayout.LEFT,180,30));

        JComboBox<String> unit = new JComboBox<String>(new String[] {"월","년","10년"});
        JPanel row1 = new JPanel(new FlowLayout());
        row1.add(new JLabel("단위"));
        row1.add(unit);

        //연,월,일 선택하는 컴포넌트 추가

        JButton visualizationBtn = new JButton("시각화 하기");

        JButton ToMain = new JButton("메인으로 돌아가기");

        //메인 panel에 개별 panel 부착
        this.add(row1);

        this.add(visualizationBtn);
        this.add(ToMain);

        //버튼 클릭시 메인 화면으로
        ToMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.switchFromInputDataToMainPanel();
            }
        });

        visualizationBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.switchFromInputDataToVisualizaiton();
            }
        });

        setVisible(true);
    }
}
