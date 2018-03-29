package Examples.Basic;

import java.util.TimerTask;

import Protocol.Models.HttpMethod;
import Server.IContext;
import Server.IPipelineMiddleware;
import Utilities.ThreadLauncher;

public class Threadtest implements IPipelineMiddleware {
	
	private TestTimer testTimer; 
	private Boolean trigger = false;
	@Override
	public Boolean Invoke(IContext context) {
		
/*		Boolean started = testTimer.getStarted();
		Boolean finished = testTimer.getFinished();
		
		if (context.Request().method() == HttpMethod.GET) {
			ThreadLauncher threadlauncher = new ThreadLauncher(1);
			
			
		
		
			if ((started == false) && (finished ==false)) {
				context.setResponseStatus(503);
				threadlauncher.launch(testTimer);
			} else if ((started == true) && (finished ==false)) {
				context.setResponseStatus(503);
				context.setResponseBody("Not working yet");
			} else if ((started == true) && (finished ==true)) {
				context.setResponseStatus(200);
				context.addResponseHeader("Content-type", "text/plain");
				context.setResponseBody("\"instance\": \"workingProperley\"");
				testTimer.setFinished(false);
				testTimer.setStarted(false);
			} else {
				context.setResponseStatus(500);
				context.setResponseBody("Started = " + started + ", finished = " + finished );
			}
		}		
		else {
			context.setResponseStatus(404);
			context.setResponseBody("Wrong method " + context.Request().method().toString()+ ", Started = \" + started + \", finished = \" + finished");
		}	
		return true; 
	}*/

	trigger = isFailureCode();
	
	if ((context.Request().method() == HttpMethod.GET)&& (trigger == false)) { 
		context.setResponseStatus(200);
		context.addResponseHeader("Content-type", "text/plain");
		context.setResponseBody("\"instance\": \"workingProperley\"");
	}
	else if ((context.Request().method() == HttpMethod.GET)&& (trigger == true))  {
		context.setResponseStatus(503);
		context.setResponseBody("Not working yet");
		
	}
	else if (context.Request().method() != HttpMethod.GET) {
		context.setResponseStatus(404);
		context.setResponseBody("Wrong method");
	} else {
		context.setResponseStatus(500);
	}
	return false;
}

private Boolean isFailureCode() {
	long time = System.currentTimeMillis();
	long rem = time % 120000;
	if (rem >= 70000) {
		return true;
	} else {
		return false;
	}
}
	
}

