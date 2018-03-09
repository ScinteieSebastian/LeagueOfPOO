package main;

public class RogueAbilities extends Abilities {
    protected final int mod1 = 200;
    protected final int mod2 = 40;
    protected int dmg1 = mod1;
    protected int dmg2 = mod2;
    protected int contor = 0;
    public static final RogueAbilities INSTANCE = new RogueAbilities();

    public RogueAbilities() {
        super();
    }

    public final int modify(final Player enemyPlayer) {
        final int mod3 = 120;
        final int mod4 = 90;
        final int mod5 = 125;
        int modify = 0;
        switch (enemyPlayer.tipe) {
        case "R":
            modify = mod3;
            break;
        case "K":
            modify = mod4;
            break;
        case "P":
            modify = mod5;
            break;
        case "W":
            modify = mod5;
            break;
        default:
            break;
        }
        return modify;
    }

    public final int modify2(final Player enemyPlayer) {
        final int mod6 = 90;
        final int mod7 = 80;
        final int mod8 = 120;
        final int mod9 = 125;
        int modify = 0;
        switch (enemyPlayer.tipe) {
        case "R":
            modify = mod6;
            break;
        case "K":
            modify = mod7;
            break;
        case "P":
            modify = mod8;
            break;
        case "W":
            modify = mod9;
            break;
        default:
            break;
        }
        return modify;
    }

    public final void incContor() {
        this.contor++;
    }

    public final void levelUp(final int howMany) {
        final int mod10 = 20;
        final int mod11 = 10;
        this.dmg1 = this.dmg1 + mod10 * howMany;
        this.dmg2 = this.dmg2 + mod11 * howMany;
    }

    // returneaza damage-ul curent si din 3 in 3 runde returneaza un damage critic
    // daca
    // eroul se afla pe Woods
    public final float backstab(final Player enemyPlayer, final Map map) {
        final int mod12 = 150;
        final int mod13 = 3;
        final int mod14 = 100;
        if (map.getGround(enemyPlayer.linePosition, enemyPlayer.colPosition) == 'W'
                && this.contor % mod13 == 0) {
            return ((float) (dmg1 * mod12) / mod14 * ((float) this.modify(enemyPlayer) / mod14));
        } else {
            return ((float) dmg1 * ((float) this.modify(enemyPlayer) / mod14));
        }
    }

    // returneaza un damage curent si seteaza Dot numarul de runde si
    // eroul inamic pe stun
    public final float paralysis(final Player enemyPlayer, final Map map) {
        final float mod15 = (float) 1.15;
        final int mod16 = 100;
        final int mod666 = 6;
        if (map.getGround(enemyPlayer.linePosition, enemyPlayer.colPosition) == 'W') {
            enemyPlayer.setRoundDot(mod666);
            enemyPlayer.setDot(Math.round((float) mod15 * (float) dmg2
                    * (float) this.modify2(enemyPlayer) / mod16));
            enemyPlayer.setStunDot(1);
        } else {
            final int mod33 = 3;
            enemyPlayer.setRoundDot(mod33);
            enemyPlayer.setDot(Math.round(dmg2 * (float) this.modify2(enemyPlayer) / mod16));
            enemyPlayer.setStunDot(1);
        }
        return ((float) dmg2 * (float) this.modify2(enemyPlayer) / mod16);
    }

    public final int getTotalDmg(final Player enemyPlayer, final Map map) {
        return Math.round(this.backstab(enemyPlayer, map) + this.paralysis(enemyPlayer, map));
    }

    // returneaza damage-ul totala fara modificatorul de rasa si din 3 in 3 runde
    // returneaza damage-ul cu critica de pe backstab
    public final int getDmgWithoutModify(final Player enemyPlayer, final Map map) {
        final int mod18 = 3;
        final float mod19 = (float) 1.5;
        if (map.getGround(enemyPlayer.linePosition, enemyPlayer.colPosition) == 'W'
                && this.contor % mod18 == 0) {
            return Math.round((float) mod19 * dmg1 + dmg2);
        } else {
            return this.dmg1 + dmg2;
        }
    }

    public final float getTotalDmgFloat(final Player enemyPlayer, final Map map) {
        return (this.backstab(enemyPlayer, map) + this.paralysis(enemyPlayer, map));
    }
}
