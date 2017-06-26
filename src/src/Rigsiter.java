package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Created by rashed on 1/12/2017.
 */
public class Rigsiter extends JPanel implements ActionListener {
    JLabel userl = new JLabel("choose a user name");
    JTextField usertf =new JTextField();
    JLabel passl = new JLabel("password");
    JPasswordField passf =new JPasswordField();
    JLabel passc =new JLabel("confirm password");
    JPasswordField passcf = new JPasswordField();
    JButton rigster =new JButton("rigster");
    JButton Back =new JButton("Back");
    public Rigsiter(){
        JPanel loginp =new JPanel();
        loginp.setLayout( new GridLayout(4,2));
        loginp.add(userl);
        loginp.add(usertf);
        loginp.add(passl);
        loginp.add(passf);
        loginp.add(passc);
        loginp.add(passcf);

        rigster.addActionListener(this);
        Back.addActionListener(this);
        loginp.add(rigster);
        loginp.add(Back);
        add(loginp);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Back){
           Login login =(Login)getParent();
            login.cl.show(login,"login");
        }
         if (e.getSource()==rigster && usertf.getText().length()>0 && passf.getPassword().length>0){
            String pass =new String(passf.getPassword());
            String confirm =new String(passcf.getPassword());
            System.out.println(" iam here");
            if(confirm.equals(pass)){
                try{
                   // File infile =new File("\\C:\\Users\\rashed\\IdeaProjects\\Editor\\passwords.txt");
                   // File outfile =new File("\\C:\\Users\\rashed\\IdeaProjects\\Editor\\passwords.txt");

                   ;
                BufferedReader input =new BufferedReader(new FileReader("passwords.txt"));
                    String line =input.readLine();
                    while(line !=null){
                        StringTokenizer st =new StringTokenizer(line);
                        if(usertf.getText().equals(st.nextToken())){
                            System.out.println("the user is already exist");
                                return;
                        }

                        line =input.readLine();}
                    input.close();
                    // iof the user is not exist then encrapt his password and write to file //
                    try {

                        MessageDigest md =MessageDigest.getInstance("SHA-256");
                        md.update(pass.getBytes());
                        byte [] bytedata =md.digest();
                        StringBuffer sb = new StringBuffer();
                        for (int i=0;i<bytedata.length;i++){
                            sb.append(Integer.toString((bytedata[i] &0xFF)+ 0x100, 16).substring(1));
                        }

                        BufferedWriter output =new BufferedWriter(new FileWriter("passwords.txt",true));//True means we are appending to file
                        output.write(" "+usertf.getText() +" "+ sb.toString()+" \n");

                        output.close();
                        System.out.println("i closed the file");
                        Login login =(Login)getParent();
                        login.cl.show(login,"login");}

                        catch(FileNotFoundException e2){
                            e2.printStackTrace();
                        }
                     catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();
                    }


                }catch (IOException ioe){
                    ioe.printStackTrace();
                }}
        }}}


