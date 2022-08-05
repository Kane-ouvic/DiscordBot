package DiscordBot_v1.me.com.main;

import javax.security.auth.login.LoginException;

import DiscordBot_v1.me.com.commands.JoinLeaveChannel;
import DiscordBot_v1.me.com.commands.Play;
import DiscordBot_v1.me.com.commands.ShowTrackList;
import DiscordBot_v1.me.com.commands.Stop;
import DiscordBot_v1.me.com.events.CategoryCreate;
import DiscordBot_v1.me.com.music.PlayerManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Bot {
	
    String Token;
	int Status;
	String Playing;
	String dataPath;
    
	
	public Bot(String Token, int Status, String Playing, String dataPath) throws LoginException {
		
		this.Token = Token;
		this.Status = Status;
		this.Playing = Playing;
		this.dataPath = dataPath;
		
		buildBot(Token, Status, Playing);
	}
	
	public String getDataPath() {
		return dataPath;
	}
	

	private void buildBot(String Token, int Status, String Playing) throws LoginException {
		
		OnlineStatus onlineStatus = OnlineStatus.ONLINE;
		
		switch(Status) {
	
		  case 0:
			onlineStatus = OnlineStatus.ONLINE; 
			break;
		
		  case 1:
			onlineStatus = OnlineStatus.IDLE;
			break;
			
		  case 2:
			onlineStatus = OnlineStatus.DO_NOT_DISTURB;
			break;
		
		  default:
			onlineStatus = OnlineStatus.ONLINE;
		
		}
		
		JDA builder = JDABuilder.createDefault(Token)
				.setToken(Token)
				.setStatus(onlineStatus)
				.setActivity(Activity.watching(Playing))
				.build();
		
		builder.addEventListener(new CategoryCreate());
		builder.addEventListener(new JoinLeaveChannel());
		builder.addEventListener(new Play());
		builder.addEventListener(new Stop());
		builder.addEventListener(new ShowTrackList());
		
		System.out.println("已成功啟動Discord機器人");
		
	}

}