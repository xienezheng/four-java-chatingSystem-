package Gui;
import java.net.*;
import java.io.*;
public class Client1 {
public static void mina(String args[])
{
	try
	{
	Socket socket =new Socket("localhost",13456);
	BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
	BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	PrintWriter os=new PrintWriter(socket.getOutputStream());
	String readline;
	for(int i=0;i<100;i++)
	{readline=sin.readLine();
		os.println(readline);
		os.flush();
		String respon=is.readLine();
		System.out.println("ÊÕµ½»Ø¸´"+respon);
	}
	is.close();
	os.close();
	socket.close();
}catch(IOException e)
{
	e.printStackTrace();
}
}
}
