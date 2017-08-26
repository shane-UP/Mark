package packUI;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JPanel;

import packData.ModelProcess;
import packData.ReloadData;

public class WrongList extends JPanel {
	public static int mount = 0;			//图片文件的数量
	public int i;							//第i个组件
	public int max = 0;
	//构造函数
	public WrongList() {				
		// TODO Auto-generated constructor stub
		initThis();
		filein();
		this.setVisible(true);
	}
	//读入文件数目并添加相应的组件
	public void filein(){
		mount =  ModelProcess.listWrongMess.size();//文件数目
		this.initThis();
		for( i=0; i<mount; i++){
			String text = ""; //文本内容
			String[] temp = ModelProcess.listWrongMess.get(i).split(" ");
			text += "文件名："+temp[0] + "漏标数量："+temp[3]+"对象编号：";
			for(int i = 5; i < temp.length; i++){
				text += temp[i] + " ";
			}
			WrongMassage WM = new WrongMassage(i, text){
				@Override
				public void getFile(int k) {
					// TODO Auto-generated method stub
					super.getFile(k);
					getfile(k);
				}
			};
			if((text.length()*11) > max) max = ( text.length()*11);
			WM.setSize(text.length()*11,ATP.objectH);
//			//根据组件值设置组件位置
			WM.setLocation(0,i*ATP.objectH);
			this.add(WM);	
		}
		//根据组件数量给FileContent设置初始大小
		this.setPreferredSize(new Dimension(max,i*ATP.objectH+5));
		this.repaint();
	}
	
	//获取当前组件的函数_2
	public void getfile(int k){
		
	}
	//初始化当前的设定
	public void initThis(){
		this.setPreferredSize(new Dimension(ATP.leftW-10,ATP.leftBotH-15));
		this.setLayout(null);
	}
}
