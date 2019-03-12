import java.awt.*;

/**
 * Klasa odpowiedzialna za "ochraniaczy" w grze
 */
public class Protectors extends CharacterInGame {

    /**
     * Konstruktor klasy Protectors
     * @param distanceBetweenProtectors - paramter mowiacy jaka odleglosc jest pomiedzy ochraniaczami
     */
    public Protectors(int distanceBetweenProtectors){
        positionX = Config.getPositionOfProtectorX()+distanceBetweenProtectors;
        positionY = Config.getPositionOfProtectorY();
        lifeLevel = Config.getLifeLevelOfProtector();

        width = Config.getWidthOfProtector();
        height = Config.getWidthOfProtector();

        rec = new Rectangle(positionX,positionY,width,height);
    }


    /**
     * Funkcja odpowiedzialna za rysowanie
     * @param g - parametr typu Graphics
     * @param scaleX - paramter typu double potrzebny do skalowania
     * @param scaleY - paramter typu double potrzebny do skalowania
     * @param x - wspolrzednia x ochraniacza
     * @param y - wspolrzednia y ochraniacza
     */
    //@Override
    public void draw(Graphics g, double scaleX, double scaleY, int x, int y) {

        width = (int)(Config.getWidthOfProtector()*scaleX);
        height = (int)(Config.getHeightOfProtector()*scaleY);

        rec.width =(int)(Config.getWidthOfProtector()*scaleX);
        rec.height =(int)(Config.getHeightOfProtector()*scaleY);

        g.setColor(Color.black);
       // g.fillRect((int)(positionX*scaleX),(int)(positionY*scaleY),width,height);
        g.fillRect((int)(x*scaleX),(int)(y*scaleY),rec.width,rec.height);

    }

    /**
     * Funkcja odjmujaca zycie ochraniaczowi jesli ten zostal trafionyy
     * @param protectorWasHit - parametr mowiacy czy ochraniacz zostal trafiony
     */
    public void  getHurt(boolean protectorWasHit){
        if(protectorWasHit){
            lifeLevel-=1;
        }
    }
}




