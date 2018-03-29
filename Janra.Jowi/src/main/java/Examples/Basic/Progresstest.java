package Examples.Basic;

import Protocol.Models.HttpMethod;
import java.util.Timer;
import java.util.TimerTask;

import Server.IContext;
import Server.IPipelineMiddleware;

public class Progresstest implements IPipelineMiddleware  {

	private Boolean trigger = false;
	
	@Override
	public Boolean Invoke(IContext context) {

		
		if ((context.Request().method() == HttpMethod.GET)&& (isFailureCode() == false)) { 
			context.setResponseStatus(200);
			context.addResponseHeader("Content-type", "text/plain");
			context.setResponseBody("\"instance\": \"workingProperley\"");
		}
		else if ((context.Request().method() == HttpMethod.GET)&& (isFailureCode() == true))  {
			context.setResponseStatus(503);
		}
		else if (context.Request().method() != HttpMethod.GET) {
			context.setResponseStatus(404);
		}

		return true;
	}
	
	private Boolean isFailureCode() {
		long time = System.currentTimeMillis();
		long rem = time % 3600000;
		if (rem <= 3000000) {
			return true;
		} else {
			return false;
		}
	}
}
