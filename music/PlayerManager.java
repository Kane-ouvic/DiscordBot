package DiscordBot_v1.me.com.music;

import java.util.HashMap;
import java.util.Map;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class PlayerManager implements TrackList { //播放器的管理功能
	
	private static PlayerManager INSTANCE;
	
	private final Map<Long, GuildMusicManager> musicManagers;
	private final AudioPlayerManager audioPlayerManager;
	
	public PlayerManager() {
		
		this.musicManagers = new HashMap<>();
		this.audioPlayerManager = new DefaultAudioPlayerManager();
		
		AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
		AudioSourceManagers.registerLocalSource(this.audioPlayerManager);

	}
	
	   public GuildMusicManager getMusicManager(Guild guild) {
	        return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
	            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);

	            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

	            return guildMusicManager;
	        });
	    }
	
	
	public void loadAndPlay(TextChannel channel, String trackUrl) {
        final GuildMusicManager musicManager = this.getMusicManager(channel.getGuild());

        this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
            	musicManager.scheduler.queue(track);

                channel.sendMessage("新增歌曲:  ")
                        .append(track.getInfo().title)
                        .append("  |上傳者: ")
                        .append(track.getInfo().author)
                        .queue();
                
                trackListName.add(track.getInfo().title);
                trackListAuthor.add(track.getInfo().author);
                
                for(int i = 0; i < trackListName.size(); i++) {
                	System.out.printf("%s %s %n" ,trackListName.get(i) ,trackListAuthor.get(i));
                }
                
            }
            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                //
            }

            @Override
            public void noMatches() {
                //
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                //
            }
            
        });
    }

    public static PlayerManager getInstance() { //生成一個播放器物件
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }

        return INSTANCE;
    }

}
