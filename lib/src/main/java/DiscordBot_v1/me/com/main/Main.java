package DiscordBot_v1.me.com.main;

import javax.security.auth.login.LoginException;

public class Main {
	
	public static void main(String[] args) throws LoginException{
		
		createGUI();
	}
	
	public static void createGUI() {
		
        Gui win = new Gui();
        win.setBounds(100, 100, 320, 320);
        win.setTitle("Discord �����H");
        win.setSize(320, 320);
        System.out.println("�w���\�Ыؤ���");
        
	}

}
