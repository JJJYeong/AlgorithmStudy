/*
 * # 문제 주소
 * https://www.acmicpc.net/problem/2292
 * 
 * # 문제
 * 위의 그림과 같이 육각형으로 이루어진 벌집이 있다. 
 * 그림에서 보는 바와 같이 중앙의 방 1부터 시작해서 이웃하는 방에 돌아가면서 1씩 증가하는 번호를 주소로 매길 수 있다. 
 * 숫자 N이 주어졌을 때, 벌집의 중앙 1에서 N번 방까지 최소 개수의 방을 지나서 갈 때 몇 개의 방을 지나가는지(시작과 끝을 포함하여)를 계산하는 프로그램을 작성하시오. 
 * 예를 들면, 13까지는 3개, 58까지는 5개를 지난다. 
 * 
 * # 제한
 * 시간 제한 : 2초, 메모리 제한 : 128 MB
 * 
 * # 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,000,000,000)이 주어진다.  
 * 
 * # 출력
 * 입력으로 주어진 방까지 최소 개수의 방을 지나서 갈 때 몇 개의 방을 지나는지 출력한다. 
 * 
 */

/*
 * # 풀이
 * 중심에서부터 벌집 테두리가 하나씩 늘어날 때 마다 6, 12, 18, ... 6의 배수로 늘어난다. 
 * 즉 중심에서부터 벌집까지의 거리가 2인 테두리(첫번째 테두리)의 벌집 개수는 6개이고 거리가 3인 테두리는 12개, ... 식으로 늘어난다.
 * 그리고 벌집의 방 번호는 1부터 시작하기 때문에 N번째 방 번호는 1 + (6*1) + (6*2) + (6*3) + ... (6*N)이라는 것을 알 수 있다.
 * 여기서 N을 구하는 것을 기본 아이디어로 잡고 시작했다.
 * 
 * 문제의 조건은 1번에서 N번까지의 거리를 구하는 것이기 때문에 
 * 첫번째 테두리의 가장 큰 수인 7번방까지의 거리를 구한다면 (6*1) + 1 = 7이다. 이 때 N은 1이다. 
 * 그런데 처음 위치도 카운트해야 하기 때문에 1을 더해줘서 최종 정답은 2가 된다. 
 * 첫번째 테두리에 있는 가장 큰 수 까지의 거리가 2니까 같은 테두리에 있는 더 작은 숫자들까지의 거리도 2가 된다. 
 * 
 * 두번째 테두리의 가장 큰 수인 19까지의 거리를 구하면 (6*1) + (6*2) + 1 = 19이고 이 때의 N은 2이고 1을 더해주면 총 거리는 3이 된다.
 * 마찬가지로 두번째 테두리의 가장 큰 수 까지의 거리가 3이니까 같은 테두리에 있는 더 작은 숫자들까지의 거리도 3이다. 
 * 
 * 이 아이디어를 N번째 숫자가 될 때까지 확장시켜가면 정답을 구할 수 있다. 
 * 
 * 위에서 더했던 과정을 반대로 생각하면 i=1부터 1씩 증가할 때 N에서 6*i을 빼고 남은 나머지가 1보다 작거나 같다면 N은 i번째 테두리에 있다고 할 수 있다.
 * 그러면 N=i가 되는 것이고 이렇게 구한 N에다 첫 시작지점인 1을 더해주면 정답을 구할 수 있다.
 * 
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// 입출력 시간을 줄이기 위해 버퍼 입출력 사용
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int A = Integer.parseInt(bf.readLine());
		int ans = 1; // 거리의 최소값은 1부터 시작한다.
		if (1 < A) // A가 1이면 처음 위치에 있는거니까 1 그대로 출력하고 1보다 크면 연산 시작
		{
			while (true)
			{
				A -= 6 * ans; 
				ans++; // 연산에 사용했던 ans에 1을 더해야 최종 정답이 되고 다음 반복을 진행할 수 있도록 1증가
				if (1 >= A) // 6*ans를 빼고 나서 남은 나머지가 1 이하면 해당 숫자는 ans번째 테두리에 있는 것이니까 반복 종료
					break;
			}
		}
		
		bw.write(Integer.toString(ans));
		
		bw.close();
		
	}
	
}