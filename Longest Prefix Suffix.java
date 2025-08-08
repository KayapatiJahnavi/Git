class Solution {
    int getLPSLength(String s) {
        int n = s.length();
        int[] lps = new int[n];  // lps[i] = length of longest prefix which is also suffix for substring s[0..i]
        
        int len = 0;  // length of the previous longest prefix suffix
        int i = 1;
        
        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        // lps[n-1] contains the length of longest prefix which is also suffix for whole string
        return lps[n - 1];
    }
}
