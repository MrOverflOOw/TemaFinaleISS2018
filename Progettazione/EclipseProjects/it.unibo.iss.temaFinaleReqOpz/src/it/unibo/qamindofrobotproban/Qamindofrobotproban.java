/* Generated by AN DISI Unibo */ 
/*
This code is generated only ONCE
*/
package it.unibo.qamindofrobotproban;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.ppcr.ai.ppcr;
import it.unibo.ppcr.gui.Grid;
import it.unibo.qactors.QActorContext;



public class Qamindofrobotproban extends AbstractQamindofrobotproban { 
	public Qamindofrobotproban(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
		super(actorId, myCtx, outEnvView);
		ppcr.setRows(10);
		ppcr.setCols(ppcr.getRows() + 1);
		Grid g = new Grid(ppcr.getRows(), ppcr.getCols());
		ppcr.setGrid(g);
	}
/*
 * ADDED BY THE APPLICATION DESIGNER	
 */
	
	public String getTestString() {
		return "data(w, 300)";
	}
	
	public int getTestNumber() {
		return 120;
	}
	
	public int setup() {
		ppcr.setOptimal(null);
		return 0;
	}
	
	public int deleteMap() {
		ppcr.deleteMapFile("map.dat");
		return 0;
	}
	
	public String getNextMove(String arg) {
		String nextMove = ppcr.getNextMove(arg);
		if (!(nextMove.equalsIgnoreCase("impassableObstacle") || nextMove.equalsIgnoreCase("obstructedFinalPosition")
				|| nextMove.equalsIgnoreCase("finished") || nextMove.equalsIgnoreCase("error") 
				|| nextMove.equalsIgnoreCase("resetCompleted"))) {
			nextMove = "data(" + nextMove + ")";
		}
		
		return nextMove;
	
	}
}
