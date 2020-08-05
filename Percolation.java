import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class Percolation {

	int openSites=0;
	int r,c;
	boolean open[][]=new boolean[1000][1000];
	int size;
	WeightedQuickUnionUF w=new WeightedQuickUnionUF(size*size+2);
	
	
	
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
    			this.size=n;
    	
    		
		    	for(int i=1;i<=n;i++)
		    	{
		    		for(int j=1;j<=n;j++)
		    		{
		    			open[i][j]=false;
		    			w.union(0,map(1,j));
		    			w.union(map(n,j),((n*n)+1));
		    		}
		    	}
    		
    	
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
    	if(row<=0||row>size)
    		throw new IllegalArgumentException("Index out of bound!!");
    	else if(col<=0||col>size)
    		throw new IllegalArgumentException("Index out of bound!!");
    	else {
    		
    		
		open[row][col]=true;
    	openSites++;
    	
    	if(row!=size && row!=1 && col!=size && col!=1)
    	{
  
					if(isOpen(row,col+1))
						w.union(map(row,col),map(row,col+1));
					if(isOpen(row,col-1))
						w.union(map(row,col),map(row,col-1));
					if(isOpen(row-1,col))
						w.union(map(row,col),map(row-1,col));
					if(isOpen(row+1,col))
						w.union(map(row,col),map(row+1,col));
    	}
    	else if(row==1)
    	{
    		if(isOpen(row,col+1))
				w.union(map(row,col),map(row,col+1));
			if(isOpen(row,col-1))
				w.union(map(row,col),map(row,col-1));
			if(isOpen(row+1,col))
				w.union(map(row,col),map(row+1,col));
    	}
    	else if(row==size)
    	{
    		if(isOpen(row,col+1))
				w.union(map(row,col),map(row,col+1));
			if(isOpen(row,col-1))
				w.union(map(row,col),map(row,col-1));
			if(isOpen(row-1,col))
				w.union(map(row,col),map(row-1,col));
		
    	}
    	else if(col==1)
    	{
    		if(isOpen(row,col+1))
				w.union(map(row,col),map(row,col+1));
			if(isOpen(row-1,col))
				w.union(map(row,col),map(row-1,col));
			if(isOpen(row+1,col))
				w.union(map(row,col),map(row+1,col));
    	}
    	else if(col==size)
    	{
			if(isOpen(row,col-1))
				w.union(map(row,col),map(row,col-1));
			if(isOpen(row-1,col))
				w.union(map(row,col),map(row-1,col));
			if(isOpen(row+1,col))
				w.union(map(row,col),map(row+1,col));
    	}
    	
				
    		}
	 }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
    	if(row<=0||row>size)
    		throw new IllegalArgumentException("Index out of bound!!");
    	else if(col<=0||col>size)
    		throw new IllegalArgumentException("Index out of bound!!");
    	else {
			    	if(open[row][col]==true)
			    		return true;
			    	else
			    		return false;
    		}
    }

    // is the site (row, col) full?
    @SuppressWarnings("deprecation")
	public boolean isFull(int row, int col) 
    {
    	if(row<=0||row>size)
    		throw new IllegalArgumentException("Index out of bound!!");
    	else if(col<=0||col>size)
    		throw new IllegalArgumentException("Index out of bound!!");
    	else {
    		
    		if(isOpen(row,col)==true)
    		{
    			if(w.connected(0,map(row,col))==true)
    			{
    				return true;
    			}
    			else
    				return false;
    		}
    		else return false;
    		
    	}
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
    	return openSites;
    }

    // does the system percolate?
    @SuppressWarnings("deprecation")
	public boolean percolates()
    {
    	
    	if(w.connected(0,(size*size+1)))
    		return true;
    	else return false;
    }
    
	public int map(int i,int j)
    {
    	int ufval=(size*(i-1))+j;
    	return ufval;
    }

    
}