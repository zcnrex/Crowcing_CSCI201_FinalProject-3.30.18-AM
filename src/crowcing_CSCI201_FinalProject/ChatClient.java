//package crowcing_CSCI201_FinalProject;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.Socket;
//import java.util.Observable;
//import java.util.Observer;
//
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.SwingUtilities;
//
//
//// Class to manage Client chat Box.
//public class ChatClient {
//
//    /** Chat client access */
//    static class ChatAccess extends Observable {
//        private Socket socket;
//        private OutputStream outputStream;
//
//        @Override
//        public void notifyObservers(Object arg) {
//            super.setChanged();
//            super.notifyObservers(arg);
//        }
//
//        /** Create socket, and receiving thread */
//        public ChatAccess(String server, int port) throws IOException {
//            socket = new Socket(server, port);
//            outputStream = socket.getOutputStream();
//
//            Thread receivingThread = new Thread() {
//                @Override
//                public void run() {
//                    try {
//                        BufferedReader reader = new BufferedReader(
//                                new InputStreamReader(socket.getInputStream()));
//                        String line;
//                        while ((line = reader.readLine()) != null)
//                            notifyObservers(line);
//                    } catch (IOException ex) {
//                        notifyObservers(ex);
//                    }
//                }
//            };
//            receivingThread.start();
//        }
//
//        private static final String CRLF = "\r\n"; // newline
//
//        /** Send a line of text */
//        public void send(String text) {
//            try {
//                outputStream.write((text + CRLF).getBytes());
//                outputStream.flush();
//            } catch (IOException ex) {
//                notifyObservers(ex);
//            }
//        }
//
//        /** Close the socket */
//        public void close() {
//            try {
//                socket.close();
//            } catch (IOException ex) {
//                notifyObservers(ex);
//            }
//        }
//    }
//
//    /** Chat client UI */
//    static class ChatFrame extends JFrame implements Observer {
//
//        private JTextArea textArea;
//        private JTextField inputTextField;
//        private JButton sendButton;
//        private ChatAccess chatAccess;
//
//        public ChatFrame(ChatAccess chatAccess) {
//            this.chatAccess = chatAccess;
//            chatAccess.addObserver(this);
//            buildGUI();
//        }
//
//        /** Builds the user interface */
//        private void buildGUI() {
//        	setLocation(100, 100);
//    		setSize(800, 630);
//    	
//    		JPanel outerPanel = new JPanel();
//    		outerPanel.setLayout(null);
//
//    		JPanel rightPanel = new JPanel();
//    		rightPanel.setLayout(new BorderLayout());
//
//            textArea = new JTextArea(20, 50);
//            textArea.setEditable(false);
//            textArea.setLineWrap(true);
//    		
//    		JScrollPane jsp = new JScrollPane(textArea);
//    		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//    		jsp.setMaximumSize(new Dimension(200, 10000));
//    		rightPanel.add(jsp, BorderLayout.CENTER);
//    		rightPanel.setBounds(600, 0, 200, 600);
//
//            Box box = Box.createHorizontalBox();
//            rightPanel.add(box, BorderLayout.SOUTH);
//            inputTextField = new JTextField();
//            sendButton = new JButton("Send");
//            box.add(inputTextField);
//            box.add(sendButton);
//
//            outerPanel.add(rightPanel);
//            add(outerPanel);
//            // Action for the inputTextField and the goButton
//            ActionListener sendListener = new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    String str = inputTextField.getText();
//                    if (str != null && str.trim().length() > 0)
//                        chatAccess.send(str);
//                    inputTextField.selectAll();
//                    inputTextField.requestFocus();
//                    inputTextField.setText("");
//                }
//            };
//            inputTextField.addActionListener(sendListener);
//            sendButton.addActionListener(sendListener);
//
//            this.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    chatAccess.close();
//                }
//            });
//        }
//
//        /** Updates the UI depending on the Object argument */
//        public void update(Observable o, Object arg) {
//            final Object finalArg = arg;
//            SwingUtilities.invokeLater(new Runnable() {
//                public void run() {
//                    textArea.append(finalArg.toString());
//                    textArea.append("\n");
//                }
//            });
//        }
//    }
//
//    public static void main(String[] args) {
//        String server = args[0];
//        int port =2232;
//        ChatAccess access = null;
//        try {
//            access = new ChatAccess(server, port);
//        } catch (IOException ex) {
//            System.out.println("Cannot connect to " + server + ":" + port);
//            ex.printStackTrace();
//            System.exit(0);
//        }
//        JFrame frame = new ChatFrame(access);
//        frame.setTitle("Chatroom - connected to " + server + ":" + port);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
//        frame.setVisible(true);
//    }
//}