//Group #8
//Manoj Kumar Rajasekar - 1014265
//Megha Parikh - 1037741

package jdbcdemo;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Megha Parikh
 */
public class Sheep_wolf_server extends JFrame  {
     //private JTextArea display1;
     private JFrame frame1;
      String sa1,sa2;
        int a,b;
        JTextArea display1 = new JTextArea();
        
          
        /*
        Constructor which creates GUI of server
        */
     public Sheep_wolf_server(){
         System.out.println("SERVER constructor");
         frame1 = new JFrame("Server");
		//JPanel panel1 = new JPanel();
		
		display1.setBounds(10,10,310,300);
                //add("center",display1);
                frame1.setSize(350,400);
                frame1.getContentPane().setLayout(null);
		//frame1.setLayout(null);
		frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.add(display1);
                display1.setText("Server Started\n");
		frame1.setVisible(true);
                
     }
       
        /*
       Method which create connection to the client when client arrive for request
        */
     List<Socket> sockets = new CopyOnWriteArrayList<Socket>();
     public void runser(){
         try{     
		
                System.out.println("hello");
                ServerSocket s1 = new ServerSocket(5000,100);
                while(true){
		//display1.append("Server is waiting for client request");
                System.out.println("Server is waiting for client request");
		Socket ss = s1.accept();
		
		//
                //display1.append("Client connected");
                System.out.println("Client Connected");
                sockets.add(ss);
		DisposableServer server =new DisposableServer(ss, sockets,this);
                  server.start();      
                //PrintStream p=new PrintStream(ss.getOutputStream());
                }  
		
	}
	
	
	catch(IOException e){System.out.println(e);}  
	}
     
         
     
    public static void main(String args[]) 
    {    Sheep_wolf_server s=new Sheep_wolf_server();
    s.runser();
}
  
        /*
      Disposable server class extends thread so we can create multiple clients 
    It takes input which is passed from server
    process the corresponding operation and send the output using outputstream
        */
class DisposableServer extends Thread{
//DataInput in; 
//DataOutput out;
//Socket socket; 
InputStream in=null ;
    OutputStream out = null;
Sheep_wolf_server soc;
//String adr;
List<Socket> sockets = null;
final Socket socket;
public DisposableServer(Socket socket, List<Socket> sockets, Sheep_wolf_server soc){ 
    
    this.soc=soc;
    this.sockets = sockets; 
    this.socket = socket;
    try {
//in = new DataInputStream(socket.getInputStream()); 
//out = new DataOutputStream(socket.getOutputStream()); 
    in = socket.getInputStream();
   /// out = socket.getOutputStream();
    } 
catch (IOException ioe) {
            }
} 

public void broadCast(EventInformation coord) throws IOException{
    for(Socket socketInternal : sockets){
        new ObjectOutputStream(socketInternal.getOutputStream()).writeObject(coord);
    }
}

public void run() {
    boolean val = true;

System.out.println("Connection Received");

try { 
   
    display1.append("Hello from Server\n");

//while (val){
while(true) {
  EventInformation coord = (EventInformation) new ObjectInputStream(in).readObject();
  // Or use unshared variant as in client:
  // MouseCoordinate coord = (MouseCoordinate) in.readUnsharedObject();
   coord.setFrom("SERVER");
  // System.out.println("kai to kar");
  System.out.println(coord);
  broadCast(coord);
 // new ObjectOutputStream(out).writeObject(coord);
  // do something with coordinate...

					
					//soc.display1.insert("X and Y :"+ x+ " " + y ,0);	
					//System.out.println(x+","+y);
//					

display1.append("Transmission complete\n. Closing socket.\n" );



System.out.println("Transmission Complete\n");

//socket.close(); 
}
}catch (IOException ioe) {
    ioe.printStackTrace(); 
}catch(ClassNotFoundException e) {
    e.printStackTrace();
}
} // run } // DisposableServer } // ServerMuti
     }

}