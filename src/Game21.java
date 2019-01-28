//Game21 class - Main Class that will run Game21 game 

//Import statements
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.*;
//import sun.audio.*;

public class Game21 extends JFrame {

	// Screen Sizes
	int screenWidth = 496;
	int screenHeight = 820;

	// Paint boolean
	private boolean paintBool = false;
	
	// Boolean for card selection
	private boolean cardSelected = false;
	
	// Hold Column choice values
	//private ArrayList<Integer> columnChoices = new ArrayList<Integer>();

	// Images
	private ImageIcon streetMagicImage = new ImageIcon(getClass().getResource("Images/streetMagic.png"));
	private Image streetMagicBackGround = streetMagicImage.getImage();

	private ImageIcon redImage = new ImageIcon(getClass().getResource("Images/redBackGround.png"));
	private Image redBackGround = redImage.getImage();

	// Card to hold selected card
	private JLabel selectedCard = null;

	// Card border for when selected
	private static final Border selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 1);
	private static final Border columnBorder = BorderFactory.createLineBorder(Color.WHITE, 1);

	// Vars to aid in random number generation
	private Random randomNum;
	private long RandomeSeed;

	// Labels
	// Should we add all of these to a list and assign them numbers in 2 for loops?
	ArrayList<JLabel> jLabels = new ArrayList<JLabel>();
	ArrayList<Column> columns = new ArrayList<Column>();

	// Default constructor
	public Game21() {

		// Closing the program
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Program Title
		setTitle("Game 21 Trick");

		// Add new board (including deck) to JFrame
		add(new Game21Board());

		// Set Size a visibility of board
		setBackground(Color.black);
		setSize(screenWidth, screenHeight);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setUndecorated(true);
		setVisible(true);
		setLayout(null);

	}

	// Method to start game
	public void startGame() {
		System.out.println("Here");
	}

	// Game21Board Class - Constructs the Game21Board Board implements the ability
	// of mouse clicks
	public class Game21Board extends JPanel implements MouseListener {

		// Default constructor
		public Game21Board() {

			// New random
			randomNum = new Random();

			// Method call to set up menu
			menu();

		}

		// Menu Method - Menu for additional game controls
		private void menu() {

			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			JMenu gameMenu = new JMenu("Game");
			menuBar.add(gameMenu);

			Action playAction = new AbstractAction("New Game") {

				public void actionPerformed(ActionEvent e) {
					startGame();
				}
			};

			JMenuItem newGame = new JMenuItem(playAction);
			gameMenu.add(newGame);

			/*
			 * JMenu soundMenu = new JMenu("Sound"); menuBar.add(soundMenu);
			 * 
			 * Action onAction = new AbstractAction("On") {
			 * 
			 * public void actionPerformed(ActionEvent e) { //soundOn(); } };
			 * 
			 * Action offAction = new AbstractAction("Off") { public void
			 * actionPerformed(ActionEvent e) { //soundOff(); } };
			 * 
			 * JMenuItem soundOn = new JMenuItem(onAction); JMenuItem soundOff = new
			 * JMenuItem(offAction); soundMenu.add(soundOn); soundMenu.addSeparator();
			 * soundMenu.add(soundOff);
			 * 
			 * JMenu helpMenu = new JMenu("Help"); menuBar.add(helpMenu);
			 * 
			 * 
			 * Action rulesAction = new AbstractAction("Game Rules") {
			 * 
			 * public void actionPerformed(ActionEvent e) {
			 * 
			 * //JOptionPane.showMessageDialog(null, "Rules", "Game Rules",
			 * JOptionPane.INFORMATION_MESSAGE);
			 * 
			 * } };
			 * 
			 * JMenuItem rules = new JMenuItem(rulesAction); helpMenu.add(rules);
			 */
		}

		// Method to Paint background
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (paintBool == false) { // Launch Screen

				g.setColor(Color.BLACK);
				g.fillRect(0, 0, getWidth(), getHeight());

				g.drawImage(streetMagicBackGround, (this.getWidth() - streetMagicBackGround.getWidth(this)) / 2,
						(this.getHeight() - streetMagicBackGround.getHeight(this)) / 2, this);

			} else { // GameField
				g.drawImage(redBackGround, 0, 0, this);
			}
		}

		public void startGame() {

			// Set border layout
			setLayout(new BorderLayout());

			// Generate new random
			RandomeSeed = System.currentTimeMillis() % 10000;
			randomNum.setSeed(RandomeSeed);

			// Call a new game
			// Game game = new Game();
			// game.newGame(randomNum);

			Dealer dealer = new Dealer(); 
			dealer.Deal(); 
			for (int i = 0; i < 3; i++) {
				Column col = new Column(i);
				for (int j = 0; j < 7; j++) {
					col.addCard(dealer.GetDeck().GetCard(j + (i * 7)));
				}
				columns.add(col);
			}
			Deal(); // deal to each JLabel
			return;
		}

		public void Deal() {

			// Repaint game Field
			paintBool = true;
			repaint();
			
			// Need to create 3 columns, add cards to columns, add columns to board
			int x = 46;
			//for (int i = 1; i < 4; i++) {
			for (Column column : columns) {
				int y = -105;
				//for (int j = 1; j < 8; j++) {
				for (Card card : column.getCards()) {
					JLabel label = new JLabel();
					//label.setName("r" + i + "c" + j);
					label.setName(card.getFace() + card.getSuit());
					y += 108;
					label.setBounds(x, y, 64, 104);
					//label.setIcon(resizeImage(card.getFace() + "_of_" + card.getSuit());
					label.setIcon(resizeImage("backOfDeckBlack"));
					label.addMouseListener(this);
					label.setHorizontalAlignment(SwingConstants.CENTER);
					add(label);
				}
				x += 150;
			}			
			
			JLabel testLbl = new JLabel();
			testLbl.setBounds(1000, 420, 1, 1);
			add(testLbl);
		}

		// Mouse pressed events
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO: Show selected card
			// System.out.println("Clicked");
			// Need to include Column choice as well
			// Handle wrong choices. If they've chosen a card, they must choose a column.
			Object source = e.getSource();
			// If source is a card
			//JLabel label = (JLabel) e.getSource();
			if (selectedCard != (JLabel)source && cardSelected)
				return;
			if (selectedCard == null) {
				selectedCard = (JLabel)source;
				selectedCard.setBorder(selectedBorder);
				cardSelected = true;	
			}
			else {
				selectedCard.setBorder(null);
				selectedCard = null;
				cardSelected = false;
			}
			repaint();
			
			// else if label is a column
			
			//columnChoices.add((Column)source.___);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	// Resize cards to correct size
	public ImageIcon resizeImage(String imageName) {

		ImageIcon card1ImageIcon = new ImageIcon(getClass().getResource("Images/" + imageName + ".png"));
		Image card1Image = card1ImageIcon.getImage();
		Image scaledImg = card1Image.getScaledInstance(75, 125, java.awt.Image.SCALE_SMOOTH);
		card1ImageIcon = new ImageIcon(scaledImg);

		return card1ImageIcon;
	}

	public static void main(String[] args) throws Exception {
		new Game21();

	}
}
