import mosaic.*;

public class Exercise4_6 {
    final static int ROWS = 20;
    final static int COLUMNS = 30;
    final static int SQUARE_SIZE = 20;

    static int currentRow;
    static int currentColumn;

    public static void main(String[] args) {
        Mosaic.open( ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE );
        Mosaic.setUse3DEffect(false);

        currentRow = ROWS / 2;
        currentColumn = COLUMNS / 2;

        while (true) {
            addGreen(currentRow, currentColumn);
            randomMove();
        }
    }

    static void addGreen(int row, int col) {
        int newGreen = Mosaic.getGreen(row, col) + 25;
        if (newGreen > 255) newGreen = 255;

        Mosaic.setColor(row, col, 0, newGreen, 0);
    }

    static void randomMove() {
        int directionNum = (int)(4*Math.random());

        switch (directionNum) {
            case 0:
                currentRow--;
                if (currentRow < 0) currentRow = ROWS - 1;
                break;
            case 1:
                currentColumn++;
                if (currentColumn >= COLUMNS) currentColumn = 0;
                break; 
            case 2:
                currentRow++;
                if (currentRow >= ROWS) currentRow = 0;
                break;
            case 3:
                currentColumn--;
                if (currentColumn < 0) currentColumn = COLUMNS - 1;
                break; 
        }
    }
}
