/* Generated by AN DISI Unibo */ 
/*
This code is generated only ONCE
*/
package it.unibo.qamindofrobotproban;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.ppcr.ai.PPCR;
import it.unibo.qactors.QActorContext;


public class Qamindofrobotproban extends AbstractQamindofrobotproban { 
	public Qamindofrobotproban(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
		super(actorId, myCtx, outEnvView);
		PPCR.init();
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
	
	public String getNextMove(String arg) {
		String nextMove = PPCR.getNextMove(arg);
		if (!(nextMove.equalsIgnoreCase("impassableObstacle") || nextMove.equalsIgnoreCase("obstructedFinalPosition")
				|| nextMove.equalsIgnoreCase("finished") || nextMove.equalsIgnoreCase("error") 
				|| nextMove.equalsIgnoreCase("resetCompleted"))) {
			nextMove = "data(" + nextMove + ")";
		}
		
		return nextMove;
	
	}
}
