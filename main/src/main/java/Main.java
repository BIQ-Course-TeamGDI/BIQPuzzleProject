import puzzle.PuzzleManager;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        args[0] = "";
        args[1] = "";
        PuzzleManager puzzleManager = new PuzzleManager(args[0],args[1]);
        puzzleManager.solvePuzzle();
    }

}
