//Group #8
//Manoj Kumar Rajasekar - 1014265
//Megha Parikh - 1037741

package jdbcdemo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;



import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;



/*import gui.Sheep_wolf_gui;
import gui.Sheep_wolf_gui.liste_sheep;
import gui.Sheep_wolf_gui.liste_wolf;
*/


public class Sheep_wolf_gui extends JFrame implements ActionListener {
	
	
	public static final Statement mystatement = null;
	private JButton send;
	private JButton start;
	private JLabel user_name_field;
	private JLabel password_field;
	private JTextField text;
	private JTextField win;
	private JPasswordField password;
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	String user;
	String pwd;
	private String sql;
	private String user_name;
	private String pass_word;
	private String roll_no;
	private int old_wins;
	private JLabel sample;
	private JLabel sample_start;
	private JLabel No_wins;
	private JLabel No_losses;
	
	
	public Sheep_wolf_gui()
	{
		panel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		user_name_field = new JLabel("Username");
		text =new JTextField(10);
		password_field = new JLabel("Password");
		password = new JPasswordField(10);
		//win = new JTextField(10);
		send = new JButton("Login");
		start = new JButton("Start the game");
		start.addActionListener(new  Sheep_wolf());
		sample = new JLabel("player name:");
		sample_start = new JLabel("Last Login Date:");
		//num_win = new JButton("num of wins");
		JTextField log_user = new JTextField(10);
		JTextField log_details = new JTextField(10);
		No_wins = new JLabel("Total wins:");
		No_losses = new JLabel("Total losses");
		JTextField wins = new JTextField(10);
		JTextField losses = new JTextField(10);
		JTextField Number_of_losses = new JTextField(10);
		//num_win.addActionListener(this);
		send.addActionListener(this);
		
		panel.add(user_name_field);
		panel.add(text);
		panel.add(password_field);
		panel.add(password);
		panel.add(send);
		
		panel.add(panel1, BorderLayout.SOUTH);
		
		
		//panel2.add(log_details);
		
		panel2.setVisible(false);
		panel.add(panel2, BorderLayout.CENTER);
		//panel.add(win);
		
		
		
		add(panel);
		//add(panel1);
		setSize(700, 550);
		setLocation(300, 300);
		//this.message = message;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println("Hai Mano");
		user = text.getText();
		pwd= password.getText();
		login();
	}
	
	public void login(){
		try{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/abc", "root", "Rajasekar*1");
			
			Statement mystatement = connect.createStatement();
			
			ResultSet myRs = mystatement.executeQuery("Select * from student Where sname='" + user   +"'");
			

			while(myRs.next())
			{
				
				user_name = myRs.getString("sname");
				pass_word = myRs.getString("password");
				roll_no = myRs.getString("rollno");
				old_wins = myRs.getInt("number_of_wins");
			}
			
			if(user_name.equals(user)&& pass_word.equals(pwd) )
			{
					
					//win.setText(roll_no);
					System.out.println(user_name);
					
					
					
					
					JOptionPane.showMessageDialog(this,
					        "Successfully logged in: Ready to Play",
					        "Successful Login",
					        JOptionPane.INFORMATION_MESSAGE);
				
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = new Date();
					
					panel2.setVisible(true);
					
					JTextField log_r = new JTextField(10);
					JTextField log_date = new JTextField(10);
					JTextField wins = new JTextField(10);
					JTextField losses = new JTextField(10);
					
					panel2.add(sample);
					panel2.add(log_r);
					panel2.add(sample_start);
					panel2.add(log_date);
					panel2.add(No_wins);
					panel2.add(wins);
					//panel2.add(No_losses);
					//panel2.add(losses);
					panel2.add(start);
					log_r.setText(user);
					log_date.setText(dateFormat.format(date));
					
					
				
				
				
				System.out.println(dateFormat.format(date)); 
				System.out.println(old_wins);
				//System.out.println(user_name);
			}	
			
			
			else
			{
				
				panel2.setVisible(false);
				
				 JOptionPane.showMessageDialog(this,
					        "Invalid Username or password",
					        "Invalid Credentials",
					        JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Invalid Credentials");
			}
			
			
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		

		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sheep_wolf_gui d = new Sheep_wolf_gui();
		
				
	}
	

	
	
	class Sheep_wolf implements ActionListener
    {       
		//dispose();
		JPanel gui = new JPanel(new BorderLayout(3, 3));
	    // public JPanel [][] squares = new JPanel[8][8];
	      JToolBar tools ;
	      JLabel sheep;
	      JLabel pic;
	       JLabel [] wolves;
	       ObjectOutputStream out=null;
	    //  private JPanel chessBoard;
	       Socket so ;
	        
	         int testX;
	         int testY; 
             InputStream input;
             OutputStream output ;
             UUID uuId = UUID.randomUUID();
	         
	    Sheep_wolf() {
	        //initializeGui();
	    	setTitle(uuId.toString());
	         int xCoordinate;
	         int yCoordinate; 
	    }

	    public void actionPerformed(ActionEvent e) 
	    {
	    	dispose();
	    	JPanel gui = new JPanel(new BorderLayout(3, 3));
	        Container c = getContentPane();
	        //c.setLayout(new GridLayout(8,8, 1,1)); 
	      
	        JFrame test = new JFrame();
	        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
	        tools = new JToolBar();
	        tools.setFloatable(false);
	        gui.add(tools, BorderLayout.PAGE_START);
	        tools.addSeparator();
	        tools.add(new JLabel("Wolf and sheep game"));
	        tools.addSeparator();
		    final long THIRTY_MINUTES = 1800000;
		    final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm : ss");
		    final JLabel clock = new JLabel(sdf.format(new Date(THIRTY_MINUTES)),JLabel.CENTER);
		    int x = 0;
		    ActionListener al = new ActionListener()
		    {
		      long x = THIRTY_MINUTES - 1000;
		      public void actionPerformed(ActionEvent ae)
		      {
		        clock.setText(sdf.format(new Date(x)));
		        x -= 1000;
		       }
		     };
		    new javax.swing.Timer(1000, al).start();
		    JPanel jp = new JPanel();
		    jp.add(clock);
		    tools.add(jp);
		    pack();
		     sheep=new JLabel(new ImageIcon("C:\\Users\\Mano\\Downloads\\final_sheep1.jpg"));
		  
		    wolves = new JLabel[4];
		    for(int i=0 ;i<4;i++)//load wolves background image
		        wolves[i] = new JLabel(new ImageIcon("C:\\Users\\Mano\\Downloads\\final_wolf.png"));
		        wolves[0].setBounds(42,48,29,29);//set the initialization to the wolves position
		        wolves[1].setBounds(102,48,29,29);
		        wolves[2].setBounds(162,48,29,29);
		        wolves[3].setBounds(222,48,29,29);
		        for(int i=0 ;i<4;i++)
		        gui.add(wolves[i]);
		        sheep.setBounds(12,259,29,29);//set size and location to the label(sheep)
		        sheep.setName("sheep");
		        gui.add(sheep);
		        sheep.addMouseListener(new liste_sheep());
		       
				for(int i=0 ;i<4;i++)
					wolves[i].addMouseListener(new liste_wolf());
		         pic = new JLabel(new ImageIcon("C:\\Users\\Mano\\Downloads\\final_Board.gif"));
		    
		  //test.add(pic);
		   
		        
		        gui.add(pic);
		        c.add(gui);
		        test.add(c);
		        test.setSize(270,330);
			    test.setResizable(false);
			    test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    test.setVisible(true);
		         
		         System.out.println("hey");
		         
		         try{
		     		
		    		 so = new Socket(InetAddress.getLocalHost(),5000);
		    		//display.append(" Socket Created\n");
		                    System.out.println("Socket Created");
		                            input = so.getInputStream();
		    			output = so.getOutputStream();

		    			Thread mythread = new Thread(new Runnable() {
		                         @Override
		                         public void run() {
		                             while(true){
		                                 try {
		                                     EventInformation coord = (EventInformation) new ObjectInputStream(input).readObject();
		                                     if(coord.getFrom().equalsIgnoreCase("SERVER")){
		                                         if(!coord.getGuiId().equalsIgnoreCase(uuId.toString())){
		                                             
		                                            System.out.println("from server " + coord);
		                                            coord.getX();
		                                            coord.getY();
		                                            repaint();
		                                            
		                                         }
		                                     }
		                                 } 
		                                 catch (Exception ex) {
		                                     Logger.getLogger(Sheep_wolf_gui.class.getName()).log(Level.SEVERE, null, ex);
		                                 }
		                                 
		      // Or use unshared variant as in client:
		      // MouseCoordinate coord = (MouseCoordinate) in.readUnsharedObject();
		       System.out.println("got response back");
		        
		                             }
		                         }
		                     });
		                            
		                            mythread.start();
		                          

		    			
		    		
		    		}
		    		
		    		catch(Exception e1)
		    		{
		    			System.out.println(e1);
		    		}  
		         
		         
	    }
	    
	    public void senddata(EventInformation eventInformation)
	    {

		  try
		  {     
		  
		      System.out.println("chess_gui.Sheep_wolf_gui.senddata()");
		      out = new ObjectOutputStream(so.getOutputStream());
		      out.writeObject(eventInformation);
		  } 
		  catch (IOException ex) 
		  {
		      ex.printStackTrace();
		  }
	    }

	
	    
	   
	    
	 



	 class liste_wolf extends MouseAdapter 
	 {
	      private Point initialLoc;
	      private Point initialLocOnScreen;
		  private boolean mouseOver = false;
		  private boolean mousePressed = false;
	      int xCoordinate;
	      int yCoordinate;
	      JButton chessPiece;
	      Sheep_wolf_gui swg;
	                         @Override
		 public void mousePressed(MouseEvent me){
	              Component comp = (Component)me.getSource();
	      		  initialLoc = comp.getLocation();
	              initialLocOnScreen = me.getLocationOnScreen();
	       //				if(contains(me.getX(), me.getY())){
//						mousePressed = true;
//						repaint();
//	                                        int x = me.getPoint().x;
//	                                        int y = me.getPoint().y;
	// 
				//	}
				}
				
				@Override
				public void mouseReleased(MouseEvent me){
					mousePressed = false;
					repaint();
	                                Component comp = (Component)me.getSource();
	                                
	      Point locOnScreen = me.getLocationOnScreen();
	      

	      int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
	      int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
	     // swg.squares[][].getLocation(locOnScreen);
	     Component c=(JLabel)me.getSource();
	   //  System.out.println(c);
	      System.out.println("Release location is:"+x+","+y);
	      
	     // if(locOnScreen.y<=200 && locOnScreen.x<=300){
	          
	      if(locOnScreen.y>initialLocOnScreen.y && locOnScreen.x<initialLocOnScreen.x){
	           c.setLocation(initialLoc.x-30, initialLoc.y+30);
	      }
	      else if(locOnScreen.y>initialLocOnScreen.y && locOnScreen.x>initialLocOnScreen.x){
	      c.setLocation(initialLoc.x+30, initialLoc.y+30);
	      }
	      else 
	          //if(locOnScreen.x>=initialLocOnScreen.x || 
	            //  locOnScreen.x<=initialLocOnScreen.x)
	      {
	      JOptionPane.showMessageDialog(null, "Ilegal move");
	      }
				}
	                     //   }
				
				@Override
				public void mouseExited(MouseEvent me){
					mouseOver = false;
					mousePressed = false;
					repaint();
				}
				
				@Override
				public void mouseMoved(MouseEvent me){
					mouseOver = contains(me.getX(), me.getY());
					repaint();
				}
	                         @Override
	   
	    public void mouseDragged(MouseEvent e) 
	    {
	      Component comp = (Component)e.getSource();
	      Point locOnScreen = e.getLocationOnScreen();
	      int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
	      int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
	      comp.setLocation(x, y);
	   }
	}
			 

	    




	 class liste_sheep extends MouseAdapter implements Serializable
	 {
		 private Point initialLoc;
         private Point initialLocOnScreen;
	private boolean mouseOver = false;
	private boolean mousePressed = false;
        int xCoordinate;
        int yCoordinate;
        JButton chessPiece;
        Sheep_wolf_gui swg;
                         @Override
			public void mousePressed(MouseEvent me){
                             Component comp = (Component)me.getSource();
      initialLoc = comp.getLocation();
      initialLocOnScreen = me.getLocationOnScreen();
       //				if(contains(me.getX(), me.getY())){
//					mousePressed = true;
//					repaint();
//                                        int x = me.getPoint().x;
//                                        int y = me.getPoint().y;
// 
			//	}
			}
			
			@Override
			public void mouseReleased(MouseEvent me){
				mousePressed = false;
				repaint();
                                Component comp = (Component)me.getSource();
                                
      Point locOnScreen = me.getLocationOnScreen();
      

      int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
      int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
     // swg.squares[][].getLocation(locOnScreen);
     Component c=(JLabel)me.getSource();
   //  System.out.println(c);
      System.out.println("Release location is:"+x+","+y);
      if(locOnScreen.y>initialLocOnScreen.y && locOnScreen.x<initialLocOnScreen.x){
           c.setLocation(initialLoc.x-30, initialLoc.y+30);
      }
      else if(locOnScreen.y>initialLocOnScreen.y && locOnScreen.x>initialLocOnScreen.x){
      c.setLocation(initialLoc.x+30, initialLoc.y+30);
      }
      else if(locOnScreen.y<initialLocOnScreen.y && locOnScreen.x<initialLocOnScreen.x){
           c.setLocation(initialLoc.x-30, initialLoc.y-30);
      }
       else if(locOnScreen.y<initialLocOnScreen.y && locOnScreen.x>initialLocOnScreen.x){
           c.setLocation(initialLoc.x+30, initialLoc.y-30);
      }
      else 
          //if(locOnScreen.x>=initialLocOnScreen.x || 
            //  locOnScreen.x<=initialLocOnScreen.x)
      {
      JOptionPane.showMessageDialog(null, "Ilegal move");
      }
			}
			
			@Override
			public void mouseExited(MouseEvent me){
				mouseOver = false;
				mousePressed = false;
				repaint();
			}
			
			@Override
			public void mouseMoved(MouseEvent me){
				mouseOver = contains(me.getX(), me.getY());
				repaint();
			}
                         @Override
		   public void mouseDragged(MouseEvent e) {
		      Component comp = (Component)e.getSource();
		      Point locOnScreen = e.getLocationOnScreen();
		
		      int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
		      int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
		      comp.setLocation(x, y);
		   }
	  }
	 
	 class Circlewolf extends JLabel{
			
			private boolean mouseOver = false;
			private boolean mousePressed = false;
		         private int dragOffsetX;
		    private int dragOffsetY;
		        int xCoordinate;
		    int yCoordinate;
		     JLabel chessPiece;
			public Circlewolf(String text){
				super(text);
				setOpaque(false);
				//setFocusPainted(false);
				//setBorderPainted(false);
					
			}
			
			private int getDiameter(){
				int diameter = Math.min(getWidth(), getHeight());
				return diameter;
			}
			
			@Override
			public Dimension getPreferredSize(){
				FontMetrics metrics = getGraphics().getFontMetrics(getFont());
				int minDiameter = 8+Math.max(metrics.stringWidth(getText()), metrics.getHeight());
				return new Dimension(minDiameter, minDiameter);
			}
			
			@Override
			public boolean contains(int x, int y){
				int radius = getDiameter()/2;
				return Point2D.distance(x, y, getWidth()/2, getHeight()/2) < radius;
			}
			
			@Override
			public void paintComponent(Graphics g){
				
				int diameter = getDiameter();
				int radius = diameter/2;
				
				if(mousePressed){
					g.setColor(Color.LIGHT_GRAY);
				}
				
		                else{
		                    g.setColor(Color.DARK_GRAY);
		                
				}
				g.fillOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);
				
				if(mouseOver){
					g.setColor(Color.BLUE);
				}
				else{
					g.setColor(Color.BLACK);
				}
				g.drawOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);
				
				g.setColor(Color.BLACK);
				g.setFont(getFont());
				FontMetrics metrics = g.getFontMetrics(getFont());
				int stringWidth = metrics.stringWidth(getText());
				int stringHeight = metrics.getHeight();
				g.drawString(getText(), getWidth()/2 - stringWidth/2, getHeight()/2 + stringHeight/4);
			}
		}
	 class CircleButtonsheep extends JButton{
		 private Point initialLoc;
	         private Point initialLocOnScreen;
		private boolean mouseOver = false;
		private boolean mousePressed = false;
	        int xCoordinate;
	        int yCoordinate;
	        JButton chessPiece;
	        Sheep_wolf_gui swg;
		public CircleButtonsheep(String text){
			super(text);
			setOpaque(false);
			setFocusPainted(false);
			setBorderPainted(false);
			
			MouseAdapter mouseListener = new MouseAdapter(){
				
			@Override
				public void mousePressed(MouseEvent me){
	                             Component comp = (Component)me.getSource();
	      initialLoc = comp.getLocation();
	      initialLocOnScreen = me.getLocationOnScreen();
	       //				if(contains(me.getX(), me.getY())){
//						mousePressed = true;
//						repaint();
//	                                        int x = me.getPoint().x;
//	                                        int y = me.getPoint().y;
	// 
				//	}
				}
				
				@Override
				public void mouseReleased(MouseEvent me){
					mousePressed = false;
					repaint();
	                                Component comp = (Component)me.getSource();
	                                
	      Point locOnScreen = me.getLocationOnScreen();
	      

	      int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
	      int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
	     // swg.squares[][].getLocation(locOnScreen);
	     Component c=(JPanel)me.getSource();
	     System.out.println(c);
	      System.out.println("Release location is:"+x+","+y);
	      c.setLocation(x, y);
	      
				}
				
				@Override
				public void mouseExited(MouseEvent me){
					mouseOver = false;
					mousePressed = false;
					repaint();
				}
				
				@Override
				public void mouseMoved(MouseEvent me){
					mouseOver = contains(me.getX(), me.getY());
					repaint();
				}
	                         @Override
	   public void mouseDragged(MouseEvent e) {
	      Component comp = (Component)e.getSource();
	      Point locOnScreen = e.getLocationOnScreen();

	      int x = locOnScreen.x - initialLocOnScreen.x + initialLoc.x;
	      int y = locOnScreen.y - initialLocOnScreen.y + initialLoc.y;
	      comp.setLocation(x, y);
	   }
			};
				
			
			addMouseListener(mouseListener);
			addMouseMotionListener(mouseListener);		
		}
		
		private int getDiameter(){
			int diameter = Math.min(getWidth(), getHeight());
			return diameter;
		}
		
		@Override
		public Dimension getPreferredSize(){
			FontMetrics metrics = getGraphics().getFontMetrics(getFont());
			int minDiameter = Math.max(metrics.stringWidth(getText()), metrics.getHeight());
			return new Dimension(minDiameter, minDiameter);
		}
		
		@Override
		public boolean contains(int x, int y){
			int radius = getDiameter()/2;
			return Point2D.distance(x, y, getWidth()/2, getHeight()/2) < radius;
		}
		
		@Override
		public void paintComponent(Graphics g){
			
			int diameter = getDiameter();
			int radius = diameter/2;
			
			if(mousePressed){
				g.setColor(Color.LIGHT_GRAY);
			}
			
	                else{
	                    g.setColor(Color.white);
	                
			}
			g.fillOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter-1, diameter-2);
			
			if(mouseOver){
				g.setColor(Color.BLUE);
			}
			else{
				g.setColor(Color.BLACK);
			}
			g.drawOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter-1, diameter-2);
			
			g.setColor(Color.BLACK);
			g.setFont(getFont());
			FontMetrics metrics = g.getFontMetrics(getFont());
			int stringWidth = metrics.stringWidth(getText());
			int stringHeight = metrics.getHeight();
			g.drawString(getText(), getWidth()/2 - stringWidth/2, getHeight()/2 + stringHeight/4);
		}
	}
	

			 

	} 
} 
	