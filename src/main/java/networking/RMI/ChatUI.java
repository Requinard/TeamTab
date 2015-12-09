package networking.RMI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vito Corleone on 30-11-2015.
 */
public class ChatUI {
    // logger for the class
    private static final Logger log = Logger.getLogger(ChatUI.class.getName());

    // binding name for the RMI registry
    private static final String bindingName = "chatApplication";

    // port for the registry
    private final static int portNumber = 1099;

    // ip address where the registry is located
    private static String ipAddress;

    // References to registry and chatserver
    private Registry registry = null;
    private IChatServer chatServer = null;
    private ChatClient chatClient = null;

    // check if a client is already connected
    private boolean connected;

    // UI components
    private JTextArea tx;
    private JTextField tf, ip, name;
    private JButton connect;
    private JList lst;
    private JFrame frame;

    /**
     * the construtor of the class
     * Author Kamil
     *
     * @param ipAddress the ipaddress of the registry
     */
    public ChatUI(String ipAddress) {
        log.log(Level.INFO, "ChatUI: starting");

        // create the GUI and events
        createFormAndEvents();
    }

    /**
     * this method will varify the gui of the input, locate the RMI registry via the ipaddress and portnumber
     * create a new client and then a new instance of chatserver through the interface IChatServer en use it to login the client to the chatapp
     * Author Kamil
     *
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    public void doConnect() throws RemoteException {
        if (connect.getText().equals("Connect")) {
            if (name.getText().length() < 2) {
                JOptionPane.showMessageDialog(frame, "You need to type a name.");
                return;
            }
            if (ip.getText().length() < 2) {
                JOptionPane.showMessageDialog(frame, "You need to type an IP.");
                return;
            }
            // set the ipaddress of the class on the input that was given by the client
            ipAddress = ip.getText();
            try {
                // connect to the registry via the ipaddress and portnumber
                try {
                    registry = LocateRegistry.getRegistry(ipAddress, portNumber);
                    log.log(Level.INFO, "ChatUI: registry located on " + ipAddress + " portnumber " + portNumber);
                    JOptionPane.showMessageDialog(frame, "Registry Located");
                } catch (RemoteException ex) {
                    log.log(Level.INFO, "ChatUI: Cannot locate registry");
                    log.log(Level.INFO, "ChatUI: RemoteException: " + ex.getMessage());
                    registry = null;
                    JOptionPane.showMessageDialog(frame, "Registry null");
                }

                // Bind student administration using registry
                if (registry != null) {
                    try {
                        chatServer = (IChatServer) registry.lookup(bindingName);
                        log.log(Level.INFO, "ChatUI: chatserver is binded");
                        JOptionPane.showMessageDialog(frame, "ChatServer bindend");

                    } catch (RemoteException ex) {
                        log.log(Level.INFO, "ChatUI: Cannot bind chat application");
                        log.log(Level.INFO, "ChatUI: RemoteException: " + ex.getMessage());
                        JOptionPane.showMessageDialog(frame, "ChatServer not binded remoteExecption");

                        return;
                    } catch (NotBoundException ex) {
                        log.log(Level.INFO, "ChatUI: Cannot bind chat application");
                        log.log(Level.INFO, "ChatUI: NotBoundException: " + ex.getMessage());
                        JOptionPane.showMessageDialog(frame, "ChatServer not bindend NotBoundException");
                        return;
                    }
                }
                // create new chatclient and set the GUI
                chatClient = new ChatClient(name.getText());
                chatClient.setGUI(this);
                log.log(Level.INFO, "ChatUI: chatclient created");

                // login the client
                chatServer.login(chatClient);
                log.log(Level.INFO, "ChatUI: Chatclient logged in");

                // get all the connected clients
                chatServer.getConnected();

                // set the connect button to read disconnect
                connect.setText("Disconnect");

                // set the boolean true because this player is connected
                connected = true;
                JOptionPane.showMessageDialog(frame, "ChatUI all good :)");

                return;
            } catch (Exception e) {
                e.printStackTrace();
                log.log(Level.INFO, "ChatUI: Cannot login in");
                JOptionPane.showMessageDialog(frame, "ERROR, we couldn't connect. Is you're IP-address right?");
            }
        }
    }

    /**
     * This method will logout the chatclient
     * Author Kamil
     *
     * @throws RemoteException       when a communication-related exception has occurred during the execution of a remote method
     * @throws NotBoundException
     * @throws MalformedURLException
     */
    public void logOut() throws RemoteException, NotBoundException, MalformedURLException {
        if (chatClient != null) {
            chatServer.logOut(chatClient);
            log.log(Level.INFO, "ChatUI: client logging out");
        }
    }

    /**
     * this method will publish the text to all the clients
     * Author Kamil
     */
    public void sendText() {
        if (connect.getText().equals("Connect")) {
            JOptionPane.showMessageDialog(frame, "You need to connect first.");
            return;
        } else {
            String st = tf.getText();
            log.log(Level.INFO, "ChatUI: sending " + st);
            st = "[" + name.getText() + "] " + st;
            tf.setText("");
            try {
                log.log(Level.INFO, "ChatUI: publishing " + st);
                chatServer.publish(st);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Author Kamil
     *
     * @param st
     */
    public void writeMsg(String st) {
        log.log(Level.INFO, "ChatUI: writing " + st);
        try {
            tx.setText(tx.getText() + "\n" + st);
        } catch (Exception e) {
        }

    }

    /**
     * this method will get the new vector en update the user screen
     * Author Kamil
     *
     * @param vector the vector with all the clients
     * @throws RemoteException when a communication-related exception has occurred during the execution of a remote method
     */
    public void updateUsers(Vector vector) throws RemoteException {
        DefaultListModel listModel = new DefaultListModel();
        if (vector != null) {
            log.log(Level.INFO, "ChatUI: updating users");
            for (int i = 0; i < vector.size(); i++) {
                try {
                    String tmp = ((IChatClient) vector.get(i)).getName();
                    listModel.addElement(tmp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        lst.setModel(listModel);
    }


    /**
     * this method will create the GUI and the necessary events
     * Author Kamil
     */
    public void createFormAndEvents() {
        log.log(Level.INFO, "ChatUI: creating form");
        frame = new JFrame("Group Chat Defusal Squad");
        JPanel main = new JPanel();
        JPanel top = new JPanel();
        JPanel cn = new JPanel();
        JPanel bottom = new JPanel();
        ip = new JTextField();
        tf = new JTextField();
        name = new JTextField();
        tx = new JTextArea();
        tx.setEditable(false);
        connect = new JButton("Connect");
        JButton sendTextButton = new JButton("Send");
        lst = new JList();
        main.setLayout(new BorderLayout(5, 5));
        top.setLayout(new GridLayout(1, 0, 5, 5));
        cn.setLayout(new BorderLayout(4, 5));
        bottom.setLayout(new BorderLayout(5, 5));
        top.add(new JLabel("Your name: "));
        top.add(name);
        top.add(new JLabel("Server Address: "));
        top.add(ip);
        top.add(connect);
        cn.add(new JScrollPane(tx), BorderLayout.CENTER);
        cn.add(lst, BorderLayout.EAST);
        bottom.add(tf, BorderLayout.CENTER);
        bottom.add(sendTextButton, BorderLayout.EAST);
        main.add(top, BorderLayout.NORTH);
        main.add(cn, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);
        main.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Event for the connect button
        connect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (connected) {
                    try {
                        logOut();
                        frame.setVisible(false);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ChatUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NotBoundException ex) {
                        Logger.getLogger(ChatUI.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(ChatUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        doConnect();
                    } catch (RemoteException ex) {
                        Logger.getLogger(ChatUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        // event for the send text button
        sendTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sendText();
            }
        });

        tf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendText();
            }
        });

        //event for closing the form
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    logOut();
                } catch (RemoteException ex) {
                    System.out.println("Cant close.");
                } catch (NotBoundException ex) {
                    Logger.getLogger(ChatUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ChatUI.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    frame.setVisible(false);
                }
            }
        });
        // set the frame and make it visible
        frame.setContentPane(main);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    /**
     * this method will close the GUI
     * Author Kamil
     */
    public boolean closeChatApp() {
        log.log(Level.INFO, "ChatUI: closing chatapp");
        try {
            frame.setVisible(false);
            return true;
        } catch (Exception e) {
            log.log(Level.INFO, "ChatUI: cannot close chatapp");
            return false;
        }
    }
}

