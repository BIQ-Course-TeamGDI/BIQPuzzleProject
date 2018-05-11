package com.att.biq.puzzle.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.att.biq.puzzle.Piece;
import com.att.biq.puzzle.PuzzleSolver;
import com.att.biq.puzzle.Shape;
import com.att.biq.puzzle.infra.FileManager;

public class ValidatePuzzleSolution {

    public static boolean validate(String input, String solutionFile) throws IOException {
        Piece[][] solutionToCheck = testPuzzleSolutions(input,solutionFile);
        PuzzleSolver puzzleSolver = new PuzzleSolver();
        if(solutionToCheck !=null && puzzleSolver.checkSolution(solutionToCheck)){
            return true;
        } else{
            return false;
        }
    }


    public static Piece[][] testPuzzleSolutions(String input, String solutionFile) throws IOException {
        Piece[][] solutionToCheck=null;
        ArrayList<Piece> pieces = getPieces(input);
        pieces = rotatePiecesDueTOSolutionFile(pieces,solutionFile);
        if (pieces!=null){
            solutionToCheck = getSolutionToCheck(pieces , solutionFile);
        }
        return solutionToCheck;
    }

    private static ArrayList<Piece> rotatePiecesDueTOSolutionFile(ArrayList<Piece> pieces, String solutionFile) {
        ArrayList<Piece> rotatePieces = new ArrayList<>();
        try {
            File file = new File(solutionFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line!=""){
                    String[] sol = line.split(" ");
                    for(String p : sol){
                        try {
                            if (p.contains("[")) {
                                String[] rotP = p.split("\\[");
                                int pId = Integer.parseInt(rotP[0]);
                                int rotation = (Integer.parseInt(rotP[1].substring(0, rotP[1].length() - 1))) / 90;
                                Shape shape = getPieceShape(pId, pieces);
                                rotatePieces.add(new Piece(pId, new Shape(shape.getEdges(), rotation), rotation));
                            } else {
                                int pId = Integer.parseInt(p);
                                Shape shape = getPieceShape(pId, pieces);
                                rotatePieces.add(new Piece(pId, shape, 0));
                            }
                        } catch (Exception e){
                            return null;
                        }
                    }
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotatePieces;
    }

    private static Shape getPieceShape(int pId, ArrayList<Piece> pieces) {
        for(Piece p : pieces) {
            if (p.getId() == pId) {
                return p.getEdges();
            }
        }
        return null;
    }


    private static ArrayList<Piece> getPieces(String input) throws IOException {
        FileManager fileManager = new FileManager(input);
        fileManager.setPiecesFromFile();
        return fileManager.getPieces();
    }


    private static Piece[][] getSolutionToCheck(ArrayList<Piece> pieces, String solutionFile) {
        ArrayList<ArrayList<Integer>> solToCheck= getSolutionFromFile(solutionFile);
        if(solToCheck==null){
            return null;
        }
        Piece[][] sol=null;
        if(solToCheck.isEmpty()){
            return sol;
        }
        sol = new Piece[solToCheck.size()][solToCheck.get(0).size()];
        for (int row=0;row<solToCheck.size();row++){
            for (int col=0;col<solToCheck.get(0).size();col++){
                for(Piece piece : pieces){
                    if (piece.getId()== solToCheck.get(row).get(col)){
                        sol[row][col] = piece;
                        break;
                    }
                }
            }
        }
        return sol;
    }

    private static ArrayList<ArrayList<Integer>> getSolutionFromFile(String solutionFile) {
        ArrayList<ArrayList<Integer>> sol = new ArrayList<>();
        try {
            File file = new File(solutionFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line!=""){
                    ArrayList<Integer> tmp =  new ArrayList<>();
                    String[] piece_ids = line.split(" ");
                    for(String id : piece_ids){
                        try {
                            if (id.contains("[")) {
                                String[] rotP = id.split("\\[");
                                int pId = Integer.parseInt(rotP[0]);
                                tmp.add(pId);
                            } else {
                                tmp.add(Integer.parseInt(id.trim()));
                            }
                        } catch (Exception e){
                            return null;
                        }
                    }
                    sol.add(tmp);
                }
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sol;
    }




}
