package main;

public class WizardAbilities extends Abilities {
    protected final int mod10 = 100;
    protected final float mod1 = (float) 0.2;
    protected final float mod2 = (float) 0.35;
    protected float percent1 = (float) mod1;
    protected float percent2 = (float) mod2;
    protected float dmg1;
    protected float dmg2;
    protected boolean isAffected = false;
    public static final WizardAbilities INSTANCE = new WizardAbilities();

    public WizardAbilities() {
        super();
    }

    public final int modify(final Player enemyPlayer) {
        int modify = 0;
        final int mod3 = 80;
        final int mod4 = 120;
        final int mod5 = 90;
        final int mod6 = 105;
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
            modify = mod6;
            break;
        default:
            break;
        }
        return modify;
    }

    public final int modify2(final Player enemyPlayer) {
        int modify = 0;
        final int mod7 = 120;
        final int mod8 = 140;
        final int mod9 = 130;
        switch (enemyPlayer.tipe) {
        case "R":
            modify = mod7;
            break;
        case "K":
            modify = mod8;
            break;
        case "P":
            modify = mod9;
            break;
        case "W":
            modify = 0;
            break;
        default:
            break;
        }
        return modify;
    }

    public final void levelUp(final int howMany) {
        final float mod11 = (float) 0.05;
        final float mod12 = (float) 0.7;
        final float mod13 = (float) 0.02;
        this.percent1 = (float) (this.percent1 + mod11 * howMany);
        if (this.percent2 <= mod12) {
            if ((this.percent2 + mod13 * howMany) <= mod12) {
                this.percent2 = (float) (this.percent2 + mod13 * howMany);
            } else {
                this.percent2 = (float) mod12;
            }
        }
    }

    // returneaza damage-ul curent el fiind un procent din viata adversarului
    public final float drain(final Player enemyPlayer) {
        final float mod14 = (float) 0.3;
        final int mod15 = 3;
        final int mod16 = 10;
        if ((mod14 * enemyPlayer.getInitialHp()) <= enemyPlayer.getHp()) {
            return (this.percent1 * (float) this.modify(enemyPlayer) / mod10
                    * (float) enemyPlayer.getInitialHp()
                    * mod15 / mod16);
        } else {
            return (this.percent1 * (float) modify(enemyPlayer) / mod10 * enemyPlayer.getHp());
        }
    }

    // calculeaza damage-ul adversarului fara modificatorii de rasa
    // si returneaza un procent din acesta
    public final float deflect(final Player enemyPlayer, final Map map) {
        return ((float) modify2(enemyPlayer) / mod10
                * Math.round((float) this.percent2
                        * (float) enemyPlayer.dealDmgWithoutModify(enemyPlayer, map)));
    }

    public final float getTotalDmgFloat(final Player enemyPlayer, final Map map) {
        return (this.drain(enemyPlayer) + this.deflect(enemyPlayer, map));
    }

    public final int getDmgWithoutModify(final Player enemyPlayer, final Map map) {
        if (this.modify2(enemyPlayer) == 0) {
            return Math.round((float) drain(enemyPlayer) * (float) mod10 / modify(enemyPlayer));
        }
        return Math.round((float) drain(enemyPlayer) * (float) mod10 / modify(enemyPlayer)
                + (float) deflect(enemyPlayer, map) * mod10 / (float) modify2(enemyPlayer));
    }

    public final int getTotalDmg(final Player enemyPlayer, final Map map) {
        return Math.round(this.drain(enemyPlayer) + this.deflect(enemyPlayer, map));
    }
}
