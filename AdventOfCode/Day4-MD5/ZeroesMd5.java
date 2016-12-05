import static org.apache.commons.codec.digest.DigestUtils.md5Hex;
import static java.lang.System.out;

public class ZeroesMd5 {
    public static void main(String[] args) {
        String str = "iwrupvqb346386";
        out.println(md5Hex(str.getBytes()));
    }
}