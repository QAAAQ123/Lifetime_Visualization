package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.util.StringTokenizer;


//단위별로 계산하는 식 만들기
/*
공통: 현재 년/월 받아오기
달 12x100
1. '현재 연도-태어난 연도-1'만큼 1년(12개) 채운 동그라미를 넣어준다.
2. '현재 월'만큼 채운 동그라미를 넣어준다.
3. 나머지 전부를 빈 동그라미로 채운다.
연 10x10
1. '현재 연도-태어난 연도'로 나이 계산
2. '태어난 월-현재 월' 계산해서 음수이면 그대로, 0과 양수이면 -1을 해준다.
3. 1,2의 결과만큼 채운 동그라미를 넣고 '100-결과'에는 빈 동그라미를 넣어서 10x10을 완성한다.
10년 10x1
1.'현재 연도-태어난 연도'로 나이 계산
2. '태어난 월-현재 월' 계산해서 음수이면 그대로, 0과 양수이면 -1을 해준다.
3. 1,2의 결과를 10의 자리와 1의 자리로 분리해서 10의 자리는 채워진 동그라미를 적용하고
4. 1의 자리는 5초과 일때 채운 동그라미,5이하 일때 빈 동그라미로 완성한다.
 */
public class VisualizationPanel extends JPanel {
    private final String filledCircle = "⚫";
    private final String emptyCircle = "⚪";
    private final String filledSquare = "⬛";
    private final String emptySquare = "⬜";
    private JTextArea showing = new JTextArea();
    private JButton toMain;
    private JButton saveImg;

    public VisualizationPanel(String unit,String birth,MainFrame frame){
        setLayout(new FlowLayout(FlowLayout.LEFT,180,30));
        SettingPanel setting = new SettingPanel(frame);
        int[] value = setting.getLangAndShape();

        switch(value[0]){
            case 0:
                toMain = new JButton("메인으로 돌아가기");
                saveImg = new JButton("이미지로 저장하기");
                break;
            case 1:
                toMain = new JButton("back to main");
                saveImg = new JButton("save as an image");
        }

        this.add(toMain);
        this.add(saveImg);

        toMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.switchFromVisualizationToMain();
            }
        });

        saveImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                saveImage();
                if(value[0] == 0) JOptionPane.showMessageDialog(null, "이미지가 저장되었습니다.");
                else JOptionPane.showMessageDialog(null, "Image has been saved.");
            }
        });

        //시각화 로직
        //현재 날짜 받아와서 연도,월 찾기
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int currnetMonth = now.getMonthValue();

        //받아온 생년월일에서 연도,월 추출하기
        StringTokenizer stringTokenizer = new StringTokenizer(birth,"/");
        String[] inputBirth = new String[3];
        int splitOfBirth = 0;
        while(stringTokenizer.hasMoreTokens()) inputBirth[splitOfBirth++] = stringTokenizer.nextToken();

        int insertedMonth = 0;
        int insertedYear = 0;
        if(value[0] == 0) {
            insertedYear = Integer.parseInt(inputBirth[0]);
            insertedMonth = Integer.parseInt(inputBirth[1]);
        }
        else if(value[0] == 1){
            insertedYear = Integer.parseInt(inputBirth[2]);
            insertedMonth = Integer.parseInt(inputBirth[1]);
        }

        String filled = "";
        String empty = "";
        if(value[1] == 0) {
            filled = filledCircle;
            empty = emptyCircle;
        }else if(value[1] == 1){
            filled = filledSquare;
            empty = emptySquare;
        }

        //시각화 하기
        if(unit.equals("월") || unit.equals("month")){
            String[][] result = whenUnitIsMonth(currentYear,currnetMonth, insertedYear, insertedMonth,filled,empty);
            showing.setEditable(false);
            showing.setLineWrap(true);
            for(int i = 0;i < result.length; i++){
                for(int j = 0; j < result[0].length; j++){
                    showing.append(result[i][j]);
                }
                showing.append("\n");
            }
            JScrollPane jScrollPane = new JScrollPane(showing);
            jScrollPane.setPreferredSize(new Dimension(200,900));
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            this.add(jScrollPane);
            frame.setSize(500,1000);
            frame.setLocation(700,0);
            revalidate();
            repaint();
        }else if(unit.equals("년") || unit.equals("year")){
            String[][] result = whenUnitIsYear(currentYear,currnetMonth, insertedYear, insertedMonth,filled,empty);
            for(int i = 0;i < result.length; i++){
                for(int j = 0; j < result[0].length; j++){
                    showing.append(result[i][j]);
                }
                showing.append("\n");
            }
            this.add(showing);
            revalidate();
            repaint();
        }else if(unit.equals("10년") || unit.equals("10-year")){
            String[] result = whenUnitIsDecade(currentYear,currnetMonth, insertedYear, insertedMonth,filled,empty);
            for(String i:result){
                showing.append(i);
            }
            this.add(showing);
            revalidate();
            repaint();
        }else{
            JLabel errorMsg = new JLabel();
            if(value[0] == 0) errorMsg.setText("에러 발생");
            else errorMsg.setText("Error occurred");

            errorMsg.setSize(10,10);
            this.add(errorMsg);
            revalidate();
            repaint();
        }

        setVisible(true);
    }

    //단위가 월인 경우 시각화 함수
    private String[][] whenUnitIsMonth(int currentYear,int currentMonth,int inputYear,int inputMonth,String filled,String empty){
//        달 12x100
//        1. '현재 연도-태어난 연도-1'만큼 1년(12개) 채운 동그라미를 넣어준다.
//        2. '현재 월'만큼 채운 동그라미를 넣어준다.
//        3. 나머지 전부를 빈 동그라미로 채운다.
        String[][] visualization = new String[100][12];

        int filledCircleByYear = currentYear - inputYear - 1;
        int filledCircleByMonth = currentMonth;


        for(int i = 0;i < filledCircleByYear - 1; i++){
            for(int j = 0; j < visualization[0].length; j++){
                visualization[i][j] = filled;
            }
        }
        for(int i = 0; i < filledCircleByMonth; i++){
            visualization[filledCircleByYear -1 ][i] = filled;
        }
        for(int i = 0;i < visualization.length; i++){
            for(int j = 0; j < visualization[0].length; j++){
                if(visualization[i][j] == null)
                    visualization[i][j] = empty;
            }
        }
        return visualization;
    }

    //단위가 년인경우
    private String[][] whenUnitIsYear(int currentYear,int currentMonth,int inputYear,int inputMonth,String filled,String empty){
//        연 10x10
//        1. '현재 연도-태어난 연도'로 나이 계산
//        2. '태어난 월-현재 월' 계산해서 음수이면 그대로, 0과 양수이면 -1을 해준다.
//        3. 1,2의 결과만큼 채운 동그라미를 넣고 '100-결과'에는 빈 동그라미를 넣어서 10x10을 완성한다.
        String[][] visualization = new String[10][10];

        int age = currentYear - inputYear;
        if(inputMonth - currentMonth >= 0) age -= 1;
        int tens = age / 10;
        int ones = age % 10;

        for(int i = 0;i < tens; i++){
            for(int j = 0;j < visualization[0].length;j++){
                visualization[i][j] = filled;
            }
        }
        for(int i = 0;i < ones;i++){
            visualization[tens][i] = filled;
        }
        for(int i = 0; i < visualization.length; i++){
            for(int j = 0; j<visualization[0].length; j++){
                if(visualization[i][j] == null)
                    visualization[i][j] = empty;
            }
        }
        return visualization;
    }

    //단위가 10년인 경우
    private String[] whenUnitIsDecade(int currentYear,int currentMonth,int inputYear,int inputMonth,String filled,String empty){
//        10년 10x1
//        1.'현재 연도-태어난 연도'로 나이 계산
//        2. '태어난 월-현재 월' 계산해서 음수이면 그대로, 0과 양수이면 -1을 해준다.
//        3. 1,2의 결과를 10의 자리와 1의 자리로 분리해서 10의 자리는 채워진 동그라미를 적용하고
//        4. 1의 자리는 5초과 일때 채운 동그라미,5이하 일때 빈 동그라미로 완성한다.

        String[] visualization = new String[10];

        int age = currentYear - inputYear;
        if(inputMonth - currentMonth >= 0) age -= 1;
        int tens = age / 10;
        int ones = age % 10;

        //forEach문으로 바꾸기
        for(int i = 0; i <= tens;i++)
            visualization[i] = filled;
        if(ones > 5){
            visualization[ones] = filled;
            for(int i = tens + 1; i< visualization.length; i++)
                visualization[i] = empty;
        }else{
            for(int i = tens; i < visualization.length; i++)
                visualization[i] = empty;
        }

        return visualization;
    }

    public void switchFromDataCheckToVisualization(MainFrame frame, DataCheckPanel dataCheckPanel) {
        frame.remove(dataCheckPanel);
        frame.currentPanel = this;
        frame.add(frame.currentPanel);
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void saveImage(){
        BufferedImage image = new BufferedImage(showing.getWidth(),showing.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2 = image.createGraphics();
        showing.paint(g2);
        try{
            ImageIO.write(image, "png", new File(System.getProperty("user.home") + "/Downloads/LifeTime_Visualization.png"));
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}