// Uso thread per gestire bene il parallelismo tra di loro e il rendez vous
// finale che viene fatto sempre dento questo dispatcher. Non c'e' bisogno di
// SingleThreadModel (deprecato) perche gestisco con i thread la sincronizzazione


public class Program extends Thread{
	

	public Program() {

	}

	@Override
	public void run() {
        try {
			
        }
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
}

thread.start(); //start the run part of thread
thread.join();  // wait until the thread end
