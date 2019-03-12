import java.awt.*;

/**
 * Klasa odpowiedzialna za wszystkie kolizje
 */
public class Collisions {

    //Zliczam liczbę zamić po to, aby wiedzieć kiedy zakończyć grę
    private static int numberOfKilledEnemies = 0;

    /**
     * Funckja odpowiedzialna za kolizje pocisku z wrogiem
     * @param myEnemies - tablica wrogow
     * @param bullet - obiekt pocisku
     * @param player - obiekt gracza
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public static void collisionBulletWithEnemy(ArrayOfEnemies myEnemies, Bullet bullet, Player player, double scaleX, double scaleY){
        for (int i = 0; i < Config.getEnemiesVertically(); i++) {
            for (int j = 0; j < Config.getEnemiesHorizontally(); j++) {
                if (myEnemies.enemies[i][j] != null&&bullet!=null) {
                    Rectangle newEnemy = new Rectangle((int)(myEnemies.enemies[i][j].positionX*scaleX),(int)(myEnemies.enemies[i][j].positionY*scaleY),myEnemies.enemies[i][j].width,myEnemies.enemies[i][j].height);
                    Rectangle newBullet = new Rectangle((int)(bullet.rec.x*scaleX),(int)(bullet.rec.y*scaleY),bullet.rec.width,bullet.rec.height);

                    if (newBullet.intersects(newEnemy)) {
                        bullet.positionY = -100;
                        bullet.positionX = -100;
                        myEnemies.enemies[i][j].lifeLevel--;
                        if(myEnemies.enemies[i][j].lifeLevel==0) {
                            player.getScore(myEnemies.enemies[i][j].points);
                            myEnemies.enemies[i][j] = null;
                            numberOfKilledEnemies++;

                        }


                    }
                }

            }
        }
    }

    /**
     * Funckja odpowiedzialna za kolizje bomby z graczem
     * @param bomb - obiekt bomby
     * @param player - obiekt gracza
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public static void collisionBombWithPlayer(Bomb bomb, Player player, double scaleX, double scaleY){
        if(bomb!=null&&player!=null  ) {
            Rectangle newBomb = new Rectangle((int)(bomb.positionX*scaleX),(int)(bomb.positionY*scaleY),bomb.width,bomb.height);
            Rectangle newPlayer = new Rectangle((int)(player.rec.x*scaleX),(int)(player.rec.y*scaleY),player.rec.width,player.rec.height);

            if (newBomb.intersects(newPlayer)) {
                bomb.positionY = -100;
                bomb.positionX = -100;

                player.getHurt(true);

                numberOfKilledEnemies++;
                bomb.ifExist =false;
            }
        }
    }

    /**
     * Funkcja odpowiedzialna za kolize bomby z ochraniaczem
     * @param bomb - obiekt bomby
     * @param protector - obiekt ochraniacza
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public static void collisionBombWithProtector(Bomb bomb, Protectors protector, double scaleX, double scaleY){
        if(bomb!=null && protector!=null ) {
            Rectangle newBomb = new Rectangle((int)(bomb.positionX*scaleX),(int)(bomb.positionY*scaleY),bomb.width,bomb.height);
            Rectangle newProtector = new Rectangle((int)(protector.rec.x*scaleX),(int)(protector.rec.y*scaleY),protector.rec.width,protector.rec.height);

            if (newBomb.intersects(newProtector)) {

                bomb.positionY = -100;
                bomb.positionX = -100;

                protector.getHurt(true);
                bomb.ifExist = false;

            }
        }
    }

    /**
     * Funkcja odpowiedzialna za kolizje pocisku z ochraniaczem
     * @param bullet - obiekt pocisku
     * @param protector - obiekt ochraniacza
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public static void collisionBulletWithProtector(Bullet bullet, Protectors protector, double scaleX, double scaleY){
        if(bullet!=null && protector!=null ) {
            Rectangle newBullet = new Rectangle((int)(bullet.positionX*scaleX),(int)(bullet.positionY*scaleY),bullet.width,bullet.height);
            Rectangle newProtector = new Rectangle((int)(protector.rec.x*scaleX),(int)(protector.rec.y*scaleY),protector.rec.width,protector.rec.height);

            if (newBullet.intersects(newProtector)) {

                bullet.positionY = -100;
                bullet.positionX = -100;

                protector.getHurt(true);
                bullet.ifExist = false;

            }
        }
    }

    /**
     * Funkcja odpowiedzialna za kolizje wroga z ochraniaczem
     * @param myEnemies - tablica wrogow
     * @param protector - obiekt ochraniacza
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     * @return - zwracana jest zmienna typu boolean: true, gdy doszlo do kolizji, false w przeciwnym przypadku
     */
    public static boolean collisionEnemyWithProtector(ArrayOfEnemies myEnemies, Protectors protector, double scaleX, double scaleY){
        boolean answer = false;
        for (int i = 0; i < Config.getEnemiesVertically(); i++) {
            for (int j = 0; j < Config.getEnemiesHorizontally(); j++) {
                if (myEnemies.enemies[i][j] != null&&protector!=null) {
                    Rectangle newEnemy = new Rectangle((int)(myEnemies.enemies[i][j].positionX*scaleX),(int)(myEnemies.enemies[i][j].positionY*scaleY),myEnemies.enemies[i][j].width,myEnemies.enemies[i][j].height);
                    Rectangle newProtector = new Rectangle((int)(protector.rec.x*scaleX),(int)(protector.rec.y*scaleY),protector.rec.width,protector.rec.height);

                    if (newProtector.intersects(newEnemy)) {

                        protector.positionY = -100;
                        protector.positionX = -100;

                        answer = true;

                    }
                }

            }
        }
        return answer;
    }

    /**
     * Funkcja odpowiedzialna za kolizje wroga z graczem
     * @param myEnemies - tablica wrogow
     * @param player - obiekt gracza
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     * @return - zwracana jest zmienna typu boolean: true, gdy doszlo do kolizji, false w przeciwnym przypadku
     */
    public static boolean collisionEnemyWithPlayer(ArrayOfEnemies myEnemies, Player player, double scaleX, double scaleY){
        boolean answer = false;
        for (int i = 0; i < Config.getEnemiesVertically(); i++) {
            for (int j = 0; j < Config.getEnemiesHorizontally(); j++) {
                if (myEnemies.enemies[i][j] != null&&player!=null) {
                    Rectangle newEnemy = new Rectangle((int)(myEnemies.enemies[i][j].positionX*scaleX),(int)(myEnemies.enemies[i][j].positionY*scaleY),myEnemies.enemies[i][j].width,myEnemies.enemies[i][j].height);
                    Rectangle newPlayer = new Rectangle((int)(player.rec.x*scaleX),(int)(player.rec.y*scaleY),player.rec.width,player.rec.height);

                    if (newPlayer.intersects(newEnemy)) {

                        player.positionY = -100;
                        player.positionX = -100;

                        answer = true;

                    }
                }

            }
        }
        return answer;
    }

    /**
     * Funkcja odpowiedzialna za kolizje extra punktu z graczem
     * @param extraPoint - obiekt extra punktu
     * @param player - obiekt gracza
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public static void collisionExtraPointWithPlayer(ExtraPoint extraPoint, Player player, double scaleX, double scaleY){
        if(extraPoint !=null&&player!=null  ) {
            Rectangle newExtraPoint = new Rectangle((int)(extraPoint.positionX*scaleX),(int)(extraPoint.positionY*scaleY), extraPoint.width, extraPoint.height);
            Rectangle newPlayer = new Rectangle((int)(player.rec.x*scaleX),(int)(player.rec.y*scaleY),player.rec.width,player.rec.height);
            if (newExtraPoint.intersects(newPlayer)) {
                extraPoint.positionY = -100;
                extraPoint.positionX = -100;

                player.getScore(Config.getExtraPoints());

                extraPoint.ifExist =false;
            }
        }
    }

    /**
     * Funkcja odpowiedzialna za kolizje extra punktu z ochraniaczem
     * @param extraPoint - obiekt extra punktu
     * @param protector - obiekt ochraniacza
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public static void collisionExtraPointWithProtector(ExtraPoint extraPoint, Protectors protector, double scaleX, double scaleY){
        if(extraPoint !=null&&protector!=null ) {
            Rectangle newExtraPoint = new Rectangle((int)(extraPoint.positionX*scaleX),(int)(extraPoint.positionY*scaleY), extraPoint.width, extraPoint.height);
            Rectangle newProtector = new Rectangle((int)(protector.rec.x*scaleX),(int)(protector.rec.y*scaleY),protector.rec.width,protector.rec.height);
            if (newExtraPoint.intersects(newProtector)) {

                extraPoint.positionY = -100;
                extraPoint.positionX = -100;

                extraPoint.ifExist = false;

            }
        }
    }

    /**
     * Funkcja odpowiedzialna za kolizje extra zycia z graczem
     * @param extraLife - obiekt extra zycia
     * @param player - obiekt gracza
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public static void collisionExtraLivesWithPlayer(ExtraLife extraLife, Player player, double scaleX, double scaleY){
        if(extraLife !=null&&player!=null  ) {
            Rectangle newExtraPoint = new Rectangle((int)(extraLife.positionX*scaleX),(int)(extraLife.positionY*scaleY), extraLife.width, extraLife.height);
            Rectangle newPlayer = new Rectangle((int)(player.rec.x*scaleX),(int)(player.rec.y*scaleY),player.rec.width,player.rec.height);
            if (newExtraPoint.intersects(newPlayer)) {
                extraLife.positionY = -100;
                extraLife.positionX = -100;

                player.getExtraLife();

                extraLife.ifExist =false;
            }
        }
    }

    /**
     * Funkcja odpowiedzialna za kolizje extra zycia z ochraniaczem
     * @param extraLife - obiekt extra zycia
     * @param protector - obiekt ochraniacza
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public static void collisionExtraLifeWithProtector(ExtraLife extraLife, Protectors protector, double scaleX, double scaleY){
        if(extraLife !=null&&protector!=null ) {
            Rectangle newExtraPoint = new Rectangle((int)(extraLife.positionX*scaleX),(int)(extraLife.positionY*scaleY), extraLife.width, extraLife.height);
            Rectangle newProtector = new Rectangle((int)(protector.rec.x*scaleX),(int)(protector.rec.y*scaleY),protector.rec.width,protector.rec.height);
            if (newExtraPoint.intersects(newProtector)) {

                extraLife.positionY = -100;
                extraLife.positionX = -100;

                extraLife.ifExist = false;

            }
        }
    }



}
