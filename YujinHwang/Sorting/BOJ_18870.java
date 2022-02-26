/*
문제 링크
https://www.acmicpc.net/problem/18870

제한
시간 : 2 초    메모리 : 512 MB

문제
수직선 위에 N개의 좌표 X1, X2, ..., XN이 있다. 이 좌표에 좌표 압축을 적용하려고 한다.

Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는 서로 다른 좌표의 개수와 같아야 한다.

X1, X2, ..., XN에 좌표 압축을 적용한 결과 X'1, X'2, ..., X'N를 출력해보자.

입력
첫째 줄에 N이 주어진다.
둘째 줄에는 공백 한 칸으로 구분된 X1, X2, ..., XN이 주어진다.
1 ≤ N ≤ 1,000,000
-109 ≤ Xi ≤ 109

출력
첫째 줄에 X'1, X'2, ..., X'N을 공백 한 칸으로 구분해서 출력한다.
 */

/*
풀이
입력받은 수 배열에서 i번째 수보다 작은 수의 갯수를 세어서 i번째에 출력하면 되는데 i번째 수보다 작은 수가 몇 개인지 알려면 오름차순으로 정렬을 해 봐야 한다.
오름차순으로 정렬을 해 보면 i번째 수의 앞에 있는 수들은 다 작은 수들이니까 배열을 순회하면서 그 수가 몇 개인지 세면 i번째 수를 압축한 수가 된다.
정답 출력 형식을 보면 입력된 숫자의 순서대로 압축한 수를 출력해야 하기 때문에 입력 배열과 같은 임시 배열을 만들어 정렬한다.

그런데 최대 백만개가 입력으로 주어지는데 2중 for문을 통해 하나씩 비교하는 n^2 알고리즘을 사용하면 시간초과를 받을 것이기 때문에 정렬 알고리즘은 n log n 시간복잡도를 가지는 것을 쓰는 것이 좋다.
그리고 입력으로 중복된 수가 들어오는데 예제의 답을 보면 중복된 수는 카운트하지 않는다.
즉 1, 1, 2 일 때 2보다 작은 수인 1이 2개 있는데 이럴 경우 답은 0, 0, 1이 되어야 한다. 2보다 작은 수는 1개가 되어야 한다.
그래서 오름차순으로 정렬하고 난 후 중복되는 숫자들은 제거해 주어야 한다. 중복 숫자를 제거하지 않으면 i번째 수보다 작은 수를 셀 때 오차가 생길 것이다.

중복된 숫자들을 제거하고 나면 <i번째 숫자, i번째 숫자보다 작은 숫자의 갯수> pair를 저장하는 map을 만들어서 각 원소를 삽입해준다.
그 다음 마지막으로 입력 원본 배열을 순회하면서 map에서 i번째 원소를 key로 가지는 value를 찾아 출력하면 된다.

시간제한이 2초인데 2788ms로 통과된 거 보면 자바는 느려서 시간을 좀 더 주는 것 같다.

결과
시간 : 2788 ms    메모리 : 254876 KB
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
        {
            arr.add(Integer.parseInt(st.nextToken()));
            tmp.add(arr.get(i));
        }

        Collections.sort(tmp);

        int cnt = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>(); // <num, count>
        map.put(tmp.get(0), 0); // 첫번째 원소는 자기보다 작은 값이 없음
        for (int i = 1; i < N; i++)
        {
            // value 비교가 되도록 한다.(== 연산자는 주소 비교)
            if (!tmp.get(i - 1).equals(tmp.get(i)))
            {
                // 중복된 수가 아닌 경우에만 카운트하고 map에 추가한다.
                cnt++;
                map.put(tmp.get(i), cnt);
            }
        }

        // map에서 i번째 원소를 key로 가지는 value를 찾아 출력하면 된다.
        for (int i = 0; i < N; i++)
            bw.write(map.get(arr.get(i)) + " ");

        bw.flush();
        bw.close();
    }
}