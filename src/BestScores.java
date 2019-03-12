import java.io.*;

/**
 * Klasa okreslajaca najlepsze wyniki
 */
public class BestScores {

    public Player listOfBestScores[] = new Player[10];
    String listofTheBest="Najlepsza dziesiatka:\n";

    /**
     * Konstruktor klasy BestScores
     * @param file_BestScores - String, informujacy gdzie jest plik tekstowy z najlepszymi wynikami
     */
    public BestScores(String file_BestScores) {
        loadBestScore(file_BestScores);
    }

    /**
     * Funkcja typu void ladujaca najlpesze wyniki z pliku txt do tablicy typu Player
     * @param file_bestScores - String, informujacy gdzie jest plik tekstowy z najlepszymi wynikami
     */
    public void loadBestScore(String file_bestScores) {
        try {

            FileReader file = new FileReader(file_bestScores);
            BufferedReader buffor = new BufferedReader(file);

            for(int k=0;k<10;k++){
                listOfBestScores[k] = new Player();
                listOfBestScores[k].name = buffor.readLine();

                listofTheBest += (k+1)+". " +listOfBestScores[k].name;
                if(listOfBestScores[k].name==null)
                {
                    break;
                }
                else{

                  listOfBestScores[k].score = Integer.parseInt(buffor.readLine());
                  listofTheBest += " zdobyl(a) " + listOfBestScores[k].score +" punktow \n";

                }
            }

            buffor.close();

        } catch (Exception zd) {
            zd.printStackTrace();
        }
    }

    /**
     * Funkcja typu void uaktualniajaca tablice typu Player najlepszych wynikow
     * @param file_bestScores - String, informujacy gdzie jest plik tekstowy z najlepszymi wynikami
     */
    public void updateBestScores(String file_bestScores) {
        try {

            FileReader file = new FileReader(file_bestScores);
            BufferedReader buffor = new BufferedReader(file);
            listofTheBest = "Najlepsza dziesiatka:\n";
            for(int k=0;k<10;k++){
                listOfBestScores[k] = new Player();
                listOfBestScores[k].name = buffor.readLine();

                listofTheBest += (k+1)+". " +listOfBestScores[k].name;
                if(listOfBestScores[k].name==null)
                {
                    break;
                }
                else{

                    listOfBestScores[k].score = Integer.parseInt(buffor.readLine());
                    listofTheBest += " zdobyl(a) " + listOfBestScores[k].score +" punktow \n";

                }
            }

            buffor.close();

        } catch (Exception zd) {
            zd.printStackTrace();
        }
    }


    /**
     * Funkcja typu void uaktualniajca plik txt z najlepszymi wynikami
     * @param player - gracz obecnie grajacy
     * @param fileBestScore - String, informujacy gdzie jest plik tekstowy z najlepszymi wynikami
     */
    public void setScoreList(Player player, String fileBestScore) {
        for (int i = 9; i >=0; i--) {
            if (player.score > listOfBestScores[i].score) {
                if (i == 9) {
                    listOfBestScores[i] = player;
                } else {
                    Player pom = listOfBestScores[i];
                    listOfBestScores[i] = listOfBestScores[i + 1];
                    listOfBestScores[i + 1] = pom;
                }

            }

        }

        try {
            FileWriter plik = new FileWriter(fileBestScore);
            BufferedWriter writer = new BufferedWriter(plik);
            for (int i = 0; i < 10; i++){
                writer.write(listOfBestScores[i].name);
                writer.newLine();
                writer.write(Integer.toString(listOfBestScores[i].score));
                writer.newLine();
            }
            writer.close();
            plik.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
