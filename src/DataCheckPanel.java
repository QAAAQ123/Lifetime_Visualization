package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataCheckPanel extends JPanel {
    private JLabel titleLabel;
    private JLabel unitLabel;
    private JLabel birthLabel;
    private JButton toVisualization;
    private JButton toInputData;

    public DataCheckPanel(String unit,String birth,MainFrame frame) {
        setLayout(new FlowLayout(FlowLayout.LEFT,180,30));
        SettingPanel setting = new SettingPanel(frame);
        int[] value = setting.getLangAndShape();

        switch(value[0]) {
            case 0:
                titleLabel = new JLabel("입력하신 데이터를 확인하세요");

                unitLabel = new JLabel("선택된 단위: " + unit);
                birthLabel = new JLabel("생년월일: " + birth);

                toVisualization = new JButton("시각화 하기");
                toInputData = new JButton("다시 입력하기");
                break;
            case 1:
                titleLabel = new JLabel("check your inserted data");

                unitLabel = new JLabel("selected unit: " + unit);
                birthLabel = new JLabel("birthdate: " + birth);

                toVisualization = new JButton("visualization");
                toInputData = new JButton("insert again");
                break;
        }


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

        DataCheckPanel dataCheckPanel = this;
        toVisualization.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (CheckDateEffectiveness(birth,value[0])) {
                    String selectedUnit = unit;
                    String brithText = birth;
                    VisualizationPanel visualizationPanel = new VisualizationPanel(selectedUnit,brithText,frame);
                    frame.switchFromDataCheckToVisualization(visualizationPanel);


                }
                else{
                    if(value[0] == 0) titleLabel.setText("날짜 형식이 잘못되었습니다. 다시 입력하세요");
                    else titleLabel.setText("wrong date format. please type again");
                    remove(toVisualization);
                    repaint();
                }
            }
        });
    }


    public boolean CheckDateEffectiveness(String birth,int lang){
        String dateFormat = null;
        if(lang == 0) dateFormat = "yyyy/MM/dd";
        else dateFormat = "dd/MM/yyyy";
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
