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
