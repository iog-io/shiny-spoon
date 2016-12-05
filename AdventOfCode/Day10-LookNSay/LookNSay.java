import java.util.Stack;

/**
 * Created by zeno on 08.09.2016.
 */
public class LookNSay {
    public static void main(String[] args) {
        String input = "1321131112";
        int times = 0;
        while (times++ < 50) {
            Stack<Character> stack = new Stack<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < input.length(); ++i) {
                if (!stack.empty() && input.charAt(i) != stack.peek()) {
                    sb.append(stack.size()).append(stack.peek());
                    stack.removeAllElements();
                }
                stack.push(input.charAt(i));
            }
            // wrapping up
            input = sb.append(stack.size()).append(stack.peek()).toString();
        }
        System.out.println(input.length());
    }
}
