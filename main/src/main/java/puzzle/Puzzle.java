package puzzle;

import java.util.ArrayList;

public class Puzzle {


    private ArrayList<Piece> puzzlePieces;


    public Puzzle(ArrayList<Piece> puzzlePieces ){
        this.puzzlePieces=puzzlePieces;
    }


    public int size(){
        return puzzlePieces.size();
    }

    public ArrayList<Piece> getPuzzlePieces() {
        return puzzlePieces;
    }
}
