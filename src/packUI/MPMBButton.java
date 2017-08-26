package packUI;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MPMBButton extends JButton {
	public MPMBButton(){
		this.setToolTipText("Alt+Down(方向键)");
		this.setMnemonic(KeyEvent.VK_M);
		this.setText("下一个");
		this.setBounds(0, 0, 50, 50);
		
	}
}
