package packUI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import packData.CalcExchange;
import packData.DataProcess;
import packData.ModelProcess;

public class ChoseCheck {
	 JFileChooser chooser;

		// DIRECTORIES_ONLY就是只选目录
		
	 public  String r;
	 
	 public ChoseCheck() {
		// TODO Auto-generated constructor stub
		 if(MainFrame.workerType && MyFrame.haveImg)
			{
				 
		
				DataProcess.openCheckXml();
				try {
					DataProcess.overTxtToXML(DataProcess.pathData+"\\imgCache.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
//					File file1 = new File(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\data\\ImgCache.txt");
//					if(file1.exists())
//						file1.delete();
//					CalcExchange.copyFile(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt", MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\data\\ImgCache.txt");
//					
//					File file2 = new File(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\logos\\Annotations");
//					if(file2.exists())
//						CalcExchange.delDir(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\logos\\Annotations");
//					CalcExchange.FileAllCopy(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\logos\\Annotations",MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\logos\\Annotations");
				
					ArrayList<String> copyList = new ArrayList<>();
					String sTmp1 = "";
					String sTmp2 = "";
					String sTmp = "";
					
					String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
			        File file = new File(txtPath);
			        FileReader read = new FileReader(file);
			        BufferedReader br = new BufferedReader(read);
							
			        String line = br.readLine();
					while(line != null)
					{
						copyList.add(line);
						line = br.readLine();
					}
			        
			        
					for(int i = 0; i < copyList.size(); i++){
			        	if(ModelProcess.checkTrainOrVal(copyList.get(i).split(" ")[0]))
			        	{
			        		sTmp1 += copyList.get(i).split(" ")[0].split("\\.")[0]+System.getProperty("line.separator");
			        	}
			        	else
			        	{
			        		sTmp2 += copyList.get(i).split(" ")[0].split("\\.")[0]+System.getProperty("line.separator");
			        	}
			        	sTmp += copyList.get(i).split(" ")[0].split("\\.")[0]+System.getProperty("line.separator");
			        	
			        }
			        
			        
			        if(sTmp1 != null)
			    	{
			    		DataProcess.dataToValCH(sTmp1);
			    	}
			        if(sTmp2 != null)
			    	{
			    		DataProcess.dataToTrainCH(sTmp2);
			    	}
			        if(sTmp != null)
			        {
			        	DataProcess.dataToTrainvalCH(sTmp);
			        }
					read.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		 try{
			 chooser = new JFileChooser();
			 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			 int value = chooser.showOpenDialog(null);
			
			 if(value == JFileChooser.APPROVE_OPTION)
			 {
				 r =  chooser.getSelectedFile().getPath();
				 MyFrame.rChoose = r;
				 MyFrame.tempR = r;

			 }
			 else{
				 r = MyFrame.tempR;
			 }
		 }catch(NullPointerException e)
		 {
			 e.getStackTrace();
		 }
		
//		 System.out.println("rrrr: "+r);
			
	}
	 
	public String getDIR(){
		return r;
	}
}
