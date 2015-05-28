import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.lang.management.ManagementFactory;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.*;
import javax.swing.JLabel;


import javax.swing.JButton;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.FileNotFoundException;

import javax.crypto.*;

import java.security.*;

//import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;


public class gui3 extends cryptography
{

	private static final Color WHITE = null;

	JFrame frame;
	String[] parts;
	JMenuBar menuBar = new JMenuBar();
	JMenu mnFile = new JMenu("File");
	JMenuItem mntmAddDevice = new JMenuItem("Register Device");
	JMenuItem mntmExit = new JMenuItem("Exit");
	JMenu mnOptions = new JMenu("Options");
	JMenuItem mntmChangePass = new JMenuItem("Change Password");
	JMenuItem mntmSecuredFolder = new JMenuItem("Secured Folder");
	JMenuItem mntmChangeSecuredFolder = new JMenuItem("Change Secured Folder");
	JMenuItem mntmSecurityOptions = new JMenuItem("Security Options");
	JMenu mnHelp = new JMenu("Help");
	JMenuItem mntmContactUs = new JMenuItem("Contact Us");
	JMenuItem mntmAbout = new JMenuItem("Help Topics");
	
	JCheckBox chk,o1,o2,chkFiles;
	JButton btnSearch,btnDevice,btnBrowse,btnSubmit,btnSkip, btnAddFiles,btnAddfile,btnDelete,btnSelectall,btnUnselectall,btnDone,btnDone2,btnDone3,btnCancel,btnAddUser,btnClose;
	JButton btnAddDevice;
	JButton btnNext1 = new JButton();
	JButton btnNext2,btnBack1,btnBack2,btnFinish;
	JButton btnTurnMonitorOn, btnTurnMonitorOff,btnMonitor,btnMenu,btnDoneMain;
	JButton btnCustomFolder, btnDefaultFolder,btnFolderDone;
	ButtonGroup bg,bgDevice;
	JButton btnDeviceCancel,btnFolderCancel,btnSecurityCancel;
	JRadioButton r1,r2,r3,r4,radioDevice;
	JLabel lbl=new JLabel("hello");
	JLabel lblFolder=new JLabel("");	
	JLabel lblSelectedFolder = new JLabel("");
	JLabel lblCurrentFolder = new JLabel("");
	JLabel lblsearch = new JLabel("");
	JLabel lblwarning = new JLabel("");
	JLabel Lblnofile = new JLabel("No file selected");
	JLabel lblSecureMode = new JLabel("");
	JLabel lblMonitor=new JLabel("");
	JLabel lblState=new JLabel("");
	JLabel lblDevice=new JLabel("");
	JTextField files_name,selected_file,selected_folder,current_folder;
	File selFile;
	JPanel panel1,panel;
	JScrollPane scrollpane1,scrollpane;
	Boolean deviceadd,fileadd,duplicatefile,check,duplicateDevice,monitorOn,folderadd,securityOp,secureFolder,monitorFolder,monitor,wiz;
	Process process;
	String currentUser = new String();
	String deviceName;
	String path=System.getProperty("user.dir")+"\\bluetooth\\";
	Runtime runTime;
	int counter,folderType;
	File originalFile, fakeFile,originalFile1, fakeFile1;
	String deviceParts[];
	JFileChooser chooser;
	
	

	final JList list = new JList(new String[] {"a","b","c"});
	
	/*
	list.addMouseMotionListener(new MouseMotionListener() {
	    public void mouseMoved(MouseEvent e) {
	        final int x = e.getX();
	        final int y = e.getY();
	        // only display a hand if the cursor is over the items
	        final Rectangle cellBounds = list.getCellBounds(0, list.getModel().getSize() - 1);
	        if (cellBounds != null && cellBounds.contains(x, y)) {
	            list.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        } else {
	            list.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	        }
	    }

	    public void mouseDragged(MouseEvent e) {
	    }
	});*/
	
	/* public gui3(JFrame frame, String str) {
	        super(frame, str);
	        addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent evt) {
	                System.exit(0);
	            }
	        });
	    }*/
	
	public static void main(String[] args) throws IOException {
		final gui3 window = new gui3();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//gui3 window = new gui3();
					
					window.frame.setVisible(true);
				

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public boolean asd()
	{
		try
		{
			if(systemBluetooth.check())
				try
				{
					String t = new String(bir.manu());	
					
					panel.remove(lblsearch);
					frame.revalidate();
					frame.repaint();
					
					parts = t.split("\n");
					bgDevice=new ButtonGroup();
					if(parts.length!=1)
					{
						btnDevice.setEnabled(true);
						btnNext1.setEnabled(true);
						for(int i=0;i<parts.length;i+=2)
						{
		
							
							radioDevice = new JRadioButton(parts[i]);	
							radioDevice.setName(parts[i+1]);
							//radioDevice.setText(parts[i]);
							
							bgDevice.add(radioDevice);
							
							/*frame.revalidate();
							frame.repaint();*/
							radioDevice.setBackground(WHITE);
							if(i==0)
								radioDevice.setSelected(true);
							
							radioDevice.setBounds(20, 45+i*20, 200, 15);
							panel.add(radioDevice);
		
						}
						btnDevice.setEnabled(true);
					}
					else
					{
						System.out.println("No Device Found!");
						return false;
					}
					frame.revalidate();
					frame.repaint();					
		
				}
				catch(IOException e)
				{	
				}
			else
			{
				System.out.println("2Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.");
				JOptionPane.showMessageDialog(frame,"Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.",
						"Info", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
		catch(Exception e)
		{
			System.out.println("1Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.");
			JOptionPane.showMessageDialog(frame,"Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.",
					"Info", JOptionPane.INFORMATION_MESSAGE);	
		}
		return true;
	}


	JLabel jlab;
	String address = new String();
	private JTextField textField1;
	private JPasswordField passwordField;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JPasswordField passwordField3;

	private void address() throws IOException
	{  
		
		duplicateDevice=false;
		try{
			
			
			originalFile  = new File(path+"deviceCode.txt");
			fakeFile  = new File(path+"deviceCode.lms");
			fakeFile.renameTo(originalFile);
			
			
			if (originalFile.exists()){	
			BufferedReader input = new BufferedReader(new FileReader(originalFile));
			
			
				
			String str;
			
			if((str=input.readLine())!=null)
			{

					deviceParts=str.split(">>");
					
				if(decryption(deviceParts[0]).equals(address))
				{
					
					duplicateDevice=true;				            		
					
					lblwarning.setText("Device already added : "+decryption(deviceParts[1]));
					frame.getContentPane().add(lblwarning);
					frame.revalidate();
					frame.repaint();
					
				}

			}			

			input.close();  
			}
			originalFile.renameTo(fakeFile);
			
			
			if(duplicateDevice==false) {
			
			originalFile  = new File(path+"deviceCode.txt");
			fakeFile  = new File(path+"deviceCode.lms");
			fakeFile.renameTo(originalFile);

			BufferedWriter output = new BufferedWriter(new FileWriter(originalFile));
		

				System.out.println("shuru se");
				output.write(encryption(address)+">>"+encryption(deviceName));
				System.out.println("File Written Successfully");
				lblwarning.setText("Device added : "+deviceName);				
				deviceadd=true;


			
			
			output.close();		
			originalFile.renameTo(fakeFile);
			}
		}catch(Exception ee1){
			System.out.println("Could not write to a file"+ee1);
		}

	}

	/*private void next()
	{
		fileadd = false;
		JLabel lb = new JLabel("Add Files to secure");
		lb.setBounds(20, 5, 200, 25);
		frame.getContentPane().add(lb);


		selected_file = new JTextField();
		selected_file.setBounds(20, 30, 300, 25);
		frame.getContentPane().add(selected_file);

		btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(330,30,90,23);
		frame.getContentPane().add(btnBrowse);


		btnAddfile = new JButton("Add File");
		btnAddfile.setBounds(20,60,120,23);
		frame.getContentPane().add(btnAddfile);

		btnSelectall = new JButton("Select All");
		btnSelectall.setBounds(20,230,90,23);
		frame.getContentPane().add(btnSelectall);

		btnUnselectall = new JButton("Unselect All");
		btnUnselectall.setBounds(120,230,110,23);
		frame.getContentPane().add(btnUnselectall);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(240,230,90,23);	
		frame.getContentPane().add(btnDelete);

		btnDone2 = new JButton("Done");
		btnDone2.setBounds(340, 230, 70, 23);
		frame.getContentPane().add(btnDone2);



		JLabel lb1 = new JLabel("Files Added");
		lb1.setBounds(20, 90, 200, 25);
		frame.getContentPane().add(lb1);


		scrollpane1 = new JScrollPane();
		scrollpane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane1.setBounds(20, 110, 400, 110);
		frame.getContentPane().add(scrollpane1);

		panel1 = new JPanel();
		//panel1.setBounds(100,60,50,100);
		scrollpane1.setViewportView(panel1);
		panel1.setLayout(null);
		panel1.setLayout(new GridLayout(0,1));


		File f = new File("c:/bluetooth/selectedFiles.txt");
		if(f.exists())
		{

			try{


				BufferedReader input = new BufferedReader(new FileReader(path+"selectedFiles.txt"));

				String  str;
				int c;

				int f1=0;
				c=input.read();
				if(c==-1)
				{
					panel1.add(Lblnofile);	

				} while((str=input.readLine())!=null)
				{

					if(f1==0)
					{
						chkFiles = new JCheckBox((char)c+str);
						f1++;
					}
					else
						chkFiles = new JCheckBox(str);

					panel1.add(chkFiles);
					frame.revalidate();
					frame.repaint();
				}	            
				
				input.close();
				originalFile.renameTo(fakeFile);
				
			}catch(Exception ee){
				System.out.println("Could not read from file"+ee);
			}

		}
		else
		{
			panel1.add(Lblnofile);
			try {
				originalFile  = new File(path+"selectedFiles.txt");
				fakeFile  = new File(path+"selectedFiles.lms");
				fakeFile.renameTo(originalFile);
				BufferedWriter output = new BufferedWriter(new FileWriter(originalFile));
				
				output.close();
				originalFile.renameTo(fakeFile);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


		btnBrowse.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if(lblwarning.getText()!="")
					frame.getContentPane().remove(lblwarning);

				String filename = File.separator+"tmp";
				JFileChooser fc = new JFileChooser(new File(filename));

				// Show open dialog; this method does not return until the dialog is closed
				fc.showOpenDialog(frame);
				selFile = fc.getSelectedFile();

				// Show save dialog; this method does not return until the dialog is closed
				//	 fc.showSaveDialog(frame);
				selFile = fc.getSelectedFile();
				selected_file.setText(selFile.getName());
		    }
		}); 
		
		btnDone2.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().removeAll();
				menu();
				frame.revalidate();
				frame.repaint();
				if(fileadd)
					JOptionPane.showMessageDialog(frame, "Selected Files will be secured when the registered \nbluetooth device is out of range.",
							"Info", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(frame, "No file was added.",
							"Info", JOptionPane.INFORMATION_MESSAGE);
		    }
		}); 
		
		btnSelectall.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	lblwarning.setText("");

				for(Component component : panel1.getComponents()) 
				{
					if(component instanceof JCheckBox) {

						((JCheckBox)component).setSelected(true);
					}

				}
		    }
		}); 
		
		btnUnselectall.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	for(Component component : panel1.getComponents()) {
					if(component instanceof JCheckBox) {

						((JCheckBox)component).setSelected(false);
					}


				}
		    }
		}); 
		
		btnDelete.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	try{




					String lineToRemove = new String("");
					for(Component component : panel1.getComponents()) {
						if(component instanceof JCheckBox) {
							if(((JCheckBox)component).isSelected()){

								panel1.remove(component);
								panel1.revalidate();
								panel1.repaint();
								lineToRemove = ((JCheckBox)component).getText();


								File inputFile = new File(path+"selectedFiles.txt");

								File tempFile = new File(path+"myTempFile.txt");

								BufferedReader reader;

								reader = new BufferedReader(new FileReader(inputFile));

								PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

								String currentLine;

								while((currentLine = reader.readLine()) != null) {
									// trim newline when comparing with lineToRemove

									String trimmedLine = currentLine.trim();




									//System.out.println(trimmedLine);
									if(!trimmedLine.equalsIgnoreCase(lineToRemove))
									{
										pw.println(currentLine);
										pw.flush();



									}
									else
									{
										System.out.println(currentLine);
									}

								}
								
								pw.close();
								originalFile.renameTo(fakeFile);
								
								if (!inputFile.delete()) {
									System.out.println("Could not delete file");
									return;
								}

								//Rename the new file to the filename the original file had.
								if (!tempFile.renameTo(inputFile))
									System.out.println("Could not rename file");

							}
						}
					}
				}catch(Exception e2)
				{
					System.out.println(e2);
				}

		    }
		}); 
		
		btnAddfile.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	duplicatefile=false;
				try{
					BufferedReader input = new BufferedReader(new FileReader(path+"selectedFiles.txt"));
					String str;

					while((str=input.readLine())!=null)
					{



						if(str.equals(selFile.getAbsolutePath()))
						{

							duplicatefile=true;				            		
							lblwarning = new JLabel("File already in the list");
							lblwarning.setBounds(150, 60, 200, 23);
							frame.getContentPane().add(lblwarning);
							frame.revalidate();
							frame.repaint();
							break;
						}

					}

					input.close();  
					

					BufferedWriter output = new BufferedWriter(new FileWriter(path+"selectedFiles.txt",true));

					if(duplicatefile==false) {


						output.write(selFile.getAbsolutePath());			            
						output.newLine();
						System.out.println("File Written Successfully");


					}

					//StringBuffer str1 = new StringBuffer("hello");
					selected_file.setText("");

					output.close();		




				}catch(Exception ee1){
					System.out.println("Could not write to a file"+ee1);
				}

				fileadd = true;
				if(!duplicatefile)
				{


					panel1.remove(Lblnofile);
					if(lblwarning.getText()!="")
						frame.getContentPane().remove(lblwarning);

					chkFiles = new JCheckBox(selFile.getAbsolutePath());	        	
					panel1.add(chkFiles);
					frame.revalidate();
					frame.repaint();


				}

		    }
		}); 

	}
*/

	private void setSearchBtnName(String s)
	{
		btnSearch.setName(s);
	}
	/**
	 * Create the application.
	 * @throws IOException 
	 * @wbp.parser.entryPoint
	 */
	public gui3() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	
	
	public void selectFolder()
	{
		lblCurrentFolder.setText("Current Secured Folder");
		lblCurrentFolder.setBounds(20, 20, 300, 30);
		frame.getContentPane().add(lblCurrentFolder);	
		

		current_folder = new JTextField();
		current_folder.setEditable(false);
		current_folder.setBounds(20, 45, 350, 30);
		frame.getContentPane().add(current_folder);
		current_folder.setForeground(Color.RED);
		
		
		lblFolder.setText("Select new folder to secure");
		lblFolder.setBounds(20, 80, 300, 30);
		frame.getContentPane().add(lblFolder);
		
		btnCustomFolder = new JButton("Custom Folder");
		btnCustomFolder.setBounds(20, 105, 140, 30);
		frame.getContentPane().add(btnCustomFolder);
		btnCustomFolder.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallCustomFolder.png"));
		
		
		btnDefaultFolder = new JButton("Default Folder");
		btnDefaultFolder.setBounds(230, 105, 140, 30);
		frame.getContentPane().add(btnDefaultFolder);
		btnDefaultFolder.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallDefaultFolder.png"));
		
		lblSelectedFolder.setText("Selected folder is");
		lblSelectedFolder.setBounds(20, 140, 150, 23);
		frame.getContentPane().add(lblSelectedFolder);
			 
		selected_folder = new JTextField(null);
		selected_folder.setBounds(20,165,350,23);
		frame.getContentPane().add(selected_folder);
				
		btnFolderDone = new JButton("Done");
		btnFolderDone.setBounds(100, 220, 100, 30);
		frame.getContentPane().add(btnFolderDone);
		btnFolderDone.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallDone.png"));
		
		btnFolderCancel = new JButton("Cancel");
		btnFolderCancel.setBounds(220, 220, 100, 30);
		frame.getContentPane().add(btnFolderCancel);
		btnFolderCancel.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallCancel.png"));
		
		
		try {
    		originalFile  = new File(path+"folder.txt");
			fakeFile  = new File(path+"folder.lms");
			fakeFile.renameTo(originalFile);
			if(originalFile.exists()){
			BufferedReader br5 = new BufferedReader(new FileReader(originalFile));
			String securedFolder;
			
			
			// Showing selected folder if selected otherwise no folder is selected!
			try{
			if((securedFolder=decryption(br5.readLine()))!=null)
			{
				
				File folderExisting = new File(securedFolder);
				if(folderExisting.exists())
				{
				current_folder.setText(""+securedFolder);
				}
				else
					current_folder.setText("Currently no folder is selected");
					
			}else
				current_folder.setText("Currently no folder is selected");
			}catch(NullPointerException e1){
				current_folder.setText("Currently no folder is selected");
			}
			
			
			
			br5.close();
			}
			originalFile.renameTo(fakeFile);
				
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
	
		
		folderadd= false;
		folderType=-1;
		
		btnCustomFolder.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				 

				 	chooser = new JFileChooser(); 
				    chooser.setCurrentDirectory(new java.io.File("."));
				    chooser.setDialogTitle("Select a folder");
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				 
				   
				    chooser.setAcceptAllFileFilterUsed(false);
				    
				    if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) { 
				        
				    	selected_folder.setText(""+chooser.getSelectedFile());
					    folderType=0;        }		
					
			    }
		});
		
		
			btnDefaultFolder.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				folderType=1;
				selected_folder.setText(path+"securedFolder");
							    }
		});
			
			
			btnFolderDone.addActionListener(new ActionListener() { 
				 public void actionPerformed(ActionEvent e) {
					 if(folderType!=-1)
					 {
						 folderadd=true;
						 BufferedWriter output;
							try {
					    		originalFile  = new File(path+"folder.txt");
								fakeFile  = new File(path+"folder.lms");
								fakeFile.renameTo(originalFile);
								output = new BufferedWriter(new FileWriter(originalFile));
							
						 if(folderType==0)
						 {
									output.write(encryption(""+chooser.getSelectedFile()));							
									
						 }
						 else if(folderType==1)
						 {
							 		output.write(encryption(path+"securedFolder"));		
							 		new File(path+"securedFolder").mkdirs();	
									
									
						 }
						 output.close();
						 originalFile.renameTo(fakeFile);		
						 } catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 
								
							 
							 
							 
							 frame.revalidate();
							 frame.repaint();
								

						 
					 }
					 
					 frame.getContentPane().removeAll();
					 if((securityOp)||(secureFolder)||(monitorFolder))
					 {
						 		if(selected_folder.getText()!=null)
						 		{
						 		
						 		originalFile  = new File(path+"folder.txt");
								fakeFile  = new File(path+"folder.lms");
								fakeFile.renameTo(originalFile);
								if(originalFile.exists()){
									 try {
								BufferedReader br5 = new BufferedReader(new FileReader(originalFile));
								String securedFolder;
								
								int op=-1;
								int n = br5.read();
					    		if(n != -1)
					    		{
								
								if((securedFolder=decryption((char)n+br5.readLine()))!=null)
								{
									
									File folderExisting = new File(securedFolder);
									if(folderExisting.exists())
									{
									Runtime.getRuntime().exec("explorer.exe "+ securedFolder);
									System.out.println(securedFolder);
									if(wiz)
										op=2;
									else if(securityOp)
									op=0;
									else if(secureFolder)
										op=1;
									else 
										op=2;
									}
								}		
								else
								{
									JOptionPane.showMessageDialog(frame, "No folder was selected",
											"Info", JOptionPane.INFORMATION_MESSAGE);
								}
					    		}
					    		else
					    		{
					    			
					    			if(wiz)
										op=2;
									else if(securityOp)
									op=0;
					    			else if(secureFolder)
										op=1;
									else
										op=2;
									
									frame.revalidate();
									frame.repaint();
									JOptionPane.showMessageDialog(frame, "No folder was selected",
											"Info", JOptionPane.INFORMATION_MESSAGE);
					    		}
								br5.close();
								originalFile.renameTo(fakeFile);
								if(op==0)
									securityOptions();
								else if(op==1)
									menu();
								else
									second();
								}catch (Exception e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								
								
									
							} 
							
							
					 }
					 
					 }
					 else
					 {
						frame.getContentPane().removeAll();
						menu();
						frame.revalidate();
						frame.repaint();
						if(folderadd)
						{
						
							JOptionPane.showMessageDialog(frame, "Folder to secure files is selected",
									"Info", JOptionPane.INFORMATION_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(frame, "No folder was selected",
									"Info", JOptionPane.INFORMATION_MESSAGE);
					 
						
				    }
					 folderType=-1;
				 }
				 
			});
			
			btnFolderCancel.addActionListener(new ActionListener() {          
			    public void actionPerformed(ActionEvent e) {
			    	
			    	frame.getContentPane().removeAll();
					menu();
					frame.revalidate();
					frame.repaint();			
			    }
			});
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
		
	public void menu()
	{

		wiz=false;
		
		originalFile  = new File(path+"username.txt");
		fakeFile  = new File(path+"username.lms");
		//fakeFile.renameTo(originalFile);
		boolean b=false;
		try{
		b = originalFile.renameTo(fakeFile);
		}catch(Exception e3){
			System.out.println(e3);
		}
		if(b)
			System.out.println("Success");
		else
			System.out.println("naah");
		
		
		
		
		btnAddDevice = new JButton("Register Device");
		btnAddDevice.setBounds(40, 70, 170, 30);
		frame.getContentPane().add(btnAddDevice);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(240, 70, 170, 30);
		frame.getContentPane().add(btnChangePassword);
		
		
		JButton btnChangeSecuredFolder = new JButton("Secure Folder");
		btnChangeSecuredFolder.setBounds(40, 120, 170, 30);
		frame.getContentPane().add(btnChangeSecuredFolder);		
		


		JButton btnSecurityOptions = new JButton("Security Options");
		btnSecurityOptions.setBounds(240, 120, 170, 30);
		frame.getContentPane().add(btnSecurityOptions);
		

		
		JButton btnViewSecuredFiles = new JButton("View Secured Files");
		btnViewSecuredFiles.setBounds(40, 170, 170, 30);
		frame.getContentPane().add(btnViewSecuredFiles);
		
		btnDoneMain = new JButton("Done");
		btnDoneMain.setBounds(240, 170, 170, 30);
		frame.getContentPane().add(btnDoneMain);
		
			
		btnAddDevice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddDevice.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallAdd.png"));		
		
		
		btnChangePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChangePassword.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallPassword.png"));
	
		
		btnChangeSecuredFolder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChangeSecuredFolder.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallClosedLock.png"));
		
		
		btnSecurityOptions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSecurityOptions.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallOptions.png"));
		
		 
		btnViewSecuredFiles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewSecuredFiles.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallOpenLock.png"));
		
		
		btnDoneMain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDoneMain.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallDone.png"));
		
		lblSecureMode.setBounds(150,20,200,30);
		frame.getContentPane().add(lblSecureMode);
		
		

		/*btnTurnMonitorOn = new JButton("Activate Security Mode");
		btnTurnMonitorOn.setBounds(30, 146, 180, 23);
		frame.getContentPane().add(btnTurnMonitorOn);
		
		btnTurnMonitorOff = new JButton("Deactivate Security Mode");
		btnTurnMonitorOff.setBounds(230, 146, 180, 23);
		frame.getContentPane().add(btnTurnMonitorOff);*/
		
		
		
		
		
		
		
		int flag = 0;
		originalFile  = new File(path+"pid.txt");
		fakeFile  = new File(path+"pid.lms");
		fakeFile.renameTo(originalFile);
		File file = new File(path+"pid.txt");
		try{
		if (file.exists()){
			
				try {
					originalFile  = new File(path+"pid.txt");
					fakeFile  = new File(path+"pid.lms");
					fakeFile.renameTo(originalFile);
					BufferedReader br = new BufferedReader(new FileReader(originalFile));
					if(br.read()!=-1)
						flag = 1;					
					br.close();
					originalFile.renameTo(fakeFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		}catch(Exception e2){
			System.out.println("Exception : "+ e2);
		}

		if(flag == 0)
		{
			lblSecureMode.setText("Secure Mode Off");
			
			lblSecureMode.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\color_label_circle_grey_T_16x16.png"));
			//btnTurnMonitorOff.setEnabled(false);
			
		}
		
		else
		{
			lblSecureMode.setText("Secure Mode On");
			lblSecureMode.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\green_transparent_16x16.png"));
			
			//btnTurnMonitorOn.setEnabled(false);
		}
		btnChangePassword.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().removeAll();
				change_pass();
				frame.revalidate();
				frame.repaint();
		    }
		}); 
		
		
		btnViewSecuredFiles.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				 
				 try {
					 originalFile  = new File(path+"folder.txt");
						fakeFile  = new File(path+"folder.lms");
						fakeFile.renameTo(originalFile);
						BufferedReader br5 = new BufferedReader(new FileReader(originalFile));
						String securedFolder;
						
						
						
						boolean flag=false;
						if((securedFolder=br5.readLine())!=null)
						{
							securedFolder = decryption(securedFolder);
							File folderExisting = new File(securedFolder);
							if(folderExisting.exists())
							{
							Runtime.getRuntime().exec("explorer.exe "+ securedFolder);
							System.out.println(securedFolder);
							flag=true;
							
							}
						}
							else
							{
						    	frame.getContentPane().removeAll();
								JOptionPane.showMessageDialog(frame,"First select a folder",
										"Info", JOptionPane.INFORMATION_MESSAGE);	
								secureFolder=true;
								frame.revalidate();
								frame.repaint();
								flag=false;
								
															
							
						}
						br5.close();
						originalFile.renameTo(fakeFile);
						if(flag)
						{
							menu();
							frame.revalidate();
							frame.repaint();
						}
						else{
							frame.getContentPane().removeAll();
							frame.revalidate();
							frame.repaint();
							selectFolder();
						}
				 }
				 catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			 }
			 });
		
		
		btnAddDevice.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
				frame.getContentPane().removeAll();
				addDevice();				
				frame.revalidate();
				frame.repaint();
		    }
		}); 
		
		btnSecurityOptions.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().removeAll();
				try {
					securityOptions();
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.revalidate();
				frame.repaint();
		    }
		}); 

		/*btnTurnMonitorOn.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {

				File f2 = new File(path+"deviceCode.txt");
				if(f2.exists())
				{

					lblSecureMode.setText("Secure Mode On");
					
					lblSecureMode.setForeground(Color.GREEN.darker());
					btnTurnMonitorOn.setEnabled(false);
					btnTurnMonitorOff.setEnabled(true);
					frame.revalidate();
					frame.repaint();
					try {
						runTime = Runtime.getRuntime() ;     
						//process = runTime.exec(System.getProperty("user.dir")+"\\blue.exe") ;
						process = runTime.exec(System.getProperty("user.dir")+"\\secure.exe") ;
						
						System.out.println(System.getProperty("user.dir")+"\\secure.exe");
						//process=Runtime.getRuntime().exec("C:/bluetooth/secure.exe");

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else
				{
					JOptionPane.showMessageDialog(frame,"Please add a device first",
							"Info", JOptionPane.INFORMATION_MESSAGE);					
					
				}
		    }
		}); 
		
		btnTurnMonitorOff.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {

		    	lblSecureMode.setText("Secure Mode Off");
		    	lblSecureMode.setForeground(Color.RED);
					try {
						BufferedReader br = new BufferedReader(new FileReader(originalFile));
						String tokill,cmd="";
						if((tokill=br.readLine())!=null)

							cmd = "taskkill /F /PID " + tokill;
						Runtime.getRuntime().exec(cmd);
						br.close();
						
						BufferedWriter output = new BufferedWriter(new FileWriter(originalFile));
						output.close();
						btnTurnMonitorOff.setEnabled(false);
						btnTurnMonitorOn.setEnabled(true);
						frame.revalidate();
						frame.repaint();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				
		    }
		});
		*/
		
		
		btnChangeSecuredFolder.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
   	
		    	frame.getContentPane().removeAll();
				selectFolder();
				frame.revalidate();
				frame.repaint();			
		    	
		    }
		});
		
		btnDoneMain.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				 frame.getContentPane().removeAll();
				 frame.revalidate();
				 frame.repaint();
				 second();
					
			    }
		});
		
		
	}
	
	public void wizard()
	{

		btnNext1 = new JButton("Add Device");
		btnNext1.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallNext.png"));
		btnNext1.setEnabled(false);
		
		

		btnNext2 = new JButton("Next");
		btnNext2.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallNext.png"));
		

		btnBack1 = new JButton("Back");
		btnBack1.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallBack.png"));
		

		btnBack2 = new JButton("Back");
		btnBack2.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallBack.png"));
		

		btnFinish = new JButton("Finish");
		btnFinish.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallFinish.png"));
		
		addDevice();
		//frame.getContentPane().remove(btnDone3);
		frame.getContentPane().remove(btnDeviceCancel);
		frame.getContentPane().remove(btnDevice);		
		frame.getContentPane().add(btnNext1);
		frame.getContentPane().add(btnFinish);
		btnSearch.setBounds(20, 220, 100, 30);		
		btnNext1.setBounds(140, 220, 120, 30);
		btnFinish.setBounds(280, 220, 100, 30);
		
		btnFinish.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				 frame.getContentPane().removeAll();
				 frame.revalidate();
				 frame.repaint();
				 second();					
			    }
		
		});
		
		btnNext1.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				 
				 for (Enumeration<AbstractButton> buttons = bgDevice.getElements(); buttons.hasMoreElements();) 
				 {
			            AbstractButton button = buttons.nextElement();

			            if (button.isSelected()) 
			            {
			            	address = button.getName();			            	
			            	deviceName = button.getText();
			            	frame.revalidate();
			            	frame.repaint();			            	
			            	break;
			            }
				 }
	
				try {
					lblwarning.setText("");
					
					address();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				 frame.getContentPane().removeAll();
				 frame.revalidate();
				 frame.repaint();
				 frame.getContentPane().add(lblwarning);
				 selectFolder();
				 frame.getContentPane().remove(btnFolderDone);
				 frame.getContentPane().remove(btnFolderCancel);
				 frame.getContentPane().add(btnBack1);
				 frame.getContentPane().add(btnNext2);
				 frame.getContentPane().add(btnFinish);
				 btnBack1.setBounds(20, 220, 100, 30);
				 btnNext2.setBounds(142, 220, 100, 30);
				 btnFinish.setBounds(264, 220, 100, 30);			 
					
			    }
		});
		
		btnBack1.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				 frame.getContentPane().removeAll();
				 frame.revalidate();
				 frame.repaint();
				 addDevice();
					//frame.getContentPane().remove(btnDone3);
				 	frame.getContentPane().remove(btnDeviceCancel);
					frame.getContentPane().add(btnNext1);
					frame.getContentPane().add(btnFinish);
					btnSearch.setBounds(20, 220, 93, 30);
					btnDevice.setBounds(121, 220, 113, 30);
					btnNext1.setBounds(244, 220, 83, 30);
					btnFinish.setBounds(336, 220, 85, 30);			 
					
			    }
		});
		btnNext2.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				 
				 if(folderType!=-1)
				 {
					 folderadd=true;
					 BufferedWriter output;
						try {
				    		originalFile  = new File(path+"folder.txt");
							fakeFile  = new File(path+"folder.lms");
							fakeFile.renameTo(originalFile);
							output = new BufferedWriter(new FileWriter(originalFile));
						
					 if(folderType==0)
					 {
								output.write(encryption(""+chooser.getSelectedFile()));							
								
					 }
					 else if(folderType==1)
					 {
						 		output.write(encryption(path+"securedFolder"));		
						 		new File(path+"securedFolder").mkdirs();	
								
								
					 }
					 output.close();
					 originalFile.renameTo(fakeFile);		
					 } catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
							
						 
						 
						 
						 frame.revalidate();
						 frame.repaint();
							

					 
				 }
				 
				 
				 frame.getContentPane().removeAll();
				 frame.revalidate();
				 frame.repaint();
				 try {
					securityOptions();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				 
				 frame.getContentPane().remove(btnSecurityCancel);	
				 frame.getContentPane().add(btnBack2);
					btnDone.setText("Finish");
					btnDone.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallFinish.png"));
					btnBack2.setBounds(20,180,100,30);
					btnAddFiles.setBounds(140,180,170,30);
					btnDone.setBounds(330, 180,100, 30);			 
					
			    }
		});
		
		btnBack2.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				 frame.getContentPane().removeAll();
				 frame.revalidate();
				 frame.repaint();
				 selectFolder();
				 frame.getContentPane().remove(btnFolderDone);
				 frame.getContentPane().remove(btnFolderCancel);
				 frame.getContentPane().add(btnBack1);
				 frame.getContentPane().add(btnNext2);
				 frame.getContentPane().add(btnFinish);
				 btnBack1.setBounds(20, 220, 100, 30);
				 btnNext2.setBounds(142, 220, 100, 30);
				 btnFinish.setBounds(264, 220, 100, 30);			 
					
			    }
		});
		

		
	}
	
	 
	public void addUser()
		{
			wiz=true;
			monitor=false;			
			monitorFolder=false;
			securityOp = false;
			secureFolder=false;
			JLabel label = new JLabel("Add New User");
			label.setBounds(198, 10, 140, 30);
			frame.getContentPane().add(label);
			label.setFont(new Font(null, Font.BOLD, 12));

			final JLabel label1 = new JLabel("Username:");
			label1.setBounds(120, 55, 80, 14);
			frame.getContentPane().add(label1);

			final JLabel label2 = new JLabel("Password:");
			label2.setBounds(120, 105, 80, 14);
			frame.getContentPane().add(label2);

			btnSubmit = new JButton("Submit");
			btnSubmit.setBounds(100, 157, 89, 23);
			frame.getContentPane().add(btnSubmit);

			btnCancel = new JButton("Cancel");
			btnCancel.setBounds(198, 157, 89, 23);
			frame.getContentPane().add(btnCancel);

			textField1 = new JTextField();
			textField1.setBounds(198, 50, 86, 20);
			frame.getContentPane().add(textField1);
			textField1.setColumns(10);

			passwordField = new JPasswordField();
			passwordField.setBounds(198, 100, 86, 20);
			frame.getContentPane().add(passwordField);

			frame.getRootPane().setDefaultButton(btnSubmit);
			
			final JLabel warning = new JLabel("First Enter Username");
			warning.setBounds(176, 70, 150, 20);
			warning.setForeground(Color.RED);

			final JLabel warning1 = new JLabel("First Enter Password");
			warning1.setBounds(176, 120, 200, 20);
			warning1.setForeground(Color.RED);
			
			btnSubmit.addActionListener(new ActionListener() {          
			    public void actionPerformed(ActionEvent e) {
			    	
			    	frame.getContentPane().remove(warning);
					frame.getContentPane().remove(warning1);
					if(textField1.getText().trim().equals("")||textField1.getText().trim()==null)
					{
						frame.getContentPane().add(warning);
						frame.revalidate();
						frame.repaint();
					}
					else if(passwordField.getPassword().length==0)
					{
						frame.getContentPane().add(warning1);
						frame.revalidate();
						frame.repaint();
					}
					else
					{

						try {

							originalFile  = new File(path+"username.txt");
							fakeFile  = new File(path+"username.lms");
							fakeFile.renameTo(originalFile);
							
							
							BufferedWriter output = new BufferedWriter(new FileWriter(originalFile,true));
							BufferedReader br = new BufferedReader(new FileReader(originalFile));
							String user = new String();
							
							output.close();
							int flag = 0;

							while((user=br.readLine())!=null)
								if(textField1.getText().equals(user))
								{
									flag = 1;
									break;
								}
							
							br.close();
							originalFile.renameTo(fakeFile);


							if(flag == 0)
							{
								originalFile  = new File(path+"username.txt");
								fakeFile  = new File(path+"username.lms");
								fakeFile.renameTo(originalFile);
								PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(originalFile,true)));
								out.println(toSHA1(textField1.getText().getBytes("UTF-8")));								
								out.close();
								originalFile.renameTo(fakeFile);

								
								char[] pass = passwordField.getPassword();
								String pass1 = new String(pass);
								
								
								originalFile  = new File(path+"password.txt");
								fakeFile  = new File(path+"password.lms");
								fakeFile.renameTo(originalFile);
								PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(originalFile,true)));
								out1.println(toSHA1(pass1.getBytes("UTF-8")));								
								out1.close();
								originalFile.renameTo(fakeFile);

								originalFile  = new File(path+"options.txt");
								fakeFile  = new File(path+"options.lms");
								fakeFile.renameTo(originalFile);
								PrintWriter out3 = new PrintWriter(new BufferedWriter(new FileWriter(originalFile, true)));
								out3.println(encryption("-1"));								
								out3.close();	
								originalFile.renameTo(fakeFile);
								
								mntmAddDevice.setEnabled(true);
								mntmChangePass.setEnabled(true);
								mntmSecuredFolder.setEnabled(true);
								mntmChangeSecuredFolder.setEnabled(true);
								mntmSecurityOptions.setEnabled(true);
								
								currentUser = textField1.getText();
								
								System.out.println("File Written Succesfully!");
								frame.getContentPane().removeAll();
								frame.revalidate();
								frame.repaint();
								wizard();
								
							}
							else
							{
								warning.setText("User Already Exist");							 
								frame.getContentPane().add(warning);
								frame.revalidate();
								frame.repaint();
							}


						} catch (Exception ee) {
							System.out.println(ee);
						
						}
					}
			    }
			}); 
			
			btnCancel.addActionListener(new ActionListener() {          
			    public void actionPerformed(ActionEvent e) {
			    	
			    	try {
			    		originalFile  = new File(path+"username.txt");
						fakeFile  = new File(path+"username.lms");
						fakeFile.renameTo(originalFile);
						BufferedReader br = new BufferedReader(new FileReader(originalFile));
						if(br.read()==-1)
							System.exit(0);						
						br.close();
						originalFile.renameTo(fakeFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.getContentPane().removeAll();
					if(currentUser.isEmpty())
						first();
					else
						menu();
					frame.revalidate();
					frame.repaint();
			    }
			}); 
			

		}

	public void change_pass()
	{
		JLabel label = new JLabel("Change Password");
		label.setBounds(150, 10, 140, 14);
		frame.getContentPane().add(label);

		final JLabel label3 = new JLabel("Current Password:");
		label3.setBounds(100, 50, 140, 14);
		frame.getContentPane().add(label3);
		
		final JLabel label1 = new JLabel("New Password:");
		label1.setBounds(100, 90, 140, 14);
		frame.getContentPane().add(label1);

		final JLabel label2 = new JLabel("Confirm Password:");
		label2.setBounds(100, 130, 140, 14);
		frame.getContentPane().add(label2);
		
		passwordField3 = new JPasswordField();
		passwordField3.setBounds(220, 50, 86, 20);
		frame.getContentPane().add(passwordField3);

		passwordField1 = new JPasswordField();
		passwordField1.setBounds(220, 90, 86, 20);
		frame.getContentPane().add(passwordField1);

		passwordField2 = new JPasswordField();
		passwordField2.setBounds(220, 130, 86, 20);
		frame.getContentPane().add(passwordField2);
		
		

		final JLabel warning = new JLabel("First Enter Current Password");
		warning.setBounds(220, 70, 150, 20);
		warning.setForeground(Color.RED);
		
		final JLabel warning1 = new JLabel("First Enter Password");
		warning1.setBounds(220, 110, 150, 20);
		warning1.setForeground(Color.RED);

		final JLabel warning2 = new JLabel("Password Not Match");
		warning2.setBounds(220, 150, 150, 20);
		warning2.setForeground(Color.RED);

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(100, 170, 89, 23);
		frame.getContentPane().add(btnSubmit);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(220, 170, 89, 23);
		frame.getContentPane().add(btnCancel);

		frame.getRootPane().setDefaultButton(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().remove(warning);
				frame.getContentPane().remove(warning1);
				frame.getContentPane().remove(warning2);
				
				int flag = 0;
				
				if(passwordField3.getPassword().length==0)
				{
					frame.getContentPane().add(warning);
					frame.revalidate();
					frame.repaint();
				}
				else 
				{
					char[] currentPass = passwordField3.getPassword();
					String currentPass1 = new String(currentPass);
					

					originalFile  = new File(path+"password.txt");
					fakeFile  = new File(path+"password.lms");
					fakeFile.renameTo(originalFile);
					
					try {
						BufferedReader br5 = new BufferedReader(new FileReader(originalFile));

						String pass1 = new String();
						pass1=br5.readLine();
						
						br5.close();
						
						if(pass1.equals(toSHA1(currentPass1.getBytes("UTF-8"))))
							flag = 1;
						else
						{
							warning.setText("Wrong Password Entered");
							frame.getContentPane().add(warning);
							frame.revalidate();
							frame.repaint();
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					originalFile.renameTo(fakeFile);
					
					
				}
				
				if(flag ==1)
				if(passwordField1.getPassword().length==0)
				{
					frame.getContentPane().add(warning1);
					frame.revalidate();
					frame.repaint();
				}
				else if(Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword()))
				{
					try{
						originalFile  = new File(path+"username.txt");
						fakeFile  = new File(path+"username.lms");
						fakeFile.renameTo(originalFile);
						BufferedReader br = new BufferedReader(new FileReader(originalFile));
						String user = new String();

						int count = 0;

						
						
						while((user=br.readLine())!=null)
							if(toSHA1(textField1.getText().getBytes("UTF-8")).equals(user))
								break;
							else
								count++;
						
						br.close();
						originalFile.renameTo(fakeFile);

						try {
							// Open the file that is the first
							// command line parameter
							originalFile  = new File(path+"password.txt");
							fakeFile  = new File(path+"password.lms");
							fakeFile.renameTo(originalFile);
							FileInputStream fstream = new FileInputStream(path+"password.txt");
							// Get the object of DataInputStream
							DataInputStream in = new DataInputStream(fstream);
							BufferedReader br1 = new BufferedReader(new InputStreamReader(in));
							String strLine;
							StringBuilder fileContent = new StringBuilder();
							//Read File Line By Line
							while ((strLine = br1.readLine()) != null) {
								// Print the content on the console
								if (count--==0) {
									String pass = new String(passwordField1.getPassword());
									fileContent.append(toSHA1(pass.getBytes("UTF-8")));
									fileContent.append(System.getProperty("line.separator"));
								}
								else {
									// update content as it is
									fileContent.append(strLine);
									fileContent.append(System.getProperty("line.separator"));
								}
							}
							// Now fileContent will have updated content , which you can override into file
							originalFile  = new File(path+"password.txt");
							fakeFile  = new File(path+"password.lms");
							fakeFile.renameTo(originalFile);
							FileWriter fstreamWrite = new FileWriter(originalFile);
							BufferedWriter out = new BufferedWriter(fstreamWrite);
							out.write(fileContent.toString());
														
							out.close();
							//Close the input stream
							
							in.close();
							originalFile.renameTo(fakeFile);
						} catch (Exception ee) {//Catch exception if any
							System.err.println("Error: " + ee.getMessage());
						}

						System.out.println("File Written Successfully");
					}catch(Exception ee){
						System.out.println("Could not write to a file"+ee);
					}
					frame.getContentPane().removeAll();
					frame.revalidate();
					frame.repaint();
					menu();

				}
				else
				{
					frame.getContentPane().add(warning2);
					frame.revalidate();
					frame.repaint();
				}
		    }
		}); 
		
		btnCancel.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().removeAll();
				menu();
				frame.revalidate();
				frame.repaint();
		    }
		}); 
	}

	public void addDevice()
	{
				
		final JLabel lblAddNewDevice = new JLabel("Add New Device");
		lblAddNewDevice.setBounds(20, 20, 96, 30);
		frame.getContentPane().add(lblAddNewDevice);		
		lblAddNewDevice.setFont(new Font(null, Font.BOLD, 12));
		
		lblwarning = new JLabel();	
		lblwarning.setBounds(20, 260, 400, 25);
		frame.getContentPane().add(lblwarning);
		lblwarning.setFont(new Font("Serif", Font.BOLD, 12));
		
		scrollpane = new JScrollPane();
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setBounds(20, 50, 400, 150);
		frame.getContentPane().add(scrollpane);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(20, 220, 100, 30);
		frame.getContentPane().add(btnSearch);
		
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallSearch.png"));
		

		panel = new JPanel();
		//panel.setBounds(100,60,50,100);
		scrollpane.setViewportView(panel);
		panel.setLayout(null);
		panel.setLayout(new GridLayout(0,1));

		btnDevice = new JButton("Add Device");
		btnDevice.setBounds(140,220,120,30);
		frame.getContentPane().add(btnDevice);
		btnDevice.setEnabled(false);

		
		btnDevice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDevice.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallAddDevice.png"));

		/*btnDone3 = new JButton("Done");
		btnDone3.setBounds(270,220,90,30);
		frame.getContentPane().add(btnDone3);
		
		btnDone3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDone3.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallDone.png"));*/
		
		btnDeviceCancel = new JButton("Cancel");
		btnDeviceCancel.setBounds(270,220,100,30);
		frame.getContentPane().add(btnDeviceCancel);
		
		btnDeviceCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeviceCancel.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallCancel.png"));
		
		originalFile  = new File(path+"deviceCode.txt");
		fakeFile  = new File(path+"deviceCode.lms");
		fakeFile.renameTo(originalFile);
		boolean fl= false;
		try {
			if (originalFile.exists()){
		BufferedReader input = new BufferedReader(new FileReader(originalFile));		
			
		String str;
		
		
			if((str=input.readLine())!=null)
			{

					deviceParts=str.split(">>");			
				
					
					lblwarning.setText("Registered device : " + decryption(deviceParts[1]));
					fl=true;
				
			}
			
			input.close();
			}
			if(!fl)
			{
				lblwarning.setText("Click on Search Button to start search");
				
			}
			
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} 
		originalFile.renameTo(fakeFile);
				
	/*	btnSearch.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
				lblsearch = new JLabel("Searching . . .");
				lblsearch.setBounds(20, 60, 150, 23);
				lblwarning.setText("Select device and click on Add Device Button");
				panel.add(lblsearch);				
				panel.removeAll();
				frame.revalidate();
				frame.repaint();				
				asd();
		    }
		});*/
		btnSearch.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e)
			{
			}
			public void mouseReleased(MouseEvent e)
			{
				if(asd())
				{
					panel.setLayout(new GridLayout(0,1));
					lblwarning.setText("Select device and click on Add Device Button");
				}
				else
				{
					panel.setLayout(new GridLayout(0,1));
					lblwarning.setText("No device found");
				}
				lblwarning.setForeground(Color.BLACK);
			}
			public void mousePressed(MouseEvent e)
			{

				try
				{
					if(systemBluetooth.check())
					{
						panel.removeAll();
						frame.revalidate();
						frame.repaint();
						panel.setLayout(null);
						lblsearch.setText("Searching . . .");
						lblsearch.setFont(new Font("Serif", Font.PLAIN, 14));
						lblsearch.setBounds(15, 5, 95, 30);
						lblwarning.setText("Please wait, searching may take few seconds");
						lblwarning.setForeground(Color.RED);				
						panel.add(lblsearch);	
					}
					else
					{
						lblwarning.setText("Bluetooth device not found on your system.");
						lblwarning.setForeground(Color.RED);	
						System.out.println("22Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.");
						JOptionPane.showMessageDialog(frame,"Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.",
								"Info", JOptionPane.INFORMATION_MESSAGE);	
					}
				}
				catch(Exception e8)
				{
					lblwarning.setText("Bluetooth device not found on your system.");
					lblwarning.setForeground(Color.RED);	
					System.out.println("12Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.");
					JOptionPane.showMessageDialog(frame,"Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.",
							"Info", JOptionPane.INFORMATION_MESSAGE);	
				}			
				
			}
			public void mouseEntered(MouseEvent e)
			{
				
			}
			public void mouseExited(MouseEvent e)
			{
				
			}
		});
		
		
		
		

		deviceadd=false;
		
		btnDevice.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	for (Enumeration<AbstractButton> buttons = bgDevice.getElements(); buttons.hasMoreElements();) 
				 {
			            AbstractButton button = buttons.nextElement();

			            if (button.isSelected()) 
			            {
			            	address = button.getName();			            	
			            	deviceName = button.getText();
			            	frame.revalidate();
			            	frame.repaint();			            	
			            	break;
			            }
				 }
	
				try {
					lblwarning.setText("");
					address();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.getContentPane().removeAll();;

				menu();
				frame.revalidate();
				frame.repaint();	
				if(duplicateDevice)
					JOptionPane.showMessageDialog(frame, "Device already registered : "+deviceName,
							"Info", JOptionPane.INFORMATION_MESSAGE);
				else if(deviceadd)
					JOptionPane.showMessageDialog(frame, "Device registered : "+deviceName,
							"Info", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(frame, "No device added.",
							"Info", JOptionPane.INFORMATION_MESSAGE);



				
		    }
		}); 
		
		
		btnDeviceCancel.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().removeAll();
				menu();
				frame.revalidate();
				frame.repaint();			
		    }
		}); 
		
		
		/*btnDone3.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    					
		    }
		}); */
		
		

		/*btnSearch.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e)
			{

				 
			}
			public void mouseReleased(MouseEvent e)
			{
				asd();
											
							}
			public void mousePressed(MouseEvent e)
			{

				frame.getContentPane().removeAll();
				frame.getContentPane().add(lblAddNewDevice);
				frame.getContentPane().add(btnSearch);
				frame.getContentPane().add(btnDevice);
				frame.getContentPane().add(btnDone3);
				
				
				lblsearch = new JLabel("Searching . . .");
				lblsearch.setBounds(20, 60, 150, 23);
				frame.getContentPane().add(lblsearch);
				lblwarning.setText("");
				frame.revalidate();
				frame.repaint();
			}
			public void mouseEntered(MouseEvent e)
			{

			}
			public void mouseExited(MouseEvent e)
			{

			}
		});
*/
	}
	
	public static JRadioButton getSelectedRadio(ButtonGroup group) {
		for (Enumeration eRadio=group.getElements(); eRadio.hasMoreElements(); ) {
			//Iterating over the Radio Buttons
			JRadioButton radioButton = (JRadioButton)eRadio.nextElement();
			//Comparing radioButtons model with groups selection
			if (radioButton.getModel() == group.getSelection()) {
				return radioButton;
			}
		}
		return null;
	}

	
	private void contactUs()
	{
		JLabel contact = new JLabel("Contact Us");
		contact.setBounds(20, 10, 200, 23);
		contact.setFont(new Font("Serif", Font.BOLD, 14));
		frame.getContentPane().add(contact);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(30, 46, 180, 23);
		frame.getContentPane().add(btnClose);
		
		btnClose.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	frame.getContentPane().removeAll();		
		    	if(currentUser.isEmpty()) {
		    		try {

		    			originalFile  = new File(path+"username.txt");
						fakeFile  = new File(path+"username.lms");
						fakeFile.renameTo(originalFile);
		    			File file = new File(path+"username.txt");
		    			if (!file.isFile() && !file.createNewFile()){
		    				throw new IOException("Error creating new file: " + file.getAbsolutePath());
		    			}

		    			originalFile  = new File(path+"username.txt");
						fakeFile  = new File(path+"username.lms");
						fakeFile.renameTo(originalFile);
		    			BufferedReader br = new BufferedReader(new FileReader(originalFile));
		    			if(br.read()==-1)
		    				addUser();
		    			else
		    				first();
		    			
		    			br.close();
		    			originalFile.renameTo(fakeFile);
		    		} catch (FileNotFoundException e2) {
		    			// TODO Auto-generated catch block
		    			System.out.println(e2);
		    		} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
				else
				menu();
				frame.revalidate();
				frame.repaint();
		    }
		}); 
	}
	
	private void about()
	{
		JLabel aboutUs = new JLabel("Help Topics");
		aboutUs.setBounds(20, 10, 200, 23);
		aboutUs.setFont(new Font("Serif", Font.BOLD, 14));
		frame.getContentPane().add(aboutUs);

		btnClose = new JButton("Close");
		btnClose.setBounds(30, 46, 180, 23);
		frame.getContentPane().add(btnClose);
		
		btnClose.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	frame.getContentPane().removeAll();		
		    	if(currentUser.isEmpty())
		    		try {
		    				    			
		    			originalFile  = new File(path+"username.txt");
						fakeFile  = new File(path+"username.lms");
						fakeFile.renameTo(originalFile);
		    			File file = new File(path+"username.txt");
		    			if (!file.isFile() && !file.createNewFile()){
		    				throw new IOException("Error creating new file: " + file.getAbsolutePath());
		    			}

		    			originalFile  = new File(path+"username.txt");
						fakeFile  = new File(path+"username.lms");
						fakeFile.renameTo(originalFile);
		    			BufferedReader br = new BufferedReader(new FileReader(originalFile));
		    			if(br.read()==-1)
		    				addUser();
		    			else
		    				first();
		    			
		    			br.close();
		    			originalFile.renameTo(fakeFile);
		    		} catch (FileNotFoundException e2) {
		    			// TODO Auto-generated catch block
		    			System.out.println(e2);
		    		} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				else
				menu();
				frame.revalidate();
				frame.repaint();
		    }
		}); 
	}
	
	public void securityOptions() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		JLabel securityOptions = new JLabel("Select security options");
		securityOptions.setBounds(20, 10, 200, 23);
		//securityOptions.setSize(150,150);
		securityOptions.setFont(new Font("Serif", Font.BOLD, 14));
		frame.getContentPane().add(securityOptions);


		o1 = new JCheckBox("Secure Files");
		o1.setBackground(WHITE);
		o1.setBounds(20, 40, 200, 23);
		frame.getContentPane().add(o1);

		o2 = new JCheckBox("Secure whole system");
		o2.setBackground(WHITE);
		o2.setBounds(20,60,200,23);
		frame.getContentPane().add(o2);



		bg = new ButtonGroup();		

		r1 = new JRadioButton("Shutdown");
		bg.add(r1);
		r1.setBackground(WHITE);
		r1.setEnabled(false);
		r1.setBounds(40, 80, 109, 23);
		frame.getContentPane().add(r1);

		r2 = new JRadioButton("Restart");
		bg.add(r2);
		r2.setBackground(WHITE);
		r2.setEnabled(false);
		r2.setBounds(40, 100, 109, 23);
		frame.getContentPane().add(r2);

		r3 = new JRadioButton("LogOff");
		bg.add(r3);
		r3.setBackground(WHITE);
		r3.setEnabled(false);
		r3.setBounds(40, 120, 109, 23);
		frame.getContentPane().add(r3);


		r4 = new JRadioButton("Lock");
		bg.add(r4);
		r4.setBackground(WHITE);
		r4.setEnabled(false);
		r4.setBounds(40, 140, 109, 23);
		frame.getContentPane().add(r4);


		btnDone = new JButton("Done");
		btnDone.setBounds(210, 180, 90, 30);
		frame.getContentPane().add(btnDone);
		
		btnDone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDone.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallDone.png"));
		
		btnSecurityCancel = new JButton("Cancel");
		btnSecurityCancel.setBounds(320, 180, 90, 30);
		frame.getContentPane().add(btnSecurityCancel);
		btnSecurityCancel.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallCancel.png"));

		btnAddFiles = new JButton("Add files to secure");
		btnAddFiles.setBounds(20,180,170,30);
		frame.getContentPane().add(btnAddFiles);
		btnAddFiles.setEnabled(false);

		
		btnAddFiles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddFiles.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallAddFiles.png"));


		originalFile  = new File(path+"options.txt");
		fakeFile  = new File(path+"options.lms");
		fakeFile.renameTo(originalFile);
		BufferedReader br2 = new BufferedReader(new FileReader(originalFile));
		char c = decryption(br2.readLine()).charAt(0);
		br2.close();

		check= false;
		
		if(c=='0'||c=='5'||c=='6'||c=='7'||c=='8')
		{
			o1.setSelected(true);
			btnAddFiles.setEnabled(true);
		}
		if(c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8')
		{
			o2.setSelected(true);
			check=true;
			
				r1.setEnabled(true);				
				r2.setEnabled(true);
				r3.setEnabled(true);
				r4.setEnabled(true);
				switch(c)
				{
					case '1' : 
					case '5' : r1.setSelected(true);
								break;
					case '2' :
		    		case '6' : r2.setSelected(true);
								break;
								
		    		case '3' : 
					case '7' : r3.setSelected(true);
								break;
					case '4' :
		    		case '8' : r4.setSelected(true);
								break;
				}
		}
		
		originalFile.renameTo(fakeFile);
		

		o1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				JCheckBox o1 = (JCheckBox)ie.getItem();

				if(o1.isSelected())
				{
					btnAddFiles.setEnabled(true);
				}
				else
					btnAddFiles.setEnabled(false);

			}
		});

		o2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {

				JCheckBox o2 = (JCheckBox)ie.getItem();

				if(o2.isSelected())
				{
					r1.setSelected(true);
					check=true;
				}




				if(o2.isSelected())
				{
					r1.setEnabled(true);				
					r2.setEnabled(true);
					r3.setEnabled(true);
					r4.setEnabled(true);
					r1.setSelected(true);

				}
				else
				{
					r1.setEnabled(false);
					r2.setEnabled(false);
					r3.setEnabled(false);	
					r4.setEnabled(false);

				}
			}
		});


		btnAddFiles.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	try{
					int sec=0;
					if(o1.isSelected())
					{
						if(o2.isSelected())
						{
							if(r1.isSelected())
								sec = 5;
							else if(r2.isSelected())
								sec = 6;
							else if(r3.isSelected())
								sec = 7;
							else if(r4.isSelected())
								sec = 8;

						}
						else
							sec = 0;
					}
					if(!(o1.isSelected()))
					{
						if(o2.isSelected())
						{

							if(r1.isSelected())
								sec = 1;
							else if(r2.isSelected())
								sec = 2;
							else if(r3.isSelected())
								sec = 3;
							else if(r4.isSelected())
								sec = 4;
						}
						else
							sec = -1;
					}
					
					originalFile  = new File(path+"options.txt");
					fakeFile  = new File(path+"options.lms");
					fakeFile.renameTo(originalFile);
					BufferedWriter output = new BufferedWriter(new FileWriter(originalFile));
					
					char c = (char) (sec + 48);
					String s = ""+c;
					output.write(encryption(s));
					output.flush();
					System.out.print(sec);					
					output.close();
					originalFile.renameTo(fakeFile);

				}catch(Exception ee){
					System.out.println("Could not write to a file"+ee);
				}

				//frame.getContentPane().removeAll();					
				//next();
				
				 try {
					 originalFile  = new File(path+"folder.txt");
						fakeFile  = new File(path+"folder.lms");
						fakeFile.renameTo(originalFile);
						BufferedReader br5 = new BufferedReader(new FileReader(originalFile));
						String securedFolder;
						
						
						
						boolean flag=false;
						if((securedFolder=br5.readLine())!=null)
						{
							securedFolder = decryption(securedFolder);
							File folderExisting = new File(securedFolder);
							if(folderExisting.exists())
							{
							Runtime.getRuntime().exec("explorer.exe "+ securedFolder);
							System.out.println(securedFolder);
							flag=true;
							
							}
						}
							else
							{
						    	frame.getContentPane().removeAll();
								JOptionPane.showMessageDialog(frame,"First select a folder",
										"Info", JOptionPane.INFORMATION_MESSAGE);	
								securityOp=true;								
								frame.revalidate();
								frame.repaint();
								flag=false;							
															
							
						}
						br5.close();
						originalFile.renameTo(fakeFile);
						
						if((flag)&&(!wiz))
						{
							frame.getContentPane().removeAll();
							frame.revalidate();
							frame.repaint();
							menu();
							
						}
						
						
						else if((!flag)&&(wiz)){
							frame.getContentPane().removeAll();
							frame.revalidate();
							frame.repaint();							
							selectFolder();
						}
				 }
				 catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 		
		    }
		}); 
		
		
		btnDone.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	try{
					int sec=0;
					if(o1.isSelected())
					{
						if(o2.isSelected())
						{
							if(r1.isSelected())
								sec = 5;
							else if(r2.isSelected())
								sec = 6;
							else if(r3.isSelected())
								sec = 7;
							else if(r4.isSelected())
								sec = 8;

						}
						else
							sec = 0;
					}
					if(!(o1.isSelected()))
					{
						if(o2.isSelected())
						{

							if(r1.isSelected())
								sec = 1;
							else if(r2.isSelected())
								sec = 2;
							else if(r3.isSelected())
								sec = 3;
							else if(r4.isSelected())
								sec = 4;
						}
						else
							sec = -1;
					}

					originalFile  = new File(path+"options.txt");
					fakeFile  = new File(path+"options.lms");
					fakeFile.renameTo(originalFile);
					BufferedWriter output = new BufferedWriter(new FileWriter(originalFile));
					
					char c = (char) (sec + 48);
					String s = ""+c;
					output.write(encryption(s));
					output.flush();
					System.out.print(sec);					
					output.close();
					originalFile.renameTo(fakeFile);

				}catch(Exception ee){
					System.out.println("Could not write to a file"+ee);
				}

				frame.getContentPane().removeAll();
				if(wiz)
					second();
				else
				menu();
				frame.revalidate();
				frame.repaint();

				JOptionPane.showMessageDialog(frame,"Selected security options will come into affect\n when the bluetooth device is out of range.",
						"Info", JOptionPane.INFORMATION_MESSAGE);		
		    }
		}); 
		
		btnSecurityCancel.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().removeAll();
				menu();
				frame.revalidate();
				frame.repaint();			
		    }
		});

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	
	private String encrypt_ext(String s)
	{
		return s;
		
	}
	
	private String decrypt_ext(String s)
	{
		return s;
		
	}
	
	private void initialize() throws IOException {
		 
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

	/*	try{
		   // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			Properties props = new Properties();
			  props.put("logoString", "sby");
			  GraphiteLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		}
		catch(Exception e){
		        System.out.println("UIManager Exception : "+e);
		} */
	/*	try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		} */
		
		monitor=false;
		counter=0;
		
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		new File(System.getProperty("user.dir")+"\\bluetooth").mkdirs();
		
		String spid="";
		boolean flag=false;
		originalFile  = new File(path+"pid.txt");
		fakeFile  = new File(path+"pid.lms");
		fakeFile.renameTo(originalFile);
		File file = new File(path+"pid.txt");
		try{
		if (file.exists()){
			
				try {
					originalFile  = new File(path+"pid.txt");
					fakeFile  = new File(path+"pid.lms");
					fakeFile.renameTo(originalFile);
					BufferedReader br = new BufferedReader(new FileReader(originalFile));
					if((spid=br.readLine())!=null)
						{
							flag=true;
						}
					br.close();
					originalFile.renameTo(fakeFile);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		}catch(Exception e2){
			System.out.println("Exception : "+ e2);
		}
		boolean remove=true;
		if(flag)
		{
		
			Process p = Runtime.getRuntime().exec("tasklist");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				

				if (line.contains("javaw.exe")) {					
					if(line.contains(spid))
					{

						System.out.println("running");
						remove=false;
						break;
					}
				}

			}
			
			if(remove){
				originalFile  = new File(path+"pid.txt");
				fakeFile  = new File(path+"pid.lms");
				fakeFile.renameTo(originalFile);
				BufferedWriter output = new BufferedWriter(new FileWriter(originalFile));				
				output.close();
				originalFile.renameTo(fakeFile);
				System.out.println("Removed");
			}
			
		}

		
		
		
		
		
		originalFile  = new File(path+"folder.txt");
		fakeFile  = new File(path+"folder.lms");
		fakeFile.renameTo(originalFile);
		BufferedWriter output = new BufferedWriter(new FileWriter(originalFile,true));		
		output.close();
		originalFile.renameTo(fakeFile);
		
	/*	
		originalFile  = new File(path+"folder.txt");
		fakeFile  = new File(path+"folder.lms");
		fakeFile.renameTo(originalFile);
		BufferedReader br5 = new BufferedReader(new FileReader(originalFile));
		String securedFolder;
		try{
			securedFolder=decryption(br5.readLine());

				try{
				File folderExisting = new File(securedFolder);
				if((folderExisting.exists())&&(!folderExisting.isHidden()))
					Runtime.getRuntime().exec("attrib +H +S " +folderExisting);
				}catch(Exception e)
				{
					System.out.println("Exception : "+e);
				}
		}
		catch(Exception ee)
		{
			System.out.println("Exception : "+ee);
		}		
		br5.close();
		originalFile.renameTo(fakeFile);*/
		

		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		frame = new JFrame();		
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds((int)(width/2)-450/2, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Secure via Bluetooth");
		/*
		try {
    		frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir")+"\\images\\bg.jpg")))));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	frame.pack();
    	frame.setVisible(true);*/
		frame.setResizable(false);
		
		
		
	/*	WindowListener exitListener = new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
        	try {
				BufferedReader br = new BufferedReader(new FileReader("c:/bluetooth/pid.txt"));
				String tokill,cmd="";
				if((tokill=br.readLine())!=null)

					cmd = "taskkill /F /PID " + tokill;
				Runtime.getRuntime().exec(cmd);


			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

        }
    };
    frame.addWindowListener(exitListener);*/
		
		
		frame.setIconImage(new ImageIcon(System.getProperty("user.dir")+"\\images\\icon.png").getImage());
		/*
		    frame.setVisible(true);
		    frame.setState(JFrame.NORMAL);
		    try {
		        Thread.sleep(100);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		*/
		
		
		
		
/*		WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
            	try {
					BufferedReader br = new BufferedReader(new FileReader("c:/bluetooth/pid.txt"));
					String tokill,cmd="";
					if((tokill=br.readLine())!=null)

						cmd = "taskkill /F /PID " + tokill;
					Runtime.getRuntime().exec(cmd);


				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

            }
        };
        frame.addWindowListener(exitListener);
*/
		frame.setJMenuBar(menuBar);

		menuBar.add(mnFile);		
		mnFile.add(mntmAddDevice);
		mntmAddDevice.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\fileAddDevice.png"));		
		mnFile.add(mntmExit);
		mntmExit.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\fileExit.png"));		
		menuBar.add(mnOptions);
		mnOptions.add(mntmChangePass);
		mntmChangePass.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\fileChangePassword.png"));
		mnOptions.add(mntmSecuredFolder);
		mntmSecuredFolder.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\fileViewSecuredFiles.png"));
		mnOptions.add(mntmChangeSecuredFolder);
		mntmChangeSecuredFolder.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\fileSecuredFolder.png"));
		mnOptions.add(mntmSecurityOptions);
		mntmSecurityOptions.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\fileSecurityOptions.png"));
		menuBar.add(mnHelp);
		mnHelp.add(mntmContactUs);
		mntmContactUs.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\fileContactUs.png"));
		mnHelp.add(mntmAbout);
		mntmAbout.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\fileHelp.png"));
		frame.getContentPane().setLayout(null);
		
		if(currentUser.isEmpty())
		{
			mntmAddDevice.setEnabled(false);
			mntmChangePass.setEnabled(false);
			mntmSecuredFolder.setEnabled(false);
			mntmChangeSecuredFolder.setEnabled(false);
			mntmSecurityOptions.setEnabled(false);
		}
		
		
		mntmContactUs.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	frame.getContentPane().removeAll();
				contactUs();
				frame.revalidate();
				frame.repaint();
		    }
		}); 
		
		mntmAbout.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	frame.getContentPane().removeAll();
				about();
				frame.revalidate();
				frame.repaint();
		    }
		}); 
		
		mntmChangePass.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	frame.getContentPane().removeAll();
				change_pass();
				frame.revalidate();
				frame.repaint();
		    }
		}); 
		
		mntmSecuredFolder.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				 
				 try {
					 originalFile  = new File(path+"folder.txt");
						fakeFile  = new File(path+"folder.lms");
						fakeFile.renameTo(originalFile);
					BufferedReader br5 = new BufferedReader(new FileReader(originalFile));
					String securedFolder,s;
					
					
					
					
					if((s=(br5.readLine()))!=null)
					{
						securedFolder = decryption(s);
						File folderExisting = new File(securedFolder);
						if(folderExisting.exists())
						{
						Runtime.getRuntime().exec("explorer.exe "+ securedFolder);
						System.out.println(securedFolder);
						}
					}
						else
						{
							JOptionPane.showMessageDialog(frame,"First select a folder",
									"Info", JOptionPane.INFORMATION_MESSAGE);
							frame.getContentPane().removeAll();
					    	frame.revalidate();
							frame.repaint();
													
							selectFolder();
														
						
					}
					
					br5.close();
					originalFile.renameTo(fakeFile);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				 
				
					
			    }
		});
		
		mntmChangeSecuredFolder.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {

		    	try {
		    		originalFile  = new File(path+"folder.txt");
					fakeFile  = new File(path+"folder.lms");
					fakeFile.renameTo(originalFile);
					BufferedReader br5 = new BufferedReader(new FileReader(originalFile));
					String securedFolder;
					
					if((securedFolder=decryption(br5.readLine()))!=null)
					{
						
						File folderExisting = new File(securedFolder);
						if(folderExisting.exists())
						{
						current_folder.setText(""+securedFolder);
						}
						else
							current_folder.setText("Currently no folder is selected");
							
					}else
						current_folder.setText("Currently no folder is selected");
					
					
					br5.close();
					originalFile.renameTo(fakeFile);
						
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		    	frame.getContentPane().removeAll();
				selectFolder();
				frame.revalidate();
				frame.repaint();
		    	
		    }
		}); 
		
		mntmAddDevice.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
				frame.getContentPane().removeAll();
				addDevice();				
				frame.revalidate();
				frame.repaint();
		    }
		}); 
		
		mntmSecurityOptions.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().removeAll();
				try {
					securityOptions();
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.revalidate();
				frame.repaint();
		    }
		}); 
		
		mntmExit.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {

				System.exit(0);
		   }
		}); 

		try {

			originalFile  = new File(path+"username.txt");
			fakeFile  = new File(path+"username.lms");
			fakeFile.renameTo(originalFile);
			File file1 = new File(path+"username.txt");
			if (!file1.isFile() && !file1.createNewFile()){
				throw new IOException("Error creating new file: " + file1.getAbsolutePath());
			}

	
			BufferedReader br = new BufferedReader(new FileReader(path+"username.txt"));
			if(br.read()==-1)
				addUser();
			else
				first();
			
			br.close();
			originalFile.renameTo(fakeFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	
	}

	
	private void second()
	{
		

		btnMenu = new JButton("Menu");
		btnMenu.setBounds(165,60,100,30);
		frame.getContentPane().add(btnMenu);
		
		btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMenu.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallMenu.png"));
		
		btnMonitor = new JButton("Turn On Security Mode");
		btnMonitor.setBounds(140,100,150,30);
		frame.getContentPane().add(btnMonitor);
		 
		btnMonitor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		counter = counter +1;
		if(counter==0)
		{
		btnMonitor.setText("Turn On Security Mode");
		//btnMonitor.setForeground(Color.GRAY);
		lblState.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\color_label_circle_grey_T_16x16.png"));lblState.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\color_label_circle_grey_T_16x16.png"));
		lblMonitor.setText("Security Mode is Currently Off");
		}
		else if(!monitor)
		{
			btnMonitor.setText("Turn On Security Mode");
			//btnMonitor.setForeground(Color.GRAY);
			lblState.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\color_label_circle_grey_T_16x16.png"));
			lblMonitor.setText("Security Mode is Currently Off");
		}
		else if(monitor)
		{
		//	btnMonitor.setForeground(Color.GREEN.darker());
			lblState.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\green_transparent_16x16.png"));
    		btnMonitor.setText("Turn Off Security Mode");
    		lblMonitor.setText("Security Mode is Currently On");
		}
		
			
		
		lblState.setBounds(120,100,23,23);
		frame.getContentPane().add(lblState);
		
		
		lblMonitor.setBounds(130,140,300,23);
		frame.getContentPane().add(lblMonitor);
		
		lblDevice.setBounds(20,230,300,23);
		frame.getContentPane().add(lblDevice);
		
		JButton btnChangeDevice = new JButton("Change Device");
		btnChangeDevice.setBounds(20,255,140,30);
		frame.getContentPane().add(btnChangeDevice);
		btnChangeDevice.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\smallChangeDevice.png"));
		
		originalFile  = new File(path+"deviceCode.txt");
		fakeFile  = new File(path+"deviceCode.lms");
		fakeFile.renameTo(originalFile);
		boolean fl= false;
		try {
			if (originalFile.exists()){
		BufferedReader input = new BufferedReader(new FileReader(originalFile));		
			
		String str;
		
		
			if((str=input.readLine())!=null)
			{

					deviceParts=str.split(">>");			
				
					
					lblDevice.setText("Registered device : " + decryption(deviceParts[1]));
					fl=true;
				
			}
			
			input.close();
			}
			if(!fl)
			{
				lblDevice.setText("Please add a registered device");
				btnChangeDevice.setText("Add Device");
			}
			
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} 
		originalFile.renameTo(fakeFile);
		
		
		int flag = 0;
		originalFile  = new File(path+"pid.txt");
		fakeFile  = new File(path+"pid.lms");
		fakeFile.renameTo(originalFile);
		
		try{
		if (originalFile.exists()){
			
				try {
					
					BufferedReader br = new BufferedReader(new FileReader(originalFile));
					if(br.read()!=-1)
						flag = 1;
					
					br.close();
					originalFile.renameTo(fakeFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		}catch(Exception e2){
			System.out.println("Exception : "+ e2);
		}

		if(flag == 0)
		{
			lblMonitor.setText("Secure Mode is currently Off");
			monitor=false;
			lblState.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\color_label_circle_grey_T_16x16.png"));
			//btnMonitor.setForeground(Color.GRAY);			
    		btnMonitor.setText("Turn On Security Mode");  		
			
		}
		
		else
		{
			lblMonitor.setText("Security Mode is Currently On");			
			lblState.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\green_transparent_16x16.png"));
			monitor=true;
			//btnMonitor.setForeground(Color.GREEN.darker());			
    		btnMonitor.setText("Turn Off Security Mode");
			//btnTurnMonitorOn.setEnabled(false);
		}
		
		
		btnMenu.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		
			
		    	frame.getContentPane().removeAll();
		    	frame.revalidate();
				frame.repaint();
				
		    	menu();
			
		    	
		
		    }
		   });
		
		btnChangeDevice.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		
			
		    	frame.getContentPane().removeAll();
		    	frame.revalidate();
				frame.repaint();				
		    	addDevice();
			
		    	
		
		    }
		   });
				
		btnMonitor.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		
		    	try
		    	{
		    		if(systemBluetooth.check())
		    		{
				    	if(monitor==false)
				    	{
				    		
				    		originalFile  = new File(path+"deviceCode.txt");
							fakeFile  = new File(path+"deviceCode.lms");
							fakeFile.renameTo(originalFile);
				    		
							boolean flag = false;
							boolean flag1 = true;
							boolean flag2 = true;
							
							if(originalFile.exists())
							{						
								try {
									
									BufferedReader br = new BufferedReader(new FileReader(originalFile));
									if(br.read()!=-1)
										flag = true;
									else
										flag = false;
									br.close();
									originalFile.renameTo(fakeFile);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							
							
							if(!flag)
							{
								frame.getContentPane().removeAll();
								JOptionPane.showMessageDialog(frame,"First add a Bluetooth Device",
										"Info", JOptionPane.INFORMATION_MESSAGE);
								System.out.println("not working");
								addDevice();
								frame.revalidate();
								frame.repaint();
							}
							else
							{
								try{
									originalFile  = new File(path+"options.txt");
									
									fakeFile  = new File(path+"options.lms");
									fakeFile.renameTo(originalFile);
									BufferedReader br7 = new BufferedReader(new FileReader(originalFile));
									char c=decryption(br7.readLine()).charAt(0);
									
									if(c=='-'||c == '/')
									{
										flag2 = false;
									}

									br7.close();
								}
								catch(Exception e8)
								{
									
								}
								
							}
								
								
							if(!flag2)
							{
								frame.getContentPane().removeAll();
								JOptionPane.showMessageDialog(frame,"First select a security option",
										"Info", JOptionPane.INFORMATION_MESSAGE);
								System.out.println("not working");
								securityOptions();
								frame.revalidate();
								frame.repaint();
							}
							else
							{
								try{
									originalFile  = new File(path+"options.txt");
									
									fakeFile  = new File(path+"options.lms");
									fakeFile.renameTo(originalFile);
									BufferedReader br7 = new BufferedReader(new FileReader(originalFile));
									char c=decryption(br7.readLine()).charAt(0);
									
									if(c=='0'||c=='5'||c=='6'||c=='7'||c=='8')
									{
										
										originalFile1  = new File(path+"folder.txt");
										fakeFile1  = new File(path+"folder.lms");
										fakeFile1.renameTo(originalFile1);
										BufferedReader br5 = new BufferedReader(new FileReader(originalFile1));
										String securedFolder;
										
										
										if((securedFolder=br5.readLine())!=null)
										{
											securedFolder = decryption(securedFolder);
											File folderExisting = new File(securedFolder);
											if(folderExisting.exists())
												flag1=true;
										}
											else
											{
										    	frame.getContentPane().removeAll();									
												
												frame.revalidate();
												frame.repaint();
												flag1=false;
												
										}
										br5.close();
										originalFile1.renameTo(fakeFile1);
										
										
										
								 }
									br7.close();
									originalFile.renameTo(fakeFile);
									if(!flag1)
									{
										JOptionPane.showMessageDialog(frame,"First select a folder as you have selected 'hide files' option",
												"Info", JOptionPane.INFORMATION_MESSAGE);	
										monitorFolder=true;
										frame.getContentPane().removeAll();
										frame.revalidate();
										frame.repaint();
										selectFolder();
									}
								}
								 catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} 		}
							
						
							if(flag && flag1 && flag2)
							{
								monitor=true;
								//btnMonitor.setForeground(Color.GREEN.darker());
								lblState.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\green_transparent_16x16.png"));
					    		btnMonitor.setText("Turn Off Security Mode");
					    		
					    		lblMonitor.setText("Security Mode is Currently On");
					    		
								
								lblSecureMode.setText("Secure Mode On");
								lblSecureMode.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\green_transparent_16x16.png"));
								
								
								//btnMonitor.setEnabled(false);
								//btnTurnMonitorOff.setEnabled(true);
								frame.revalidate();
								frame.repaint();
								try {
									runTime = Runtime.getRuntime() ;     
									
									process = runTime.exec(System.getProperty("user.dir")+"\\blue.exe") ;
									
									
									
									//process=Runtime.getRuntime().exec("C:/bluetooth/secure.exe");
		
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
		
							}
				    	}
		
				    	else
				    	{
				    		
				    		try {
							
				    			monitor=false;
							//	btnMonitor.setForeground(Color.GRAY);
				    			lblState.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\color_label_circle_grey_T_16x16.png"));
					    		btnMonitor.setText("Turn On Security Mode");
					    		
					    		lblMonitor.setText("Security Mode is Currently Off");
					    		
								
								lblSecureMode.setText("Secure Mode Off");
								lblSecureMode.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\images\\color_label_circle_grey_T_16x16.png"));
								
							
				    			
				    			
								originalFile1  = new File(path+"pid.txt");
								fakeFile1  = new File(path+"pid.lms");
								fakeFile1.renameTo(originalFile1);
				    			BufferedReader br = new BufferedReader(new FileReader(originalFile1));
								String tokill,cmd="";
								if((tokill=br.readLine())!=null)
		
									cmd = "taskkill /F /PID " + tokill;
								Runtime.getRuntime().exec(cmd);						
								br.close();
								originalFile1.renameTo(fakeFile1);
								
								originalFile1  = new File(path+"pid.txt");
								fakeFile1  = new File(path+"pid.lms");
								fakeFile1.renameTo(originalFile1);
								BufferedWriter output = new BufferedWriter(new FileWriter(originalFile1));						
								output.close();
								originalFile1.renameTo(fakeFile1);
								//btnTurnMonitorOff.setEnabled(false);
								//btnTurnMonitorOn.setEnabled(true);
								frame.revalidate();
								frame.repaint();
		
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
						
		
				    	}
				    	originalFile.renameTo(fakeFile);
		    		}
		    		else
		    		{
		    			System.out.println("2Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.");
		    			JOptionPane.showMessageDialog(frame,"Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.",
		    					"Info", JOptionPane.INFORMATION_MESSAGE);	
		    		}
		    	}
		    	catch(Exception e8)
		    	{
		    		System.out.println("1Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.");
					JOptionPane.showMessageDialog(frame,"Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.",
							"Info", JOptionPane.INFORMATION_MESSAGE);	
		    	}
		    }
		    
		   });
		
	}
	
	
	private void first()
	{
		monitor=false;
		wiz=false;
		monitorFolder=false;
		securityOp = false;
		secureFolder=false;
		
		final JLabel label = new JLabel("User Login");
		label.setBounds(176, 20, 150, 30);
		frame.getContentPane().add(label);
		label.setFont(new Font(null, Font.BOLD, 12));

		
		final JLabel label1 = new JLabel("Username:");
		label1.setBounds(100, 55, 80, 32);
		frame.getContentPane().add(label1);

		final JLabel label2 = new JLabel("Password:");
		label2.setBounds(100, 110, 80, 23);
		frame.getContentPane().add(label2);

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(176, 157, 86, 23);
		frame.getContentPane().add(btnSubmit);

		textField1 = new JTextField();
		textField1.setBounds(176, 60, 86, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(176, 110, 86, 20);
		frame.getContentPane().add(passwordField);


		final JLabel warning = new JLabel("First Enter Username");
		warning.setBounds(176, 80, 150, 20);
		warning.setForeground(Color.RED);

		final JLabel warning1 = new JLabel("First Enter Password");
		warning1.setBounds(176, 130, 200, 20);
		warning1.setForeground(Color.RED);

		frame.getRootPane().setDefaultButton(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().remove(warning);
				frame.getContentPane().remove(warning1);
				if(textField1.getText().trim().equals("")||textField1.getText().trim()==null)
				{
					frame.getContentPane().add(warning);
					frame.revalidate();
					frame.repaint();
				}
				else if(passwordField.getPassword().length==0)
				{
					frame.getContentPane().add(warning1);
					frame.revalidate();
					frame.repaint();
				}
				else
				{
					originalFile  = new File(path+"username.txt");
					fakeFile  = new File(path+"username.lms");
					fakeFile.renameTo(originalFile);
					
					if(originalFile.exists())
					{
						try{
						
														
							originalFile1  = new File(path+"password.txt");
							fakeFile1  = new File(path+"password.lms");
							fakeFile1.renameTo(originalFile1);
							
							
							BufferedReader br = new BufferedReader(new FileReader(originalFile));
							BufferedReader br1 = new BufferedReader(new FileReader(originalFile1));

							char[] pass = passwordField.getPassword();
							String pass1 = new String(pass);
							String user = new String();

							int count = 0,flag =0;

							while((user=br.readLine())!=null)
								if(toSHA1(textField1.getText().getBytes()).equals(user))
								{
									while(count-->0)
										br1.readLine();

									if(toSHA1(pass1.getBytes()).equals(br1.readLine()))
									{
										frame.getContentPane().removeAll();
										frame.revalidate();
										frame.repaint();
										currentUser = textField1.getText();
										
										mntmAddDevice.setEnabled(true);
										mntmChangePass.setEnabled(true);
										mntmSecuredFolder.setEnabled(true);
										mntmChangeSecuredFolder.setEnabled(true);
										mntmSecurityOptions.setEnabled(true);										
										
										flag = 1;
									}
									break;
								}
								else
									count++;

							br.close();
							br1.close();
							originalFile.renameTo(fakeFile);
							originalFile1.renameTo(fakeFile1);

							if(flag==0)
							{
								warning1.setText("Wrong Username or Password");
								frame.getContentPane().add(warning1);
								frame.revalidate();
								frame.repaint();
							}
							else
							{
								second();
							}
							
							
						
						}catch(Exception ee){
							System.out.println("Could not write to a file"+ee);
						}
					} else
					{
						warning.setText("User doesn't Exists");	
						warning.setBounds(150, 200, 200, 23);
						frame.getContentPane().add(warning);
						frame.revalidate();
						frame.repaint();
					}
					
					
				}
		    }
		}); 
	}
}