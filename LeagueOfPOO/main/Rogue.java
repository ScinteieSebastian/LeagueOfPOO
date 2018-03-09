package main;

public class Rogue extends Player {
    protected final int mod1 = 600;
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

    public Rogue(final int line, final int col) {
        super("R");
        this.tipe = "R";
        this.linePosition = line;
        this.colPosition = col;
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

    public final int getLevelsUp() {
        final int mod3 = 250;
        final int mod4 = 50;
        int numbOfLevels;
        numbOfLevels = ((this.xp - mod3) / mod4 + 1) - this.level;
        return numbOfLevels;
    }

    public final void setPlayerDead() {
        if (this.hp <= 0) {
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

    public final int groundModify(final Map map) {
        char s = map.getGround(this.linePosition, this.colPosition);
        final int mod7 = 115;
        final int mod8 = 100;
        if (s == 'W') {
            return mod7;
        } else {
            return mod8;
        }
    }

    public final void levelUp() {
        final int mod9 = 40;
        if (this.getLevelsUp() > 0) {
            this.hpInitial = this.hpInitial + mod9 * this.getLevelsUp();
            this.level = this.level + this.getLevelsUp();
            this.hp = this.hpInitial;
            KnightAbilities.INSTANCE.levelUp(this.getLevelsUp());
        }
    }

    public final void takeDmg(final int dmg) {
        this.hp = this.hp - dmg;
    }

    public final void dealDmg(final Player enemyPlayer, final Map map) {
        final int mod10 = 100;
        enemyPlayer.takeDmg(Math
                .round(RogueAbilities.INSTANCE.backstab(enemyPlayer, map)
                        * ((float) this.groundModify(map) / mod10))
                + Math.round(RogueAbilities.INSTANCE.paralysis(enemyPlayer, map)
                        * ((float) this.groundModify(map) / mod10)));
    }

    public final int dealDmgWithoutModify(final Player enemyPlayer, final Map map) {
        final int mod11 = 100;
        return Math.round(RogueAbilities.INSTANCE.getDmgWithoutModify(enemyPlayer, map)
                * ((float) this.groundModify(map) / mod11));
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
