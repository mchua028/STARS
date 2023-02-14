public abstract class NotificationMode {
	
	private String username, sendMessage;
	
	public NotificationMode(String username, String sendMessage) {
		this.username = username;
		this.sendMessage = sendMessage;
	}
	
	public abstract void sendNotification(boolean adminAccess);
	
	public String getUsername() { return username; }
	
	public String getMessage() { return sendMessage; }
	
}
