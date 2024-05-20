import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public TCPClient(String address, int port) {
        try{
            socket = new Socket(address, port);
            System.out.println("Connected");

            inputStream = new DataInputStream(System.in);
            outputStream = new DataOutputStream(socket.getOutputStream());

            String data = "";
            while(!data.equals("over"))
            {
                data = inputStream.readLine();
                outputStream.writeUTF(data);
            }
            socket.close();
            inputStream.close();
            outputStream.close();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[])
    {
        TCPClient client = new TCPClient("127.0.0.1", 5001);
    }
}
