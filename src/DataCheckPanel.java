package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataCheckPanel extends JPanel {

    public DataCheckPanel(String unit,String birth,MainFrame frame) {
        setLayout(new FlowLayout(FlowLayout.LEFT,180,30));

        JLabel titleLabel = new JLabel("입력하신 데이터를 확인하세요");

        JLabel unitLabel = new JLabel("선택된 단위: " + unit);
        JLabel birthLabel = new JLabel("생년월일: " + birth);

        JButton toInputData = new JButton("다시 입력하기");
        JButton toVisualization = new JButton("시각화 하기");

        this.add(titleLabel);
        this.add(unitLabel);
        this.add(birthLabel);
        this.add(toInputData);
        this.add(toVisualization);

        toInputData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.switchFromDataCheckToInputData();
            }
        });

        toVisualization.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (CheckDateEffectiveness(birth)) {
                    frame.switchFromDataCheckToVisualization();
                    String selectedUnit = unit;
                    String brithText = birth;

                    VisualizationPanel data = new VisualizationPanel(selectedUnit,brithText,frame);
                }
                else{
                    titleLabel.setText("날짜 형식이 잘못되었습니다. 다시 입력하세요");
                    remove(toVisualization);
                    repaint();
                }
            }
        });
    }


    public boolean CheckDateEffectiveness(String birth){
        String dateFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        //birth 형식이 날짜 형식에 맞지 않으면 넘어가지 않고 label바뀌면서 경고
        // birth 형식이 맞으면 시각화 화면으로 넘어감
        if(dateFormat.length() != birth.length()) {
            return false;
        }
        try{
            sdf.setLenient(false);
            sdf.parse(birth);
            return true;
        } catch(ParseException e){
            return false;
        }

    }
}
