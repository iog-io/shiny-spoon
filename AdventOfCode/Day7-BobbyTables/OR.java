import com.google.common.collect.Sets;

/**
 * Created by zeno on 07.09.2016.
 */
public class OR implements Instruction {

    private String lVar, rVar;

    public OR(String a, String b) {
        lVar = a;
        rVar = b;
    }

    @Override
    public boolean solved() {
        return LookNSay.Values.containsKey(lVar) && LookNSay.Values.containsKey(rVar);
    }

    @Override
    public int eval() {
        return LookNSay.Values.get(lVar) | LookNSay.Values.get(rVar);
    }

    @Override
    public Iterable<String> operands() {
        return Sets.newHashSet(lVar, rVar);
    }
}
