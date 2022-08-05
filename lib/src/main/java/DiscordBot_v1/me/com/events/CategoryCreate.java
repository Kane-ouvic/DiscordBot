package DiscordBot_v1.me.com.events;

import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelDeleteEvent;

public class CategoryCreate extends IEvent {
	
	public void onCategoryCreate(CategoryCreateEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("���O�w�إ� " + e.getCategory().toString()).queue();
		
	}
	
	public void OnCategoryDelete(CategoryDeleteEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("���O�w�R��").queue();
	}
	
	public void onVoiceChannelCreate(VoiceChannelCreateEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("�y���W�D�w�إ�").queue();
		//e.getChannel().delete().queue();
		System.out.println("Done");
	}
	
	public void onVoiceChannelDelete(VoiceChannelDeleteEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("�y���W�D�w�R��").queue();
		//e.getChannel().delete().queue();
		System.out.println("Done");
		
	}
	
	public void onTextChannelCreate(TextChannelCreateEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("��r�W�D�w�إ�").queue();
		//e.getChannel().delete().queue();
		System.out.println("Done");
		
	}
	
	public void onTextChannelDelete(TextChannelDeleteEvent e) {
		
		e.getGuild().getDefaultChannel().sendMessage("��r�W�D�w�R��").queue();
		//e.getChannel().delete().queue();
		System.out.println("Done");
		
	}
}
