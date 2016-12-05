import java.util.Collections;

/**
 * Created by zeno on 07.09.2016.
 */
public class NOT implements Instruction {

    private String var;

    public NOT(String wire) {
        var = wire;
    }

    @Override
    public boolean solved() {
        return LookNSay.Values.containsKey(var);
    }

    @Override
    public int eval() {
        return ~LookNSay.Values.get(var);
    }

    @Override
    public Iterable<String> operands() {
        return Collections.singleton(var);
    }
}
