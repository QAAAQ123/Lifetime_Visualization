package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//기준 단위 선택하기-JComboBox
//생년월일 입력 받기-외부 라이브러리
//2개 데이터 VisualizationPanel로 넘기기
public class InputDataPanel extends JPanel {
    final JComboBox<String> unit;
    JTextField birth;
    final JButton visualizationBtn;
    final JButton toMain;

    public InputDataPanel(MainFrame frame){
        setLayout(new FlowLayout(FlowLayout.LEFT,180,30));

        //단위 선택 컴포넌트
        unit = new JComboBox<String>(new String[] {"월","년","10년"});
        JPanel row1 = new JPanel(new FlowLayout());
        row1.add(new JLabel("단위"));
        row1.add(unit);

        //연,월,일 선택하는 컴포넌트 추가
        birth = new JTextField("yyyy/MM/dd 형식",8);
        JPanel row2 = new JPanel(new FlowLayout());
        row2.add(new JLabel("생년월일"));
        row2.add(birth);

        //시각화 데이터 확인 페이지로 이동 버튼
        visualizationBtn = new JButton("입력한 데이터 확인");

        //메인 페이지로 이동 버튼
        toMain = new JButton("메인으로 돌아가기");

        //메인 panel에 개별 panel 부착
        this.add(row1);
        this.add(row2);
        this.add(visualizationBtn);
        this.add(toMain);

        //버튼 클릭시 메인 화면으로
        toMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.switchFromInputDataToMainPanel();
            }
        });

        //버튼 클릭시 데이터 확인 화면으로 이동하며 2개 데이터 던짐
        visualizationBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedUnit = (String) unit.getSelectedItem();
                String birthText = birth.getText();

                DataCheckPanel data = new DataCheckPanel(selectedUnit, birthText, frame);

                frame.switchFromInputDataToDataCheck(data);
            }
        });

        setVisible(true);
    }


}
