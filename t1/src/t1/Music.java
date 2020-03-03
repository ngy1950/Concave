package t1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

//import javazoom.jl.player.Player;

public class Music extends Thread{
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, String music_number, boolean isLoop){
	    String music_name = name + music_number;
		System.out.println(music_name);
		try{
			this.isLoop = isLoop;
			file = new File(t1.class.getResource("Music/" + music_name).toURI());		//파일에 노래 폴더 경로 지정
			fis = new FileInputStream(file);			//
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}catch(Exception e){
			System.out.println(e.getMessage() + " Music 함수");
		}
	}
	public void close(){
//		isLoop = false;
		player.close();
		this.interrupt();		// 곡 재생을 들려주는 프로그램을 종료해주는 것이 interrupt
	}
	
	public void run(){
		try{
//			do{
//				file = new File(t1.class.getResource("Music/" + music_name).toURI());
				player.play();
				fis = new FileInputStream(file);			//
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
//			}while(isLoop == true);
		}catch(Exception e){
//			System.out.println(e.getMesasage() + " run 함수 ! ");
		}
	}
	
}
