package me.ezb2661.ircserver;

import me.ezb2661.ircserver.gui.ChatFrame;

public class ClientThread extends Thread implements Runnable
{
    private final ClientConnection client;

    public ClientThread( ClientConnection client )
    {
        this.client = client;
        ChatFrame.instance.logMessage( "[+] Started new thread for client " + this.client.getSocket( ).getInetAddress( ) );
    }

    @Override
    public void run( )
    {
        while( true )
        {
            String incomingMessage = null;
            try
            {
                incomingMessage = this.client.readString( );
            }
            catch( Exception ex )
            {
                try
                {
                    this.client.getSocket( ).getInputStream( ).read( );
                }
                catch( Exception ex1 )
                {
                    ChatFrame.instance.logMessage( "[-] Client: " + client + " disconnected." );
                    Server.instance.removeClientThread( this );
                    return;
                }
                Logger.logError( ex );
            }

            if( incomingMessage == null ) { continue; }
            Server.instance.sendMessageToClients( incomingMessage );
            ChatFrame.instance.logMessage( "[*] [" + this.client.getSocket( ).getInetAddress( ) + "] " + incomingMessage );
        }
    }

    public ClientConnection getClientConnection( )
    {
        return this.client;
    }

}
