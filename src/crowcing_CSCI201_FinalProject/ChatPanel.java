package crowcing_CSCI201_FinalProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

//import crowcing_CSCI201_FinalProject.ChatClient.ChatAccess;

//ChatPanel has a fixed position and size on the right of the screen
public class ChatPanel extends JPanel implements Observer{

	private JTextArea textArea;
    private JTextField inputTextField;
    private JButton sendButton;
    private ChatAccess chatAccess;

   /* public ChatPanel(ChatAccess chatAccess) {
        this.chatAccess = chatAccess;
        chatAccess.addObserver(this);
        buildGUI();
    }*/
    static class ChatAccess extends Observable {
        private Socket socket;
        private OutputStream outputStream;

        @Override
        public void notifyObservers(Object arg) {
            super.setChanged();
            super.notifyObservers(arg);
        }

        /** Create socket, and receiving thread */
        public ChatAccess(String server, int port) throws IOException {
            socket = new Socket(server, port);
            outputStream = socket.getOutputStream();

            Thread receivingThread = new Thread() {
                @Override
                public void run() {
                    try {
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null)
                            notifyObservers(line);
                    } catch (IOException ex) {
                        notifyObservers(ex);
                    }
                }
            };
            receivingThread.start();
        }

        private static final String CRLF = "\r\n"; // newline

        /** Send a line of text */
        public void send(String text) {
            try {
                outputStream.write((text + CRLF).getBytes());
                outputStream.flush();
            } catch (IOException ex) {
                notifyObservers(ex);
            }
        }

        /** Close the socket */
        public void close() {
            try {
                socket.close();
            } catch (IOException ex) {
                notifyObservers(ex);
            }
        }
    }
    
    
    public ChatPanel() {
        String server = Crowcing.ipAdress;
//        String server = "10.120.112.100";
        int port =2232;
       // ChatAccess access = null;
        try {
        	chatAccess = new ChatAccess(server, port);
        } catch (IOException ex) {
            System.out.println("Cannot connect to " + server + ":" + port);
            ex.printStackTrace();
            System.exit(0);
        }
        
        //this.chatAccess = chatAccess;
        chatAccess.addObserver(this);
        buildGUI();
    }
    

    /** Builds the user interface */
    private void buildGUI() {
    	setLocation(100, 100);
		setSize(200, 600);
	
		JPanel outerPanel = new JPanel();
		//outerPanel.setLayout(null);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());

        textArea = new JTextArea(20, 16);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
		
		JScrollPane jsp = new JScrollPane(textArea);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setMaximumSize(new Dimension(200, 10000));
		rightPanel.add(jsp, BorderLayout.CENTER);
		//rightPanel.setBounds(600, 0, 200, 600);

        Box box = Box.createHorizontalBox();
        rightPanel.add(box, BorderLayout.SOUTH);
        inputTextField = new JTextField();
        sendButton = new JButton("Send");
        box.add(inputTextField);
        box.add(sendButton);

        rightPanel.setPreferredSize(new Dimension(180,550));
        outerPanel.add(rightPanel);
        //add(rightPanel);
        add(outerPanel);
        // Action for the inputTextField and the goButton
        ActionListener sendListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             	String userName=LoginPanel.userName;
            	System.out.println(userName+"  1111");
                String str = inputTextField.getText();
                if (str != null && str.trim().length() > 0)
                	//gaidong
                    chatAccess.send("<"+userName+">: "  + str);
                inputTextField.selectAll();
                inputTextField.requestFocus();
                inputTextField.setText("");
                textArea.append("<"+userName+">: "  + str);
                textArea.append("\n");

            }
        };
        inputTextField.addActionListener(sendListener);
        sendButton.addActionListener(sendListener);

       /* this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                chatAccess.close();
            }
        });*/
    }

    /** Updates the UI depending on the Object argument */
    public void update(Observable o, Object arg) {
        final Object finalArg = arg;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//gaidong
            	String line = finalArg.toString();
            	if(line.substring(0,1).equals("<")){
	                textArea.append(line);
	                textArea.append("\n");
            	}
            }
        });
    }
}
