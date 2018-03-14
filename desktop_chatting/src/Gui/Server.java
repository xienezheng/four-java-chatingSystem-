package Gui;
import java.io.*;
import java.net.*;
public class Server {
public static void main(String args[])
{ServerSocket server=null;

try
{
	server=new ServerSocket(13456);
	System.out.println("服务器启动"+server.getInetAddress().getHostAddress()+":"+server.getLocalPort());
while(true)
{
	Socket socket=server.accept();
	System.out.println("客户端连接"+socket.getInetAddress().getHostAddress()+socket.getLocalPort());
	new ServerThread(socket).start();
}

}
catch(Exception e)
{
	e.printStackTrace();
}finally
{
try
{
	server.close();
}catch(Exception e)
{
	e.printStackTrace();
}
}
}
}