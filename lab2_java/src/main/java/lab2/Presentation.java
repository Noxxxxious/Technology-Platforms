package lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class Presentation {
    public static void main(String[] args) throws InterruptedException, IOException {
        int threadCount = Integer.parseInt(args[0]);
        String file_path = args[1];

        TaskList taskList = new TaskList();
        TaskListResult taskListResult = new TaskListResult();
        List<Thread> threads = new ArrayList<>();

        FileOutputStream fileStream = new FileOutputStream(new File("divisors.txt"));
        BufferedWriter fileToWrite = new BufferedWriter(new OutputStreamWriter(fileStream));

        for(int i = 0; i < threadCount; i++){
            Thread thread = new Thread(new Manager(taskList, taskListResult, fileToWrite));
            threads.add(thread);
            thread.start();
        }

        loadTasks(taskList, file_path);

        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;

        while(!exitProgram){
            switch(scanner.next()){
                case "1":
                    long number = scanner.nextInt();
                    taskList.put(new Task(number));
                    System.out.println("Task added");
                    break;
                case "exit":
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        scanner.close();
        for (Thread thread : threads){
            thread.interrupt();
            thread.join();
        }
        fileToWrite.close();
    }

    public static void loadTasks(TaskList taskList, String file_path){
        File file = new File(file_path);
        Scanner file_scanner;
        try{
            file_scanner = new Scanner(file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return;
        }
        while (file_scanner.hasNextLine()){
            taskList.put(new Task(Long.parseLong(file_scanner.nextLine())));
        }
    }
}
