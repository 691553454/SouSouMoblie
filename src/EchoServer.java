

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer extends Thread
{
    String reply="大家帅才是真的帅！";
    String reply2="最近阴雨连绵，记得出门带伞！";
    String reply3="最近工地活少，没钱拿！";
    String reply4="面向赚钱编程！";
    private int port = 8000;
    private ServerSocket serverSocket;

    public EchoServer() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("服务器启动");
    }

    public String echo(String msg) {
        return "服务器传回的数据:" + msg;
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        return new PrintWriter(out, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }

    public void run() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("新的连接以建立");
                System.out.println("客户端IP" + socket.getInetAddress());
                System.out.println("客户端端口号" + socket.getPort());
                BufferedReader br = getReader(socket);
                PrintWriter pw = getWriter(socket);

                String msg = null;
                while ((msg = br.readLine()) != null) {
                    System.out.println("客户端传来的数据:" + msg);
                    if(msg.contains("帅"))
                    {
                	pw.println(reply+"   您还有什么要问的？");
                    }
                    else if(msg.contains("钱"))
                    {
                	pw.println(reply3+"   您还有什么要问的？");
                    }
                    else if(msg.contains("天气"))
                    {
                	pw.println(reply2+"   您还有什么要问的？");
                    }
                    else if(msg.contains("语言"))
                    {
                	pw.println(reply4+"   您还有什么要问的？");
                    }
                    else if (msg.contains("bye"))
                    {
                	pw.println("谢谢您的使用，祝您生活愉快，再见！");
                        break;
                    }
                    pw.flush();
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
    }

//    public static void main(String[] args) throws IOException {
////        new EchoServer().service();
//}
}
