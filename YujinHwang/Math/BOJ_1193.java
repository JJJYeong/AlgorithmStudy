/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/1193
 * 
 * # 문제
 * 무한히 큰 배열에 다음과 같이 분수들이 적혀있다.
 * 
 * 1/1	1/2	1/3	1/4	1/5	…
 * 2/1	2/2	2/3	2/4	…	…
 * 3/1	3/2	3/3	…	…	…
 * 4/1	4/2	…	…	…	…
 * 5/1	…	…	…	…	…
 * …	…	…	…	…	…
 * 
 * 이와 같이 나열된 분수들을 1/1 → 1/2 → 2/1 → 3/1 → 2/2 → … 과 같은 지그재그 순서로 차례대로 1번, 2번, 3번, 4번, 5번, … 분수라고 하자.
 * 
 * X가 주어졌을 때, X번째 분수를 구하는 프로그램을 작성하시오.
 * 
 * # 제한
 * 시간 제한 : 0.5초, 메모리 제한 : 256 MB
 * 
 * # 입력
 * 첫째 줄에 X(1 ≤ X ≤ 10,000,000)가 주어진다.  
 * 
 * # 출력
 * 첫째 줄에 분수를 출력한다. 
 * 
 */

/*
 * # 풀이
 * ! 1시간 고민했지만 풀이를 찾지 못해서 질문 게시판 참고함
 * 
 * x/y 라고 해볼께요. 일단 x=1일때를 봅시다. 왼쪽아래, 또는 오른쪽 으로 이동하죠? 이 때 x+y를 봅시다. 
 * x+y가 짝수일때 오른쪽으로 이동하고, 홀수일 때 왼쪽아래로 이동하죠. 이와 마찬가지로 y=1일 때를 봅시다. 
 * x+y가 짝수일 때 오른쪽 위로 이동하고, 홀수일 때 아래로 이동합니다.
 * 
 * 다음과 같이 생각하시면 쉽게 풀 수 있을겁니다.
 * 
 * # x==1 or y==1 : (x+y)가 홀수일 때와 짝수일 때로 나눠서 시행하기
 * # else : 바로 이전에 한 이동과 같은 방향으로 이동
 * 
 * 
 * 위의 글에서 아이디어를 얻어서 문제 해결법을 생각해 봄
 * 
 * 0. 필요한 것 : 현재 번호, 현재 분자와 분모를 저장할 변수, 이동 방향을 저장할 변수
 * 
 * 1. x==1 or y==1 이라면 (x+y)가 홀수일 때 이동 방향을 왼쪽으로 설정한다. 
 * 	- 왼쪽으로 이동한다는 것은 x가 증가한다.
 * 1-1. (x+y)가 짝수면 이동방향을 오른쪽으로 설정한다.
 * 	- 오른쪽으로 이동한다는 것은 y가 증가한다.
 * 
 * 2. 1번 경우에 들어가지 않는 경우에는 1번 경우에서 정해진 이동방향에 따라 이동한다.
 * 2-1. 왼쪽 이동이면 x++, y--
 * 2-2. 오른쪽 이동이면 x--, y++
 * 
 * 3. 위의 두 경우 모두 현재 번호가 증가하니까 현재 번호를 저장한 변수도 증가시킨다. 
 * 
 * 4. 그런데 x==1 or y==1일 때에도 증가시키지 않는 쪽을 감소시켜야 하는데 최소값은 1이어야 하는데 그냥 감소시키면 0이 되어버리니까
 * 	  1보다 클 때에만 감소시키도록 한다.  
 * 
 * 5. 위의 과정을 코드로 구현하면 정답!!
 * 
 * 
 * => 결과 : 메모리) 16184kb, 시간) 176ms
 * 
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		final int LEFT = 1;
		final int RIGHT = 2;
		
		// 입력 시간을 줄이기 위해 버퍼 입력 사용
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int X = Integer.parseInt(bf.readLine());
		
		int left = 1, right = 1, num = 1; // 분자, 분모, 현재 번호
		int dir = LEFT; // 이동방향 flag. 초기값은 왼쪽으로 이동
		
		if (1 == X) // 입력이 1이면 그냥 1/1 출력 후 종료
			System.out.println("1/1");
		else // 2부터 연산 시작
		{
			while (num < X)
			{
				// left=1일 때 or right=1일 때 
				// 어느 방향으로 이동을 시작하는지도 변경
				if (1 == left || 1 == right)
				{
					// 맨 윗줄에서 이동하면 증가하지 않는 쪽은 1이 되어야 함
					// left+right가 짝수면 right 증가 -> 오른쪽으로 이동
					if (0 == (left + right) % 2)
					{
						dir = RIGHT;
						right++;
						if (1 < left) // 증가하지 않는 쪽을 감소시켜야 하는데 그냥 감소시키면 0 이하가 되니까 1보다 클 때만 감소시킨다.
							left--;
					}
					else // left+right가 홀수면 left 증가 -> 왼쪽으로 이동 
					{
						dir = LEFT;
						left++;
						if (1 < right)
							right--;
					}
				}
				else // 직전 진행방향에 따라 left와 right를 각각 반대로 증감
				{
					// 오른쪽 대각선 방향으로 이동 : left--, right++
					if (RIGHT == dir)
					{
						left--;
						right++;
					}
					else // 왼쪽 대각선 방향으로 이동 : left++, right-- 
					{
						left++;
						right--;
					}
				}
				
				// 무슨 방향이 되었든 진행하고 나면 번호 증가
				num++;
			} 
			
			System.out.println(left + "/" + right);
		}
		
	}
	
}
