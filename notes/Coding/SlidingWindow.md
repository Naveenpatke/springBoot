# Sliding window

- Identification pattern for sliding window problem
    - Array / string
    - Find subArray / SubString
    - window size
    - Largest / smallest subarray forming the given string

- Types
    - Fixed size
        - Max sum sub array of size K
    - variable size
        - find the largest / smallest, subArray / subString subjected to given condition

### Fixed sliding window

- Max sum sub array of size K
    - Input : A = {9, 1, 2, 3, 4, 5, 6}, K = 3
    - Output : 15

  -variants - [stocks question, where considering 3 days range which 3 days range i got max profit]

```java
import java.util.*;

class SlidingWindowP1Fixed {
    public static void main(String[] args) {
        int[] arr = {9, 1, 2, 3, 4, 5, 6};
        int n = arr.length, k = 3;

        System.out.println("Largest sum is : " +
                longestSubArrForGivenWindowSize(arr, n, k));
    }

    public static int longestSubArrForGivenWindowSize(int[] A, int n, int k) {
        int start = 0, end = 0, sum = 0, lSubArray = Integer.MIN_VALUE, count = 0;
        while (start <= n - k) {
            while (end < n && count < k) {
                sum += A[end++];
                count++;
            }

            if (sum > lSubArray) {
                lSubArray = sum;
            }
            sum -= A[start++];
            count--;

        }
        return lSubArray;

    }
}
```

---

- First negative integer in every window size k
    - case 1
        - Input : arr = {12,-1,-7,8,-15,30,-16,28}, k=3
        - Output : { -1 -1 -7 -15 -15 -16 }
    - case 2
        - Input : arr = {-1,2,-4,-5,8,9,10, 20,-11}, k= 4
        - Output : { -1 -4 -4 -5 0 -11 }

```java
import java.util.*;

class SlidingWindowP2Fixed {
    public static void main(String[] args) {
        int[] arr = {-1, 2, -4, -5, 8, 9, 10, 20, -11};
        int n = arr.length, k = 4;

        System.out.print("Length of the longest subarray with sum K is : ");
        firstNegNumberInGivenWindow(arr, n, k);
    }

    public static void firstNegNumberInGivenWindow(int[] A, int n, int k) {
        int start = 0, end = 0, count = 0;
        List<Integer> list = new ArrayList<>();
        while (start <= n - k) {
            while (end < n && count < k) {
                if (A[end] < 0) {
                    list.add(A[end]);
                }
                end++;
                count++;
            }
            if (list.size() == 0) {
                System.out.print(0 + " ");
            } else if (A[start] >= 0)
                System.out.print(list.get(0) + " ");
            else if (A[start] < 0) {
                System.out.print(list.get(0) + " ");
                list.remove(list.get(0));
            }
            start++;
            count--;
        }
    }
}
```

---

- Count occurence of anagram
  - case 1
    - Input : str1 = "forsadasdffrosdhoorfaforfro", str2 = "for"
    - Output : 6
  - case 2
      - Input : str1 = "aabchaabaabbbaada", str2 = "abaa"
      - Output : 2

```java
import java.util.*;

class SlidingWindowP3Fixed {
    public static void main(String[] args) {
        String str1 = "forsadasdffrosdhoorfaforfro";
        String str2 = "for";

        System.out.print("Total occurance of anagram in given string : " + calculateOcurranceOfAnagraminGivenString(str1, str2));

    }

    public static int calculateOcurranceOfAnagraminGivenString(String str1, String str2) {
        int start = 0, end = 0, count = 0;
        Map<Character, Integer> alpCounter = new HashMap<>();
        for (int i = 0; i < str2.length(); i++) {
            alpCounter.put(Character.valueOf(str2.charAt(i)), alpCounter.getOrDefault(str2.charAt(i), 0) + 1);
        }

        int uniAlp = alpCounter.keySet().size(), n = str1.length(), k = str2.length(), anaCounter = 0;

        while (start <= (n - k)) {
            while (end < n && count < k) {
                if (alpCounter.containsKey(str1.charAt(end))) {
                    int val = alpCounter.get(str1.charAt(end)) - 1;
                    alpCounter.put(Character.valueOf(str1.charAt(end)), val);
                    if (val == 0)
                        uniAlp--;

                }
                end++;
                count++;
            }
            if (uniAlp == 0)
                anaCounter++;

            if (alpCounter.containsKey(str1.charAt(start)) && alpCounter.get(str1.charAt(start)) == 0)
                uniAlp++;

            if (alpCounter.containsKey(str1.charAt(start))) {
                alpCounter.put(Character.valueOf(str1.charAt(start)), alpCounter.get(str1.charAt(start)) + 1);
            }
            count--;
            start++;

        }
        return anaCounter;
    }
}
```
---
