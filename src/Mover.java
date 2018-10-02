
/**
 * @Wizard Santiago Quiroga
 *
 * This class was made to help students learn how to move tiles in a two dimensional array.
 * Please use this  a tool for learning !! don't share this code with others unless they are
 * willing to abide by the same rule of learning. Wile I know this is not the best way write
 * java, I think it does depict how methods can be broken into pieces to ease the coding blocks
 * and make code much more readable. read only moveOver, moveLeft, and combine. The rest is just
 * extra stuff that can be used to learn other interesting things, but doesn't really help
 * understand the main concept of this class.
 */
public class Mover {
  //Field variable for my board
   private int [][] board;

  /**
   * Default constructor
   */
  public Mover() {
    //Instantiates our board with different scenarios
    //[rows][columns]
    board = new int[][]{
        {2,2,2,2}, //This segment is a row [0]
        {0,2,0,2}, //This segment is a row [1]
        {2,2,0,2}, //This segment is a row [2]
        {2,0,0,2}, //This segment is a row [3]
    };

    //The interesting thing about this is that the best way to get the column would be by getting the values through a for loop :(
    //but we can get the rows very fast.  So even though I can get the segment that I call column by just using the segment index
    // 0,1,2,3. I cannot do the same with columns by. eg. I cannot do this :
    //column = board[][0];
    //To get a column, you would have to iterate through that particular set of indexes in the board.
    //eg if I wanna get column 2 {2,0,0,0} I can do
//    int columnIndex = 2;
//    int[] segment  = new int[4];
//
//    //I get the third column that is equibalent to {2,0,0,0}
//    for (int row = 0; row< board.length; ++row){
//        segment[row] = board[row][columnIndex];
//    }
//
//    //Ignore this, this is just to make the segment a pretty string.
//    StringBuilder formattedSegment = new StringBuilder("[");
//    for(int index = 0; index < segment.length; ++index){
//      formattedSegment
//          .append((index != segment.length - 1) ? segment[index] + ", " : segment[index] + "]");
//    }
//
//    //Here is the segment that I got from the loop in line 33.
//    System.out.println(formattedSegment);

    //text
    System.out.println("\nInitial board:\n");
    printBoard();
    System.out.println();
  }

  /**
   * Moves tiles left
   * @return int[][]
   */
  public void moveLeft() {
     //I'm going to iterate through the segments of the array from top to bottom
    for (int currentColumnIndex = 0; currentColumnIndex < board.length; ++currentColumnIndex){
      //Define the temporary segment
      int[] currentSegment;

      //Now I'm going to  get all my non zero tiles for current specific segment (in this case the  current row), and store it into the currentSegment.
      currentSegment= moveOver(board[currentColumnIndex]);

      //Now I'll combine my current segment and update it
      currentSegment = combine (currentSegment);

      //Now I'll move everything in the current segment to the desired side (in this case from  right to left), and I will update the state of the current segment.
      currentSegment = moveOver(currentSegment);



      //At last I will update the state of my current column.
      board[currentColumnIndex] = currentSegment;
    }
  }

  //TODO: comment
  public void moveUp(){
    for (int currentColumnIndex = 0; currentColumnIndex < board.length; ++currentColumnIndex){
      int[] currentSegment;
      
      currentSegment = getColumn(currentColumnIndex);
      
      currentSegment = moveOver(currentSegment);
      
      currentSegment = combine(currentSegment);
      
      currentSegment = moveOver(currentSegment);

      setColumn(currentColumnIndex, currentSegment);
    }
  }
  
  


  /**
   * Printing method
   * This method prints the board in a way that depicst the way it would look
   * based on my standards of rows and columns.
   */
  private void printBoard(){
    //I calculate the actual array bound, since the actual array bound is different from its size.
    int indexBound = board.length - 1;

    //I iterate through the array from left to right.
    for (int[] aBoard : board) {
      System.out.print("[");

      //I iterate through the array from top to bottom.
      for (int row = 0; row < aBoard.length; ++row) {

        //I check if I'm at the edge of the array
        if (row != indexBound) {
          System.out.print(aBoard[row] + ", ");
        } else {
          System.out.print(aBoard[row]);
        }
      }
      System.out.print("]\n");
    }
  }

  //helper methods

  /**
   * This method will move everything in the array from right to left
   * eg
   * @param segment a one dimensional
   * @return
   */
  private int [] moveOver(int [] segment){
    //local variables
    int emptySpaces = 0;

      //Iterate through the segment from left to right
      for (int index = 0; index < segment.length; ++index){
          //if the current array is empty I add one to my count
          if(segment[index] == 0){
            ++emptySpaces;
          }else{
            if(emptySpaces != 0) {
              //I move the current nonzero value the number of empty spaces that I counted
              segment[index - emptySpaces] = segment[index];
              //Since I moved this value x spaces I will now place a zero in its place
              segment[index] = 0;

              // This takes into account that I only moved one tile
              emptySpaces -= 1;
            }
          }

      }
    return  segment;
  }

  private int [] combine(int[] segment){
    //I iterate through the segment from ledt to right.
    for (int index = 0; index < board.length; ++index){

      //I use a try catch here to illustrate a different way to take into account that we are at the edge of the array
      //When we are at the edge of the array, and we should not look for the next index, since it will throw an IndexOutOfBounds
      //However, I know that only happens when I'm on the last iteration of y loop, so I actually take advantage of java's built in error handling
      // and use it to handle that special case. this might not be the nicest way to write code, but I think is cool to use this too.
      try {
        //I check if the adjacent tiles are equal
        if (segment[index] == segment[index + 1]) {

          //Since the tiles add their own value to each other, I can just multiply it by two.
          segment[index] *= 2;

          //Since I added the tiles, I have to make the one over and empty tile.
          segment[index + 1] = 0;
        }
      }catch(IndexOutOfBoundsException error){
//        System.out.println("We are currently at the edge of the array");
      }
    }

    return segment;
  }


  //TODO: comment
  private int [] getColumn(int columnIndex){
    int [] column = new int[board[columnIndex].length];

    for (int index = 0;  index < board[columnIndex].length; ++index){
        column[index] = board[index][columnIndex];
    }

    return column;
  }


  //TODO: comment
  private void  setColumn(int columnIndex,int [] columnSegment){
    for (int index = 0; index < board[columnIndex].length; ++index){
      board[index][columnIndex] = columnSegment[index];
    }
  }


  /**
   * main method.
   * @param args
   */
  public static void main(String[] args) {
    Mover test = new Mover();

//    System.out.println("MoveLeft()");
//    test.moveLeft();

    System.out.println("MoveUp()");
    test.moveUp();

    System.out.println();
    test.printBoard();

    //the printBoard is a great method for seeing the step by step of what exactly happens during each step
  }
}
