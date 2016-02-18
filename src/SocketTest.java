import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketTest implements Channel {

	BufferedInputStream din = null;
	BufferedOutputStream dout = null;
	Socket socket;

	public SocketTest(Socket socket) throws IOException {
		this.socket = socket;
		this.din = new BufferedInputStream(socket.getInputStream());
		this.dout = new BufferedOutputStream(socket.getOutputStream());
	}

	/**
	 * @param args
	 */
	/*
	 * public static void main(String args[]) throws Exception { ServerSocket ss
	 * = new ServerSocket(3333); Socket s = ss.accept(); din = new
	 * BufferedInputStream(s.getInputStream()); dout = new
	 * BufferedOutputStream(s.getOutputStream());
	 * 
	 * 
	 * String str = "", str2 = ""; int i = 0; while (!str.equals("stop")) {
	 * if((i++) %2 == 1) { sendToClient(); }else{ receiveFromClient(); }
	 * //dout.flush(); } din.close(); s.close(); ss.close(); }
	 * 
	 * public static void receiveFromClient() throws Exception { BufferedReader
	 * br = new BufferedReader(new InputStreamReader(System.in)); //String str =
	 * din.readUTF(); byte[] buffer = new byte[1024]; //If you handle larger
	 * data use a bigger buffer size int read; StringBuilder builder = new
	 * StringBuilder(); while((read = din.read(buffer)) != -1) {
	 * builder.append(new String(buffer)); // Your code to handle the data }
	 * System.out.println("client says: " + builder.toString());
	 * System.out.println("Type response to be sent to client:"); String str2 =
	 * br.readLine();
	 * System.out.println("Sending -  "+" repsonse from server to "+str2);
	 * dout.write(str2.getBytes()); }
	 * 
	 * public static void sendToClient() throws Exception { BufferedReader br =
	 * new BufferedReader(new InputStreamReader(System.in));
	 * System.out.println("Type request to be sent to client:"); String str2 =
	 * br.readLine(); System.out.println("Sending request to client - "+str2);
	 * dout.write(str2.getBytes()); dout.flush(); //String str = din.readUTF();
	 * byte[] buffer = new byte[1024]; //If you handle larger data use a bigger
	 * buffer size int read; StringBuilder builder = new StringBuilder();
	 * while((din.available() > 0) && (read = din.read(buffer)) != -1) {
	 * builder.append(new String(buffer)); // Your code to handle the data }
	 * System.out.println("Response from client : " + builder.toString());
	 * 
	 * }
	 */

	@Override
	public Object readData() {
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in));
		// String str = din.readUTF();
		byte[] buffer = new byte[1024]; // If you handle larger data use a
										// bigger buffer size
		int read;
		StringBuilder builder = new StringBuilder();
		try {
			while ((din.available() > 0) && (read = din.read(buffer)) != -1) {
				builder.append(new String(buffer));
				// Your code to handle the data
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("client says: " + builder.toString());
		return builder.toString();
	}

	@Override
	public void sendData(Object data) {
		String msg = (String) data;
		System.out.println("Sending request to client - " + msg);

		try {
			dout.write(msg.getBytes());
			dout.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}
