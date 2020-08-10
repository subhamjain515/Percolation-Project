import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.StdStats;



public class PercolationStats {

		
		
		
		private int T;
		private double p[];
		
	

		
	    // perform independent trials on an n-by-n grid
	    public PercolationStats(int n, int trials)
	    {
	    	if(n<=0 || trials<=0)
	    		throw new IllegalArgumentException("Index out of bound!!");
	    	
	    	p=new double[trials];
	    	T=trials;
	    	for(int k=0;k<trials;k++)
	    	{
	    		Percolation perc=new Percolation(n);
		    	
	    		while(!perc.percolates())
	    		{
	    			
			    	int row=StdRandom.uniform(1,n+1);
		    		int col=StdRandom.uniform(1,n+1);
		    		
	    
		    			perc.open(row,col);
		    		
	    		}
	    			
	    			double probability=perc.numberOfOpenSites()/(double)(n*n);
	    			p[k]=probability;
	    			
	    			
	    			
	    		
	    	}
	    	
	    	
	 
	    }

	    // sample mean of percolation threshold
	    public double mean()
	    {
		    	return StdStats.mean(p);
		    	
		    
	    }

	    // sample standard deviation of percolation threshold
	    public double stddev()
	    {
	    	return StdStats.stddev(p);
	    	
	    }
	    

	    // low endpoint of 95% confidence interval
	    public double confidenceLo()
	     {
	    	return mean()-((1.96*stddev())/Math.sqrt(T));
	    	
	     }

	    // high endpoint of 95% confidence interval
	    public double confidenceHi()
	    {
	    	return mean()+((1.96*stddev())/Math.sqrt(T));
	    	
	    	
	    }

	   // test client (see below)
	   public static void main(String[] args)
	   {
		   int n=Integer.parseInt(args[0]);
		   int t=Integer.parseInt(args[1]);
		   
		   PercolationStats obj=new PercolationStats(n,t);
		   System.out.println("Mean: "+obj.mean());
		   System.out.println("Standard Deviation: "+obj.stddev());
		   System.out.println("95% confidence interval = ["+obj.confidenceLo()+", "+obj.confidenceHi()+"]");
		   
		   
		   
		   
	   }

	}
