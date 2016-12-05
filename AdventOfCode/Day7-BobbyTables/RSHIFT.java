import java.util.Collections;

/**
 * Created by zeno on 07.09.2016.
 */
public class RSHIFT implements Instruction {

    private String lVar;
    private int pos;

    public RSHIFT(String a, String b) {
        lVar = a;
        pos = Integer.parseInt(b);
    }

    @Override
    public boolean solved() {
        return LookNSay.Values.containsKey(lVar);
    }

    @Override
    public int eval() {
        return LookNSay.Values.get(lVar) >>> pos;
    }

    @Override
    public Iterable<String> operands() {
        return Collections.singleton(lVar);
    }
}
