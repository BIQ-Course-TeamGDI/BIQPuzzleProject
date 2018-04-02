package puzzle;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.log4j.Logger;

public class AnalyzeInputs {

	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	private ArrayList<String> errors = new ArrayList<>();

	public ArrayList<Piece> analyzePicesList(ArrayList<Piece> input) {
		validateEdgesSum(input);
		validatePiecesIds(input);
		validatePiecesFormat(input);
		validateMinimumCorners(input);
		validateMinimumStraightEdges(input);

		return null;

	}

	private void validateMinimumStraightEdges(ArrayList<Piece> input) {
		// TODO Auto-generated method stub

	}

	private void validateMinimumCorners(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		boolean leftTopCorner = false, topRightCorner = false, rightBottomCorner = false, bottomLeftCorner = false;
		int index = 0;

		while(!leftTopCorner && !topRightCorner && !rightBottomCorner && !bottomLeftCorner) {
			if(index > input.size())
				break;
			else {
				//if isPrime == need to check one row corners, else not in the same row 
				if(input.size()==1) {
					
				}
				else if(isPrime(input.size()) ) {
					
				}
				else {
					
				}
			}
			index++;
			
		}

	}

	private boolean isPrime(int size) {
		// TODO Auto-generated method stub
		return false;
	}

	private void validatePiecesFormat(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		for (Piece p : input) {
			if (p.getRight() >= -1 && p.getRight() <= 1 && p.getTop() >= -1 && p.getTop() <= 1 && p.getBottom() >= -1
					&& p.getBottom() <= 1 && p.getLeft() >= -1 && p.getLeft() <= 1) {
				System.out.println("Id: " + p.getId() + ". is not valid!");
			}
		}
	}

	private void validateEdgesSum(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		for (Piece p : input) {
			if (p.getRight() + p.getTop() + p.getBottom() + p.getLeft() != 0) {
				System.out.println("Id: " + p.getId() + ". is not valid!");
				// Logger.error(ErrorsManagment.ERROR_MISSING_ELEMENTS + " ");
			}
		}

	}

	private void validatePiecesIds(ArrayList<Piece> input) {
		// TODO Auto-generated method stub
		Arrays.sort(input.toArray());
		Collections.sort(input);
		if (input.size() != input.indexOf(input.size() - 1)) {
			System.out.println("Missing Ids of pieces...");
			// Need to find out what pieces are missing...
			checkPiecesSequences(input);
		}
	}

	private void checkPiecesSequences(ArrayList<Piece> input) {
		// TODO Auto-generated method stub

	}

}
