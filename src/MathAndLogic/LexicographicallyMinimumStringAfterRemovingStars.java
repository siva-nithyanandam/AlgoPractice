package MathAndLogic;
/**
 * @time 6/2/24-9:01 AM
 * @author sivaprakashnithyanandam
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LexicographicallyMinimumStringAfterRemovingStars {

    public static void main(String[] args) {
        LexicographicallyMinimumStringAfterRemovingStars o = new LexicographicallyMinimumStringAfterRemovingStars();
        System.out.println(o.clearStars("de*"));
    }

    public String clearStars(String s) {
        List<List<Integer>> poss = new ArrayList<>();
        for(int i = 0;i < 26;i++){
            poss.add(new ArrayList<>());
        }
        int n = s.length();
        boolean[] deled = new boolean[n];
        for(int i = 0;i < n;i++){
            char c = s.charAt(i);
            if (c == '*'){
                deled[i] = true;
                for(int j = 0;j < 26;j++){
                    if(poss.get(j).size() > 0){
                        int t = poss.get(j).get(poss.get(j).size()-1);
                        deled[t] = true;
                        poss.get(j).remove(poss.get(j).size()-1);
                        break;
                    }
                }
            }else{
                poss.get(c-'a').add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < n;i++){
            if(!deled[i]){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
