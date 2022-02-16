package me.ezb2661.ircserver;

import me.ezb2661.ircserver.gui.LogFrame;

public class ClientThread extends Thread implements Runnable
{
    private final ClientConnection client;

    public ClientThread( ClientConnection client )
    {
        this.client = client;
        LogFrame.instance.logMessage( "[+] Started new thread for client " + this.client.getSocket( ).getInetAddress( ) );
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
                    LogFrame.instance.logMessage( "[-] Client: " + client + " disconnected." );
                    Server.instance.removeClientThread( this );
                    return;
                }
                Logger.logError( ex );
            }

            if( incomingMessage == null ) { continue; }
            if( incomingMessage.contains( "nickname: ") && this.client.getClientName( ) == null )
            {
                this.client.setClientName( incomingMessage.split("nickname: ")[1] );
                LogFrame.instance.logMessage( "[*] [" + this.client.getSocket( ).getInetAddress( ) + "] set nickname to: " + this.client.getClientName( ) );
                continue;
            }
            Server.instance.sendMessageToClients( this.client.getClientName( ) + ": " + incomingMessage );
            LogFrame.instance.logMessage( "[*] [" + this.client.getSocket( ).getInetAddress( ) + " / " + this.client.getClientName( ) + "]: " + incomingMessage );
        }
    }

    public ClientConnection getClientConnection( )
    {
        return this.client;
    }

}
