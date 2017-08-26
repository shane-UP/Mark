package packUI;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MyPanelMidBo extends JPanel {
	public static MPMBButton nextOne;			//下一张的按钮
	public static boolean canKey = true;
	public MyPanelMidBo(){		//当前构造函数
		this.setBackground(new Color(0xCCCCCC));
		this.setBounds(ATP.leftW+ATP.midW, ATP.rightH, ATP.rightW, ATP.midBotH);
		iniMPMBB();
		
		this.add(nextOne);
		
		this.setVisible(true);
	}
	
	//初始化按钮
	public void iniMPMBB(){
		
		
		nextOne = new MPMBButton();

		nextOne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
//				MyFrame.FistRun = 
				nextPage();
				
			}
		});
		nextOne.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 0x28){
					nextPage();
				}
			}
		});
		nextOne.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(canKey)
				nextOne.requestFocus();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//用于复写的一个函数
	public void nextPage(){
		
	}
}
