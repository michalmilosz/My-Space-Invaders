import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * klasa, ktora dziedziczy po JFrame i jest odpowiedzialna z okienko wyskakujace po zakonczeniu gry
 */
public class EndPanel extends JFrame implements ActionListener {

    JButton extiToMenu;
    JButton gameFromBeginning ;
    JLabel information;


    Config config;
    BestScores bestScores;
    Player player;

    /**
     * konstruktor klasy EndPanel
     * @param exitToMenu - przycisk powodujava wyjscie do menu
     * @param gameFromBeginning - przycisk powodujacy ponowne rozpoczecie gry
     * @param c - obiekt klasy Config
     * @param bS - obiekt klasy BestScores
     * @param player - obiekt gracza
     * @param endingText - parametr typu String przekazujacy tekst wypisywany na koniec gry
     */
    public EndPanel(JButton exitToMenu,JButton gameFromBeginning,Config c, BestScores bS, Player player, String endingText){

        config = c;
        bestScores = bS;
        this.player = player;

        setLayout(null);
        setBounds(400,400,500,500);
        setTitle("Koniec gry");

        Font font = new Font("SanSerif", Font.ITALIC, 22);

        //Opis panelu
        information = new JLabel(endingText+"\n"+"Twoj wynik to: "+player.score);
        information.setFont(font);
        information.setBounds(100,10,400,150);
        information.setVisible(true);
        add(information);


        //Opis przycisku do wyjścia do menu
        this.extiToMenu = exitToMenu;
        this.extiToMenu.setBounds(150,110,200,40);;
        this.extiToMenu.setVisible(true);
        add(this.extiToMenu);
        this.extiToMenu.addActionListener(this);

        //Opis przycisku do ponownego rozpoczęcia gry
        this.gameFromBeginning = gameFromBeginning;
        this.gameFromBeginning.setBounds(150,210,200,40);
        this.gameFromBeginning.setVisible(true);
        add(this.gameFromBeginning);
        this.gameFromBeginning.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == this.extiToMenu){

            dispose();
        }

        if(src == this.gameFromBeginning){
            config.setLevel(1);
            /*NamePanel nazwagracz = new NamePanel(config,bestScores);
            nazwagracz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            nazwagracz.setVisible(true);*/

            GamePanel gamePanel = new GamePanel(player.name,config,bestScores);
            gamePanel.setTitle("GRA");
            gamePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gamePanel.setVisible(true);

            dispose();
        }
    }
}
