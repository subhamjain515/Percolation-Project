import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class Percolation {

	private boolean open[][];
	private int openSites;
	private int size;
	private WeightedQuickUnionUF wGrid;
	private WeightedQuickUnionUF wFull;
	private int virtualtop;
	private int virtualbottom;
	
	
	
	
	
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
    	if(n<=0)
    		throw new IllegalArgumentException("Index out of bound!!");
    	size=n;
    	wGrid=new WeightedQuickUnionUF(size*size+2);
    	wFull=new WeightedQuickUnionUF(size*size+1);
    	open=new boolean[n][n];  //change
    	virtualtop=size*size;
    	virtualbottom=size*size+1;
    	openSites=0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
    	
    		validateSite(row,col);
    		int curSite=map(row,col)-1; //change
    		
    		int shiftrow=row-1;
    		int shiftcol=col-1;
    		
    		if(isOpen(row,col))
    			return;
    		
				open[shiftrow][shiftcol]=true;
		    	openSites++;
		    	
		    	
		    	
		    	if(row==1)
		    	{
		    		wGrid.union(virtualtop,curSite);
		    		wFull.union(virtualtop,curSite);
		    	}
		    	
		    	if(row==size)
		    	{
		    		wGrid.union(virtualbottom,curSite);
		    	}
		    	
		    	//Check and open Left
		    	if(isOnGrid(row,col-1) && isOpen(row,col-1))
		    	{
		    		wGrid.union(curSite,map(row,col-1)-1);
		    		wFull.union(curSite,map(row,col-1)-1);
		    	}
		    	
		    	// Check and Open Right
		        if (isOnGrid(row, col + 1) && isOpen(row, col + 1)) {
		            wGrid.union(curSite, map(row, col + 1)-1);
		            wFull.union(curSite, map(row, col + 1)-1);
		        }

		        // Check and Open Up
		        if (isOnGrid(row - 1, col) && isOpen(row - 1, col)) {
		            wGrid.union(curSite, map(row - 1, col)-1);
		            wFull.union(curSite, map(row - 1, col)-1);
		        }

		        // Check and Open Down
		        if (isOnGrid(row + 1, col) && isOpen(row + 1, col)) {
		            wGrid.union(curSite, map(row + 1, col)-1);
		            wFull.union(curSite, map(row + 1, col)-1);
		        }
    	
    	
//    	if(row!=size && row!=1 && col!=size && col!=1)
//    	{
//  
//					if(isOpen(row,col+1))
//						w.union(map(row,col),map(row,col+1));
//					if(isOpen(row,col-1))
//						w.union(map(row,col),map(row,col-1));
//					if(isOpen(row-1,col))
//						w.union(map(row,col),map(row-1,col));
//					if(isOpen(row+1,col))
//						w.union(map(row,col),map(row+1,col));
//    	}
//    
//		    	if(row==1 && col!=1 && col!=size)
//		    	{
//		    		if(isOpen(row,col+1))
//						w.union(map(row,col),map(row,col+1));
//					if(isOpen(row,col-1))
//						w.union(map(row,col),map(row,col-1));
//					if(isOpen(row+1,col))
//						w.union(map(row,col),map(row+1,col));
//		    	}
//		    	if(row==size && col!=1 && col!=size)
//		    	{
//		    		if(isOpen(row,col+1))
//						w.union(map(row,col),map(row,col+1));
//					if(isOpen(row,col-1))
//						w.union(map(row,col),map(row,col-1));
//					if(isOpen(row-1,col))
//						w.union(map(row,col),map(row-1,col));
//				
//		    	}
//		    	if(col==1 && row!=1 && row!=size)
//		    	{
//		    		if(isOpen(row,col+1))
//						w.union(map(row,col),map(row,col+1));
//					if(isOpen(row-1,col))
//						w.union(map(row,col),map(row-1,col));
//					if(isOpen(row+1,col))
//						w.union(map(row,col),map(row+1,col));
//		    	}
//		    	if(col==size && row!=1 && row!=size)
//		    	{
//					if(isOpen(row,col-1))
//						w.union(map(row,col),map(row,col-1));
//					if(isOpen(row-1,col))
//						w.union(map(row,col),map(row-1,col));
//					if(isOpen(row+1,col))
//						w.union(map(row,col),map(row+1,col));
//		    	}
//		    	if(row==1 && col==1 && size!=1)
//		    	{
//		    		if(isOpen(row,col+1))
//		    			w.union(map(row,col),map(row,col+1));
//		    		if(isOpen(row+1,col))
//		    			w.union(map(row,col),map(row+1,col));
//		    	}
//		    	if(row==1 && col==size && size!=1)
//		    	{
//		    		if(isOpen(row,col-1))
//		    			w.union(map(row,col),map(row,col-1));
//		    		if(isOpen(row+1,col))
//		    			w.union(map(row,col),map(row+1,col));
//		    	}
//		    	if(row==size && col==1 && size!=1)
//		    	{
//		    		if(isOpen(row-1,col))
//		    			w.union(map(row,col),map(row-1,col));
//		    		if(isOpen(row,col+1))
//		    			w.union(map(row,col),map(row,col+1));
//		    	}
//		    	if(row==size && col==size && size!=1)
//		    	{
//		    		if(isOpen(row-1,col))
//		    			w.union(map(row,col),map(row-1,col));
//		    		if(isOpen(row,col-1))
//		    			w.union(map(row,col),map(row,col-1));
//		    	}
//    	
				
    		
	 }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
    	validateSite(row,col);
    			
			    	return open[row-1][col-1];
    		
    }

    // is the site (row, col) full?
 
	
	public boolean isFull(int row, int col) 
    {
    	validateSite(row,col);
    		
    		
    			return (wFull.find(virtualtop)==wFull.find(map(row,col)-1));
   	
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
    	return openSites;
    }

    // does the system percolate?
  
	public boolean percolates()
    {
    	
    	return wGrid.find(virtualtop)==wGrid.find(virtualbottom);
    }
    
	private int map(int i,int j)
    {
    	return ((size*(i-1))+j);  //change
    	
    }
	
	private void validateSite(int row, int col)
	{
		if(!isOnGrid(row,col))
			throw new IllegalArgumentException("Index is out of bounds!!");

	}
	
	private boolean isOnGrid(int row, int col)
	{
		int shiftrow=row-1;
		int shiftcol=col-1;
		return (shiftrow>=0 && shiftcol>=0 && shiftrow<size && shiftcol<size );
	}

    
}