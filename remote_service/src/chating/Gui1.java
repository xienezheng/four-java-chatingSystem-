package chating;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
public class Gui1 extends JFrame implements ActionListener {
	int i=1;
	String s="";
	String s1="";
		JButton jb=new JButton("发送");
		JPanel jp=new JPanel();
		JLabel jl=new JLabel();
		JTextArea jta=new JTextArea(10,10);
	public Gui1()
	{
		this.setBounds(100,100,600,400);
		this.setVisible(true);
		
		this.add(jp);
		jp.add(jb);
		jp.add(jl);
		jp.add(jta);
		jp.setLayout(null);
		jb.addActionListener(this);
		jta.setBounds(10,300,450,50);
		jb.setBounds(470,300,100,50);
	}
	public void actionPerformed(ActionEvent e )
	{
		 s=jta.getText();
		 s=s1+s;
		 s1=s;
		 
		jl.setBounds(5,5,400,300);
		jl.setText(s);
		
		jta.setText(null);
		try
		{
		Socket socket=new Socket("localhost",12349);
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter os=new PrintWriter(socket.getOutputStream());
		String line;
		for(int i=0;i<100;i++)
		{
			line=s;
			os.println(line);
			os.flush();
			String responseline=is.readLine();
			System.out.println("收到回复："+responseline);
			s=s+responseline;
		}
		is.close();
		os.close();
		socket.close();
		}catch(Exception e)
		{
	e.printStackTrace();
		}
	}
		
		

	public static void main(String args[])
	{
		Gui1 g=new Gui1();
		
	}
}