package app;

import java.util.*;

public class MageLeague {
    private int sorting_variation;
    private Set<Mage> mageSet;
    private Map<Mage, Integer> mageStats;

    public MageLeague(int sort_var){
        this.sorting_variation = sort_var;
        if (this.sorting_variation == 0){
            this.mageSet = new HashSet<>();
        }
        else if (this.sorting_variation == 1){
            this.mageSet = new TreeSet<>();
        }
        else {
            this.mageSet = new TreeSet<>(new ComparatorClass());
        }
    }

    public void addMage(Mage mage){
        mageSet.add(mage);
    }

    private void DFS(Set<Mage> apprentices, Mage mage){
        for(Mage apprentice : mage.getApprentices()){
            apprentices.add(apprentice);
            DFS(apprentices, apprentice);
        }
    }

    public void printMages(){
        Set<Mage> apprentices = new TreeSet<>();
        for(Mage mage : mageSet){
            DFS(apprentices, mage);
        }
        for(Mage mage : mageSet){
            if (!apprentices.contains(mage)){
                mage.print("-");
            }
        }
    }

    public void printMageStats(){
        if (this.sorting_variation == 0)
            mageStats = new HashMap<>();
        else
            mageStats = new TreeMap<>();
        for(Mage mage : mageSet){
            Set<Mage> apprentices = new HashSet<>();
            DFS(apprentices, mage);
            int size = apprentices.size();
            mageStats.put(mage, size);
        }
        for(Mage mage : mageStats.keySet()){
            System.out.println(mage + ": " + mageStats.get(mage));
        }
    }
}
