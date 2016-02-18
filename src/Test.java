import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Test {

	public static void main(String[] args) throws IOException {
		System.out.println("Start");
		ServerSocket serverSocket = new ServerSocket(5222);
		Socket socket= serverSocket.accept();
		Channel channel = new SocketTest(socket);
		Object message = channel.readData();
		System.out.println("Message=== = "+message);
	}
}
