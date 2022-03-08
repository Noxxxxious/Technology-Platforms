package app;

public class Presentation {
    public static void main(String[]args){
        Presentation presentation = new Presentation();
        MageLeague mage_league = new MageLeague(0);
        presentation.init(mage_league);
        mage_league.printMages();
        mage_league.printMageStats();
    }

    public void init(MageLeague mage_league){
        Mage Anivia = new Mage("Anivia", 1, 2.71);
        Mage Ryze = new Mage("Ryze", 2, 3.14);
        Mage Vladimir = new Mage("Vladimir", 3, 4.92);
        Mage Malzahar = new Mage("Malzahar", 4, 3.36);
        Mage Swain = new Mage("Swain", 5, 1.85);
        Mage Xerath = new Mage("Xerath", 6, 4.47);
        Mage Lux = new Mage("Lux", 7, 2.03);
        Mage Ziggs = new Mage("Ziggs", 8, 1.73);
        Mage Ahri = new Mage("Ahri", 9, 2.96);
        Mage LeBlanc = new Mage("LeBlanc", 10, 4.38);
        Mage Syndra = new Mage("Syndra", 11, 3.67);
        Mage Veigar = new Mage("Veigar", 12, 2.52);
        Mage Orianna = new Mage("Orianna", 13, 3.06);
        Mage Brand = new Mage("Brand", 14, 1.38);
        Mage Sylas = new Mage("Sylas", 15, 4.40);
        Mage Lissandra = new Mage("Lissandra", 16, 2.87);
        Mage Taliyah = new Mage("Taliyah", 17, 4.35);
        Mage Karthus = new Mage("Karthus", 18, 5.00);

        mage_league.addMage(Anivia);
        mage_league.addMage(Ryze);
        mage_league.addMage(Vladimir);
        mage_league.addMage(Malzahar);
        mage_league.addMage(Swain);
        mage_league.addMage(Xerath);
        mage_league.addMage(Lux);
        mage_league.addMage(Ziggs);
        mage_league.addMage(Ahri);
        mage_league.addMage(LeBlanc);
        mage_league.addMage(Syndra);
        mage_league.addMage(Veigar);
        mage_league.addMage(Orianna);
        mage_league.addMage(Brand);
        mage_league.addMage(Sylas);
        mage_league.addMage(Lissandra);
        mage_league.addMage(Taliyah);
        mage_league.addMage(Karthus);

        Malzahar.addApprentice(Swain);
        Malzahar.addApprentice(Orianna);
        Malzahar.addApprentice(Vladimir);
        Orianna.addApprentice(Sylas);
        Karthus.addApprentice(Brand);
        Karthus.addApprentice(Anivia);
        Ryze.addApprentice(Lux);
        Xerath.addApprentice(Veigar);
    }
}
