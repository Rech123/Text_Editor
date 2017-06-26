package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by rashed on 2/1/2017.
 */
public class Editor extends JPanel implements ActionListener{
    JScrollPane editorScrollPane;
    JButton save =new JButton("save");
    JButton savec =new JButton("savec");
    JButton find = new JButton("find");
    JTextField findtf =new JTextField(5);
    JTextArea text =new JTextArea(40,100);
    File file;
    public Editor(String s){
        file =new File(s);
        save.addActionListener(this);
        savec.addActionListener(this);
        find.addActionListener(this);
        if(file.exists()){
            try {
                BufferedReader input = new BufferedReader(new FileReader(file));
                String line =input.readLine();
                while (line != null)
                {
                    text.append(line+"\n");
                    //why this kine of code
                    line = input.readLine();
                }
                input.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        add(save);
        add(savec);
        add(find);
        add(findtf);
        add(text);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FileWriter out = new FileWriter(file);
            out.write(text.getText());
            out.close();
            if(e.getSource()==savec){
                Login login =(Login)getParent();
                login.cl.show(login,"fb");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if(e.getSource()==find){

        }
    }
}
