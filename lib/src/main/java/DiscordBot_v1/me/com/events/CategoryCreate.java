package DiscordBot_v1.me.com.events;

import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelDeleteEvent;

public class CategoryCreate extends IEvent {
	
	public void onCategoryCreate(CategoryCreateEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("類別已建立 " + e.getCategory().toString()).queue();
		
	}
	
	public void OnCategoryDelete(CategoryDeleteEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("類別已刪除").queue();
	}
	
	public void onVoiceChannelCreate(VoiceChannelCreateEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("語音頻道已建立").queue();
		//e.getChannel().delete().queue();
		System.out.println("Done");
	}
	
	public void onVoiceChannelDelete(VoiceChannelDeleteEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("語音頻道已刪除").queue();
		//e.getChannel().delete().queue();
		System.out.println("Done");
		
	}
	
	public void onTextChannelCreate(TextChannelCreateEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("文字頻道已建立").queue();
		//e.getChannel().delete().queue();
		System.out.println("Done");
		
	}
	
	public void onTextChannelDelete(TextChannelDeleteEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("文字頻道已刪除").queue();
		//e.getChannel().delete().queue();
		System.out.println("Done");
		
	}
}
