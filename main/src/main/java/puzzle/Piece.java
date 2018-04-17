package puzzle;

/**
 * 
 * @author Guy Bitan
 *
 */
public class Piece implements Comparable<Piece> {

	public static int numOfElements = 0;
	private int id;
	int[] piece = new int[4];

	public Piece(int id, int[] piece) {
		this.id = id;
		this.piece = piece;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBottom() {
		return piece[3];
	}

	public int getTop() {
		return piece[1];
	}

	public int getLeft() {
		return piece[0];
	}

	public int getRight() {
		return piece[2];
	}

	@Override
	public int compareTo(Piece piece) {
		return getId().compareTo(piece.getId());
	}

	@Override
	public boolean equals(Object obj) {
		return id == ((Piece) obj).id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
