package proxy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
interface mySockets { 
	String readLine(); 
	void wrtieLine(String myString); 
	void dispose(); }

public class SocketProxy implements mySockets { 
	private Socket mySocket; private BufferedReader myIn;
	private PrintWriter myOut; 
	public SocketProxy(String myHost, int myPort, boolean myWait) {
		try { 
			if (myWait) { 
				System.out.println("Iniciando servidor");
				ServerSocket myServer = new ServerSocket(myPort);
				System.out.println("Aguardando conexao");
				mySocket = myServer.accept();
				System.out.println("Conexao estabelecida");
			} 
			else mySocket = new Socket(myHost, myPort); 
			myIn = new BufferedReader( new InputStreamReader(mySocket.getInputStream()));
			myOut = new PrintWriter(mySocket.getOutputStream(), true);
			} catch(IOException e) { 
				e.printStackTrace(); 
				} 
	}


	public String readLine() { 
		String myString = null; 
		try { 
			myString = myIn.readLine(); 
			} catch( IOException e ) { 
			e.printStackTrace(); } 
		return myString; }

	public void wrtieLine(String myString) { 
		myOut.println(myString); }

	public void dispose() { 
		try { 
			mySocket.close(); 
			} catch( IOException e ) {
				e.printStackTrace(); } 
	} 
}
