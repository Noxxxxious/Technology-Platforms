package lab4;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Beer {
    @Getter
    @Setter
    @Id
    private String name;

    @Getter
    @Setter
    private long price;

    @Getter
    @Setter
    @ManyToOne
    private Brewery brewery;

    public Beer(){}
    public Beer(String name, long price, Brewery brewery){
        this.name = name;
        this.price = price;
        this.brewery = brewery;
    }

    @Override
    public String toString(){
        return "Beer{ name: " + this.name + ", price: " + this.price + ", brewery: " + this.brewery + " }";
    }
}
