package DiscordBot_v1.me.com.commands;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DiscordBot_v1.me.com.music.TrackList;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ShowTrackList extends ICommand implements TrackList {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] messageSent = event.getMessage().getContentRaw().split(" ");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy�~MM��dd�� HH�I mm�� ss��");
		Date date = new Date();
		
		if(messageSent[0].equalsIgnoreCase("!list")) {
			
			EmbedBuilder listEmbed = new EmbedBuilder();
			
			listEmbed.setTitle("�q���C��");
			listEmbed.setColor(Color.RED);
			listEmbed.addField("Rank", solveData(trackListName.size()), true);
			listEmbed.addField("�q���W", solveData(trackListName), true);
			listEmbed.addField("�W�Ǫ�", solveData(trackListAuthor), true);
			
			listEmbed.setFooter("��s�ɶ�: " + formatter.format(date));
			
			event.getChannel().sendMessageEmbeds(listEmbed.build()).queue();
			
		}
	}
	
	public String solveData(ArrayList<String> trackList) {
		
		String result = "";
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < trackList.size(); i++) {
			
			buffer.append(trackList.get(i));
			if(trackList.get(i).length() >= 30) {
			    buffer.delete(31, trackList.get(i).length());
			    buffer.append("...");
			}
			result += buffer.toString() + "\n";
			buffer.delete(0, buffer.length());
		}
		
		return result;
	}
	
	public String solveData(int number) {
		
		String result = "";
		
		
		for(int i = 0; i < number ; i++) {
			result += "#" + Integer.toString(i+1) + "\n";
		}
		
		return result;
		
	}

}
