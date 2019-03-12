import javax.swing.*;
import java.awt.*;




/**
 * ProgramMain zawierajacy kod gry Space Invaders 0.0. Klasa ProgramMain zawiera metode main.
 * @author Michal Milosz
 * @version 3.0 , 28.01.2019 r.
 *
 *
 */
public class ProgramMain {
    /**
     * Funkcja main
     * @param args -
     */
    public static void main(String[]args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                /**
                 * Tworzony jest obiekt klasy Config, który ustawia wszystkie wartosci konfiguracyjne
                 */

                    Config config = new Config("./config.txt");
                /**
                 * Tworzony jest obiekt klasy BestScores, który pobiera liste najlepszych wynikow
                 */
                    BestScores bestScores = new BestScores("best_scores.txt");

                /**
                 * Tworzona jest klasa MenuPanel, która wyświetla menuPanel gry
                 */
                MenuPanel menuPanel = new MenuPanel(config, bestScores);
                menuPanel.setTitle("SPACE INVADERS");
                menuPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menuPanel.setVisible(true);

            }
        });
    }
}
