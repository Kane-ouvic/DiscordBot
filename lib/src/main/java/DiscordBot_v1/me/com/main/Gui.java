package DiscordBot_v1.me.com.main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.login.LoginException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Gui extends JFrame implements BotInformation {
	
	JTextField text1,text2,text3;
	JLabel label1, label2, label3;
	JButton button;
	ButtonGroup group;
	JRadioButton radio1, radio2, radio3; 
	
	public Gui() {
		
		init();
		setVisible(true);
		//setSize(700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void init() { //設定介面
		
		setLayout(new FlowLayout());
		
        ImageIcon icon= new ImageIcon(getClass().getResource("/DiscordBot_v1/images/bot1.png")); //設定Logo
        setIconImage(icon.getImage());
		
		label1 = new JLabel("機器人的序號");
		//label1.setLocation(250, 250);
        add(label1);
        text1 = new JTextField(18);
        text1.setText("ODM1OTA5NzU4NTU2NzAwNjcy.YIWToA.5w17VcRRyWnRWCnAu6yDsCQblcM"); //設定初始文字
        add(text1);
        
        
        
        label2 = new JLabel("正在做的事情");
        add(label2);
        text2 = new JTextField(18);
        text2.setText("公車站牌"); //設定初始文字
        add(text2);
        
        label3 = new JLabel("預設播放歌曲");
        add(label3);
        text3 = new JTextField(18);
        text3.setText("https://www.youtube.com/watch?v=2ips2mM7Zqw"); //設定初始文字
        add(text3);
        
        
    
        add(new JLabel("狀態:"));
        group = new ButtonGroup();
        radio1 = new JRadioButton("線上", true);
        radio2 = new JRadioButton("閒置");
        radio3 = new JRadioButton("勿擾");
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);
        add(radio1);
        add(radio2);
		add(radio3);
		
		button = new JButton("啟動");
		add(button);
		
		
		add(new JLabel("                                          ")); //文字說明
		add(new JLabel("注意: 按鈕只可啟動一次 若要重新設定請重開程式")); //文字說明
		eventHandler();
		
	}
	
	//事件處理
	private void eventHandler() {
		
		ReaderListen listener1 = new ReaderListen();
		listener1.setJTextField(text1, text2, text3);
		listener1.setButtonGroup(radio1, radio2, radio3);
		button.addActionListener(listener1);
	}
	
}

class ReaderListen implements ActionListener,BotInformation{
	
	
	private boolean isStarted = false; //是否啟動過
	
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
	private JRadioButton radio1, radio2, radio3;

	public void setJTextField(JTextField text1, JTextField text2, JTextField text3) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }
	
	public void setButtonGroup(JRadioButton radio1, JRadioButton radio2, JRadioButton radio3) {
		
		this.radio1 = radio1;
		this.radio2 = radio2;
		this.radio3 = radio3;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!isStarted) {
			
			isStarted = true;
		
			int statusNumber = 0;
			if(radio1.isSelected()) statusNumber = 0;
			if(radio2.isSelected()) statusNumber = 1;
			if(radio3.isSelected()) statusNumber = 2;
		
		
			System.out.println(text1.getText());
			System.out.println(text2.getText());
			dataPath.append(text3.getText());

			try {
				Bot TestBot = new Bot(text1.getText(), statusNumber, text2.getText(), text3.getText());
				} catch (LoginException e1) {
	
				e1.printStackTrace();
			}
			}
	}
}