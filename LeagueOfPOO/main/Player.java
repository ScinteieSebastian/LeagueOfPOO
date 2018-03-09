package main;

//din aceasta clasa vom deriva toti eroi, ea contine toate metodele necesare pentru functionarea
//corecta a unui erou
public abstract class Player {
    protected String tipe;
    protected int linePosition;
    protected int colPosition;
    protected int hpInitial;
    protected int hp;
    protected int level;
    protected int xp;
    protected boolean isDead;
    protected final int mod1 = 3;
    protected int[] isAffected = new int[mod1];

    public Player(final String tipe) {
        this.tipe = tipe;
        this.level = 0;
        this.xp = 0;
        this.isDead = false;
    };

    // returneaza o valoare in functie de pozitia eroului pe harta si
    // tipul de teren de pe acea pozitie
    public abstract int groundModify(Map map);;

    // functia de miscare, ea modifica coordonatele unui erou in functie de ce
    // string primeste
    public abstract void move(char direction);;

    // urmatoare functii sunt getters pentru membri claselor derivate din Player
    public abstract int getInitialHp();;

    public abstract int getHp();;

    public abstract int getXp();;

    public abstract int getLevel();;

    public abstract int getLevelsUp();;

    public abstract int getLine();;

    public abstract int getCol();;

    // aceasta metoda verifica daca un erou e mort si daca e seteaza booleanul
    // isDead pe true
    // si scoate eroul de pe harta
    public abstract void setPlayerDead();;

    // creste xp-ul eroului dupa o lupta castigata
    public abstract void setXp(Player enemyPlayer);;

    // returneaza booleanul isDead
    public abstract boolean isDead();;

    // aceasta metoda se ocupa de abilitatile cu efect prelungit
    // pentru fiecare erou salvez intr-un vector cate runde se aplica efectul, cat
    // damage
    // trebuie sa primeasca si daca a primit stun sau nu
    public abstract boolean abilityEffect();;

    // metode setters pentru vectorul in care salvez datele pentru abilitatile cu
    // efect prelungit
    public abstract void setRoundDot(int x);;

    public abstract void setDot(int x);;

    public abstract void setStunDot(int x);;

    // metoda verifica daca eroul are destul xp pentru a face levelup si daca da
    // creste
    // damage ul de pe abilitati viata initiala si viata curenta se reseteaza
    public abstract void levelUp();;

    // metoda calculeaza damage-ul total dat de cele doua abilitati ale eroului si
    // scade aceasta valoare din viata curenta a inamicului
    public abstract void dealDmg(Player enemyPlayer, Map map);;

    // returneaza dmage-ul dat de cele doua abilitati fara a mai fi amplificate de
    // raceModify
    public abstract int dealDmgWithoutModify(Player enemyPlayer, Map map);;

    // scade din viata curenta damage-ul primit de la adversar
    public abstract void takeDmg(int dmg);;
}
