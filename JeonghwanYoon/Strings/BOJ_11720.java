import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11720 {

	public static void main(String[] args) throws IOException {

		/*
		 *  
		 * ���� 
		 * 
		 * N���� ���ڰ� ���� ���� �����ִ�. �� ���ڸ� ��� ���ؼ� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
		 * 
		 * �Է� 
		 * 
		 * ù° �ٿ� ������ ���� N (1 �� N �� 100)�� �־�����. ��° �ٿ� ���� N���� ������� �־�����.
		 * 
		 * ��� 
		 * 
		 * �Է����� �־��� ���� N���� ���� ����Ѵ�.
		 * 
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String Number = br.readLine();
		int[] arr = new int[N];
		int result = 0;
		
		for(int i=0; i<N; i++)
		{
			arr[i] = (Number.charAt(i) - '0'); //char Ÿ�� -> intŸ������ ��ȯ
		}
		
		for(int i=0; i<N; i++)
		{
			result += arr[i];
		}
		
		System.out.println(result);
	}
}
