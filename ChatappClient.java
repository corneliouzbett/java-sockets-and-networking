package chatappclient;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.net.Socket;
import javax.swing.*;
public class ChatappClient implements Runnable {

    private static JTextArea messageArea;
    private JButton send;
    private JTextField textField;

    public void run() {
        //chat gui.
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLUE);

        messageArea = new JTextArea();
        messageArea.setBounds(20, 20, 300, 200);
        textField = new JTextField();
        textField.setBounds(20, 250, 200, 30);
        send = new JButton("send");
        send.setBounds(270, 250, 90, 30);

        frame.add(textField);
        frame.add(send);
        frame.add(messageArea);

        try {
            Socket skt = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            System.out.print("Received string: ");

            while (!in.ready()) {
            }
            messageArea.append("received: " + in.readLine());

            System.out.print("'\n");

            in.close();
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new ChatappClient());

    }
}
