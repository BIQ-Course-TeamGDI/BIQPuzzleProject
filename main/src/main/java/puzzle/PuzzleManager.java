package puzzle;

import infra.FileManager;

import java.io.IOException;

public class PuzzleManager {

    private String inputPiecesFile;
    private String outPutFile;

    public PuzzleManager(String inputPiecesFile, String outPutFile) {
        this.inputPiecesFile = inputPiecesFile;
        this.outPutFile = outPutFile;
    }

    public void solvePuzzle() throws IOException {
        FileManager fileManager = new FileManager(inputPiecesFile);
        fileManager.getPiecesFromFile();
        if (fileManager.getAllErrors().size()!=0){
            fileManager.printErrorsToFile(outPutFile);
            return;
        }
        AnalyzeInputs analyzeInputs = new AnalyzeInputs(fileManager.getPiecesFromFile());
        analyzeInputs.analyzePicesList();
        if (fileManager.getAllErrors().size()!=0){
            fileManager.printErrorsToFile(outPutFile);
            return;
        }
        Puzzle puzzle = new Puzzle(fileManager.getPiecesFromFile(),analyzeInputs.getSolutionPossibleRows());
        puzzle.solve();
        puzzle.save(outPutFile);
    }
}
