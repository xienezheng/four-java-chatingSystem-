package Gui;
import java.io.*;
import java.net.*;
public class ServerThread extends Thread {
Socket socket;
public ServerThread(Socket socket)
{
	this.socket=socket;
}
public void run()
{
try
{
	BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
	BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	PrintWriter os=new PrintWriter(socket.getOutputStream());
	String line;
	for(int i=0;i<100;i++)
	{
		line=is.readLine();
		System.out.println("ÊÕµ½"+line);
		os.print(sin.readLine());
		os.flush();
		
	}
	is.close();
	os.close();
	socket.close();
}catch(Exception e)
{
	e.printStackTrace();
}
}
}
