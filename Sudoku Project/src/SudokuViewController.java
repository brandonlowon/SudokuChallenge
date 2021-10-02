import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * SudokuViewController is the controller class in our model-view-controller
 * design pattern. This class controls what is displayed on the GUI and calls
 * appropriate methods in the Model class to update the GUI.
 * 
 * @author Identifier 629
 *
 */
public class SudokuViewController {
	private SudokuGUI guiFrame;
	private SudokuModel model;

	public SudokuViewController() {
		guiFrame = new SudokuGUI();
		assignController();
		addListeners();
	}

	/**
	 * addListeners() implements actionListeners to all of our JButtons on the GUI
	 */
	public void addListeners() {
		guiFrame.getClearButton().addActionListener(new ActionListener() {
			/**
			 * When the user clicks the ClearButton all cells are set to emptyStrings to
			 * make it easier to reset and reload new puzzles custom or text file ones.
			 * 
			 * @param e is the actionEvent when the button is pressed
			 */
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < guiFrame.getCellList().size(); i++) {
					guiFrame.getCellList().get(i).setText("");
				}
			}
		});

		guiFrame.getSolveButton().addActionListener(new ActionListener() {

			/**
			 * When the user clicks the Solve button the triggerSolve() method from the
			 * model is called. Then once the list of solved numbers is obtained the GUI is
			 * updated and a prompt is shown.
			 * 
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				int[] solvedNumber = model.triggerSolve();
				for (int i = 0; i < solvedNumber.length; i++) {
					getGuiFrame().getCellList().get(i).setText(Integer.toString(solvedNumber[i]));
				}
				JOptionPane.showMessageDialog(guiFrame.getFrame(), "Best of luck next time! I have solved it for you!");
			}
		});

		guiFrame.getLoadButton().addActionListener(new ActionListener() {

			/**
			 * When the user clicks the Load button, they are prompted to enter a correct
			 * file name with ".txt in the string". Then the loadBoard() function from the
			 * model is called to update the GUI with the imported text file Puzzle
			 * 
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				String fileName = JOptionPane.showInputDialog(guiFrame.getFrame(),
						"Enter the name of the Sudoku File you wish to load", null);
				model.loadBoard(fileName);
			}
		});

		guiFrame.getCustomButton().addActionListener(new ActionListener() {

			/**
			 * This button gives the option to the user to enter their own custom puzzle by
			 * filling in the cells of the GUI. Once they fill the board and click custom,
			 * the board populates with zeros in the empty spaces.
			 * 
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				String text = "";
				for (int i = 0; i < guiFrame.getCellList().size(); i++) {
					if (guiFrame.getCellList().get(i).getText().equals("")) {
						text += 0;
					} else {
						text += guiFrame.getCellList().get(i).getText();
					}
				}
				model.preProcess(text);
			}
		});
	}

	public void displayErrorFile() {
		JOptionPane.showMessageDialog(guiFrame, "Please check your file name!", "Alert", JOptionPane.WARNING_MESSAGE);
	}

	public void displayErrorSize() {
		JOptionPane.showMessageDialog(guiFrame, "The file has an incorrect format!", "Alert",
				JOptionPane.WARNING_MESSAGE);
	}

	public void displaySolvingError() {
		JOptionPane.showMessageDialog(guiFrame, "This puzzle cannot be solved", "Alert", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * assignControllers() establishes the two way composition in our design pattern
	 * where the model and controller are referenced to each other
	 */
	private void assignController() {
		SudokuModel model = new SudokuModel();
		setModel(model);
		model.setViewControl(this);

	}

	private void setModel(SudokuModel model) {
		this.model = model;
	}

	public SudokuGUI getGuiFrame() {
		return guiFrame;
	}

	public void setGuiFrame(SudokuGUI guiFrame) {
		this.guiFrame = guiFrame;
	}

	public static void main(String args[]) {
		SudokuViewController start = new SudokuViewController();

	}

}
