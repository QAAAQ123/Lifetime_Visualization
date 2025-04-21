package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//기준 단위 선택하기-JComboBox
//생년월일 입력 받기-외부 라이브러리
//2개 데이터 VisualizationPanel로 넘기기
public class InputDataPanel extends JPanel {
    private JComboBox<String> unit;
    private JTextField birth;
    private JButton visualizationBtn;
    private JButton toMain;
    private JPanel row1;
    private JPanel row2;

    public InputDataPanel(MainFrame frame){
        setLayout(new FlowLayout(FlowLayout.LEFT,180,30));

        SettingPanel setting = new SettingPanel(frame);
        int[] value = setting.getLangAndShape();


        switch(value[0]){
            case 0:
                //단위 선택 컴포넌트
                unit = new JComboBox<String>(new String[] {"월","년","10년"});
                row1 = new JPanel(new FlowLayout());
                row1.add(new JLabel("단위"));
                row1.add(unit);

                //연,월,일 선택하는 컴포넌트 추가
                birth = new JTextField("yyyy/MM/dd 형식",8);
                row2 = new JPanel(new FlowLayout());
                row2.add(new JLabel("생년월일"));
                row2.add(birth);

                //시각화 데이터 확인 페이지로 이동 버튼
                visualizationBtn = new JButton("입력한 데이터 확인");

                //메인 페이지로 이동 버튼
                toMain = new JButton("메인으로 돌아가기");
                break;
            case 1:
                //단위 선택 컴포넌트
                unit = new JComboBox<String>(new String[] {"month","year","10-year"});
                row1 = new JPanel(new FlowLayout());
                row1.add(new JLabel("unit"));
                row1.add(unit);

                //연,월,일 선택하는 컴포넌트 추가
                birth = new JTextField("dd/MM/yyyy format",12);
                row2 = new JPanel(new FlowLayout());
                row2.add(new JLabel("birthdate"));
                row2.add(birth);

                //시각화 데이터 확인 페이지로 이동 버튼
                visualizationBtn = new JButton("checking inserted data");

                //메인 페이지로 이동 버튼
                toMain = new JButton("to main");
                break;
        }


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
