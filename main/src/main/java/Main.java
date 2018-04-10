import infra.FileManagment;
import puzzle.AnalyzeInputs;
import puzzle.Piece;
import puzzle.Puzzle;

import java.io.*;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {
        String inputPiecesFile = "";
        String outPutFile = "";
        FileManagment fileManagment = new FileManagment(inputPiecesFile);
        ArrayList<Piece> pieces = fileManagment.getPicesFromFile();
        if (pieces==null || pieces.size()==0){
            return;
        }
        ArrayList<Integer> posibleSolutionsRows = AnalyzeInputs.analyzePicesList(pieces, outPutFile);
        if (posibleSolutionsRows==null || posibleSolutionsRows.size()==0){
            return;
        }
        Puzzle puzzle = new Puzzle(pieces,posibleSolutionsRows);
        puzzle.solve();
        printSolutionToFile(puzzle,outPutFile);
    }


    private static void printSolutionToFile(Puzzle puzzle, String outPutFile) {
        File fout = new File(outPutFile);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(puzzle.solutionToString());
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
