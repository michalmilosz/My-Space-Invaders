import javax.swing.*;
import java.awt.*;
/**
 * Klasa Bullet - odpowiada za pociski gracza
 */
public class Bullet extends CharacterInGame {

    public boolean ifExist;

    /**
     * Konstruktr klasy Bullet
     * @param x - wspolrzedna x bomby
     * @param y - wspolrzedna y bomby
     */
    public Bullet(int x, int y){
        positionX = x;
        positionY = y;
        width = Config.getWidthOfBullet();
        height = Config.getHeightOfBullet();
        rec = new Rectangle(positionX,positionY,width,height);
        rec.y = positionY;
        rec.x = positionX;
        look = new ImageIcon("icons/bullet.png").getImage();
    }

    /**
     * Funkcja typu void odpowiedzialna za rysowanie pocisku
     * @param g - parametr typu Graphics
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public void draw(Graphics g, double scaleX, double scaleY) {

        width = (int) (Config.getWidthOfBullet() * scaleX);
        height = (int) (Config.getHeightOfBullet() * scaleY);

        rec.width = (int) (Config.getWidthOfBullet() * scaleX);
        rec.height = (int) (Config.getHeightOfBullet() * scaleY);

        g.drawImage(look,(int)(positionX*scaleX),(int)(positionY*scaleY),width,height,this);

    }

    /**
     * Funkcja typu void odpowiedzialna za poruszanie sie pocisku
     */
    public void move(){
        this.positionY-=5;
        rec.y = positionY;
        if(this.positionY<0){
            ifExist = false;

        }
    }
    /**
     * Funkcja typu void sprawdzajaca czy pocisk nie zderzyl sie z wrogiem lub "ochraniaczami"
     * @param myEnemies- tablica wrogow
     * @param player - obecny gracz
     * @param myProtector1 - "ochraniacz"
     * @param myProtector2 - "ochraniacz"
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */

    public void collisions(ArrayOfEnemies myEnemies, Player player, double scaleX, double scaleY, Protectors myProtector1, Protectors myProtector2){
        Collisions.collisionBulletWithEnemy(myEnemies, this, player, scaleX, scaleY);
        Collisions.collisionBulletWithProtector(this, myProtector1, scaleX, scaleY);
        Collisions.collisionBulletWithProtector(this, myProtector2, scaleX, scaleY);

    }
}
