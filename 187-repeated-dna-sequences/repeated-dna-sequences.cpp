class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
          vector<string>ans;
         unordered_map<string,int>mpp;
         int n=s.length();
         if (n<10) return ans;
          for(int i=0;i<=n-10;i++){
            string sub=s.substr(i,10);
            mpp[sub]++;

          }
          for(auto it:mpp){
            if(it.second>1){
                ans.push_back(it.first);
            }
          }
          return ans;

    }
};