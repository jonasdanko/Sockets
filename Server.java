import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket socket;
    private ServerSocket server;
    private BufferedReader input;
    private PrintWriter output;

    public Server(int port){

        try{
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client to connect...");
            socket = server.accept();
            System.out.println("Client connected to server.");
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //out = new PrintWriter(socket.getOutputStream(), true);
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("Server: GREETINGS");

            String message = "";
            String response = "";

            while (!message.equals("D")){
                try{
                    message = input.readLine();
                    System.out.println(message);
                    if(message.equals("D")){
                        response = "exit";
                    }else if(message.equals("A")){
                        response = socket.getInetAddress().toString().replace("/","");
                    }
                    output.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Closing connection");
            socket.close();
            server.close();
            input.close();
            //out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Server server = new Server(Integer.parseInt(args[0]));
    }
}
