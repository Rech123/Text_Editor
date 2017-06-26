package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by rashed on 1/31/2017.
 */
public class FileBrowser extends JPanel implements ActionListener{
    JLabel label = new JLabel("file list");
    JButton newfile = new JButton("New File");
    JTextField newfiletf = new JTextField(10);
    JButton open = new JButton("Open");
    JButton delete = new JButton("delete");




    ButtonGroup bg ;
    File directory ;

    public FileBrowser(String dir){


        directory = new File(dir);
        directory.mkdir();
        JPanel filelist =new JPanel(new GridLayout(directory.listFiles().length+3,1));
        filelist.add(label);
        bg =new ButtonGroup();

        for(File file : directory.listFiles())
        {
            JRadioButton radio = new JRadioButton(file.getName());
            //adding this for
            radio.setActionCommand(file.getName());
            //add this file to uttongroup
            bg.add(radio);
            filelist.add(radio);
        }
        JPanel newpanel =new JPanel();
        newpanel.setLayout( new GridLayout(3,2));
        newpanel.add(newfiletf);
        newpanel.add(newfile);

        newfile.addActionListener(this);
        open.addActionListener(this);
        delete.addActionListener(this);

        filelist.add(open);
        filelist.add(delete);
        filelist.add(newpanel);


        add(filelist);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Login login =(Login)getParent();
        if(e.getSource()==open){

            login.add(new Editor(directory.getName() +"\\"+bg.getSelection().getActionCommand()),"editor");
            login.cl.show(login,"editor");
        }
        if(e.getSource()==delete){
            String s =bg.getSelection().getActionCommand();
            for (File file :directory.listFiles()){
                if(file.getName().equals(s)){
                    file.delete();
                    System.out.println("file deleted");

                }
            }

        }
        if(e.getSource()==newfile){
            String file =directory.getName()+"\\"+newfiletf.getText()+".txt";
            if(newfiletf.getText().length() > 0 && !(new File(file)).exists())
            {
                login.add(new Editor(file),"editor");
                login.cl.show(login,"editor");

            }
        }

    }
}
