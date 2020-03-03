package t1;

public class Music_play {
	public void music_play() throws InterruptedException{		//노래 재생 함수
		int music_count = 0;
		int thread_sleep = 239000;
//		System.out.println("play_state : 플레이 함수 " + play_state);
		do{
			String music_String_count = Integer.toString(music_count);
			Music music1 = new Music("music",music_String_count+".MP3" ,true);
//			music1 = new Music("music",music_String_count+".MP3" ,true);
			music1.start();
			Thread.sleep(thread_sleep);
			music_count++;
			
			if(music_count == 1){
				thread_sleep = 264000;	
			}
			else if(music_count == 4){
				thread_sleep = 239000;
				music_count = 1;
			}else if(music_count == 2){
				thread_sleep = 305000;
			}else if(music_count == 3){
				thread_sleep = 227000;
			}
		}while(true);
	}
}
