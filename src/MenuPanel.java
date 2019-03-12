import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Klasa MenuPanel wyswietla menu glowne.
 */

public class MenuPanel extends JFrame implements ActionListener {
    /**
     * tworzone sa przyciski start, najlepsze wyniki, pomoc i wyjscie
     */
    JButton StartButton = new JButton("START!");
    JButton BestScoresButton = new JButton("Najlepsze wyniki");
    JButton HelpButton = new JButton("Pomoc");
    JButton ExitButton = new JButton("Wyjscie");

    Config config;
    BestScores bestScores;

    /**
     * kontruktor klasy menuPanel
     * @param c - obiekt klasy Config
     * @param bS - obiekt klasy BestScores
     */
    public MenuPanel(Config c, BestScores bS) {

        config = c;
        bestScores = bS;

        setBounds(300,300,Config.getWidthMenu(), Config.getHeightMenu());


        setTitle("MenuPanel gry");
        setLayout(null);



        StartButton.setBounds(150,50,200,40);;
        StartButton.setVisible(true);
        BestScoresButton.setBounds(150,150,200,40);
        BestScoresButton.setVisible(true);
        HelpButton.setBounds(150,250,200,40);
        HelpButton.setVisible(true);
        ExitButton.setBounds(150,350,200,40);
        ExitButton.setVisible(true);

        add(StartButton);
        StartButton.addActionListener(this);
        add(BestScoresButton);
        BestScoresButton.addActionListener(this);
        add(HelpButton);
        HelpButton.addActionListener(this);
        add(ExitButton);
        ExitButton.addActionListener(this);
        setBackground(Color.blue);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == StartButton){
            NamePanel nazwagracz = new NamePanel(config,bestScores);
            nazwagracz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            nazwagracz.setVisible(true);

        }
        if(src == BestScoresButton){
            bestScores.updateBestScores("best_scores.txt");
            JOptionPane.showMessageDialog(this, bestScores.listofTheBest);
        }
        if(src == HelpButton){

            JOptionPane.showMessageDialog(this, Config.getInstruction());
        }
        if(src == ExitButton){
            dispose();
            System.exit(0);

        }
    }
}





