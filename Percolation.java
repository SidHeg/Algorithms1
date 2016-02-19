public class Percolation {
    
    private int[][] id;
    private int[][] sz;
    private int len;asfagddfgdf
    private int fir = 0;
    private int las = 0;
    private int ch = 0;
    
    
    public Percolation(int N)               // create N-by-N grid, with all sites blocked
    {
        if (N <= 0) throw new IllegalArgumentException("N out of bound");  
        len = N;
        sz = new int[N][N];
        id = new int[N][N];
        
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                id[i][j] = 0;
                sz[i][j] = 1;
            }
        }
    }
        
    
    private int root(int i, int j)
    {
        int ii = i;
        int jj = j;
        int val = (i*len)+(j+1);
        while (id[ii][jj] != val)
        {
            val = id[ii][jj];
            jj = (val-1) % len;
            ii = (val-1)/len;
        }
            return (val);
    }       
    
    
    private void union(int val1, int val2)
    {
        if (val1 == val2) return;
        
        int j = (val1-1) % len;
        int i = (val1-1)/len;
        int l = (val2-1) % len;
        int k = (val2-1)/len;
        if (ch == 0)
        {
            if (i == 0)
            {
                if (fir == 0)
                {
                    fir = val1;
                    id[k][l] = fir;
                    sz[i][j] = sz[i][j] + sz[k][l];
                }
                else
                {
                    id[i][j] = fir;
                    id[k][l] = fir;
                    sz[i][j] = sz[i][j] + sz[k][l]; 
                }
                if (val2 == las || k == len-1)
                {
                    ch = 1;
//                System.out.println("1: "+id[i][j]+" "+id[k][l]+" "+val1+" "+val2+" "+ch);
                    return;
                }
                return;
            }
            
            if (k == 0)
            {
                if (fir == 0)
                {
                    fir = val2;
                    id[i][j] = fir;
                    sz[k][l] = sz[k][l] + sz[i][j];
                }
                else
                {
                    id[i][j] = fir;
                    id[k][l] = fir;
                    sz[k][l] = sz[i][j] + sz[k][l]; 
                }
                if (val1 == las || i == len-1)
                {
                    ch = 1;
//                System.out.println("2: "+id[i][j]+" "+id[k][l]+" "+val1+" "+val2+" "+ch);
                    return;
                }
                return;
            }
            
            if (i == len-1)
            {
                if (las == 0)
                {
                    las = val1;
                    id[k][l] = val1;
                    sz[i][j] = sz[i][j] + sz[k][l];
                }
                else
                {
                    id[i][j] = las;
                    id[k][l] = las;
                    sz[i][j] = sz[i][j] + sz[k][l]; 
                }
                if (val2 == fir)
                {
                    ch = 1;
//                System.out.println("3: "+id[i][j]+" "+id[k][l]+" "+val1+" "+val2+" "+ch);
                    return;
                }
                return;
            }
            
            if (k == len-1)
            {
                if (las == 0)
                {
                    las = val2;
                    id[i][j] = val2;
                    sz[k][l] = sz[k][l] + sz[i][j];
                }
                else
                {
                    id[i][j] = las;
                    id[k][l] = las;
                    sz[k][l] = sz[i][j] + sz[k][l]; 
                }
                if (val1 == fir)
                {
                    ch = 1;
//                System.out.println("4: "+id[i][j]+" "+id[k][l]+" "+val1+" "+val2+" "+ch);
                    return;
                }
                return;
            }
            
            if (sz[i][j] < sz[k][l])
            {
                id[i][j] = val2;
                sz[k][l] = sz[k][l] + sz[i][j];
            }
            else
            {
                id[k][l] = val1;
                sz[i][j] = sz[i][j] + sz[k][l];
            }
            if (val1 == fir && val2 == las)
            {
                ch = 1;
//            System.out.println("5: "+id[i][j]+" "+id[k][l]+" "+val1+" "+val2+" "+ch);
                return;
            }
            if (val1 == las && val2 == fir)
            {
                ch = 1;
//            System.out.println("6: "+id[i][j]+" "+id[k][l]+" "+val1+" "+val2+" "+ch);
                return;
            }
        }
        else
        {
            if (sz[i][j] < sz[k][l])
            {
                id[i][j] = val2;
                sz[k][l] = sz[k][l] + sz[i][j];
            }
            else
            {
                id[k][l] = val1;
                sz[i][j] = sz[i][j] + sz[k][l];
            }
        }
    }
    
    
    public void open(int x, int y)          // open site (row i, column j) if it is not open already
    {
        if (x <= 0 || x > len) throw new IndexOutOfBoundsException("x out of bound");  
        if (y <= 0 || y > len) throw new IndexOutOfBoundsException("y out of bound");  
        int i = x-1;
        int j = y-1;
        
        if (id[i][j] == 0)
        {
            id[i][j] = (i*len)+y;
            
            if (y < len && isOpen(x, y+1))
            {
                union(root(i, j), root(i, j+1));
            }
            
            if (x < len && isOpen(x+1, y))
            {
                union(root(i, j), root(i+1, j));
            }       
            
            if (y > 1 && isOpen(x, y-1))
            {
                union(root(i, j), root(i, j-1));
            } 
            
            if (x > 1 && isOpen(x-1, y))
            {
                union(root(i, j), root(i-1, j));
            }   
        }    
    }
    
    
    public boolean isOpen(int x, int y)   // is site (row i, column j) open?
    {  
        if (x <= 0 || x > len) throw new IndexOutOfBoundsException("x out of bound");  
        if (y <= 0 || y > len) throw new IndexOutOfBoundsException("y out of bound");         
        
        int i = x-1;
        int j = y-1;
        return (id[i][j] != 0);
    }
    
    
    public boolean isFull(int x, int y)     // is site (row i, column j) full?
    {
        if (x <= 0 || x > len) throw new IndexOutOfBoundsException("x out of bound");  
        if (y <= 0 || y > len) throw new IndexOutOfBoundsException("y out of bound"); 
        
        int i = x-1;
        int j = y-1;
        
        if (isOpen(x,y))
        {
            if (i == 0)
            {
                return true;
            }
            if (root(i, j) == fir)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean percolates()             // does the system percolate?
    {
        if (len == 1)
        {
            return (isOpen(1, 1));
        }
//        System.out.println((ch == 1)+" "+fir+" "+las);
        return ( ch == 1);
    }
            
    
//    public boolean percolates()             // does the system percolate?
//    {
//        if (len == 1)
//        {
//            return (isOpen(1, 1));
//        }
//        if (fir != 0)
//        {
//            for (int t = 0; t < len; t++)
//            {
//                if (isOpen(len, t+1))
//                {
//                    if (fir == root(len-1, t))
//                        return (true);
//                }
//            }
//        }
//        return false;
//    }
    
    
//    public void print()
//    {
//        for (int i = 0; i < len; i++)
//        {
//            for ( int j = 0; j < len; j++)
//            {
//                System.out.print(id[i][j]+" ");
//            }
//            System.out.println("");
//        }
//    }
//    
//   public static void main(String[] args)   // test client (optional)
//   {
//       Percolation a;
//       
//       a = new Percolation(4);
////       a.open(1,1);
//       a.open(1,2);
//       a.open(2,2);
//       a.open(2,3);
////       a.open(3,4);
//       a.open(3,3);
////       a.open(4,1);
//       a.open(4,4);
//       a.open(4,3);
////       System.out.println(a.isFull(2,3));
//       System.out.println(a.isFull(3,4));
//       System.out.println(a.percolates());
//       a.print();
//       
//   }
}
