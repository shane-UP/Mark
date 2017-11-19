package packData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import packUI.FileName;
import packUI.MainFrame;
import packUI.MyFrame;
import packUI.MyPanelLeTop;
import packUI.MyPanelRight;
import packUI.PanelMid;

public class DataProcess {
	
	public static String pathRoot = "";
	public static String testTxt = "";
	public static String ttemp = "";
	public static String pathLogos;
	public static String pathImgSM;
	public static String pathData;
	public static String pathAnno;
	public static String pathJPEGImages;
	
	public static String back_pathLogos;
	public static String back_pathImgSM;
	public static String back_pathData;
	public static String back_pathAnno;
	public static String back_pathJPEGImages;
	
	
	public static ArrayList<String> caStr;
//	public static String testTxt = "";
	
	public static void txtToXML(String txtPath) throws IOException
    {
		
//		File fileName = new File(MyFrame.r +"\\"+ModelProcess.folderName+"_MPhoto\\logos\\Annotations\\")
		
		String newFileName = "";
		
		String[] tt;
		
        File file = new File(txtPath);
         
        Document document = DocumentHelper.createDocument();
         
         
        Element annotation = document.addElement("annotation");
         
        FileReader read = new FileReader(file);
        BufferedReader br = new BufferedReader(read);
	
         
        String line = null;
			line = br.readLine();
	
        Element folder = annotation.addElement("folder");
        Element filename = annotation.addElement("filename");
        
        
        while(line != null && !line.split(" ")[0].equals(MyFrame.onePicName))
        {
        	line = br.readLine();
        }
        if( line !=null ){
        	String temp[] = line.split(" ");
            folder.setText("logos");
            filename.setText(temp[0]);
            
            
            ttemp = temp[0];
            tt = ttemp.split("\\.");
//            String subName = ttemp.substring(0, 6);
            String subName = tt[0];
//            System.out.println(subName);
            newFileName = subName+".xml";
            testTxt = subName;
            
            Element source = annotation.addElement("source");
            Element database = source.addElement("database");
            Element annotation_source = source.addElement("source");
            Element image = source.addElement("image");
//            Element flickrid = source.addElement("flickrid");
            
            database.setText("The logs Database");
            annotation_source.setText("The logs Database");
            image.setText("trafic video of Qingdao");
//            flickrid.setText("0");
            
            Element owner = annotation.addElement("owner");
//            Element flickrid_owner = owner.addElement("flickrid");
            Element name = owner.addElement("name");
            
//            flickrid_owner.setText("I don't know");
            name.setText("Qingdao University");
            
            Element marker = annotation.addElement("marker");
            Element marker_name = marker.addElement("name");
            marker_name.setText(MainFrame.userID);
            
            Element size = annotation.addElement("size");
            Element width = size.addElement("width");
            Element height = size.addElement("height");
            Element depth = size.addElement("depth");
            
            width.setText(PanelMid.ImgWidth+"");
            height.setText(PanelMid.ImgHeight+"");
            depth.setText("3");
            
            Element segmented = annotation.addElement("segmented");
            segmented.setText("0");
            
      
            	
                for(int i = 2;i<temp.length;i+=11)
                {
                	Element object = annotation.addElement("object");
                	Element obj_name = object.addElement("name");
                	Element obj_pose = object.addElement("pose");
                	Element obj_color = object.addElement("color");
                	Element obj_truncated = object.addElement("truncated");
                	Element obj_area = object.addElement("area");
                	Element obj_difficult = object.addElement("difficult");
                	Element obj_shadow = object.addElement("shadow");
                	Element obj_bndbox = object.addElement("bndbox");
                	Element xmin = obj_bndbox.addElement("xmin");
                	Element ymin = obj_bndbox.addElement("ymin");
                	Element xmax = obj_bndbox.addElement("xmax");
                	Element ymax = obj_bndbox.addElement("ymax");
                    obj_name.setText(temp[i]);              
                    obj_color.setText(temp[i+2]);
                    obj_pose.setText(temp[i+1]);
                    obj_truncated.setText(temp[i+3]);
                    obj_area.setText(temp[i+4]);
                    obj_difficult.setText(temp[i+5]);
                    obj_shadow.setText(temp[i+6]);
                    xmin.setText(temp[i+7]);
                    ymin.setText(temp[i+8]);
                    xmax.setText(temp[i+9]);
                    ymax.setText(temp[i+10]);
                }
                Writer filewriter;
            	
    			filewriter = new FileWriter(pathAnno + "\\"+newFileName);
    			XMLWriter xmlWriter = new XMLWriter(filewriter);
    	        xmlWriter.write(document);
    	        xmlWriter.close();
    		
            read.close();
            br.close();
        }
         
    }
	
	public static void dataToTxt(String txtData) throws IOException
	{
//		File file = new File(pathImgSM +"\\test.txt");
//        FileWriter writer;
//			writer = new FileWriter(file, true);
//			writer.write(txtData+System.getProperty("line.separator"));
//	        writer.close();
//	
        
	}
	public static void dataToTrainvalCH(String txtData) throws IOException
	{
		
		
		String path = "";
		if(MainFrame.workerType)
//			path = back_pathImgSM +"\\trainval.txt";
			;
		else
			path = pathImgSM +"\\trainval.txt";
		File file = new File(path);
        FileWriter writer;
			writer = new FileWriter(file);
			writer.write(txtData);
	        writer.close();
	
        
	}
	public static void dataToTrainCH(String txtData) throws IOException
	{
		
		
		String path = "";
		if(MainFrame.workerType)
//			path = back_pathImgSM +"\\train.txt";
			;
		else
			path = pathImgSM +"\\train.txt";
			
		File file = new File(path);
        FileWriter writer;
			writer = new FileWriter(file);
			writer.write(txtData);
	        writer.close();
	
        
	}
	public static void dataToValCH(String txtData) throws IOException
	{
		String path = "";
		if(MainFrame.workerType)
//			path = back_pathImgSM +"\\val.txt";
			;
		else
			path = pathImgSM +"\\val.txt";
		File file = new File(path);
        FileWriter writer;
			writer = new FileWriter(file);
			writer.write(txtData);
	        writer.close();
	
        
	}
	
	public static void dataToCache() throws IOException
	{
		int i;
		String strCache = "";
		strCache = MyFrame.newOnePicName +" "+MainFrame.userID+ " ";
		
		for(i = 0; i < MyPanelRight.markedR.size(); i++)
		{
			strCache = strCache+MyPanelRight.markedR.get(i) + MyFrame.listS.get(i)+" ";
		}
		if(i!=0)
		{
			strCache = strCache+System.getProperty("line.separator");
			File file = new File(pathData +"\\imgCache.txt");
//			File file = new File("D:\\SaveData\\imgCache.txt");
	        FileWriter writer;
			writer = new FileWriter(file, true);
			writer.write(strCache);
	        writer.close();
		}
			
		
	}
	
	public static void changeToCache() throws IOException
	{
		
		
		caStr = new ArrayList<>();
		String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
        
//		checkXml(new File(pathAnno));
		
		File file = new File(txtPath);
		FileReader read = new FileReader(file);
		BufferedReader br = new BufferedReader(read);
        
		
		
		
//        writer = new FileWriter(file,true);
        String line = br.readLine();
        
//      System.out.println("Read is : " + line);
        
        int i = 0;
		while(line != null)
		{
			caStr.add(line);
//			System.out.println("caStr :"+caStr.get(i)+"  i = "+i);
			line = br.readLine();
			i++;
		}
		
//		FileOutputStream testfile = new FileOutputStream(txtPath);
//		testfile.write(new String("").getBytes());
		String ttStr = "";
		String sTmp = "";
		boolean delete = false;
		for(int m=0; m<caStr.size(); m++)
		{
			if(caStr.get(m).split(" ")[0].equals(MyFrame.newOnePicName))
			{
				if(FileName.deleteAll)
				{
					delete = true;
				}
				else if(MyPanelRight.hChange)
				{
					sTmp += MyFrame.newOnePicName +" "+ MainFrame.userID+" ";
					String sTp[] = caStr.get(m).split(" ");
					for(int n=2; n<sTp.length; n+=11 )
					{
						if((n/11) == MyFrame.ObjectNum){
							sTmp += ReloadData.rMarkL.get(MyFrame.ObjectNum) + sTp[n+7] + " " + sTp[n+8] + " "+sTp[n+9]+" "+sTp[n+10]+" ";
						}
						else
						{
							sTmp += sTp[n] + " " + sTp[n+1] + " " + sTp[n+2] + " " + sTp[n+3] + " " + sTp[n+4] + " " + sTp[n+5] +" " + sTp[n+6] + " " + sTp[n+7] + " " + sTp[n+8] + " "+sTp[n+9]+" "+sTp[n+10]+" ";
						}
					}
				}
				else if(MyFrame.afterPaint && !MyPanelRight.hChange && !MyFrame.deletePaint)
				{
					sTmp += caStr.get(m)+MyPanelRight.markedR.get(0) + MyFrame.listS.get(0)+" ";
					MyFrame.cleatAllList();
				}
				else if(MyFrame.deletePaint && !MyFrame.afterPaint && !MyPanelRight.hChange)
				{
					String sTp[] = caStr.get(m).split(" ");
					if(sTp.length > 13)
						sTmp += MyFrame.newOnePicName +" "+ MainFrame.userID + " ";
					else{
							delete = true;
							Object obj = null;
							if(MyPanelLeTop.fC.getComponentCount()>MyFrame.PictureNum)
							obj = MyPanelLeTop.fC.getComponent(MyFrame.PictureNum); 
							if(obj instanceof FileName){
								FileName FN = (FileName)obj;
								FN.clearSpecial();
							}
					}
					for(int n=2; n<sTp.length; n+=11 )
					{
						if((n/11) == MyFrame.ObjectNum){
							
						}
						else
						{
							sTmp += sTp[n] + " " + sTp[n+1] + " " + sTp[n+2] + " " + sTp[n+3] + " " + sTp[n+4] + " " + sTp[n+5] +" " + sTp[n+6] + " " + sTp[n+7] + " " + sTp[n+8] + " "+sTp[n+9]+" "+sTp[n+10]+" ";
						}
					}
				}
				
				if(!delete)
				{
					sTmp += System.getProperty("line.separator");
				}
				else delete = false;
			}
			else
			{
				System.out.println("这里是不是没运行");
				sTmp += caStr.get(m) + System.getProperty("line.separator");
				System.out.println(sTmp);
			}
			
		}
		FileWriter writer = new FileWriter(file);
		writer.write(sTmp);
	    writer.close();
	    read.close();
	    br.close();

	}
	
	public static void mdirBack(){		//留作研究备用
		String back_pathRoot = MyFrame.r +"\\"+ModelProcess.folderName+"_MPhoto-try";
//		System.out.println("pathRoot:" + pathRoot);
		File f1 = new File(back_pathRoot);
		if(!f1.exists()){
		f1.mkdirs();
		}
		
		back_pathData = back_pathRoot + "\\data";
		File f2 = new File(back_pathData);
		if(!f2.exists()){
		f2.mkdirs();
		} 
		
		back_pathLogos = back_pathRoot+ "\\logos";
		File f3 = new File(back_pathLogos);
		if(!f3.exists()){
		f3.mkdirs();
		} 
		
		
		back_pathAnno = back_pathLogos + "\\Annotations";
		File f4 = new File(back_pathAnno);
		if(!f4.exists()){
		f4.mkdirs();
		} 
		
		back_pathImgSM = back_pathLogos + "\\ImageSets\\Main";
		File f5 = new File(back_pathImgSM);
		if(!f5.exists()){
		f5.mkdirs();
		} 
		
		back_pathJPEGImages = back_pathLogos + "\\JPEGImages";
		File f6 = new File(back_pathJPEGImages);
		if(!f6.exists()){
		f6.mkdirs();
		} 
		
		
		// fileName表示你创建的文件名；为txt类型；
		String fileImgC="ImgCache.txt";
		File file1 = new File(f2,fileImgC);
		if(!file1.exists()){
			try {
			file1.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		String filetest="test.txt";
		File file2 = new File(f5,filetest);
		if(!file2.exists()){
			try {
			file2.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		
		String filetrain="train.txt";
		File file3 = new File(f5,filetrain);
		if(!file3.exists()){
			try {
			file3.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		String filetrainval="trainval.txt";
		File file4 = new File(f5,filetrainval);
		if(!file4.exists()){
			try {
			file4.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		String fileval="val.txt";
		File file5 = new File(f5,fileval);
		if(!file5.exists()){
			try {
			file5.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
	
	public static void mdir(){
		//path表示你所创建文件的路径
		pathRoot = MyFrame.r +"\\"+ModelProcess.folderName+"_MPhoto";
//		System.out.println("pathRoot:" + pathRoot);
		File f1 = new File(pathRoot);
		if(!f1.exists()){
		f1.mkdirs();
		}
		
		pathData = pathRoot + "\\data";
		File f2 = new File(pathData);
		if(!f2.exists()){
		f2.mkdirs();
		} 
		
		pathLogos = pathRoot+ "\\logos";
		File f3 = new File(pathLogos);
		if(!f3.exists()){
		f3.mkdirs();
		} 
		
		
		pathAnno = pathLogos + "\\Annotations";
		File f4 = new File(pathAnno);
		if(!f4.exists()){
		f4.mkdirs();
		} 
		
		pathImgSM = pathLogos + "\\ImageSets\\Main";
		File f5 = new File(pathImgSM);
		if(!f5.exists()){
		f5.mkdirs();
		} 
		
		pathJPEGImages = pathLogos + "\\JPEGImages";
		File f6 = new File(pathJPEGImages);
		if(!f6.exists()){
		f6.mkdirs();
		} 
		
		
		// fileName表示你创建的文件名；为txt类型；
		String fileImgC="ImgCache.txt";
		File file1 = new File(f2,fileImgC);
		if(!file1.exists()){
			try {
			file1.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		String filetest="test.txt";
		File file2 = new File(f5,filetest);
		if(!file2.exists()){
			try {
			file2.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		
		String filetrain="train.txt";
		File file3 = new File(f5,filetrain);
		if(!file3.exists()){
			try {
			file3.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		String filetrainval="trainval.txt";
		File file4 = new File(f5,filetrainval);
		if(!file4.exists()){
			try {
			file4.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		String fileval="val.txt";
		File file5 = new File(f5,fileval);
		if(!file5.exists()){
			try {
			file5.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		String filepass = ModelProcess.folderName+"_pass.txt";
		File file6 = new File(f1,filepass);
		if(!file6.exists()){
			try {
			file6.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		String filenopass = ModelProcess.folderName+"_no_pass.txt";
		File file7 = new File(f1,filenopass);
		if(!file7.exists()){
			try {
			file7.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		String fileNoFininsh = "Unfinish.txt";
		String fileFininsh = "Finish.txt";
		File file8 = new File(f1,fileFininsh);
		File file9 = new File(f1, fileNoFininsh);
		if(!file8.exists() && !file9.exists()){
			try {
				file9.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	
	public static void overTxtToXML(String txtPath) throws IOException
    {
		String newFileName = "";
		
		String[] tt;
		
        File file = new File(txtPath);
         
        
         
        FileReader read = new FileReader(file);
        BufferedReader br = new BufferedReader(read);
	
         
        String line = null;
			line = br.readLine();
			
        
        String lineTmp = "";
        while(line != null)
        {
        	Document document = DocumentHelper.createDocument();
	         
	         
	        Element annotation = document.addElement("annotation");
        Element folder = annotation.addElement("folder");
        Element filename = annotation.addElement("filename");
        	lineTmp = line;
        
        
        
        String temp[] = line.split(" ");
        folder.setText("logos");
        filename.setText(temp[0]);
        
        ttemp = temp[0];
        tt = ttemp.split("\\.");
//        String subName = ttemp.substring(0, 6);
        String subName = tt[0];
//        System.out.println(subName);
        newFileName = subName+".xml";
        testTxt = subName;
        
        Element source = annotation.addElement("source");
        Element database = source.addElement("database");
        Element annotation_source = source.addElement("source");
        Element image = source.addElement("image");
//        Element flickrid = source.addElement("flickrid");
        
        database.setText("The logs Database");
        annotation_source.setText("The logs Database");
        image.setText("trafic video of Qingdao");
//        flickrid.setText("0");
        
        Element owner = annotation.addElement("owner");
//        Element flickrid_owner = owner.addElement("flickrid");
        Element name = owner.addElement("name");
        
//        flickrid_owner.setText("I don't know");
        name.setText("Qingdao University");
        
        Element marker = annotation.addElement("marker");
        Element marker_name = marker.addElement("name");
        marker_name.setText(MainFrame.userID);
        
        Element size = annotation.addElement("size");
        Element width = size.addElement("width");
        Element height = size.addElement("height");
        Element depth = size.addElement("depth");
        
        width.setText(PanelMid.ImgWidth+"");
        height.setText(PanelMid.ImgHeight+"");
        depth.setText("3");
        
        Element segmented = annotation.addElement("segmented");
        segmented.setText("0");
        
        
//        	Element object = annotation.addElement("object");
//        	Element obj_name = object.addElement("name");
//        	Element obj_pose = object.addElement("pose");
//        	Element obj_color = object.addElement("color");
//        	Element obj_truncated = object.addElement("truncated");
//        	Element obj_difficult = object.addElement("difficult");
//        	Element obj_bndbox = object.addElement("bndbox");
//        	Element xmin = obj_bndbox.addElement("xmin");
//        	Element ymin = obj_bndbox.addElement("ymin");
//        	Element xmax = obj_bndbox.addElement("xmax");
//        	Element ymax = obj_bndbox.addElement("ymax");
        	
        for(int i = 2;i<temp.length;i+=11)
        {
        	Element object = annotation.addElement("object");
        	Element obj_name = object.addElement("name");
        	Element obj_pose = object.addElement("pose");
        	Element obj_color = object.addElement("color");
        	Element obj_truncated = object.addElement("truncated");
        	Element obj_area = object.addElement("area");
        	Element obj_difficult = object.addElement("difficult");
        	Element obj_shadow = object.addElement("shadow");
        	Element obj_bndbox = object.addElement("bndbox");
        	Element xmin = obj_bndbox.addElement("xmin");
        	Element ymin = obj_bndbox.addElement("ymin");
        	Element xmax = obj_bndbox.addElement("xmax");
        	Element ymax = obj_bndbox.addElement("ymax");
            obj_name.setText(temp[i]);              
            obj_color.setText(temp[i+2]);
            obj_pose.setText(temp[i+1]);
            obj_truncated.setText(temp[i+3]);
            obj_area.setText(temp[i+4]);
            obj_difficult.setText(temp[i+5]);
            obj_shadow.setText(temp[i+6]);
            xmin.setText(temp[i+7]);
            ymin.setText(temp[i+8]);
            xmax.setText(temp[i+9]);
            ymax.setText(temp[i+10]);
        }
	
//        }
         
        Writer filewriter;
	
			filewriter = new FileWriter(pathAnno + "\\"+newFileName);
			XMLWriter xmlWriter = new XMLWriter(filewriter);
	        xmlWriter.write(document);
	        xmlWriter.close();
	        filewriter.close();
		
	    	line = br.readLine();
        }    
	        
        read.close();
         br.close();
    }
	
	public static void openCheckXml()
	{
		String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
		pathAnno = pathLogos + "\\Annotations";
		boolean result = deleteDir(new File(pathAnno));
		
		
		File f4 = new File(pathAnno);
		if(!f4.exists()){
			f4.mkdirs();
		}
		
		try {
			overTxtToXML(txtPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
	
	public static void newJpegFile(){
		pathJPEGImages = pathLogos + "\\JPEGImages";
		File f6 = new File(pathJPEGImages);
		if(!f6.exists()){
		f6.mkdirs();
		} 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
