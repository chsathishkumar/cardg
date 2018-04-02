
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.net.URL;


/**
 * An object of type GraphicalCard represents a playing card from a
 * standard Poker deck, including Jokers.  The card has a suit, which
 * can be spades, hearts, diamonds, clubs, or joker.  A space, heart,
 * diamond, or club has one of the 13 values: ace, 2, 3, 4, 5, 6, 7,
 * 8, 9, 10, jack, queen, or king.  Note that "ace" is considered to be
 * the smallest value.  A joker can also have an associated value; 
 * this value can be anything and can be used to keep track of several
 * different jokers.
 * <p>Two methods are defined for drawing cards onto GUI components.
 * These methods only work if the images file, cards.png, is available.
 * This image file contains images of all 52 cards plus two Jokers and
 * the reverse face of a card.  It is distributed with the GNOME desktop
 * environment, and so can be freely used in free software.  If the
 * image file can't be located, then a poor-quality string representation
 * of the card is drawn.
 */
public class GraphicalCard {
	
	public final static int SPADES = 0;       // Codes for the 4 suits, plus Joker.
	public final static int HEARTS = 1;
	public final static int DIAMONDS = 2;
	public final static int CLUBS = 3;
	public final static int JOKER = 4;
	
	public final static int ACE = 1;          // Codes for the non-numeric cards.
	public final static int JACK = 11;        //   Cards 2 through 10 have their 
	public final static int QUEEN = 12;       //   numerical values for their codes.
	public final static int KING = 13;
	
	/**
	 * This card's suit, one of the constants SPADES, HEARTS< DIAMONDS,
	 * CLUBS, or JOKER.  The suit cannot be changed after the card is
	 * constructed.
	 */
	private final int suit; 
	
	/**
	 * The card's value.  For a normal cards, this is one of the values
	 * 1 through 13, with 1 representing ACE.  For a JOKER, the value
	 * can be anything.  The value cannot be changed after the card
	 * is constructed.
	 */
	private final int value;
	
	/**
	 * The image that contains pictures of all the cards.  This is
	 * a static variable, since there is no need to have a different
	 * copy for each card.  By using a static variable, there is only
	 * one variable that is shared by all cards.
	 */
	private static Image cardImage;
	
	/**
	 * This will be set to true when an attempt to load the image
	 * has been made, so avoid doing it over and over each time a
	 * card is drawn.
	 */
	private static boolean imageLoaded;
	
	/**
	 * Creates a Joker, with 1 as the associated value.  (Note that
	 * "new GraphicalCard()" is equivalent to 
	 * "new GraphicalCard(1,GraphicalCard.JOKER)".)
	 */
	public GraphicalCard() {
		suit = JOKER;
		value = 1;
	}
	
	/**
	 * Creates a card with a specified suit and value.
	 * @param theValue the value of the new card.  For a regular card (not a Joker),
	 * the value must be in the range 1 through 13, with 1 representing an Ace.
	 * You can use the constants GraphicalCard.ACE, GraphicalCard.JACK,
	 * GraphicalCard.QUEEN, and GraphicalCard.KING.  For a Joker, the value
	 * can be anything.
	 * @param theSuit the suit of the new card.  This must be one of the values
	 * GraphicalCard.SPADES, GraphicalCard.HEARTS, GraphicalCard.DIAMONDS,
	 * GraphicalCard.CLUBS, or GraphicalCard.JOKER.
	 * @throws IllegalArgumentException if the parameter values are not in the
	 * permissable ranges
	 */
	public GraphicalCard(int theValue, int theSuit) {
		if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS && 
				theSuit != CLUBS && theSuit != JOKER)
			throw new IllegalArgumentException("Illegal playing card suit");
		if (theSuit != JOKER && theValue < 1 || theValue > 13)
			throw new IllegalArgumentException("Illegal playing card value");
		value = theValue;
		suit = theSuit;
	}

	/**
	 * Returns the suit of this card.
	 * @returns the suit, which is one of the constants GraphicalCard.SPADES, 
	 * GraphicalCard.HEARTS, GraphicalCard.DIAMONDS, GraphicalCard.CLUBS, 
	 * or GraphicalCard.JOKER
	 */
	public int getSuit() {
		return suit;
	}
	
	/**
	 * Returns the value of this card.
	 * @return the value, which is one the numbers 1 through 13, inclusive for
	 * a regular card, and which can be any value for a Joker.
	 */
	public int getValue() {
		// Return the int that codes for this card's value.
		return value;
	}
	
	/**
	 * Returns a String representation of the card's suit.
	 * @return one of the strings "Spades", "Hearts", "Diamonds", "Clubs"
	 * or "Joker".
	 */
	public String getSuitAsString() {
		switch ( suit ) {
		case SPADES:   return "Spades";
		case HEARTS:   return "Hearts";
		case DIAMONDS: return "Diamonds";
		case CLUBS:    return "Clubs";
		default:       return "Joker";
		}
	}
	
	/**
	 * Returns a String representation of the card's value.
	 * @return for a regular card, one of the strings "Ace", "2",
	 * "3", ..., "10", "Jack", "Queen", or "King".  For a
	 * Joker, the string is always numerical.
	 */
	public String getValueAsString() {
		// Return a String representing the card's value.
		// If the card's value is invalid, "??" is returned.
		if (suit == JOKER)
			return "" + value;
		else {
			switch ( value ) {
			case 1:   return "Ace";
			case 2:   return "2";
			case 3:   return "3";
			case 4:   return "4";
			case 5:   return "5";
			case 6:   return "6";
			case 7:   return "7";
			case 8:   return "8";
			case 9:   return "9";
			case 10:  return "10";
			case 11:  return "Jack";
			case 12:  return "Queen";
			default:  return "King";
			}
		}
	}
	
	/**
	 * Returns a string representation of this card, including both
	 * its suit and its value (except that for a Joker with value 0,
	 * the return value is just "Joker").  Sample reutrn values
	 * are: "Queen of Hearts", "10 of Diamonds", "Ace of Spades",
	 * "Joker", "Joker #2"
	 */
	public String toString() {
		if (suit == JOKER) {
			if (value == 1)
				return "Joker";
			else
				return "Joker #" + value;
		}
		else
			return getValueAsString() + " of " + getSuitAsString();
	}
	
	/**
	 * Draws the reverse face of a card in a 79x123 pixel rectangle with its
	 * upper left corner at a specified point (x,y).  Drawing the card 
	 * requires loading the image file "cards.png".  If this image file
	 * can't be loaded, then a beige rectangle will be drawn.
	 * (Note that this is a static method, which means it can be called
	 * as GraphicalCard.drawFaceDown(...).  However, a static method is
	 * considered to be shared by all instances of the class, so you can
	 * also call this method as card.drawFaceDown(), where card is a variable
	 * of type GraphicalCard.)
	 * @param g The non-null graphics context used for drawing the card.  If g is
	 * null, a NullPointerException will be thrown.
	 * @param component The GUI component, such as a JPanel, on which the card is
	 * being drawn.  (This component is used as an "image observer".  If the image
	 * has not yet been loaded when g.drawImage() method is called,  this method
	 * does not necessarily complete the drawing.  Instead, it can just start the
	 * process of loading the image.  Later, after the image has been loaded,
	 * the component is told to finish the actual drawing.  The component can
	 * be null, but then if the image has not yet been loaded, the card might
	 * not actually appear on the screen.)
	 * @param x the x-coord of the upper left corner of the card
	 * @param y the y-coord of the upper left corner of the card
	 */
	public static void drawFaceDown(Graphics g, Component component, int x, int y) {
		if (findImage()) {
			int cx = 2*79;    // x-coord of upper left corner of the card inside cardsImage
			int cy = 4*123;   // y-coord of upper left corner of the card inside cardsImage
			g.drawImage(cardImage,x,y,x+79,y+123,cx,cy,cx+79,cy+123,component);
		}
		else {
			Color c = g.getColor();  // Save current drawing color.
			g.setColor( new Color(240, 200, 150) );
			g.fillRect(x,y,79,120);
			g.setColor(Color.BLACK);
			g.drawRect(x,y,78,119);
			g.drawRect(x+3,y+3,72,113);
			g.setColor(c);
		}
	}
	
	/**
	 * Draws this card in a 79x123 pixel rectangle with its
	 * upper left corner at a specified point (x,y).  Drawing the card 
	 * requires loading the image file "cards.png".  If this image file
	 * can't be loaded, then a string representation of the card will
	 * be drawn.
	 * @param g The non-null graphics context used for drawing the card.  If g is
	 * null, a NullPointerException will be thrown.
	 * @param component The GUI component, such as a JPanel, on which the card is
	 * being drawn.  (This component is used as an "image observer".  If the image
	 * has not yet been loaded when g.drawImage() method is called,  this method
	 * does not necessarily complete the drawing.  Instead, it can just start the
	 * process of loading the image.  Later, after the image has been loaded,
	 * the component is told to finish the actual drawing.  The component can
	 * be null, but then if the image has not yet been loaded, the card might
	 * not actually appear on the screen.)
	 * @param x the x-coord of the upper left corner of the card
	 * @param y the y-coord of the upper left corner of the card
	 */
	public void draw(Graphics g, Component component, int x, int y) {
		if (findImage()) {
			int cx;    // x-coord of upper left corner of the card inside cardsImage
			int cy;    // y-coord of upper left corner of the card inside cardsImage
			switch (suit) {
			case CLUBS:    
				cy = 0; 
				break;
			case DIAMONDS: 
				cy = 123; 
				break;
			case HEARTS:   
				cy = 2*123; 
				break;
			case SPADES:   
				cy = 3*123; 
				break;
			default:       
				cy = 4*123;   // Joker
			}
			if (suit == JOKER) {
				if (value == 1)
					cx = 0;
				else
					cx = 79;
			}
			else
				cx = (value-1)*79;
			g.drawImage(cardImage,x,y,x+79,y+123,cx,cy,cx+79,cy+123,component);
		}
		else {
			Color c = g.getColor();  // Save current drawing color.
			g.setColor( Color.WHITE );
			g.fillRect(x,y,79,120);
			g.setColor(Color.BLACK);
			g.drawRect(x,y,78,119);
			g.setColor(Color.BLUE);
			if (suit == JOKER)
				g.drawString("Joker", x+10, y+30);
			else {
				g.drawString(getValueAsString(), x+10, y+30);
				g.drawString("of", x+20, y + 60);
				g.drawString(getSuitAsString(), x+10, y+90);
			}
			g.setColor(c);
		}
	}
	
	private static boolean findImage() {
		if (imageLoaded)
			return (cardImage != null);
		imageLoaded = true;
		ClassLoader cl = GraphicalCard.class.getClassLoader();
		URL imageURL = cl.getResource("cards.png");
		if (imageURL == null)
			return false;
		cardImage = Toolkit.getDefaultToolkit().createImage(imageURL);
		return (cardImage != null);
	}
	
	
} // end class Card
