import mosaic.*;

import javafx.scene.paint.Color;

public class Exercise4_7 {
    final static int ROWS = 20;
    final static int COLUMNS = 30;
    final static int SQUARE_SIZE = 20;

    static int currentRow;
    static int currentColumn;

    public static void main(String[] args) {
        Mosaic.open(ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE);
        Mosaic.setUse3DEffect(false);

        currentRow = ROWS/2;
        currentColumn = COLUMNS/2;

        // Make all tiles a random color
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                setToRandomColor(j, i);
            }
        }

        // Select random point and copy color to a neighbouring point continuously
        while (true) {
            currentRow = (int)(Math.random()*ROWS);
            currentColumn = (int)(Math.random()*COLUMNS);

            copyColorToRandomNeighbour();
        }
    }

    static void setToRandomColor(int row, int col) {
        int r = (int)(256*Math.random());
        int g = (int)(256*Math.random());
        int b = (int)(256*Math.random());

        Mosaic.setColor(row, col, r, g, b);  
    }

    static void copyColorToRandomNeighbour() {
        Color color = Mosaic.getColor(currentRow, currentColumn);
        randomMove();
        Mosaic.setColor(currentRow, currentColumn, color);
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
