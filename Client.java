import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String hostName;
    private int portNum;
    private Socket socket;
    private BufferedReader input;
    private BufferedReader response;
    private PrintWriter out;

    public Client (String hostName, int portNum){
        this.hostName = hostName;
        this.portNum = portNum;

        try {
                socket = new Socket(hostName, portNum);
                System.out.println("Session has been established.");
                input = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(socket.getOutputStream(), true);
                response = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(response.readLine());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message = "";
        String res = "";

        while(!res.equals("exit")){
            try{
                message = input.readLine();
                out.println(message);
                res =  response.readLine();
                System.out.println("Server: " + res);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try{
            input.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        Client client = new Client(args[0], Integer.parseInt(args[1]));
    }
}
