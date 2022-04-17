package lab5;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class BeerDataBase {
    private final Collection<Beer> beers = new HashSet<>();

    public Optional<Beer> find(String name){
        return beers.stream().filter(beer -> beer.getName().equals(name)).findFirst();
    }
    public void remove(String name){
        Beer beer = beers.stream().filter(removed_beer -> removed_beer.getName().equals(name)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Beer doesn't exist."));
        beers.remove(beer);
    }
    public void add(Beer new_beer){
        for (Beer beer : beers) {
            if(beer.getName().equals(new_beer.getName()))
                throw new IllegalArgumentException("Beer " + new_beer.getName() + " already exists.");
        }
        beers.add(new_beer);
    }
}
