package packUI;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//import java.awt.Font;
//import java.awt.GraphicsEnvironment;
//import java.awt.Insets;
//import java.awt.Rectangle;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
	import javax.swing.Icon;
import javax.swing.ImageIcon;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JDialog;
	import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JOptionPane;

import packData.CalcExchange;
import packData.DataProcess;
import packData.ModelProcess;

public class MainFrame {
	public static MyFrame myFrame;	
	public static boolean workerType = true; 	//打开类型为标记类型还是检查类型
	public static boolean pre_worker = false;
	public static String userID = null;
	public static boolean isOpen = false;
	public static int checkSta;					//检查者的状态
	public static int checkStaNum = 1;			//检查的次数
	public static boolean numFlag = false;		//用来判断checkStaNum的状态
	public static String strS ;					//记录选择检查状态的字符串
	public static void main(String[] args) {
		
		myFrame = new MyFrame();
//		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setCheck();
		chooseCheckStatus();
		myFrame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				
				//关闭时执行的动作
				
				boolean tmpN = false;
				String txtPath11 = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
				try {
					File txtFile = new File(txtPath11);
					FileReader txtReader = new FileReader(txtFile);
					BufferedReader br = new BufferedReader(txtReader);
					String line = br.readLine();
					if(line == null)
					{
						tmpN = true;
						txtReader.close();
						br.close();
					}
					
				
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				if(tmpN)
				{
//					File txtFile = new File(DataProcess.pathData+"\\imgCache.txt");
//					boolean temp = txtFile.delete();
//					if(!temp)
//					{
//						System.gc();
//						txtFile.delete();
//					}
//					System.out.println("txtFile.delete()  : "+temp );
					DataProcess.deleteDir(new File(DataProcess.pathData));
					DataProcess.deleteDir(new File(DataProcess.pathAnno));
					DataProcess.deleteDir(new File(DataProcess.pathImgSM));
					DataProcess.deleteDir(new File(DataProcess.pathJPEGImages));
					DataProcess.deleteDir(new File(DataProcess.pathImgSM));
					DataProcess.deleteDir(new File(DataProcess.pathLogos));
					
					DataProcess.deleteDir(new File(MyFrame.r +"\\"+ModelProcess.folderName+"_MPhoto"));
					System.exit(0);
				}
				
				if(workerType)
				{
					
					DataProcess.openCheckXml();
//					try {
//						DataProcess.overTxtToXML(DataProcess.pathData+"\\imgCache.txt");
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					
					
					
					
					
					try {
//						File file1 = new File(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\data\\ImgCache.txt");
//						if(file1.exists())
//							file1.delete();
//						CalcExchange.copyFile(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt", MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\data\\ImgCache.txt");
//						
//						File file2 = new File(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\logos\\Annotations");
//						if(file2.exists())
//							CalcExchange.delDir(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\logos\\Annotations");
//						CalcExchange.FileAllCopy(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\logos\\Annotations",MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\logos\\Annotations");
					
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
				        
				        
//				        if(sTmp1 != null)
//				    	{
//				    		DataProcess.dataToValCH(sTmp1);
//				    	}
//				        if(sTmp2 != null)
//				    	{
//				    		DataProcess.dataToTrainCH(sTmp2);
//				    	}
//				        if(sTmp != null)
//				        {
//				        	DataProcess.dataToTrainvalCH(sTmp);
//				        }
						read.close();
						br.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					//检查者模式
				}
			
				System.exit(0);
			}
			
			
		});

	}
	//自定义对话框
	public static void setCheck(){
		Object[] modeType= {"标记模式","检查模式"};
		int messageType = 0;
		//Icon icon = null; 
		Icon icon = new ImageIcon(myFrame.getClass().getResource("/image/2.png"));
		int n = 0;
		
		
//		if(userID == null)
//		{
			
			n = JOptionPane.showOptionDialog(null,"请选择使用模式","模式", JOptionPane.YES_NO_OPTION, messageType ,icon, modeType, null);
//			System.out.println("tttttt");
//		}
//		
		if( n == 0){
			workerType = true;
			pre_worker = true;
			MyFrame.mPR.removeAll();
			MyPanelRight.initMarkLPR();
			userID = JOptionPane.showInputDialog("请输入标记者编号：");
			System.out.println("userID:" + userID);
			if(userID == null)
			{
				System.exit(0);
			}
			if(userID.isEmpty()){
				JOptionPane.showMessageDialog(null, "标记者编号不能为空");
				System.exit(0);
			}

			MyPanelRight.label.setText("标记者编号："+userID);
			MyPanelRight.myJB.setEnabled(false);
			MyFrame.unEnableCom();
			MyFrame.mPR.repaint();

		}
		else if(n == 1){
			workerType = false;
			pre_worker = true;
			
			MyFrame.mPR.removeAll();
			
			MyPanelRight.initCheckLPR();
			userID = JOptionPane.showInputDialog("请输入检查者编号："); 
//			System.out.println("userID:" + userID);
			if(userID == null)
			{
				System.exit(0);
			}
			if(userID.isEmpty()){
				JOptionPane.showMessageDialog(null, "检查者编号不能为空");
				System.exit(0);
			}
//			else
//			{
				MyPanelRight.label.setText("检查者编号："+userID);
				MyPanelRight.comMiss.setEnabled(false);
				MyPanelRight.allRight.setEnabled(false);
				MyFrame.mPR.repaint();
//			}
			
//			pre_worker = false;
		} 
		else{
			System.exit(0);
		}
		

		if(isOpen){
			myFrame.PictureNum = 0;
			myFrame.PictureNumF = 0;
			myFrame.ObjectNum = 0;
			myFrame.ObjectNumF = 0;
			myFrame.mPLT.openAll();
			myFrame.mPLT.repaint();
			myFrame.mPLT.changeBC();
			myFrame.mPLB.openAll();
			myFrame.mPLB.changeBC();
		}
//
	}
	
	public static void chooseCheckStatus(){		//检查者的检查状态
		if(!workerType)
		{
			Icon icon = new ImageIcon(myFrame.getClass().getResource("/image/2.png"));
			Object[] obj = {"进行新一轮检查","未完成本次检查，继续检查"};
			String s = (String) JOptionPane.showInputDialog(null,"注意：提交过文件夹才算完成一次检查:\n", "检查状态", JOptionPane.PLAIN_MESSAGE, icon, obj, "未提交过检查记录");
			
			strS = s;
			
			if(s == null)
			{
				System.exit(0);
			}
			
		}
	}
	
	public static void showMessages(String message){
		ShowMessageFrame smf = new ShowMessageFrame();
		new Thread(smf).start();
		smf.showMessage(message);
	}
}
