package lab4;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Brewery {
    @Getter
    @Setter
    @Id
    private String name;

    @Getter
    @Setter
    private long value;

    @Getter
    @Setter
    @OneToMany(mappedBy = "brewery")
    private List<Beer> beers;

    public Brewery(){}
    public Brewery(String name, long value){
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString(){
        return "Brewery{ name: " + this.name + ", value: " + this.value + " }";
    }
}
