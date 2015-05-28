import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import javax.swing.*;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.crypto.*;

import java.security.*;

public class gui extends cryptography
{

	private static final Color WHITE = null;

	JFrame frame;
	String[] parts;
	JMenuBar menuBar = new JMenuBar();
	JMenu mnFile = new JMenu("File");
	JMenuItem mntmRegisterDevice = new JMenuItem("Register Device");
	JMenuItem mntmExit = new JMenuItem("Exit");
	JMenu mnOptions = new JMenu("Options");
	JMenuItem mntmChangePass = new JMenuItem("Change Password");
	JMenuItem mntmSecuredFolder = new JMenuItem("Secured Folder");
	JMenuItem mntmChangeSecuredFolder = new JMenuItem("Change Secured Folder");
	JMenuItem mntmSecurityOptions = new JMenuItem("Security Options");
	JMenu mnHelp = new JMenu("Help");
	JMenuItem mntmAboutUs = new JMenuItem("About Us");
	JMenuItem mntmHelpTopics = new JMenuItem("Help Topics");
	JMenuItem mntmEnterLicense = new JMenuItem("Enter License");
	
	JCheckBox chk,o1,o2,chkFiles;
	JButton btnSearch,btnDevice,btnBrowse,btnSubmit,btnSkip, btnAddFiles,btnAddfile,btnDelete,btnSelectall,btnUnselectall,btnDone,btnDone2,btnDone3,btnCancel,btnAddUser,btnClose;
	JButton btnAddDevice;
	JButton btnNext1 = new JButton();
	JButton btnNext2,btnBack1,btnBack2,btnFinish,btnAbort;
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
	boolean validLicense = false;
	String deviceName;
	Runtime runTime;
	int counter,folderType;
	File file,file1;
	String deviceParts[];
	JFileChooser chooser;
	
	final String DIR = System.getProperty("user.dir");
	final String path = DIR+"\\bluetooth\\";

	JLabel jlab;
	String address = new String();
	private JTextField textField1;
	private JTextField textField2;
	private JTextArea textArea1;
	private JPasswordField passwordField;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JPasswordField passwordField3;
	
	public static void main(String[] args) throws IOException {
		final gui window = new gui();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private gui() throws IOException {
		initialize();
	}	
	
	private void initialize() throws IOException {
		 
		// Set Look And Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			System.out.println("Exception : "+ e1);
		}
		
		monitor=false;
		counter=0;
		
		// Get Working Directory.
		
		if (!(new File(DIR+"\\bluetooth").exists())) {
			new File(DIR+"\\bluetooth").mkdirs();
			System.out.println("Created Bluetooth Directory");
		}
		
		System.out.println("Working Directory = " + DIR);
		
		// Create .lms files if not exist.		
		for(int i = 1081;i<=1087;i++){
			file = new File(path+i+".lms");
			if(!file.exists()){
			BufferedWriter output1 = new BufferedWriter(new FileWriter(file,true));
				//Runtime.getRuntime().exec("attrib +H +S " +file); 
				//Runtime.getRuntime().exec("attrib -H -S " +file);
				output1.close();
			}
		}
		
		//file = new File(path);
		//Runtime.getRuntime().exec("attrib +H +S " +file);
		//Runtime.getRuntime().exec("attrib -H -S " +file);
		
		
		// Check Process is Still Running.
		String spid = "";
		boolean flag = false;
		file  = new File(path+"1087.lms");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			if((spid=br.readLine())!=null){
					flag=true;
			}
			br.close();
			
		} catch (IOException e2) {
			System.out.println("Exception : "+ e2);
		}
		
		boolean remove=true;
		if(flag) {
		
			Process p = Runtime.getRuntime().exec("tasklist");
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {

				if (line.contains("javaw.exe") && line.contains(spid)) {
					System.out.println("Process is still running.");
					remove=false;
					break;
				}
			}
			
			if(remove) {
				file  = new File(path+"1087.lms");
				BufferedWriter output = new BufferedWriter(new FileWriter(file));				
				output.close();
				System.out.println("Process ID is removed");
			}
		}
		
		//Check LicenseFile for YES or NO.
		file = new File(path+"1081.lms");
		BufferedReader br1 = new BufferedReader(new FileReader(file));
		if(toSHA1("YES".getBytes()).equals(br1.readLine())){
			validLicense = true;
		}
			
		br1.close();
		
		
		// Set screen size, background Color, title, icon, and deactivate maximize button.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		frame = new JFrame();		
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds((int)(width/2)-450/2, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("SecureViaBluetooth (Beta Version)");
		frame.setIconImage(new ImageIcon(DIR+"\\images\\icon.png").getImage());
		frame.setResizable(false);
		
		// Set MenuBar with all Menu Options and their icons.
		frame.setJMenuBar(menuBar);
		menuBar.add(mnFile);
		
		mnFile.add(mntmRegisterDevice);
		mntmRegisterDevice.setIcon(new ImageIcon(DIR+"\\images\\fileAddDevice.png"));
		
		mnFile.add(mntmExit);
		mntmExit.setIcon(new ImageIcon(DIR+"\\images\\fileExit.png"));		
		
		menuBar.add(mnOptions);
		mnOptions.add(mntmChangePass);
		
		mntmChangePass.setIcon(new ImageIcon(DIR+"\\images\\fileChangePassword.png"));
		mnOptions.add(mntmSecuredFolder);
		
		mntmSecuredFolder.setIcon(new ImageIcon(DIR+"\\images\\fileViewSecuredFiles.png"));
		mnOptions.add(mntmChangeSecuredFolder);
		
		mntmChangeSecuredFolder.setIcon(new ImageIcon(DIR+"\\images\\fileSecuredFolder.png"));
		mnOptions.add(mntmSecurityOptions);
		
		mntmSecurityOptions.setIcon(new ImageIcon(DIR+"\\images\\fileSecurityOptions.png"));
		menuBar.add(mnHelp);
		
		mnHelp.add(mntmAboutUs);
		mntmAboutUs.setIcon(new ImageIcon(DIR+"\\images\\fileContactUs.png"));
		
		mnHelp.add(mntmHelpTopics);
		mntmHelpTopics.setIcon(new ImageIcon(DIR+"\\images\\fileHelp.png"));
		
		mnHelp.add(mntmEnterLicense);
		mntmEnterLicense.setIcon(new ImageIcon(DIR+"\\images\\fileLicense.png"));
		
		frame.getContentPane().setLayout(null);
		
		// If No Current User is set then deactivate the following options in menu bar.
		if(currentUser.isEmpty()) {
			
			mntmRegisterDevice.setEnabled(false);
			mntmChangePass.setEnabled(false);
			mntmSecuredFolder.setEnabled(false);
			mntmChangeSecuredFolder.setEnabled(false);
			mntmSecurityOptions.setEnabled(false);
		}
		
		// If User registered the Product.
		if(validLicense){
			frame.setTitle("SecureViaBluetooth");
			mntmEnterLicense.setEnabled(false);
		}
		
		// Call addDevice() when user click on Register Device in Menu Options.
		mntmRegisterDevice.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	frame.getContentPane().removeAll();
				addDevice();
				frame.revalidate();
				frame.repaint();
		    }
		}); 
		
		// Exit when User click on Exit in Menu Options.
		mntmExit.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
				System.exit(0);
		   }
		}); 
		
		// Call change_pass() when User click on Change Password in Menu Options.
		mntmChangePass.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	frame.getContentPane().removeAll();
				change_pass();
				frame.revalidate();
				frame.repaint();
		    }
		}); 
		
		
		// First Check secured Folder is set when user click on Secured Folder in Menu Options.
		// If yes, then open the folder.
		// Else, Send User to Set secure folder menu.
		mntmSecuredFolder.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
				 
				 try {
					file  = new File(path+"1082.lms");
					BufferedReader br5 = new BufferedReader(new FileReader(file));
					String securedFolder,s;
					
					
					if((s=(br5.readLine()))!=null) {
						
						securedFolder = decryption(s);
						File folderExisting = new File(securedFolder);
						
						if(folderExisting.exists()) {
							Runtime.getRuntime().exec("explorer.exe "+ securedFolder);
							System.out.println(securedFolder + " is open");
						}
					}
					
					else {
						JOptionPane.showMessageDialog(frame,"First select a folder", "Info", JOptionPane.INFORMATION_MESSAGE);
						frame.getContentPane().removeAll();
				    	frame.revalidate();
						frame.repaint();
												
						selectFolder();	
					}
					
					br5.close();
				} catch (Exception e2) {
					System.out.println("Exception :"+e2);
				}
					
			 }
		});
		
		
		// Call selectFolder() when user Click on Change Secured Folder in Menu Options.
		mntmChangeSecuredFolder.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	frame.getContentPane().removeAll();
		    	frame.revalidate();
				frame.repaint();
		    	selectFolder();
		    }
		}); 
		
		
		// Call securityOptions() when user click on Security Options in Menu Options.
		mntmSecurityOptions.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().removeAll();
				try {
					
					securityOptions();
					
				} catch (InvalidKeyException e1) {
					System.out.println("Exception :"+e1);
				} catch (NoSuchAlgorithmException e1) {
					System.out.println("Exception :"+e1);
				} catch (NoSuchPaddingException e1) {
					System.out.println("Exception :"+e1);
				} catch (IllegalBlockSizeException e1) {
					System.out.println("Exception :"+e1);
				} catch (BadPaddingException e1) {
					System.out.println("Exception :"+e1);
				} catch (IOException e1) {
					System.out.println("Exception :"+e1);
				}
				frame.revalidate();
				frame.repaint();
		    }
		}); 
		
		// Show About us Dialog Box when user click on Contact Us in Menu Options.
		mntmAboutUs.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    /*	Icon icon = new ImageIcon(DIR+"\\images\\icon_32x32.png");  
		    	JOptionPane jp = new JOptionPane("SecureViaBluetooth\n\n WebSite: http://www.secureviabluetooth.bvcoe.info\n Conatct Us: secureviabluetooth@gmail.com",   
		    						JOptionPane.INFORMATION_MESSAGE, JOptionPane.CLOSED_OPTION, icon); 
		    	
		    	JDialog dialog = jp.createDialog(null, "SecureViaBluetooth");  
		    	((Frame)dialog.getParent()).setIconImage(((ImageIcon)icon).getImage());  
		    	dialog.setResizable(true);  
		    	dialog.setLocationRelativeTo(frame);
		    	dialog.setVisible(true);
		    	*/
		    	
		    				    	
		    	JLabel label = new JLabel();
		        Font font = label.getFont();
		        Icon icon = new ImageIcon(DIR+"\\images\\icon_32x32.png");
		        // create some css from the label's font
		        StringBuffer style = new StringBuffer("font-family:" + font.getFamily() + ";");
		        style.append("font-weight:" + (font.isBold() ? "bold" : "normal") + ";");
		        style.append("font-size:" + font.getSize() + "pt;");

		        // html content
		        JEditorPane ep = new JEditorPane("text/html", "<html><body style=\"" + style + "\">" //
		                + "SecureViaBluetooth - Beta Version<br/><br/>"
		                
		        		+"WebSite: <a href=\"http://www.secureviabluetooth.bvcoe.info\">http://www.secureviabluetooth.bvcoe.info</a><br/><br/>"
		                +"Contact us: secureviabluetooth@gmail.com<br/><br/>"  		
		                
		                +"Copyright (c) SecureViaBluetooth. All rights reserved." //
		                + "</body></html>");

		        // handle link events
		        ep.addHyperlinkListener(new HyperlinkListener()
		        {
		            @Override
		            public void hyperlinkUpdate(HyperlinkEvent e)
		            {
		                if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED))
							try {
								Desktop.getDesktop().browse(e.getURL().toURI());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (URISyntaxException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                
		            }
		        });
		        ep.setEditable(false);
		        ep.setBackground(label.getBackground());

		        // show
		        JOptionPane.showMessageDialog(frame, ep,"About SecureViaBluetooth", 1);
		    	
			 }
		    	
		    	
		    	
		}); 

		
		// Call showPDF() which in turn open Help PDF if exist when user click on Help Topics in Menu Options.
		mntmHelpTopics.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
				showPDF("Help");
		    }
		}); 
		
		//Open Dialog Box where user Enters User License with username and email and Product key.
		mntmEnterLicense.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {

		    	frame.getContentPane().removeAll();
		    	enterLicense();
		    	frame.revalidate();
				frame.repaint();
		    }
		}); 
		

		// Checks whether there is already a register user with the Application.
		// If not, call addUser().
		// Else, call first().
		try {

			file  = new File(path+"1085.lms");	
			BufferedReader br = new BufferedReader(new FileReader(file));
			if(br.read()==-1)
				addUser();
			else
				first();
			
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Exception :"+e);
		}
		
	}
	
	private void enterLicense() {
		
		JLabel label = new JLabel("Enter License");
		label.setBounds(138, 10, 140, 30);
		frame.getContentPane().add(label);
		label.setForeground(Color.BLUE);
		label.setFont(new Font(null, Font.BOLD, 14));
		

		final JLabel label1 = new JLabel("Username:");
		label1.setBounds(60, 55, 80, 14);
		frame.getContentPane().add(label1);

		final JLabel label2 = new JLabel("Email:");
		label2.setBounds(60, 105, 80, 14);
		frame.getContentPane().add(label2);
		
		final JLabel label3 = new JLabel("Product Key:");
		label3.setBounds(60, 155, 80, 14);
		frame.getContentPane().add(label3);

		btnSubmit = new JButton("Done");
		btnSubmit.setBounds(138, 255, 89, 30);
		frame.getContentPane().add(btnSubmit);
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setIcon(new ImageIcon(DIR+"\\images\\smallDone.png"));

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(250, 255, 89, 30);
		frame.getContentPane().add(btnCancel);
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setIcon(new ImageIcon(DIR+"\\images\\smallCancel.png"));

		textField1 = new JTextField();
		textField1.setBounds(138, 50, 150, 20);
		frame.getContentPane().add(textField1);
		
		textField2 = new JTextField();
		textField2.setBounds(138, 100, 150, 20);
		frame.getContentPane().add(textField2);
		
		textArea1 = new JTextArea();
		textArea1.setBounds(138, 150, 220, 80);
		frame.getContentPane().add(textArea1);
		textArea1.setFont(new Font("Courier New",Font.PLAIN,12));
		textArea1.setLineWrap(true);                                                                                                                           
		textArea1.setWrapStyleWord(true);
		
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		textArea1.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(2, 2, 2, 2)));

		frame.getRootPane().setDefaultButton(btnSubmit);
		
		final JLabel warning = new JLabel("First Enter Username");
		warning.setBounds(138, 70, 150, 20);
		warning.setForeground(Color.RED);

		final JLabel warning1 = new JLabel("First Enter Email");
		warning1.setBounds(138, 120, 200, 20);
		warning1.setForeground(Color.RED);
		
		final JLabel warning2 = new JLabel("Invalid Product Key");
		warning2.setBounds(138, 230, 200, 20);
		warning2.setForeground(Color.RED);
		
		btnSubmit.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().remove(warning);
				frame.getContentPane().remove(warning1);
				frame.getContentPane().remove(warning2);
				if(textField1.getText().trim().equals("")||textField1.getText().trim()==null) {
					
					frame.getContentPane().add(warning);
					frame.revalidate();
					frame.repaint();
				}
				else if(textField2.getText().trim().equals("")||textField2.getText().trim()==null) {
					
					frame.getContentPane().add(warning1);
					frame.revalidate();
					frame.repaint();
				}
				else if(textArea1.getText().trim().equals("")||textArea1.getText().trim()==null) {
					
					frame.getContentPane().add(warning2);
					frame.revalidate();
					frame.repaint();
				}
				else {
					
					String key = textArea1.getText();
					String username = textField1.getText();
					String email = textField2.getText();
					String salt = "good will hunting";
					
					try {
						String cipherNum1 = key.substring(0, 64);
						String randNum1 = cryptography.decryption(cipherNum1).substring(11);
						
						String messageDigest = key.substring(64);
						String pass1 = username + email + salt + randNum1;
						String cipher1 = cryptography.encryption(pass1);
						if(messageDigest.equals(cryptography.toSHA1(cipher1.getBytes("UTF-8")))){
							
							file  = new File(path+"1081.lms");
							
							PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
							out.println(toSHA1("YES".getBytes("UTF-8")));								
							out.close();
							
							mntmEnterLicense.setEnabled(false);
							frame.setTitle("SecureViaBluetooth");
							validLicense = true;
							
							System.out.println("License Registered");
							JOptionPane.showMessageDialog(frame,"License Enterd Successfully!","Info", JOptionPane.INFORMATION_MESSAGE);
							
							frame.getContentPane().removeAll();
							if(currentUser.isEmpty())
								first();
							else
								menu();
							frame.revalidate();
							frame.repaint();
						}
						else{
							
							frame.getContentPane().add(warning2);
							frame.revalidate();
							frame.repaint();
						}
					} catch(Exception e1) {
						
						frame.getContentPane().add(warning2);
						frame.revalidate();
						frame.repaint();
					}
				}
		    }
		}); 
		
		btnCancel.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
				frame.getContentPane().removeAll();
				try {

					file  = new File(path+"1085.lms");	
					BufferedReader br = new BufferedReader(new FileReader(file));
					if(br.read()==-1)
						addUser();
					else if(currentUser.isEmpty())
						first();
					else
						menu();
					
					br.close();
				} catch (FileNotFoundException e3) {
					System.out.println("Exception :"+e3);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.revalidate();
				frame.repaint();
		    }
		}); 

		
	}

	private boolean asd() {
		
		try {
			// Check System Bluetooth Is Present or Not and Turned On or Not.
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
						"Info", JOptionPane.ERROR_MESSAGE);	
			}
		}
		catch(Exception e)
		{
			System.out.println("1Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.");
			JOptionPane.showMessageDialog(frame,"Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.",
					"Info", JOptionPane.ERROR_MESSAGE);	
		}
		return true;
	}

	private void address() throws IOException {  
		
		duplicateDevice=false;
		try{
			
			
			file  = new File(path+"1086.lms");
			
			
			if (file.exists()){	
			BufferedReader input = new BufferedReader(new FileReader(file));
			
			
				
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
			
			
			if(duplicateDevice==false) {
			
				file  = new File(path+"1086.lms");
	
				BufferedWriter output = new BufferedWriter(new FileWriter(file));
			
	
					System.out.println("shuru se");
					output.write(encryption(address)+">>"+encryption(deviceName));
					System.out.println("File Written Successfully");
					lblwarning.setText("Device added : "+deviceName);				
					deviceadd=true;
	
				
				output.close();		
			}
		}catch(Exception ee1){
			System.out.println("Could not write to a file"+ee1);
		}

	}
	
	private void selectFolder() {
		lblCurrentFolder.setText("Current Secured Folder");
		lblCurrentFolder.setBounds(20, 20, 300, 30);
		frame.getContentPane().add(lblCurrentFolder);	
		

		current_folder = new JTextField();
		current_folder.setEditable(false);
		current_folder.setBounds(20, 45, 400, 30);
		frame.getContentPane().add(current_folder);
		current_folder.setForeground(Color.DARK_GRAY);
		
		
		lblFolder.setText("Select new folder to secure");
		lblFolder.setBounds(20, 80, 300, 30);
		frame.getContentPane().add(lblFolder);
		
		btnCustomFolder = new JButton("Custom Folder");
		btnCustomFolder.setBounds(20, 105, 140, 30);
		frame.getContentPane().add(btnCustomFolder);
		btnCustomFolder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCustomFolder.setIcon(new ImageIcon(DIR+"\\images\\smallCustomFolder.png"));
		
		
		btnDefaultFolder = new JButton("Default Folder");
		btnDefaultFolder.setBounds(280, 105, 140, 30);
		frame.getContentPane().add(btnDefaultFolder);
		btnDefaultFolder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDefaultFolder.setIcon(new ImageIcon(DIR+"\\images\\smallDefaultFolder.png"));
		
		lblSelectedFolder.setText("Selected folder is");
		lblSelectedFolder.setBounds(20, 140, 150, 23);
		frame.getContentPane().add(lblSelectedFolder);
			 
		selected_folder = new JTextField(null);
		selected_folder.setBounds(20,165,400,30);
		frame.getContentPane().add(selected_folder);
				
		btnFolderDone = new JButton("Done");
		btnFolderDone.setBounds(100, 220, 100, 30);
		frame.getContentPane().add(btnFolderDone);
		btnFolderDone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFolderDone.setIcon(new ImageIcon(DIR+"\\images\\smallDone.png"));
		
		btnFolderCancel = new JButton("Cancel");
		btnFolderCancel.setBounds(240, 220, 100, 30);
		frame.getContentPane().add(btnFolderCancel);
		btnFolderCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFolderCancel.setIcon(new ImageIcon(DIR+"\\images\\smallCancel.png"));
		
		
		try {
			file  = new File(path+"1082.lms");
			if(file.exists()){
				BufferedReader br5 = new BufferedReader(new FileReader(file));
				String securedFolder;
			
			
				// Showing selected folder if selected otherwise no folder is selected!
				try{
					if((securedFolder=decryption(br5.readLine()))!=null)
					{
					
						File folderExisting = new File(securedFolder);
						if(folderExisting.exists()) {
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
				
		} catch (Exception e2) {
			System.out.println("Exception :"+e2);
		}
		
	
		
		folderadd= false;
		folderType=-1;
		
		
		if(!validLicense){
			/*Demo Version only start*/	
			final JLabel lblcustom = new JLabel("");
			btnCustomFolder.setEnabled(false);
			
			btnCustomFolder.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e)
				{
				}
				public void mouseReleased(MouseEvent e) {
					
					
					
				}
				public void mousePressed(MouseEvent e) {
	
					
					
				}
				public void mouseEntered(MouseEvent e)
				{
					lblcustom.setText("This feature is available in full version only");
					lblcustom.setBounds(120, 275, 300, 30);
					lblcustom.setForeground(Color.RED);
					
					frame.getContentPane().add(lblcustom);
					
					
				}
				public void mouseExited(MouseEvent e)
				{
					lblcustom.setText("");
				}
			});

			/*Demo Version only ends*/
		}
		else{
		
			/*full version starts */ 

			//btnCustomFolder.setEnabled(true);
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
			/* full version ends*/
		}
		
			btnDefaultFolder.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				folderType=1;
				selected_folder.setText(DIR+"\\securedFolder");
			}
		});
			
			
			btnFolderDone.addActionListener(new ActionListener() { 
				 public void actionPerformed(ActionEvent e) {
					 if(folderType!=-1)
					 {
						 folderadd=true;
						 BufferedWriter output;
							try {
					    		file  = new File(path+"1082.lms");
								output = new BufferedWriter(new FileWriter(file));
							
								 if(folderType==0)
								 {
											output.write(encryption(""+chooser.getSelectedFile()));							
											
								 }
								 else if(folderType==1)
								 {
									 		output.write(encryption(DIR+"\\securedFolder"));		
									 		new File(DIR+"\\securedFolder").mkdirs();	
											
											
								 }
								 output.close();	
						 } catch (Exception e1) {
							 System.out.println("Exception :"+e1);
						 } 
								
							 frame.revalidate();
							 frame.repaint();
						 
					 }
					 
					 frame.getContentPane().removeAll();
					 if((securityOp)||(secureFolder)||(monitorFolder))
					 {
						 		if(selected_folder.getText()!=null)
						 		{
						 		
						 		file  = new File(path+"1082.lms");
								if(file.exists()){
									 try {
										 BufferedReader br5 = new BufferedReader(new FileReader(file));
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
								if(op==0)
									securityOptions();
								else if(op==1)
									menu();
								else
									second();
								}catch (Exception e2) {
									System.out.println("Exception :"+e2);
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
	
	private void menu() {

		wiz=false;
		
		/*file  = new File(path+"1085.lms");
		boolean b=false;
		
		try {
			b = originalFile.renameTo(fakeFile);
		} catch(Exception e3) {
			System.out.println(e3);
		}
		
		if(b)
			System.out.println("Success");
		else
			System.out.println("naah");
		
		*/
		
		
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
		btnAddDevice.setIcon(new ImageIcon(DIR+"\\images\\smallAdd.png"));		
		
		
		btnChangePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChangePassword.setIcon(new ImageIcon(DIR+"\\images\\smallPassword.png"));
	
		
		btnChangeSecuredFolder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChangeSecuredFolder.setIcon(new ImageIcon(DIR+"\\images\\smallClosedLock.png"));
		
		
		btnSecurityOptions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSecurityOptions.setIcon(new ImageIcon(DIR+"\\images\\smallOptions.png"));
		
		 
		btnViewSecuredFiles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewSecuredFiles.setIcon(new ImageIcon(DIR+"\\images\\smallOpenLock.png"));
		
		
		btnDoneMain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDoneMain.setIcon(new ImageIcon(DIR+"\\images\\smallDone.png"));
		
		lblSecureMode.setBounds(150,20,200,30);
		frame.getContentPane().add(lblSecureMode);		
		
		boolean flag = false;
		file  = new File(path+"1087.lms");
		
		try {
			if (file.exists()) {
				
					try {
						BufferedReader br = new BufferedReader(new FileReader(file));
						if(br.read() != -1)
							flag = true;					
						br.close();
					} catch (IOException e1) {
						System.out.println("Exception :"+e1);
					}
			}
		}catch(Exception e2){
			System.out.println("Exception : "+ e2);
		}

		if(!flag) {
			lblSecureMode.setText("Secure Mode OFF");			
			lblSecureMode.setIcon(new ImageIcon(DIR+"\\images\\color_label_circle_grey_T_16x16.png"));
			//btnTurnMonitorOff.setEnabled(false);
			
		}
		else {
			lblSecureMode.setText("Secure Mode ON");
			lblSecureMode.setIcon(new ImageIcon(DIR+"\\images\\green_transparent_16x16.png"));
			
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
					 file  = new File(path+"1082.lms");
						BufferedReader br5 = new BufferedReader(new FileReader(file));
						String securedFolder;
						
						
						boolean flag=false;
						if((securedFolder=br5.readLine()) != null) {
							
							securedFolder = decryption(securedFolder);
							File folderExisting = new File(securedFolder);
							flag=true;
							if(folderExisting.exists()) {
								
								Runtime.getRuntime().exec("explorer.exe "+ securedFolder);
								System.out.println(securedFolder);
							}
							else {
								JOptionPane.showMessageDialog(frame,"Either files are secured since registered device is not Present\n Or Secured folder is deleted.", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else {
							
					    	frame.getContentPane().removeAll();
							JOptionPane.showMessageDialog(frame,"First select a folder", "Info", JOptionPane.INFORMATION_MESSAGE);	
							secureFolder=true;
							frame.revalidate();
							frame.repaint();
							flag=false;
							
						}
						br5.close();
						if(flag) {
							
							menu();
							frame.revalidate();
							frame.repaint();
						}
						else {
							frame.getContentPane().removeAll();
							frame.revalidate();
							frame.repaint();
							selectFolder();
						}
				 }
				 catch (Exception e1) {
					 System.out.println("Exception :"+e1);
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
	
	private void wizard() {

		btnNext1 = new JButton("Add Device");
		btnNext1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNext1.setIcon(new ImageIcon(DIR+"\\images\\smallAddDevice.png"));
		btnNext1.setEnabled(false);
		
		

		btnNext2 = new JButton("Next");
		btnNext2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNext2.setIcon(new ImageIcon(DIR+"\\images\\smallNext.png"));
		

		btnBack1 = new JButton("Back");
		btnBack1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack1.setIcon(new ImageIcon(DIR+"\\images\\smallBack.png"));
		

		btnBack2 = new JButton("Back");
		btnBack2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack2.setIcon(new ImageIcon(DIR+"\\images\\smallBack.png"));
		

		btnFinish = new JButton("Finish");
		btnFinish.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFinish.setIcon(new ImageIcon(DIR+"\\images\\smallFinish.png"));
		
		
		btnAbort = new JButton("Cancel");
		btnAbort.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAbort.setIcon(new ImageIcon(DIR+"\\images\\smallCancel.png"));
		
		addDevice();
		//frame.getContentPane().remove(btnDone3);
		frame.getContentPane().remove(btnDeviceCancel);
		frame.getContentPane().remove(btnDevice);		
		frame.getContentPane().add(btnNext1);
		frame.getContentPane().add(btnAbort);
		btnSearch.setBounds(20, 220, 100, 30);		
		btnNext1.setBounds(140, 220, 120, 30);
		btnAbort.setBounds(280, 220, 100, 30);
		
		btnAbort.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				 				 
				 int response = JOptionPane.showConfirmDialog(frame, "Do you really want to cancel\nthe configuration process ?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				 if (response == JOptionPane.YES_OPTION ) {
					 
					 frame.getContentPane().removeAll();
					 frame.revalidate();
					 frame.repaint();
					 second();       
		           }
				 
			 }
		
		});
		
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
					System.out.println("Exception :"+e1);
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
				 frame.getContentPane().add(btnAbort);
				 btnBack1.setBounds(20, 220, 100, 30);
				 btnNext2.setBounds(142, 220, 100, 30);
				 btnAbort.setBounds(264, 220, 100, 30);			 
					
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
				 	frame.getContentPane().remove(btnDevice);
				 	
					frame.getContentPane().add(btnNext1);
					frame.getContentPane().add(btnAbort);
					btnSearch.setBounds(20, 220, 100, 30);
					//btnDevice.setBounds(142, 220, 120, 30);
					btnNext1.setBounds(142, 220, 120, 30);
					btnAbort.setBounds(284, 220, 100, 30);
					btnNext1.setEnabled(false);
					
			    }
		});
		btnNext2.addActionListener(new ActionListener() { 
			 public void actionPerformed(ActionEvent e) {
			    
				 
				 if(folderType!=-1)
				 {
					 folderadd=true;
					 BufferedWriter output;
						try {
							file  = new File(path+"1082.lms");
							output = new BufferedWriter(new FileWriter(file));
						
					 if(folderType==0)
					 {
								output.write(encryption(""+chooser.getSelectedFile()));							
								
					 }
					 else if(folderType==1)
					 {
						 		output.write(encryption(DIR+"\\securedFolder"));		
						 		new File(DIR+"\\securedFolder").mkdirs();	
								
								
					 }
					 output.close();	
					 } catch (Exception e1) {
						 System.out.println("Exception :"+e1);
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
					System.out.println("Exception :"+e1);
				} 
				 
				 frame.getContentPane().remove(btnSecurityCancel);	
				 frame.getContentPane().add(btnBack2);
					btnDone.setText("Finish");
					btnDone.setIcon(new ImageIcon(DIR+"\\images\\smallFinish.png"));
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
				 frame.getContentPane().add(btnAbort);
				 btnBack1.setBounds(20, 220, 100, 30);
				 btnNext2.setBounds(142, 220, 100, 30);
				 btnAbort.setBounds(264, 220, 100, 30);			 
					
			    }
		});
		
	}
	 
	private void addUser() {
		
		wiz=true;
		monitor=false;			
		monitorFolder=false;
		securityOp = false;
		secureFolder=false;
		
		JLabel label = new JLabel("Add New User");
		label.setBounds(198, 20, 140, 30);
		frame.getContentPane().add(label);
		label.setForeground(Color.BLUE);
		label.setFont(new Font(null, Font.BOLD, 14));

		final JLabel label1 = new JLabel("Username:");
		label1.setBounds(120, 65, 80, 14);
		label1.setFont(new Font(null, Font.PLAIN, 13));
		frame.getContentPane().add(label1);

		final JLabel label2 = new JLabel("Password:");
		label2.setBounds(120, 115, 80, 14);
		label2.setFont(new Font(null, Font.PLAIN, 13));
		frame.getContentPane().add(label2);

		btnSubmit = new JButton("Done");
		btnSubmit.setBounds(120, 165, 89, 30);
		frame.getContentPane().add(btnSubmit);
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setIcon(new ImageIcon(DIR+"\\images\\smallDone.png"));
		

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(230, 165, 89, 30);
		frame.getContentPane().add(btnCancel);
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setIcon(new ImageIcon(DIR+"\\images\\smallCancel.png"));

		textField1 = new JTextField();
		textField1.setBounds(198, 60, 120, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(198, 110, 120, 20);
		frame.getContentPane().add(passwordField);

		frame.getRootPane().setDefaultButton(btnSubmit);
		
		final JLabel warning = new JLabel("First Enter Username");
		warning.setBounds(198, 80, 150, 20);
		warning.setForeground(Color.RED);

		final JLabel warning1 = new JLabel("First Enter Password");
		warning1.setBounds(198, 130, 200, 20);
		warning1.setForeground(Color.RED);
		
		btnSubmit.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().remove(warning);
				frame.getContentPane().remove(warning1);
				if(textField1.getText().trim().equals("")||textField1.getText().trim()==null) {
					
					frame.getContentPane().add(warning);
					frame.revalidate();
					frame.repaint();
				}
				else if(passwordField.getPassword().length==0) {
					
					frame.getContentPane().add(warning1);
					frame.revalidate();
					frame.repaint();
				}
				else {

					try {

						file  = new File(path+"1085.lms");
						
						
						BufferedWriter output = new BufferedWriter(new FileWriter(file,true));
						BufferedReader br = new BufferedReader(new FileReader(file));
						String user = new String();
						
						output.close();
						int flag = 0;

						while((user=br.readLine())!=null)
							if(textField1.getText().equals(user)) {
								flag = 1;
								break;
							}
						
						br.close();


						if(flag == 0) {
							
							file  = new File(path+"1085.lms");
							PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
							out.println(toSHA1(textField1.getText().getBytes("UTF-8")));								
							out.close();

							
							char[] pass = passwordField.getPassword();
							String pass1 = new String(pass);
							
							
							file  = new File(path+"1084.lms");
							PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
							out1.println(toSHA1(pass1.getBytes("UTF-8")));								
							out1.close();

							file  = new File(path+"1083.lms");
							PrintWriter out3 = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
							out3.println(encryption("-1"));								
							out3.close();	
							
							mntmRegisterDevice.setEnabled(true);
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
						else {
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
		    		file  = new File(path+"1085.lms");
					BufferedReader br = new BufferedReader(new FileReader(file));
					if(br.read()==-1)
						System.exit(0);						
					br.close();
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

	private void change_pass() {
		
		JLabel label = new JLabel("Change Password");
		label.setBounds(150, 20, 140, 20);
		frame.getContentPane().add(label);
		label.setFont(new Font("Serif", Font.BOLD, 14));

		final JLabel label3 = new JLabel("Current Password:");
		label3.setBounds(100, 60, 140, 14);
		frame.getContentPane().add(label3);
		label3.setFont(new Font("Serif", Font.PLAIN, 13));
		
		final JLabel label1 = new JLabel("New Password:");
		label1.setBounds(100, 100, 140, 14);
		frame.getContentPane().add(label1);
		label1.setFont(new Font("Serif", Font.PLAIN, 13));

		final JLabel label2 = new JLabel("Confirm Password:");
		label2.setBounds(100, 140, 140, 14);
		frame.getContentPane().add(label2);
		label2.setFont(new Font("Serif", Font.PLAIN, 13));
		
		passwordField3 = new JPasswordField();
		passwordField3.setBounds(220, 60, 120, 20);
		frame.getContentPane().add(passwordField3);

		passwordField1 = new JPasswordField();
		passwordField1.setBounds(220, 100, 120, 20);
		frame.getContentPane().add(passwordField1);

		passwordField2 = new JPasswordField();
		passwordField2.setBounds(220, 140, 120, 20);
		frame.getContentPane().add(passwordField2);
		
		

		final JLabel warning = new JLabel("First Enter Current Password");
		warning.setBounds(220, 80, 150, 20);
		warning.setForeground(Color.RED);
		
		final JLabel warning1 = new JLabel("First Enter Password");
		warning1.setBounds(220, 120, 150, 20);
		warning1.setForeground(Color.RED);

		final JLabel warning2 = new JLabel("Passwords Do Not Match");
		warning2.setBounds(220, 160, 150, 20);
		warning2.setForeground(Color.RED);

		btnSubmit = new JButton("Done");
		btnSubmit.setBounds(110, 180, 89, 30);		
		frame.getContentPane().add(btnSubmit);
		btnSubmit.setIcon(new ImageIcon(DIR+"\\images\\smallDone.png"));
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(230, 180, 89, 30);
		frame.getContentPane().add(btnCancel);
		btnCancel.setIcon(new ImageIcon(DIR+"\\images\\smallCancel.png"));
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		frame.getRootPane().setDefaultButton(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		    	
		    	frame.getContentPane().remove(warning);
				frame.getContentPane().remove(warning1);
				frame.getContentPane().remove(warning2);
				
				int flag = 0;
				
				if(passwordField3.getPassword().length==0) {
					frame.getContentPane().add(warning);
					frame.revalidate();
					frame.repaint();
				}
				else {
					
					char[] currentPass = passwordField3.getPassword();
					String currentPass1 = new String(currentPass);
					

					file  = new File(path+"1084.lms");
					
					try {
						BufferedReader br5 = new BufferedReader(new FileReader(file));

						String pass1 = new String();
						pass1=br5.readLine();
						
						br5.close();
						
						if(pass1.equals(toSHA1(currentPass1.getBytes("UTF-8"))))
							flag = 1;
						else {
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
					
					
				}
				
				if(flag ==1)
				if(passwordField1.getPassword().length==0) {
					frame.getContentPane().add(warning1);
					frame.revalidate();
					frame.repaint();
				}
				else if(Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword())) {
					
					try {
						file  = new File(path+"1085.lms");
						BufferedReader br = new BufferedReader(new FileReader(file));
						String user = new String();

						int count = 0;

						while((user=br.readLine())!=null)
							if(toSHA1(textField1.getText().getBytes("UTF-8")).equals(user))
								break;
							else
								count++;
						
						br.close();

						try {
							// Open the file that is the first
							// command line parameter
							file  = new File(path+"1084.lms");
							FileInputStream fstream = new FileInputStream(path+"1084.lms");
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
							file  = new File(path+"1084.lms");
							FileWriter fstreamWrite = new FileWriter(file);
							BufferedWriter out = new BufferedWriter(fstreamWrite);
							out.write(fileContent.toString());
														
							out.close();
							//Close the input stream
							
							in.close();
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
				else {
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

	private void addDevice() {
				
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
		btnSearch.setIcon(new ImageIcon(DIR+"\\images\\smallSearch.png"));
		

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
		btnDevice.setIcon(new ImageIcon(DIR+"\\images\\smallAddDevice.png"));
		
		btnDeviceCancel = new JButton("Cancel");
		btnDeviceCancel.setBounds(270,220,100,30);
		frame.getContentPane().add(btnDeviceCancel);
		
		btnDeviceCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeviceCancel.setIcon(new ImageIcon(DIR+"\\images\\smallCancel.png"));
		
		file  = new File(path+"1086.lms");
		boolean fl= false;
		try {
			if (file.exists()) {
				BufferedReader input = new BufferedReader(new FileReader(file));		
			
				String str;
		
		
				if((str=input.readLine())!=null) {
	
					deviceParts=str.split(">>");			
				
					
					lblwarning.setText("Registered device : " + decryption(deviceParts[1]));
					fl=true;
					
				}
			
				input.close();
			}
			if(!fl) {
				lblwarning.setText("Click on Search Button to start search");
				
			}
			
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} 
		
		btnSearch.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e)
			{
			}
			public void mouseReleased(MouseEvent e) {
				
				if(asd()) {
					panel.setLayout(new GridLayout(0,1));
					lblwarning.setText("Select device and click on Add Device Button");
				}
				else {
					panel.setLayout(new GridLayout(0,1));
					lblwarning.setText("No device found");
				}
				lblwarning.setForeground(Color.BLACK);
				
			}
			public void mousePressed(MouseEvent e) {

				try
				{
					btnDevice.setEnabled(false);
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
		
	}
	
	/*public static JRadioButton getSelectedRadio(ButtonGroup group) {
		for (Enumeration eRadio=group.getElements(); eRadio.hasMoreElements(); ) {
			//Iterating over the Radio Buttons
			JRadioButton radioButton = (JRadioButton)eRadio.nextElement();
			//Comparing radioButtons model with groups selection
			if (radioButton.getModel() == group.getSelection()) {
				return radioButton;
			}
		}
		return null;
	}*/
	
	private void showPDF(String fileName) {
		
        File myFile = new File(DIR + "//" + fileName + ".pdf");
		if(myFile.exists()) {
			if (Desktop.isDesktopSupported()) {
			    try {
			        Desktop.getDesktop().open(myFile);
			    } catch (IOException ex) {
			        // no application registered for PDFs
			    	JOptionPane.showMessageDialog(frame,"First Install Adobe Reader.", "Info", JOptionPane.INFORMATION_MESSAGE);
			    }
			}
		}
		else {
			// If somehow file is deleted or not present.
			JOptionPane.showMessageDialog(frame,"File is deleted or moved to wrong location", "File Not Found", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void securityOptions() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
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
		btnDone.setIcon(new ImageIcon(DIR+"\\images\\smallDone.png"));
		
		btnSecurityCancel = new JButton("Cancel");
		btnSecurityCancel.setBounds(320, 180, 90, 30);
		frame.getContentPane().add(btnSecurityCancel);
		btnSecurityCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSecurityCancel.setIcon(new ImageIcon(DIR+"\\images\\smallCancel.png"));

		btnAddFiles = new JButton("Add files to secure");
		btnAddFiles.setBounds(20,180,170,30);
		frame.getContentPane().add(btnAddFiles);
		btnAddFiles.setEnabled(false);

		
		btnAddFiles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddFiles.setIcon(new ImageIcon(DIR+"\\images\\smallAddFiles.png"));


		file  = new File(path+"1083.lms");
		BufferedReader br2 = new BufferedReader(new FileReader(file));
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
		
		if(!validLicense){
			/* Demo version only starts*/
			o2.setSelected(false);
			r1.setSelected(false);
			r2.setSelected(false);
			r3.setSelected(false);
			r4.setSelected(false);
			
			o2.setEnabled(false);
			r1.setEnabled(false);		
			r2.setEnabled(false);
			r3.setEnabled(false);
			r4.setEnabled(false);
			
			
			
			final JLabel lblcustom = new JLabel("");
			o2.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e)
				{
				}
				public void mouseReleased(MouseEvent e) {
					
					
					
				}
				public void mousePressed(MouseEvent e) {
	
					
					
				}
				public void mouseEntered(MouseEvent e)
				{
					lblcustom.setText("This feature is available in full version only");
					lblcustom.setBounds(120, 275, 300, 30);
					lblcustom.setForeground(Color.RED);
					
					frame.getContentPane().add(lblcustom);
					
					
				}
				public void mouseExited(MouseEvent e)
				{
					lblcustom.setText("");
				}
			});
			
			r1.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e)
				{
				}
				public void mouseReleased(MouseEvent e) {
					
					
					
				}
				public void mousePressed(MouseEvent e) {
	
					
					
				}
				public void mouseEntered(MouseEvent e)
				{
					lblcustom.setText("This feature is available in full version only");
					lblcustom.setBounds(120, 275, 300, 30);
					lblcustom.setForeground(Color.RED);
					
					frame.getContentPane().add(lblcustom);
					
					
				}
				public void mouseExited(MouseEvent e)
				{
					lblcustom.setText("");
				}
			});
			
			r2.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e)
				{
				}
				public void mouseReleased(MouseEvent e) {
					
					
					
				}
				public void mousePressed(MouseEvent e) {
	
					
					
				}
				public void mouseEntered(MouseEvent e)
				{
					lblcustom.setText("This feature is available in full version only");
					lblcustom.setBounds(120, 275, 300, 30);
					lblcustom.setForeground(Color.RED);
					
					frame.getContentPane().add(lblcustom);
					
					
				}
				public void mouseExited(MouseEvent e)
				{
					lblcustom.setText("");
				}
			});
			
			r3.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e)
				{
				}
				public void mouseReleased(MouseEvent e) {
					
					
					
				}
				public void mousePressed(MouseEvent e) {
	
					
					
				}
				public void mouseEntered(MouseEvent e)
				{
					lblcustom.setText("This feature is available in full version only");
					lblcustom.setBounds(120, 275, 300, 30);
					lblcustom.setForeground(Color.RED);
					
					frame.getContentPane().add(lblcustom);
					
					
				}
				public void mouseExited(MouseEvent e)
				{
					lblcustom.setText("");
				}
			});
			
			r4.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e)
				{
				}
				public void mouseReleased(MouseEvent e) {
					
					
					
				}
				public void mousePressed(MouseEvent e) {
	
					
					
				}
				public void mouseEntered(MouseEvent e)
				{
					lblcustom.setText("This feature is available in full version only");
					lblcustom.setBounds(120, 275, 300, 30);
					lblcustom.setForeground(Color.RED);
					
					frame.getContentPane().add(lblcustom);
					
					
				}
				public void mouseExited(MouseEvent e)
				{
					lblcustom.setText("");
				}
			});
			
			/*demo version only ends*/
		}

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
					
					file  = new File(path+"1083.lms");
					BufferedWriter output = new BufferedWriter(new FileWriter(file));
					
					char c = (char) (sec + 48);
					String s = ""+c;
					output.write(encryption(s));
					output.flush();
					System.out.print(sec);					
					output.close();

				}catch(Exception ee){
					System.out.println("Could not write to a file"+ee);
				}

				//frame.getContentPane().removeAll();					
				//next();
				
				 try {
					 file  = new File(path+"1082.lms");
						BufferedReader br5 = new BufferedReader(new FileReader(file));
						String securedFolder;
						
						
						
						boolean flag=false;
						if((securedFolder=br5.readLine())!=null) {
							securedFolder = decryption(securedFolder);
							File folderExisting = new File(securedFolder);
							flag=true;
							if(folderExisting.exists()) {
								Runtime.getRuntime().exec("explorer.exe "+ securedFolder);
								System.out.println(securedFolder);
							
							}
							else {
								JOptionPane.showMessageDialog(frame,"Either files are secured since registered device is not Present\n Or Secured folder is deleted.", "Info", JOptionPane.INFORMATION_MESSAGE);
							}
						}
							else
							{
						    	frame.getContentPane().removeAll();
								JOptionPane.showMessageDialog(frame,"First select a folder",
										"Info", JOptionPane.INFORMATION_MESSAGE);	
								securityOp=true;
								selectFolder();
								frame.revalidate();
								frame.repaint();
								flag=false;							
															
							
						}
						br5.close();
						
						/*if((flag)&&(!wiz))
						{
							frame.getContentPane().removeAll();
							frame.revalidate();
							frame.repaint();
							menu();
							
						}
						else */
						if((!flag)&&(wiz)){
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

					file  = new File(path+"1083.lms");
					BufferedWriter output = new BufferedWriter(new FileWriter(file));
					
					char c = (char) (sec + 48);
					String s = ""+c;
					output.write(encryption(s));
					output.flush();
					System.out.print(sec);					
					output.close();

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
	
	private void second() {
		

		btnMenu = new JButton("Menu");
		btnMenu.setBounds(165,60,100,30);
		frame.getContentPane().add(btnMenu);
		
		btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMenu.setIcon(new ImageIcon(DIR+"\\images\\smallMenu.png"));
		
		btnMonitor = new JButton("Turn on Security Mode");
		btnMonitor.setBounds(140,100,150,30);
		frame.getContentPane().add(btnMonitor);
		 
		btnMonitor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		counter = counter +1;
		if(counter==0) {
			btnMonitor.setText("Turn on Security Mode");
			//btnMonitor.setForeground(Color.GRAY);
			lblState.setIcon(new ImageIcon(DIR+"\\images\\color_label_circle_grey_T_16x16.png"));
			lblMonitor.setText("Security Mode is Currently OFF");
		}
		else if(!monitor) {
			btnMonitor.setText("Turn on Security Mode");
			//btnMonitor.setForeground(Color.GRAY);
			lblState.setIcon(new ImageIcon(DIR+"\\images\\color_label_circle_grey_T_16x16.png"));
			lblMonitor.setText("Security Mode is Currently OFF");
		}
		else if(monitor) {
		//	btnMonitor.setForeground(Color.GREEN.darker());
			lblState.setIcon(new ImageIcon(DIR+"\\images\\green_transparent_16x16.png"));
    		btnMonitor.setText("Turn off Security Mode");
    		lblMonitor.setText("Security Mode is Currently OFF");
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
		btnChangeDevice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChangeDevice.setIcon(new ImageIcon(DIR+"\\images\\smallChangeDevice.png"));
		
		file  = new File(path+"1086.lms");
		boolean fl= false;
		try {
			if (file.exists()){
				BufferedReader input = new BufferedReader(new FileReader(file));		
			
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
		
		
		int flag = 0;
		file  = new File(path+"1087.lms");
		
		try {
			if (file.exists()) {
				
					try {
						
						BufferedReader br = new BufferedReader(new FileReader(file));
						if(br.read()!=-1)
							flag = 1;
						
						br.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		} catch(Exception e2) {
			System.out.println("Exception : "+ e2);
		}

		if(flag == 0)
		{
			lblMonitor.setText("Security Mode is currently OFF");
			monitor=false;
			lblState.setIcon(new ImageIcon(DIR+"\\images\\color_label_circle_grey_T_16x16.png"));
			//btnMonitor.setForeground(Color.GRAY);			
    		btnMonitor.setText("Turn on Security Mode");  		
			
		}
		
		else
		{
			lblMonitor.setText("Security Mode is Currently ON");			
			lblState.setIcon(new ImageIcon(DIR+"\\images\\green_transparent_16x16.png"));
			monitor=true;
			//btnMonitor.setForeground(Color.GREEN.darker());			
    		btnMonitor.setText("Turn off Security Mode");
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
				    		
				    		file  = new File(path+"1086.lms");
				    		
							boolean flag = false;
							boolean flag1 = true;
							boolean flag2 = true;
							
							if(file.exists())
							{						
								try {
									
									BufferedReader br = new BufferedReader(new FileReader(file));
									if(br.read()!=-1)
										flag = true;
									else
										flag = false;
									br.close();
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
									file  = new File(path+"1083.lms");
									BufferedReader br7 = new BufferedReader(new FileReader(file));
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
									file  = new File(path+"1083.lms");
									BufferedReader br7 = new BufferedReader(new FileReader(file));
									char c=decryption(br7.readLine()).charAt(0);
									
									if(c=='0'||c=='5'||c=='6'||c=='7'||c=='8')
									{
										
										file  = new File(path+"1082.lms");
										BufferedReader br5 = new BufferedReader(new FileReader(file));
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
										
										
										
								 }
									br7.close();
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
								lblState.setIcon(new ImageIcon(DIR+"\\images\\green_transparent_16x16.png"));
					    		btnMonitor.setText("Turn off Security Mode");
					    		
					    		lblMonitor.setText("Security Mode is Currently ON");
					    		
								
								lblSecureMode.setText("Secure Mode ON");
								lblSecureMode.setIcon(new ImageIcon(DIR+"\\images\\green_transparent_16x16.png"));
								
								
								//btnMonitor.setEnabled(false);
								//btnTurnMonitorOff.setEnabled(true);
								frame.revalidate();
								frame.repaint();
								try {
									
									file  = new File(path+"1087.lms");
									BufferedWriter output = new BufferedWriter(new FileWriter(file));
									output.write("1139074389928374827498237479234723974823479823478237482374");
									
									output.close();
									
									runTime = Runtime.getRuntime() ;     
									
									process = runTime.exec(DIR+"\\rll.exe") ;
									
									
									
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
				    			lblState.setIcon(new ImageIcon(DIR+"\\images\\color_label_circle_grey_T_16x16.png"));
					    		btnMonitor.setText("Turn on Security Mode");
					    		
					    		lblMonitor.setText("Security Mode is Currently OFF");
					    		
								
								lblSecureMode.setText("Secure Mode OFF");
								lblSecureMode.setIcon(new ImageIcon(DIR+"\\images\\color_label_circle_grey_T_16x16.png"));
								
							
				    			
				    			
								file  = new File(path+"1087.lms");
				    			BufferedReader br = new BufferedReader(new FileReader(file));
								String tokill,cmd="";
								if((tokill=br.readLine())!=null)
		
									cmd = "taskkill /F /PID " + tokill;
								Runtime.getRuntime().exec(cmd);						
								br.close();
								
								file  = new File(path+"1087.lms");
								BufferedWriter output = new BufferedWriter(new FileWriter(file));						
								output.close();
								//btnTurnMonitorOff.setEnabled(false);
								//btnTurnMonitorOn.setEnabled(true);
								frame.revalidate();
								frame.repaint();
		
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
						
		
				    	}
		    		}
		    		else
		    		{
		    			System.out.println("2Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.");
		    			JOptionPane.showMessageDialog(frame,"Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.",
		    					"Info", JOptionPane.ERROR_MESSAGE);	
		    		}
		    	}
		    	catch(Exception e8)
		    	{
		    		System.out.println("1Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.");
					JOptionPane.showMessageDialog(frame,"Bluetooth device not found on your system.\n Please verify that bluetooth device is properly connected and turned on.",
							"Info", JOptionPane.ERROR_MESSAGE);	
		    	}
		    }
		    
		   });
		
	}
		
	private void first() {
		monitor=false;
		wiz=false;
		monitorFolder=false;
		securityOp = false;
		secureFolder=false;
		
		final JLabel label = new JLabel("User Login");
		label.setBounds(175, 30, 150, 30);
		frame.getContentPane().add(label);
		label.setForeground(Color.BLUE);
		label.setFont(new Font(null, Font.BOLD, 14));

		
		final JLabel label1 = new JLabel("Username:");
		label1.setBounds(100, 70, 80, 32);
		label1.setFont(new Font(null, Font.PLAIN, 13));
		frame.getContentPane().add(label1);

		final JLabel label2 = new JLabel("Password:");
		label2.setBounds(100, 125, 80, 23);
		label2.setFont(new Font(null, Font.PLAIN, 13));
		frame.getContentPane().add(label2);

		btnSubmit = new JButton("Done");
		btnSubmit.setBounds(176, 180, 86, 30);
		frame.getContentPane().add(btnSubmit);
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setIcon(new ImageIcon(DIR+"\\images\\smallDone.png"));

		textField1 = new JTextField();
		textField1.setBounds(176, 75, 120, 23);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(176, 125, 120, 23);
		frame.getContentPane().add(passwordField);


		final JLabel warning = new JLabel("First Enter Username");
		warning.setBounds(176, 100, 150, 20);
		warning.setForeground(Color.RED);

		final JLabel warning1 = new JLabel("First Enter Password");
		warning1.setBounds(176, 150, 200, 20);
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
				else {
					file  = new File(path+"1085.lms");
					
					if(file.exists()) {
						try{
						
														
							file1  = new File(path+"1084.lms");
							
							
							BufferedReader br = new BufferedReader(new FileReader(file));
							BufferedReader br1 = new BufferedReader(new FileReader(file1));

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
										
										mntmRegisterDevice.setEnabled(true);
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
					} else {
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