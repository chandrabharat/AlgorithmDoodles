public class OnesAndZeroes {
        public int findMaxForm(String[] strs, int m, int n) {
            return helper(strs, m, n, 0, 0, 0);
        }

        public int helper(String[] strs, int m, int n,
                          int ans, int nums, int pos) {
            if ((m == 0 && n == 0)|| pos >= strs.length) {
                ans = Math.max(nums, ans);
                return ans;
            } else if (m < 0 || n < 0) {
                return ans;
            }
            String currString = strs[pos];
            int newM = m;
            int newN = n;
            for (int i = 0; i < currString.length(); i++) {
                newM = currString.charAt(i) == '0' ? newM-1:newM;
                newN = currString.charAt(i) == '1' ? newN-1:newN;
            }
            return Math.max(helper(strs, m, n, ans, nums, pos + 1),
                    helper(strs, newM, newN, ans, nums + 1, pos + 1));
        }
}
