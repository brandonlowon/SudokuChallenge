import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class SudokuViewController {
	private SudokuGUI guiFrame;
	private SudokuModel model;

	public SudokuViewController() {
		guiFrame = new SudokuGUI();
		assignController();
		addListeners();
	}

	public void addListeners() {
		guiFrame.getClearButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < guiFrame.getCellList().size(); i++) {
					guiFrame.getCellList().get(i).setText("");
				}
			}
		});

		guiFrame.getSolveButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				model.triggerSolve();

			}

		});

		guiFrame.getNewButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String fileName = JOptionPane.showInputDialog(guiFrame.getFrame(),
						"Enter the name of the Sudoku File you wish to load", null);
				model.loadBoard(fileName);
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
		JOptionPane.showMessageDialog(guiFrame, "This puzzle cannot be solved", "Alert",
				JOptionPane.WARNING_MESSAGE);
	}

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
