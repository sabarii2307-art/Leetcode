class Solution:
    def buildLPSLength(self, s):
        lps_length = [0] * len(s)
        length = 0
        j = 1
        while j < len(s):
            if s[j] == s[length]:
                length += 1
                lps_length[j] = length
                j += 1
            else:
                if length == 0:
                    lps_length[j] = 0
                    j += 1
                else:
                    length = lps_length[length - 1]
        return lps_length
    
    def shortestPalindrome(self, s):
        s_reverse = s[::-1]
        
        concat = s + "@" + s_reverse
        lps_length = self.buildLPSLength(concat)
        palindrome_length_at_start = lps_length[len(concat) - 1]
        
        to_add = s_reverse[:len(s) - palindrome_length_at_start]
        
        return to_add + s