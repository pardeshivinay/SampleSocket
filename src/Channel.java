
public interface Channel {

	public void initialize();
	public Object readData();
	public void sendData(Object message);
	public void sendData(Object message,int noOfTimes);
}
