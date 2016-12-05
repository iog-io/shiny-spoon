import edu.princeton.cs.algs4.In;

/**
 * Created by zeno on 03.09.2016.
 */
public class Beginning {
    public static void main(String[] args) {
        In in = new In(args[0]);
        String str = in.readLine();
        int floor = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '(') floor++;
            else floor--;
            if (floor == -1) {
                System.out.println(i+1);    // Part 2: 1797
                break;
            }
        }
        long open = str.chars().filter(p->p=='(').count();
        System.out.println(open - (str.length() - open));   // Part 1: 280
    }
}
