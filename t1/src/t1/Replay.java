	package t1;
	
	import javax.swing.JFrame;
	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.Point;
	import java.util.Vector;
	
	import javax.swing.ImageIcon;
	import javax.swing.JPanel;
	
	public class Replay extends JFrame {
		
		public int[] relpay_x = new int[361];
		public int[] relpay_y = new int[361];
		public int count=0;
		
		public static boolean replay_state = true;
		
		private board pane1; // Mypane1 클래스 객체 생성
		// private Input_stone tt = new Input_stone();
	
		public Replay(int[] replay_x, int[] replay_y, int replay_num) {
			
			relpay_y = replay_y;
			relpay_x = replay_x;
			count = replay_num;
			
			setSize(700, 440);
			setTitle("오목 대전");

			pane1 = new board();
			setContentPane(pane1);
			setVisible(true); // 프레임 출력

			System.out.println("replay 클래스 호출 ???????????");
		}
		
		class board extends JPanel {
			private Vector<Point> vStart = new Vector<Point>();
			public board() {
				for(int i = 0;i<count;i++){
					System.out.println("for");
					Point s=new Point();
					
					s.x = relpay_x[i];
					s.y = relpay_y[i];
					vStart.add(s);
//					repaint();
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						System.out.println(e.getMessage());
//					}
				}
			}
			int b = 0;
			public void paintComponent(Graphics g) {
				super.paintComponent(g);					//부모 페인트 호출
				System.out.println("바둑 판 클래스 호출~!!!!!!!!!!");																				
				ImageIcon icon =
						new ImageIcon("C:\\Users\\남기윤\\Desktop\\Image\\badook1.png");
				Image img = icon.getImage();
				g.drawImage(img,0,0,this);
				
				ImageIcon icon2 = new ImageIcon("C:\\Users\\남기윤\\Desktop\\black_user.png"); // 흑돌 유저 이미지
				Image img2 = icon2.getImage();
				g.drawImage(img2, 400, 0, this);

				ImageIcon icon3 = new ImageIcon("C:\\Users\\남기윤\\Desktop\\white_user.png"); // 흰돌 유저 이미지														
				Image img3 = icon3.getImage();
				g.drawImage(img3, 547, 0, this);
				
				System.out.println(" do !!!!!!!!!!!!!! ");
			
				try{
					Thread.sleep(1500);
				}catch(Exception e ){
					System.out.println(e.getMessage());
				}
				for (int i = 0; i < vStart.size(); i++) {
					Point s = vStart.elementAt(i);
						if (i % 2 == 1) {
						g.setColor(Color.white);
						g.fillOval((int) s.getX(), (int) s.getY(), 21, 21); // 바둑알 크기
					} else {
						g.setColor(Color.black);
						g.fillOval((int) s.getX(), (int) s.getY(), 21, 21); // 바둑알 크기
					}
				if(b == i ){
					b++;
					repaint();
					break;
				}
//				validate();
				}
			}
			
		}
	}
	
