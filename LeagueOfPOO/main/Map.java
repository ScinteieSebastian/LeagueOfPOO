package main;

public class Map {
    protected char[][] map;
    protected int k = 0;

    // o matrice de [n][m] plina cu char -uri ele reprezentand tipul de teren
    public Map(final int n, final int m, final String sir) {
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sir.charAt(k);
                k++;
            }
        }
    }

    public final char getGround(final int line, final int col) {
        return map[line][col];
    }
}
