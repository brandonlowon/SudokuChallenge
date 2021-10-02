import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * SudokuModel class represents the model class in our model-view-controller
 * design pattern. This class will contain the data members and functions to
 * execute the reading of text files, populating of the 2D int array to
 * represent the sudoku puzzle and finally the method to solve the puzzle.
 * 
 * @author Identifier 629
 *
 */
public class SudokuModel implements ISudokuChallenge {
	private SudokuViewController viewControl;
	public int[][] board;
	public int[][] solvedBoard;
	public final int rows = 9;
	public final int columns = 9;

	public SudokuModel() {
		this.board = new int[rows][columns];
		this.solvedBoard = new int[rows][columns];
	}

	public void setViewControl(SudokuViewController viewControl) {
		this.viewControl = viewControl;
	}

	/**
	 * loadBoard method parses a fileName inputed by the user and creates a raw
	 * String from the text file.
	 * 
	 * @param fileName is the name of the text file
	 */
	public void loadBoard(String fileName) {
		String text = "";
		try {
			Scanner myReader = new Scanner(new File(fileName));
			while (myReader.hasNextLine()) {
				text += myReader.nextLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			getViewControl().displayErrorFile();
			return;

		}

		preProcess(text);
	}

	/**
	 * preProcess method will clean up the string from the text file, removing any
	 * all non-integer chars and replacing 'empty spaces' with zeroes.
	 * 
	 * @param text is the string from the text file.
	 */
	public void preProcess(String text) {
		String replacedPeriod = text.replace(".", "0");
		String removeChars = replacedPeriod.replaceAll("[-+\n|\\s]", "");
		// We call the provided Parse method if false, another popup occurs letting the
		// user know of an error in the provided text file.
		if (Parse(removeChars, getBoard())) {

		} else {
			getViewControl().displayErrorSize();
		}
	}

	/**
	 * 
	 * @param content is the body of text to parse into a Sudoku board.
	 * @param board   is to place the board parsed from content.
	 * @returns - TRUE if parse was successful, FALSE if content is not valid input.
	 */
	@Override
	public boolean Parse(String content, int[][] board) {
		String[] list = content.split("");

		for (int i = 0; i < list.length; i++) {
			try {
				Integer.parseInt(list[i]);
			} catch (NumberFormatException e) {
				return false;
			}
		}
		if (list.length != 81) {
			return false;
		} else {
			int listIndex = 0;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					board[i][j] = Integer.parseInt(list[listIndex++]);
				}
			}

			for (int i = 0; i < list.length; i++) {
				getViewControl().getGuiFrame().getCellList().get(i).setText(list[i]);
			}
			return true;
		}
	}

	/**
	 * triggerSolve() method is called when the user clicks on the Solve button on
	 * the GUI. It calls the Solve method and after obtaining the solved sudoku
	 * board in 2D int array form, it converts it to a readable list for the GUI to
	 * update on the screen.
	 * 
	 * @return an 81 length list of the solved numbers
	 */
	public int[] triggerSolve() {
		int[] solvedNumber = new int[81];
		int solvedNumberIndex = 0;
		if (Solve(getBoard(), getSolvedBoard())) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					solvedNumber[solvedNumberIndex++] = getSolvedBoard()[i][j];
				}
			}
		} else {
			getViewControl().displaySolvingError();
		}
		return solvedNumber;
	}

	/**
	 * @param inputBoard  is an incomplete/unsolved Sudoku board.
	 * @param solvedBoard is where to place the complete/solved board.
	 * @return TRUE if a solution was found, FALSE if the puzzle could not be
	 *         solved.
	 */
	@Override
	public boolean Solve(int[][] inputBoard, int[][] solvedBoard) {

		if (solveSudokuBoard(inputBoard)) {
			this.solvedBoard = inputBoard;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * print is use for testing purposes to display our board in the console
	 * 
	 * @param grid is the 2D array representing the puzzle
	 */
	void print(int[][] grid) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				System.out.print(grid[i][j] + " ");
			System.out.println();
		}
	}

	/**
	 * solveSudokuBoard uses a backtracking algorithm from online and incombination
	 * with isSafe method. It becomes a recursive method to "backtrack" and recheck
	 * all the cells once it becomes safe.
	 * 
	 * @param board is the 2D array representing the Sudoku puzzle
	 * 
	 * @return TRUE if the board is solvable, FALSE if the board cannot be solved
	 */
	public boolean solveSudokuBoard(int[][] board) {
		int row = -1;
		int column = -1;
		int n = 9;
		boolean isEmpty = true; // Represents if a cell contains a zero or 'empty'

		
		// Once all the cells are filled in isEmpty will always be true but for the first iteration it will be false
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					row = i;
					column = j;
					isEmpty = false;
					break;
				}
			}
			if (!isEmpty) {
				break;
			}
		}
		
		if (isEmpty) {
			return true;
		}

		
		for (int number = 1; number <= n; number++) {
			if (isSafe(board, row, column, number)) {
				board[row][column] = number;
				if (solveSudokuBoard(board)) {
					return true;
				} else {
					board[row][column] = 0;
				}

			}
		}
		return false;
	}

	/**
	 * isSafe() method is the backtracking algorithm used to fill in the cells.
	 * @param board is the 2D int array representing the Sudoku Puzzle
	 * @param row is the current row number
	 * @param column is the current row number
	 * @param number is the number to insert into the cell
	 * @return TRUE once the board is solved
	 */
	public boolean isSafe(int[][] board, int row, int column, int number) {
		// Checks if number is already present in the rank
		for (int i = 0; i < board.length; i++) {
			if (board[row][i] == number) {
				return false;
			}
		}

		for (int r = 0; r < board.length; r++) {

			// Checks for number is already present in the file.
			if (board[r][column] == number) {
				return false;
			}
		}

		int sqrt = (int) Math.sqrt(board.length);
		int boxRow = row - row % sqrt;
		int boxColumn = column - column % sqrt;

		// In a Sudoku Puzzle each box has a number that we will check to see if the current numbers checks out
		for (int r = boxRow; r < boxRow + sqrt; r++) {
			for (int j = boxColumn; j < boxColumn + sqrt; j++) {
				if (board[r][j] == number) {
					return false;
				}
			}
		}
		return true;
	}

	public SudokuViewController getViewControl() {
		return viewControl;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public int[][] getSolvedBoard() {
		return solvedBoard;
	}

	public void setSolvedBoard(int[][] solvedBoard) {
		this.solvedBoard = solvedBoard;
	}

}