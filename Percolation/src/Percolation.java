import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
	int n;
	int grid[][];
	int top = 0;
	int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    
    {
    	if(n<=0) throw new IllegalArgumentException("Invalid number");
    	this.n=n;
    	grid = new int[n+1][n+1]; //this is our grid 
    	
    	//blocking all site
    	//-1 is blocked
    	//0 is opened
    	//1 is full
    	for(int i=1; i<=n; i++)
    	{
    		for(int j=1; j<=n; j++)
    		{
    			grid[i][j] = -1;
    		}
    	}
    	
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
    	if(row<1 || col<1 || row>n || col>n) throw new IllegalArgumentException("Invalid range");
    	if(isOpen(row, col)==false)grid[row][col] = 0;

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
    	if(row<1 || col<1 || row>n || col>n) throw new IllegalArgumentException("Invalid range");
    	if(grid[row][col] == 0 || grid[row][col] == 1) return true;
    	else return false; 
    }
    

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
    	if(row<1 || col<1 || row>n || col>n) throw new IllegalArgumentException("Invalid range");
    	if(grid[row][col] == 1) return true;
    	else return false; 

    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
    	int count=0;
    	for(int i=1; i<=n; i++)
    	{
    		for(int j=1; j<=n; j++)
    		{
    			if(grid[i][j] == 0 || grid[i][j] == 1) count++;
    		}
    	}
    	return count;
    }

    // does the system percolate?
    public boolean percolates()
    {
    	WeightedQuickUnionUF obj = new WeightedQuickUnionUF(n); 
    	return obj.find(top)==obj.find(bottom);
    	if //the system percolates
    	return true;
    	else return false;
    }
    
    // test client (optional)
    public static void main(String[] args)
    {
    	try {
	    	Percolation obj = new Percolation(20);
	    	int rand1, rand2;
	    	//try catch block when creating instance of Percolation
	    	while(obj.percolates()!=true)
	    	{
	    		//if random site is open, continue, else open the random site 
	    		rand1 = StdRandom.uniformInt(1, obj.n+1);
	    		rand2 = StdRandom.uniformInt(1, obj.n+1);
	    		if(obj.isOpen(rand1,rand2)==true ) continue;
	    		else
	    		{
	    			obj.open(rand1, rand2);
	    		}
	    	}
	    	int percolationThreshold = obj.numberOfOpenSites()/(obj.n*obj.n);
	    	StdOut.print(percolationThreshold);
    	}
    	catch(IllegalArgumentException e)
    	{
    		StdOut.print(e.getMessage());
    	}
    }
}