package packUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

//左上的Panel组件
public class MyPanelLeTop extends JPanel {
	public static FileContent fC;		//文件列表存放组件
	public static JScrollPane jP;			//滚动条组件
	//构造函数
	public MyPanelLeTop(){	
		initMPLT();
		this.setVisible(true);
	}
	//初始化当前组件
	public void initMPLT(){
		this.setBackground(new Color(0xCCCCCC));
		this.setBounds(0, 0, ATP.leftW, ATP.leftTopH);
//		this.repaint();
		this.setLayout(null);
	}
	//生成所有组件
	public void openAll(){
		if( jP != null ) this.remove(jP);
		fC = new FileContent(){
			
			@Override
			public void getfile(int i) {
				// TODO Auto-generated method stub
				getfiles(i);
			}
		};
		
		
		jP = new JScrollPane(fC);
		jP.setSize(ATP.leftW,ATP.leftTopH);
		//设置横向滚动条永不出现
		jP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jP);
		this.setVisible(true);
		this.updateUI();
		
	}
	//获取当前组件的函数_3
	public void getfiles(int i){
		
	}
	
	//改变列表项的背景颜色（选中、未选中）
	public void changeBC(){
		Object obj = null;
		//找到相应的组件并改变值
	    obj = fC.getComponent(MyFrame.PictureNumF);
		if(obj instanceof FileName){
			FileName FN = (FileName)obj;
			FN.init();
		}
		obj = fC.getComponent(MyFrame.PictureNum);
		if(obj instanceof FileName){
			FileName FN = (FileName)obj;
			FN.BC();
		}
	}
	
	//标记是否已经对图片进行标记
	public static void changeGL(){
		Object obj = null;
		FileName FN = null;
		//找到对应的组件
//		System.out.println("ce shi 1");
		if(fC != null){
//			System.out.println("ce shi 2");
			obj = fC.getComponent(MyFrame.PictureNum); 
			if(obj instanceof FileName)
		    FN = (FileName)obj;
			
			//判断图片是否标记并改变值
			if(fC.getComponentCount()>MyFrame.PictureNum && MainFrame.workerType)
			{
				FN.addSpecial();
			}
			else if( !MainFrame.workerType && FN.getShow()){
				FN.addSpecial();
			}
			else
			{
				FN.clearSpecial();
			}
		}

	}
	//标记图片为错误标记文件
	public static void changeWR(){
		Object obj = null;
		FileName FN = null;
		//找到对应的组件
		obj = fC.getComponent(MyFrame.PictureNum); 
		if(obj instanceof FileName)
	    FN = (FileName)obj;
		
		//判断图片是否标记并改变值
		FN.addWrong();
	}
//	//读取文件中错误的标记文件
//	public static void changeWRAll(){
//		Object obj = null;
//		FileName FN = null;
//		for(int i=0; i<FileRead.wrongObjNuum.length; i++ )
//		{
//			if(FileRead.wrongObjNuum[i]>0)
//			{
//				obj = fC.getComponent(i);
//				if(obj instanceof FileName)
//				FN = (FileName)obj;
//				FN.addWrong();
//			}
//		}
//	}
}
