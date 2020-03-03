package t1;

public class Confirm {
	public int confirm (int[][] arr, int color){
		int result = 0;
		int count = 0;
		Loop:
		for(int i=0;i<31;i++){
			for(int j=0;j<31;j++){
				if(arr[i][j] == color && arr[i][j+1] == color){	
					result = check(arr,color,i,j);				//  ㅡ 방향 승자확인
				}if(arr[i][j] == color && arr[i+1][j] == color){
					result = check2(arr,color,i,j);				// | 방향 승자 확인
				}if(arr[i][j] == color && arr[i-1][j+1] == color){
								// / 방향 승자 확인
					for(int ck1=-1, ck2=1; ck2<5; ck1--, ck2++) {
						if(arr[i][j]==arr[i+ck1][j+ck2]) {
							count++;
						}
					}
					
					if(count == 4) {
						result = arr[i][j];
					}
					count=0;
				}if(arr[i][j] == color && arr[i+1][j+1] == color){
					result = check4(arr,color,i,j);				// \방향 승자 확인
				}if(result == color){
					break Loop;
				}
			}
		}
		
		return result;
	}
	
	public int check(int [][] arr, int color, int i, int j){		// ㅡ 방향 승자확인
		int count = 0;
		int result = 0;

		for(int q=0;q<5;q++){
			if(arr[i][j+q] == color && (arr[i][j-1] != color && arr[i][j+5] != color)){
				count++;
			}if(count == 5){
				result = color;
			}
		}
		return result;
	}
	public int check2(int [][] arr, int color, int i, int j){		// |방향 승자 확인
		int count = 0;
		int result = 0;
		for(int q=0;q<5;q++){
			if(arr[i+q][j] == color && (arr[i-1][j] != color && arr[i+5][j] != color)){
				count++;
			}
		}if(count == 5){
			result = color;
		}
		return result;
	}
//	public int check3(int [][] arr, int color, int i, int j){	// / 방향 승자 확인
//		int count = 0;
//		int result = 0;
//		for(int q=0;q<5;q++){
//			if(arr[i-q][j+q] == color && (arr[i+1][j-1] != color &&arr[i-5][j+5] != color)){
//				count++;
//			}
//		}if(count == 5){
//			result = color;
//		}
//		return result;
//	}
	public int check4(int [][] arr, int color, int i, int j){	// \ 방향 승자 확인
		int count = 0;
		int result = 0;
		for(int q=0;q<5;q++){
			if(arr[i+q][j+q] == color && (arr[i-1][j-1] != color && arr[i+5][j+5] != color)){
				count++;
			}}if(count == 5){
			result = color;
		}
			System.out.println(" i : "+i + " j : "+j + " count : " + count + " j+5 : " + (j + 5));	

		return result;
	}
}

