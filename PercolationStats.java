import edu.princeton.cs.algs4.StdRandom;
import java.util.Scanner;
import edu.princeton.cs.algs4.StdStats;
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationStats {

		double p[]=new double[1000];
		double probability;
		double m;
		double s;
		int t;
		
	

		
	    // perform independent trials on an n-by-n grid
	    public PercolationStats(int n, int trials)
	    {
	    	if(n<=0 || trials<=0)
	    		throw new IllegalArgumentException("Index out of bound!!");
	    	this.t=trials;
	    	Percolation perc=new Percolation(n);
	    	for(int k=0;k<trials;k++)
	    	{
	    		
		    	int i=StdRandom.uniform(n);
	    		int j=StdRandom.uniform(n);
	    	
	    		if(perc.isOpen(i,j)==false)
	    			perc.open(i,j);
	    		if(perc.percolates()==true)
	    		{
	    			probability=perc.numberOfOpenSites()/(double)(n*n);
	    			p[k]=probability;
	    		}
	
	    	}
	    	
	    }

	    // sample mean of percolation threshold
	    public double mean()
	    {
		    	m=StdStats.mean(p);
		    	return m;
		    
	    }

	    // sample standard deviation of percolation threshold
	    public double stddev()
	    {
	    	s=StdStats.stddev(p);
	    	return s;
	    }
	    

	    // low endpoint of 95% confidence interval
	    public double confidenceLo()
	     {
	    	double lowEndPoint=m-(1.96*s)/Math.sqrt(t);
	    	return lowEndPoint;
	     }

	    // high endpoint of 95% confidence interval
	    public double confidenceHi()
	    {
	    	double highEndPoint=m+(1.96*s)/Math.sqrt(t);
	    	return highEndPoint;
	    	
	    }

	   // test client (see below)
	   public static void main(String[] args)
	   {
		   int n,t;
		   Scanner sc=new Scanner(System.in);
		   System.out.println("Enter n: ");
		   n=sc.nextInt();
		   System.out.println("Enter number of trials: ");
		   t=sc.nextInt();
		   sc.close();
		   PercolationStats obj=new PercolationStats(n,t);
		   System.out.println("Mean: "+obj.mean());
		   System.out.println("Standard Deviation: "+obj.stddev());
		   System.out.println("Low End Point: "+obj.confidenceLo());
		   System.out.println("High End Point: "+obj.confidenceHi());
		   
		   
		   
		   
	   }

	}
