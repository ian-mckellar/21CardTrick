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
import java.util.*;
import javax.swing.*;
import javax.swing.border.*; 
//import sun.audio.*;

public class Game21 extends JFrame {

	// Screen Sizes
	int screenWidth = 800;
	int screenHeight = 820;

	// Paint boolean
	private boolean paintBool = false;

	// Boolean for card selection
	private boolean cardSelected = false;

	// Hold Column choice values
	private ArrayList<Column> columnChoices = new ArrayList<Column>();

	// Images
	private ImageIcon streetMagicImage = new ImageIcon(getClass().getResource("Images/streetMagic.png"));
	private Image streetMagicBackGround = streetMagicImage.getImage();

	private ImageIcon redImage = new ImageIcon(getClass().getResource("Images/redBackGround.png"));
	private Image redBackGround = redImage.getImage();

	// Card to hold selected card
	private JLabel selectedCard = null;

	// Card border for when selected
	private static final Border selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 1);

	// Vars to aid in random number generation
	private Random randomNum;
	private long RandomeSeed;

	// Labels
	ArrayList<JLabel> jLabels = new ArrayList<JLabel>();
	ArrayList<Column> columns;

	JLabel commandLbl = new JLabel();
	String command = "Select a Card";
	
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
		setTitle("Game 21 Trick");

		// Add new board (including deck) to JFrame
		add(new Game21Board());

		// Set Size a visibility of board
		setBackground(Color.black);
		setSize(screenWidth, screenHeight);
		setVisible(true);
		setLayout(null);

	}

	// Method to start game
	public void startGame() {
		System.out.println("Here");
	}

	// Game21Board Class - Constructs the Game21Board Board implements the ability of mouse clicks
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

			DealDeck();
			columnChoices.clear();
		}

		public void Deal() {

			// Repaint game Field
			paintBool = true;
			repaint();

			int x = 46;
			for (Column column : columns) {
				int y = 650;
				for (Card card : column.getCards()) {
					JLabel label = new JLabel();
					label.setName(card.getFace() + "_of_" + card.getSuit());
					y -= 79;
					label.setBounds(x, y, 75, 125);
					label.setIcon(resizeImage((card.getFace() + "_of_" + card.getSuit()).toLowerCase()));
					label.setOpaque(true);
					label.setBackground(Color.WHITE);
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					label.setHorizontalAlignment(SwingConstants.CENTER);
					label.setToolTipText(card.getFace() + " of " + card.getSuit());
					add(label);
					jLabels.add(label);
				}
				x += 150;
			}

			commandLbl.setText("<html>Think of a card displayed and choose a column</html>");
			commandLbl.setBounds(525, 100, 200, 50);
			commandLbl.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));
			commandLbl.setForeground(Color.WHITE);
			commandLbl.setHorizontalAlignment(SwingConstants.CENTER);
			commandLbl.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
			add(commandLbl);
			
			for (int i = 1; i < 4; i++) {
				JButton btn = new JButton("Column " + i);
				btn.setBounds(545, 240 + (60 * (i -1)), 100, 40);
				int j = i;
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ColumnSelected(j);
					}
				});
				add(btn);				
			}
			
			JLabel testLbl = new JLabel();
			testLbl.setBounds(445, 420, 1, 1);
			add(testLbl);
		}
		
		private void DealDeck() {
			columns = new ArrayList<Column>();
			//dealer = new Dealer();
			dealer.PickupCards();
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
		
		private void ColumnSelected(int i) {
			columnChoices.add(columns.get(i - 1));
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
			
			DealDeck();
			revalidate();
			repaint();
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
