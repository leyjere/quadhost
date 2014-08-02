package Networking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import Information.Data;
import Information.DataPipe;
import Information.Digital;

public class Server implements Runnable{

	private Socket connection;
	private ServerSocket serverSocket;
	private ObjectInputStream commandIn;
	private ObjectOutputStream commandOut;

	public Server(int port) throws IOException
	{
		serverSocket = new ServerSocket(port);
	}

	@Override
	public void run() {

			try
			{
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");

				Socket connection = serverSocket.accept();

				System.out.println("Just connected to " + connection.getRemoteSocketAddress());

				/* Initiate a ois for reading messages */
				commandIn = new ObjectInputStream(connection.getInputStream());
				
				/* We also require a oos so that messages can be sent to the client */
				commandOut = new ObjectOutputStream(connection.getOutputStream());
				
				while (true){

					/* Control state for incoming messages from quad */
					if(commandIn.available() > 0){

						try {
							System.out.println("SERVER: RECEIVED OBJECT");
							Data d = (Data)commandIn.readObject();

							DataPipe.incoming.put(d);

						} catch (ClassNotFoundException e) {
							e.printStackTrace();
							System.out.println("TYPE CAST READ FAILED");
						} catch (InterruptedException e) {
							e.printStackTrace();
							System.out.println("COULD NOT ADD DATA TO INCOMING");
						}
						
						continue;
					}
					
					/* Control statement for outgoing messages to quad */
					if(DataPipe.outgoing.size() > 0){
						
						try {
							System.out.println("SERVER: SENDING OBJECT");
							Data d = (Data)DataPipe.outgoing.take();
							commandOut.writeObject(d);
						} catch (InterruptedException e) {
							e.printStackTrace();
							System.out.println("TYPE CAST WRITE FAILED");
						}
						
					}
					Thread.sleep(25);	
				}

			}catch(SocketTimeoutException s){
				s.printStackTrace();
				System.out.println("SOCKET TIMEOUT");
			}catch(IOException e){
				e.printStackTrace();
				System.out.println("NETWORK SOCKET FAILURE");
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("THREAD SLEEP FAILURE");
			}

	}

}
