package Examples.Basic;

import java.util.TimerTask;

import Protocol.Models.HttpMethod;
import Server.IContext;
import Server.IPipelineMiddleware;
import Utilities.ThreadLauncher;

public class Threadtest implements IPipelineMiddleware {
	
	private TestTimer testTimer; 

	@Override
	public Boolean Invoke(IContext context) {
		
		
		if (context.Request().method() == HttpMethod.GET) {
			ThreadLauncher threadlauncher = new ThreadLauncher(1);
		
		
			if ((testTimer.getStarted() == false) && (testTimer.getFinished() ==false)) {
				context.setResponseStatus(503);
				threadlauncher.launch(testTimer);
			} else if ((testTimer.getStarted() == true) && (testTimer.getFinished() ==false)) {
				context.setResponseStatus(503);
			} else if ((testTimer.getStarted() == true) && (testTimer.getFinished() ==true)) {
				context.setResponseStatus(200);
				context.addResponseHeader("Content-type", "text/plain");
				context.setResponseBody("\"instance\": \"workingProperley\"");
				testTimer.setFinished(false);
				testTimer.setStarted(false);
			} else {
				context.setResponseStatus(500);
			}
		}		
		else if (context.Request().method() != HttpMethod.GET) {
			context.setResponseStatus(404);
		}	
		return true;
	}

}
