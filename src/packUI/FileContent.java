package packUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import packData.ModelProcess;
import packData.ReloadData;

//存放文件列表的组件
public class FileContent extends JPanel {
	
	public static int mount = 0;			//图片文件的数量
	public int i;							//第i个组件
	
	//构造函数
	public FileContent() {				
		// TODO Auto-generated constructor stub
		initThis();
		filein();
		this.setVisible(true);
	}
	//读入文件数目并添加相应的组件
	public void filein(){
		mount = MyFrame.openKey.getSize();
		this.initThis();
		for( i=0; i<mount; i++){
			String text = MyFrame.openKey.getOneName(i);
//			System.out.println(text);
			FileName FN = new FileName(text,i){
				@Override
				public void getFile(int k) {
					// TODO Auto-generated method stub
					super.getFile(k);
					getfile(k);
//					if(ReloadData.canCheck())
				}

			};
			FN.setSize(ATP.leftW,ATP.objectH);
			//根据组件值设置组件位置
			FN.setLocation(0,i*ATP.objectH);
			if( !MainFrame.workerType && ( FileRead.wrongObjNuum[i]>0 || FileRead.leaveObjNum[i]>0) && ModelProcess.checkNoName(MyFrame.openKey.getOneName(i)))	//
			{
				FN.addWrong();
			}
			else{
				if(MainFrame.workerType){
					try {
						if(ReloadData.specialCheck(i))
						FN.addSpecial();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					if(MyPanelRight.showGood(i)) 
						FN.addSpecial();
				}
			}
			this.add(FN);	
		}
		//根据组件数量给FileContent设置初始大小
		this.setPreferredSize(new Dimension(ATP.leftW,i*ATP.objectH+5));
		this.repaint();
	}
	
	//获取当前组件的函数_2
	public void getfile(int k){
		
	}
	//初始化当前的设定
	public void initThis(){
		this.setPreferredSize(new Dimension(ATP.leftW,ATP.leftBotH-15));
		this.setLayout(null);
	}
	
}
