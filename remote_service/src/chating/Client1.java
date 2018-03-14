package chating;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class Client1  {
	
		
	public static void main(String args[])
	{  
	
		
		try
		{
		Socket socket=new Socket("localhost",12361);
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter os=new PrintWriter(socket.getOutputStream());
		String line;
		for(int i=0;i<100;i++)
		{
			line=sin.readLine();
			os.println(line);
			os.flush();
			String responseline=is.readLine();
			System.out.println("ÊÕµ½»Ø¸´£º"+responseline);
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
