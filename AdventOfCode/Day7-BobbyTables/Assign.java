/**
 * Created by zeno on 07.09.2016.
 */
public class Assign implements Instruction {

    private int value;

    public Assign(int value) {
        this.value = value;
    }

    @Override
    public boolean solved() {
        return true;
    }

    @Override
    public int eval() {
        return value;
    }

    @Override
    public Iterable<String> operands() {
        return null;
    }
}
