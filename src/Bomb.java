import javax.swing.*;
import java.awt.*;

/**
 * Klasa Bomb dziedziczaca po klasiu Bullet - odpowiada za spadajace od wrogow bomby
 */
public class Bomb extends Bullet {


    /**
     * Konstruktr klasy Bomb
     * @param x - wspolrzedna x bomby
     * @param y - wspolrzedna y bomby
     */
    public Bomb(int x, int y){
        super(x,y);
        width = Config.getWidthOfBomb();
        height = Config.getHeightOfBomb();
        rec = new Rectangle(positionX,positionY,width,height);
        rec.y = positionY;
        rec.x = positionX;
        look = new ImageIcon("icons/bomb.png").getImage();
    }

    /**
     * Funkcja typu void odpowiedzialna za rysowanie bomby
     * @param g - parametr typu Graphics
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public void draw(Graphics g, double scaleX, double scaleY) {

        width = (int) (Config.getWidthOfBomb() * scaleX);
        height = (int) (Config.getHeightOfBomb() * scaleY);

        rec.width = (int) (Config.getWidthOfBomb() * scaleX);
        rec.height = (int) (Config.getHeightOfBomb() * scaleY);

        g.drawImage(look,(int)(positionX*scaleX),(int)(positionY*scaleY),width,height,this);

    }

    /**
     * Funkcja typu void odpowiedzialna za poruszanie sie bomby
     * @param hight - wysokosc okienka gry
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public void move(int hight, double scaleY){
        this.positionY+=2;
        rec.y = positionY;
        if(this.positionY*scaleY>hight){
            ifExist = false;

        }
    }

    /**
     * Funkcja typu void sprawdzajaca czy bomba nie zderzyla sie z graczem lub "ochraniaczami"
     * @param player - obecny gracz
     * @param myProtector1 - "ochraniacz"
     * @param myProtector2 - "ochraniacz"
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     */
    public void collision( Player player, Protectors myProtector1, Protectors myProtector2, double scaleX, double scaleY){
        Collisions.collisionBombWithPlayer(this, player, scaleX, scaleY);
        Collisions.collisionBombWithProtector(this, myProtector1, scaleX, scaleY);
        Collisions.collisionBombWithProtector(this, myProtector2, scaleX, scaleY);
    }

}
