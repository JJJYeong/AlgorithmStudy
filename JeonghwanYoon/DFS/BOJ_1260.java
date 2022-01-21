import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1260 {

	static int N; 
	static int M; 
	static int V; 
	static int[][] map;
	static boolean[] visited;
	
	
	public static void main(String[] args) {
		
	/*
	 * ����
	 * 
	 * �׷����� DFS�� Ž���� ����� BFS�� Ž���� ����� ����ϴ� ���α׷��� �ۼ��Ͻÿ�. ��, �湮�� �� �ִ� ������ ���� ���� ��쿡��
	 * ���� ��ȣ�� ���� ���� ���� �湮�ϰ�, �� �̻� �湮�� �� �ִ� ���� ���� ��� �����Ѵ�. ���� ��ȣ�� 1������ N�������̴�.
	 * 
	 * �Է�
	 * 
	 * ù° �ٿ� ������ ���� N(1 �� N �� 1,000), ������ ���� M(1 �� M �� 10,000), Ž���� ������ ������ ��ȣ V��
	 * �־�����. ���� M���� �ٿ��� ������ �����ϴ� �� ������ ��ȣ�� �־�����. � �� ���� ���̿� ���� ���� ������ ���� �� �ִ�. �Է�����
	 * �־����� ������ ������̴�.
	 * 
	 * ���
	 * 
	 * ù° �ٿ� DFS�� ������ �����, �� ���� �ٿ��� BFS�� ������ ����� ����Ѵ�. V���� �湮�� ���� ������� ����ϸ� �ȴ�.
	 * 
	 */
		
	Scanner sc = new Scanner(System.in);
	
	N = sc.nextInt(); // ��������
	M = sc.nextInt(); // ��������
	V = sc.nextInt(); // �������� 
	
	map = new int[1001][1001]; // ��ǥ�� (1~1000���� �̹Ƿ�)
	visited = new boolean[N+1]; // �ʱⰪ�� false
	
	
	for(int i=0; i<M; i++) //���� ������� ����
	{
		int x = sc.nextInt();
		int y = sc.nextInt();
		
		map[x][y] = map[y][x] = 1;
		
	}
	sc.close();
	
	dfs(V);
	visited = new boolean[N+1]; // �ε���üũ �ʱ�ȭ
	System.out.println();
	
	bfs();

	}
	
	//�������� ������ �޾� Ȯ��, ��� �� ���� �������� ã�� �������� �����Ͽ� ��ȣ��
	public static void dfs(int i)
	{
		visited[i] = true;
		System.out.print(i + " ");
		
		for(int j=1; j<=N; j++)
		{
			if(map[i][j] == 1 && visited[j] == false) // i�� �ε����� ����Ǿ������鼭, �湮�����ִ� �ε����� ���ܽ�Ű��(Depth-First�̹Ƿ�)
			{
				dfs(j);
			}
			
		}
	}
	
	public static void bfs() { //V�� �Ἥ �����ϸ�ǰ� �ε����켱 -> �״������� [V][j]�� j�� ������ �ε��� ->  
		// -> LinkedList ���ΰ� �ʿ� , arraylist�� ���ι迭�� ��ü�� �����ؼ� ���� LinkedList�� ���� ������ ��ũ�ؼ� ü��ó�� ����
		// �� arraylist�� �迭ó�� �߰��� ���Եǰų� �����Ǹ� �ش��ε��� �޺κ��� �о����������
		// LinkedList�� �߰��� ���Եǰų� �����Ǹ� �����Ȱ� ���� �׻��̸� �����ع������
		// ���� arraylist�� ���������� �߰��ϰų� �����Ҷ� ������ LinkedList�� �߰��� ���԰ų� �����Ҷ� ����
		
		Queue<Integer> queue = new LinkedList<Integer>(); // bfs�� ququeŸ���̹Ƿ� QueueŸ�� LinkedList����Ʈ ����
		queue.offer(V);
		visited[V] = true;
		System.out.print(V + " ");
		
		while(!queue.isEmpty()) // queue�� �������� �ݺ�!
		{
			int temp = queue.poll(); // poll�� queue�� ���� ������� ���°� 
		
			for(int j=1; j<=N; j++)
			{
				if(map[temp][j]== 1 && !visited[j])
				{
					queue.offer(j);
					visited[j] = true; // �ߺ��ε��� ����!
					System.out.print(j + " "); // -> V�ε����� j�� ��� offer�ϰ� ���� �ٽ� 
				}
			}
		}
	}
}
