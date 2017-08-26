package packUI;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.jws.WebParam.Mode;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import packData.CalcExchange;
import packData.DataProcess;
import packData.ModelProcess;
import packData.ReloadData;

public class MyPanelRight extends JPanel{
	
	public static int OpenNum = 0;
	public static String box1 = null;
	public static String box2 = null;
	public static String box3 = null;
	public static int tmpIndex;
	public static ArrayList<String> markedR;
	public static String tmpMark;
	public static boolean btnMark;
	public static boolean hChange = false;
	public static JButton myJB;
	public static JComboBox<String> objName;
	public static JComboBox<String> objPose;
	public static JComboBox<String> objColor;
	public static String markTS[];
	public static ArrayList<String> markL;
	public static boolean flagLU = false;
	public static JLabel label;
	
	public static JRadioButton fg;
	public static JRadioButton mfg;
	
	public static JRadioButton kn;
	public static JRadioButton bkn;
	
	public static JRadioButton dmid;
	public static JRadioButton xmid;
	
	public static JRadioButton myy;
	public static JRadioButton yy;
	
	
	public static ButtonGroup bgTrunc;
	public static ButtonGroup bgDiff;
	public static ButtonGroup bgCover;
	public static ButtonGroup bgShadow;
	
	public static int xmlTrunc = 0;
	public static int xmlDiff = 0;
	public static int xmlCover = 0;
	public static int xmlShadow = 0;
	
	public static int missNum = 0;
	public static JComboBox<Integer> comMiss;
	
	public static JButton allRight;
	public static boolean checkSave = false;		//如果检查模式下保存情况
	
	public static boolean clickRight = false;
	
	public MyPanelRight(){
		this.setBackground(new Color(0xCCCCCC));
		this.setBounds(ATP.leftW+ATP.midW, 0, ATP.rightW, ATP.rightH);
		this.setLayout(null);
//		label = new JLabel("请为选框添加标记");
//		label.setFont(new Font("宋体",1,11));
//		this.add(label);

//		if(MainFrame.workerType && MainFrame.pre_worker)
//			initMarkLPR();
//		else if(!MainFrame.workerType && MainFrame.pre_worker)
//			initCheckLPR();
			
	}

	public static void initMarkLPR() {
		label = new JLabel("请为选框添加标记");
		label.setFont(new Font("宋体",1,11));
		MyFrame.mPR.add(label);
		
		btnMark = false;
		markedR =  new ArrayList<>();

		objName = new JComboBox<>();
		objName.addItem("--选择类型--");
		objName.addItem("Car");
		objName.addItem("Bus");
		objName.addItem("Taxi");
		objName.addItem("MPV");
		objName.addItem("Van");
		objName.addItem("SUV");
		objName.addItem("Pickup");
		objName.addItem("Non-Motor");
		objName.addItem("Unspecified");
		
		objName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MyPanelMidBo.canKey = false;
				box1 = (String)objName.getSelectedItem();

			}
		});
		MyFrame.mPR.add(objName);
		
		
		//----------------------------------
		objPose = new JComboBox<>();
		objPose.addItem("--选择方位--");
		objPose.addItem("Left");
		objPose.addItem("Right");
		objPose.addItem("Frontal");
		objPose.addItem("Rear");
		objPose.addItem("Unspecified");
		
		objPose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MyPanelMidBo.canKey = false;
				box2 = (String)objPose.getSelectedItem();
//				markedR.set(tmpIndex, tmpMark);
//				markedR.add((String)objPose.getSelectedItem());
				
			}
		});
		MyFrame.mPR.add(objPose);
		
		
		//------------------------------------------
		
		objColor = new JComboBox<>();
//		objColor.setFont(new Font("宋体",1,12));
		objColor.addItem("--选择颜色--");
		objColor.addItem("Black");
		objColor.addItem("White");
		objColor.addItem("Sliver");
		objColor.addItem("Blue");
		objColor.addItem("Gray");
		objColor.addItem("Red");
		objColor.addItem("Green");
		objColor.addItem("Yellow");
		objColor.addItem("Orange");

		objColor.addItem("Unspecified");
		
		objColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MyPanelMidBo.canKey = false;
				box3 = (String)objColor.getSelectedItem();
				
			}
		});
		MyFrame.mPR.add(objColor);
		
		
		label.setBounds(30, 0, 140, 20);
		JLabel carName = new JLabel("选择车辆类型");
		carName.setFont(new Font("宋体",1,11));
		MyFrame.mPR.add(carName);
		carName.setBounds(5, 40, 140, 20);
		objName.setBounds(5, 60, 140, 20);
		
		JLabel carPose = new JLabel("选择车头朝向");
		carPose.setFont(new Font("宋体",1,11));
		MyFrame.mPR.add(carPose);
		carPose.setBounds(5, 110, 140, 20);
		objPose.setBounds(5, 130, 140, 20);

		
		JLabel carColor = new JLabel("选择车辆颜色");
		carColor.setFont(new Font("宋体",1,11));
		MyFrame.mPR.add(carColor);
		carColor.setBounds(5, 180, 140, 20);
		objColor.setBounds(5, 200, 140, 20);
		
		JLabel lTrunc = new JLabel("遮挡 ");
		mfg = new JRadioButton("否");
		fg = new JRadioButton("是");
		bgTrunc = new ButtonGroup();
		bgTrunc.add(mfg);
		bgTrunc.add(fg);
		mfg.setSelected(true);
		lTrunc.setBounds(5, 230, 65, 20);
		mfg.setBounds(65, 230, 55, 20);
		fg.setBounds(120, 230, 60, 20);
		lTrunc.setFont(new Font("宋体",1,11));
		mfg.setFont(new Font("宋体",1,11));
		fg.setFont(new Font("宋体",1,11));
		MyFrame.mPR.add(lTrunc);
		MyFrame.mPR.add(mfg);
		MyFrame.mPR.add(fg);
		
		fg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xmlTrunc = 1;
			}
		});
		mfg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xmlTrunc = 0;
			}
		});
		
		
		
		
		
		JLabel lArea = new JLabel("车面积 ");
		dmid = new JRadioButton("≥50%");
		xmid = new JRadioButton("<50%");
		bgCover = new ButtonGroup();
		bgCover.add(dmid);
		bgCover.add(xmid);
		dmid.setSelected(true);
		lArea.setBounds(5, 260, 65, 20);
		dmid.setBounds(65, 260, 58, 20);
		xmid.setBounds(120, 260, 60, 20);
		lArea.setFont(new Font("宋体",1,11));
		dmid.setFont(new Font("宋体",1,11));
		xmid.setFont(new Font("宋体",1,11));
		MyFrame.mPR.add(lArea);
		MyFrame.mPR.add(dmid);
		MyFrame.mPR.add(xmid);
		
		dmid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xmlCover = 0;
			}
		});
		xmid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xmlCover = 1;
			}
		});
		
		
		
		
		
	
		JLabel lDiff = new JLabel("识别难度 ");
		bkn = new JRadioButton("普通");
		kn = new JRadioButton("困难");
		lDiff.setFont(new Font("宋体",1,10));
		bgDiff = new ButtonGroup();
		bgDiff.add(bkn);
		bgDiff.add(kn);
		bkn.setSelected(true);
		lDiff.setBounds(5, 290, 65, 20);
		bkn.setBounds(65, 290, 55, 20);
		kn.setBounds(120, 290, 60, 20);
		
		lDiff.setFont(new Font("宋体",1,11));
		bkn.setFont(new Font("宋体",1,11));
		kn.setFont(new Font("宋体",1,11));
		MyFrame.mPR.add(lDiff);
		MyFrame.mPR.add(bkn);
		MyFrame.mPR.add(kn);
//		JLabel goodL = new JLabel(new ImageIcon("image\\good.jpg"));
//		goodL.setBounds(5 , 350, 140, 22);
//		this.add(goodL);
		
		
		kn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xmlDiff = 1;
			}
		});
		bkn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xmlDiff = 0;
			}
		});
		
		
		JLabel lShadow = new JLabel("阴影 ");
		myy = new JRadioButton("无");
		yy = new JRadioButton("有");
		lShadow.setFont(new Font("宋体",1,10));
		bgShadow = new ButtonGroup();
		bgShadow.add(myy);
		bgShadow.add(yy);
		myy.setSelected(true);
		lShadow.setBounds(5, 320, 65, 20);
		myy.setBounds(65, 320, 55, 20);
		yy.setBounds(120, 320, 60, 20);
		
		lShadow.setFont(new Font("宋体",1,11));
		myy.setFont(new Font("宋体",1,11));
		yy.setFont(new Font("宋体",1,11));
		MyFrame.mPR.add(lShadow);
		MyFrame.mPR.add(myy);
		MyFrame.mPR.add(yy);
//		JLabel goodL = new JLabel(new ImageIcon("image\\good.jpg"));
//		goodL.setBounds(5 , 350, 140, 22);
//		this.add(goodL);
		
		
		yy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xmlShadow = 1;
			}
		});
		myy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xmlShadow = 0;
			}
		});
		
		myJB = new JButton("保存一下");
		myJB.setMnemonic(KeyEvent.VK_S);
		myJB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(MyFrame.havePaint == true && hChange == false && MyFrame.afterPaint == false)
				{
					
					if(MyFrame.havePaint == true && btnMark == false && MyFrame.haveImg)
					{
						if(box1 == null || box2 == null || box3 == null || box1 == "--选择类型--" || box2 == "--选择方位--" || box3 == "--选择颜色--")
						{
						
							MainFrame.showMessages("您的属性没有填写完整！");
						}
						else
						{
							MyPanelMidBo.canKey = true;
							MyPanelMidBo.nextOne.requestFocus();
							MyPanelMidBo.canKey = true;
//							System.out.println("Right  MyFrame.havePaint 这里执行了");
							MyFrame.nPLT = false;
							MyFrame.havePaint = true;
							MyPanelRight.btnMark = true;
							MyFrame.leftMark = true;
							
							markedR.add(box1+" "+box2+" "+box3+" "+xmlTrunc+" "+xmlCover+" "+xmlDiff+" "+xmlShadow+" ");
							MyFrame.pP.repaint();
							MyFrame.mPLB.openAll();
							box1 = null;
							box2 = null;
							box3 = null;
							if(MyPanelRight.markedR.size() == MyFrame.list.size() && MyFrame.list.size() != 0)
							{
								
								try {
									DataProcess.dataToCache();
									DataProcess.txtToXML(DataProcess.pathData+"\\imgCache.txt");
//									DataProcess.dataToTxt(DataProcess.testTxt);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								MyFrame.cleatAllList();
//								MyPanelRight.btnMark = false;
//								MyFrame.havePaint = false;
							}
//							MyFrame.pP.repaint();
//							MyFrame.mPLB.openAll();
							MyPanelRight.reLook();
							
//							MyFrame.mP.openRe(bg);
//							MyFrame.mPLT.openAll();
//							MyFrame.mPLT.repaint();
//							MyFrame.mPLT.changeBC();
							MyFrame.mPLB.openAll();
							MyFrame.mPLB.changeBC();

							MyPanelRight.objName.setSelectedIndex(0);
							MyPanelRight.objColor.setSelectedIndex(0);
							MyPanelRight.objPose.setSelectedIndex(0);
							MyPanelRight.mfg.setSelected(true);
							MyPanelRight.xmlTrunc = 0;
							MyPanelRight.bkn.setSelected(true);
							MyPanelRight.xmlDiff = 0;
							MyPanelRight.xmlCover = 0;
							MyPanelRight.xmlShadow = 0;
							MyPanelRight.myy.setSelected(true);
							MyPanelRight.dmid.setSelected(true);
							MyPanelRight.box1 = null;
							MyPanelRight.box2 = null;
							MyPanelRight.box3 = null;
//							objName.setEnabled(false);
//							objColor.setEnabled(false);
//							objPose.setEnabled(false);
							MyPanelRight.btnMark = true;
							MyFrame.unEnableCom();
							myJB.setEnabled(false);
							MyFrame.editMenu.setEnabled(false);
							MyFrame.mPLB.abAllBtn();
							MyPanelLeTop.changeGL();
						}
						
					}
					
				}
				else if(hChange)
				{
					if(box1 == null || box2 == null || box3 == null || box1 == "--选择类型--" || box2 == "--选择方位--" || box3 == "--选择颜色--")
					{
						MainFrame.showMessages("您的属性没有填写完整！");
					}
					else
					{
//						System.out.println("Right   hChange这里执行了");
						MyPanelMidBo.canKey = true;
						MyPanelMidBo.nextOne.requestFocus();
						ReloadData.rMarkL.set(MyFrame.ObjectNum, box1+" "+box2+" "+box3+" "+xmlTrunc+" "+xmlCover+" "+xmlDiff+" "+xmlShadow+" ");
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
						
						MyFrame.nPLT = false;
						MyFrame.havePaint = true;
						MyPanelRight.btnMark = true;
						MyFrame.leftMark = true;
						
						MyFrame.pP.repaint();
						MyFrame.mPLB.openAll();
						box1 = null;
						box2 = null;
						box3 = null;
						
						MyPanelRight.objName.setSelectedIndex(0);
						MyPanelRight.objColor.setSelectedIndex(0);
						MyPanelRight.objPose.setSelectedIndex(0);
						MyPanelRight.mfg.setSelected(true);
						MyPanelRight.xmlTrunc = 0;
						MyPanelRight.bkn.setSelected(true);
						MyPanelRight.xmlDiff = 0;
						MyPanelRight.xmlCover = 0;

						MyPanelRight.xmlShadow = 0;
						MyPanelRight.myy.setSelected(true);
						
						MyPanelRight.dmid.setSelected(true);
						MyPanelRight.box1 = null;
						MyPanelRight.box2 = null;
						MyPanelRight.box3 = null;
//						objName.setEnabled(false);
//						objColor.setEnabled(false);
//						objPose.setEnabled(false);
						MyFrame.unEnableCom();
						MyPanelRight.btnMark = true;
						myJB.setEnabled(false);
						MyFrame.editMenu.setEnabled(false);
						MyFrame.mPLB.abAllBtn();
						MyPanelLeTop.changeGL();
						
					}
					
					hChange = false;
				}
				else if(MyFrame.afterPaint && hChange == false)
				{
					if(box1 == null || box2 == null || box3 == null || box1 == "--选择类型--" || box2 == "--选择方位--" || box3 == "--选择颜色--")
					{
						MainFrame.showMessages("您的属性没有填写完整！");
					}
					else
					{
						MyPanelMidBo.canKey = true;
						MyPanelMidBo.nextOne.requestFocus();
						MyFrame.ObjectNumF = MyFrame.ObjectNum;
						MyFrame.ObjectNum = MyFrame.mPLB.lblist.getComponentCount();
//						System.out.println("  Right         MyFrame.afterPaint这里执行了");
						markedR.add(box1+" "+box2+" "+box3+" "+xmlTrunc+" "+xmlCover+" "+xmlDiff+" "+xmlShadow+" ");
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
						MyFrame.nPLT = false;
						MyFrame.havePaint = true;
						MyPanelRight.btnMark = true;
						MyFrame.leftMark = true;
						
//						MyFrame.pP.repaint();
//						MyFrame.mPLB.openAll();
//						MyFrame.mPLB.changeBC();
						MyPanelRight.reLook();
						
//						MyFrame.mP.openRe(bg);
//						MyFrame.mPLT.openAll();    //这里的函数是 做什么操作的，我指的是判断里所有的
//						MyFrame.mPLT.repaint();
						
//						MyFrame.mPLT.changeBC();

						MyFrame.mPLB.openAll();
						MyFrame.mPLB.changeBC();
						
						box1 = null;
						box2 = null;
						box3 = null;
						MyFrame.afterPaint = false;
						
						MyPanelRight.objName.setSelectedIndex(0);
						MyPanelRight.objColor.setSelectedIndex(0);
						MyPanelRight.objPose.setSelectedIndex(0);
						MyPanelRight.mfg.setSelected(true);
						MyPanelRight.xmlTrunc = 0;
						MyPanelRight.bkn.setSelected(true);
						MyPanelRight.xmlDiff = 0;
						MyPanelRight.xmlCover = 0;

						MyPanelRight.xmlShadow = 0;
						MyPanelRight.myy.setSelected(true);
						MyPanelRight.dmid.setSelected(true);
						MyPanelRight.box1 = null;
						MyPanelRight.box2 = null;
						MyPanelRight.box3 = null;
//						objName.setEnabled(false);
//						objColor.setEnabled(false);
//						objPose.setEnabled(false);
						MyFrame.unEnableCom();
						myJB.setEnabled(false);
						MyFrame.editMenu.setEnabled(false);
						MyFrame.mPLB.abAllBtn();
						MyPanelLeTop.changeGL();
//						MyFrame.mPLT.changeGL();
//						MyFrame.mPLT.repaint();
						MyPanelRight.btnMark = true;

								
					}
				}
				else{
					MainFrame.showMessages("您还没有选中区域!");
				
				}
				
				
			}
		});
		myJB.setBounds(12, 360, 140, 20);
		
		MyFrame.mPR.add(myJB);
		myJB.setToolTipText("Alt+S");
		myJB.setMnemonic(KeyEvent.VK_S);

		
		
		MyFrame.mPR.setVisible(true);
	}
	
	public static void initCheckLPR(){
		
		label = new JLabel("请为选框添加标记");
		label.setFont(new Font("宋体",1,11));
		MyFrame.mPR.add(label);
		label.setBounds(30, 0, 140, 20);
		
		comMiss = new JComboBox<>();
		JLabel missLabel = new JLabel("选择漏标数量");
		for(int i = 0; i < 20; i++)
		{
			comMiss.addItem(i);
		}

		comMiss.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ModelProcess.errorFlag = true;
				missNum = (int) comMiss.getSelectedItem();
				ModelProcess.curFilename = MyFrame.newOnePicName;
				ModelProcess.curMiss = missNum;
//				System.out.println("missNum  "+missNum);
				
				FileRead.leaveObjNum[MyFrame.PictureNum] = ModelProcess.curMiss;
				if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0  )
				{
					ModelProcess.haveZero = true;

				}else
					
				try {
					ModelProcess.writeToWrong();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ModelProcess.haveZero = false;
				try {
					
					ModelProcess.writeToLeave();
				
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				if(MyFrame.notOpen)
				{
					if(MainFrame.checkSta == 1)
					{
	//					System.out.println("curMiss    : "+ModelProcess.curMiss);
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
				
//				try {
//					CalcExchange.useCopy();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				CalcExchange.calcFinishShow();
				ModelProcess.errorFlag = false;

//				if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0  )
//				{
//					ModelProcess.haveZero = true;
//
//				}
//				else
//				try {
//					ModelProcess.writeToWrong();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				ModelProcess.haveZero = false;
				try {
					ModelProcess.writeToLeave();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0 && ModelProcess.curMiss == 0 )
				{
//					ModelProcess.haveZero = true
					CalcExchange.calcFinishShow();
					MyPanelLeTop.changeGL();
				}
				if (ModelProcess.curMiss != 0 )
				MyPanelLeTop.changeWR();
				ModelProcess.errorFlag = false;
				OpenNum++;
				CalcExchange.calcFinishShow();
			}
		});
		
		MyFrame.mPR.add(missLabel);
		MyFrame.mPR.add(comMiss);
		missLabel.setBounds(5, 80, 140, 20);
		missLabel.setFont(new Font("宋体",1,11));
		comMiss.setBounds(5, 100, 140, 20);
		
		String aT  = "若全对，点击保存";
		allRight = new JButton(aT);
		allRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean checkTmp = false;
				for(int i = 0; i < ModelProcess.rObjNum.length; i++)
				{
					if(ModelProcess.rObjNum[i] != 0){
						checkTmp = true;
						break;
					}
				}
//				if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0  )
//				{
//					ModelProcess.haveZero = true;
//
//				}
//				else
//					
//				try {
//					ModelProcess.writeToWrong();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				ModelProcess.haveZero = false;

				if(FileRead.wrongObjNuum[MyFrame.PictureNum] != 0 || ModelProcess.curMiss != 0){
					
					MainFrame.showMessages("此图还有错误标记或漏标，请仔细检查！");
				}
				else{
//					ModelProcess.rObjNum[MyFrame.PictureNum] = 0;
					clickRight = true;
					ModelProcess.errorObject = 0;
					System.out.println("MyFrame.notOpen "+MyFrame.notOpen);
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
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					CalcExchange.calcFinishShow();
					MyPanelRight.checkSave = true;

					
					ModelProcess.errorFlag = false;
//					ModelProcess.curMiss = 0;
					
					
//					FileRead.wrongObjNuum[MyFrame.PictureNum] = 0;
//					ModelProcess.curMiss = 0;
	
					if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0 && ModelProcess.curMiss == 0 )
					{
						System.out.println("is True~!!");
						Object obj = null;
						FileName FN = null;
						if(MyFrame.mPLT.fC != null){
							obj = MyFrame.mPLT.fC.getComponent(MyFrame.PictureNum); 
							if(obj instanceof FileName)
						    FN = (FileName)obj;
							FN.setShowt();
						}
						MyPanelLeTop.changeGL();
					}
					clickRight = false;
					
				}
				
				
				
			}
		});
		MyFrame.mPR.add(allRight);
		allRight.setBounds(5, 140, 140, 20);
		
		
	}
	public static void reLook(){
		flagLU = true;
		
		ReloadData.rMarkL = new ArrayList<>();
		MyFrame.rList = new ArrayList<>();
		try {
			ReloadData.reLoadT();
			MyFrame.RPP.repaint();
//			System.out.println("reLook 使用了");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public static boolean checkSaveTxt()		//检查模式下用来判断该图是否已保存过,true 保存过 ，false没保存过
	{
		String nopassFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+ModelProcess.folderName+"_no_pass.txt";

		String passFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+ModelProcess.folderName+"_pass.txt";
		try{
			File fileP = new File(passFilePath);
			FileReader readP = new FileReader(fileP);
	        BufferedReader brP = new BufferedReader(readP);
			
			
	        File fileNP = new File(nopassFilePath);
			FileReader readNP = new FileReader(fileNP);
	        BufferedReader brNP = new BufferedReader(readNP);

	        String line2 = brP.readLine();
	        String line3 = brNP.readLine();
	        
	        while(line2 != null)
	        {
	        	if(line2.split(" ")[0].equals(MyFrame.newOnePicName))
	        		return true;
	        	line2 = brP.readLine();
	        }
	        
	        while(line3 != null)
	        {
	        	if(line3.split(" ")[0].equals(MyFrame.newOnePicName))
	        		return true;
	        	line3 = brNP.readLine();
	        }
	        readP.close();
	        readNP.close();
		}catch(IOException e){
			e.getStackTrace();
		}
		
		return false;
		
	}
	
	
	
	
	
	
	public static boolean showGood(int piv)	//检查模式下用来判断该图是否已保存过,true 保存过 ，false没保存过
	{
		String passFilePath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\"+ModelProcess.folderName+"_pass.txt";
		try{
			File fileP = new File(passFilePath);
			FileReader readP = new FileReader(fileP);
	        BufferedReader brP = new BufferedReader(readP);

	        String line2 = brP.readLine();
	        
	        while(line2 != null)
	        {
	        	if(line2.split(" ")[0].equals(MyFrame.openKey.getOneName(piv)))
	        		return true;
	        	line2 = brP.readLine();
	        }
	        readP.close();
		}catch(IOException e){
			e.getStackTrace();
		}
		
		return false;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
