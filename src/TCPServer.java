import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private ServerSocket server;
    private Socket socket;
    private DataInputStream inputStream;
    public TCPServer(int port)
    {
        try{
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for client connection ...");

            socket = server.accept();
            System.out.println("Client accepted");

            inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String data = "";
            while(!data.equals("over"))
            {
                data = inputStream.readUTF();
                System.out.println(data);
            }
            System.out.println("Closing connection");
            socket.close();
            inputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String args[])
    {
        TCPServer server = new TCPServer(5001);
    }
}