import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientTest {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost",5222);
		Channel channel = new SocketTest(socket);
		channel.sendData("Hello");
	}

}
