package main;

public class PyromancerAbilities extends Abilities {
    protected final int mod1 = 350;
    protected final int mod2 = 150;
    protected final int mod3 = 50;
    protected int dmg1 = mod1;
    protected int dmg2 = mod2;
    protected int roundDmg = mod3;
    public static final PyromancerAbilities INSTANCE = new PyromancerAbilities();

    public PyromancerAbilities() {
        super();
    }

    public final int modify(final Player enemyPlayer) {
        int modify = 0;
        final int mod4 = 80;
        final int mod5 = 120;
        final int mod6 = 90;
        final int mod7 = 105;
        switch (enemyPlayer.tipe) {
        case "R":
            modify = mod4;
            break;
        case "K":
            modify = mod5;
            break;
        case "P":
            modify = mod6;
            break;
        case "W":
            modify = mod7;
            break;
        default:
            break;
        }
        return modify;
    }

    public final int modify2(final Player enemyPlayer) {
        int modify = 0;
        final int mod8 = 80;
        final int mod9 = 120;
        final int mod10 = 90;
        final int mod11 = 105;
        switch (enemyPlayer.tipe) {
        case "R":
            modify = mod8;
            break;
        case "K":
            modify = mod9;
            break;
        case "P":
            modify = mod10;
            break;
        case "W":
            modify = mod11;
            break;
        default:
            break;
        }
        return modify;
    }

    public final void levelUp(final int howMany) {
        final int mod12 = 50;
        final int mod13 = 20;
        final int mod14 = 30;
        this.dmg1 = this.dmg1 + mod12 * howMany;
        this.dmg2 = this.dmg2 + mod13 * howMany;
        this.roundDmg = this.roundDmg + mod14 * howMany;
    }

    // metoda returneaza o valoare ce reprezinta damage-ul dat
    // de aceasta abilitate in runda curenta
    public final float fireblast(final Player enemyPlayer) {
        final int mod15 = 100;
        return ((dmg1 * (float) modify(enemyPlayer)) / mod15);
    }

    // returneaza damage-ul curent si seteaza Dot si numarul de runde afectate
    public final float ignite(final Player enemyPlayer, final Map map) {
        final int mod16 = 125;
        final int mod17 = 100;
        enemyPlayer.setRoundDot(2);
        if (map.getGround(enemyPlayer.linePosition, enemyPlayer.colPosition) == 'V') {
            enemyPlayer
                    .setDot(Math.round((float) mod16 / mod17 * roundDmg
                            * (float) this.modify2(enemyPlayer) / mod17));
        } else {
            enemyPlayer.setDot(Math.round(roundDmg * (float) this.modify2(enemyPlayer) / mod17));
        }
        enemyPlayer.setStunDot(0);
        return ((dmg2 * (float) this.modify2(enemyPlayer)) / mod17);
    }

    public final int getTotalDmg(final Player enemyPlayer, final Map map) {
        return Math.round(fireblast(enemyPlayer) + ignite(enemyPlayer, map));
    }

    public final int getDmgWithoutModify(final Player enemyPlayer, final Map map) {
        return dmg1 + dmg2;
    }

    public final float getTotalDmgFloat(final Player enemyPlayer, final Map map) {
        return fireblast(enemyPlayer) + ignite(enemyPlayer, map);
    }
}
