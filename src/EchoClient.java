

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient 
{
    private String host = "localhost";
    private int port = 8000;
    private Socket socket;

    public EchoClient() throws IOException {
        socket = new Socket(host, port);
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        return new PrintWriter(out, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }

    public void talk()  {

        try {
            BufferedReader br = getReader(socket);
            PrintWriter pw = getWriter(socket);
            Scanner sc = new Scanner(System.in);
            String msg = null;
            while ((msg = sc.next()) != null) {
                pw.println(msg);
                System.out.println(br.readLine());
                if (msg.equals("bye")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    public static void main(String[] args) throws IOException {
//        new EchoClient().talk();
//    }
}
