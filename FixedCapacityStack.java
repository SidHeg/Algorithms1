public class FixedCapacityStack<Item>
{
    private Item[] stack;
    private int N;
    
    public FixedCapacityStack(int capacity)
    { stack = (Item[]) new Object[capacity]; }
    
    public boolean isEmpty()
    { return N == 0; }
    
    public void push(Item item)
    { stack[N++] = item; }
    
    public Item pop()
    { return stack[--N]; }
}
        