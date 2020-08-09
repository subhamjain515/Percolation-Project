import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.StdStats;
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationStats {

		
		private double probability;
		private double m;
		private double s;
		private int T;
		private double p[]=null;
		
	

		
	    // perform independent trials on an n-by-n grid
	    public PercolationStats(int n, int trials)
	    {
	    	if(n<=0 || trials<=0)
	    		throw new IllegalArgumentException("Index out of bound!!");
	    	Percolation perc=new Percolation(n);
	    	p=new double[trials];
	    	for(int k=0;k<trials;k++)
	    	{
	    		while(perc.percolates()!=true)
	    		{
	    			
			    	int i=StdRandom.uniform(n)+1;
		    		int j=StdRandom.uniform(n)+1;
		    		
		    		if(perc.isOpen(i,j)==false)
		    			perc.open(i,j);
		    
		    		
	    		}
	    		
	    			
	    			probability=perc.numberOfOpenSites()/(double)(n*n);
	    			p[k]=probability;
	    		
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
	    	double lowEndPoint=m-(1.96*s)/Math.sqrt(T);
	    	return lowEndPoint;
	     }

	    // high endpoint of 95% confidence interval
	    public double confidenceHi()
	    {
	    	double highEndPoint=m+(1.96*s)/Math.sqrt(T);
	    	return highEndPoint;
	    	
	    }

	   // test client (see below)
	   public static void main(String[] args)
	   {
		   int n=Integer.parseInt(args[0]);
		   int t=Integer.parseInt(args[1]);
		   
		   PercolationStats obj=new PercolationStats(n,t);
		   obj.T=t;
		   System.out.println("Mean: "+obj.mean());
		   System.out.println("Standard Deviation: "+obj.stddev());
		   System.out.println("95% confidence interval = ["+obj.confidenceLo()+", "+obj.confidenceHi()+"]");
		   
		   
		   
		   
	   }

	}
