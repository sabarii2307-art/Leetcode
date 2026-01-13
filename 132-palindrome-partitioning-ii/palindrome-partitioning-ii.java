class Solution {
    class Range {
        int left;
        int right;

        public Range(int l, int r) {
            this.left = l;
            this.right = r;
        }

        @Override
        public int hashCode() {
            return left * 31 + right;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            else if (!(obj instanceof Range)) return false;

            Range temp = (Range)(obj);
            return this.left == temp.left && this.right == temp.right;
        }
    }

    public int minCut(String s) {
        if (s.length() <= 1) return 0;

        Set<Range> set = buildSet(s); // O(1) tells whether s[i:j] is itself a palindrome
        int[] dp = new int[s.length()];
        
        // base case
        dp[0] = 0;

        for (int i = 1; i < s.length(); i++) {
            // case 1: s[0:i] is itself a palindrome
            if (set.contains(new Range(0, i))) {
                dp[i] = 0;
                continue;
            }

            // case 2: s[0:i] can be cut into palindromes. At least one cut.
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j <= i - 1; j++) {
                if (set.contains(new Range(j + 1, i))) {
                    dp[i] = Math.min(
                        dp[i],
                        dp[j] + 1
                    );
                }
            }
        }

        return dp[s.length() - 1];
    }

    private Set<Range> buildSet(String s) {
        Set<Range> set = new HashSet<>();

        char[] str = s.toCharArray();

        // base case
        for (int i = 0; i < str.length; i++) {
            set.add(new Range(i, i));
        }

        // update
        for (int i = str.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < str.length; j++) {
                if (str[i] != str[j]) continue;

                // case 1: Range(i + 1, j - 1) is INVALID // i + 1 == j
                if (i + 1 == j) {
                    set.add(new Range(i, j));
                }

                // case 2: Range(i + 1, j - 1) is VALID
                else if (
                    set.contains(new Range(i + 1, j - 1))
                ) {
                    set.add(new Range(i, j));
                }
            }
        }
  
        return set;
    }
}