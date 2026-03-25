import java.util.Scanner;

public class StrRemove {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        int n = str.length();
        char[] ch = new char[n];
        String result = "";
        String removed = "";

        for(int i=0;i<n;i++){
            ch[i] = str.charAt(i);
        }

        result += ch[0];

        for(int i=1;i<n;i++){
            if(ch[i] == ch[i-1]) {
                removed += ch[i];
            }else{
                result += ch[i];
            }
        }

        System.out.println(result);
        System.out.println(removed);
    }
}

//question: The program must accept string and remove consecutively repeated characters and then print the resulting string value the program must also print the removed characters in the second line.
