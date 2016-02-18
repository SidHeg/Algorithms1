import java.util.Random;

public class PercolationStats {
    
    private double[] count;
    private int time;
    
    public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
    {
        if (N <= 0) throw new IllegalArgumentException("N out of bound");  
        if (T <= 0) throw new IllegalArgumentException("T out of bound");  
        Percolation per;
        time = T;
        count = new double[T];
        for (int i = 0; i < T; i++)
        {
            int cnt = 0;
            per = new Percolation(N);
            Random r = new Random();
            while (!per.percolates())
            {
                int x = r.nextInt(N*N);
                int p = x/N;
                int q = x % N;
                if (!per.isOpen(p+1, q+1))
                    cnt = cnt + 1;
                per.open(p+1, q+1);
            }
            count[i] = cnt*1.0/(N*N);
        }
    }
    public double mean()                      // sample mean of percolation threshold
    {
        double sum = 0;
        double mean;
        
        for (int i = 0; i < time; i++)
        {
            sum = sum + count[i];
        }
        mean = sum/time;
        return (mean);
    }
    
    public double stddev()                    // sample standard deviation of percolation threshold
    {
        double m = mean();
        double sumsq = 0;
        double var;
        double sd;
        
        for (int i = 0; i < time; i++)
        {
            sumsq = sumsq + (count[i] - m)*(count[i] - m);
        }
        var = sumsq/(time-1);
        sd = Math.sqrt(var);
        return (sd);
    }
    
    public double confidenceLo()              // low  endpoint of 95% confidence interval
    {
        double m = mean();
        double sd = stddev();
        double lo;
        double w;
        
        w = (1.96*sd/Math.sqrt(time));
        lo = m - w;
        return (lo);
    }
    
    public double confidenceHi()              // high endpoint of 95% confidence interval
    {
        double m = mean();
        double sd = stddev();
        double hi;
        double w;
        
        w = (1.96*sd/Math.sqrt(time));
        hi = m + w;
        return (hi);
    }
    
    public static void main(String[] args)    // test client (described below)
    {
//        Stopwatch sw = new Stopwatch();
        PercolationStats obj;
        obj = new PercolationStats(200, 100);
        System.out.println(obj.mean());
        System.out.println(obj.stddev());
        System.out.println(obj.confidenceHi());
//        StdOut.println("Time :" + sw.elapsedTime());
    }
    
}