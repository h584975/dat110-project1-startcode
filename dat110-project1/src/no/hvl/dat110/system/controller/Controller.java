package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		
		RPCClient displayclient,sensorclient;
		
		System.out.println("Controller starting ...");
		
		Sensor sensor = new Sensor();
		Display display = new Display(); 
		RPCServerStopStub stopdisplay = new RPCServerStopStub();
		RPCServerStopStub stopsensor = new RPCServerStopStub();
		
		displayclient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);
		
		displayclient.register(display);
		displayclient.register(stopdisplay);
		
		sensorclient.register(sensor);
		sensorclient.register(stopsensor);
		
		for(int i = 0; i < N; i++) {
			
			int temp = sensor.read();
			display.write(Integer.toString(temp));
		}
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayclient.disconnect();
		sensorclient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}
