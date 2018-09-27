import java.util.ArrayList<E>;
import java.util.Stack<E>;

/**
* @wizard Santiago Quiroga
*
* This class shows how to create an unddo method, and also tries
* to create a picture of the different types of data structures
* that can be used (ArrayList and Stacks are java representations
* of data structures).
* 
*/
public Class BoardRecorder(){
	//field variables 
	int [][] board;
	ArrayList<int[][]> boardRecorder;
	Stack<int[][]> betterBoardRecorder;

	/**
	 * This is the class default constructor
	 */
	public BoardRecorder(){
		board = new int{
			{2,0,0,0},
			{0,2,0,0},
			{0,0,2,0},
			{0,0,0,2}
		};
		boardRecorder = new ArrayList<ArrayList<int[][]>>();
		betterBoardRecorder = new Stack<ArrayList<int[][]>>();
	}

	//Helper methods 
	
	/**
	* This method will record the state of the current board
	*/ 
	private void record (){
		// Adds the current board to the boardRecorder in order to 
		// save the state of the board at that specific state. 
		boardRecorder.add(currentBoard);
		betterBoardRecorder.push(currentBoard);
	}

	/**
	* This method will set the state of the board to the previous 
	* state. 
	*/
	private void  undo (){
		// Local variables
		int [][] previousBoard;
		
		// To retrieve the decired state of the board, we would have to 
		// get the last value that was stored in the board. ` 
		board = boardRecorder.get(boardRecorder.length() - 1);	
		
		// Since we only read the value of the int[][] object that was stored at
		// that specific position, we have to delete the object, so that we don't 
		// read it twice. 
		boardRecorder.remove(boardRecorder.length() -1);
		
		// To retrieve the last state of the board, we can just use the 
		// pop method, which will remove the object at the top of the stack
		// and returns it. 	
		board = betterBoardRecorder.pop();
	}
}
