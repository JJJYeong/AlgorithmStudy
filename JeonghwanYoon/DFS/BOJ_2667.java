import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BOJ_2667 {

	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int cnt = 0; // �Ѵ����� 
	static int addresscnt;
	static int[] address = new int[626]; // ������ ���� ��, 25x25�̹Ƿ� �ִ� 626���� ����
	static int nowX;
	static int nowY;
	
	public static void main(String[] args) throws IOException {

		/*
		 * 
		 * ���� 
		 * 
		 * <�׸� 1>�� ���� ���簢�� ����� ������ �ִ�. 1�� ���� �ִ� ����, 0�� ���� ���� ���� ��Ÿ����. ö���� �� ������ ������
		 * ����� ���� ������ ������ �����ϰ�, ������ ��ȣ�� ���̷� �Ѵ�. ���⼭ ����Ǿ��ٴ� ���� � ���� �¿�, Ȥ�� �Ʒ����� �ٸ� ���� �ִ�
		 * ��츦 ���Ѵ�. �밢���� ���� �ִ� ���� ����� ���� �ƴϴ�. <�׸� 2>�� <�׸� 1>�� �������� ��ȣ�� ���� ���̴�. ������
		 * �Է��Ͽ� �������� ����ϰ�, �� ������ ���ϴ� ���� ���� ������������ �����Ͽ� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
		 * 
		 * 
		 * 
		 * �Է� 
		 * 
		 * ù ��° �ٿ��� ������ ũ�� N(���簢���̹Ƿ� ���ο� ������ ũ��� ������ 5��N��25)�� �Էµǰ�, �� ���� N�ٿ��� ���� N����
		 * �ڷ�(0Ȥ�� 1)�� �Էµȴ�.
		 * 
		 * ��� 
		 * 
		 * ù ��° �ٿ��� �� �������� ����Ͻÿ�. �׸��� �� ������ ���� ���� ������������ �����Ͽ� �� �ٿ� �ϳ��� ����Ͻÿ�.
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine()); // ���簢�� �迭���� n = 5~25
		map = new int[n+1][n+1];
		address = new int[626];
		
		for(int i=0; i<n; i++) // �ڷ��Է�
		{
			String s = br.readLine();
			for(int j=0; j<n; j++)
			{
				map[i][j] = s.charAt(j) - '0'; 
			}
		}
		
		
		visited = new boolean[n+1][n+1];
		
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				if(map[i][j] > 0 && !visited[i][j])
				{
					dfs(i,j);
					address[cnt] = addresscnt;
					addresscnt = 0; //������ ���� �� �ʱ�ȭ
					cnt++; // ������ ���
				}
			}
		}
		
		Arrays.sort(address); //������������
		
		System.out.println(cnt);
		for(int i=address.length-cnt; i<address.length; i++)
		{
			System.out.println(address[i]);
		}
		
	}

	static void dfs(int i, int j)
	{
		visited[i][j] = true;
		Stack<int[]> s = new Stack<int[]>();
		s.add(new int[] {i,j});
		
		while(!s.isEmpty())
		{
			int now[] = s.pop();
			 nowX = now[0];
			 nowY = now[1];
			
			for(int k=0; k<4; k++)
			{
				int nextX = nowX + dx[k];
				int nextY = nowY + dy[k];
				
				if(nextX < 0 || nextY <0 || nextX > n || nextY > n)
					continue;
				if(visited[nextX][nextY] || map[nextX][nextY] == 0)
					continue;
				
				s.add(new int[] {nextX,nextY});
				visited[nextX][nextY] = true;
				addresscnt++;
			}
		}
		addresscnt = addresscnt + 1; 
	}
}
