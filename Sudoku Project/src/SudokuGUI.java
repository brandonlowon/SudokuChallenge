import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SudokuGUI class creates a GUI interface to represent the Sudoku Board
 * 
 * @author Identifier 629
 *
 */
public class SudokuGUI extends JFrame {
	private JFrame frame;
	private JPanel panel;
	private JTextArea display;
	private JLabel welcomeLabel;
	public ArrayList<JTextField> cellList;
	private JTextField box11, box12, box13, box14, box15, box16, box17, box18, box19, box21, box22, box23, box24, box25,
			box26, box27, box28, box29, box31, box32, box33, box34, box35, box36, box37, box38, box39, box41, box42,
			box43, box44, box45, box46, box47, box48, box49, box51, box52, box53, box54, box55, box56, box57, box58,
			box59, box61, box62, box63, box64, box65, box66, box67, box68, box69, box71, box72, box73, box74, box75,
			box76, box77, box78, box79, box81, box82, box83, box84, box85, box86, box87, box88, box89, box91, box92,
			box93, box94, box95, box96, box97, box98, box99;

	private Font fontBox;
	private JButton clearButton, solveButton, newButton;

	public SudokuGUI() {
		createGUI();
		createBorder();
		cellList = new ArrayList<>();
		createList();
		applyDocumentFilter();
	}

	/*
	 * applyDocumentFilter() iterates through the ArrayList of JTextField objects or
	 * 'cells' and e nsures that the user can allow input one integer for each box.
	 * For this method I found solutions online how to implement it and modified the
	 * code to only accept integers.
	 */
	private void applyDocumentFilter() {
		for (int i = 0; i < cellList.size(); i++) {
			AbstractDocument d = (AbstractDocument) cellList.get(i).getDocument();
			d.setDocumentFilter(new DocumentFilter() {
				Pattern regEx = Pattern.compile("\\d*");
				int max = 1;

				@Override
				public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
						AttributeSet attrs) throws BadLocationException {
					Matcher matcher = regEx.matcher(text);
					if (!matcher.matches()) {
						return;
					}
					int documentLength = fb.getDocument().getLength();
					if (documentLength - length + text.length() <= max)
						super.replace(fb, offset, length, text.toUpperCase(), attrs);
				}
			});
		}
	}

	private void createList() {
		addToList(box11);
		addToList(box12);
		addToList(box13);
		addToList(box14);
		addToList(box15);
		addToList(box16);
		addToList(box17);
		addToList(box18);
		addToList(box19);

		addToList(box21);
		addToList(box22);
		addToList(box23);
		addToList(box24);
		addToList(box25);
		addToList(box26);
		addToList(box27);
		addToList(box28);
		addToList(box29);

		addToList(box31);
		addToList(box32);
		addToList(box33);
		addToList(box34);
		addToList(box35);
		addToList(box36);
		addToList(box37);
		addToList(box38);
		addToList(box39);

		addToList(box41);
		addToList(box42);
		addToList(box43);
		addToList(box44);
		addToList(box45);
		addToList(box46);
		addToList(box47);
		addToList(box48);
		addToList(box49);

		addToList(box51);
		addToList(box52);
		addToList(box53);
		addToList(box54);
		addToList(box55);
		addToList(box56);
		addToList(box57);
		addToList(box58);
		addToList(box59);

		addToList(box61);
		addToList(box62);
		addToList(box63);
		addToList(box64);
		addToList(box65);
		addToList(box66);
		addToList(box67);
		addToList(box68);
		addToList(box69);

		addToList(box71);
		addToList(box72);
		addToList(box73);
		addToList(box74);
		addToList(box75);
		addToList(box76);
		addToList(box77);
		addToList(box78);
		addToList(box79);

		addToList(box81);
		addToList(box82);
		addToList(box83);
		addToList(box84);
		addToList(box85);
		addToList(box86);
		addToList(box87);
		addToList(box88);
		addToList(box89);

		addToList(box91);
		addToList(box92);
		addToList(box93);
		addToList(box94);
		addToList(box95);
		addToList(box96);
		addToList(box97);
		addToList(box98);
		addToList(box99);

	}

	private void addToList(JTextField cell) {
		cellList.add(cell);

	}

	public void createGUI() {

		fontBox = new Font("SansSerif", Font.BOLD, 40);
		frame = new JFrame();
		panel = new JPanel();

		frame.setSize(1080, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);

		welcomeLabel = new JLabel("Sudoku Board");
		welcomeLabel.setBounds(400, 1, 700, 50);
		welcomeLabel.setFont(new Font("Serif", Font.BOLD, 40));
		panel.add(welcomeLabel);

		// ROW 1
		box11 = new JTextField(30);
		box11.setBounds(40, 110, 90, 90);
		box11.setHorizontalAlignment(JTextField.CENTER);
		box11.setFont(fontBox);
		panel.add(box11);

		box12 = new JTextField(30);
		box12.setBounds(130, 110, 90, 90);
		box12.setHorizontalAlignment(JTextField.CENTER);
		box12.setFont(fontBox);
		panel.add(box12);

		box13 = new JTextField(30);
		box13.setBounds(220, 110, 90, 90);
		box13.setHorizontalAlignment(JTextField.CENTER);
		box13.setFont(fontBox);

		panel.add(box13);

		box14 = new JTextField(30);
		box14.setBounds(310, 110, 90, 90);
		box14.setHorizontalAlignment(JTextField.CENTER);
		box14.setFont(fontBox);
		panel.add(box14);

		box15 = new JTextField(30);
		box15.setBounds(400, 110, 90, 90);
		box15.setHorizontalAlignment(JTextField.CENTER);
		box15.setFont(fontBox);
		panel.add(box15);

		box16 = new JTextField(30);
		box16.setBounds(490, 110, 90, 90);
		box16.setHorizontalAlignment(JTextField.CENTER);
		box16.setFont(fontBox);
		panel.add(box16);

		box17 = new JTextField(30);
		box17.setBounds(580, 110, 90, 90);
		box17.setHorizontalAlignment(JTextField.CENTER);
		box17.setFont(fontBox);
		panel.add(box17);

		box18 = new JTextField(30);
		box18.setBounds(670, 110, 90, 90);
		box18.setHorizontalAlignment(JTextField.CENTER);
		box18.setFont(fontBox);
		panel.add(box18);

		box19 = new JTextField(30);
		box19.setBounds(760, 110, 90, 90);
		box19.setHorizontalAlignment(JTextField.CENTER);
		box19.setFont(fontBox);
		panel.add(box19);

		// ROW 2
		box21 = new JTextField(30);
		box21.setBounds(40, 200, 90, 90);
		box21.setHorizontalAlignment(JTextField.CENTER);
		box21.setFont(fontBox);
		panel.add(box21);

		box22 = new JTextField(30);
		box22.setBounds(130, 200, 90, 90);
		box22.setHorizontalAlignment(JTextField.CENTER);
		box22.setFont(fontBox);
		panel.add(box22);

		box23 = new JTextField(30);
		box23.setBounds(220, 200, 90, 90);
		box23.setHorizontalAlignment(JTextField.CENTER);
		box23.setFont(fontBox);

		panel.add(box23);

		box24 = new JTextField(30);
		box24.setBounds(310, 200, 90, 90);
		box24.setHorizontalAlignment(JTextField.CENTER);
		box24.setFont(fontBox);
		panel.add(box24);

		box25 = new JTextField(30);
		box25.setBounds(400, 200, 90, 90);
		box25.setHorizontalAlignment(JTextField.CENTER);
		box25.setFont(fontBox);
		panel.add(box25);

		box26 = new JTextField(30);
		box26.setBounds(490, 200, 90, 90);
		box26.setHorizontalAlignment(JTextField.CENTER);
		box26.setFont(fontBox);
		panel.add(box26);

		box27 = new JTextField(30);
		box27.setBounds(580, 200, 90, 90);
		box27.setHorizontalAlignment(JTextField.CENTER);
		box27.setFont(fontBox);
		panel.add(box27);

		box28 = new JTextField(30);
		box28.setBounds(670, 200, 90, 90);
		box28.setHorizontalAlignment(JTextField.CENTER);
		box28.setFont(fontBox);
		panel.add(box28);

		box29 = new JTextField(30);
		box29.setBounds(760, 200, 90, 90);
		box29.setHorizontalAlignment(JTextField.CENTER);
		box29.setFont(fontBox);
		panel.add(box29);

		// ROW 3
		box31 = new JTextField(30);
		box31.setBounds(40, 290, 90, 90);
		box31.setHorizontalAlignment(JTextField.CENTER);
		box31.setFont(fontBox);
		panel.add(box31);

		box32 = new JTextField(30);
		box32.setBounds(130, 290, 90, 90);
		box32.setHorizontalAlignment(JTextField.CENTER);
		box32.setFont(fontBox);
		panel.add(box32);

		box33 = new JTextField(30);
		box33.setBounds(220, 290, 90, 90);
		box33.setHorizontalAlignment(JTextField.CENTER);
		box33.setFont(fontBox);
		panel.add(box33);

		box34 = new JTextField(30);
		box34.setBounds(310, 290, 90, 90);
		box34.setHorizontalAlignment(JTextField.CENTER);
		box34.setFont(fontBox);
		panel.add(box34);

		box35 = new JTextField(30);
		box35.setBounds(400, 290, 90, 90);
		box35.setHorizontalAlignment(JTextField.CENTER);
		box35.setFont(fontBox);
		panel.add(box35);

		box36 = new JTextField(30);
		box36.setBounds(490, 290, 90, 90);
		box36.setHorizontalAlignment(JTextField.CENTER);
		box36.setFont(fontBox);
		panel.add(box36);

		box37 = new JTextField(30);
		box37.setBounds(580, 290, 90, 90);
		box37.setHorizontalAlignment(JTextField.CENTER);
		box37.setFont(fontBox);
		panel.add(box37);

		box38 = new JTextField(30);
		box38.setBounds(670, 290, 90, 90);
		box38.setHorizontalAlignment(JTextField.CENTER);
		box38.setFont(fontBox);
		panel.add(box38);

		box39 = new JTextField(30);
		box39.setBounds(760, 290, 90, 90);
		box39.setHorizontalAlignment(JTextField.CENTER);
		box39.setFont(fontBox);
		panel.add(box39);

		// ROW 4
		box41 = new JTextField(40);
		box41.setBounds(40, 380, 90, 90);
		box41.setHorizontalAlignment(JTextField.CENTER);
		box41.setFont(fontBox);
		panel.add(box41);

		box42 = new JTextField(30);
		box42.setBounds(130, 380, 90, 90);
		box42.setHorizontalAlignment(JTextField.CENTER);
		box42.setFont(fontBox);
		panel.add(box42);

		box43 = new JTextField(30);
		box43.setBounds(220, 380, 90, 90);
		box43.setHorizontalAlignment(JTextField.CENTER);
		box43.setFont(fontBox);

		panel.add(box43);

		box44 = new JTextField(30);
		box44.setBounds(310, 380, 90, 90);
		box44.setHorizontalAlignment(JTextField.CENTER);
		box44.setFont(fontBox);
		panel.add(box44);

		box45 = new JTextField(30);
		box45.setBounds(400, 380, 90, 90);
		box45.setHorizontalAlignment(JTextField.CENTER);
		box45.setFont(fontBox);
		panel.add(box45);

		box46 = new JTextField(30);
		box46.setBounds(490, 380, 90, 90);
		box46.setHorizontalAlignment(JTextField.CENTER);
		box46.setFont(fontBox);
		panel.add(box46);

		box47 = new JTextField(30);
		box47.setBounds(580, 380, 90, 90);
		box47.setHorizontalAlignment(JTextField.CENTER);
		box47.setFont(fontBox);
		panel.add(box47);

		box48 = new JTextField(30);
		box48.setBounds(670, 380, 90, 90);
		box48.setHorizontalAlignment(JTextField.CENTER);
		box48.setFont(fontBox);
		panel.add(box48);

		box49 = new JTextField(30);
		box49.setBounds(760, 380, 90, 90);
		box49.setHorizontalAlignment(JTextField.CENTER);
		box49.setFont(fontBox);
		panel.add(box49);

		// ROW 5
		box51 = new JTextField(40);
		box51.setBounds(40, 470, 90, 90);
		box51.setHorizontalAlignment(JTextField.CENTER);
		box51.setFont(fontBox);
		panel.add(box51);

		box52 = new JTextField(30);
		box52.setBounds(130, 470, 90, 90);
		box52.setHorizontalAlignment(JTextField.CENTER);
		box52.setFont(fontBox);
		panel.add(box52);

		box53 = new JTextField(30);
		box53.setBounds(220, 470, 90, 90);
		box53.setHorizontalAlignment(JTextField.CENTER);
		box53.setFont(fontBox);
		panel.add(box53);

		box54 = new JTextField(30);
		box54.setBounds(310, 470, 90, 90);
		box54.setHorizontalAlignment(JTextField.CENTER);
		box54.setFont(fontBox);
		panel.add(box54);

		box55 = new JTextField(30);
		box55.setBounds(400, 470, 90, 90);
		box55.setHorizontalAlignment(JTextField.CENTER);
		box55.setFont(fontBox);
		panel.add(box55);

		box56 = new JTextField(30);
		box56.setBounds(490, 470, 90, 90);
		box56.setHorizontalAlignment(JTextField.CENTER);
		box56.setFont(fontBox);
		panel.add(box56);

		box57 = new JTextField(30);
		box57.setBounds(580, 470, 90, 90);
		box57.setHorizontalAlignment(JTextField.CENTER);
		box57.setFont(fontBox);
		panel.add(box57);

		box58 = new JTextField(30);
		box58.setBounds(670, 470, 90, 90);
		box58.setHorizontalAlignment(JTextField.CENTER);
		box58.setFont(fontBox);
		panel.add(box58);

		box59 = new JTextField(30);
		box59.setBounds(760, 470, 90, 90);
		box59.setHorizontalAlignment(JTextField.CENTER);
		box59.setFont(fontBox);
		panel.add(box59);

		// ROW 6
		box61 = new JTextField(40);
		box61.setBounds(40, 560, 90, 90);
		box61.setHorizontalAlignment(JTextField.CENTER);
		box61.setFont(fontBox);
		panel.add(box61);

		box62 = new JTextField(30);
		box62.setBounds(130, 560, 90, 90);
		box62.setHorizontalAlignment(JTextField.CENTER);
		box62.setFont(fontBox);
		panel.add(box62);

		box63 = new JTextField(30);
		box63.setBounds(220, 560, 90, 90);
		box63.setHorizontalAlignment(JTextField.CENTER);
		box63.setFont(fontBox);
		panel.add(box63);

		box64 = new JTextField(30);
		box64.setBounds(310, 560, 90, 90);
		box64.setHorizontalAlignment(JTextField.CENTER);
		box64.setFont(fontBox);
		panel.add(box64);

		box65 = new JTextField(30);
		box65.setBounds(400, 560, 90, 90);
		box65.setHorizontalAlignment(JTextField.CENTER);
		box65.setFont(fontBox);
		panel.add(box65);

		box66 = new JTextField(30);
		box66.setBounds(490, 560, 90, 90);
		box66.setHorizontalAlignment(JTextField.CENTER);
		box66.setFont(fontBox);
		panel.add(box66);

		box67 = new JTextField(30);
		box67.setBounds(580, 560, 90, 90);
		box67.setHorizontalAlignment(JTextField.CENTER);
		box67.setFont(fontBox);
		panel.add(box67);

		box68 = new JTextField(30);
		box68.setBounds(670, 560, 90, 90);
		box68.setHorizontalAlignment(JTextField.CENTER);
		box68.setFont(fontBox);
		panel.add(box68);

		box69 = new JTextField(30);
		box69.setBounds(760, 560, 90, 90);
		box69.setHorizontalAlignment(JTextField.CENTER);
		box69.setFont(fontBox);
		panel.add(box69);

		// ROW 7
		box71 = new JTextField(40);
		box71.setBounds(40, 650, 90, 90);
		box71.setHorizontalAlignment(JTextField.CENTER);
		box71.setFont(fontBox);
		panel.add(box71);

		box72 = new JTextField(30);
		box72.setBounds(130, 650, 90, 90);
		box72.setHorizontalAlignment(JTextField.CENTER);
		box72.setFont(fontBox);
		panel.add(box72);

		box73 = new JTextField(30);
		box73.setBounds(220, 650, 90, 90);
		box73.setHorizontalAlignment(JTextField.CENTER);
		box73.setFont(fontBox);
		panel.add(box73);

		box74 = new JTextField(30);
		box74.setBounds(310, 650, 90, 90);
		box74.setHorizontalAlignment(JTextField.CENTER);
		box74.setFont(fontBox);
		panel.add(box74);

		box75 = new JTextField(30);
		box75.setBounds(400, 650, 90, 90);
		box75.setHorizontalAlignment(JTextField.CENTER);
		box75.setFont(fontBox);
		panel.add(box75);

		box76 = new JTextField(30);
		box76.setBounds(490, 650, 90, 90);
		box76.setHorizontalAlignment(JTextField.CENTER);
		box76.setFont(fontBox);
		panel.add(box76);

		box77 = new JTextField(30);
		box77.setBounds(580, 650, 90, 90);
		box77.setHorizontalAlignment(JTextField.CENTER);
		box77.setFont(fontBox);
		panel.add(box77);

		box78 = new JTextField(30);
		box78.setBounds(670, 650, 90, 90);
		box78.setHorizontalAlignment(JTextField.CENTER);
		box78.setFont(fontBox);
		panel.add(box78);

		box79 = new JTextField(30);
		box79.setBounds(760, 650, 90, 90);
		box79.setHorizontalAlignment(JTextField.CENTER);
		box79.setFont(fontBox);
		panel.add(box79);

		// ROW 8
		box81 = new JTextField(40);
		box81.setBounds(40, 740, 90, 90);
		box81.setHorizontalAlignment(JTextField.CENTER);
		box81.setFont(fontBox);
		panel.add(box81);

		box82 = new JTextField(30);
		box82.setBounds(130, 740, 90, 90);
		box82.setHorizontalAlignment(JTextField.CENTER);
		box82.setFont(fontBox);
		panel.add(box82);

		box83 = new JTextField(30);
		box83.setBounds(220, 740, 90, 90);
		box83.setHorizontalAlignment(JTextField.CENTER);
		box83.setFont(fontBox);
		panel.add(box83);

		box84 = new JTextField(30);
		box84.setBounds(310, 740, 90, 90);
		box84.setHorizontalAlignment(JTextField.CENTER);
		box84.setFont(fontBox);
		panel.add(box84);

		box85 = new JTextField(30);
		box85.setBounds(400, 740, 90, 90);
		box85.setHorizontalAlignment(JTextField.CENTER);
		box85.setFont(fontBox);
		panel.add(box85);

		box86 = new JTextField(30);
		box86.setBounds(490, 740, 90, 90);
		box86.setHorizontalAlignment(JTextField.CENTER);
		box86.setFont(fontBox);
		panel.add(box86);

		box87 = new JTextField(30);
		box87.setBounds(580, 740, 90, 90);
		box87.setHorizontalAlignment(JTextField.CENTER);
		box87.setFont(fontBox);
		panel.add(box87);

		box88 = new JTextField(30);
		box88.setBounds(670, 740, 90, 90);
		box88.setHorizontalAlignment(JTextField.CENTER);
		box88.setFont(fontBox);
		panel.add(box88);

		box89 = new JTextField(30);
		box89.setBounds(760, 740, 90, 90);
		box89.setHorizontalAlignment(JTextField.CENTER);
		box89.setFont(fontBox);
		panel.add(box89);

		// ROW 9
		box91 = new JTextField(40);
		box91.setBounds(40, 830, 90, 90);
		box91.setHorizontalAlignment(JTextField.CENTER);
		box91.setFont(fontBox);
		panel.add(box91);

		box92 = new JTextField(30);
		box92.setBounds(130, 830, 90, 90);
		box92.setHorizontalAlignment(JTextField.CENTER);
		box92.setFont(fontBox);
		panel.add(box92);

		box93 = new JTextField(30);
		box93.setBounds(220, 830, 90, 90);
		box93.setHorizontalAlignment(JTextField.CENTER);
		box93.setFont(fontBox);
		panel.add(box93);

		box94 = new JTextField(30);
		box94.setBounds(310, 830, 90, 90);
		box94.setHorizontalAlignment(JTextField.CENTER);
		box94.setFont(fontBox);
		panel.add(box94);

		box95 = new JTextField(30);
		box95.setBounds(400, 830, 90, 90);
		box95.setHorizontalAlignment(JTextField.CENTER);
		box95.setFont(fontBox);
		panel.add(box95);

		box96 = new JTextField(30);
		box96.setBounds(490, 830, 90, 90);
		box96.setHorizontalAlignment(JTextField.CENTER);
		box96.setFont(fontBox);
		panel.add(box96);

		box97 = new JTextField(30);
		box97.setBounds(580, 830, 90, 90);
		box97.setHorizontalAlignment(JTextField.CENTER);
		box97.setFont(fontBox);
		panel.add(box97);

		box98 = new JTextField(30);
		box98.setBounds(670, 830, 90, 90);
		box98.setHorizontalAlignment(JTextField.CENTER);
		box98.setFont(fontBox);
		panel.add(box98);

		box99 = new JTextField(30);
		box99.setBounds(760, 830, 90, 90);
		box99.setHorizontalAlignment(JTextField.CENTER);
		box99.setFont(fontBox);
		panel.add(box99);

		// BUTTONS
		clearButton = new JButton("Clear");
		getClearButton().setBounds(900, 400, 100, 50);
		panel.add(getClearButton());

		solveButton = new JButton("Solve");
		solveButton.setBounds(900, 600, 100, 50);
		panel.add(getSolveButton());

		newButton = new JButton("New");
		newButton.setBounds(900, 200, 100, 50);
		panel.add(getNewButton());

		frame.setVisible(true);
	}

	private void createBorder() {
		Border oldBorder = box12.getBorder();
		Border botRight = BorderFactory.createMatteBorder(0, 0, 3, 3, Color.BLACK);
		Border bottomRight = BorderFactory.createCompoundBorder(botRight, oldBorder);
		Border rig = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK);
		Border right = BorderFactory.createCompoundBorder(rig, oldBorder);
		Border bottom = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK),
				oldBorder);

		box13.setBorder(right);
		box23.setBorder(right);
		box43.setBorder(right);
		box53.setBorder(right);
		box73.setBorder(right);
		box83.setBorder(right);
		box93.setBorder(right);
		box16.setBorder(right);
		box26.setBorder(right);
		box46.setBorder(right);
		box56.setBorder(right);
		box76.setBorder(right);
		box86.setBorder(right);
		box96.setBorder(right);
		box33.setBorder(bottomRight);
		box36.setBorder(bottomRight);
		box63.setBorder(bottomRight);
		box66.setBorder(bottomRight);
		box31.setBorder(bottom);
		box32.setBorder(bottom);
		box34.setBorder(bottom);
		box35.setBorder(bottom);
		box37.setBorder(bottom);
		box38.setBorder(bottom);
		box39.setBorder(bottom);
		box61.setBorder(bottom);
		box62.setBorder(bottom);
		box64.setBorder(bottom);
		box65.setBorder(bottom);
		box67.setBorder(bottom);
		box68.setBorder(bottom);
		box69.setBorder(bottom);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	public JButton getSolveButton() {
		return solveButton;
	}

	public JButton getNewButton() {
		return newButton;
	}

	
	public void actionPerformed(ActionEvent e) {
	}

	public JTextArea getDisplay() {
		return display;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}

	public ArrayList<JTextField> getCellList() {
		return cellList;
	}

	public JTextField getBox11() {
		return box11;
	}

	public JTextField getBox12() {
		return box12;
	}

	public JTextField getBox13() {
		return box13;
	}

	public JTextField getBox14() {
		return box14;
	}

	public JTextField getBox15() {
		return box15;
	}

	public JTextField getBox16() {
		return box16;
	}

	public JTextField getBox17() {
		return box17;
	}

	public JTextField getBox18() {
		return box18;
	}

	public JTextField getBox19() {
		return box19;
	}

	public JTextField getBox21() {
		return box21;
	}

	public JTextField getBox22() {
		return box22;
	}

	public JTextField getBox23() {
		return box23;
	}

	public JTextField getBox24() {
		return box24;
	}

	public JTextField getBox25() {
		return box25;
	}

	public JTextField getBox26() {
		return box26;
	}

	public JTextField getBox27() {
		return box27;
	}

	public JTextField getBox28() {
		return box28;
	}

	public JTextField getBox29() {
		return box29;
	}

	public JTextField getBox31() {
		return box31;
	}

	public JTextField getBox32() {
		return box32;
	}

	public JTextField getBox33() {
		return box33;
	}

	public JTextField getBox34() {
		return box34;
	}

	public JTextField getBox35() {
		return box35;
	}

	public JTextField getBox36() {
		return box36;
	}

	public JTextField getBox37() {
		return box37;
	}

	public JTextField getBox38() {
		return box38;
	}

	public JTextField getBox39() {
		return box39;
	}

	public JTextField getBox41() {
		return box41;
	}

	public JTextField getBox42() {
		return box42;
	}

	public JTextField getBox43() {
		return box43;
	}

	public JTextField getBox44() {
		return box44;
	}

	public JTextField getBox45() {
		return box45;
	}

	public JTextField getBox46() {
		return box46;
	}

	public JTextField getBox47() {
		return box47;
	}

	public JTextField getBox48() {
		return box48;
	}

	public JTextField getBox49() {
		return box49;
	}

	public JTextField getBox51() {
		return box51;
	}

	public JTextField getBox52() {
		return box52;
	}

	public JTextField getBox53() {
		return box53;
	}

	public JTextField getBox54() {
		return box54;
	}

	public JTextField getBox55() {
		return box55;
	}

	public JTextField getBox56() {
		return box56;
	}

	public JTextField getBox57() {
		return box57;
	}

	public JTextField getBox58() {
		return box58;
	}

	public JTextField getBox59() {
		return box59;
	}

	public JTextField getBox61() {
		return box61;
	}

	public JTextField getBox62() {
		return box62;
	}

	public JTextField getBox63() {
		return box63;
	}

	public JTextField getBox64() {
		return box64;
	}

	public JTextField getBox65() {
		return box65;
	}

	public JTextField getBox66() {
		return box66;
	}

	public JTextField getBox67() {
		return box67;
	}

	public JTextField getBox68() {
		return box68;
	}

	public JTextField getBox69() {
		return box69;
	}

	public JTextField getBox71() {
		return box71;
	}

	public JTextField getBox72() {
		return box72;
	}

	public JTextField getBox73() {
		return box73;
	}

	public JTextField getBox74() {
		return box74;
	}

	public JTextField getBox75() {
		return box75;
	}

	public JTextField getBox76() {
		return box76;
	}

	public JTextField getBox77() {
		return box77;
	}

	public JTextField getBox78() {
		return box78;
	}

	public JTextField getBox79() {
		return box79;
	}

	public JTextField getBox81() {
		return box81;
	}

	public JTextField getBox82() {
		return box82;
	}

	public JTextField getBox83() {
		return box83;
	}

	public JTextField getBox84() {
		return box84;
	}

	public JTextField getBox85() {
		return box85;
	}

	public JTextField getBox86() {
		return box86;
	}

	public JTextField getBox87() {
		return box87;
	}

	public JTextField getBox88() {
		return box88;
	}

	public JTextField getBox89() {
		return box89;
	}

	public JTextField getBox91() {
		return box91;
	}

	public JTextField getBox92() {
		return box92;
	}

	public JTextField getBox93() {
		return box93;
	}

	public JTextField getBox94() {
		return box94;
	}

	public JTextField getBox95() {
		return box95;
	}

	public JTextField getBox96() {
		return box96;
	}

	public JTextField getBox97() {
		return box97;
	}

	public JTextField getBox98() {
		return box98;
	}

	public JTextField getBox99() {
		return box99;
	}

	public Font getFontBox() {
		return fontBox;
	}

}
