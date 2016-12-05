/**
 * Created by zeno on 07.09.2016.
 */
public interface Instruction {
    boolean solved();
    int eval();
    Iterable<String> operands();
}
