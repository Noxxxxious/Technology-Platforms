package lab2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskListResult {
    private final List<Result> results = new ArrayList<>();

    public synchronized void addResult(Result result, BufferedWriter file, long num) throws IOException {
        results.add(result);
        file.write(String.valueOf(num) + ": ");
        for (Long divisor : result.getDivisors()){
            file.write(divisor + ", ");
        }
        file.newLine();
    }
}
