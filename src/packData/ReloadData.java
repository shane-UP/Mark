package packData;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.Receiver;
import javax.xml.soap.AttachmentPart;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import packUI.MainFrame;
import packUI.MyFrame;

public class ReloadData {
	
	public static ArrayList<String> str1;
	public static ArrayList<String> rMarkL;
	
	
	public ReloadData(String txtPath) throws IOException{

	}
	
	public static void reLoadT() throws IOException{
		
		String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
        File file = new File(txtPath);
        FileReader read = new FileReader(file);
        BufferedReader br = new BufferedReader(read);
				
        String line = br.readLine();
		
//        System.out.println("newOnePicName:"+MyFrame.newOnePicName);
        String tmp[];
        String FName = null;
//        if(!MainFrame.workerType){
//        	FName = ModelProcess.curFilename;
//        }
//        else
//        {
        	FName = MyFrame.newOnePicName;
//        }
        if(line != null){
        	
        	while(line != null && !line.split(" ")[0].equals(FName))
            {
    			line = br.readLine();
            }
        }
        
        if(line != null && line.split(" ")[0].equals(FName)){
        	String temp[] = line.split(" ");
          System.out.println("查看测试:"+line);
          for(int i = 2;i<temp.length;i+=11)
          {
          	
              String obj_name = temp[i];
              String obj_color = temp[i+1];
              String obj_pose = temp[i+2];
              
              String obj_trunc = temp[i+3];
              String obj_cover = temp[i+4];
              String obj_diff = temp[i+5];
              String obj_shad = temp[i+6];
              
              String xmin = temp[i+7];
              String ymin = temp[i+8];
              String xmax = temp[i+9];
              String ymax = temp[i+10];
              
              int xstart = Integer.parseInt(xmin);
              int ystart = Integer.parseInt(ymin);
              int w = Integer.parseInt(xmax) - Integer.parseInt(xmin);
              int h = Integer.parseInt(ymax) - Integer.parseInt(ymin);
              
              Rectangle rect = new Rectangle();
              rect.setBounds(xstart, ystart, w, h);
              rMarkL.add(obj_name+" "+obj_color+" "+obj_pose+" "+obj_trunc+" "+obj_cover+" "+obj_diff+" "+obj_shad+" ");
              MyFrame.rList.add(rect);
          }
        }
        read.close();
        br.close();
	}
	
	public static boolean canCheck() throws IOException{
		String[] tt;
		String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
        File file = new File(txtPath);
         
        BufferedReader br = new BufferedReader(new FileReader(file));
				
        String line = br.readLine();
		
 //       System.out.println("检查弹出框newOnePicName:"+MyFrame.newOnePicName);
        if(line != null){
        	while(line != null && !line.split(" ")[0].equals(MyFrame.newOnePicName))
            {
    			line = br.readLine();
            }
        }
        br.close();
        if(line != null && line.split(" ")[0].equals(MyFrame.newOnePicName)){
          return true;
        }
        return false;
	}
	
	public static boolean specialCheck(int pictrueNum) throws IOException{
		
		String filename = "";
		filename = MyFrame.openKey.getOneName(pictrueNum);
		String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
        File file = new File(txtPath);
         
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String line = br.readLine();
		
        if(line != null){
        	while(line != null && !line.split(" ")[0].equals(filename))
            {
    			line = br.readLine();
            }
        }
        String aTmp[] = null;
        if(line != null){
        	aTmp = line.split(" ");
        }
        	
        br.close();
        if(line != null && line.split(" ")[0].equals(filename)){
        	if(aTmp.length > 1)
        	{
        		return true;
        	}
          
        }
        return false;
	}
	
	public static void copyList(){
		
	}
	
	public static void changeObj(){
		
		
	}
	
	
	public static void showRLD(){
		
		
	}
	
}
