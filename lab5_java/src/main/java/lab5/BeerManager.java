package lab5;

import java.util.Optional;

public class BeerManager {
    private BeerDataBase dataBase;

    public BeerManager(BeerDataBase dataBase){
        this.dataBase = dataBase;
    }

    public String find(String name){
        Optional<Beer> optional = dataBase.find(name);
        if(optional.isEmpty())
            return "No matching beers found.";
        return optional.get().toString();
    }
    public String remove(String name){
        try{
            dataBase.remove(name);
        } catch (IllegalArgumentException e){
            return "Beer doesn't exist.";
        }
        return "Beer" + name + "was successfully removed.";
    }
    public String add(String name, float alcoholContent){
        try{
            dataBase.add(new Beer(name, alcoholContent));
        } catch (IllegalArgumentException e){
            return "Beer already exists.";
        }
        return "Beer" + name + "was successfully added.";
    }
}
