import java.awt.*;


/**
 *  Klasa, bedaca tablica wrogow
 */
public class ArrayOfEnemies {

    //zmienna okreslajaca czy wrogowie poruszaja sie w lewo czy w prawo
    private boolean EnemyMoveRight = false;

    // liczba odbić potrzebna do opadania wrogów
    int numberOfReflections = 0;

    //tablica wrogow
    Enemy enemies[][] = new Enemy[20][20];

    /**
     * Funckja typu void tworzaca wrogow w tablicy
     * @param g - parametr typu Graphics
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     * @param config - parametr klasy Config potrzebny do tego, aby wykonac metoda set_enemies(), ktora to ustawia odpowiednie wartosci w zaleznosci od levelu
     */
    public void MadeEnemies(Graphics g,double scaleX, double scaleY,Config config) {

        config.set_enemies();

        //ustawienie wrogow pierwszego typu
        for (int i = 0; i < Config.getHowManyEnemy3(); i++) {
            for (int j = 0; j < Config.getEnemiesHorizontally(); j++) {
                enemies[i][j] = new Enemy3(Config.getStartingPositionOfEnemyX() + j * (Config.getWidthOfImage()+20), Config.getHeightOfImage() * (i + 1));
                if (enemies[i][j] != null) {
                    enemies[i][j].draw(g, scaleX, scaleY);
                }

            }
        }

        //ustawienie wrogow drugiego typu
        for (int i = Config.getHowManyEnemy3(); i < Config.getHowManyEnemy3()+Config.getHowManyEnemy2(); i++) {
            for (int j = 0; j < Config.getEnemiesHorizontally(); j++) {
                enemies[i][j] = new Enemy2(Config.getStartingPositionOfEnemyX() + j * (Config.getWidthOfImage()+20), Config.getHeightOfImage() * (i + 1));
                if (enemies[i][j] != null) {
                    enemies[i][j].draw(g, scaleX, scaleY);
                }

            }
        }

        //ustawienie wrogow trzeciego typu
        for (int i = Config.getHowManyEnemy3()+Config.getHowManyEnemy2(); i < Config.getHowManyEnemy1()+Config.getHowManyEnemy2()+Config.getHowManyEnemy3(); i++) {
            for (int j = 0; j < Config.getEnemiesHorizontally(); j++) {
                enemies[i][j] = new Enemy(Config.getStartingPositionOfEnemyX() + j * (Config.getWidthOfImage()+20), Config.getHeightOfImage() * (i + 1));
                if (enemies[i][j] != null) {
                    enemies[i][j].draw(g, scaleX, scaleY);
                }

            }
        }
    }




    /**
     * Funckja typu void, ktora porusza wrogami
     * @param width - szerokosc okienka gra
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     * @param config - parametr klasy Config potrzebny do tego, aby wykonac metoda set_enemies(), ktora to ustawia odpowiednie wartosci w zaleznosci od levelu
     */
    public void MoveEnemies(int width,double scaleX, double scaleY,Config config) {
        ifEnemyMoveRight(width,scaleX,scaleY);
        for (int i = 0; i < Config.getEnemiesVertically(); i++) {
            for (int j = 0; j < Config.getEnemiesHorizontally(); j++) {

                if (enemies[i][j] != null) {
                    if (EnemyMoveRight) {
                        enemies[i][j].moveRight(config);
                        if(numberOfReflections>=1){
                            enemies[i][j].positionY+=enemies[i][j].jump;
                            enemies[i][j].rec.y+=enemies[i][j].jump;

                        }

                    } else {
                        enemies[i][j].moveLeft(config);
                        if(numberOfReflections>=1){
                            enemies[i][j].positionY+=enemies[i][j].jump;
                            enemies[i][j].rec.y+=enemies[i][j].jump;
                        }
                    }
                }

            }
        }
        if(numberOfReflections>=1)numberOfReflections=0;
    }

    /**
     * Funckja typu void sprawdzajaca czy wrogowie poruszaja sie width prawo czy width lewo
     * @param width - szerokosc okienka gra
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     **/
    void ifEnemyMoveRight(int width,double scaleX, double scaleY) {
        for (int i = 0; i < Config.getEnemiesVertically(); i++) {
            for (int j = 0; j < Config.getEnemiesHorizontally(); j++) {
                if (enemies[i][j] != null) {
                    if ((int)(enemies[i][j].positionX*scaleX) + (int)(enemies[i][j].width) > width) {
                        EnemyMoveRight = false;
                        numberOfReflections++;
                    }
                }

                if (enemies[i][j] != null) {
                    if ((int)(enemies[i][j].positionX*scaleX) < 0) {
                        EnemyMoveRight = true;
                        numberOfReflections++;
                    }
                }
            }
        }
    }

    /**
     * Funckja typu bool sprawdzajaca czy tablica wrogow jest pusta
     * @return - zwraca true jesli tablica wrogow pusta, false jesli jest nie jest pusta
     */
    public boolean ifEnemyEmpty(){
        int ifEmpty = 0;
        for (int i = 0; i < Config.getEnemiesVertically(); i++) {
            for (int j = 0; j < Config.getEnemiesHorizontally(); j++) {
                if (enemies[i][j]==null) ifEmpty++;
            }
        }
        if(ifEmpty==Config.getEnemiesVertically()*Config.getEnemiesHorizontally()) {
            return true;
        }
        else return false;
    }
}