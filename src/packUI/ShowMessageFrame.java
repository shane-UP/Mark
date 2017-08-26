package packUI;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShowMessageFrame extends Frame implements Runnable{
	
	public ShowMessageFrame() {
	
	}
	
	public void showMessage(String message){
		JOptionPane.showMessageDialog(this,message);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			this.dispose();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
