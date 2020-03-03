package t1;

public class BanNumber {
	public static boolean banNumber(int [][] arr , int y, int x, int color) {
		// TODO Auto-generated method stub
		
		boolean ban = false;
		int count = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
		int count6 = 0;
		int count7 = 0;
		int count8= 0;
		int fine = 0;
		int fine2 = 0;
		int fine3 = 0;
		int fine4 = 0;
		int cor = 0;
		if(color == 1){
			cor = 3;
		}else if(color == 3){
			cor = 1;
		}
		for(int j=0;j<3;j++){
			int result = 0;
			if(arr[y][x+j] == color){
				count++;
				if(count == 2 && arr[y][x+3] == 0 && (arr[y][x-1] != cor)){		// ㅡ 방향 오른쪽 방향3*3 체크
					result++;
					fine++;
				}
			}if(arr[y][x-j] == color){
				count2++;
				if(count2 == 2 && arr[y][x-3] == 0 && arr[y][x+1] != cor){			// ㅡ 왼쪽 방향 3*3체크
					result++;
					fine++;
				}
			}if(arr[y-j][x+j] == color){
				count3++;
				if(count3 == 2 && arr[y-3][x+3] == 0 && arr[y+1][x-1] != cor){		// / 오른쪽 대각선 방향 3*3체크
					result++;
					fine2++;
				}
			}if(arr[y+j][x-j] == color){
				count4++;
				if(count4 == 2 && arr[y+3][x-3] == 0 && arr[y-1][x+1] != cor){		// / 왼쪽 아래 방향 3*3체크
					result++;
					fine2++;
				}
			}if(arr[y+j][x] == color){
				count5++;
				if(count5 == 2 && arr[y+3][x] == 0 && arr[y-1][x] != cor){			// | 아래쪽 방향 3*3 체크
					result++;
					fine3++;
				}
			}if(arr[y-j][x] == color){
				count6++;
				if(count6 == 2 && arr[y-3][x] == 0 && arr[y+1][x] != cor){			//  | 위쪽 방향 3*3체크
					result++;
					fine3++;
				}
			}if(arr[y-j][x-j] == color){
				count7++;
				if(count7 == 2 && arr[y-3][x-3] == 0 && arr[y+1][x+1] != cor){		// \ 위쪽 방향 3*3체크
					result++;
					fine4++;
				}
			}if(arr[y+j][x+j] == color){
				count8++;
				if(count8 == 2 && arr[y+3][x+3] == 0 && arr[y-1][x-1] != cor){			// \ 아래쪽 방향 3*3체크
					result++;
					fine4++;
				}
			}if(arr[y][x+1] == color && arr[y][x-1] == color && arr[y-1][x] == color && arr[y+1][x] == color
					&& arr[y][x+2] == 0 && arr[y][x-2] == 0 && arr[y-2][x] == 0 && arr[y+2][x] == 0){
				result = 2;
			}if(arr[y-1][x-1] == color && arr[y-1][x+1] == color && arr[y+1][x-1] == color && arr[y+1][x+1] == color
					&& arr[y-2][x-2] == 0 && arr[y-2][x+2] == 0 && arr[y+2][x-2] == 0 && arr[y+2][x+2] == 0){
				result = 2;
			}if(result == 2 && (fine !=2 && fine2 != 2 && fine3 != 2 && fine4 != 2)){
				ban = true;
			}
		}
		
		return ban;
//		int[][] confirm_ban = pixels;
//		int confirm = 0;	// confirm값이 2 이면 금수 
//		int fine1 = 0;		// \방향일때는 놓을수 있도록
//		int fine2 = 0;		// /방향일때는 놓을수 있도록
//		int fine3 = 0;		// |방향일때는 는 놓을수 있도록
//		int fine4 = 0;		// ㅡ 방향일때는 놓을수 있도록
//		boolean ban = false;	// 금수 판정
//		
//		if ((y >= 3 && x >= 3) && (y<=16 && x<=16)) {
//			if (confirm_ban[y - 1][x - 1] == color 
//					&& confirm_ban[y - 2][x - 2] == color && confirm_ban[y - 3][x - 3] == 0){
//				confirm++;
//				fine1++;	
//			}
//			
//			System.out.println(" 11111 : "+confirm);
//			if(confirm_ban[y][x-1] == color 
//					&& confirm_ban[y][x-2] == color && confirm_ban[y][x-3] == 0){
//				confirm++;
//				fine4++;	
//			}
//			System.out.println(" 22222 : "+confirm);
//			if(confirm_ban[y-1][x+1] == color
//					&& confirm_ban[y-2][x+2] == color && confirm_ban[y-3][x+3] == 0){
//				confirm++;
//				fine3++;
//			}
//				
//			System.out.println(" 33333 : "+confirm);
//			if(confirm_ban[y][x+1] == color
//					&& confirm_ban[y][x+2] == color && confirm_ban[y][x+3] == 0){
//				confirm++;
//				fine4++;
//			}
//			System.out.println(" 44444 : "+confirm);
//			if(confirm_ban[y+1][x+1] == color
//					&& confirm_ban[y+2][x+2] == color && confirm_ban[y+3][x+3] == 0){
//				confirm++;
//				fine1++;
//			}
//				
//			System.out.println(" 5555 : "+confirm);
//			if(confirm_ban[y+1][x] == color 
//					&& confirm_ban[y+2][x] == color && confirm_ban[y+3][x] == 0){
//				confirm++;
//				fine2++;
//			}
//			System.out.println(" 6666 : "+confirm);
//			if(confirm_ban[y+1][x-1] == color
//					&& confirm_ban[y+2][x-2] == color && confirm_ban[y+3][x-3] == 0){
//				confirm++;
//				fine3++;
//			}
//			System.out.println(" 7777 : "+confirm);
//			if(confirm_ban[y-1][x] == color
//					&& confirm_ban[y-2][x] == color && confirm_ban[y-3][x] == 0){
//				confirm++;
//				fine2++;
//			}
//			System.out.println(" 9999 : "+confirm);
//			if(confirm_ban[y-1][x] == color
//					&& confirm_ban[y+1][x] == color && confirm_ban[y][x-1] == color && confirm_ban[y][x+1] == color
//					&& confirm_ban[y-2][x] ==0 && confirm_ban[y+2][x] == 0 && confirm_ban[y][x-2] == 0 && confirm_ban[y][x+2] == 0)
//				confirm = 2;
//			if(confirm_ban[y-1][x-1] == color 
//					&& confirm_ban[y-1][x+1] == color && confirm_ban[y+1][x-1] == color && confirm_ban[y+1][x+1] == color
//					&& confirm_ban[y-2][x-2] == 0 && confirm_ban[y-1][x+2] == 0 && confirm_ban[y+2][x-2] == 0 && confirm_ban[y+2][x+2] == 0)
//				confirm = 2;
//			System.out.println(" confirm : "+confirm);
//			System.out.println(" confirm_ban[y-1][x] : "+ confirm_ban[y-1][x] + " confirm_ban[y-2][x] : " + confirm_ban[y-2][x]+" confirm_ban[y-3][x] : " + confirm_ban[y-3][x]);
//			System.out.println(" color : "+ color);
//			System.out.println(" fine1 : "+ fine1 + " fine2 : " + fine2 + " fine3 : "+fine3 + " fine4 : "+fine4);
//		}else if(x<=2 && y<=2){
//			if(confirm_ban[y][x+1] == color
//					&& confirm_ban[y][x+2] == color && confirm_ban[y][x+3] ==0)
//				confirm++;
//			if(confirm_ban[y+1][x] == color
//					&& confirm_ban[y+2][x] == color && confirm_ban[y+3][x] == 0)
//				confirm++;
//			if(confirm_ban[y+1][x+1] == color
//					&& confirm_ban[y+2][x+2] == color && confirm_ban[y+3][x+3] == 0)
//				confirm++;
//		}
//		if(confirm == 2 && (fine1 != 2 || fine2 != 2 || fine3 != 2 || fine4 != 2)){
//			ban = true;
//		}
//		return ban;
	}
}
