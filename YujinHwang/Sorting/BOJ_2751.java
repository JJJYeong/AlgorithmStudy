/*
문제 링크
https://www.acmicpc.net/problem/2751

제한
시간 : 2 초    메모리 : 256 MB

문제
N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.

입력
첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수가 주어진다. 이 수는 절댓값이 1,000,000보다 작거나 같은 정수이다. 수는 중복되지 않는다.

출력
첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.
 */

/*
풀이
제한시간 2초에 N 최대값이 백만이라 n log n 정렬 알고리즘을 써야 했는데 합병정렬을 쓰면 좋겠지만 구현하기 귀찮기도 하고... 자바에 구현되어 있는 sort 함수 사용법도 익힐겸 저걸 썼다.
처음에는 Arrays.sort를 썼는데 1700ms 가 나와서 시간복잡도를 알아보니까 평균 n log n이지만 최악의 경우에는 n^2이었다.
그래서 최악의 경우에도 n log n을 보장한다는 Collections.sort를 써 봤는데 1600ms 정도가 나와서 크게 시간 차이가 나지는 않았다.
합병정렬 기억을 되살릴 겸 한 번 구현해 보는 것이 좋을 거 같다.

결과
시간 : 1636 ms    메모리 : 171216 KB
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main
{
    static BufferedWriter bw;
    static int N;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < N; i++)
            arr.add(Integer.parseInt(br.readLine()));

        Collections.sort(arr);

        for (int i = 0; i < N; i++)
            bw.write(arr.get(i) + "\n");

        bw.flush();
        bw.close();
    }
}