package my_domain.numerical_analysis.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MjForm {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel matrixInputPanel;

    private MjForm(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("title");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 400));

        mainPanel = new JPanel();
        frame.setContentPane(mainPanel);
        matrixInputPanel = new JPanel(new SpringLayout());
        {
            int tx = 200, ty = 200;
            matrixInputPanel.setPreferredSize(new Dimension(tx, ty));
            matrixInputPanel.setMinimumSize(new Dimension(tx, ty));
            matrixInputPanel.setMaximumSize(new Dimension(tx, ty));
            matrixInputPanel.setSize(new Dimension(tx, ty));
        }
        frame.setLayout(new FlowLayout());

        mainPanel.add(matrixInputPanel, FlowLayout.LEFT);

        frame.pack();
        frame.setVisible(true);

        resize();
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resize();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void resize() {
        int rows, cols;
        try(Scanner sc = new Scanner(new FileReader("input.txt"))){
            rows = sc.nextInt();
            cols = sc.nextInt();
        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(1);
            return;
        }

        matrixInputPanel.removeAll();
        for(int i = 1; i<=rows; i++)
            for(int j = 1; j<=cols; j++)
                matrixInputPanel.add(new JTextField(i + " , " + j, 8));
        System.out.println(rows * cols + " buttons added");

        SpringUtilities.makeCompactGrid(matrixInputPanel, rows, cols, 0, 0, 0, 0);

        frame.repaint();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new MjForm();
    }
}