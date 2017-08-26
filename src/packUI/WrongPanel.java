package packUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class WrongPanel extends JPanel {
	public static WrongList WL;		//文件列表存放组件
	private JScrollPane jP;			//滚动条组件
	private JLabel title1;

	//构造函数
	public WrongPanel(){	
		initWP();
		this.setVisible(true);
	}
	//初始化当前组件
	public void initWP(){
		this.setBackground(new Color(0xCCCCCC));
		this.setBounds( ATP.frameW-ATP.rightW, ATP.frameH-320, ATP.leftW,210);//位置信息
//		this.repaint();
		this.setLayout(null);
		title1 = new JLabel("错误信息");
		title1.setHorizontalAlignment(SwingConstants.CENTER);
		title1.setFont(new Font("宋体", 1, 11));
		title1.setBounds(0, 0, 180, 25);
		if(MainFrame.userID != null){
			this.add(title1);
		}

	}
	//生成所有组件
	public void openAll(){
		if( jP != null ) this.remove(jP);
		WL = new WrongList(){
			
			@Override
			public void getfile(int i) {
				// TODO Auto-generated method stub
				getfiles(i);
			}
		};
		
		initWP();
		jP = new JScrollPane(WL);
		jP.setSize(ATP.leftW,185);
		jP.setLocation(0,25);
		//设置横向滚动条永不出现
	//	jP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(jP);
		this.setVisible(true);
		this.updateUI();
		
	}
	//获取当前组件的函数_3
	public void getfiles(int i){
		
	}
	
//	//改变列表项的背景颜色（选中、未选中）
//	public void changeBC(){
//		Object obj = null;
//		//根据当前点击的组件改变滚动条的位置
//		jP.getVerticalScrollBar().setValue(MyFrame.PictureNum*ATP.objectH-ATP.objectH*11);
//		//找到相应的组件并改变值
//	    obj = fC.getComponent(MyFrame.PictureNumF);
//		if(obj instanceof FileName){
//			FileName FN = (FileName)obj;
//			FN.init();
//		}
//		obj = fC.getComponent(MyFrame.PictureNum);
//		if(obj instanceof FileName){
//			FileName FN = (FileName)obj;
//			FN.BC();
//		}
//	}
//	
//	//标记是否已经对图片进行标记
//	public static void changeGL(){
//		Object obj = null;
//		FileName FN = null;
//		//找到对应的组件
////		System.out.println("ce shi 1");
//		if(fC != null){
////			System.out.println("ce shi 2");
//			obj = fC.getComponent(MyFrame.PictureNum); 
//			if(obj instanceof FileName)
//		    FN = (FileName)obj;
//			
//			//判断图片是否标记并改变值
//			if(fC.getComponentCount()>MyFrame.PictureNum && MainFrame.workerType)
//			{
//				FN.addSpecial();
//			}
//			else if( !MainFrame.workerType && FN.getShow()){
//				FN.addSpecial();
//			}
//			else
//			{
//				FN.clearSpecial();
//			}
//		}
//
//	}
//	//标记图片为错误标记文件
//	public static void changeWR(){
//		Object obj = null;
//		FileName FN = null;
//		//找到对应的组件
//		obj = fC.getComponent(MyFrame.PictureNum); 
//		if(obj instanceof FileName)
//	    FN = (FileName)obj;
//		
//		//判断图片是否标记并改变值
//		FN.addWrong();
//	}
}
