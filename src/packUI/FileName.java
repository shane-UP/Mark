package packUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import packData.DataProcess;
import packData.ModelProcess;
import packData.ReloadData;

//这个是列表中的一个组件
public class FileName extends JPanel{
	public static boolean deleteAll = false;   //判断是否调用函数changeToCache中的清空操作
	public static boolean flagGoog = false;	  
	//
	private Color color; 						  //组件的背景颜色
	private Color color1 = new Color(0xCCCCCC); //组件的默认背景色
	private int fnID;							 //组件的ID
	private JLabel fileName;					 //显示的名称
	private JLabel special;					 //显示为对勾颜色的组件
	private boolean isSpecial = false;		 //当前组件是否被标记
	private boolean isShow = false;
	public FileName(String text,int i) {
		// TODO Auto-generated constructor stub
		
		this.fnID = i;
		color = color1;
		this.setBackground(color);
		this.setSize(180,25);
		this.setLayout(null);
		//显示文件的名字的组件初始化
		fileName = new JLabel();
		fileName.setBounds(0, 0,135, 25);
		fileName.setHorizontalAlignment(SwingConstants.LEFT);
		fileName.setText(text);		
		//标记图片显示的组件初始化
		special = new JLabel();
		special.setBounds(138, 3, 19, 19);
		special.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				getFile(fnID);
				if(isSpecial&&MainFrame.workerType)
				{
					if(JOptionPane.showInternalConfirmDialog(MainFrame.myFrame.getContentPane(), 
							"确认要删除此图所有的对象吗", "清空对象", 
							JOptionPane.YES_NO_OPTION, 
							JOptionPane.INFORMATION_MESSAGE)==JOptionPane.YES_OPTION)
					{
						deleteAll = true;
						try {
							DataProcess.changeToCache();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(!MyFrame.rList.isEmpty())
						{
							MyFrame.rList.clear();
							ReloadData.rMarkL.clear();
						}
						MyPanelRight.reLook();
						MyFrame.mPLB.openAll();
						clearSpecial();
						deleteAll = false;
					}	
				}
			}
		});
		
		this.add(fileName);
		this.add(special);
		this.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
		this.setOpaque(true);
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				color = color1;
				changeColor();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			   color = Color.cyan;
			   changeColor();
//			   System.out.println("is Entered");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
//				MyPanelRight.reLook();

				if(MyFrame.havePaint == true)
				{
					if(MyPanelRight.btnMark == false){
						  JOptionPane.showMessageDialog(null, "您还没有保存！！");
						  
					  }
					else
					{
						String bg = MyFrame.openKey.getOnePic(MyFrame.PictureNum);
						String arrBgTemp = bg;
						String arrBg[] = arrBgTemp.split("\\\\");
						arrBgTemp = arrBg[arrBg.length-1];
						MyFrame.onePicName = arrBgTemp;
						

						if(MyPanelRight.markedR.size() == MyFrame.list.size())
						{
//							try {
//								test1.dataToCache();
//								test1.txtToXML(test1.pathData+"\\imgCache.txt");
//								test1.dataToTxt(test1.testTxt);
//							} catch (IOException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
							
							MyFrame.cleatAllList();
							MyPanelRight.btnMark = false;
							MyFrame.havePaint = false;
						}
						if(MyFrame.clearRect > 0 && MyPanelRight.markedR.size() != MyFrame.list.size())
						{
							for(int i = MyFrame.list.size()-1; i >= 0 && MyFrame.clearRect > 0; i--,MyFrame.clearRect--)
							  {
								MyFrame.list.remove(i);
								MyFrame.listS.remove(i);
								  
							  }
						}
//						saveData();
//						System.out.println("保存的picnum："+MyFrame.onePicName);
						getFile(fnID);
						
					}
					
				}
				else
				{
					getFile(fnID);
				}
				
				
			}
		});
//		visitFile();
	}
	
	//获取当前组件的函数_1
	public void getFile(int k){
		
	}
	
	//改变当前组件的背景颜色
	public void changeColor(){
		this.setBackground(color);
	}
	
	
	//改变组件的默认背景色
	public void BC(){
		color1 = Color.cyan;
		color = color1;
		changeColor();
	}
	
	//还原组件的默认背景色
	public void init(){
		color1 = new Color(0xCCCCCC);
		color = color1;
		changeColor();
	}
	
//	public void visitFile(){
//		
//		for(int i = 0; i < FileContent.mount; i++){
//			addGoodLabel(MyFrame.openKey.getOneName(i), i);
//		}
//	}
	
//	public void addGoodLabel(String filename, int i){
////		System.out.println("运行1");
//		try {
//			if(ReloadData.specialCheck(filename))
//			{
//				System.out.println("运行");
//				goodL.setBounds(135, 25, 20, 20);
//				this.add(goodL);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			System.out.println("addGoodLabel 有问题!");
//		}
//		
//	}
	
	//设置该组件为已标记组件
	public void addSpecial(){
		special.setIcon(new ImageIcon(FileName.class.getResource("/image/good.jpg")));
		if(MainFrame.workerType) isSpecial = true;
		else isShow = true;
	}
	//设置该组件为未标记组件
	public void clearSpecial(){
		special.setIcon(null);
		isSpecial = false;
		isShow = false;
	}
	//设置该组件为错误标记组件
	public void addWrong()
	{
		special.setIcon(new ImageIcon(FileName.class.getResource("/image/wrong.png")));
		isSpecial = true;
		isShow = false;
	}
	
	public boolean getShow(){
		return isShow;
	}
	public void setShowt(){
		System.out.println(" is  setShowt");
		isShow = true;
	}
//	public void setShoww(){
//		isShow = false;
//	}
}
