

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
    String reply="���˧�������˧��";
    String reply2="����������࣬�ǵó��Ŵ�ɡ��";
    String reply3="������ػ��٣�ûǮ�ã�";
    String reply4="����׬Ǯ��̣�";
    private int port = 8000;
    private ServerSocket serverSocket;

    public EchoServer() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("����������");
    }

    public String echo(String msg) {
        return "���������ص�����:" + msg;
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
                System.out.println("�µ������Խ���");
                System.out.println("�ͻ���IP" + socket.getInetAddress());
                System.out.println("�ͻ��˶˿ں�" + socket.getPort());
                BufferedReader br = getReader(socket);
                PrintWriter pw = getWriter(socket);

                String msg = null;
                while ((msg = br.readLine()) != null) {
                    System.out.println("�ͻ��˴���������:" + msg);
                    if(msg.contains("˧"))
                    {
                	pw.println(reply+"   ������ʲôҪ�ʵģ�");
                    }
                    else if(msg.contains("Ǯ"))
                    {
                	pw.println(reply3+"   ������ʲôҪ�ʵģ�");
                    }
                    else if(msg.contains("����"))
                    {
                	pw.println(reply2+"   ������ʲôҪ�ʵģ�");
                    }
                    else if(msg.contains("����"))
                    {
                	pw.println(reply4+"   ������ʲôҪ�ʵģ�");
                    }
                    else if (msg.contains("bye"))
                    {
                	pw.println("лл����ʹ�ã�ף��������죬�ټ���");
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
