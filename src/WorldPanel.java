import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private int cellsPerRow;
	private int cellSize;
	private Cell[][] cells;
	private Timer timer;
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
	
		//calculate the cellSize
		cellSize = ConwaysGameOfLife.HEIGHT/ConwaysGameOfLife.CELLS_PER_ROW;
		
		//initialize the cells array
		cells = new Cell[ConwaysGameOfLife.CELLS_PER_ROW][ConwaysGameOfLife.CELLS_PER_ROW];
		//initialize each cell in the array
		for(int i = 0; i < ConwaysGameOfLife.CELLS_PER_ROW; i++) {
			for(int j = 0; j < ConwaysGameOfLife.CELLS_PER_ROW; j++) {
				cells[i][j] = new Cell(i*cellSize,j*cellSize,cellSize);
			}
		}
	}
	
	public void randomizeCells() {
		// make each cell alive or dead randomly
		repaint();
		for(int i = 0; i < ConwaysGameOfLife.CELLS_PER_ROW; i++) {
			for(int j = 0; j < ConwaysGameOfLife.CELLS_PER_ROW; j++) {
				if((Math.random()*100)%2 == 0) {
					cells[i][j].isAlive = true;
				}
			}
		}
	}
	
	public void clearCells() {
		// set isAlive to false for all cells
		repaint();
		for(int i = 0; i < ConwaysGameOfLife.CELLS_PER_ROW; i++) {
			for(int j = 0; j < ConwaysGameOfLife.CELLS_PER_ROW; j++) {
				
					cells[i][j].isAlive = true;
				
			}
		}
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//iterate through the cells and draw them
		for(int i = 0; i < ConwaysGameOfLife.CELLS_PER_ROW; i++) {
			for(int j = 0; j < ConwaysGameOfLife.CELLS_PER_ROW; j++) {
				cells[i][j].draw(g);
			}
		}
	}
	
	//advances world one step
	public void step() {
		//initialize the numLivingNbors variable to be the same size as the cells
		int[][] numLivingNbors = new int[ConwaysGameOfLife.CELLS_PER_ROW][ConwaysGameOfLife.CELLS_PER_ROW];
		
		//iterate through the cells and populate the numLivingNbors array with their neighbors
		for(int i = 0; i < ConwaysGameOfLife.CELLS_PER_ROW; i++) {
			for(int j = 0; j < ConwaysGameOfLife.CELLS_PER_ROW; j++) {
				numLivingNbors[i][j] = getLivingNeighbors(i,j);
			}
		}
		
		repaint();
	}
	
	//returns an array list of the  8 or less neighbors of the 
	//cell identified by x and y
	public int getLivingNeighbors(int x, int y){
		int livingNeighbors = 0;
		if(cells[x+1][y+1].isAlive) {
			livingNeighbors++;
		}
		if(cells[x][y+1].isAlive) {
			livingNeighbors++;
		}
		if(cells[x+1][y].isAlive) {
			livingNeighbors++;
		}
		if(cells[x-1][y-1].isAlive) {
			livingNeighbors++;
		}
		if(cells[x-1][y].isAlive) {
			livingNeighbors++;
		}
		if(cells[x][y-1].isAlive) {
			livingNeighbors++;
		}
		if(cells[x+1][y-1].isAlive) {
			livingNeighbors++;
		}
		if(cells[x-1][y+1].isAlive) {
			livingNeighbors++;
		}
		//add 1 to livingNeighbors for each
		//neighboring cell that is alive
		
		return livingNeighbors;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// IGNORE
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// IGNORE
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// IGNORE
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//get the location of the mouse
		
		//toggle the cell at that location to either alive or dead
		//based on its current state
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// IGNORE
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}
