/* Generated by AN DISI Unibo */ 
package it.unibo.qamindofrobotproban;
import it.unibo.qactors.PlanRepeat;
import it.unibo.qactors.QActorContext;
import it.unibo.qactors.StateExecMessage;
import it.unibo.qactors.QActorUtils;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.action.AsynchActionResult;
import it.unibo.qactors.action.IActorAction;
import it.unibo.qactors.action.IActorAction.ActionExecMode;
import it.unibo.qactors.action.IMsgQueue;
import it.unibo.qactors.akka.QActor;
import it.unibo.qactors.StateFun;
import java.util.Stack;
import java.util.Hashtable;
import java.util.concurrent.Callable;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import it.unibo.qactors.action.ActorTimedAction;
public abstract class AbstractQamindofrobotproban extends QActor { 
	protected AsynchActionResult aar = null;
	protected boolean actionResult = true;
	protected alice.tuprolog.SolveInfo sol;
	protected String planFilePath    = null;
	protected String terminationEvId = "default";
	protected String parg="";
	protected boolean bres=false;
	protected IActorAction action;
	 
	
		protected static IOutputEnvView setTheEnv(IOutputEnvView outEnvView ){
			return outEnvView;
		}
		public AbstractQamindofrobotproban(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
			super(actorId, myCtx,  
			"./srcMore/it/unibo/qamindofrobotproban/WorldTheory.pl",
			setTheEnv( outEnvView )  , "init");
			this.planFilePath = "./srcMore/it/unibo/qamindofrobotproban/plans.txt";
	  	}
		@Override
		protected void doJob() throws Exception {
			String name  = getName().replace("_ctrl", "");
			mysupport = (IMsgQueue) QActorUtils.getQActor( name ); 
			initStateTable(); 
	 		initSensorSystem();
	 		history.push(stateTab.get( "init" ));
	  	 	autoSendStateExecMsg();
	  		//QActorContext.terminateQActorSystem(this);//todo
		} 	
		/* 
		* ------------------------------------------------------------
		* PLANS
		* ------------------------------------------------------------
		*/    
	    //genAkkaMshHandleStructure
	    protected void initStateTable(){  	
	    	stateTab.put("handleToutBuiltIn",handleToutBuiltIn);
	    	stateTab.put("init",init);
	    	stateTab.put("waitForThinkingRequest",waitForThinkingRequest);
	    	stateTab.put("handleThinking",handleThinking);
	    }
	    StateFun handleToutBuiltIn = () -> {	
	    	try{	
	    		PlanRepeat pr = PlanRepeat.setUp("handleTout",-1);
	    		String myselfName = "handleToutBuiltIn";  
	    		println( "qamindofrobotproban tout : stops");  
	    		repeatPlanNoTransition(pr,myselfName,"application_"+myselfName,false,false);
	    	}catch(Exception e_handleToutBuiltIn){  
	    		println( getName() + " plan=handleToutBuiltIn WARNING:" + e_handleToutBuiltIn.getMessage() );
	    		QActorContext.terminateQActorSystem(this); 
	    	}
	    };//handleToutBuiltIn
	    
	    StateFun init = () -> {	
	    try{	
	     PlanRepeat pr = PlanRepeat.setUp("init",-1);
	    	String myselfName = "init";  
	    	temporaryStr = "qaconsoleproban(starts)";
	    	println( temporaryStr );  
	    	//switchTo waitForThinkingRequest
	        switchToPlanAsNextState(pr, myselfName, "qamindofrobotproban_"+myselfName, 
	              "waitForThinkingRequest",false, false, null); 
	    }catch(Exception e_init){  
	    	 println( getName() + " plan=init WARNING:" + e_init.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//init
	    
	    StateFun waitForThinkingRequest = () -> {	
	    try{	
	     PlanRepeat pr = PlanRepeat.setUp(getName()+"_waitForThinkingRequest",0);
	     pr.incNumIter(); 	
	    	String myselfName = "waitForThinkingRequest";  
	    	temporaryStr = "\"MIND: waitForThinkingRequest\"";
	    	println( temporaryStr );  
	    	//bbb
	     msgTransition( pr,myselfName,"qamindofrobotproban_"+myselfName,false,
	          new StateFun[]{stateTab.get("handleThinking") }, 
	          new String[]{"true","M","thinkingRequestMsg" },
	          300000, "handleToutBuiltIn" );//msgTransition
	    }catch(Exception e_waitForThinkingRequest){  
	    	 println( getName() + " plan=waitForThinkingRequest WARNING:" + e_waitForThinkingRequest.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//waitForThinkingRequest
	    
	    StateFun handleThinking = () -> {	
	    try{	
	     PlanRepeat pr = PlanRepeat.setUp("handleThinking",-1);
	    	String myselfName = "handleThinking";  
	    	temporaryStr = "\"MIND: handleThinking\"";
	    	println( temporaryStr );  
	    	//onMsg 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("thinkingRequest(\"reset\")");
	    	if( currentMessage != null && currentMessage.msgId().equals("thinkingRequestMsg") && 
	    		pengine.unify(curT, Term.createTerm("thinkingRequest(DATA)")) && 
	    		pengine.unify(curT, Term.createTerm( currentMessage.msgContent() ) )){ 
	    		//println("WARNING: variable substitution not yet fully implemented " ); 
	    		{//actionseq
	    		temporaryStr = "\"ThinkingRequest received: reset\"";
	    		println( temporaryStr );  
	    		it.unibo.ppcr.ai.ppcr.setOptimal( myself  );
	    		parg = "getNextMove(clear)"; 
	    		actorOpExecute(parg, false);	//OCT17		 
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " ??actorOpDone(OP,R)" )) != null ){
	    		{//actionseq
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine, "thought(SENDER,DATA)","thought(mind,R)", guardVars ).toString();
	    		emit( "thought", temporaryStr );
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine,"thinkingRequestReceived(CODE)","thinkingRequestReceived(ok)", guardVars ).toString();
	    		sendMsg("thinkingRequestReceived","qabusinesslogicproban", QActorContext.dispatch, temporaryStr ); 
	    		temporaryStr = "\"MIND: Thought!\"";
	    		println( temporaryStr );  
	    		};//actionseq
	    		}
	    		};//actionseq
	    	}
	    	//onMsg 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("thinkingRequest(\"deleteMap\")");
	    	if( currentMessage != null && currentMessage.msgId().equals("thinkingRequestMsg") && 
	    		pengine.unify(curT, Term.createTerm("thinkingRequest(DATA)")) && 
	    		pengine.unify(curT, Term.createTerm( currentMessage.msgContent() ) )){ 
	    		//println("WARNING: variable substitution not yet fully implemented " ); 
	    		{//actionseq
	    		temporaryStr = "\"ThinkingRequest received: deleteMap\"";
	    		println( temporaryStr );  
	    		parg = "deleteMap()"; 
	    		actorOpExecute(parg, false);	//OCT17		 
	    		};//actionseq
	    	}
	    	//onMsg 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("thinkingRequest(\"obstacle\")");
	    	if( currentMessage != null && currentMessage.msgId().equals("thinkingRequestMsg") && 
	    		pengine.unify(curT, Term.createTerm("thinkingRequest(DATA)")) && 
	    		pengine.unify(curT, Term.createTerm( currentMessage.msgContent() ) )){ 
	    		//println("WARNING: variable substitution not yet fully implemented " ); 
	    		{//actionseq
	    		temporaryStr = "\"ThinkingRequest received: obstacle\"";
	    		println( temporaryStr );  
	    		parg = "getNextMove(obstacle)"; 
	    		actorOpExecute(parg, false);	//OCT17		 
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " ??actorOpDone(OP,R)" )) != null ){
	    		{//actionseq
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine, "thought(SENDER,DATA)","thought(mind,R)", guardVars ).toString();
	    		emit( "thought", temporaryStr );
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine,"thinkingRequestReceived(CODE)","thinkingRequestReceived(ok)", guardVars ).toString();
	    		sendMsg("thinkingRequestReceived","qabusinesslogicproban", QActorContext.dispatch, temporaryStr ); 
	    		temporaryStr = "\"MIND: Thought!\"";
	    		println( temporaryStr );  
	    		};//actionseq
	    		}
	    		};//actionseq
	    	}
	    	//onMsg 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("thinkingRequest(\"clear\")");
	    	if( currentMessage != null && currentMessage.msgId().equals("thinkingRequestMsg") && 
	    		pengine.unify(curT, Term.createTerm("thinkingRequest(DATA)")) && 
	    		pengine.unify(curT, Term.createTerm( currentMessage.msgContent() ) )){ 
	    		//println("WARNING: variable substitution not yet fully implemented " ); 
	    		{//actionseq
	    		temporaryStr = "\"ThinkingRequest received: clear\"";
	    		println( temporaryStr );  
	    		parg = "getNextMove(clear)"; 
	    		actorOpExecute(parg, false);	//OCT17		 
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " ??actorOpDone(OP,R)" )) != null ){
	    		{//actionseq
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine, "thought(SENDER,DATA)","thought(mind,R)", guardVars ).toString();
	    		emit( "thought", temporaryStr );
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine,"thinkingRequestReceived(CODE)","thinkingRequestReceived(ok)", guardVars ).toString();
	    		sendMsg("thinkingRequestReceived","qabusinesslogicproban", QActorContext.dispatch, temporaryStr ); 
	    		temporaryStr = "\"MIND: Thought!\"";
	    		println( temporaryStr );  
	    		};//actionseq
	    		}
	    		};//actionseq
	    	}
	    	repeatPlanNoTransition(pr,myselfName,"qamindofrobotproban_"+myselfName,false,true);
	    }catch(Exception e_handleThinking){  
	    	 println( getName() + " plan=handleThinking WARNING:" + e_handleThinking.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//handleThinking
	    
	    protected void initSensorSystem(){
	    	//doing nothing in a QActor
	    }
	
	}
