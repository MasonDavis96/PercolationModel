
/*
  Mason Davis
  CS 318 Winter 2019
  Program #1

  This program seeks to utilize user-built libraries in order to create a
  grid that simulates the effects of percolation.
*/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{

    int numOpenSites = 0;
    int size;
    boolean grid[][];
    WeightedQuickUnionUF ufArray;

    /*
        The Percolation constructor takes a single integer argument and
        creates a boolean 2D grid of that size (n * n), initalized, by defualt,
        to false. It inititalizes the instance
        variables, and throws an IllegalArgumentException if the argument is
        less than 0.
    */
    public Percolation(int n)
    {
        if(n <= 0)
            throw new IllegalArgumentException("must be > 0");

        grid = new boolean[n+1][n+1];
        size = n;
        ufArray = new WeightedQuickUnionUF((n*n)+1);
    }

    /*
        The open() method takes two integer arguments, a 2d index for row and
        column.It has the job of "opening" one of those indices (intitializing
        that specific index to the "true" value) and connecting any other open
        indices that are immediately around it with the
        WeightedQuickUnionFind API. It returns nothing, but throws an
        IndexOutOfBoundsException if the operations eceed the grid size.
    */
    public void open(int row, int col)
    {
        // if indices are in range
        if (validate(row, col))
        {
            grid[row][col] = true; // open site
            numOpenSites++;

            if (row > 1) // if not top row
            {
                // if index directly below the arugment index is open,
                // connect the two
                if (grid[row - 1][col])
                    ufArray.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
            if (row < size) // if not bottom row
            {
                // if index directly above is open, connect
                if (grid[row + 1][col])
                    ufArray.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
            if (col > 1) // if not leftmost column
            {
                // if index directly to the left is open, connect
                if (grid[row][col - 1])
                    ufArray.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
            if (col < size) // if not rightmost column
            {
                // if index directly to the right is open, connect
                if (grid[row][col + 1])
                    ufArray.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
        }
        else
            throw new IndexOutOfBoundsException("Out of Bounds");
    }

    /*
        the method isOpen() takes in two integer arguments, a 2D index, row and
        column. It returns a boolean, whether or not a specific index is
        "open," (true/false) and throws an IndexOutOfBoundsException if the
        index is out of range.
    */
    public boolean isOpen(int row, int col)
    {
        if(validate(row, col)) // is index valid?
        {
            return (grid[row][col]); // true or false if index is open or closed
        }
        else
            throw new IndexOutOfBoundsException("Out of Bounds");
    }

    /*
        the method isFull() takes in the same two arguments as previous methods.
        It checks to see if any index on the bottom row is open, and if it
        is, it checks to see if that index is connected in any way to an
        index at the top row, indicating that there is a clear path from top
        to bottom, thus completing the percolation. It returns a boolean on
        whether or not the sites are full, and throws an
        IndexOutOfBoundsException if the indices are out of range
    */
    public boolean isFull(int row, int col)
    {
        boolean full = false;
        if (validate(row, col)) // is index valid?
        {
            if(isOpen(row, col)) //  is index open?
            {
                for (int i = 1; i <= size; i++) // check through top row indices
                {
                    if (ufArray.connected(xyTo1D(row, col), i))
                        full = true;
                    // if bottom row is connected to top row == full
                }
            }
            return full;
        }
        else
            throw new IndexOutOfBoundsException("Out of Bounds");
    }

    /*
        the numberOfOpenSites() method takes in no arguments and returns the
        amount of open sites in the grid.
    */
    public int numberOfOpenSites()
    {
        return numOpenSites;
    }

    /*
        the method percolates() takes no arguments, and determines if any
        index in the bottom row of the grid is full, indicating complete
        percolation from top to bottom. Returns a boolean.
    */
    public boolean percolates()
    {
        boolean check = false;

        for(int i = 1; i <= size; i++) // check through bottom row of grid
        {
            if(isFull(size, i)) // if an index is full
                check = true;
        }
        return check;
    }

    /*
        the xyTo1D() method takes in the two integer index arguments like the
        previous methods, and calculates/converts those two indices into a
        1d index suitable for the UnionFind algorithm in use. Returns an int.
    */
    private int xyTo1D(int row, int col)
    {
        return size * (row - 1) + col;
    }

    /*
        the method validate() takes in the same two arguments, and checks to
        see if the two integers are within range of the grid. It returns a
        boolean.
    */
    private boolean validate(int row, int col)
    {
        return (row > 0 && row <= size && col > 0 && col <= size);
    }
}