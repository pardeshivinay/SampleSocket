import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSocketTest {

	static BufferedInputStream din = null;
	static BufferedOutputStream dout = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("localhost", 3333);
		din = new BufferedInputStream(s.getInputStream());
		dout = new BufferedOutputStream(s.getOutputStream());

		String str = "", str2 = "";
		int i = 0;
		while (!str.equals("stop")) {
			if((i++) % 2 == 1){
				receiveFromServer();
			}else{
				sendToSerer();
			}
		}

		dout.close();
		s.close();
	}

	public static void receiveFromServer() throws Exception {
		
		byte[] buffer = new byte[1024];    //If you handle larger data use a bigger buffer size
		int read;
		StringBuilder builder  = new StringBuilder();
		while((din.available() > 0) && (read = din.read(buffer)) != -1) {
			System.err.println("Read = "+read);
			builder.append(new String(buffer));
		    // Your code to handle the data
		}
		//String str2 = din.read();
		System.out.println("Request from server: " + builder.toString());

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Type response to be sent to server:"); 
		String str = br.readLine();
		System.out.println("Sending response - " + str);
		dout.write(str.getBytes());

	}

	public static void sendToSerer() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Type response to be sent to server:");
		String str = br.readLine();
		System.out.println("Sending request to server - " + str);
		dout.write(str.getBytes());
		dout.flush();
		//String str2 = din.read();
		byte[] buffer = new byte[1024];    //If you handle larger data use a bigger buffer size
		int read;
		StringBuilder builder  = new StringBuilder();
		while((read = din.read(buffer)) != -1) {
			builder.append(new String(buffer));
		    // Your code to handle the data
		}
		System.out.println("Response from server -" + builder.toString());

	}
}
