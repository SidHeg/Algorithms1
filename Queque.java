import java.util.*;

public class Queque{
    
    private Node first = null;
    private Node last = null;
    
    private class Node{
        
        String item;
        Node next;
    }
    
    public boolean isEmpty(){
        
        return first == null;
    }
    
    public void enqueue(String item){
        
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }
    
    public String dequeue(){
        
        String item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public static void main(String[] args){
        
        Scanner kb = new Scanner(System.in);
        Queque test = new Queque();
        String item = "java";
        while (!item.equals("esc")){    
            System.out.println("Enter the item value: ");
            item = kb.next();
            if (item.equals("-")) System.out.println(test.dequeue());
            else test.enqueue(item);
        }
        kb.close();
    }
}