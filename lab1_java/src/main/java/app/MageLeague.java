package app;

import java.util.*;

public class MageLeague {
    private final int sorting_variation;
    private Set<Mage> mageSet;

    public MageLeague(int sort_var){
        this.sorting_variation = sort_var;
        if (this.sorting_variation == 0){
            this.mageSet = new HashSet<>();
        }
        else if (this.sorting_variation == 1){
            this.mageSet = new TreeSet<>();
        }
        else if (this.sorting_variation == 2){
            this.mageSet = new TreeSet<>(new ComparatorClass());
        }
    }

    public void addMage(Mage mage){
        mageSet.add(mage);
    }

    private void searchApprentice(Set<Mage> apprentices, Mage mage){
        for(Mage apprentice : mage.getApprentices()){
            apprentices.add(apprentice);
            searchApprentice(apprentices, apprentice);
        }
    }

    public void printMages(){
        Set<Mage> apprentices = getApprentices();
        for(Mage mage : mageSet){
            if (!apprentices.contains(mage)){
                mage.print("+");
            }
        }
        System.out.println("\n");
    }

    private Set<Mage> getApprentices(){
        Set<Mage> apprentices = new TreeSet<>();
        for(Mage mage : mageSet){
            searchApprentice(apprentices, mage);
        }
        return apprentices;
    }

    public void printMageStats(){
        Map<Mage, Integer> mageStats = getMageStats();
        for(Mage mage : mageStats.keySet()){
            System.out.println(mage + ", apprentice number: " + mageStats.get(mage));
        }
    }

    private Map<Mage, Integer> getMageStats(){
        Map<Mage, Integer> mageStats;
        if (this.sorting_variation == 0)
            mageStats = new HashMap<>();
        else if (this.sorting_variation == 1)
            mageStats = new TreeMap<>();
        else
            mageStats = new TreeMap<>(new ComparatorClass());

        for(Mage mage : mageSet){
            Set<Mage> apprentices = new HashSet<>();
            searchApprentice(apprentices, mage);
            int count = apprentices.size();
            mageStats.put(mage, count);
        }
        return mageStats;
    }
}
