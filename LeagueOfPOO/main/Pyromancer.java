package main;

public class Pyromancer extends Player {
    protected final int mod1 = 500;
    protected final int mod2 = 3;
    protected String tipe;
    protected int linePosition;
    protected int colPosition;
    protected int hpInitial = mod1;
    protected int hp = mod1;
    protected int level;
    protected int xp;
    protected boolean isDead;
    protected int[] isAffected = new int[mod2];

    public Pyromancer(final int line, final int col) {
        super("P");
        this.tipe = "P";
        this.linePosition = line;
        this.colPosition = col;
        this.isDead = false;
        this.isAffected[0] = 0;
        this.isAffected[1] = 0;
        this.isAffected[2] = 0;
    }

    public final void move(final char direction) {
        switch (direction) {
        case 'U':
            this.linePosition = this.linePosition - 1;
            break;
        case 'D':
            this.linePosition = this.linePosition + 1;
            break;
        case 'L':
            this.colPosition = this.colPosition - 1;
            break;
        case 'R':
            this.colPosition = this.colPosition + 1;
            break;
        case '_':
        default:
            break;
        }
    }

    public final int groundModify(final Map map) {
        final int mod3 = 125;
        final int mod4 = 100;
        char s = map.getGround(this.linePosition, this.colPosition);
        if (s == 'V') {
            return mod3;
        } else {
            return mod4;
        }
    }

    public final int getInitialHp() {
        return this.hpInitial;
    }

    public final int getHp() {
        return this.hp;
    }

    public final int getLevel() {
        return this.level;
    }

    public final int getXp() {
        return this.xp;
    }

    public final boolean isDead() {
        return this.isDead;
    }

    public final int getLine() {
        return this.linePosition;
    }

    public final int getCol() {
        return this.colPosition;
    }

    public final void setPlayerDead() {
        if (this.getHp() <= 0) {
            this.isDead = true;
            this.hp = 0;
            this.linePosition = -1;
            this.colPosition = -1;
        }
    }

    public final void setXp(final Player enemyPlayer) {
        final int mod5 = 200;
        final int mod6 = 40;
        this.xp = this.xp + Math.max(0, mod5 - (this.level - enemyPlayer.getLevel() * mod6));
    }

    public final int getLevelsUp() {
        final int mod7 = 250;
        final int mod8 = 50;
        int numbOfLevels;
        numbOfLevels = ((this.xp - mod7) / mod8 + 1) - this.level;
        return numbOfLevels;
    }

    public final void levelUp() {
        if (this.getLevelsUp() > 0) {
            final int mod9 = 50;
            this.hpInitial = this.hpInitial + mod9 * this.getLevelsUp();
            this.level = this.level + this.getLevelsUp();
            this.hp = this.hpInitial;
            PyromancerAbilities.INSTANCE.levelUp(this.getLevelsUp());
        }
    }

    public final void takeDmg(final int dmg) {
        this.hp = this.hp - dmg;
    }

    public final void dealDmg(final Player enemyPlayer, final Map map) {
        final int mod10 = 100;
        enemyPlayer.takeDmg(Math.round(
                PyromancerAbilities.INSTANCE.getTotalDmg(enemyPlayer, map)
                * (float) this.groundModify(map) / mod10));
    }

    public final int dealDmgWithoutModify(final Player enemyPlayer, final Map map) {
        final int mod11 = 100;
        return Math.round(PyromancerAbilities.INSTANCE.getDmgWithoutModify(enemyPlayer, map)
                * (float) this.groundModify(map) / mod11);
    }

    public final void setRoundDot(final int x) {
        this.isAffected[0] = x;
    }

    public final void setDot(final int x) {
        this.isAffected[1] = x;
    }

    public final void setStunDot(final int x) {
        this.isAffected[2] = x;
    }

    public final boolean abilityEffect() {
        if (this.isAffected[0] != 0) {
            this.isAffected[0] = this.isAffected[0] - 1;
            this.takeDmg(this.isAffected[1]);
            return !(this.isAffected[2] == 1);
        } else {
            return true;
        }
    }
}
