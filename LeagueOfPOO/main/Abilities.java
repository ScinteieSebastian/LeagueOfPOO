package main;

//clasa abstracta unde am definit toate metodele necesare pentru abilitatile unui erou
public abstract class Abilities {
    public Abilities() {
    }

    // returneaza in functie de tipul eroului inamic modificatorul e rasa smecific
    // pentru prima abilitate
    public abstract int modify(Player enemyPlayer);;

    // returneaza in functie de tipul eroului inamic modificatorul e rasa smecific
    // pentru a doua abilitate
    public abstract int modify2(Player enemyPlayer);;

    // primeste ca parametru cate nivele a evoluat eroul si creste damage-ul sau
    // procentul
    // abilitatilor cu o valoare stabilita
    public abstract void levelUp(int howMany);;

    // returneaza damage-ul dat de cele doua abilitati fara modificatorul de rasa
    public abstract int getDmgWithoutModify(Player enemyPlayer, Map map);

    // returneaza damage-ul dat de cele doua abilitati cu modificatorul de rasa
    public abstract int getTotalDmg(Player enemyPlayer, Map map);;

    // acelasi lucru ca metoda anterioara numai ca nu mai aplica MAth.round
    // pe suma celor doua abilitati
    public abstract float getTotalDmgFloat(Player enemyPlayer, Map map);;
}
