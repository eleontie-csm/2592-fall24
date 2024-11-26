import java.net.*;
import java.io.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java KnockKnockServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {
           
           while(true) {
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            inputLine = "";
            // Initiate conversation with client
            //KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = "Ping back: " + inputLine;
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = "Ping back: " + inputLine;
                out.println(outputLine);
		System.out.println(outputLine);
                if (outputLine.equals("Bye."))
                	break;
            }
	    clientSocket.close();
	    }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
