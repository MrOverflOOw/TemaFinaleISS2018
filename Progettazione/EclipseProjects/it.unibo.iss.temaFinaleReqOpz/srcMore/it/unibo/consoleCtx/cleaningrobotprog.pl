%====================================================================================
% Context consoleCtx  SYSTEM-configuration: file it.unibo.consoleCtx.cleaningRobotProg.pl 
%====================================================================================
context(progctx, "localhost",  "TCP", "8019" ).  		 
context(consolectx, "localhost",  "TCP", "8020" ).  		 
%%% -------------------------------------------
qactor( qafrontendactivator , consolectx, "it.unibo.qafrontendactivator.MsgHandle_Qafrontendactivator"   ). %%store msgs 
qactor( qafrontendactivator_ctrl , consolectx, "it.unibo.qafrontendactivator.Qafrontendactivator"   ). %%control-driven 
qactor( qafrontend , consolectx, "it.unibo.qafrontend.MsgHandle_Qafrontend"   ). %%store msgs 
qactor( qafrontend_ctrl , consolectx, "it.unibo.qafrontend.Qafrontend"   ). %%control-driven 
qactor( qabusinesslogicproban , progctx, "it.unibo.qabusinesslogicproban.MsgHandle_Qabusinesslogicproban"   ). %%store msgs 
qactor( qabusinesslogicproban_ctrl , progctx, "it.unibo.qabusinesslogicproban.Qabusinesslogicproban"   ). %%control-driven 
qactor( qamindofrobotproban , progctx, "it.unibo.qamindofrobotproban.MsgHandle_Qamindofrobotproban"   ). %%store msgs 
qactor( qamindofrobotproban_ctrl , progctx, "it.unibo.qamindofrobotproban.Qamindofrobotproban"   ). %%control-driven 
qactor( qaledproban , progctx, "it.unibo.qaledproban.MsgHandle_Qaledproban"   ). %%store msgs 
qactor( qaledproban_ctrl , progctx, "it.unibo.qaledproban.Qaledproban"   ). %%control-driven 
qactor( qahuelamp , progctx, "it.unibo.qahuelamp.MsgHandle_Qahuelamp"   ). %%store msgs 
qactor( qahuelamp_ctrl , progctx, "it.unibo.qahuelamp.Qahuelamp"   ). %%control-driven 
qactor( qarobotproban , progctx, "it.unibo.qarobotproban.MsgHandle_Qarobotproban"   ). %%store msgs 
qactor( qarobotproban_ctrl , progctx, "it.unibo.qarobotproban.Qarobotproban"   ). %%control-driven 
qactor( qasoffrittirobot , progctx, "it.unibo.qasoffrittirobot.MsgHandle_Qasoffrittirobot"   ). %%store msgs 
qactor( qasoffrittirobot_ctrl , progctx, "it.unibo.qasoffrittirobot.Qasoffrittirobot"   ). %%control-driven 
qactor( qacityinformationproban , progctx, "it.unibo.qacityinformationproban.MsgHandle_Qacityinformationproban"   ). %%store msgs 
qactor( qacityinformationproban_ctrl , progctx, "it.unibo.qacityinformationproban.Qacityinformationproban"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evhdeletemap,consolectx,"it.unibo.consoleCtx.Evhdeletemap","deleteMapCmd").  
eventhandler(evhconsole,consolectx,"it.unibo.consoleCtx.Evhconsole","consoleCmd").  
%%% -------------------------------------------

