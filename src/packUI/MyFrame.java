package packUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import packData.CalcExchange;
import packData.DataProcess;
import packData.ModelProcess;
import packData.ReloadData;


public class MyFrame extends JFrame{
	
	public static boolean openCheck = false;
	public static boolean notOpen = false;
	public static String onePicName;
	public static boolean cCheck = false;
	public static int pointRect = 0;
	public static boolean afterPaint = false;
	public static boolean deletePaint = false;
	public static ArrayList<Rectangle> rList = new ArrayList<>();
	public static ArrayList<Rectangle> rListS = new ArrayList<>();
	public static String newOnePicName;
	public static ArrayList<Rectangle> objList = new ArrayList<>();
	public static ArrayList<String> objMark = new ArrayList<>();
	public static ArrayList<Rectangle> list;
	public static ArrayList<String> listS;
	public static Rectangle current;
	public static Point pEnd;
	public static int x_min;
	public static int y_min;
	public static int x_max;
	public static int y_max;
	public static boolean clearAllRect;
	public static RePaintingPanel RPP;
	public static PointPaintRect PPR;
	public static int clearRect = 0;
	public static boolean havePaint = false;
	public static boolean nPLT = false;
	public static boolean leftMark = true;
	public static boolean haveImg = false;
	private Action saveAction;
	private Action saveAsAction;
	private JCheckBoxMenuItem readonlyItem;
	private JPopupMenu popup;				
	public static FileRead openKey = new FileRead();//文件读取器
	public static int PictureNum = 0;		//当前图片ID
	public static int PictureNumF = 0;		//之前的图片ID
	public static int ObjectNum = 0;		//当前框的ID
	public static int ObjectNumF = -1;		//之前框的ID
	public static String r;					//所打开文件夹的路径
	public static MyPanelLeTop mPLT;				//左上方的Panel
	public static MyPanelLeBo mPLB; 		//左下方的Panel
	public static PanelMid mP ;				//中间的Panel
	public static MyPanelMidBo mPMB;						//中间下面的Panel
	public static MyPanelRight mPR;	//右边的Panel
	public static WrongPanel wP;       //错误信息栏
	public static JMenuItem editMenu;
//	public static JLabel goodL = new JLabel(new ImageIcon("image\\good.jpg"));
	public static PaintingPanel pP;
	public static boolean openCX = false;
	public static String rChoose = null;
	public static String tempR;
	
	public MyFrame(){
		
		
		
//		this.setIconImage(new ImageIcon("tIcon.jpg"));
		this.setTitle("车辆标记软件");
		try {
			this.setIconImage(ImageIO.read(this.getClass().getResource("/image/1.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initMyPanelLeTop();
		initMyPanelMidBo();
		initMyPanelLeBo();
		initPanelMid();
		
		if(MainFrame.workerType) 
		{
			initWP();
			this.add(wP);
		}
		mPR  = new MyPanelRight();
		
		this.add(mP);
		this.add(mPLT);
		this.add(mPLB);

		RPP = new RePaintingPanel();
		RPP.setBounds(ATP.leftW, 0,ATP.midW, ATP.midTopH);
		RPP.setOpaque(false);
		
		pP= new PaintingPanel();
		pP.setBounds(ATP.leftW, 0,ATP.midW, ATP.midTopH);
		pP.setOpaque(false);
		
		PPR = new PointPaintRect();
		PPR.setBounds(ATP.leftW, 0,ATP.midW, ATP.midTopH);
		PPR.setOpaque(false);
		
		this.add(PPR);
		this.add(RPP);
		this.add(pP);
		
		this.add(mP);
		this.add(mPMB);
		this.add(mPR);
		
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		Rectangle rect=ge.getMaximumWindowBounds();
		int w=rect.width;
		int h=rect.height;
		this.setBounds((w-ATP.frameW)/2, (h-ATP.frameH)/2, ATP.frameW, ATP.frameH);
//		this.setSize(1000, 700);
		initMenu();
		initPop();
		this.setResizable(false);
		this.setVisible(true);
		if(MainFrame.workerType && MainFrame.pre_worker)
		{
			MyPanelRight.myJB.setEnabled(false);
			MyFrame.unEnableCom();
		}
			
		MyFrame.editMenu.setEnabled(false);
		mPMB.nextOne.setEnabled(false);
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel") ; 
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//				| UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		JOptionPane.showMessageDialog(null, "系统自动在D://MPhoto生成标记图片的数据，标记完所有图片后，上传MPhoto文件即可");
	
	
	
	}
	
	
class RePaintingPanel extends JPanel{
	
	
	public RePaintingPanel(){
		
	}
	
	public void paint(Graphics g) {
		if(true){
			super.paint(g);
			  int x1 = Math.abs(PanelMid.ImgWidth-ATP.midW)/2;
			  int x2 = Math.abs(PanelMid.ImgWidth+ATP.midW)/2;
			  int y1 = Math.abs(PanelMid.ImgHeight-ATP.midTopH)/2;
			  int y2 = Math.abs(PanelMid.ImgHeight+ATP.midTopH)/2;
//			  System.out.println("RPP");
			  g.setColor(Color.CYAN);
			  
			  Graphics2D g2d = (Graphics2D)g;
//			  g2d.setStroke(new BasicStroke(2f));
			  g.setFont(new Font("Tahoma", Font.BOLD, 11));
			  
			  int i = 0;
			  if(!rList.isEmpty()){
				  for (Rectangle rect : rList) {
						//   g.fillRect(rect.x, rect.y, rect.width, rect.height);
//							  System.out.println("进行了查看重绘");
							  g.drawRect(rect.x+x1, rect.y+y1, rect.width, rect.height);
//							  g.setColor(Color.YELLOW);
//							  String str[] = ReloadData.rMarkL.get(i).split(" ");
////							  
////							  String hc = i+1+" "+str[0]+" "+str[1]+" "+str[2]+" "+ str[3]+" "+str[4];
////							  String hc = i+1 +" "+ReloadData.rMarkL.get(i);
//							  String tt = "";
//							  tt = str[1].substring(0, 2);
//							  String hc = i+1+" "+str[0]+" "+tt+" "+str[2]+" ";
//							  String h2 = str[3]+" "+str[4]+" "+str[5]+" "+str[6];
//							  
//							  if(rect.y == 0)
//							  {
//								  g.drawString(hc, rect.x+x1,11+y1);
//								  g.drawString(h2, rect.x+x1,22+y1);
//							  }
//							  else{
//								  g.drawString(hc, rect.x+x1, rect.y+y1);
//								  g.drawString(h2, rect.x+x1,rect.y+y1+11);
//							  }
//								  
//							  g.setColor(Color.CYAN);
							  i++;
						  }
			  }
			  
		}
		}  
		
	
}


class PaintingPanel extends JPanel {
	 
//	 ArrayList<Rectangle> list;
//	 Rectangle current;
	Point endPoint;
	
	 public PaintingPanel() {
	  list = new ArrayList<Rectangle>();
	  listS = new ArrayList<String>();
	  if(MainFrame.workerType)
	  {
		  addMouseMotionListener(mouseHandler);
		  addMouseListener(mouseHandler);
	  }
	  
	 }
	 
	 MouseInputListener mouseHandler = new MouseInputAdapter() {
	  Point startPoint;
	  
		public void mouseExited(MouseEvent e) {
			MainFrame.myFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	  
		public void mouseMoved(MouseEvent e) {
			if(MainFrame.workerType)
			{
				endPoint = e.getPoint();
				int x1 = Math.abs(PanelMid.ImgWidth-ATP.midW)/2;
				  int x2 = Math.abs(PanelMid.ImgWidth+ATP.midW)/2;
				  int y1 = Math.abs(PanelMid.ImgHeight-ATP.midTopH)/2;
				  int y2 = Math.abs(PanelMid.ImgHeight+ATP.midTopH)/2;
				  if( e.getX()<x1 || e.getX()> x2 || e.getY()<y1 || e.getY()>y2 )
				  {	  
					  MainFrame.myFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					  endPoint = null;
					  repaint();
				  }
				  if( e.getX()>=x1 && e.getX()<=x2 && e.getY()>y1 && e.getY()<=y2)
				  {
					  MainFrame.myFrame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(this.getClass().getResource("/image/mouse_1.png")).getImage(),new Point(15, 15),"closs"));		
					  repaint();
				  }
			}
			  
			}
		
		public void mouseEntered(MouseEvent e) {
//			System.out.println("Entered");
//			if( e.getX()>=0 && e.getX()<=PanelMid.ImgWidth && e.getY()>0 && e.getY()<=PanelMid.ImgHeight)
//			MainFrame.myFrame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
	  
	  public void mousePressed(MouseEvent e) {
		  if(MainFrame.workerType && SwingUtilities.isLeftMouseButton(e))
		  {

		  
		  MyPanelRight.hChange = false;
		  
					try {
						if(ReloadData.canCheck())
						{
//							System.out.println("这个被标记onepictureName:"+onePicName);
//							JOptionPane.showMessageDialog(null, "图片已标记，请勿重复此操作！");
							afterPaint = true;
							
							
//							System.out.println("afterPaint : "+afterPaint);
							if(leftMark == true)
							{
								restSaveData();
								startPoint = e.getPoint();
//								startPoint = new Point(x, y)
								current = new Rectangle();
							}
//						
							else
							{
								MainFrame.showMessages("请先保存该对象！");
							}
						}
						else
						{
//							if(clearRect == 0 || clearRect%2 == 1)
//							{
								if(leftMark == true)
								{
									restSaveData();
									startPoint = e.getPoint();
									current = new Rectangle();
								}
//							}
							else
							{
								MainFrame.showMessages("请先保存该对象！");
							}
							
							
						}
					} catch (HeadlessException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			  
	  		}
	  }

	  public void mouseReleased(MouseEvent e) {
		  if(MainFrame.workerType && SwingUtilities.isLeftMouseButton(e))
		  {
			  
			  makeRectangle(startPoint, e.getPoint());
			   if(current.width > 0 && current.height > 0) {
//			    list.add(current);
				list.add(current);
				listS.add(x_min+" "+y_min+" "+x_max+" "+y_max);
//			    System.out.println(x_min+" "+y_min+" "+x_max+" "+y_max);
			    current = null;
			    MyFrame.clearRect++; 
			    pP.repaint();
			    restCom();
			    nPLT = false;
			    leftMark = false;
			    havePaint = true;
			    restSaveData();
			  
			    editMenu.setEnabled(true);
			    MyPanelRight.myJB.setEnabled(true);
//			    ObjectName.delect.setEnabled(false)
			    MyFrame.enableCom();
			    mPLB.unAllBtn();
			   }
		  }
	   
	  }

//	  else if(SwingUtilities.isRightMouseButton(e))
//	  {
//		  
//		  int li = list.size()-1;
//		  boolean btmp;
//		  if(MyFrame.clearRect > 0)
//			  btmp = true;
//		  else
//			  btmp = false;
//		  		try{
//		  			list.remove(li);
//					  listS.remove(li);
//					  if(MyPanelRight.btnMark)
//					  {
//						  MyPanelRight.markedR.remove(li);
//						  MyPanelRight.btnMark = false;
//					  }
//					 
//					  
//					  li--;
//					  MyFrame.havePaint = false;
//				  repaint();
//				  
//		  		}catch(ArrayIndexOutOfBoundsException e1){
//		  			System.out.println(e1);
//		  		}
//	  }
	 
	  
	  public void mouseDragged(MouseEvent e) {
		  if(MainFrame.workerType && SwingUtilities.isLeftMouseButton(e))
		  {
			  endPoint = e.getPoint();
			  int x1 = Math.abs(PanelMid.ImgWidth-ATP.midW)/2;
			  int x2 = Math.abs(PanelMid.ImgWidth+ATP.midW)/2;
			  int y1 = Math.abs(PanelMid.ImgHeight-ATP.midTopH)/2;
			  int y2 = Math.abs(PanelMid.ImgHeight+ATP.midTopH)/2;
			  if( e.getX()<x1 || e.getX()> x2 || e.getY()<y1 || e.getY()>y2 )
					MainFrame.myFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			  if( e.getX()>=x1 && e.getX()<=x2 && e.getY()>y1 && e.getY()<=y2)
					MainFrame.myFrame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(this.getClass().getResource("/image/mouse_1.png")).getImage(),new Point(13,13),"closs"));

			  if (current != null && startPoint.getX() >=  x1 && startPoint.getY() >= y1 && startPoint.getX()<= x2 && startPoint.getY() <= y2) {
			    makeRectangle(startPoint, e.getPoint());
			    pP.repaint();
			   }
			  else{
				  if(startPoint.getX() <  x1)
				  {
					  startPoint.x = x1;
				  }
				  if(startPoint.getY() <  y1)
				  {
					  startPoint.y = y1;
				  }
				  if(startPoint.getX() > x2)
				  {
					  startPoint.x = x2;
				  }
				  if(startPoint.getY() > y2)
				  {
					  startPoint.y = y2;
				  }
			  }
		  }
	   
	  }
	  
	  private void makeRectangle(Point p1, Point p2) {
		  int w = 0;
		  int h = 0;

		  int x1 = Math.abs(PanelMid.ImgWidth-ATP.midW)/2;
		  int x2 = Math.abs(PanelMid.ImgWidth+ATP.midW)/2;
		  int y1 = Math.abs(PanelMid.ImgHeight-ATP.midTopH)/2;
		  int y2 = Math.abs(PanelMid.ImgHeight+ATP.midTopH)/2;
	      
//		  if(p1.x < 0)
		  
		   if(p2.x < x1) p2.x = x1;
		   if(p2.y < y1) p2.y = y1;
		   if(p2.x - p1.x > x2 - p1.x) p2.x = x2;
		   if(p2.y - p1.y > y2 - p1.y) p2.y = y2;
		   
	   int x = Math.min(p1.x, p2.x);
	   int y = Math.min(p1.y, p2.y);
	   w = Math.abs(p1.x - p2.x);
	   h = Math.abs(p1.y - p2.y);
	   

	   
	   if(x < x1) x = x1;
	   if(y < y1) y = y1;
	   if(w > x2 - x) w = x2 - x;
	   if(h > y2 - y) h = y2 - y;
//	   if(Math.abs(Math.abs(p2.x)-p1.x) > PanelMid.ImgWidth){
//		   
//	   }
	   x_min = Math.min(p1.x-x1, p2.x-x1);
	   y_min = Math.min(p1.y-y1, p2.y-y1);
	   x_max = Math.max(p1.x-x1, p2.x-x1);
	   y_max = Math.max(p1.y-y1, p2.y-y1);
	   
	   
	   current.setBounds(x, y, w, h);
	  }
	 };
	 
	 public void paint(Graphics g) {
		  if(MainFrame.workerType)
		  {
			  super.paint(g);
			  g.setColor(Color.CYAN);
			  
			  int x1 = Math.abs(PanelMid.ImgWidth-ATP.midW)/2;
			  int x2 = Math.abs(PanelMid.ImgWidth+ATP.midW)/2;
			  int y1 = Math.abs(PanelMid.ImgHeight-ATP.midTopH)/2;
			  int y2 = Math.abs(PanelMid.ImgHeight+ATP.midTopH)/2;
			  
//			  System.out.println("pP");
			  Graphics2D g2d = (Graphics2D)g;
//			  g2d.setStroke(new BasicStroke(2f));
			  g.setFont(new Font("Tahoma", Font.BOLD, 11));
			  int j = 0;
			  //************画虚线**************
			  if(endPoint != null)
			  {
				  g2d.setColor(new Color(237,255,100));
				  Line2D lineX = new Line2D.Double(x1, endPoint.getY(), x2, endPoint.getY());
				  Line2D lineY = new Line2D.Double(endPoint.getX(), y1, endPoint.getX(), y2);
				  g2d.draw(lineX);
				  g2d.draw(lineY);
				  
				  
				  g2d.setColor(Color.CYAN);
			  }
				  
			  
			  //*******************************
			  for (Rectangle rect : list) {
			//   g.fillRect(rect.x, rect.y, rect.width, rect.height);
				  g.drawRect(rect.x, rect.y, rect.width, rect.height);												//！！！！！！！！！！这个什么东西有问题吗？
				  if(!MyPanelRight.markedR.isEmpty()){
					  j++;
				  }

				  
			  }
			  
			  if (current != null) {
			   g.drawRect(current.x, current.y, current.width, current.height); 
			   
			  }
			 }
		  }
	  
	 
	}




class PointPaintRect extends JPanel{
	public PointPaintRect(){
		
	}
	
	public void paint(Graphics g) {
		  int x1 = Math.abs(PanelMid.ImgWidth-ATP.midW)/2;
		  int x2 = Math.abs(PanelMid.ImgWidth+ATP.midW)/2;
		  int y1 = Math.abs(PanelMid.ImgHeight-ATP.midTopH)/2;
		  int y2 = Math.abs(PanelMid.ImgHeight+ATP.midTopH)/2;
		  super.paint(g);
		  g.setColor(Color.CYAN);
//		  System.out.println("PPR");
		  Graphics2D g2d = (Graphics2D)g;
//		  g2d.setStroke(new BasicStroke(2f));
		  g.setFont(new Font("Tahoma", Font.BOLD, 11));
		  
		  int i = 0;
//		  System.out.println("这里执行了");
		  if(!rList.isEmpty()){
			  for (Rectangle rect : rList) {
		  		if(i == ObjectNum)
		  		{	  
//		  			  System.out.println("进行了特征标记，objNum:"+ObjectNum);
		  			  g.setColor(Color.RED);
//		  			  System.out.println(rect.x+" "+ rect.y+" "+ rect.width+" "+ rect.height);
		  			  g.drawRect(rect.x+x1, rect.y+y1, rect.width, rect.height);
		  			  g.setColor(Color.CYAN);
					  g.setColor(Color.YELLOW);
					  String str[] = ReloadData.rMarkL.get(i).split(" ");
//					  String hc = ObjectNum+1+" "+str[0]+" "+str[1]+" "+str[2];
//					  String hc = ObjectNum+1+" "+ReloadData.rMarkL.get(i);
					  
//					  String tt = "";
//					  tt = str[1].substring(0, 2);
					  String hc = i+1+" "+str[0]+" "+str[1]+" "+str[2]+" ";
//					  String h2 = str[3]+" "+str[4]+" "+str[5]+" "+str[6];
					  
					  String s1 = "", s2 = "", s3 = "", s4 = "";
					  if(Integer.parseInt(str[3]) == 1) s1 = "truncated";
					  if(Integer.parseInt(str[4]) == 1) s2 = "<50%";
					  if(Integer.parseInt(str[5]) == 1) s3 = "difficult";
					  if(Integer.parseInt(str[6]) == 1) s4 = "shadow";				  
					  
					  String h2 = s1+" "+s2+" "+s3+" "+s4;
					  
					  
					  
					  
					  if(rect.y == 0)
					  {
						  g.drawString(hc, rect.x+x1,11+y1);
						  g.drawString(h2, rect.x+x1,22+y1);
					  }
					  else{
						  g.drawString(hc, rect.x+x1, rect.y+y1);
						  g.drawString(h2, rect.x+x1,rect.y+y1+11);
					  }
					  g.setColor(Color.CYAN);
//					  System.out.println(hc);
					  i++;
		  		}
		  		else{
//		  			System.out.println("没有进行了特征标记，objNum:"+i);
		  			 g.drawRect(rect.x+x1, rect.y+y1, rect.width, rect.height);
//					  g.setColor(Color.YELLOW);
//					  
//					  String str[] = ReloadData.rMarkL.get(i).split(" ");
//					  String tt = "";
//					  tt = str[1].substring(0, 2);
//					  String hc = i+1+" "+str[0]+" "+tt+" "+str[2]+" ";
//					  String h2 = str[3]+" "+str[4]+" "+str[5]+" "+str[6];
//					  
//					  if(rect.y == 0)
//					  {
//						  g.drawString(hc, rect.x+x1,11+y1);
//						  g.drawString(h2, rect.x+x1,22+y1);
//					  }
//					  else{
//						  g.drawString(hc, rect.x+x1, rect.y+y1);
//						  g.drawString(h2, rect.x+x1,rect.y+y1+11);
//					  }
//					  
//					  g.setColor(Color.CYAN);
					  i++;
		  		}
				 
			 	}
		  }
		  
	}
}


class TestAction extends AbstractAction
{
	public TestAction(String name)
	{
		super(name);
	}
	public void actionPerformed(ActionEvent event)
	{
//		System.out.println(getValue(Action.NAME)+" selected.");
	}
}


//初始化菜单栏函数
public void initMenu(){
	JMenu fileMenu = new JMenu(new TestAction("文件"));
	
	JMenuItem openItem = fileMenu.add(new TestAction("打开文件"));
	openItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.isOpen = true;
			
			notOpen = false;
			MyPanelRight.missNum  = 0;
			ModelProcess.sumObject = 0;
			
			ModelProcess.indexM = 0;
			ChoseCheck cC = new ChoseCheck();
			r = cC.getDIR();
			
			
			String rTemp[] = r.split("\\\\");
			ModelProcess.folderName = rTemp[rTemp.length-1];

			
			
			
//			CalcExchange.addEc();//记录模式切换的次数,用来判断追加和覆写模式
//			CalcExchange.judgeType = CalcExchange.judegNPList();
//			System.out.println(r);
			openKey.clearAll();
			openKey.readAll(r);
			

			
			
//			mPLT.changeGL();
			if(openKey.getSize()>0){
//				String makrs = openKey.getOnePic(0);
////				System.out.println("makrs  :  "+makrs);
//				System.out.println("m - r : " + makrs - rChoose);
//				
				//****生成目录******
				
				DataProcess.mdir();			
//				DataProcess.mdirBack();
				
				//************************************
				
				haveImg = true;
				

				afterPaint = false;
				String bg = openKey.getOnePic(0);
//				System.out.println("test"+ bg);
				PictureNum = 0;
				PictureNumF = 0;
				ObjectNum = 0;
				ObjectNumF = 0;
				
				String arrBgTemp = bg;
				String arrBg[] = arrBgTemp.split("\\\\");
				arrBgTemp = arrBg[arrBg.length-1];
				onePicName = arrBgTemp;
				newOnePicName = arrBgTemp;
				ModelProcess.curFilename = newOnePicName ;
				if(!rList.isEmpty())
				{
					rList.clear();
					ReloadData.rMarkL.clear();
				}
				MyPanelRight.reLook();
				//判断模式
				if(MainFrame.workerType)
				{
					try{
						MyPanelRight.myJB.setEnabled(false);
						String txtPath = MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt";
				        
	//					checkXml(new File(pathAnno));
						
						File file = new File(txtPath);
						FileReader read = new FileReader(file);
						BufferedReader br = new BufferedReader(read);
						String line = br.readLine();
						if(line != null && line.split(" ")[1] != null){
//							MainFrame.userID = line.split(" ")[1];
						}
						read.close();
						br.close();
					}catch(IOException e2){
						e2.getStackTrace();
					}
					
				}
				else
				{

					ModelProcess.spList = new ArrayList<>();
					ModelProcess.snpList = new ArrayList<>();
					MyPanelRight.checkSave = MyPanelRight.checkSaveTxt();
//					if(MyPanelRight.checkSave)
//					{
////						MyPanelRight.allRight.setText("已保存，点击修改");
//						MyPanelRight.allRight.setText("若全对，点击保存");
//					}
//					else{
//						MyPanelRight.allRight.setText("若全对，点击保存");
//					}
//					if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0  )
//					{
//						ModelProcess.haveZero = true;
//
//					}
//					else 
					try {
						ModelProcess.writeToWrong();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ModelProcess.haveZero = false;
					try {
						
						ModelProcess.writeToLeave();
						ModelProcess.writeTorobjnum();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					 try {
							ModelProcess.getIndexM();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					 
						if(ModelProcess.indexM == 0)
						 {
							MyPanelRight.comMiss.setEnabled(true);
							MyPanelRight.comMiss.setSelectedIndex(0);
							MyPanelRight.allRight.setEnabled(true);
						 }
						else{
							MyPanelRight.comMiss.setEnabled(true);
							MyPanelRight.allRight.setEnabled(true);
							MyPanelRight.missNum = ModelProcess.indexM;
							MyPanelRight.comMiss.setSelectedIndex(ModelProcess.indexM);
							ModelProcess.curMiss = (int) MyPanelRight.comMiss.getSelectedItem();
//							System.out.println("......"+ModelProcess.indexM+".....");
						}
//						CalcExchange.calcFinishShow();
						
						CalcExchange.calcFinishShow();
						//========================
						
						try {
							ModelProcess.showCurCnt();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						if(MainFrame.strS.equals("进行新一轮检查")){		//进行新一轮检查
							System.out.println("checkStaNum   " +MainFrame.checkStaNum);
							if(MainFrame.checkStaNum == 0)
							{
								MainFrame.checkStaNum += 1;
								MainFrame.checkSta = 1;
							}
							else
							{
								MainFrame.checkStaNum += 1;
								MainFrame.checkSta = 2;
							}
						}
						if(MainFrame.strS.equals("未完成本次检查，继续检查")){
							System.out.println("checkStaNum   " +MainFrame.checkStaNum);
							MainFrame.checkSta = 2;
						}
						
						//========================
				}
				
				mP.openRe(bg);
				mPLT.openAll();
				mPLT.repaint();
				mPLT.changeBC();
				
				mPLB.openAll();
				mPLB.changeBC();
				
				try {
					ModelProcess.writelistWrongMess();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!ModelProcess.listWrongMess.isEmpty() && MainFrame.workerType ) wP.openAll();
				
				MyFrame.editMenu.setEnabled(false);
				mPMB.nextOne.setEnabled(true);
//				PPR.repaint();
				
//				DataProcess.openCheckXml();
				MyFrame.notOpen = true;
				openCheck = true;
//				openCX = true;
//				test1.openCheckXml();
				MyPanelMidBo.nextOne.requestFocus();
			}
			else
			{
				
				MainFrame.showMessages("所选文件夹未包含任何图片！");
			}
		}
		
	});
	
//	JMenuItem chModel = fileMenu.add(new TestAction("选择模式"));
//	chModel.addActionListener(new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			MainFrame.setCheck();
//		}
//	});		
//	
	fileMenu.addSeparator();
//	saveAction = new TestAction("保存");
//	JMenuItem saveItem = fileMenu.add(saveAction);
	
	
//	fileMenu.add(new AbstractAction("保存") {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			try {
//				saveData();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//	});
	
//	saveAsAction = new TestAction("另存为");
//	fileMenu.add(saveAsAction);
//	fileMenu.addSeparator();
	
	fileMenu.add(new AbstractAction("退出") {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
				if(MainFrame.workerType)
				{
					try {
						File file1 = new File(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\data\\ImgCache.txt");
						if(file1.exists())
							file1.delete();
						CalcExchange.copyFile(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\data\\ImgCache.txt", MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\data\\ImgCache.txt");
						
						File file2 = new File(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\logos\\Annotations");
						if(file2.exists())
							CalcExchange.delDir(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\logos\\Annotations");
						CalcExchange.FileAllCopy(MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto"+"\\logos\\Annotations",MyFrame.r+"\\"+ModelProcess.folderName+"_MPhoto-try"+"\\logos\\Annotations");
					
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
				        		sTmp1 += copyList.get(i)+System.getProperty("line.separator");
				        	}
				        	else
				        	{
				        		sTmp2 += copyList.get(i)+System.getProperty("line.separator");
				        	}
				        	sTmp += copyList.get(i)+System.getProperty("line.separator");
				        	
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
						br.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			System.exit(0);
			
		}
	});
	

	
	 editMenu = new JMenuItem(new TestAction("撤销"));
	 editMenu.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			clearRect--;
			int li = clearRect;
			  boolean btmp;
			  if(MyFrame.clearRect >= 0)
				  btmp = true;
			  else
				  btmp = false;
			  System.out.println("MyFrame.clearRect  ： "+MyFrame.clearRect);
			  		try{
			  			  list.remove(li);
						  listS.remove(li);
						  
						 if(MyPanelRight.markedR.size()>0)
						 {
							 MyPanelRight.markedR.remove(li);
						 }
						  
						  li--;

					  repaint();

					  MyPanelRight.btnMark = false;
					  MyFrame.havePaint = false;
					  MyFrame.leftMark = true;
			  		}catch(ArrayIndexOutOfBoundsException e1){
			  			System.out.println(e1);
			  		}
			  		if(MainFrame.workerType && MainFrame.pre_worker)
			  		{
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
	
						MyPanelRight.btnMark = true;
						MyFrame.unEnableCom();
						MyPanelRight.myJB.setEnabled(false);
			  		}
			  		
					
					MyFrame.mPLB.abAllBtn();
					editMenu.setEnabled(false);
		}

	});
//	editMenu.add(new AbstractAction("撤销") {
//		
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//	});
	
	
//	JMenu optionMenu = new JMenu("选项");
//	
//	optionMenu.add(readonlyItem);
//	optionMenu.addSeparator();
//	optionMenu.add(insertItem);
//	optionMenu.add(overtypeItem);
	
//	editMenu.addSeparator();
//	editMenu.add(optionMenu);
	
//	JMenu helpMenu = new JMenu("帮助");
//	helpMenu.setMnemonic('H');
//	
//	JMenuItem indexItem = new JMenuItem("首页");
//	indexItem.setMnemonic('I');
//	helpMenu.add(indexItem);
//	
//	Action aboutAction = new TestAction("关于");
//	aboutAction.putValue(Action.MNEMONIC_KEY, new Integer('A'));
//	helpMenu.add(aboutAction);
	
	JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	
	menuBar.add(fileMenu);
	menuBar.add(editMenu);
//	menuBar.add(helpMenu);
	
}

//
public void initPop(){

	PopAction upPage = new PopAction("上一张"){
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			super.actionPerformed(e);
			up_Page();	
		}
	};
	PopAction downPage = new PopAction("下一张"){
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			super.actionPerformed(e);
			try {
				next_Page();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	
//	Action cutAction = new TestAction("剪切");
//	cutAction.putValue(Action.SMALL_ICON, new ImageIcon("cut.gif"));
//	Action copyAction = new TestAction("复制");
//	copyAction.putValue(Action.SMALL_ICON, new ImageIcon("copy.gif"));
//	Action pasteAction = new TestAction("粘贴");
//	pasteAction.putValue(Action.SMALL_ICON, new ImageIcon("paste.gif"));
	popup = new JPopupMenu();
//	popup.add(upPage);
//	popup.add(downPage);
//	popup.add(cutAction);
//	popup.add(copyAction);
//	popup.add(pasteAction);
	
	JPanel panel= new JPanel();
	panel.setComponentPopupMenu(popup);
	add(panel);
	
	panel.addMouseListener(new MouseAdapter() {
	});
	
}


//初始化中间下面的Panel
private void initMyPanelMidBo() {
	mPMB = new MyPanelMidBo(){
		@Override
		public void nextPage() {
		//	 TODO Auto-generated method stub
			super.nextPage();
			try {
				
				next_Page();
//				MyPanelRight.btnMark = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
}

//初始化左上面Panel
private void initMyPanelLeTop() {
	 mPLT = new MyPanelLeTop(){
		 @Override
		public void getfiles(int i){
			 if(MainFrame.workerType && MainFrame.pre_worker)
				 MyPanelRight.myJB.setEnabled(false);
			 	
			 ModelProcess.indexM = 0;
			 MyPanelRight.missNum  = 0;
			 	afterPaint = false;
			 	
			 	PictureNumF = PictureNum;
				PictureNum = i;
				ObjectNum = 0;
				ObjectNumF = 0;
				
				String bg = openKey.getOnePic(PictureNum);
				
				String arrBgTemp = bg;
				String arrBg[] = arrBgTemp.split("\\\\");
				arrBgTemp = arrBg[arrBg.length-1];
				onePicName = arrBgTemp;
				newOnePicName = arrBgTemp;
				 if(!MainFrame.workerType)
				 {
					 notOpen = false;
					 MyPanelRight.checkSave = MyPanelRight.checkSaveTxt();
//					 if(MyPanelRight.checkSave)
//						{
////							MyPanelRight.allRight.setText("已保存，点击修改");
//							MyPanelRight.allRight.setText("若全对，点击保存");
//						}
//						else{
//							MyPanelRight.allRight.setText("若全对，点击保存");
//						}
					 FileRead.leaveObjNum[MyFrame.PictureNum] = ModelProcess.curMiss;
						if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0  )
						{
							ModelProcess.haveZero = true;

						}
						else
						try {
							ModelProcess.writeToWrong();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ModelProcess.haveZero = false;
						try {
							
							ModelProcess.writeToLeave();
							ModelProcess.writeTorobjnum();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					 try {
//						 ModelProcess.writeTorobjnum();
						 ModelProcess.indexM = 0;
						ModelProcess.getIndexM();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(ModelProcess.indexM == 0)
					 {
						MyPanelRight.comMiss.setSelectedIndex(0);
					 }
					else{
						MyPanelRight.missNum = ModelProcess.indexM;
						MyPanelRight.comMiss.setSelectedIndex(ModelProcess.indexM);
						ModelProcess.curMiss = (int) MyPanelRight.comMiss.getSelectedItem();
					}
					MyPanelRight.OpenNum = 0;
							
				 }
				 else
				 {
					 //标记者
				 }
//				if(!rList.isEmpty())
//				{
					rList.clear();
					ReloadData.rMarkL.clear();
//				}	
				MyPanelRight.reLook();
				
				if(bg != null)
				{
					mP.openRe(bg);
					cleatAllList();
					this.changeBC();
					
				}
				

				mPLB.openAll();
				mPLB.changeBC();
				MyFrame.notOpen = true;
		}
	 };
}

//初始化左下角的Panel
private void initMyPanelLeBo(){
	
	mPLB = new MyPanelLeBo(){
		@Override
		public void getOneRect(int k) {
			// TODO Auto-generated method stub
			//k是当前object的ID
			super.getOneRect(k);
//			System.out.println("选中了   "+ k);
			ObjectNumF = ObjectNum;
			ObjectNum = k;
			PPR.repaint();
			this.changeBC();
		}
		
		@Override
		public void edit(int k) {
			// TODO Auto-generated method stub
			super.edit(k);
			getOneRect(k);
//			String sTmp = ReloadData.rMarkL.get(k);
////			System.out.println("sTmp: "+sTmp);
//			MyPanelRight.markTS = sTmp.split(" ");
//			
//			MyPanelRight.objName.setSelectedItem(MyPanelRight.markTS[0]);
//			
//			MyPanelRight.objPose.setSelectedItem(MyPanelRight.markTS[1]);
//			MyPanelRight.objColor.setSelectedItem(MyPanelRight.markTS[2]);
////			System.out.println(MyPanelRight.markTS[3]+"||"+MyPanelRight.markTS[3]);
//			int ttr = Integer.parseInt(MyPanelRight.markTS[3]);
//			int tco = Integer.parseInt(MyPanelRight.markTS[4]);
//			int tdi = Integer.parseInt(MyPanelRight.markTS[5]);
//			int tsh = Integer.parseInt(MyPanelRight.markTS[6]);
//			
//			MyPanelRight.xmlTrunc = ttr;
//			MyPanelRight.xmlCover = tco;
//			MyPanelRight.xmlDiff = tdi;
//			MyPanelRight.xmlShadow = tsh;
//			
//			
//			if(MyPanelRight.xmlDiff == 1){
//				MyPanelRight.kn.setSelected(true);
//			}
//			else
//			{
//				MyPanelRight.bkn.setSelected(true);
//			}
//			
//			if(MyPanelRight.xmlTrunc == 1){
//				MyPanelRight.fg.setSelected(true);
//			}
//			else
//			{
//				MyPanelRight.mfg.setSelected(true);
//			}
//			
//			if(MyPanelRight.xmlCover == 1){
//				MyPanelRight.xmid.setSelected(true);
//			}
//			else
//			{
//				MyPanelRight.dmid.setSelected(true);
//			}
//			
//			if(MyPanelRight.xmlShadow == 1){
//				MyPanelRight.yy.setSelected(true);
//			}
//			else
//			{
//				MyPanelRight.myy.setSelected(true);
//			}
//			
//			MyPanelRight.box1 = MyPanelRight.markTS[0];
//			MyPanelRight.box2 = MyPanelRight.markTS[1];
//			MyPanelRight.box3 = MyPanelRight.markTS[2];
//			
//			MyFrame.unEnableCom();
//			MyPanelRight.myJB.setEnabled(true);
			
		}
		
		@Override
		public void delete(int k) {
			// TODO Auto-generated method stub
			super.delete(k);
			getOneRect(k);
			
		}
	};
}

public void initPanelMid(){
	mP = new PanelMid();		
}

//下一张图片
public void next_Page() throws IOException{
//	try {
//		Thread.sleep(1000);
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	MyPanelRight.missNum  = 0;
	afterPaint = false;
	ModelProcess.indexM = 0;
	 MyPanelRight.missNum  = 0;
	 if(!MainFrame.workerType)
	 {
		 if(PictureNum<openKey.getSize()-1)
			{
				
				PictureNumF = PictureNum;
				PictureNum++;
				ObjectNum = 0;
				ObjectNumF = 0;
				
				String bg = openKey.getOnePic(PictureNum);

				String arrBgTemp = bg;
				String arrBg[] = arrBgTemp.split("\\\\");
				arrBgTemp = arrBg[arrBg.length-1];
				onePicName = arrBgTemp;
				newOnePicName = arrBgTemp;
//				if(!rList.isEmpty())
//				{
					rList.clear();
					ReloadData.rMarkL.clear();
//				}	
				MyPanelRight.reLook();
				if(bg != null)
				{
					mP.openRe(bg);
					cleatAllList();
					mPLB.openAll();
					mPLB.changeBC();
					//根据当前点击的组件改变滚动条的位置
					MyPanelLeTop.jP.getVerticalScrollBar().setValue(MyFrame.PictureNum*ATP.objectH-ATP.objectH*8);
					mPLT.changeBC();
				}
				
			}
		 
		 notOpen = false;
		 MyPanelRight.checkSave = MyPanelRight.checkSaveTxt();
//		 if(MyPanelRight.checkSave)
//			{
////				MyPanelRight.allRight.setText("已保存，点击修改");
//				MyPanelRight.allRight.setText("若全对，点击保存");
//			}
//			else{
//				MyPanelRight.allRight.setText("若全对，点击保存");
//			}
		 FileRead.leaveObjNum[MyFrame.PictureNum] = ModelProcess.curMiss;
			if (FileRead.wrongObjNuum[MyFrame.PictureNum] == 0  )
			{
				ModelProcess.haveZero = true;

			}
			else 
			try {
				ModelProcess.writeToWrong();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ModelProcess.haveZero = false;
			try {
				
				ModelProcess.writeToLeave();
				ModelProcess.writeTorobjnum();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		 
		 try {
//			 ModelProcess.writeTorobjnum();
			 ModelProcess.indexM = 0;
			ModelProcess.getIndexM();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("indexM " + ModelProcess.indexM);
		if(ModelProcess.indexM == 0)
		 {
			MyPanelRight.comMiss.setSelectedIndex(0);
		 }
		else{
			MyPanelRight.missNum = ModelProcess.indexM;
			MyPanelRight.comMiss.setSelectedIndex(ModelProcess.indexM);
			ModelProcess.curMiss = (int) MyPanelRight.comMiss.getSelectedItem();
		}
		MyPanelRight.OpenNum = 0;
	 }
	 else
	 {
		 
	 }
	if(MyFrame.havePaint == true)
	{
		if(MyPanelRight.btnMark == false){
			  MainFrame.showMessages("您还没有保存！！");
		  }
		else
		{
			if(PictureNum<openKey.getSize()-1)
			{
		
				String bg = openKey.getOnePic(PictureNum);
				String arrBgTemp = bg;
				String arrBg[] = arrBgTemp.split("\\\\");
				arrBgTemp = arrBg[arrBg.length-1];
				onePicName = arrBgTemp;

				PictureNumF = PictureNum;
				PictureNum++;
				ObjectNum = 0;
				ObjectNumF = 0;

				bg = openKey.getOnePic(PictureNum);
				String arrBgTemp2 = bg;
				String arrBg2[] = arrBgTemp2.split("\\\\");
				arrBgTemp2 = arrBg2[arrBg2.length-1];
				
				newOnePicName = arrBgTemp2;
//				if(!rList.isEmpty())
//				{
					rList.clear();
					ReloadData.rMarkL.clear();
//				}
				MyPanelRight.reLook();
				
				if(bg != null)
				{
					mP.openRe(bg);		

					mPLB.openAll();
					mPLB.changeBC();
					//根据当前点击的组件改变滚动条的位置
					MyPanelLeTop.jP.getVerticalScrollBar().setValue(MyFrame.PictureNum*ATP.objectH-ATP.objectH*8);
					mPLT.changeBC();
				}
			}
//			if(MyPanelRight.markedR.size() == list.size() && list.size() != 0)
//			{
//				test1.dataToCache();
//				test1.txtToXML(test1.pathData+"\\imgCache.txt");
//				test1.dataToTxt(test1.testTxt);
//				cleatAllList();
				MyPanelRight.btnMark = false;
				MyFrame.havePaint = false;
//			}
			cleatAllList();
			if(MainFrame.workerType && MainFrame.pre_worker)
				MyPanelRight.myJB.setEnabled(false);
			MyFrame.unEnableCom();
//			saveData();
			
		}
		
	}
	else
	{
		if(MainFrame.workerType)
		{
			if(PictureNum<openKey.getSize()-1)
			{
				
				PictureNumF = PictureNum;
				PictureNum++;
				ObjectNum = 0;
				ObjectNumF = 0;
				
				String bg = openKey.getOnePic(PictureNum);

				String arrBgTemp = bg;
				String arrBg[] = arrBgTemp.split("\\\\");
				arrBgTemp = arrBg[arrBg.length-1];
				onePicName = arrBgTemp;
				newOnePicName = arrBgTemp;
//				if(!rList.isEmpty())
//				{
					rList.clear();
					ReloadData.rMarkL.clear();
//				}	
				MyPanelRight.reLook();
				if(bg != null)
				{
					mP.openRe(bg);
					cleatAllList();

					mPLB.openAll();
					mPLB.changeBC();
					//根据当前点击的组件改变滚动条的位置
					MyPanelLeTop.jP.getVerticalScrollBar().setValue(MyFrame.PictureNum*ATP.objectH-ATP.objectH*8);
					mPLT.changeBC();
				}
				if(MainFrame.workerType && MainFrame.pre_worker)
					MyPanelRight.myJB.setEnabled(false);
				MyFrame.unEnableCom();
			}
		}
		
		
	}
	MyFrame.notOpen = true;
}

//上一张图片
public void up_Page(){
	if(PictureNum>0){
		PictureNum--;
		String bg = openKey.getOnePic(PictureNum);
		if(bg!=null)
		{
			mP.openRe(bg);
			cleatAllList();
		}	
	}
}


public static void cleatAllList(){
	
	if(MainFrame.workerType && MainFrame.pre_worker){
		for(int i = list.size()-1; i >= 0 && MyFrame.clearRect > 0; i--,MyFrame.clearRect--)
		  {
			  list.remove(i);
			  listS.remove(i);
			  MyPanelRight.markedR.remove(i);
//			  System.out.println(MyFrame.clearRect);
		  }
		MyPanelRight.objName.setSelectedIndex(0);
		MyPanelRight.objColor.setSelectedIndex(0);
		MyPanelRight.objPose.setSelectedIndex(0);
	}
	
}

public static void restCom(){
	if(MainFrame.workerType && MainFrame.pre_worker){
		MyPanelRight.objName.setSelectedIndex(0);
		MyPanelRight.objColor.setSelectedIndex(0);
		MyPanelRight.objPose.setSelectedIndex(0);
	}
	
}

public static void saveData() throws IOException{
	if(MyPanelRight.markedR.size() == list.size())
	{
		DataProcess.dataToCache();
		try{
			DataProcess.txtToXML(DataProcess.pathData+"\\imgCache.txt");
		}catch(NullPointerException e2){
			System.out.println(e2);
		}
		
		DataProcess.dataToTxt(DataProcess.testTxt);
		cleatAllList();
		MyPanelRight.btnMark = false;
	}
	if(MyFrame.clearRect > 0 && MyPanelRight.markedR.size() != list.size())
	{
		for(int i = list.size()-1; i >= 0 && MyFrame.clearRect > 0; i--,MyFrame.clearRect--)
		  {
			  list.remove(i);
			  listS.remove(i);
		  }
	}
}
public static void restSaveData() {
	if(MainFrame.workerType && MainFrame.pre_worker){
		MyPanelRight.btnMark = false;
		MyPanelRight.objName.setSelectedIndex(0);
		MyPanelRight.objColor.setSelectedIndex(0);
		MyPanelRight.objPose.setSelectedIndex(0);
//		MyPanelRight.objName.setEnabled(false);
//		MyPanelRight.objColor.setEnabled(false);
//		MyPanelRight.objPose.setEnabled(false);
		MyPanelRight.mfg.setSelected(true);
		MyPanelRight.xmlTrunc = 0;
		MyPanelRight.xmlCover = 0;
		MyPanelRight.bkn.setSelected(true);
		MyPanelRight.xmlDiff = 0;
		MyPanelRight.xmlShadow = 0;
		MyPanelRight.box1 = null;
		MyPanelRight.box2 = null;
		MyPanelRight.box3 = null;
	}

}

public static void dImg(JPanel jpanel) throws IOException
{
	
	BufferedImage  bi = new BufferedImage(jpanel.getWidth(), jpanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
	Graphics2D  g2d = bi.createGraphics();
	jpanel.paint(g2d);
	ImageIO.write(bi, "PNG", new File("frame.png"));
}

public static void unEnableCom(){
	if(MainFrame.workerType && MainFrame.pre_worker){
		MyPanelRight.objName.setEnabled(false);
		MyPanelRight.objColor.setEnabled(false);
		MyPanelRight.objPose.setEnabled(false);
	}

}
public static void enableCom(){
	if(MainFrame.workerType && MainFrame.pre_worker){
		MyPanelRight.objName.setEnabled(true);
		MyPanelRight.objColor.setEnabled(true);
		MyPanelRight.objPose.setEnabled(true);
	}
	
}

public void initWP(){
	wP = new WrongPanel(){
		@Override
		public void getfiles(int i) {
			// TODO Auto-generated method stub
			super.getfiles(i);
		}
	};
}
}