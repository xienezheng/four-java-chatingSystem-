package chating;
import java.io.*;
import java.net.*;
public class Server {
public static void main(String args[])
{
	ServerSocket server=null;
	ServerSocket server1=null;
	try
	{
		server=new ServerSocket(12360);
		server1=new ServerSocket(12361);
		System.out.println("服务器启动");
		while(true)
		{
			Socket socket=server.accept();
			System.out.println("客户一连接");
			Socket socket1=server1.accept();
			System.out.println("客户二连接");
			BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			BufferedReader is1=new BufferedReader(new InputStreamReader(socket1.getInputStream()));
			PrintWriter os1=new PrintWriter(socket1.getOutputStream());
		String line,line1;
		for(int i=0;i<100;i++)
		{
			line=is.readLine();
			line1=is1.readLine();
			System.out.println("收到客户1："+line);
			System.out.println("收到客户2："+line1);
			os.println(line1);
			os.flush();
			os1.println(line);
			os1.flush();
			
			
		}
		is.close();
		os.close();
		socket.close();
		}
		
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
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
