import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Klasa odpowiedzialna za ramke do ktorej mozemy wpisac nick gracza
 */
public class NamePanel extends JFrame implements ActionListener {
    /**
     * String przechowujacy nick gracza
     */
    String name;

    /**
     * tworzone sa przyciski "graj" oraz "wyjscie" oraz JTextField do ktorego bedzie mogl wpisac uzytkownik nick gracza
     */
    JButton Game = new JButton("Graj!");
    JButton Exit = new JButton("Wyjscie");
    JTextField NamePlayer = new JTextField("");
    Config config;
    BestScores bestScores;

    /**
     * konstruktor klasy NamePanel
     * @param c - obiekt klasy Config
     * @param bS - obiekt klasy BestScores
     */
    public NamePanel(Config c, BestScores bS){

        config = c;
        bestScores = bS;


        JLabel information  = new JLabel("Podaj swoj nick: ");

        setSize(400,200);
        setTitle("Podaj nazwe gracza");
        setBounds(350,400,400, 300);
        setLayout(null);

        Font font = new Font("SanSerif", Font.ITALIC, 25);

        /**
         * Opis panelu
         */

        information.setFont(font);
        information.setBounds(100,10,400,150);
        information.setVisible(true);
        add(information);


        NamePlayer.setBounds(150,110,100,40);
        NamePlayer.setVisible(true);

        Game.setBounds(100,160,80,20);
        Game.setVisible(true);

        Exit.setBounds(200,160,80,20);
        Exit.setVisible(true);

        add(NamePlayer);

        add(Game);
        Game.addActionListener(this);

        add(Exit);
        Exit.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        name = NamePlayer.getText();

        Object src = e.getSource();
        if(src == Game){

            JFrame gamePanel = new GamePanel(name,config,bestScores);

            gamePanel.setTitle("GRA");
            gamePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gamePanel.setVisible(true);
            dispose();


        }
        if(src == Exit){
            dispose();

        }
    }
}
