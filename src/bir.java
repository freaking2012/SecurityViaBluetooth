import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

class bir {

	public static final Vector/*<RemoteDevice>*/ devicesDiscovered = new Vector();
	static StringBuffer s = new StringBuffer("");
	// main method of the application
	public static StringBuffer manu() throws IOException {
		s = new StringBuffer("");

        final Object inquiryCompletedEvent = new Object();
        
        
        devicesDiscovered.clear();

        DiscoveryListener listener = new DiscoveryListener() {

            public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
                
                devicesDiscovered.addElement(btDevice);
                try {
                    s.append(btDevice.getFriendlyName(false)+"\n");
                    s.append(btDevice.getBluetoothAddress() + "\n");
                } catch (IOException cantGetDeviceName) {
                }
            }

            public void inquiryCompleted(int discType) {
                System.out.println("Device Inquiry completed!");
                synchronized(inquiryCompletedEvent){
                    inquiryCompletedEvent.notifyAll();
                }
            }

            public void serviceSearchCompleted(int transID, int respCode) {
            }

            public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
            }
        };

        synchronized(inquiryCompletedEvent) {
            boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
            if (started) {
                System.out.println("wait for device inquiry to complete...");
                try {
					inquiryCompletedEvent.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                System.out.println(devicesDiscovered.size() +  " device(s) found");
            }
        }
		return s;

	}// end main

	// methods of DiscoveryListener
	
}// end class


class systemBluetooth {
	public static boolean check() throws IOException {
        
        LocalDevice localDevice = LocalDevice.getLocalDevice();
        if(localDevice.getFriendlyName()==null)
        	return false;
        else
        	return true;
   	}	
}