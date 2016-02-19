public class ArrayStackOfStrings
{
    private String[] stack;
    private int N = 0;
    
    public ArrayStackOfStrings(int capacity)
    { stack = new String[capacity]; }
    
    public boolean IsEmpty()
    { return N == 0; }
    
    public void push(String item)
    {
        stack[N++] = item;
    }
    
    public String pop()
    {
        return stack[--N];
    }
        
    
    public static void main(String[] args)
    {
        StdOut.print("Please difine the array size");
        int size = StdIn.readInt();
        ArrayStackOfStrings stack = new ArrayStackOfStrings(size);
        
        while (!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop());
            else stack.push(s);
        }
    }
}