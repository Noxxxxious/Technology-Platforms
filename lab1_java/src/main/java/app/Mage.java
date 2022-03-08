package app;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Mage implements Comparable<Mage> {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    public Mage(String name, int level, double power){
        this.name = name;
        this.level = level;
        this.power = power;
        apprentices = new TreeSet<>();
    }

    @Override
    public boolean equals(Object object){
        if (this == object)
            return true;
        if (!(object instanceof Mage)) {
            return false;
        }

        Mage mage_object = (Mage) object;

        if (level != mage_object.level || Double.compare(mage_object.power, power) != 0 || !name.equals(mage_object.name))
            return false;

        return Objects.equals(apprentices, mage_object.apprentices);
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + level;
        result = 31 * result + (int) power;
        result = 31 * result + (apprentices != null ? apprentices.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "Mage{name='" + name + "', level=" + level + ", power=" + power + "}";
    }

    @Override
    public int compareTo(Mage mage){
        int comparison = this.name.compareTo(mage.name);
        if (comparison != 0)
            return comparison;

        comparison = level - mage.level;
        if (comparison != 0 )
            return comparison;

        comparison = Double.compare(this.power, mage.power);
        return  comparison;
    }

    public void print(String indent){
        System.out.print(indent);
        System.out.println(this);
        for(Mage mage : apprentices){
            mage.print(indent + "-");
        }
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }

    public double getPower(){
        return power;
    }

    public Set<Mage> getApprentices(){
        return apprentices;
    }

    public void addApprentice(Mage apprentice){
        this.apprentices.add(apprentice);
    }
}
