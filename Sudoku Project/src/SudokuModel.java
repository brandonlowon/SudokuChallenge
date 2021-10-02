import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	public void loadBoard(String fileName) {
		String text = "";
		try {
			text = new String(Files.readAllBytes(Paths.get(fileName)));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			getViewControl().displayErrorFile();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			getViewControl().displayErrorFile();
			return;
		}

		createGrid(text);
	}

	public void createGrid(String text) {
		String replacedPeriod = text.replace(".", "0");
		String removeChars = replacedPeriod.replaceAll("[-+\n|\\s]", "");
		if (Parse(removeChars, getBoard())) {

		} else {
			getViewControl().displayErrorSize();
		}
	}

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
			for(int i=0; i<rows; i++) {
				for(int j = 0; j < columns; j++) {
					board[i][j] = Integer.parseInt(list[listIndex++]);
				}
			}
			
			for (int i = 0; i < list.length; i++) {
				getViewControl().getGuiFrame().getCellList().get(i).setText(list[i]);
			}
			return true;
		}
	}

	public void triggerSolve() {
		print(getBoard());
		print(getSolvedBoard());
		Solve(getBoard(), getSolvedBoard());
		int[] solvedNumber = new int[81];
		int solvedNumberIndex = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				solvedNumber[solvedNumberIndex++] = getSolvedBoard()[i][j];
			}
		}
	
		for (int i = 0; i < solvedNumber.length; i++) {
			getViewControl().getGuiFrame().getCellList().get(i).setText(Integer.toString(solvedNumber[i]));
		}
	}

	@Override
	public boolean Solve(int[][] inputBoard, int[][] solvedBoard) {

		if (solveSudoku(inputBoard, 9)) {
			this.solvedBoard = inputBoard;
			return true;
		} else {
			getViewControl().displaySolvingError();
			return false;
		}
	}

	void print(int[][] grid) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				System.out.print(grid[i][j] + " ");
			System.out.println();
		}
	}

	public boolean solveSudoku(int[][] board, int n) {
		int row = -1;
		int col = -1;
		boolean isEmpty = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					row = i;
					col = j;
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

		for (int num = 1; num <= n; num++) {
			if (isSafe(board, row, col, num)) {
				board[row][col] = num;
				if (solveSudoku(board, n)) {
					// print(board, n);
					return true;
				} else {
					// replace it
					board[row][col] = 0;
				}
			}
		}
		return false;
	}

	public static boolean isSafe(int[][] board, int row, int col, int num) {
		for (int d = 0; d < board.length; d++) {
			if (board[row][d] == num) {
				return false;
			}
		}

		for (int r = 0; r < board.length; r++) {

			if (board[r][col] == num) {
				return false;
			}
		}

		int sqrt = (int) Math.sqrt(board.length);
		int boxRowStart = row - row % sqrt;
		int boxColStart = col - col % sqrt;

		for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
			for (int d = boxColStart; d < boxColStart + sqrt; d++) {
				if (board[r][d] == num) {
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