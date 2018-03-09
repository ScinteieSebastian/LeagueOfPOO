package main;

public class KnightAbilities extends Abilities {
    protected final int mod1 = 200;
    protected final int mod2 = 100;
    protected final int mod3 = 20;
    protected int dmg1 = mod1;
    protected int dmg2 = mod2;
    protected int percentDmg = mod3;
    protected int countPercent;
    protected static final KnightAbilities INSTANCE = new KnightAbilities();

    public KnightAbilities() {
        super();
    }

    public final int modify(final Player enemyPlayer) {
        int modify = 0;
        final int mod4 = 115;
        final int mod5 = 100;
        final int mod6 = 110;
        final int mod7 = 80;
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
        final int mod11 = 115;
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
        final int mod16 = 30;
        final int mod17 = 40;
        this.dmg1 = this.dmg1 + mod16 * howMany;
        this.dmg2 = this.dmg2 + mod17 * howMany;
        if (this.percentDmg + howMany <= mod17) {
            this.countPercent = this.countPercent + howMany;
        } else {
            final int mod12 = 40;
            this.percentDmg = mod12;
            this.countPercent = 0;
        }
        this.percentDmg = this.percentDmg + this.countPercent;
    }

    // metoda execute verifica daca viata curenta e mai mica decat 20%din viata
    // maxima
    // daca nu metoda va returna un dmg egal cu viata ramasa a eroului incamic
    // daca da, va returna o valoare in functie de nivelul eroului curent
    public final int execute(final Player enemyPlayer) {
        final int mod14 = 20;
        final int mod15 = 100;
        if (Math.round((float) (enemyPlayer.getInitialHp()
                * mod14) / mod15) < enemyPlayer.getHp()) {
            return Math.round(dmg1 * (float) modify(enemyPlayer) / mod15);
        } else {
            return Math.round((float) (enemyPlayer.hpInitial * mod14) / mod15);
        }
    }

    // metoda va returna un anumit damage si va seta stun pentru eroul inamic
    public final int slam(final Player enemyPlayer) {
        final int mod13 = 100;
        enemyPlayer.setRoundDot(1);
        enemyPlayer.setDot(0);
        enemyPlayer.setStunDot(1);
        return Math.round((float) (dmg2 * modify2(enemyPlayer)) / mod13);
    }

    public final int getTotalDmg(final Player enemyPlayer, final Map map) {
        return Math.round(execute(enemyPlayer) + slam(enemyPlayer));
    }

    public final int getDmgWithoutModify(final Player enemyPlayer, final Map map) {
        return dmg1 + dmg2;
    }

    public final float getTotalDmgFloat(final Player enemyPlayer, final Map map) {
        return execute(enemyPlayer) + slam(enemyPlayer);
    }
}
