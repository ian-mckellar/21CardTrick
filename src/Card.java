import javax.swing.*;
import java.awt.*;

public class Card extends JPanel {
    private String suit;
    private String face;
    private String imageName;
    private ImageIcon cardImage;

    /**
     * initialise a card with a suit(spade, heart, diamond, club) and a face(jack, queen, king)
     * all face cards have a value of 10
     *
     * @param suit - spade, heart, diamond, club
     * @param face - ace, jack, queen, kind
     */
    Card(String face, String suit, String imageName) {
        this.face = face;
        this.suit = suit;
        this.imageName = imageName;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCard(g);
    }

    private void drawCard(Graphics g) {
        ImageIcon imageIcon = new ImageIcon();
        imageIcon.paintIcon(this, g, 25, 5);
    }

    /**
     * @return String representing suit of the card
     */
    String getSuit() {
        return suit;
    }

    /**
     * @return String representing face of card
     */
    String getFace() {
        return face;
    }

    /**
     * @return String repreenting location of image in project
     */
    String getImageName() {
        return imageName;
    }

    /**
     * returns string with field values for cards for testing purposes
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("suit = %-8s " +
                "face = %-5s " +
                "imageName = %10s", suit, face, imageName);
    }
}