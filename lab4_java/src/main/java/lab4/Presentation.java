package lab4;

import javax.persistence.*;
import java.util.List;

public class Presentation {
    private final static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("lab4");
    public static void main(String[] args){
        addBrewery("Carlsberg", 111111);
        addBrewery("Okocim", 222222);
        addBrewery("Tyskie", 333333);
        addBeer("Lager", 3, "Tyskie");
        addBeer("Pszeniczne", 5, "Okocim");
        addBeer("IPA", 6, "Carlsberg");
        addBeer("Imperial Stout", 10, "Okocim");
        addBeer("APA", 9, "Carlsberg");
        printAllEntries();
        printBeersCheaperThan(8);
        printFromBreweryMoreExpensiveThan("Carlsberg", 7);
        printBreweriesWithBeersCheaperThan(6);

        delBeer("Lager");
        delBeer("IPA");
        delBrewery("Okocim");
        printAllEntries();
    }

    public static void addBeer(String name, long price, String breweryName){
        EntityManager entityManager = managerFactory.createEntityManager();
        String q = "SELECT brewery FROM Brewery brewery WHERE brewery.name LIKE :name";
        Query query = entityManager.createQuery(q, Brewery.class).setParameter("name", breweryName);
        List<Brewery> breweries = query.getResultList();
        Brewery brewery = breweries.get(0);
        entityManager.getTransaction().begin();
        entityManager.persist(new Beer(name, price, brewery));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void addBrewery(String name, long value){
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Brewery(name, value));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void delBeer(String name){
        EntityManager entityManager = managerFactory.createEntityManager();
        String q = "SELECT beer FROM Beer beer WHERE beer.name LIKE :name";
        Query query = entityManager.createQuery(q, Beer.class).setParameter("name", name);
        List<Beer> beers = query.getResultList();
        Beer beer = beers.get(0);
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(entityManager.merge(beer));
        entityTransaction.commit();
        entityManager.close();
    }

    public static void delBrewery(String name){
        EntityManager entityManager = managerFactory.createEntityManager();
        String q = "SELECT brewery FROM Brewery brewery WHERE brewery.name LIKE :name";
        Query query = entityManager.createQuery(q, Brewery.class).setParameter("name", name);
        List<Brewery> breweries = query.getResultList();
        Brewery brewery = breweries.get(0);
        List<Beer> beers = brewery.getBeers();
        for(Beer beer : beers)
            beer.setBrewery(null);
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(entityManager.merge(brewery));
        entityTransaction.commit();
        entityManager.close();
    }

    public static void printAllEntries(){
        EntityManager entityManager = managerFactory.createEntityManager();
        String q = "SELECT brewery FROM Brewery brewery";
        Query query = entityManager.createQuery(q, Brewery.class);
        List<Brewery> breweries = query.getResultList();
        for(Brewery brewery : breweries)
            System.out.println(brewery);
        q = "SELECT beer FROM Beer beer";
        query = entityManager.createQuery(q, Beer.class);
        List<Beer> beers = query.getResultList();
        for(Beer beer : beers)
            System.out.println(beer);
        entityManager.close();
        System.out.println("");
    }

    public static void printBeersCheaperThan(long price){
        EntityManager entityManager = managerFactory.createEntityManager();
        String q = "SELECT beer FROM Beer beer WHERE beer.price < :price";
        Query query = entityManager.createQuery(q, Beer.class).setParameter("price", price);
        List<Beer> beers = query.getResultList();
        for(Beer beer : beers)
            System.out.println(beer);
        entityManager.close();
        System.out.println("");
    }

    public static void printFromBreweryMoreExpensiveThan(String name, long price){
        EntityManager entityManager = managerFactory.createEntityManager();
        String q = "SELECT beer FROM Beer beer WHERE beer.brewery.name LIKE :name AND beer.price > :price";
        Query query = entityManager.createQuery(q, Beer.class).setParameter("name", name).setParameter("price", price);
        List<Beer> beers = query.getResultList();
        for(Beer beer : beers)
            System.out.println(beer);
        entityManager.close();
        System.out.println("");
    }

    public static void printBreweriesWithBeersCheaperThan(long price){
        EntityManager entityManager = managerFactory.createEntityManager();
        String q = "SELECT beer FROM Beer beer WHERE beer.price < :price";
        Query query = entityManager.createQuery(q, Beer.class).setParameter("price", price);
        List<Beer> beers = query.getResultList();
        for (Beer beer : beers){
            q = "SELECT brewery FROM Brewery brewery WHERE brewery.name LIKE :name";
            query = entityManager.createQuery(q, Brewery.class).setParameter("name", beer.getBrewery().getName());
            List<Brewery> breweries = query.getResultList();
            for(Brewery brewery : breweries)
                System.out.println(brewery);
        }
        entityManager.close();
        System.out.println("");
    }
}
