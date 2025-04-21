package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingPanel extends JPanel {
    private static int langIndex;
    private static int shapeIndex;

    private JPanel langPanel;
    private JComboBox<String> language;
    private JLabel langLabel;
    private JPanel shapePanel;
    private JComboBox<String> shape;
    private JLabel shapeLabel;
    private JButton savingBtn;
    private JButton toMainBtn;

    public SettingPanel(MainFrame frame){
        setLayout(new FlowLayout(FlowLayout.CENTER,200,30));

        int[] value = getLangAndShape();

        switch(value[0]){
            case 0:
                langPanel = new JPanel(new FlowLayout());
                language = new JComboBox<>(new String[] {"한국어","English"});
                langLabel = new JLabel("언어 설정");
                langPanel.add(langLabel);
                langPanel.add(language);

                shapePanel = new JPanel(new FlowLayout());
                shape = new JComboBox<>(new String[] {"⚫","⬛"});
                shapeLabel = new JLabel("시각화 도형 설정");
                shapePanel.add(shapeLabel);
                shapePanel.add(shape);

                savingBtn = new JButton("설정 저장하기");
                toMainBtn = new JButton("메인으로");
                break;
            case 1:
                langPanel = new JPanel(new FlowLayout());
                language = new JComboBox<>(new String[] {"한국어","English"});
                langLabel = new JLabel("language");
                langPanel.add(langLabel);
                langPanel.add(language);

                shapePanel = new JPanel(new FlowLayout());
                shape = new JComboBox<>(new String[] {"⚫","⬛"});
                shapeLabel = new JLabel("shape");
                shapePanel.add(shapeLabel);
                shapePanel.add(shape);

                savingBtn = new JButton("save setting");
                toMainBtn = new JButton("To main");
                break;
        }

        add(langPanel);
        add(shapePanel);
        add(savingBtn);
        add(toMainBtn);

        toMainBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.switchFromSettingToMain();
            }
        });

        savingBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                langIndex = language.getSelectedIndex();
                shapeIndex = shape.getSelectedIndex();

                if(langIndex == 1){
                    langLabel.setText("language");
                    shapeLabel.setText("shape");
                    savingBtn.setText("save setting");
                    toMainBtn.setText("To main");
                    revalidate();
                    repaint();
                }
                if(langIndex == 0){
                    langLabel.setText("언어 설정");
                    shapeLabel.setText("시각화 도형 설정");
                    savingBtn.setText("설정 저장하기");
                    toMainBtn.setText("메인으로");
                    revalidate();
                    repaint();
                }
            }
        });

        setVisible(true);
    }

    public int[] getLangAndShape(){
        return new int[] {langIndex,shapeIndex};
    }
}
