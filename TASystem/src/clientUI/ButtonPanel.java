package clientUI;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ButtonPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3075751934178155530L;
	private ButtonGroup group;
	public ButtonPanel(ActionListener action,String title,String[] labels){
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		group = new ButtonGroup();
		for(int i = 0;labels!=null && i<labels.length;i++){
			JRadioButton b = new JRadioButton(labels[i]);
			b.setActionCommand(labels[i]);
			this.add(b);
			b.addActionListener(action);
			group.add(b);
//			b.setSelected(i == 0);
		}
	}
}
