public class MaskThread implements Runnable {
	private boolean endOfPassword;
	
	public void run() {
		endOfPassword = false;
		while (!endOfPassword) {
			System.out.print("\010 "); // masking each character of the password with a space being displayed as the password is being entered
			try {
				Thread.currentThread().sleep(1); // takes 1ms to mask the character entered by the user
			}
			catch (InterruptedException e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
	}
	
	public void stopMasking() {
		endOfPassword = true;
	}
}
