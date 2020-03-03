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
	private Mypane1 pane1 = new Mypane1(); // Mypane1 Ŭ���� ��ü ����
	// private music_play2 music = new music_play2();

	public t1() throws InterruptedException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(pane1);
		setSize(711, 440);
		setTitle("���� ����");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Timer"); // �ð���� �� ��
		lblNewLabel.setFont(new Font("����", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(653, 380, 57, 15);
		getContentPane().add(lblNewLabel); // ������ ���
		lblNewLabel.setText("" + timer_s + "��");

		JLabel lblNewLabel2 = new JLabel("Timer"); // �ð���� �� ��
		lblNewLabel2.setFont(new Font("����", Font.BOLD, 13));
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setBounds(555, 380, 120, 15);
		getContentPane().add(lblNewLabel2); // ������ ���
		lblNewLabel2.setText("���ӽð� : " + timer_m + "��");

		JButton music_playButton = new JButton("\u25B6"); // �뷡 �����ư
		music_playButton.setFont(new Font("����", Font.PLAIN, 12));
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
				System.out.println("play_state : stop ��ư!!" + play_state);
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
					lblNewLabel.setText("" + timer_s + "��");
					lblNewLabel2.setText("���ӽð� : " + timer_m + "��");
					if (timer_s == 60) {
						timer_m += 1;
						timer_s = 0;
					}
				}
			}
		};
		timer.schedule(task1, 1000, 1000); // 1�� ���� run�޼ҵ� ����
	}

	public int timer_count = 0; // ��� �� ���� �� Ÿ�̸Ӱ� 2�ʾ� �þ�°� ���ֱ� ���ؼ� �����count�� ��ŭ �� �������� ����
	public int timer_s = 0; // Ÿ�̸� �� ��
	public int timer_m = 0; // Ÿ�̸� �� ��
	int result = 5; // �˸� �����

	String[] buttons = { "���ܤ�", "�ٽ��ϱ�", "������" };
	static boolean game = true; // ���� ����
	public int[][] pixels = new int[31][31]; // �ٵ� �� ��ǥ ��
	public int count = 0;
	public int winner = 0; // ��,�� �¸� ���� "1 : �浹 �¸� 2: �鵹 �¸�"

	public int[] replay_x = new int[361]; // ���÷��� ����
	public int[] replay_y = new int[361];
	public int replay_num = 0;
	 
	public static int music_count = 1;
	public static String music_String_count = Integer.toString(music_count);
	public static Music music1 = new Music("music",music_String_count+ ".MP3" ,true); // ���� ��ü ����
	public static boolean play_state = true;
	public static MusicPlayThread mpt;
	
	public static void main(String[] args) throws InterruptedException {
		t1 frame = new t1(); // t1 Ŭ������ frame ��ü ����

		music_play();
//		 int music_count = 1;
//		 String music_String_count = Integer.toString(music_count);
//		 Music music1 = new Music("music",music_String_count+ ".MP3" ,true);
//		 music1.start();
	}

	public static void music_play() throws InterruptedException { // �뷡 ��� �Լ�
		int music_count = 0;
		int thread_sleep = 239000;
		System.out.println("play_state : �÷��� �Լ� " + play_state);
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
		private Vector<Point> vStart = new Vector<Point>(); // ����

		public Mypane1() {
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (game == true) {
						Point startP = e.getPoint(); // Ŭ���� �κ��� ����������
						boolean ban = false;
						int color = 1;

						startP.x = (((startP.x)) / 21 * 21); // �ٵ� �� ��ǥ
						startP.y = (((startP.y)) / 21 * 21); // �ٵ� �� ��ǥ

						if (((startP.x >= 0 && startP.x <= 380) && (startP.y >= 0 && startP.y <= 380))) { // �ٵ� �� ���϶�
																											// �ٵϾ� ȣ��
							if (pixels[startP.y / 20 + 5][startP.x / 20 + 5] == 0) { // �ٵϾ��� �����ִ��� Ȯ��

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
										winner = check.confirm(pixels, 1); // �浹 ����Ȯ��
									} else if (count % 2 == 1) {
										pixels[startP.y / 20 + 5][startP.x / 20 + 5] = 3;
										count++;
										Confirm check = new Confirm();
										winner = check.confirm(pixels, 3); // �鵹 ����Ȯ��
									}

									replay_num = count; // ���÷��̸� ���� �ٵ� �� ���� �迭�� ����
									replay_x[count - 1] = (((startP.x)) / 21 * 21);
									replay_y[count - 1] = (((startP.y)) / 21 * 21);

									System.out.println((startP.y / 20 + 5) + " : " + (startP.x / 20 + 5) + " : "
											+ pixels[startP.y / 20 + 5][startP.x / 20 + 5]);
									vStart.add(startP);
									repaint(); // ������ paint�޼ҵ带 �ѹ��� ȣ���ϰ� ���� �� ���(���
												// ó���� �۾��� ȭ�鿡 �ٷ� �����ְ� ������ repaint()
												// �޼ҵ带 ȣ���Ͽ� paint()�޼ҵ� ȣ��

									if (winner == 1) { // �浹 ����Ȯ��
										game = false;
										result = JOptionPane.showOptionDialog(null, "�浹�� �̰���ϴ�.", "Game over",
												JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_CANCEL_OPTION, null,
												buttons, ""); // �˸�â

									} else if (winner == 3) { // �鵹 ����Ȯ��
										game = false;
										result = JOptionPane.showOptionDialog(null, "�鵹�� �̰���ϴ�.", "Game over",
												JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_CANCEL_OPTION, null,
												buttons, ""); // �˸�â
									}
								} else if (ban == true) {
									System.out.println("����Դϴ�.");
									JOptionPane.showMessageDialog(null, "����Դϴ�!");
								}
							}
						}
						if (result == 0) { // ������
							pixels[startP.y / 20 + 5][startP.x / 20 + 5] = 0;
							vStart.remove(startP);
							repaint();

							count--;
							game = true;
							result = 5;
						} else if (result == 1) { // �ٽ��ϱ�
							result = JOptionPane.showConfirmDialog(null, "Replay�� ���ðڽ��ϱ�?", "Replay ����",
									JOptionPane.YES_NO_OPTION);

							if (result == 0) { // ���÷��� ���� ���� ��
								Replay replay = new Replay(replay_x, replay_y, replay_num);
								result = 5;
							}
							for (int i = 0; i <= count; i++) { // ���� ���� ��ǥ���� �̹��� �����
								startP.x = replay_x[i];
								startP.y = replay_y[i];
								vStart.remove(startP);
							}
							repaint();
							replay_x = new int[361]; // ���÷��� ���� �� �ʱ�ȭ
							replay_y = new int[361]; // ���÷��� ���� �� �ʱ�ȭ
							count = 0; // ���÷��� ī��Ʈ �� �ʱ�ȭ

							for (int i = 0; i < 31; i++) { // ���� �迭 �� �ʱ�ȭ
								for (int j = 0; j < 31; j++) {
									pixels[i][j] = 0;
								}
							}
							timer_count++;
							game = true; // �ٽ� �ϱ� �� ���� ���°� true�� ����
							result = 5; // �˸�â ��� �� �ʱ�ȭ
							timer_m = 0; // Ÿ�̸� �� �ʱ�ȭ
							timer_s = 0; // Ÿ�̸� �� �ʱ�ȭ
						} else if (result == 2) { // �����ϱ�
							System.exit(0);
						}
					}
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // �θ� ����Ʈ ȣ��

			ImageIcon icon = new ImageIcon("C:\\Users\\skaru\\Image\\badook.png"); // ���� �� �̹���
			Image img = icon.getImage();
			g.drawImage(img, 0, 0, this);

			ImageIcon icon2 = new ImageIcon("C:\\Users\\������\\Desktop\\black_user.png"); // �浹 ���� �̹���
			Image img2 = icon2.getImage();
			g.drawImage(img2, 400, 0, this);

			ImageIcon icon3 = new ImageIcon("C:\\Users\\������\\Desktop\\white_user.png"); // �� ���� �̹���
			Image img3 = icon3.getImage();
			g.drawImage(img3, 547, 0, this);

			for (int i = 0; i < vStart.size(); i++) {
				Point s = vStart.elementAt(i);

				if (i % 2 == 1) {
					g.setColor(Color.white);
					g.fillOval((int) s.getX(), (int) s.getY(), 21, 21); // �ٵϾ� ũ��
				} else {
					g.setColor(Color.black);
					g.fillOval((int) s.getX(), (int) s.getY(), 21, 21); // �ٵϾ� ũ��
				}
			}
		}
	}
}
