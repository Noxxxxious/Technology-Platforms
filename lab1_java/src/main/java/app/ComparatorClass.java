package app;

import java.util.Comparator;

public class ComparatorClass implements Comparator<Mage> {

    @Override
    public int compare(Mage mage1, Mage mage2){
        int comparison = Integer.compare(mage1.getLevel(), mage2.getLevel());
        if (comparison != 0)
            return comparison;

        comparison = mage1.getName().compareTo(mage2.getName());
        if (comparison != 0 )
            return comparison;

        comparison = Double.compare(mage1.getPower(), mage2.getPower());
        return  comparison;
    }
}
