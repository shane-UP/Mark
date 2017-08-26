package packUI;
import java.io.File;
import java.util.ArrayList;

import packData.ModelProcess;

//文件读取组件
public class FileRead {
	 public static int[] wrongObjNuum;	//错误的框数量
	 public static int[] leaveObjNum;		//漏标的框数量
	 
//	 public static int [][] rObjNum;
	 private static ArrayList<String> filelist = new ArrayList<String>();		//文件路径的列表
	 public static ArrayList<String> flienamelist = new ArrayList<String>(); //文件名称的列表
	 public void readAll(String boom){

	    String filePath = boom;
	    getFiles(filePath);
	 }
	    /*
	     * 通过递归得到某一路径下所有的目录及其文件
	     */
	    static void getFiles(String filePath){
		     File root = new File(filePath);
			       File[] files = root.listFiles();
			       for(File file:files){     
				        if(file.isDirectory()){
				         /*
				          * 递归调用
				          */
				        	getFiles(file.getAbsolutePath());
				         //System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getAbsolutePath());
				        }
				        else{
				        	
				        	String s = file.getAbsolutePath();
				        	//System.out.println(s.endsWith(".jpg") + " " + s.endsWith(".png") );
				        	if( s.endsWith(".jpg")  || s.endsWith(".png") || s.endsWith(".PNG") || s.endsWith(".JPG") )
				        	{
//				        		if(ModelProcess.checkNoName(file.getName())){
				        			flienamelist.add(file.getName());
					        		filelist.add(file.getAbsolutePath());
//				        		}
				        		
				        		
				        	//	System.out.println("显示"+filePath+"下所有子目录"+file.getAbsolutePath());
				             }
				        }  
			       }
			       wrongObjNuum = new int[filelist.size()];
			       leaveObjNum = new int[filelist.size()];
//			       rObjNum = new int[filelist.size()][100];
		    }
		    
	    	//得到指定文件的路径
		    public String getOnePic(int i){
		    	return filelist.get(i);
		    }
		    //得到文件的数量
		    public int getSize(){
		    	return filelist.size();
		    }
		    //清空文件组件
		    public void clearAll(){
		    	filelist.clear();
		    	flienamelist.clear();
		    }
		    //得到指定文件的名称
		    
		    public String getOneName(int i){
		    	return flienamelist.get(i);
		    }
		    
		    //根据文件名获取文件ID
		    public static int idFromName(String name){
		    	int i;
		    	for( i=0; i<flienamelist.size(); i++)
		    	{
		    		if(flienamelist.get(i).equals(name)) break;
		    	}
		    	return i;
		    }
}
