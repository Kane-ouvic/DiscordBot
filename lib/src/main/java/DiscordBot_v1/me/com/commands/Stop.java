package DiscordBot_v1.me.com.commands;

import DiscordBot_v1.me.com.music.GuildMusicManager;
import DiscordBot_v1.me.com.music.PlayerManager;
import DiscordBot_v1.me.com.music.TrackList;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Stop extends ICommand implements TrackList{
	
public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		
		String[] messageSent = event.getMessage().getContentRaw().split(" ");
		String name = event.getMember().getUser().getName();
		
		if(messageSent[0].equalsIgnoreCase("!stop")) {
			if(!event.getMember().getUser().isBot()) {
				
				 GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
				 musicManager.scheduler.player.stopTrack();
				 trackListName.clear();
				 trackListAuthor.clear();
				 System.out.println("Test: stop");
	            
			}
		}
		
		if(messageSent[0].equalsIgnoreCase("!skip")) {
			if(!event.getMember().getUser().isBot()) {
				
				 GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
				 final boolean newRepeating = !musicManager.scheduler.repeating;
				 musicManager.scheduler.repeating = newRepeating;
				 musicManager.scheduler.nextTrack();
	
				 System.out.println("Test: skip");
	            
			}
		}
		
	}

}
