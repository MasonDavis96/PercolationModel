# PercolationModel
A program written in java to model the effects of percolation<br>


This program (Percolation.java) was written using a .jar file obtained from princeton which contains an essential algorithm, the WeightedQuickUnionFind. Along with being a foundation in learning the process of using .jar file (and specifically the unionfind algorithm) this program seeks to stand as a model for water percolation.<br>

There are 3 types of squares you will notice in running this program - black, white, and blue. black squares are closed squares, meaning water cannot pass through them. white sqaures are sqaures that are open, and can be filled with water. blue squares represent the water. The water will percolate down the screen starting from the top. The provided PercolationVisualizer.java file -- WHICH I DID NOT WRITE -- randomly creates open sites. In my program - Percolation.java - the UnionFind algorithm is used to connect any adjacent open sites. If the water connects with any of these open sites, then the water fills them, and begins percolating down. This continues until either the water percolates all the way to the bottom of the screen, or it does not - in which case a message will be displayed at the bottom notifying you.<br>

This project was given to me during my Algorithm Analysis course, specifically designed to teach me, on a more foundational level - how algorithms are used and how they can make our jobs much simpler. The emphasis on this project was to figure out  how to use the WeightedQuickUnionFind algorithm to our advantage when modeling percolation. As a side benefit, it introduced me to working with jar files, and learning how to integrate my code with other algortithms and code that was not directly written by me. This program took me a little over a week to work through. It was actually one of the more challenging programs in the class, and the feeling of figuring out the problem and successfully creating a model was worth the time put into it.<br>

to compile these files: `javac -cp algs4.jar *.java`<br>
to run the program: `java -cp .:algs4.jar PercolationVisualizer input10.txt` (The text file can be replaced with one of the other text files provided to show different models of percolation)
