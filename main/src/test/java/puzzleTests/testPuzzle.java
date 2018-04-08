package puzzleTests;

import org.junit.jupiter.api.Test;
import infra.FileManagment;
import infra.FileManagmentException;
import puzzle.Piece;
import puzzle.Puzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class testPuzzle {

 @Test
 public void test_3_rows_solution_puzzle() throws IOException, FileManagmentException {
     String ExpectedSolution = "[[4, 7, 3, 6], [5, 2, 9, 10], [8, 11, 12, 1]]";
     String piecesFile = "C:\\Development_Course\\Passover_Project\\GIT_BIQ_Course\\BIQPuzzleProject\\main\\src\\test\\java\\puzzleTests\\resourcesPuzzleFiles\\_3_rows_solution_puzzle_12_pieces.txt";
     FileManagment fileManagment = new FileManagment(piecesFile);
     ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
     Puzzle puzzle = new Puzzle(pieces);
     puzzle.solve();
     puzzle.printSolution();
     String puzzleSolution = Arrays.deepToString(puzzle.getSolution());
     assertEquals(puzzleSolution,ExpectedSolution);
 }

    @Test
    public void NO_solution_puzzle() throws IOException, FileManagmentException {
        String piecesFile = "C:\\Development_Course\\Passover_Project\\GIT_BIQ_Course\\BIQPuzzleProject\\main\\src\\test\\java\\puzzleTests\\resourcesPuzzleFiles\\_No_solution_puzzle_4_pieces.txt";
        FileManagment fileManagment = new FileManagment(piecesFile);
        ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
        Puzzle puzzle = new Puzzle(pieces);
        puzzle.solve();
        puzzle.printSolution();
        assertNull(puzzle.getSolution());
    }

    @Test
    public void Amir_tests() throws IOException, FileManagmentException {
        String piecesFile = "C:\\Development_Course\\Passover_Project\\AmirTests\\AdditionalPuzzleTests\\test15.in";
        FileManagment fileManagment = new FileManagment(piecesFile);
        ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
        Puzzle puzzle = new Puzzle(pieces);
        puzzle.solve();
        puzzle.printSolution();
    }

}
