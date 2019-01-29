//Game21 class - Main Class that will run Game21 game 

//Import statements
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.util.*;
import javax.swing.*;
//import sun.audio.*;

public class Game21 extends JFrame {

	// Screen Sizes
	int screenWidth = 800;
	int screenHeight = 700;

	// Paint boolean
	private boolean paintBool = false;

	// Hold Column choice values
	private ArrayList<Column> columnChoices = new ArrayList<Column>();

	// Images
	private ImageIcon streetMagicImage = new ImageIcon(getClass().getResource("Images/streetMagic.png"));
	private Image streetMagicBackGround = streetMagicImage.getImage();

	private ImageIcon redImage = new ImageIcon(getClass().getResource("Images/redBackGround.png"));
	private Image redBackGround = redImage.getImage();

	// Vars to aid in random number generation
	private Random randomNum;
	private long RandomeSeed;

	// Labels
	ArrayList<JLabel> jLabels = new ArrayList<JLabel>();
	ArrayList<Column> columns;

	// Command button/labels to hold the instructions for the game 
	JLabel commandLbl = new JLabel();
	String command = "Select a Card";
	
	// Dealer to run various methods and hold values
	Dealer dealer = new Dealer();

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
		setTitle("Game 21 Magic Trick");

		// Add new board (including deck) to JFrame
		add(new Game21Board());

		// Set Size a visibility of board
		setBackground(Color.BLACK);
		setSize(screenWidth, screenHeight);
		setVisible(true);
		setLayout(null);

	}

	// Game21Board Class - Constructs the Game21Board Board implements the ability
	// of mouse clicks
	public class Game21Board extends JPanel {
		
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
			menuBar.setBackground(new Color(150, 150, 150));
			menuBar.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
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
				this.setBackground(Color.BLACK);
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, getWidth(), getHeight());

				g.drawImage(streetMagicBackGround, (this.getWidth() - streetMagicBackGround.getWidth(this)) / 2,
						(this.getHeight() - streetMagicBackGround.getHeight(this)) / 2, this);

			} else { // GameField
				g.drawImage(redBackGround, 0, 0, this);
			}
		}
		
		// Start a game on 'New Game' pressed		
		public void startGame() {

			// Set border layout
			setLayout(new BorderLayout());

			// Generate new random
			//////// Do we need this?
			RandomeSeed = System.currentTimeMillis() % 10000;
			randomNum.setSeed(RandomeSeed);
			
			// Get the deck and deal it out
			dealDeck();
			columnChoices.clear();
		}
		
		// Adds UI components
		public void deal() {

			// Repaint game Field
			paintBool = true;
			repaint();
				
			// Add the cards to the window
			int x = 50;
			for (int i = 0; i < 3; i++) {
				int y = 450;
				for (int j = 6; j >= 0; j--) {
					Card card = columns.get(i).getCards().get(j);
					JLabel label = new JLabel();
					label.setName(card.getFace() + "_of_" + card.getSuit());
					label.setIcon(resizeImage((card.getFace() + "_of_" + card.getSuit()).toLowerCase()));
					label.setOpaque(true);
					label.setBackground(Color.WHITE);
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					label.setHorizontalAlignment(SwingConstants.CENTER);
					label.setToolTipText(card.getFace() + " of " + card.getSuit());
					label.setBounds(x, y, 89, 125);
					add(label);
					jLabels.add(label);
					y -= 64;
				}
				x += 144;
			}
			
			// Create the top left instruction/command label
			commandLbl.setText("<html>Think of a card displayed and choose the column it's in</html>");
			commandLbl.setBounds(500, 100, 275, 40);
			commandLbl.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));
			commandLbl.setForeground(Color.WHITE);
			commandLbl.setHorizontalAlignment(SwingConstants.CENTER);
			commandLbl.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
			add(commandLbl);
			
			// Add the 3 buttons for each column
			for (int i = 1; i < 4; i++) {
				JButton btn = new JButton("Column " + i);
				btn.setBounds(545, 240 + (60 * (i - 1)), 100, 40);
				int j = i;
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ColumnSelected(j - 1);
					}
				});
				btn.setBackground(new Color(224, 224, 224));
				add(btn);
			}
			
			JLabel testLbl = new JLabel();
			testLbl.setBounds(445, 420, 1, 1);
			add(testLbl);
		}
		
		// Deal the deck to the board
		private void dealDeck() {
			// Create new columns to hold the cards every round
			columns = new ArrayList<Column>();
			for (int i = 0; i < 3; i++) {
				columns.add(new Column());
			}
			
			// Add cards to the columns
			int x = 0;
			ArrayList<Card> temp = new ArrayList<Card>();
			for (int j = 0; j < 7; j++) {
				for (int i = 0; i < 3; i++) {
					columns.get(i).addCard(dealer.getDeck().getCard(x));
					x++;
				}
			}
			
			// Set the game cards equal to the new deck.
			for (Column col : columns) {
				for (Card card : col.getCards()) {
					temp.add(card);
				}
			}
			dealer.getDeck().setGameCards(temp);
			deal();
			return;
		}

		// Run on a column button being selected
		private void ColumnSelected(int i) {
			columnChoices.add(columns.get(i));
			for (JLabel label : jLabels)
				remove(label);

			if (columnChoices.size() > 3)
				return;
			if (columnChoices.size() == 3) {
				// disable buttons
				ArrayList<Set<Card>> listOfSet = new ArrayList<Set<Card>>();
				for (Column co : columnChoices) {
					Set<Card> setOfCards = new HashSet<Card>();
					for (Card car : co.getCards()) {
						setOfCards.add(car);
					}
					listOfSet.add(setOfCards);
				}

				for (Set<Card> set : listOfSet) {
					for (Card card : set) {
						System.out.print(card.getFace() + " " + card.getSuit());
					}
					System.out.println();
				}

				listOfSet.get(0).retainAll(listOfSet.get(1));
				for (Card card : listOfSet.get(0))
					System.out.print(card.getFace() + card.getSuit());
				listOfSet.get(0).retainAll(listOfSet.get(2));

				JOptionPane.showMessageDialog(this, "This is your card: " + listOfSet.get(0).toString());
				return;
			}

			dealer.PickupCards(i);
			dealDeck();
			revalidate();
			repaint();
		}
	}

	// Resize cards to correct size
	public ImageIcon resizeImage(String imageName) {

		ImageIcon card1ImageIcon = new ImageIcon(getClass().getResource("Images/" + imageName + ".png"));
		Image card1Image = card1ImageIcon.getImage();
		Image scaledImg = card1Image.getScaledInstance(89, 125, java.awt.Image.SCALE_SMOOTH);
		card1ImageIcon = new ImageIcon(scaledImg);

		return card1ImageIcon;
	}
	
	// Runs the game initially
	public static void main(String[] args) throws Exception {
		new Game21();
	}
}
