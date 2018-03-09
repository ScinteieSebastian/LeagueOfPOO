package main;

public class Round {

    public Round() {
    }

    // metoda realizeaza lupta dintre doi eroi si eventualele update -uri dupa
    // incheierea luptei
    public final void combat(final Player player1, final Player player2, final Map map) {
        if (!player1.isDead() && !player2.isDead()) {
            player1.dealDmg(player2, map);
            player2.dealDmg(player1, map);
            player1.setPlayerDead();
            player2.setPlayerDead();
            if (player1.isDead()) {
                player2.setXp(player1);
                player2.levelUp();
            } else if (player2.isDead()) {
                player1.setXp(player2);
                player1.levelUp();
            }
            if (player1.tipe == "R" || player2.tipe == "R") {
                if (map.getGround(player1.linePosition, player1.colPosition) == 'W') {
                    RogueAbilities.INSTANCE.incContor();
                }
            }
        }
    }

    // metoda realizeaza desfasurarea unei runde ea actualizand Dot, miscarea
    // eroilor
    // si lupta eroilor de pe aceeasi pozitie de pe harta
    public final void gameRound(final Player[] players, final int numbPlayers,
            final Map map, final String directions) {
        for (int i = 0; i < numbPlayers; i++) {
            if (players[i].abilityEffect()) {
                players[i].move(directions.charAt(i));
            }
            if (players[i].getHp() <= 0) {
                players[i].setPlayerDead();
            }
        }
        for (int i = 0; i < numbPlayers; i++) {
            for (int j = i + 1; j < numbPlayers; j++) {
                if (players[i].getLine() == players[j].getLine()
                        && players[i].getCol() == players[j].getCol()) {
                    this.combat(players[i], players[j], map);
                }
            }
        }
    }
}
