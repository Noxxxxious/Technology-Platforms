package lab2;

public class Task {
    private long number;

    public Task(long number) {
        this.number = number;
    }

    public long getNumber(){
        return this.number;
    }
    public Result divisors(){
        Result result = new Result();
        for(long i = 1; i <= number/2; i++){
            if(number % i == 0)
                result.addDivisor(i);
        }
        result.addDivisor(number);
        return result;
    }
}
