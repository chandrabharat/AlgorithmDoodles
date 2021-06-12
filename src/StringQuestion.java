import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class StringQuestion {


    public boolean unique(String str) {
        if (str.equals("")) {
            return true;
        }
        if (str.length() == 2) {
            return str.charAt(0) != (str.charAt(1));
        }
        if (str.length() > 2) {
            if (str.substring(1).contains(str.substring(0, 1))) {
                return false;
            }
        }
        return unique(str.substring(1));
    }

    public String addStrings(String num1, String num2) {
        HashMap<String, Integer> conversion = new HashMap<>();
        int multiplier = 1;
        int result = 0;
        for (Integer i = 0; i < 10; i++) {
            conversion.put(String.valueOf(i), i);
        }
        while (!num1.equals("") || !num2.equals("")) {
            String first = (!num1.equals("")) ? num1.
                    substring(num1.length() - 1) : null;
            String second = (!num2.equals("")) ? num2.
                    substring(num2.length() - 1) : null;
            int add1 = (first != null) ? conversion.get(first): 0;
            int add2 = (second != null) ? conversion.get(second): 0;
            result = ((add1 + add2) * multiplier) + result;
            multiplier *= 10;
            if (num1.length() != 0) {
                num1 = num1.substring(0, num1.length() - 1);
            }
            if (num2.length() != 0) {
                num2 = num2.substring(0, num2.length() - 1);
            }
        }
        return String.valueOf(result);
    }

    public boolean isUnique(String str) {
        for (int i = 1; i < str.length(); i++) {
            if (str.substring(i).contains(str.substring(i- 1, i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isPermutation(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            Character c = str1.charAt(i);
            if (!map.containsKey(str1.charAt(i))) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        for (int i = 0; i < str2.length(); i++) {
            Character c = str1.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            } else if (map.get(c) == 1) {
                map.remove(c);
            } else {
                map.put(c, map.get(c) - 1);
            }
        }
        return map.isEmpty();
    }

    ArrayList<String> allPerm = new ArrayList<>();
    public boolean checkInclusion(String s1, String s2) {
        permutation(s1, 0, s1.length() - 1);
        for (int i = 0; i < allPerm.size(); i++) {
            if (s2.contains(allPerm.get(i))) {
                return true;
            }
        }
        return false;
    }


    public void permutation(String str, int left, int right) {
        if (left == right) {
            allPerm.add(str);
        } else {
            for (int i = left; i <= right; i++) {
                String swapped = swap(str, left, i);
                permutation(swapped, left + 1, right);
            }
        }
    }

    public String swap(String str, int left, int right) {
        char[] strArray = str.toCharArray();
        char temp = strArray[left];
        strArray[left] = strArray[right];
        strArray[right] = temp;
        return new String(strArray);
    }


    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int i = 0;
        int j = 0;
        HashSet<Character> set = new HashSet<>();
        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                result = Math.max(result, j - i);
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        HashSet<ArrayList<Integer>> set = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int x = 0; x < nums.length - 1; x++) {
            int bottom = x + 1;
            int top = nums.length - 1;
            while (bottom != top) {
                if (nums[x] + nums[bottom] + nums[top] == 0) {
                    ArrayList<Integer> element = new ArrayList<>();
                    element.add(nums[x]);
                    element.add(nums[bottom]);
                    element.add(nums[top]);
                    Collections.sort(element);
                    if (!set.contains(element)) {
                        result.add(element);
                        set.add(element);
                    }
                    top--;
                } else if (nums[x] + nums[bottom] + nums[top] > 0) {
                    top--;
                } else {
                    bottom++;
                }
            }
        }
        return result;
    }

    public boolean isBadVersion(int mid) {
        return mid >= 1702766719;
    }

    public int firstBadVersion(int n) {
        return helper(0, n);
    }

    public int helper(int low, int high) {
        if (low == high) {
            return low;
        }
        int mid = (low + high) / 2;
        if (isBadVersion(mid)) {
            return helper(low, mid);
        } else {
            return helper(mid + 1, high);
        }
    }

    public String upperCase(String s) {
        return s.toUpperCase();
    }

    public static int[] solution(int area) {
        ArrayList<Integer> arr = new ArrayList<>();
        while (area != 0) {
            int largestSubArea = helper(area);
            arr.add(largestSubArea);
            area = area - largestSubArea;
        }
        int[] result = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            result[i] = arr.get(i);
        }
        return result;
    }

    public static int helper(int area) {
        if (area == 1) {
            return 1;
        }
        int half = (int) Math.sqrt(area);
        if (half * half == area) {
            return area;
        }
        return helper(area - 1);
    }

    public int[] squareUp(int n) {
        int counter = 0;
        int x = n;
        int[] temp = new int[n * n];
        for (int i = 0; i < n; i++) {
            int val = 1;
            while (val <= x) {
                temp[counter] = val;
                counter++;
                val++;
            }
            for (int j = x; j < n; j++) {
                temp[counter] = 0;
                counter++;
            }
            x= x-1;
        }
        for (int i = 0; i < temp.length/2; i++) {
            int first = temp[i];
            temp[i] = temp[temp.length -1 - i];
            temp[temp.length -1 - i] = first;
        }
        return temp;
    }

    public static int uniqueMorseRepresentations(String[] words) {
        StringBuilder convertingWord = new StringBuilder();
        HashSet<String> seen = new HashSet<>();
        String[] alphabet = new String[]{".-","-...","-.-."
                ,"-..",".","..-.","--.","....","..",".---","-.-"
                ,".-..","--","-.","---",".--.","--.-",".-.","...",
                "-","..-","...-",".--","-..-","-.--","--.."};
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                convertingWord.append(alphabet[words[i].charAt(j) - 'a']);
            }
            if (!seen.contains(new String(convertingWord))) {
                seen.add(new String(convertingWord));
            }
            convertingWord.delete(0, convertingWord.length());
        }
        return seen.size();
    }

    public static int minimumLengthEncoding(String[] words) {
        int lengthOfWord = 0;
        HashSet<String> allVariations = new HashSet<>();
        Arrays.sort(words);
        for (int i = words.length - 1; i >= 0; i--) {
            lengthOfWord = allVariations.contains(words[i])
                    ? lengthOfWord:lengthOfWord + words[i].length();
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < words[i].length(); j++) {
                temp.append(words[i].charAt(j));
                if (!allVariations.contains(words[i].charAt(j))) {
                    allVariations.add(new String(temp));
                }
            }
        }
        return lengthOfWord;
    }

    public static int maximum69Number (int num) {
        int ans = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        while (num != 0) {
            arr.add(0, num % 10);
            num = num/10;
        }
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == 6) {
                arr.set(i, 9);
                break;
            }
        }
        for (int i = 0; i < arr.size(); i++) {
            ans = 10 * ans + arr.get(i);
        }
        return ans;
    }

    public static int numWaterBottles(int numBottles, int numExchange) {
        return drunk(numBottles, numExchange, 0);
    }

    public static int drunk(int numBottles, int numExchange, int remainder) {
        if (numBottles == 0 && remainder < numExchange) {
            return 0;
        }
        if (remainder >= numExchange) {
            numBottles += remainder / numExchange;
            remainder = remainder % numExchange;
        }
        return numBottles + drunk(numBottles / numExchange,
                numExchange, remainder + numBottles % numExchange);
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        if (k == 0) {
            return scanForZereos(nums);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map = fillUp(map, i, nums[i]);
            if (timeToCheck(map, k)) {
                return true;
            }
            map.put(i, nums[i]);
        }
        return false;
    }

    public static HashMap<Integer, Integer>
    fillUp (HashMap<Integer, Integer> map, int high, int toAdd) {
        for (Integer num : map.keySet()) {
            if (num < high) {
                map.put(num, map.get(num) + toAdd);
            }
        }
        return map;
    }

    public static boolean timeToCheck(HashMap<Integer, Integer> map, int k) {
        for (Integer num : map.keySet()) {
            if (map.get(num) % k == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean scanForZereos(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i  + 1] && nums[i] == 0) {
                return true;
            }
        }
        return false;
    }

    public static int longestStrChain(String[] words) {
        int result = 0;
        for (int i = 0; i < words.length; i++) {
            result = Math.max(result, checkPredecessor(words, words[i]));
        }
        return result + 1;
    }

    public static int checkPredecessor(String[] words, String word) {
        int result = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i] != word && checkIfWorks(word, words[i])) {
                result = Math.max(result, 1 + checkPredecessor(words, words[i]));
            }
        }
        return result;
    }

    public static boolean checkIfWorks(String original, String toCheck) {
        while (!original.equals("")) {
            String currChar = original.substring(0, 1);
            if(!toCheck.contains(currChar)) {
                return false;
            }
            toCheck = toCheck.replaceFirst(currChar, "");
            original = original.substring(1);
        }
        return original.length() == 0 && toCheck.length() == 1;
    }

    public static String reverseString(String s) {
        return s.length() == 0 ? "" : reverseString(s.substring(1)) + s.substring(0, 1);
    }

    public static boolean isPalindrome(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || Character.isLetter(s.charAt(i))) {
                str.append(s.substring(i, i + 1).toLowerCase());
            }
        }
        char[] strArr = new String(str).toCharArray();
        for (int i = 0; i < strArr.length / 2; i++) {
            int lowIndex = i;
            int highIndex = strArr.length - 1 - i;
            if (strArr[lowIndex] != strArr[highIndex]) {
                return false;
            }
        }
        return true;
    }

     static boolean[] rectangleBoxes(int[][] operations) {
        int length = 0;
        for (int i = 0; i < operations.length; i++) {
            length = operations[i][0] == 1 ? length + 1 : length;
        }
        if (length == 0) {
            return new boolean[]{};
        }
        int index = 0;
        boolean[] sol = new boolean[length];
        Queue<ArrayList<Integer>> set = new LinkedList<>();
        for (int i = 0; i < operations.length; i++) {
            if (operations[i][0] == 0) {
                set.add(new ArrayList<>(Arrays.asList(operations[i][1], operations[i][2])));
            } else {
                sol[index] = rectangleBoxesFit(operations[i][1], operations[i][2], set);
                index++;
            }
        }
        return sol;
    }

    static boolean rectangleBoxesFit(int widthToFit, int lengthToFit, Queue<ArrayList<Integer>> fringe) {
        Queue<ArrayList<Integer>> fringe2 = new LinkedList<>(fringe);
        while (!fringe2.isEmpty()) {
            ArrayList<Integer> toCheck = fringe2.poll();
            if (toCheck.get(0) > widthToFit && toCheck.get(0) > lengthToFit) {
                return false;
            }
            if (toCheck.get(1) > widthToFit && toCheck.get(1) > lengthToFit) {
                return false;
            }
        }
        return true;
    }


     static int[] deleteMinimalPeaks(int[] numbers) {
        int[] sol = new int[numbers.length];
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int num : numbers) {
            arr.add(num);
        }
        for (int i = 0; i < sol.length; i++) {
            sol[i] = findMinPeak(arr);
        }
        return sol;
    }

    static int findMinPeak(ArrayList<Integer> arr) {
        if (arr.size() == 1) {
            int toReturn = arr.get(0);
            arr.remove((Integer) toReturn);
            return toReturn;
        }
        int minPeak = Integer.MAX_VALUE;
        boolean leftIsSmaller = true;
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i + 1) && leftIsSmaller) {
                minPeak = Math.min(minPeak, arr.get(i));
                leftIsSmaller = false;
            } else {
                leftIsSmaller = true;
            }
        }
        minPeak = leftIsSmaller && arr.get(arr.size() - 1) > arr.get(arr.size() - 2)
                ? arr.get(arr.size() -1) : minPeak;
        arr.remove((Integer) minPeak);
        return minPeak;
    }

    public static void printInt(int num) {
        int power = 0;
        while (num > 0) {
            System.out.println((int)((num % 10) * Math.pow(10, power++)));
            num /= 10;
        }
    }

    static List<String> checkIllumination(int N, List<List<Integer>> lamps, List<List<Integer>> queries) {
        List<String> sol = new ArrayList<>();
        int[][] grid = new int[N][N];
        for (List<Integer> query : queries) {
            initGrid(grid, lamps);
            if (illuminated(grid, query.get(0) - 1, query.get(1) - 1)) {
                sol.add("LIGHT");
            } else {
                sol.add("DARK");
            }
        }
        return sol;
    }

    static boolean illuminated(int[][] grid, int i , int j) {
        int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {-1,-1}};
        grid[i][j] = 0;
        for (int[] dir : dirs) {
            int dirX = dir[0] + i;
            int dirY = dir[1] + j;
            if (dirX >= 0 && dirX < grid.length
                    && dirY >= 0 && dirY < grid[dirX].length) {
                grid[dirX][dirY] = 0;
            }
        }
        for (int[] dir : dirs) {
            int nextX = i;
            int nextY = j;
            while (nextX >= 0 && nextX < grid.length
                    && nextY >= 0 && nextY < grid[0].length) {
                if (grid[nextX][nextY] == 1) {
                    return true;
                }
                nextX += dir[0];
                nextY += dir[1];
            }
        }
        return false;
    }

    static void initGrid(int[][] grid, List<List<Integer>> lamps) {
        for (List<Integer> pos : lamps) {
            grid[pos.get(0) - 1][pos.get(1) - 1] = 1;
        }
    }

    public static int minimumSwaps(String status) {
        // Write your code here
        StringBuilder choiceOne = new StringBuilder("S");
        StringBuilder choiceTwo = new StringBuilder("R");
        for (int i = 1; i < status.length(); i++) {
            if (choiceOne.charAt(i - 1) == 'S') {
                choiceOne.append('R');
            } else {
                choiceOne.append('S');
            }
            if (choiceTwo.charAt(i - 1) == 'S') {
                choiceTwo.append('R');
            } else {
                choiceTwo.append('S');
            }
        }
        int choiceOneDiff = 0, choiceTwoDiff = 0;
        for (int i = 0; i < status.length(); i++){
            choiceOneDiff = choiceOne.charAt(i) == status.charAt(i)
                    ? choiceOneDiff : choiceOneDiff + 1;
            choiceTwoDiff = choiceTwo.charAt(i) == status.charAt(i)
                    ? choiceTwoDiff : choiceTwoDiff + 1;
        }
        return Math.min(choiceOneDiff, choiceTwoDiff);
    }

    static int reverseInt(int num) {
        if (num == 0) return 0;
        int y = 0;
        int x = Math.abs(num);
        while (x > 0) {
            y = (y * 10) + x % 10;
            x = x / 10;
        }
        return num < 0 ? -y : y;
    }

    static int findPrime(int n) {
        /**
         * Remember to Look at This
         */
        return 0;
    }

    static public int amountOfSubArraySums(int[] arr, int low, int high) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int currSum  = 0, ans = 0;
        map.put(0, 1);
        for (int num : arr) {
            currSum += num;
            ans += amountValid(map, low, high, currSum);
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }
        return ans;
    }

    static public int amountValid(HashMap<Integer, Integer> map , int low, int high, int currSum) {
        int ans = 0;
        for (int i = low; i <= high; i++) {
            ans += map.getOrDefault(currSum - i, 0);
        }
        return ans;
    }

    public static int triangularNumber(int k) {
        if (k < 0) return -1;
        int[] dp = new int[k + 1];
        dp[0] = 0;
        for (int i = 1; i <= k; i++) {
            dp[i] = dp[i - 1] + i;
        }
        return dp[k];
    }

    public static void fizzBuzz () {
        StringBuilder sol = new StringBuilder();
        for (int i = 0; i <= 200; i++) {
            if (i % 3 == 0 && i == 5) {
                sol.append("FizzBuzz");
            } else if (i % 5 == 0) {
                sol.append("buzz");
            } else {
                sol.append("fizz");
            }
            sol.append(" ");
        }
        System.out.println(new String(sol));
    }
    static String[] composeRanges(int[] nums) {
        List<String> arr = new ArrayList<>();
        String str1 = null, str2 = null;
        for (int i = 0; i < nums.length; i++) {
            if (str1 == null) {
                str1 = Integer.toString(nums[i]);
            }  else {
                if (str2 == null) {
                    if (nums[i] - 1 > Integer.parseInt(str1)) {
                        arr.add(new String(str1));
                        str1 = Integer.toString(nums[i]);
                    } else {
                        str2 = Integer.toString(nums[i]);
                    }
                } else {
                    if (nums[i] -1 > Integer.parseInt(str2)) {
                        arr.add(new String(str1 + "->" + str2));
                        str1 = Integer.toString(nums[i]);
                        str2 = null;
                    } else {
                        str2 = Integer.toString(nums[i]);
                    }
                }
            }
        }
        if (str1 != null && str2 != null) {
            arr.add(new String(str1 + "->" + str2));
        } else if (str1 != null) {
            arr.add(new String(str1));
        }
        return arr.toArray(new String[arr.size()]);
    }
   static int minDiffOfArrays(int[] a, int[] b) {
        int difference = findDifference(a, b);
        int ans = difference, biggestDiff = Integer.MIN_VALUE, bIndex = -1;
        for (int i = 0; i < a.length; i++) {
            if (Math.abs(a[i] - b[i]) > biggestDiff) {
                biggestDiff =  Math.abs(a[i] - b[i]);
                bIndex = i;
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (Math.abs(a[i] - b[bIndex]) < biggestDiff) {
                ans = ans + (Math.abs(a[i] - b[bIndex]) - biggestDiff);
                biggestDiff = Math.abs(a[i] - b[bIndex]);
            }
        }
        return ans;
    }

    static int findDifference(int[] a, int[] b) {
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            ans += Math.abs(a[i] - b[i]);
        }
        return ans;
    }
    static String[] justifyNewspaperText(String[][] lines, String[] aligns, int width) {
        List<String> ans = new ArrayList<>();
        ans.add(addStars(width));
        for (int i = 0; i < lines.length; i++) {
            addPragraph(lines[i], aligns[i], width, ans);
        }
        ans.add(addStars(width));
        return ans.toArray(new String[ans.size()]);
    }

    static String addStars(int width) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < width; i++) {
            str.append('*');
        }
        return new String(str);
    }

    static void addPragraph(String[] lines, String align, int width, List<String> ans) {
        int amountOfLetters = 0;
        StringBuilder str = new StringBuilder();
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                amountOfLetters++;
                str.append(line.charAt(i));
                if (amountOfLetters > width) {
                    ans.add(new String(str));
                    amountOfLetters = 0;
                    str = new StringBuilder();
                }
            }
        }
        for (int i =amountOfLetters; i < width; i++) {
            if (align.equals("LEFT")) {
                str.append('*');
            } else {
                str.insert(0,  '*');
            }
        }
        ans.add(new String(str));
    }

    static public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            str.append(s.charAt(i));
            if (checkStringBuilder(str, s)) {
                return true;
            }
        }
        return false;
    }

    static public boolean checkStringBuilder(StringBuilder str, String s) {
        String strSoFar = str.toString();
        strSoFar = strSoFar.repeat((s.length() / strSoFar.length()));
        return strSoFar.equals(s);
    }

    static int[] mutateTheArray(int n, int[] a) {
        int[] b = new int[n];
        for (int i = 0; i < b.length; i++) {
            b[i] = process(a, i);
        }
        return b;
    }

    static boolean alternatingSort(int[] a) {
        int index = 0;
        int[] b = new int[a.length];
        for (int i = 0; index < b.length; i++) {
            b[index] = a[i];
            index += 2;
        }
        index = 1;
        for (int i = a.length - 1; index < b.length; i--) {
            b[index] = a[i];
            index += 2;
        }
        return asscending(b);
    }

    static boolean asscending(int[] b) {
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] >= b[i + 1]) {
                return false;
            }
        }
        return true;
    }

    static int process(int[] a, int pos) {
        int left = pos == 0 ? 0 : a[pos - 1], right = pos == a.length - 1 ? 0 : a[pos + 1];
        return right + left + a[pos];
    }

    static long hashMap(String[] queryType, int[][] query) {
        long ans = 0;
        Hash myHash = new Hash();
        for (int i = 0; i < queryType.length; i++) {
            if (queryType[i].equals("insert")) {
                myHash.insert(query[i][0], query[i][1]);
            } else if (queryType[i].equals("get")) {
                ans += myHash.get(query[i][0]);
            } else if (queryType[i].equals("addToKey")) {
                myHash.addToKey(query[i][0]);
            } else {
                myHash.addToValue(query[i][0]);
            }
        }
        return ans;
    }

    static public String removeKdigits(String num, int k) {
        if (num == null || num.equals("") || k == 0) {
            return num.equals("") ? "0" : num;
        }
        int newK = k;
        char largestVal = findLargest(num);
        StringBuilder newNum = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            if (newNum.length() != 0 || num.charAt(i) != '0') {
                if (k <= 0) {
                    newNum.append(num.charAt(i));
                } else {
                    if (newK == k && num.charAt(i) == largestVal) {
                        newK--;
                    } else {
                        newNum.append(num.charAt(i));
                    }
                }
            }
        }
        return removeKdigits(newNum.toString(), newK);
    }


    static public char findLargest(String num) {
        char ch = num.charAt(0);
        for (int i = 1; i < num.length(); i++) {
            if (num.charAt(i) >= ch) {
                ch = num.charAt(i);
            } else {
                break;
            }
        }
        return ch;
    }

    private static class Hash {
        HashMap<Integer, Integer> map;
        private Hash() {
            map = new HashMap<>();
        }

        private void insert(Integer x, Integer y) {
            map.put(x, y);
        }

        private Integer get (Integer x) {
            return map.get(x);
        }

        private void addToKey(Integer x) {
            HashMap<Integer, Integer> toReplace = new HashMap<>();
            for (Integer key : map.keySet()) {
                toReplace.put(key + x, map.get(key));
            }
            map = toReplace;
        }

        private void addToValue (Integer y) {
            for (Integer key : map.keySet()) {
                map.put(key, map.get(key) + y);
            }
        }
    }


    static int[] findLongestSubarrayBySum(int s, int[] arr) {
        int[] ans = new int[2];
        ans[0] = -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int currSum = 0;
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            currSum += arr[i];
            if (map.containsKey(currSum - s)) {
                if (ans[0] == -1 || ans[1] - ans[0] < i - map.get(currSum - s)) {
                    ans[0] = map.get(currSum - s) + 2;
                    ans[1] = i + 1;
                }
            }
            if (!map.containsKey(currSum)) {
                map.put(currSum, i);
            }
        }
        return ans[0] == -1 ? new int[]{-1} : ans;
    }

    static int[][] meanGroupsOld(int[][] a) {
        HashMap<Double, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            double mean = meanOfRow(a[i]);
            List<Integer> indices = map.getOrDefault(mean, new ArrayList<>());
            indices.add(i);
            Collections.sort(indices);
            map.put(mean, indices);
        }
        int[][] sol = new int[map.size()][];
        int index = 0;
        for (Double mean : map.keySet()) {
            sol[index] = new int[map.get(mean).size()];
            fillIndex(sol[index], map.get(mean));
            index++;
        }
        Arrays.sort(sol);
        return sol;
    }

    static double meanOfRow(int[] row) {
        double total = 0;
        for (int num : row) {
            total += num;
        }
        return total / row.length;
    }

    static void fillIndex(int[] sol, List<Integer> indices) {
        for (int i = 0; i < sol.length; i++) {
            sol[i] = indices.get(i);
        }
    }


    static long concatenationsSum(int[] a) {
        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                sum += Long.parseLong(Integer.toString(a[i]) + Integer.toString(a[j]));
            }
        }
        return sum;
    }



    static String minSubstringWithAllChars(String s, String t) {
        String ans = "";
        int left = 0, size = Integer.MAX_VALUE, count = 0;
        char[] letters = new char[128];
        for (char ch : t.toCharArray()) {
            letters[ch]++;
        }
        for (int right = 0; right < s.length(); right++) {
            if (letters[s.charAt(right)]-- > 0) {
                count++;
            }
            while (count == t.length()) {
                if (right - left + 1 < size) {
                    size = right - left + 1;
                    ans = s.substring(left, right + 1);
                }
                if (letters[s.charAt(left)]++ >= 0) {
                    count--;
                }
                left++;
            }
        }
        return ans;
    }

    static private class ListNode<T> {
        ListNode(T x) {
            value = x;
        }
        T value;
        ListNode<T> next;
    }

    static boolean isListPalindrome(ListNode<Integer> l) {
        if (l == null) return true;
        ArrayList<Integer> arr = new ArrayList<>();
        while (l != null) {
            arr.add(l.value);
            l = l.next;
        }
        for (int i = 0; i < arr.size() / 2; i++) {
            if (!arr.get(i).equals(arr.get(arr.size() - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    public static void runProposeRejectAlgorithm(proposeRejectUsers setup) {
        List<proposeRejectUsers.Job> jobs = setup.getJobs();
        List<proposeRejectUsers.Candidate> candidates = setup.getCandidates();
        while (!setup.matchingComplete()) {
            for (proposeRejectUsers.Job job : jobs) {
                proposeRejectUsers.Candidate nextIdeal = job.preference.get(job.pos);
                Integer bestOffer = candidates.get(candidates.indexOf(nextIdeal)).bestOffer;
                if (!job.hasMatching && (bestOffer == null || bestOffer > nextIdeal.rankOffer(job))) {
                    if (nextIdeal.bestOffer != null) {
                        candidates.get(candidates.indexOf(nextIdeal)).preference.get(bestOffer).setHasMatching();
                    }
                    nextIdeal.setBestOffer(job);
                    job.setHasMatching();
                }
                job.incrementPos();
            }
        }
        printPairing(setup);
    }

    public static void printPairing(proposeRejectUsers setup) {
        List<proposeRejectUsers.Candidate> candidates = setup.getCandidates();
        for (proposeRejectUsers.Candidate candidate : candidates) {
            System.out.print("Job " + candidate.preference.get(candidate.bestOffer).name
                    + " is paired with " + "Candidate " + candidate.name);
            System.out.println();
        }
    }

    static int[][] meanGroups(int[][] a) {
        if (a == null || a.length == 0) {
            return new int[0][0];
        }
        HashMap<Double, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            List<Integer> soFar = map.getOrDefault(mean(a, i), new ArrayList<>());
            soFar.add(i);
            map.put(mean(a, i), soFar);
        }
        int[][] sol = new int[map.size()][];
        int index = 0;
        for (Double key : map.keySet()) {
            sol[index] = new int[map.get(key).size()];
            putValues(sol, index, map.get(key));
            index++;
        }
        Arrays.sort(sol, Comparator.comparingInt(o -> o[0]));
        return sol;
    }

    static double mean(int[][] a, int pos) {
        double sum = 0;
        for (int i = 0; i < a[pos].length; i++) {
            sum += a[pos][i];
        }
        return sum != 0 ? (sum / a[pos].length) : sum;
    }

    static void putValues(int[][] sol, int index, List<Integer> indices) {
        for (int i = 0; i < indices.size(); i++) {
            sol[index][i] = indices.get(i);
        }
    }


    static String capitailizeWord(String str) {
       if (str == null || str.length() == 0) return str;

       StringBuilder sb = new StringBuilder();
       char ch = ' ';
       for (int i = 0; i < str.length(); i++) {
           if (ch == ' ') {
               sb.append(Character.toUpperCase(str.charAt(i)));
           } else {
               sb.append(str.charAt(i));
           }
           ch = str.charAt(i);
       }
       return sb.toString().trim();
    }

    public static int performAct(String num, String operation) {
        int num1 = 0, index = 0;
        while (index < operation.length() && Character.isLetter(operation.charAt(index))) {
            num1 = num1 * 10 + Integer.parseInt(String.valueOf(num.charAt(index++)));
        }
        return index < operation.length() ? performOperation(num, operation, num1, index) : num1;
    }

    public static int performOperation(String num, String letters, int numSoFar, int index) {
        int num2 = 0;
        char operation = letters.charAt(index);
        while (index < num.length()) {
            num2  = num2 * 10 + Integer.parseInt(String.valueOf(num.charAt(index++)));
        }
        return operation == '+' ? numSoFar + num2 : numSoFar - num2;
    }



    public static String compressWord(String word, int k) {
        StringBuilder sol = new StringBuilder();
        int index = 0;
        boolean doneCompressing = false;
        while (index < word.length()) {
            int numConsec = 0;
            char curr = word.charAt(index);
            StringBuilder soFar = new StringBuilder();
            while (index < word.length() && curr == word.charAt(index) && numConsec < k) {
                soFar.append(word.charAt(index++));
                numConsec++;
            }
            if (soFar.length() > 0 && soFar.length() < k) {
                sol.append(soFar.toString());
            }
            doneCompressing = soFar.length() >= k ? true : doneCompressing;
        }
        return doneCompressing ? compressWord(sol.toString(), k) : sol.toString();
    }

    public static int deleteProducts(List<Integer> ids, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue(Comparator.comparingInt(map::get));
        for (Integer id : ids) {
            map.put(id, map.getOrDefault(id, 0) + 1);
        }
        for (Integer id : map.keySet()) {
            heap.add(id);
        }
        while (m > 0 && !heap.isEmpty()) {
            m = m >= map.get(heap.peek()) ? m - map.get(heap.poll()) : 0;
        }
        return heap.size();
    }

    public static long getMaxUnits(List<Long> boxes, List<Long> unitsPerBox, long truckSize) {
        long ans = 0;
        HashMap<Long, Long> map = new HashMap<>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>((n1, n2) -> Math.toIntExact(n2 - n1));
        for (int i = 0; i < boxes.size(); i++) {
            map.put(unitsPerBox.get(i), boxes.get(i));
        }
        for (Long product : map.keySet()) {
            heap.add(product);
        }
        while (truckSize > 0 && !heap.isEmpty()) {
            ans += heap.peek() * Math.min(truckSize, map.get(heap.peek()));
            truckSize = truckSize -  Math.min(truckSize, map.get(heap.poll()));
        }
        return ans;
    }

    public static int computeGCD(Integer[] nums) {
        if (nums[0] == 0) {
            return nums[1];
        }
        nums[1] = nums[1] % nums[0];
        Arrays.sort(nums);
        return computeGCD(nums);
    }


    public static Integer[] parseInput(String line) {
        StringBuilder str = new StringBuilder();
        int index = 0;
        Integer[] nums = new Integer[2];
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ',') {
                nums[index++] = Integer.parseInt(str.toString());
                str = new StringBuilder();
            } else {
                str.append(line.charAt(i));
            }
        }
        if (str.length() > 0) {
            nums[index++] = Integer.parseInt(str.toString());
        }
        Arrays.sort(nums);
        return nums;
    }


    public static void main(String[] args) {
        System.out.println(computeGCD(parseInput("30,0")));
    }
}
