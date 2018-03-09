package main;

//aceasta clasa are rolul de a genera un erou de un anumit tip
public final class FactoryGenerator {
    // am creat in interiorul clasei un obiect static final de tip clasa
    // pentru a o putea instantia fara a o mai instantia
    public static final FactoryGenerator INSTANCE = new FactoryGenerator();

    private FactoryGenerator() {
    }

    // primeste ca parametru un string care va defini tipul eroului si pozitia pe
    // harta
    public Player createPlayer(final String strategy, final int line, final int col) {
        if (strategy.equals("P")) {
            return new Pyromancer(line, col);
        } else if (strategy.equals("K")) {
            return new Knight(line, col);
        } else if (strategy.equals("W")) {
            return new Wizard(line, col);
        } else if (strategy.equals("R")) {
            return new Rogue(line, col);
        } else {
            return null;
        }
    }
}
