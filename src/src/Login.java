package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;
import javax.swing.*;


public class Login extends JPanel implements ActionListener  {
    JLabel user =new JLabel("user name");
    JTextField usertf =new JTextField();

    JLabel password =new JLabel("password");
    JPasswordField passwordtf =new JPasswordField();
    JPanel loginp =new JPanel(new GridLayout(3,2));
  JPanel panel =new JPanel();
    JButton login =new JButton("Login");
    JButton Rigsiter =new JButton("Rigster");
CardLayout cl ;
    Login(){
        setLayout(new CardLayout());
        loginp.add(user);
        loginp.add(usertf);
        loginp.add(password);
        loginp.add(passwordtf);
        login.addActionListener(this);
        Rigsiter.addActionListener(this);
        loginp.add(login);
        loginp.add(Rigsiter);
      panel.add(loginp);
       add(panel,"login");
        cl =(CardLayout)getLayout();

    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==Rigsiter){


            add(new Rigsiter(),"r");
            cl.show(this,"r");

        }
        if(e.getSource()==login){
            try {
                BufferedReader bf = new BufferedReader(new FileReader("passwords.txt"));
                String pass =null;
                String line =bf.readLine();
                while (line !=null){
                    StringTokenizer st =new StringTokenizer(line);
                    if(usertf.getText().equals(st.nextToken())){
                        pass =st.nextToken();
                        System.out.println(pass);
                    }
                    line =bf.readLine();
                }try{
                    //encrupt the password to check if it is equal the oassword that already stored n password.txt
                MessageDigest md =MessageDigest.getInstance("SHA-256");
                md.update(new String(passwordtf.getPassword()).getBytes());
                byte [] bytedata =md.digest();
                StringBuffer sb = new StringBuffer();
                for (int i=0;i<bytedata.length;i++) {
                    sb.append(Integer.toString((bytedata[i] & 0xFF) + 0x100, 16).substring(1));
                }if (pass.equals(sb.toString()))
                    {
                        System.out.println("logged in as :"+usertf.getText());
                        add(new FileBrowser(usertf.getText()),"fb");
                        cl.show(this,"fb");

                    }
                }
                catch (NoSuchAlgorithmException e1) {}



                } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }



    public static  void main(String [] arags){
        JFrame frame =new JFrame("Rashed Text Edtitor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        Login login =new Login();
        frame.add(login);
        frame.setVisible(true);
    }

}