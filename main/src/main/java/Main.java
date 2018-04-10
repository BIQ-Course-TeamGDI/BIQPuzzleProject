import infra.FileManagment;
import puzzle.AnalyzeInputs;
import puzzle.Piece;
import puzzle.Puzzle;

import java.io.*;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {
//        String inputPiecesFile = args[0];
//        String outPutFile = args[1];
        String inputPiecesFile = "C:\\Development_Course\\Passover_Project\\AmirTests\\AdditionalPuzzleTests\\test1.in";
        String outPutFile = "C:\\Development_Course\\Passover_Project\\AmirTests\\AdditionalPuzzleTests\\test1_doron.out";
        FileManagment fileManagment = new FileManagment(inputPiecesFile);
        ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
        if (pieces==null || pieces.size()==0){
            return;
        }
        ArrayList<Integer> posibleSolutionsRows = AnalyzeInputs.analyzePicesList(pieces,outPutFile);
        if (posibleSolutionsRows==null || posibleSolutionsRows.size()==0){
            return;
        }
        Puzzle puzzle = new Puzzle(pieces,posibleSolutionsRows);
        puzzle.solve();
        puzzle.save(outPutFile);
    }

}
