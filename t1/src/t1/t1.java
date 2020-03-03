package t1;

//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
//import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class t1 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mypane1 pane1 = new Mypane1(); // Mypane1 클래스 객체 생성
	// private music_play2 music = new music_play2();

	public t1() throws InterruptedException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(pane1);
		setSize(711, 440);
		setTitle("오목 대전");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Timer"); // 시간경과 초 라벨
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(653, 380, 57, 15);
		getContentPane().add(lblNewLabel); // 프레임 출력
		lblNewLabel.setText("" + timer_s + "초");

		JLabel lblNewLabel2 = new JLabel("Timer"); // 시간경과 분 라벨
		lblNewLabel2.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setBounds(555, 380, 120, 15);
		getContentPane().add(lblNewLabel2); // 프레임 출력
		lblNewLabel2.setText("게임시간 : " + timer_m + "분");

		JButton music_playButton = new JButton("\u25B6"); // 노래 재생버튼
		music_playButton.setFont(new Font("굴림", Font.PLAIN, 12));
		MusicPlayThread mpt = new MusicPlayThread();
		
		music_playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("play_state   " + play_state);
				if (play_state == false) {
					mpt.start();
					play_state = true;
				}
			}
		});
		music_playButton.setBounds(446, 376, 50, 23);
		getContentPane().add(music_playButton);
		JButton music_stopButton = new JButton("\u25A0");
		
		music_stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				music1.close();
				mpt.stop();
				play_state = false;
				System.out.println("play_state : stop 버튼!!" + play_state);
			}
		});
		music_stopButton.setBounds(401, 376, 45, 23);
		getContentPane().add(music_stopButton);

		setVisible(true);

		Timer timer = new Timer();
		TimerTask task1 = new TimerTask() {
			@Override
			public void run() {

				if (game == true) {
					timer_s += 1;
					lblNewLabel.setText("" + timer_s + "초");
					lblNewLabel2.setText("게임시간 : " + timer_m + "분");
					if (timer_s == 60) {
						timer_m += 1;
						timer_s = 0;
					}
				}
			}
		};
		timer.schedule(task1, 1000, 1000); // 1초 마다 run메소드 실행
	}

	public int timer_count = 0; // 경기 재 시작 시 타이머가 2초씩 늘어나는걸 빼주기 위해서 재시작count수 만큼 초 증가에서 빼줌
	public int timer_s = 0; // 타이머 초 값
	public int timer_m = 0; // 타이머 분 값
	int result = 5; // 알림 결과값

	String[] buttons = { "봐줌ㅋ", "다시하기", "끝내기" };
	static boolean game = true; // 게임 진행
	public int[][] pixels = new int[31][31]; // 바둑 알 좌표 값
	public int count = 0;
	public int winner = 0; // 흑,백 승리 유무 "1 : 흑돌 승리 2: 백돌 승리"

	public int[] replay_x = new int[361]; // 리플레이 저장
	public int[] replay_y = new int[361];
	public int replay_num = 0;
	 
	public static int music_count = 1;
	public static String music_String_count = Integer.toString(music_count);
	public static Music music1 = new Music("music",music_String_count+ ".MP3" ,true); // 뮤직 객체 생성
	public static boolean play_state = true;
	public static MusicPlayThread mpt;
	
	public static void main(String[] args) throws InterruptedException {
		t1 frame = new t1(); // t1 클래스에 frame 객체 생성

		music_play();
//		 int music_count = 1;
//		 String music_String_count = Integer.toString(music_count);
//		 Music music1 = new Music("music",music_String_count+ ".MP3" ,true);
//		 music1.start();
	}

	public static void music_play() throws InterruptedException { // 노래 재생 함수
		int music_count = 0;
		int thread_sleep = 239000;
		System.out.println("play_state : 플레이 함수 " + play_state);
		do {
			String music_String_count = Integer.toString(music_count);
			music1 = new Music("music", music_String_count + ".MP3", true);
			music1.start();
			Thread.sleep(thread_sleep);
			music_count++;

			if (music_count == 1) {
				thread_sleep = 264000;
			} else if (music_count == 4) {
				thread_sleep = 239000;
				music_count = 1;
			} else if (music_count == 2) {
				thread_sleep = 305000;
			} else if (music_count == 3) {
				thread_sleep = 227000;
			}
		} while (play_state == true);
	}

	public class MusicPlayThread extends Thread {
		@Override
		public void run() {
			boolean ply_st = play_state;
			Music_play music_play = new Music_play();
			try {
				music_play.music_play();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	class Mypane1 extends JPanel {
		private Vector<Point> vStart = new Vector<Point>(); // 시작

		public Mypane1() {
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (game == true) {
						Point startP = e.getPoint(); // 클릭한 부분을 시작점으로
						boolean ban = false;
						int color = 1;

						startP.x = (((startP.x)) / 21 * 21); // 바둑 알 좌표
						startP.y = (((startP.y)) / 21 * 21); // 바둑 알 좌표

						if (((startP.x >= 0 && startP.x <= 380) && (startP.y >= 0 && startP.y <= 380))) { // 바둑 판 안일때
																											// 바둑알 호출
							if (pixels[startP.y / 20 + 5][startP.x / 20 + 5] == 0) { // 바둑알이 놓여있는지 확인

								if (count % 2 == 0) {
									color = 1;
								} else if (count % 2 == 1) {
									color = 3;
								}
								ban = BanNumber.banNumber(pixels, (startP.y / 20) + 5, (startP.x / 20) + 5, color);

								if (ban == false) {
									if (count % 2 == 0) {
										pixels[startP.y / 20 + 5][startP.x / 20 + 5] = 1;
										count++;
										Confirm check = new Confirm();
										winner = check.confirm(pixels, 1); // 흑돌 승자확인
									} else if (count % 2 == 1) {
										pixels[startP.y / 20 + 5][startP.x / 20 + 5] = 3;
										count++;
										Confirm check = new Confirm();
										winner = check.confirm(pixels, 3); // 백돌 승자확인
									}

									replay_num = count; // 리플레이를 위한 바둑 알 순서 배열에 저장
									replay_x[count - 1] = (((startP.x)) / 21 * 21);
									replay_y[count - 1] = (((startP.y)) / 21 * 21);

									System.out.println((startP.y / 20 + 5) + " : " + (startP.x / 20 + 5) + " : "
											+ pixels[startP.y / 20 + 5][startP.x / 20 + 5]);
									vStart.add(startP);
									repaint(); // 강제로 paint메소드를 한번더 호출하고 싶을 때 사용(방금
												// 처리한 작업을 화면에 바로 보여주고 싶으면 repaint()
												// 메소드를 호출하여 paint()메소드 호출

									if (winner == 1) { // 흑돌 승자확인
										game = false;
										result = JOptionPane.showOptionDialog(null, "흑돌이 이겼습니다.", "Game over",
												JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_CANCEL_OPTION, null,
												buttons, ""); // 알림창

									} else if (winner == 3) { // 백돌 승자확인
										game = false;
										result = JOptionPane.showOptionDialog(null, "백돌이 이겼습니다.", "Game over",
												JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_CANCEL_OPTION, null,
												buttons, ""); // 알림창
									}
								} else if (ban == true) {
									System.out.println("삼삼입니다.");
									JOptionPane.showMessageDialog(null, "삼삼입니다!");
								}
							}
						}
						if (result == 0) { // 무르기
							pixels[startP.y / 20 + 5][startP.x / 20 + 5] = 0;
							vStart.remove(startP);
							repaint();

							count--;
							game = true;
							result = 5;
						} else if (result == 1) { // 다시하기
							result = JOptionPane.showConfirmDialog(null, "Replay를 보시겠습니까?", "Replay 보기",
									JOptionPane.YES_NO_OPTION);

							if (result == 0) { // 리플레이 보기 선택 시
								Replay replay = new Replay(replay_x, replay_y, replay_num);
								result = 5;
							}
							for (int i = 0; i <= count; i++) { // 최초 오목 좌표부터 이미지 지우기
								startP.x = replay_x[i];
								startP.y = replay_y[i];
								vStart.remove(startP);
							}
							repaint();
							replay_x = new int[361]; // 리플레이 변수 값 초기화
							replay_y = new int[361]; // 리플레이 변수 값 초기화
							count = 0; // 리플레이 카운트 값 초기화

							for (int i = 0; i < 31; i++) { // 오목 배열 값 초기화
								for (int j = 0; j < 31; j++) {
									pixels[i][j] = 0;
								}
							}
							timer_count++;
							game = true; // 다시 하기 후 게임 상태값 true로 변경
							result = 5; // 알림창 결과 값 초기화
							timer_m = 0; // 타이머 분 초기화
							timer_s = 0; // 타이머 초 초기화
						} else if (result == 2) { // 종료하기
							System.exit(0);
						}
					}
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 부모 페인트 호출

			ImageIcon icon = new ImageIcon("C:\\Users\\skaru\\Image\\badook.png"); // 오목 판 이미지
			Image img = icon.getImage();
			g.drawImage(img, 0, 0, this);

			ImageIcon icon2 = new ImageIcon("C:\\Users\\남기윤\\Desktop\\black_user.png"); // 흑돌 유저 이미지
			Image img2 = icon2.getImage();
			g.drawImage(img2, 400, 0, this);

			ImageIcon icon3 = new ImageIcon("C:\\Users\\남기윤\\Desktop\\white_user.png"); // 흰돌 유저 이미지
			Image img3 = icon3.getImage();
			g.drawImage(img3, 547, 0, this);

			for (int i = 0; i < vStart.size(); i++) {
				Point s = vStart.elementAt(i);

				if (i % 2 == 1) {
					g.setColor(Color.white);
					g.fillOval((int) s.getX(), (int) s.getY(), 21, 21); // 바둑알 크기
				} else {
					g.setColor(Color.black);
					g.fillOval((int) s.getX(), (int) s.getY(), 21, 21); // 바둑알 크기
				}
			}
		}
	}
}
