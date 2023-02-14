public class StudentNotification {
	public StudentNotification() { }
	
	public void notifyStudent(NotificationMode notiMode) {
		notiMode.sendNotification(false);
	}
}
