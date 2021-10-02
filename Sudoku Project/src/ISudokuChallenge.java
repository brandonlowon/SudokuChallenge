
public interface ISudokuChallenge
{

	// content - The body of text to parse into a Sudoku board.
	// board - Where to place the board parsed from content.
	// Returns - TRUE if parse was successful, FALSE if content is not valid input.
	public boolean Parse(String content, int[][] board);

	// inputBoard - An incomplete/unsolved Sudoku board.
	// solvedBoard - Where to place the complete/solved board.
	// Returns - TRUE if a solution was found, FALSE if the puzzle could not be solved.
	public boolean Solve(int[][] inputBoard, int[][] solvedBoard);

}


