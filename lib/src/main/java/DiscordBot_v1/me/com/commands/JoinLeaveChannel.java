package DiscordBot_v1.me.com.commands;

import DiscordBot_v1.me.com.main.BotInformation;
import DiscordBot_v1.me.com.music.PlayerManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinLeaveChannel extends ICommand implements BotInformation {

	
	@SuppressWarnings("deprecation")
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		if(event.getAuthor().isBot()) {
			return;
		}
		
		String message = event.getMessage().getContentRaw().toLowerCase();
		TextChannel channel = event.getChannel();
		
		if(message.equals("!join")) {
            if(!event.getGuild().getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT)) {
                channel.sendMessage("�ڨS���v���i�J�W�D").queue();
                return;
            }
            net.dv8tion.jda.api.entities.VoiceChannel connectedChannel = (net.dv8tion.jda.api.entities.VoiceChannel) event.getMember().getVoiceState().getChannel();
            if(connectedChannel == null) {
                channel.sendMessage("�A���b�W�D��! �ڵL�k�i�J").queue();
                return;
            }
            
            AudioManager audioManager = event.getGuild().getAudioManager();
            
            if(audioManager.isAttemptingToConnect()) {
                channel.sendMessage("�W�D�H�Ƥw�F�W���A�ڵL�k�i�J").queue();
                return;
            }
            
            audioManager.openAudioConnection(connectedChannel);
            audioManager.setSelfDeafened(true);
           
            channel.sendMessage("�ڤw�i�J�y���W�D").queue();
            PlayerManager player = PlayerManager.getInstance();
            player.loadAndPlay(event.getChannel(), dataPath.toString());
          
            
        } else if(message.equals("!leave")) { 
        	
        	net.dv8tion.jda.api.entities.VoiceChannel connectedChannel = (net.dv8tion.jda.api.entities.VoiceChannel) event.getGuild().getSelfMember().getVoiceState().getChannel();
            if(connectedChannel == null) {
                channel.sendMessage("�ڤ��b�y���W�D��").queue();
                return;
            }
            event.getGuild().getAudioManager().closeAudioConnection();
            channel.sendMessage("�ڤw���}�y���W�D").queue();
        }
		
	}
	

}

