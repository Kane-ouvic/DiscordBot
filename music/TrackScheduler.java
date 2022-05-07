package DiscordBot_v1.me.com.music;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

public class TrackScheduler extends AudioEventAdapter implements TrackList { //實作部分的功能
	  
	  public final AudioPlayer player;
	  private final BlockingQueue<AudioTrack> queue;
	  public boolean repeating = false;
	  
	  public TrackScheduler(AudioPlayer player) {
		  
		  this.player = player;
		  this.queue = new LinkedBlockingQueue<>();
	  }
	  
	  public void queue(AudioTrack track) {
		  
		  if(!this.player.startTrack(track, true)) {
			  this.queue.offer(track);
		  }
	  }
	  
	  public void nextTrack() {
		  this.player.startTrack(this.queue.poll(), false);
		  
		  if(!repeating) {
		    trackListName.remove(0);
		    trackListAuthor.remove(0);
		  }
	  }
	  
	  @Override
	  public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
	    if (endReason.mayStartNext) {
	    	if(this.repeating) {
	    		this.player.startTrack(track.makeClone(), false);
	    		return;
	    	}
	      nextTrack();
	    }
	  }
	  
	  public void onPlayerPause() {
	    // Player was paused
		  player.setPaused(true);
	  }

	  public void onPlayerResume() {
	    // Player was resumed
		  player.setPaused(false);
	  }

	  @Override
	  public void onTrackStart(AudioPlayer player, AudioTrack track) {
	    // A track started playing
		  player.startTrack(track, true);
	  }



	  @Override
	  public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception) {
	    // An already playing track threw an exception (track end event will still be received separately)
	  }

	  @Override
	  public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs) {
	    // Audio track has been unable to provide us any audio, might want to just start a new track
	  }
	}
