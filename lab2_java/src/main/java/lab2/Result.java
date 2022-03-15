package lab2;

import java.util.ArrayList;

public class Result {
    private ArrayList<Long> divisors = new ArrayList<>();

    public ArrayList<Long> getDivisors(){
        return this.divisors;
    }

    public void addDivisor(Long divisor){
        this.divisors.add(divisor);
    }
}
