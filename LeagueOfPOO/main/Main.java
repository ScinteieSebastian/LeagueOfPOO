package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import fileio.implementations.FileReader;
import fileio.implementations.FileWriter;;

public class Main {
    protected Main() {
    }
    public static void main(final String[] args) throws IOException {
        final Round round = new Round();
        String fileIn = args[0];
        String fileOut = args[1];
        // realizez citirea din fisier
        try {
            FileReader read = new FileReader(fileIn);
            int n = read.nextInt();
            int m = read.nextInt();
            String s = new String();
            for (int i = 0; i < n; i++) {
                s = s + read.nextWord();
            }
            Map map = new Map(n, m, s);
            int nrPlayers = read.nextInt();
            Player[] players = new Player[nrPlayers];
            for (int i = 0; i < nrPlayers; i++) {
                players[i] = FactoryGenerator.INSTANCE.createPlayer(read.nextWord(),
                        read.nextInt(), read.nextInt());
            }
            // desfasurarea rundelor
            int nrRounds = read.nextInt();
            while (nrRounds != 0) {
                String directions = read.nextWord();
                round.gameRound(players, nrPlayers, map, directions);
                nrRounds--;
            }
            try {
                // scrierea in fisier
                FileWriter write = new FileWriter(fileOut);
                for (int i = 0; i < nrPlayers - 1; i++) {
                    if (players[i].isDead()) {
                        write.writeWord(players[i].tipe + " " + "dead");
                        write.writeNewLine();
                    } else {
                        write.writeWord(players[i].tipe + " " + players[i].getLevel()
                                + " " + players[i].getXp() + " "
                                + players[i].getHp() + " " + players[i].getLine()
                                + " " + players[i].getCol());
                        write.writeNewLine();
                    }
                }
                int i = nrPlayers - 1;
                if (players[i].isDead()) {
                    write.writeWord(players[i].tipe + " " + "dead");
                } else {
                    write.writeWord(players[i].tipe + " " + players[i].getLevel()
                            + " " + players[i].getXp() + " "
                            + players[i].getHp() + " " + players[i].getLine()
                            + " " + players[i].getCol());
                }
                write.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
