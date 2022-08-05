package DiscordBot_v1.me.com.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager { //���API���Ѫ�����
	
	private final AudioPlayer audioPlayer;
	public final  TrackScheduler scheduler;
	private final AudioPlayerSendHandler sendHandler;
	
	public GuildMusicManager(AudioPlayerManager manager) {
		
		this.audioPlayer = manager.createPlayer();
		this.scheduler = new TrackScheduler(this.audioPlayer);
		this.audioPlayer.addListener(this.scheduler);
		this.sendHandler = new AudioPlayerSendHandler(this.audioPlayer);
	}
	
	public AudioPlayerSendHandler getSendHandler() {
		return sendHandler;
	}

}
