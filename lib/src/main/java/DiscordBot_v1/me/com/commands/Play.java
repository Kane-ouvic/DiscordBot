package DiscordBot_v1.me.com.commands;

import com.google.common.util.concurrent.AbstractScheduledService.Scheduler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

import DiscordBot_v1.me.com.music.GuildMusicManager;
import DiscordBot_v1.me.com.music.PlayerManager;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Play extends ICommand {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		
		String[] messageSent = event.getMessage().getContentRaw().split(" ");
		String name = event.getMember().getUser().getName();
		
		if(messageSent.length == 2 && messageSent[0].equalsIgnoreCase("!play")) {
			if(!event.getMember().getUser().isBot()) {
				
				//PlayerManager.getInstance().loadAndPlay(event.getChannel(), messageSent[1]);
	            System.out.println("Test");
	            
	            PlayerManager player = PlayerManager.getInstance();
	            player.loadAndPlay(event.getChannel(), messageSent[1]);
	            
			}
		}
		
		if(messageSent[0].equalsIgnoreCase("!repeat")) {
			if(!event.getMember().getUser().isBot()) {
				
				//PlayerManager.getInstance().loadAndPlay(event.getChannel(), messageSent[1]);
				 final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
				 final boolean newRepeating = !musicManager.scheduler.repeating;
				 musicManager.scheduler.repeating = newRepeating;
				 event.getChannel().sendMessageFormat("同一首重複播放功能 **%s**", newRepeating ? "開啟" : "關閉").queue();

			}
		}
		
		if(messageSent[0].equalsIgnoreCase("!resume")) {
			if(!event.getMember().getUser().isBot()) {
				
				//PlayerManager.getInstance().loadAndPlay(event.getChannel(), messageSent[1]);
				 final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
				 //final boolean newRepeating = !musicManager.scheduler.repeating;
				 musicManager.scheduler.onPlayerResume();
				 //event.getChannel().sendMessageFormat("同一首重複播放功能 **%s**", newRepeating ? "開啟" : "關閉").queue();

			}
		}
		
		if(messageSent[0].equalsIgnoreCase("!pause")) {
			if(!event.getMember().getUser().isBot()) {
				
				//PlayerManager.getInstance().loadAndPlay(event.getChannel(), messageSent[1]);
				 final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
				 //final boolean newRepeating = !musicManager.scheduler.repeating;
				 musicManager.scheduler.onPlayerPause();;
				 //event.getChannel().sendMessageFormat("同一首重複播放功能 **%s**", newRepeating ? "開啟" : "關閉").queue();

			}
		}
		
		
	}

}
