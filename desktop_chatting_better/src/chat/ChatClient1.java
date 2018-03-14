

package chat;

import java.awt.BorderLayout; 
import java.awt.Frame; 
import java.awt.TextArea; 
import java.awt.TextField; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.Random; 
  
/** 
 * 在线聊天客户端 步骤： 
 
 *1、生成图形窗口界面轮廓 
 
 *2、为轮廓添加关闭事件 
 
 *3、在轮廓中加入输入区域和内容展示区域 
 
 *4、为输入区域添加回车事件 
 * 5、建立服务端连接并发送数据 
 * 
 * @author tuzongxun123 
 * 
 */
public class ChatClient1 extends Frame { 
  /** 
   * 
   */
  private static final long serialVersionUID = 1L; 
  // 用户输入区域 
  private TextField tfTxt = new TextField(); 
  // 内容展示区域 
  private TextArea tarea = new TextArea(); 
  private Socket socket = null; 
  // 数据输出流 
  private DataOutputStream dataOutputStream = null; 
  // 数据输入流 
  private DataInputStream dataInputStream = null; 
  private boolean isConnect = false; 
  Thread tReceive = new Thread(new ReceiveThread()); 
  String name = ""; 
  
  public static void main(String[] args) { 
    ChatClient1 chatClient = new ChatClient1(); 
    chatClient.createName(); 
    chatClient.launcFrame(); 
  
  } 
  
  /** 
   * 建立一个简单的图形化窗口 
   * 
   * @author：tuzongxun 
   * @Title: launcFrame 
   * @param 
   * @return void 
   * @date May 18, 2016 9:57:00 AM 
   * @throws 
   */
  public void launcFrame() { 
	  setLocation(300, 200); 
	    this.setSize(200, 400); 
    add(tfTxt, BorderLayout.SOUTH); 
    add(tarea, BorderLayout.NORTH); 
    // 根据窗口里面的布局及组件的preferedSize来确定frame的最佳大小 
    pack(); 
    // 监听图形界面窗口的关闭事件 
    this.addWindowListener(new WindowAdapter() { 
  
     
      public void windowClosing(WindowEvent e) { 
        System.exit(0); 
        disConnect(); 
      } 
    }); 
    tfTxt.addActionListener(new TFLister()); 
    // 设置窗口可见 
    setVisible(true); 
    connect(); 
    // 启动接受消息的线程 
    tReceive.start(); 
  } 
  
  /** 
   * 连接服务器 
   * 
   * @author：tuzongxun 
   * @Title: connect 
   * @param 
   * @return void 
   * @date May 18, 2016 9:56:49 AM 
   * @throws 
   */
  public void connect() { 
    try { 
      // 新建服务端连接 
      socket = new Socket("127.0.0.1", 8912); 
      // 获取客户端输出流 
      dataOutputStream = new DataOutputStream(socket.getOutputStream()); 
      dataInputStream = new DataInputStream(socket.getInputStream()); 
      System.out.println("连上服务端"); 
      isConnect = true; 
    } catch (UnknownHostException e) { 
      e.printStackTrace(); 
    } catch (IOException e) { 
      e.printStackTrace(); 
    } 
  } 
  
  // 生成随机的客户端名字 
  public void createName() { 
    String[] str1 = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", 
        "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", 
        "9", "0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", 
        "W", "X", "Y", "Z" }; 
    Random ran = new Random(); 
  
    for (int i = 0; i < 6; i++) { 
      // long num = Math.round(Math.random() * (str1.length - 0) + 0); 
      // int n = (int) num; 
      int n = ran.nextInt(str1.length); 
      if (n < str1.length) { 
        String str = str1[n]; 
        name = name + str; 
        System.out.println(name); 
      } else { 
        i--; 
        continue; 
      } 
  
    } 
    this.setTitle(name); 
  } 
  
  /** 
   * 关闭客户端资源 
   * 
   * @author：tuzongxun 
   * @Title: disConnect 
   * @param 
   * @return void 
   * @date May 18, 2016 9:57:46 AM 
   * @throws 
   */
  public void disConnect() { 
    try { 
      isConnect = false; 
      // 停止线程 
      tReceive.join(); 
    } catch (InterruptedException e) { 
      e.printStackTrace(); 
    } finally { 
      try { 
        if (dataOutputStream != null) { 
          dataOutputStream.close(); 
        } 
        if (socket != null) { 
          socket.close(); 
          socket = null; 
        } 
  
      } catch (IOException e) { 
        e.printStackTrace(); 
      } 
    } 
  } 
  
  /** 
   * 向服务端发送消息 
   * 
   * @author：tuzongxun 
   * @Title: sendMessage 
   * @param @param text 
   * @return void 
   * @date May 18, 2016 9:57:56 AM 
   * @throws 
   */
  private void sendMessage(String text) { 
    try { 
      dataOutputStream.writeUTF(name + ":" + text); 
      dataOutputStream.flush(); 
    } catch (IOException e1) { 
      e1.printStackTrace(); 
    } 
  } 
  
  /** 
   * 图形窗口输入区域监听回车事件 
   * 
   * @author tuzongxun123 
   * 
   */
  private class TFLister implements ActionListener { 
  
    
    public void actionPerformed(ActionEvent e) { 
      String text = tfTxt.getText().trim(); 
      // 清空输入区域信息 
      tfTxt.setText(""); 
      // 回车后发送数据到服务器 
      sendMessage(text); 
    } 
  
  } 
  
  private class ReceiveThread implements Runnable { 
  
   
    public void run() { 
      try { 
        while (isConnect) { 
          String message = dataInputStream.readUTF(); 
          System.out.println(message); 
          String txt = tarea.getText(); 
          if (txt != null && !"".equals(txt.trim())) { 
            message = tarea.getText() + "\n" + message; 
          } 
          tarea.setText(message); 
        } 
      } catch (IOException e) { 
        e.printStackTrace(); 
      } 
    } 
  
  } 
} 