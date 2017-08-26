package packUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import packData.CalcExchange;
import packData.DataProcess;
import packData.ModelProcess;
import packData.ReloadData;

//框选对象组件
public class ObjectName extends JPanel{
	private Color color; 						//组件的背景颜色
	private Color color1 = new Color(0xCCCCCC);//组件的默认背景色
	private int fnID;							//组件的ID
	public  JButton change;						//编辑按钮
	public  JButton delect;						//删除按钮
	public  JButton wrong;						//错误按钮
	public  JLabel errorP;						//错误图标
	private JLabel objName;					//组件名
	boolean ischeck = false;
	
	
	public ObjectName(String text,int i) {
		// TODO Auto-generated constructor stub
		this.fnID = i;
		color = color1;
		
		this.setBackground(color);
		this.setSize(180,25);
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(new Color(0xCCCCCC)));
		this.setOpaque(true);
		
		objName = new JLabel();
		objName.setText(text);
		objName.setBounds(0, 0, 80, 25);
		this.add(objName);
	
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
				if(!MyPanelRight.hChange)
				{
					color = Color.cyan;
					changeColor();
					getFile(fnID);
				}	
			   
//			   System.out.println("is Entered");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
					getFile(fnID);
					
				
			}
		});
		
		if(MainFrame.workerType) init_makeer();
		else init_tester();
		
	}
	
	//初始化函数（标记者状态）
	public void init_makeer(){
		change = new JButton("修改");
		change.setBounds(81, 1, 38, 23);
		change.setFont(new Font("微软雅黑",1,12));
		change.setMargin(new Insets(0,0,0,0));
		
		delect = new JButton("删除");
		delect.setBounds(121, 1, 38, 23);
		delect.setFont(new Font("微软雅黑",1,12));
		delect.setMargin(new Insets(0,0,0,0));

//		Double(fnID);
		
		this.add(change);
		this.add(delect);
		change.addMouseListener(new MouseListener() {
			
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
				if(!MyPanelRight.hChange)
				{
					color = Color.cyan;
					changeColor();
					getFile(fnID);
				}	
			   
//			   System.out.println("is Entered");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				getFile(fnID);
				MyPanelRight.hChange = true;
				MyFrame.afterPaint = false;
				
				String sTmp = ReloadData.rMarkL.get(fnID);
//				System.out.println("sTmp: "+sTmp);
				MyPanelRight.markTS = sTmp.split(" ");
				
				MyPanelRight.objName.setSelectedItem(MyPanelRight.markTS[0]);
				
				MyPanelRight.objPose.setSelectedItem(MyPanelRight.markTS[1]);
				MyPanelRight.objColor.setSelectedItem(MyPanelRight.markTS[2]);
//				System.out.println(MyPanelRight.markTS[3]+"||"+MyPanelRight.markTS[3]);
				int ttr = Integer.parseInt(MyPanelRight.markTS[3]);
				int tco = Integer.parseInt(MyPanelRight.markTS[4]);
				int tdi = Integer.parseInt(MyPanelRight.markTS[5]);
				int tsh = Integer.parseInt(MyPanelRight.markTS[6]);
				
				MyPanelRight.xmlTrunc = ttr;
				MyPanelRight.xmlCover = tco;
				MyPanelRight.xmlDiff = tdi;
				MyPanelRight.xmlShadow = tsh;
				
				
				if(MyPanelRight.xmlDiff == 1){
					MyPanelRight.kn.setSelected(true);
				}
				else
				{
					MyPanelRight.bkn.setSelected(true);
				}
				
				if(MyPanelRight.xmlTrunc == 1){
					MyPanelRight.fg.setSelected(true);
				}
				else
				{
					MyPanelRight.mfg.setSelected(true);
				}
				
				if(MyPanelRight.xmlCover == 1){
					MyPanelRight.xmid.setSelected(true);
				}
				else
				{
					MyPanelRight.dmid.setSelected(true);
				}
				
				if(MyPanelRight.xmlShadow == 1){
					MyPanelRight.yy.setSelected(true);
				}
				else
				{
					MyPanelRight.myy.setSelected(true);
				}
				
				MyPanelRight.box1 = MyPanelRight.markTS[0];
				MyPanelRight.box2 = MyPanelRight.markTS[1];
				MyPanelRight.box3 = MyPanelRight.markTS[2];
				
				MyFrame.unEnableCom();
				MyPanelRight.myJB.setEnabled(true);
				
				
				MyFrame.enableCom();
			}
		});
//		change.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		delect.addMouseListener(new MouseListener() {
			
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
				if(!MyPanelRight.hChange)
				{
					color = Color.cyan;
					changeColor();
					getFile(fnID);
				}	
			   
//			   System.out.println("is Entered");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				MyFrame.deletePaint = true;
				try {
					DataProcess.changeToCache();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					DataProcess.txtToXML(DataProcess.pathData+"\\imgCache.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!MyFrame.rList.isEmpty())
				{
					System.out.println("这里执行了");
					MyFrame.rList.clear();
					ReloadData.rMarkL.clear();
				}
				MyPanelRight.reLook();
				System.out.println(MyFrame.rList);
				System.out.println(ReloadData.rMarkL);
//				MyFrame.pP.repaint();

//				System.out.println(MyFrame.PictureNum);
				MyFrame.mPLB.openAll();
				MyFrame.mPLB.changeBC();
				MyFrame.deletePaint = false;
			}
		});
//		delect.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
	}
	//初始化函数（检查者状态）
	public void init_tester(){
		wrong = new JButton("错误");
		wrong.setBounds(98, 1, 38, 23);
		wrong.setFont(new Font("微软雅黑",1,12));
		wrong.setMargin(new Insets(0,0,0,0));
		this.add(wrong);
		errorP = new JLabel(); 
		errorP.setBounds(138, 1, 38, 23);
		this.add(errorP);
		wrong.addMouseListener(new MouseListener() {
			
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
				// TODO Auto-generated method stub
				if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0  )
				{
					ModelProcess.haveZero = true;

				}
				try {
					ModelProcess.writeToWrong();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!ischeck){
					turnWrong();		
					ModelProcess.pointObj[fnID]++;
					++FileRead.wrongObjNuum[MyFrame.PictureNum];
					
					if (FileRead.wrongObjNuum[MyFrame.PictureNum] >= 1 )
						MyPanelLeTop.changeWR();
					ModelProcess.errorFlag = true;
					ModelProcess.curFilename = MyFrame.newOnePicName;
					FileRead.leaveObjNum[MyFrame.PictureNum] = ModelProcess.curMiss;
					
					ModelProcess.haveZero = false;

//					ModelProcess.errorObject = FileRead.wrongObjNuum[FileRead.idFromName(ModelProcess.curFilename)];
//					ModelProcess.sumObject = ReloadData.rMarkL.size() - ModelProcess.errorObject;
					if(MyFrame.notOpen)
					{
						try {
							ModelProcess.savePassData();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						if(MainFrame.checkSta == 1)
						{
							try {
								ModelProcess.saveNoPassData();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else
						{
							try {
								ModelProcess.saveNoPassDataGo();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						try {
							ModelProcess.savePassData();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					try {
						ModelProcess.writeTorobjnum();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					CalcExchange.calcFinishShow();
//					try {
//						CalcExchange.useCopy();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					
					ModelProcess.errorFlag = false;
				}
				else
				{
					errorP.setIcon(null);
					wrong.setText("错误");
					ischeck =false;
					ModelProcess.pointObj[fnID]+=10;
					--FileRead.wrongObjNuum[MyFrame.PictureNum];
					System.out.println("FileRead.wrongObjNuum[MyFrame.PictureNum]**1:" + FileRead.wrongObjNuum[MyFrame.PictureNum]);
					//FileRead.rObjNum[MyFrame.PictureNum][fnID] = 0;

					
						
					ModelProcess.errorFlag = true;
					ModelProcess.curFilename = MyFrame.newOnePicName;

//					ModelProcess.errorObject = FileRead.wrongObjNuum[FileRead.idFromName(ModelProcess.curFilename)];
//					ModelProcess.sumObject = ReloadData.rMarkL.size() - ModelProcess.errorObject;
//					try {
//						if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0){
//							ModelProcess.haveZero = true;
//						}
//						ModelProcess.writeToWrong();
//						ModelProcess.haveZero = false;
//					} catch (IOException e3) {
//						// TODO Auto-generated catch block
//						e3.printStackTrace();
//					}
					if(MyFrame.notOpen)
					{
						if(MainFrame.checkSta == 1)
						{
							try {
								ModelProcess.saveNoPassData();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else
						{
							try {
								ModelProcess.saveNoPassDataGo();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						try {
							ModelProcess.savePassData();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
//						MyFrame.notOpen = false;
						
					}
					
					CalcExchange.calcFinishShow();
//					try {
//						CalcExchange.useCopy();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					try {
//					if( ModelProcess.curMiss == 0)
//					{
//					if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0){
//						ModelProcess.haveZero = true;
//					}
//					ModelProcess.writeToWrong();
//					ModelProcess.haveZero = false;
					ModelProcess.writeTorobjnum();
					}catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
					System.out.println("FileRead.wrongObjNuum[MyFrame.PictureNum]**2:" + FileRead.wrongObjNuum[MyFrame.PictureNum]);
					if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0 && ModelProcess.curMiss == 0 )
					{
//						CalcExchange.calcFinishShow();
//						ModelProcess.haveZero = true;
						MyPanelLeTop.changeGL();
					}
					ModelProcess.errorFlag = false;
				}
			}
		});
//		wrong.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
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
	//初始化按钮鼠标事件_1
	public void Double(int k){
		
	}
	
	//设置按钮不能点击
	public void unBtn(){
		delect.setEnabled(false);
		change.setEnabled(false);
	}
	//设置按钮可以点击
	public void abBtn(){
		delect.setEnabled(true);
		change.setEnabled(true);
	}
	//将图片改为wrong
	public void turnWrong(){
		errorP.setIcon(new ImageIcon(ObjectName.class.getResource("/image/wrong.png")));
		wrong.setText("撤销");
		ischeck = true;
	}
}

