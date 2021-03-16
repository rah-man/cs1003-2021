import java.util.*;

public class VowelCheck {
    static HashSet<Character> vowels = new HashSet<>(Arrays.asList(new Character[]{'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'}));
    public static void main(String[] args){
        String s = "Offer radiotherapy or MRI to young people with terminal cancer.";
        String vowelRemoved = removeVowel(s);
        System.out.println(vowelRemoved);
    }

    public static String removeVowel(String s){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            // need to convert the charater to String, as our method only accepts a String
            if(!isVowel(Character.toString(s.charAt(i)))){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();

        // an alternative using standard String (not StringBuilder)
        // for a long string, this process is expensive. better to use StringBuilder
        // String st = "";
        // for(int i = 0; i < s.length(); i++){
        //     if(!isVowel(Character.toString(s.charAt(i)))){
        //         st += s.charAt(i);
        //     }
        // }
        // return st;
    }

    /**
     * only works if s is a single character string
     */
    public static boolean isVowel(String s){
        // char[] vowels = {'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'};
        // for(char c: vowels){
        //     if(s.charAt(0) == c){
        //         return true;
        //     }
        // }

        // a better approach using set, as you are more familiar now
        if(vowels.contains(s.charAt(0))){
            return true;
        }
        return false;
    }
}
