package main;

public class Knight extends Player {
    protected String tipe;
    protected int linePosition;
    protected final int mod4 = 3;
    protected int[] isAffected = new int[mod4];
    protected int colPosition;
    protected final int mod1 = 900;
    protected final int mod2 = 900;
    protected final int mod5 = 200;
    protected final int mod6 = 40;
    protected final int mod7 = 250;
    protected final int mod8 = 50;
    protected final int mod3 = 0;
    protected int hpInitial = mod1;
    protected int hp = mod2;
    protected int currentLevel = mod3;
    protected int level;
    protected int xp;
    protected boolean isDead;

    public Knight(final int line, final int col) {
        super("K");
        tipe = "K";
        this.isDead = false;
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
            break;
        default:
            break;
        }
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

    public final void setPlayerDead() {
        if (this.hp <= 0) {
            this.isDead = true;
            this.hp = 0;
            this.linePosition = -1;
            this.colPosition = -1;
        }
    }

    public final void setXp(final Player enemyPlayer) {
        this.xp = this.xp + Math.max(0, mod5 - (this.level - enemyPlayer.getLevel() * mod6));
    }

    // retuneaza numarul de nivele evoluate de un erou in urma unei lupte
    public final int getLevelsUp() {
        int numbOfLevels;
        numbOfLevels = ((this.xp - mod7) / mod8 + 1) - this.level;
        return numbOfLevels;
    }

    public final int getInitialHp() {
        return this.hpInitial;
    }

    public final int getHp() {
        return this.hp;
    }

    public final int getLine() {
        return this.linePosition;
    }

    public final int getCol() {
        return this.colPosition;
    }

    public final int groundModify(final Map map) {
        char s = map.getGround(this.linePosition, this.colPosition);
        if (s == 'L') {
            final int x = 115;
            return x;
        } else {
            final int x = 100;
            return x;
        }
    }

    public final void levelUp() {
        if (this.getLevelsUp() > 0) {
            final int mod = 80;
            this.hpInitial = this.hpInitial + mod * this.getLevelsUp();
            this.level = this.level + this.getLevelsUp();
            this.hp = this.hpInitial;
            KnightAbilities.INSTANCE.levelUp(this.getLevelsUp());
        }
    }

    public final void takeDmg(final int dmg) {
        this.hp = this.hp - dmg;
    }

    public final void dealDmg(final Player enemyPlayer, final Map map) {
        final int mod = 100;
        enemyPlayer.takeDmg(Math.round(KnightAbilities.INSTANCE.getTotalDmg(enemyPlayer, map)
                * ((float) this.groundModify(map) / mod)));
    }

    public final int dealDmgWithoutModify(final Player enemyPlayer, final Map map) {
        final int mod = 100;
        return Math.round(KnightAbilities.INSTANCE.getDmgWithoutModify(enemyPlayer, map)
                * ((float) this.groundModify(map) / mod));
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
