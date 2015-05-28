import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.util.Vector;
import java.util.ArrayList;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.crypto.*;

import java.security.*;

public class bluetoothInRange extends cryptography implements DiscoveryListener {

// object used for waiting
private static Object lock = new Object();

// vector containing the devices discovered
private static Vector<RemoteDevice> vecDevices = new Vector<RemoteDevice>();
private static Vector<String> vecServices = new Vector<String>();
private static int[] status =new int[1000000];


public static void restartApplication()
{
  final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
  File currentJar = null;
try {
	currentJar = new File(bluetoothInRange.class.getProtectionDomain().getCodeSource().getLocation().toURI());
} catch (URISyntaxException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}

  /* is it a jar file? */
  //if(!currentJar.getName().endsWith(".jar"))
  //  return;

  if(!currentJar.getName().endsWith(".exe"))
	    return;
  /* Build command: java -jar application.jar */
  final ArrayList<String> command = new ArrayList<String>();
 // command.add(javaBin);
  //command.add("-jar");
  command.add(currentJar.getPath());

  final ProcessBuilder builder = new ProcessBuilder(command);
  try {
	    builder.start();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
  System.exit(0);
}

// main method of the application
public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

	String processName = ManagementFactory.getRuntimeMXBean().getName();
	String processNameParts[] = processName.split("@");
	File originalFile,fakeFile,originalFile1,fakeFile1;
	String path = System.getProperty("user.dir")+"\\bluetooth\\";
	try {
		originalFile  = new File(path+"pid.txt");
		fakeFile  = new File(path+"pid.lms");
		fakeFile.renameTo(originalFile);
		BufferedWriter output = new BufferedWriter(new FileWriter(originalFile));
		output.write(processNameParts[0]);
		System.out.println(System.getProperty("user.dir"));
		output.close();
		originalFile.renameTo(fakeFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
		
	
	// create an instance of this class
	bluetoothInRange bluetoothDeviceDiscovery = new bluetoothInRange();

    // display local device address and name
    LocalDevice localDevice = LocalDevice.getLocalDevice();
    
    System.out.println("Address: " + localDevice.getBluetoothAddress());
    System.out.println("Name: " + localDevice.getFriendlyName());


    // find devices
       DiscoveryAgent agent = localDevice.getDiscoveryAgent();

    System.out.println("Starting device inquiry...");
    agent.startInquiry(DiscoveryAgent.GIAC, bluetoothDeviceDiscovery);

    try {
        synchronized (lock) {
            lock.wait();
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    System.out.println("Device Inquiry Completed. ");
    System.out.println("Service Inquiry Started. ");

    UUID uuids[] = new UUID[1];
    uuids[0] = new UUID("fa87c0d0afac11de8a390800200c9a66", false);

    for (RemoteDevice rd : vecDevices) {
       // System.out.println("From: " + rd.getFriendlyName(false));
        agent.searchServices(null, uuids, rd, bluetoothDeviceDiscovery);
        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }

    // print all devices in vecDevices
    int deviceCount = vecDevices.size();
    boolean b=false;
    if (deviceCount <= 0) 
    {
        System.out.println("No Devices Found .");
    } 
    else 
    {	originalFile  = new File(path+"deviceCode.txt");
		fakeFile  = new File(path+"deviceCode.lms");
		fakeFile.renameTo(originalFile);
    	
	    if (!originalFile.isFile() && !originalFile.createNewFile()){
	        throw new IOException("Error creating new file: " + originalFile.getAbsolutePath());
	    }
	    originalFile.renameTo(fakeFile);

	    
	    originalFile  = new File(path+"options.txt");
		fakeFile  = new File(path+"options.lms");
		fakeFile.renameTo(originalFile);
    	
	    if (!originalFile.isFile() && !originalFile.createNewFile()){
	        throw new IOException("Error creating new file: " + originalFile.getAbsolutePath());
	    }
	    originalFile.renameTo(fakeFile);
	    
    	// Reading the Registered Bluetooth Device
	    originalFile  = new File(path+"deviceCode.txt");
		fakeFile  = new File(path+"deviceCode.lms");
		fakeFile.renameTo(originalFile);
    	BufferedReader br = new BufferedReader(new FileReader(originalFile));
    	int n = br.read();
    		if(n == -1)
    			System.exit(0);
            String bluetoothDeviceCode,str;
            str= (char)n+br.readLine();
            String parts[];
            parts = str.split(">>");            
            bluetoothDeviceCode = decryption(parts[0]);
            
            
            br.close();
            originalFile.renameTo(fakeFile);
     // print devices discovered in range
        System.out.println("Bluetooth Devices in range: ");
         for (int i = 0; i < deviceCount; i++) 
         {
                RemoteDevice rds = (RemoteDevice) vecDevices
                        .elementAt(i);
                if(status[i+1]==4)
                System.out.println((i + 1) + ". "
                        + rds.getBluetoothAddress() + " ("
                        + rds.getFriendlyName(false) + ")");
                if(rds.getBluetoothAddress().equals(bluetoothDeviceCode) && status[i+1]==4)
                {
                	
                /*	BufferedReader br2 = new BufferedReader(new FileReader("c:/Bluetooth/options.txt"));
                	char c = (char) br2.read();*/
                	try {
                		  originalFile  = new File(path+"folder.txt");
                			fakeFile  = new File(path+"folder.lms");
                			fakeFile.renameTo(originalFile);
    					BufferedReader br5 = new BufferedReader(new FileReader(originalFile));
    					String securedFolder;
    					
    					if((securedFolder=decryption(br5.readLine()))!=null)
    					{
    						
    						
    						//File f = new File(System.getProperty("user.dir")+"\\bluetooth\\securedFolder");
    						File f = new File(securedFolder);
    						if(f.exists())
    						{
    							Runtime.getRuntime().exec("attrib -H -S " +f); 
    							Runtime.getRuntime().exec("attrib -H -S " +f +"/*.* /D /S");
    							System.out.println("device in range");
    						}
    					}
    						
    					br5.close();
    					originalFile.renameTo(fakeFile);
    				} catch (Exception e2) {
    					// TODO Auto-generated catch block
    					e2.printStackTrace();
    				}
                	
                //	File f = new File(System.getProperty("user.dir")+"\\bluetooth\\securedFolder");  
                	//Runtime.getRuntime().exec("attrib -H -S " +f); 
                	
                	//if(c=='0'||c=='5'||c=='6'||c=='7'||c=='8')
                		/*{
                		BufferedReader br1 = new BufferedReader(new FileReader("c:/Bluetooth/selectedFiles.txt"));
                        		String fileSecured = new String();
                        		String filePath = new String();
                        		String fileName = new String();
                        		String[]  fileSeparator;
                        			while((fileSecured = br1.readLine())!=null)
                        			{
                        				fileSeparator = fileSecured.split(">");
                        				int j;
                    					for(j=fileSeparator[0].length()-1;fileSeparator[0].charAt(j)!='\\';j--);
                    					
                    					filePath = fileSeparator[0].substring(0,j+1);
                        				File f = new File(filePath+fileSeparator[1]);
                        				fileName = fileSeparator[0].substring(j+1,fileSeparator[0].length());
                        				
                        				System.out.println(f.getAbsolutePath());
                        				if(f.isHidden())
                        				{
                        					Runtime.getRuntime().exec("attrib -H -S "+"\""+f.getAbsolutePath()+"\"");  
                        					Runtime.getRuntime().exec("ren "+"\""+f.getAbsolutePath()+"\""+   " "    +"\""+fileName+"\"");  
                        					System.out.println("\""+filePath+fileName+"\"");
                        					System.out.println("Device in Range, You may access files now");
                        				}
                        			}
                        			br1.close();
                	}*/
                //	br2.close();
            		b=true;            		            		
                }
        }
    }
    if(!b)
    {
    	System.out.println("nahi mila"); 
    	originalFile  = new File(path+"options.txt");
			fakeFile  = new File(path+"options.lms");
			fakeFile.renameTo(originalFile);
    	
		    if (!originalFile.isFile() && !originalFile.createNewFile())
		    {
		        throw new IOException("Error creating new file: " + originalFile.getAbsolutePath());
		    }
		    originalFile.renameTo(fakeFile);
			
		    
		    originalFile  = new File(path+"options.txt");
			fakeFile  = new File(path+"options.lms");
			fakeFile.renameTo(originalFile);
    	BufferedReader br2 = new BufferedReader(new FileReader(originalFile));
    	char c = decryption(br2.readLine()).charAt(0);
    	if(c=='0'||c=='5'||c=='6'||c=='7'||c=='8')
    	{
    		System.out.println("hide karni hai"); 
    		originalFile1  = new File(path+"folder.txt");
 			fakeFile1  = new File(path+"folder.lms");
 			fakeFile1.renameTo(originalFile1);
    		BufferedReader br5 = new BufferedReader(new FileReader(originalFile1));
			String securedFolder;
			
			if((securedFolder=decryption(br5.readLine()))!=null)
			{
				File f = new File(securedFolder);  
				System.out.println(f.getName());
    		
				if(!f.isHidden())
				{
					System.out.println("hide nahi thi kar di");
					Runtime.getRuntime().exec("attrib +H +S " +f +"/*.* /D /S");
					Runtime.getRuntime().exec("attrib +H +S " +f);
    		
				}
				else if(f.isHidden())
					System.out.println("Files already hidden");
			}
			br5.close();
			 originalFile1.renameTo(fakeFile1);
		}
    /*	BufferedReader br1 = new BufferedReader(new FileReader("c:/Bluetooth/selectedFiles.txt"));
    		String fileSecured = new String();
    		String filePath = new String();
    		String fileName = new String();
    		String[]  fileSeparator;
    			while((fileSecured = br1.readLine())!=null)
    			{
    				fileSeparator = fileSecured.split(">");
    				int j;
					for(j=fileSeparator[0].length()-1;fileSeparator[0].charAt(j)!='\\';j--);
					
					filePath = fileSeparator[0].substring(0,j+1);
					fileName = fileSeparator[0].substring(j+1,fileSeparator[0].length());
    				File f = new File(filePath+fileName);
    				System.out.println("\file hidden"+f.getAbsolutePath()+"\"");  
    				System.out.println("\""+filePath+fileName+"\"");  
    				
    				
    				if(!f.isHidden())
    				{
    					System.out.println("ren "+"\""+f.getPath()+"\""+     " "    +"\""+fileSeparator[1]+"\"");
    					Runtime.getRuntime().exec("ren "+"\""+f.getAbsolutePath()+"\""+     " "    +"\""+filePath+fileSeparator[1]+"\"");  
    					System.out.println("ren "+"\""+f.getPath()+"\""+     " "    +"\""+fileSeparator[1]+"\"");
    					Runtime.getRuntime().exec("attrib +H +S "+"\""+filePath+fileSeparator[1]+"\"");      					
    					System.out.println("\""+filePath+fileSeparator[1]+"\"");   					
    					
    					
    					/*Runtime.getRuntime().exec("attrib +H +S "+"\""+f.getPath()+"\"");  
    					System.out.println("\""+f.getPath()+"\"");
    					
    					System.out.println("Device out of Range, Your files secured now");
    				}
    				else if(f.isHidden())
    					System.out.println("Files already hidden");
    			}
    			br1.close();*/
    
    	
    	String os = System.getProperty("os.name");
    	switch(c)
    	{

    		case '1' : 
    		case '5' : if (os.contains("Windows")) {

        		Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");

        		Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");

        		Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");

        		Runtime.getRuntime().exec("taskkill /F /IM safari.exe");

        		Runtime.getRuntime().exec("taskkill /F /IM opera.exe");

        		} else {

        		// Assuming a non Windows OS will be some version of Unix, Linux, or Mac

        		Runtime.getRuntime().exec("kill `ps -ef | grep -i firefox | grep -v grep | awk '{print $2}'`");

        		Runtime.getRuntime().exec("kill `ps -ef | grep -i chrome | grep -v grep | awk '{print $2}'`");

        		Runtime.getRuntime().exec("kill `ps -ef | grep -i safari | grep -v grep | awk '{print $2}'`");
        		
        		Runtime.getRuntime().exec("taskkill /F /IM explorer.exe");

        		}
	
    			Runtime.getRuntime().exec("shutdown -f -s -t 0");            		
					System.out.println("Device in Range!");
					break;
			
    		case '2' :
    		case '6':     		

    		if (os.contains("Windows")) {

    		Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");

    		Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");

    		Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");

    		Runtime.getRuntime().exec("taskkill /F /IM safari.exe");

    		Runtime.getRuntime().exec("taskkill /F /IM opera.exe");
    		
    		Runtime.getRuntime().exec("taskkill /F /IM explorer.exe");

    		} else {

    		// Assuming a non Windows OS will be some version of Unix, Linux, or Mac

    		Runtime.getRuntime().exec("kill `ps -ef | grep -i firefox | grep -v grep | awk '{print $2}'`");

    		Runtime.getRuntime().exec("kill `ps -ef | grep -i chrome | grep -v grep | awk '{print $2}'`");

    		Runtime.getRuntime().exec("kill `ps -ef | grep -i safari | grep -v grep | awk '{print $2}'`");

    		}
    			Runtime.getRuntime().exec("shutdown -f -r");            		
					System.out.println("Device in Range!");
					break;
    		case '3' :		
    		case '7': 
    		

    		if (os.contains("Windows")) {

    		Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");

    		Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");

    		Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");

    		Runtime.getRuntime().exec("taskkill /F /IM safari.exe");

    		Runtime.getRuntime().exec("taskkill /F /IM opera.exe");
    		
    		Runtime.getRuntime().exec("taskkill /F /IM explorer.exe");

    		} else {

    		// Assuming a non Windows OS will be some version of Unix, Linux, or Mac

    		Runtime.getRuntime().exec("kill `ps -ef | grep -i firefox | grep -v grep | awk '{print $2}'`");

    		Runtime.getRuntime().exec("kill `ps -ef | grep -i chrome | grep -v grep | awk '{print $2}'`");

    		Runtime.getRuntime().exec("kill `ps -ef | grep -i safari | grep -v grep | awk '{print $2}'`");

    		}
    		Runtime.getRuntime().exec("shutdown -f -l");


    		
    			//Runtime.getRuntime().exec("rundll32.exe user32.dll,LockWorkStation");            		
					System.out.println("Device in Range!");
					break;
    		case '4' :
    		case '8' : Runtime.getRuntime().exec("rundll32.exe user32.dll,LockWorkStation");            		
						System.out.println("Device in Range!");
						break;
			//default: System.out.println("Not a Valid Option!");
    	}
    	br2.close();
    	 originalFile.renameTo(fakeFile);
    }
    restartApplication();

}// end main


// methods of DiscoveryListener

/**
 * This call back method will be called for each discovered bluetooth
 * devices.
 */
public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
    System.out.println("Device discovered: "
            + btDevice.getBluetoothAddress());
    // add the device to the vector
    if (!vecDevices.contains(btDevice)) {
        vecDevices.addElement(btDevice);
    }
}

// no need to implement this method since services are not being discovered
public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
    for (ServiceRecord sr : servRecord) {
        vecServices.add(sr.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false));
    }
} 

// no need to implement this method since services are not being discovered
public void serviceSearchCompleted(int transID, int respCode) {
	status[transID] = respCode;
    synchronized (lock) {
        lock.notify();
    }
} 

/**
 * This callback method will be called when the device discovery is
 * completed.
 */
public void inquiryCompleted(int discType) {
    switch (discType) {
    case DiscoveryListener.INQUIRY_COMPLETED:
        System.out.println("INQUIRY_COMPLETED");
        break;

    case DiscoveryListener.INQUIRY_TERMINATED:
        System.out.println("INQUIRY_TERMINATED");
        break;

    case DiscoveryListener.INQUIRY_ERROR:
        System.out.println("INQUIRY_ERROR");
        break;

    default:
        System.out.println("Unknown Response Code");
        break;
    }
    synchronized (lock) {
        lock.notify();
    }
}// end method
}// end class
