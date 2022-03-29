package lab3;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
    private int number;
    private int[] content = new int[10];

    public Message(int number, int[] content) {
        this.number = number;
        this.content = content;
    }

    public int getNumber(){
        return this.number;
    }

    public int[] getContent(){
        return this.content;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public void setContent(int[] content){
        this.content = content;
    }
}
