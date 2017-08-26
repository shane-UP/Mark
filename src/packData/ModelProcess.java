package packData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.management.ObjectName;

import packUI.FileRead;
import packUI.MainFrame;
import packUI.MyFrame;
import packUI.MyPanelRight;

public class ModelProcess {
	
	public static String folderName;
	public static int sumObject = 0;//一张图片总的对象数目 - 错的
	public static int errorObject = 0;
	public static ArrayList<String> pList = new ArrayList<>();
	public static ArrayList<String> npList = new ArrayList<>();
	
	public static ArrayList<String> spList;
	public static ArrayList<String> snpList;
	
	public static ArrayList<String> listWrongMess = new ArrayList<>();
	
	public static boolean iFlag = false;
	public static int indexM = 0;
	public static boolean errorFlag = false;
	
	public static int[] pointObj = new int[1000]; 
	public static int indexObj = 0;
	public static String fStr;
	public static int[] rObjNum = new int[1000];		//返回的对象编号
	public static String curFilename;			//当前修改的这张图片的名字
	public static int curMiss = 0;
	
	public static boolean modelSave = false;
	public static boolean haveZero = false;
	
	public static void writeTorobjnum() throws IOException{

		Arrays.fill(rObjNum, 0);
		
		String nopassFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_no_pass.txt";

		String passFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_pass.txt";
	
		File fileP = new File(passFilePath);
		FileReader readP = new FileReader(fileP);
        BufferedReader brP = new BufferedReader(readP);
		
		
        File fileNP = new File(nopassFilePath);
		FileReader readNP = new FileReader(fileNP);
        BufferedReader brNP = new BufferedReader(readNP);

        
        String line2 = brP.readLine();
        String line3 = brNP.readLine();
        String sTmp = null;
        String smp = null;
        int cnt = 0;
        while(line3 != null)
        {
        	if(line3.split(" ")[0].equals(MyFrame.newOnePicName))
        	{	
        		sTmp = line3;
        		cnt++;
        	}
			line3 = brNP.readLine();
        }
        
        
        
        while(line2 != null){
        	if(line2.split(" ")[0].equals(MyFrame.newOnePicName))
        	{	
        		smp = line2;
        		
        	}
			line2 = brP.readLine();
        }
        
        if(sTmp!= null && smp == null){
        	String objTmp[] = sTmp.split(" ");
			for(int j = 5; j < objTmp.length; j++)
			{
				rObjNum[Integer.parseInt(objTmp[j])]++;
			}
        }
        readP.close();
        readNP.close();
        brP.close();
        brNP.close();
	}
	
	public static void writeToWrong() throws IOException
	{
		String nopassFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_no_pass.txt";
		Arrays.fill(FileRead.wrongObjNuum, 0);
        File fileNP = new File(nopassFilePath);
		FileReader readNP = new FileReader(fileNP);
        BufferedReader brNP = new BufferedReader(readNP);

        String line3 = brNP.readLine();

//        int cnt = 0;
//        while(line3 != null)
//        {
//        	int tIndex = FileRead.idFromName(line3.split(" ")[0]);
////        	System.out.println("line   :" + line3);
//        	if(Integer.parseInt(line3.split(" ")[4]) != 0 && FileRead.wrongObjNuum[tIndex] == 0 && !haveZero)
//        	{
//        		cnt++;
////        		if(cnt == MainFrame.checkStaNum )
//        			FileRead.wrongObjNuum[tIndex]++;
//        	}
//        		
//
//			line3 = brNP.readLine();
//        }
        
        
        int tempA[] = new int[1000];
        
        while(line3 != null){
        	int tIndex = FileRead.idFromName(line3.split(" ")[0]);
        	if(Integer.parseInt(line3.split(" ")[4]) != 0  ){   		
        		
        		tempA[tIndex]++;
        	}
        	
        	line3 = brNP.readLine();
        }
        readNP = new FileReader(fileNP);
        brNP = new BufferedReader(readNP);
        line3 = brNP.readLine();
        while(line3 != null){
        	int tIndex = FileRead.idFromName(line3.split(" ")[0]);
        	if((tempA[tIndex] == MainFrame.checkStaNum || tempA[tIndex] == MainFrame.checkStaNum - 1) && Integer.parseInt(line3.split(" ")[4]) > 0 && checkNoName(line3.split(" ")[0]) ){
        		FileRead.wrongObjNuum[tIndex] = Integer.parseInt(line3.split(" ")[4]);
        	}
        	
        	line3 = brNP.readLine();
        }

        readNP.close();
        brNP.close();
	}

	public static void writeToLeave() throws IOException
	{
		String nopassFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_no_pass.txt";
		Arrays.fill(FileRead.leaveObjNum, 0);
        File fileNP = new File(nopassFilePath);
		FileReader readNP = new FileReader(fileNP);
        BufferedReader brNP = new BufferedReader(readNP);

        String line3 = brNP.readLine();
        int cnt = 0;
        int tempA[] = new int[1000];
        
        while(line3 != null){
        	int tIndex = FileRead.idFromName(line3.split(" ")[0]);
        	if(Integer.parseInt(line3.split(" ")[3]) != 0  ){   		
        		
        		tempA[tIndex]++;
        	}
        	
        	line3 = brNP.readLine();
        }
        readNP = new FileReader(fileNP);
        brNP = new BufferedReader(readNP);
        line3 = brNP.readLine();
        while(line3 != null){
        	int tIndex = FileRead.idFromName(line3.split(" ")[0]);
        	if((tempA[tIndex] == MainFrame.checkStaNum || tempA[tIndex] == MainFrame.checkStaNum - 1) && Integer.parseInt(line3.split(" ")[3]) > 0 && checkNoName(line3.split(" ")[0]) ){
        		FileRead.leaveObjNum[tIndex] = Integer.parseInt(line3.split(" ")[3]);
        	}
        	
        	line3 = brNP.readLine();
        }
        
//        while(line3 != null)
//        {
//
//        	int tIndex = FileRead.idFromName(line3.split(" ")[0]);
//        	if(Integer.parseInt(line3.split(" ")[3]) != 0  ){
//        		
////        		if( cnt == MainFrame.checkStaNum )
//        		
//        			FileRead.leaveObjNum[tIndex] = Integer.parseInt(line3.split(" ")[3]);
//        	}
//        		
//
//			line3 = brNP.readLine();
//        }

        readNP.close();
        brNP.close();
	}
	
	
//	public static boolean checkIndex() throws IOException
//	{
//		pList = new ArrayList<>();
//		npList = new ArrayList<>();
//
//		String passFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_pass.txt";
//		String nopassFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_no_pass.txt";
//		
//		
//		
//		File fileP = new File(passFilePath);
//		FileReader readP = new FileReader(fileP);
//        BufferedReader brP = new BufferedReader(readP);
//	
//        
//		String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
//        File file = new File(txtPath);
//        FileReader read = new FileReader(file);
//        BufferedReader br = new BufferedReader(read);
//				
//        
//        //
//    
//        String line2 = brP.readLine();
//        String line = br.readLine();
//        String sTT = "";
//        String sTmp = "";
//        
//
//        String mName = null;
//		mName = line.split(" ")[1];
//        while(line2 != null)
//        {
//        	String temp[] = line2.split(" ");
////        	String temp2[] = line3.split(" ");
//			pList.add(temp[0]+" "+temp[1]+" "+temp[2]+" "+temp[3]+" ");
////			npList.add(temp2[0]+" "+temp2[1]+" "+temp2[2]+" "+temp2[3]+" "+temp2[4]+" ");
//			line2 = brP.readLine();
////			line3 = brNP.readLine();
//        }
//        for(int i = 0; i < pList.size(); i++)
//        {
//        	if(pList.get(i).split(" ")[0].equals(MyFrame.newOnePicName)){
//        		return false;
//        	}
//        }
//        read.close();
//        readP.close();
//        return true;
//
//	}
//	
	public static void getIndexM() throws IOException//获得已标记过漏标的数量
	{
		
		indexM = 0;
//		String nopassFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_no_pass.txt";
//		npList.clear();
//        pList.clear();
//        File fileNP = new File(nopassFilePath);
//		FileReader readNP = new FileReader(fileNP);
//        BufferedReader brNP = new BufferedReader(readNP);
//        String line3 = brNP.readLine();
//
//        while(line3 != null)
//        {
////        	String temp2[] = null;
////        	if(line3 != null){
////        		temp2 = line3.split(" ");
////				npList.add(temp2[0]+" "+temp2[1]+" "+temp2[2]+" "+temp2[3]+" "+temp2[4]+" ");
////        	}
//        	npList.add(line3);
//			line3 = brNP.readLine();
//        }
//        for(int i = 0; i < npList.size(); i++)
//        {
//        	if(npList.get(i).split(" ")[0].equals(MyFrame.newOnePicName))
//        		indexM = Integer.parseInt(npList.get(i).split(" ")[3]);
//        }
//        npList.clear();
//        pList.clear();
		
//        readNP.close();
		indexM = FileRead.leaveObjNum[MyFrame.PictureNum];
		
		
	}


	
	public static boolean checkNoName(String filename){ //把标记错的图片重新打印在左上角
		String passFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_pass.txt";
		try{
	        File fileP = new File(passFilePath);
			FileReader readP = new FileReader(fileP);
	        BufferedReader brP = new BufferedReader(readP);

	        String line = brP.readLine();

	        while(line != null)
	        {
	        	if(line.split(" ")[0].equals(filename))
	        	{
	        		return false;		//有这个名字返回false
	        	}
				line = brP.readLine();
	        }
	        
	        readP.close();
	        brP.close();
		}catch(IOException e){
			e.getStackTrace();
		}
		
        return true;
	}
	
	public static boolean checkTrainOrVal(String filename){//检查是否够条件向train or val文件写入文件名
		
		String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
		try{
			File fileP = new File(txtPath);
			FileReader readP = new FileReader(fileP);
	        BufferedReader brP = new BufferedReader(readP);

	        String line = brP.readLine();
	        while(line != null){
	        	
	        	String[] temp = line.split(" ");
	        	if(filename.equals(temp[0])){
	        		for(int i = 2;i<temp.length;i+=11){
		        		int t1 = Integer.parseInt(temp[i+3]);
		        		int t2 = Integer.parseInt(temp[i+4]);
		        		int t3 = Integer.parseInt(temp[i+5]);
		        		int t4 = Integer.parseInt(temp[i+6]);
		        		if(t1 == 1 || t2 == 1 || t3 == 1|| t4 == 1)
		        		{
		        			return true;
		        		}
	        		}
	        	}
	        	
	        	line = brP.readLine();
	        }
	        readP.close();
	        brP.close();
		}catch(IOException e){
			e.getStackTrace();
		}
		
        return false;
	}
	
	public static void savePassData() throws IOException{	//保存全对的数据
//		errorObject = FileRead.wrongObjNuum[FileRead.idFromName(curFilename)];
//		sumObject = ReloadData.rMarkL.size() - errorObject;

		String passFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_pass.txt";
		File fileP = new File(passFilePath);
		FileReader readP = new FileReader(fileP);
        BufferedReader brP = new BufferedReader(readP);
		
		String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
        File file = new File(txtPath);
        FileReader read = new FileReader(file);
        BufferedReader br = new BufferedReader(read);
				
        String line2 = brP.readLine();
        String line = br.readLine();
        String sTmp1 = "";
        String sTmp2 = "";
        String sTmp = "";
        
        while(line2 != null){
        	spList.add(line2);
        	line2 = brP.readLine();
        }

        String mName = null;
		mName = line.split(" ")[1];

		for(int i = 0; i < spList.size(); i++){
			
			if(spList.get(i).split(" ")[0].equals(MyFrame.newOnePicName) && (sumObject != ReloadData.rMarkL.size() || curMiss != 0)){
				spList.remove(i);
				System.out.println("111");
			}
			
		}
        
		System.out.println(ReloadData.rMarkL.size() +" "+sumObject);
            if(ReloadData.rMarkL.size() != 0 && sumObject == ReloadData.rMarkL.size() && curMiss == 0 && MyFrame.openCheck && MyPanelRight.clickRight)
            {
            	for(int i = 0; i < spList.size(); i++){	
            		if(spList.get(i).split(" ")[0].equals(curFilename)){
            			spList.remove(i);
            		}
            	}
            	spList.add(curFilename+" "+sumObject+" "+mName+" "+MainFrame.userID+" ");

            	System.out.println("2222");
            }
        		

                String ssTmp = "";
        for(int i = 0; i < spList.size(); i++){
        	if(checkTrainOrVal(spList.get(i).split(" ")[0]))
        	{
        		sTmp1 += spList.get(i).split(" ")[0].split("\\.")[0]+System.getProperty("line.separator");
        	}
        	else
        	{
        		sTmp2 += spList.get(i).split(" ")[0].split("\\.")[0]+System.getProperty("line.separator");
        	}
        	ssTmp += spList.get(i).split(" ")[0].split("\\.")[0]+System.getProperty("line.separator");
        	sTmp += spList.get(i) +System.getProperty("line.separator");
        	
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
        	DataProcess.dataToTrainvalCH(ssTmp);
        }
        Arrays.fill(rObjNum, 0);
        spList.clear();
        FileWriter writer = new FileWriter(fileP);
        
		writer.write(sTmp);
		
	    writer.close();
        read.close();
        readP.close();
        br.close();
        brP.close();
	}
	
	public static void saveNoPassData() throws IOException{//(覆写)保存有错误的数据
		errorObject = FileRead.wrongObjNuum[MyFrame.PictureNum];
		sumObject = ReloadData.rMarkL.size() - errorObject;
		
		String nopassFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_no_pass.txt";
		
		String objStr = "";
		for(int i = 0; i < pointObj.length; i++)
		{
			if(pointObj[i] == 1)
			{
				objStr += i+" ";
			}
			if(pointObj[i] == 10)
			{
				fStr = i+" ";
//				System.out.println("fStr "+fStr);
			}
		}
		
        File fileNP = new File(nopassFilePath);
		FileReader readNP = new FileReader(fileNP);
        BufferedReader brNP = new BufferedReader(readNP);
        
        
        String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
        File file = new File(txtPath);
        FileReader read = new FileReader(file);
        BufferedReader br = new BufferedReader(read);
				
        
        //
        String line3 = brNP.readLine();
        String line = br.readLine();
        String sTT = "";
        String sTmp = "";
        
        while(line3 != null){
        	snpList.add(line3);
        	line3 = brNP.readLine();
        }

        String mName = null;
		mName = line.split(" ")[1];

		String sTemp = null;
		int ttIndex = 0;
        for(int i = 0; i < snpList.size(); i++)
        {
        	
        	if(snpList.get(i).split(" ")[0].equals(curFilename) && errorFlag){
        		
//        		indexM = Integer.parseInt(snpList.get(i).split(" ")[3]);
        		sTemp = snpList.get(i);
        		ttIndex = i;
        	}
        }
       
		
		
		
		if(sTemp == null && errorFlag)
		{
			if(errorObject != 0 || curMiss != 0)
				snpList.add(curFilename+" "+mName+" "+MainFrame.userID+" "+curMiss+" "+errorObject+" "+objStr);
		}
			
		else if(sTemp != null && errorFlag)
		{
			 String objTmp[] = snpList.get(ttIndex).split(" ");
			 if(errorObject != 0){
				 for(int j = 5; j < objTmp.length; j++)
					{
						
						if(fStr != null && fStr.equals(objTmp[j]+" ")){
							continue;
						}
						if(objStr.equals(objTmp[j]+" ")){
							continue;
						}
						objStr += objTmp[j] + " ";
					}
			 }
				
				
				System.out.println("NO GO ");
				
//				errorObject = objStr.split(" ").length;
//				System.out.println("objStr  : "+objStr);
				if(errorObject != 0 || curMiss != 0)
					snpList.set(ttIndex, curFilename+" "+mName+" "+MainFrame.userID+" "+curMiss+" "+errorObject+" "+objStr);
				else
					snpList.remove(ttIndex);
		}

        
        for(int i = 0; i < snpList.size(); i++){
        	sTT += snpList.get(i)+System.getProperty("line.separator");
        }
        
        Arrays.fill(pointObj, 0);
        Arrays.fill(rObjNum, 0);
        
        snpList.clear();
        FileWriter writern = new FileWriter(fileNP);
		writern.write(sTT);
		writern.close();
        read.close();
        readNP.close();
        br.close();
        brNP.close();
	}
	
public static void saveNoPassDataGo() throws IOException{//(追加(或者可以说是，找到同名的记录的最后一条记录,在进行覆写))保存有错误的数据
		
		errorObject = FileRead.wrongObjNuum[MyFrame.PictureNum];
		sumObject = ReloadData.rMarkL.size() - errorObject;

		String nopassFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_no_pass.txt";
		
		String objStr = "";
		for(int i = 0; i < pointObj.length; i++)
		{
			if(pointObj[i] == 1)
			{
				objStr += i+" ";
			}
			if(pointObj[i] == 10)
			{
				fStr = i+" ";
//				System.out.println("fStr "+fStr);
			}
		}
		
        File fileNP = new File(nopassFilePath);
		FileReader readNP = new FileReader(fileNP);
        BufferedReader brNP = new BufferedReader(readNP);
        
        
        String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
        File file = new File(txtPath);
        FileReader read = new FileReader(file);
        BufferedReader br = new BufferedReader(read);
				
        
        //
        String line3 = brNP.readLine();
        String line = br.readLine();
        String sTT = "";
        String sTmp = "";
        
        while(line3 != null){
        	snpList.add(line3);
        	line3 = brNP.readLine();
        }

        String mName = null;
		mName = line.split(" ")[1];

		String sTemp = null;
		String goFu = "";
		int ttIndex = 0;
		
		int count = 0;
        for(int i = 0; i < snpList.size(); i++)
        {
        	
        	if(snpList.get(i).split(" ")[0].equals(curFilename) && errorFlag){     		
        		count++;
        		goFu = snpList.get(i);
        		sTemp = snpList.get(i);
        		ttIndex = i;
        	}
        }
       if(count == MainFrame.checkStaNum - 1)
       {
    	   if(snpList.size() > 0){
    		   String objTmp[] = snpList.get(ttIndex).split(" ");
    	   
    		   
	    	   if(errorObject != 0)
	    	   {
	    		   for(int j = 5; j < objTmp.length; j++)
					{
						
						if(fStr != null && fStr.equals(objTmp[j]+" ")){
							continue;
						}
						if(objStr.equals(objTmp[j]+" ")){
							continue;
						}
						objStr += objTmp[j] + " ";
					}
	    	   }
    	   }
			
			
			if(errorObject != 0 || curMiss != 0)
				snpList.add(curFilename+" "+mName+" "+MainFrame.userID+" "+curMiss+" "+errorObject+" "+objStr);
    	   System.out.println("test1");
       }
       
       if(count == MainFrame.checkStaNum )
       {
    	   String objTmp[] = snpList.get(ttIndex).split(" ");
			for(int j = 5; j < objTmp.length; j++)
			{
				
				if(fStr != null && fStr.equals(objTmp[j]+" ")){
					continue;
				}
				if(objStr.equals(objTmp[j]+" ")){
					continue;
				}
				objStr += objTmp[j] + " ";
			}
			 
			
			if(errorObject != 0 || curMiss != 0)
				snpList.set(ttIndex, curFilename+" "+mName+" "+MainFrame.userID+" "+curMiss+" "+errorObject+" "+objStr);
			else
				snpList.remove(ttIndex);
			System.out.println("test2");
       }


		if(sTemp == null && errorFlag)
		{
			if(errorObject != 0 || curMiss != 0)
				snpList.add(curFilename+" "+mName+" "+MainFrame.userID+" "+curMiss+" "+errorObject+" "+objStr);
			
			System.out.println("test3");
		}
       
       

        
        for(int i = 0; i < snpList.size(); i++){
        	sTT += snpList.get(i)+System.getProperty("line.separator");
        }
        
        Arrays.fill(pointObj, 0);
        Arrays.fill(rObjNum, 0);
        
        snpList.clear();
        FileWriter writern = new FileWriter(fileNP);
		writern.write(sTT);
		writern.close();
        read.close();
        readNP.close();
        br.close();
        brNP.close();
	}
	
	public static void writelistWrongMess() throws IOException
	{
		String nopassFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_no_pass.txt";

        File fileNP = new File(nopassFilePath);
		FileReader readNP = new FileReader(fileNP);
        BufferedReader brNP = new BufferedReader(readNP);
        String line3 = brNP.readLine();
        
        
        
        while(line3 != null)
        {
        	listWrongMess.add(line3);
        	line3 = brNP.readLine();
        }
//		String nopassFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_no_pass.txt";
//
//		String passFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_pass.txt";
//	
//		File fileP = new File(passFilePath);
//		FileReader readP = new FileReader(fileP);
//        BufferedReader brP = new BufferedReader(readP);
//		
//		
//        File fileNP = new File(nopassFilePath);
//		FileReader readNP = new FileReader(fileNP);
//        BufferedReader brNP = new BufferedReader(readNP);
//
//        
//        String line2 = brP.readLine();
//        String line3 = brNP.readLine();
//        String sTmp = null;
//        String smp = null;
//        int cnt = 0;
//        
//        
//        ArrayList tempL = new ArrayList<>();
//        
//        while(line2 != null)
//        {
//        	
//        	tempL.add(line2);
//        	line2 = brP.readLine();
//        	
//        }
//        
//        while(line3 != null)
//        {
//        	String tLine = null; 
//        	boolean flag = false;
//        	for(int i = 0; i < tempL.size(); i++)
//        	{
//        		if(line3.split(" ")[0].equals(tempL.get(i)))
//        		{
//        			flag = true;
//        			
//        		}
//        		else
//        		{
//        			tLine = line3;
//        		}
//        	}
//        	
//        	if(!flag && tLine != null)
//        	{
//        		listWrongMess.add(tLine);
//        	}
//        	flag = false;
//        	line3 = brNP.readLine();
//        }
        readNP.close();
        brNP.close();
	}
	
	
	
	
	public static void showCurCnt() throws IOException{
		
		String nopassFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+folderName+"_no_pass.txt";
        File fileNP = new File(nopassFilePath);
		FileReader readNP = new FileReader(fileNP);
        BufferedReader brNP = new BufferedReader(readNP);

        String line3 = brNP.readLine();
		
        int[] cnt = new int[1000];
        Arrays.fill(cnt, 0);
        while(line3 != null)
        {
        	snpList.add(line3);
        	line3 = brNP.readLine();
        }
        ArrayList<String> cntList = new ArrayList<>();
        boolean tempC = false;
		for(int i = 0; i < snpList.size(); i++)
		{
			
				for(int j = 0; j < cntList.size(); j++)
				{
					if(cntList.get(j).split(" ")[0].equals(snpList.get(i).split(" ")[0]))
					{
						cnt[j]++;
						tempC = true;
//						System.out.println("1cnt "+i+"       "+ cnt[j]);
						
					}
					
					
				}
				if(!tempC)
				{
					cntList.add(snpList.get(i));
					cnt[cntList.size()-1]++;
//					System.out.println("2cnt "+i+"       "+ cnt[i]);
				}
				if(cntList.isEmpty())
				{
					cntList.add(snpList.get(i));
					cnt[0]++;
//					System.out.println("3cnt "+i+"       "+ cnt[i]);
				}
				
				tempC = false;
			
		}
		int tempMax = cnt[0];
		for(int i = 1; i < 1000; i++)
		{
			tempMax = Math.max(tempMax, cnt[i]);
		}
			
		MainFrame.checkStaNum = tempMax;
		snpList.clear();
        readNP.close();
        brNP.close();
	}
	
	
	
	
	
	
	
}
