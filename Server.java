package me.ezb2661.ircserver;

import me.ezb2661.ircserver.command.CommandManager;
import me.ezb2661.ircserver.gui.ChatFrame;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread implements Runnable
{
    public static final String SERVER_VERSION = "1.0";

    public static Server instance;
    private final int port;
    private ServerSocket serverSocket;
    private final ArrayList<ClientThread> clientThreads = new ArrayList<>( );

    public Server( int port )
    {
        this.port = port;
    }

    public void run( )
    {
        try
        {
            this.serverSocket = new ServerSocket( this.port );
        }
        catch( Exception ex )
        {
            Logger.logMessage( "[!] Unable to start server." );
            Logger.logError( ex );
            System.exit( 0 );
        }

        Logger.logMessage( "[+] Server started on port " + this.port );

        Logger.logMessage( "[*] Initializing command manager" );
        CommandManager.instance = new CommandManager( );

        while( !this.serverSocket.isClosed( ) )
        {
            try
            {
                Socket socket = this.serverSocket.accept( );
                ClientConnection incomingConnection = new ClientConnection( socket );
                this.createClientThread( incomingConnection );
            }
            catch( Exception ex )
            {
                Logger.logMessage( "[!] Unable to create connection with incoming client" );
                Logger.logError( ex );
            }
        }
    }

    public void sendMessageToClients( String msg )
    {
        for( ClientThread clientThread : this.clientThreads )
        {
            ClientConnection clientConnection = clientThread.getClientConnection( );
            try
            {
                clientConnection.writeString( msg );
            }
            catch( Exception ex )
            {
                Logger.logMessage( "[!] Failed to send message to client: " + clientConnection.clientId );
                Logger.logError( ex );
            }
        }
    }

    public void removeClientThread( ClientThread clientThread )
    {
        ChatFrame.instance.removeClientConnection( clientThread.getClientConnection( ) );
        this.clientThreads.remove( clientThread );
        try
        {
            clientThread.join( );
        }
        catch( Exception ex )
        {
            Logger.logError( ex );
        }
    }

    private void createClientThread( ClientConnection incomingConnection )
    {
        ClientThread clientThread = new ClientThread( incomingConnection );
        clientThread.start( );
        this.clientThreads.add( clientThread );
        ChatFrame.instance.addClientConnection( incomingConnection );
    }

    public void kickUserId( int idToKick )
    {
        for( ClientThread clientThread : this.clientThreads )
        {
            if( clientThread.getClientConnection( ).clientId == idToKick )
            {
                try
                {
                    clientThread.getClientConnection().getSocket().close( );
                }
                catch( Exception ex )
                {
                    Logger.logMessage( "[!] Error kicking " + clientThread.getClientConnection( ) );
                    Logger.logError( ex );
                }
                return;
            }
        }

        Logger.logMessage( "[!] Error: client ID " + idToKick + " is not connected." );
    }
}
