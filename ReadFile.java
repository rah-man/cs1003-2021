import java.io.*;
import java.util.*;

public class ReadFile{
    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("give us more!!");
        }
        try{
            FileReader fr = new FileReader(args[0]);
        }
        // try{
        //     FileReader fr = new FileReader("./newfolder/new 8.txt");
        //     BufferedReader br = new BufferedReader(fr);

        //     ArrayList<String> lines = new ArrayList<String>();
        //     String line = br.readLine();
        //     while(line != null){
        //         lines.add(line);
        //         line = br.readLine();
        //     }

        //     for(String s: lines){
        //         System.out.println(s);
        //     }

        // }catch(Exception e){
        //     System.err.println(e);

        // }

        // String text = "Offer radiotherapy or MRI to young people with terminal cancer. Offer radiotherapy and MRI to young people with terminal cancer. Offer radiotherapy and MRI to people with terminal cancer. If the person is aged under 80 years with stage 1 hypertension and has renal disease, offer antihypertensive drug treatment.";
        // String[] words = text.split(" ");


        // System.out.println(text);
        // System.out.println("There are " + words.length + " words in the text");

        String sb = "Offer radiotherapy or MRI to young people with terminal cancer.";
        String vowelRemoved = removeVowel(sb);
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

        // a different approach using standard String (not StringBuilder)
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
        char[] vowels = {'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'};
        for(char c: vowels){
            if(s.charAt(0) == c){
                return true;
            }
        }
        return false;
    }
}
