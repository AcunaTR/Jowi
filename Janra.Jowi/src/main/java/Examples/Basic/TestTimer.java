package Examples.Basic;

import java.util.Timer;
import java.util.TimerTask;

public class TestTimer extends TimerTask implements Runnable  {

	
	public Boolean started = false;
	public Boolean finished = false;
	
	@Override
	public void run() {
		finished = true;
	}

	public void main(String args[]){
		TimerTask timertask = new TestTimer();
		started = true;
		
		Timer timer = new Timer(true);
		timer.schedule(timertask, 30*1000);
	}
	
	public Boolean getStarted() {
		return started;
	}

	public void setStarted(Boolean started) {
		this.started = started;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	
	public void finish() {
		
	}
	
	
	public void start() {
		started = true;
	}
	
	
	
}



