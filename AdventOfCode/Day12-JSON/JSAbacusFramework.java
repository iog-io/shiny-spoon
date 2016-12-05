import edu.princeton.cs.algs4.In;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;

/**
 * Created by zeno on 20.09.2016.
 */
public class JSAbacusFramework {
    public static void main(String[] args) {
        In in = new In(args[0]);
        JSONObject json = new JSONObject(in.readAll());
        System.out.println(accumulateObject(json));
    }

    private static int accumulateObject(JSONObject obj) {
        int total = 0;
        for (String key : obj.keys()) {
            Object val = obj.get(key);
//            System.out.println(val.getClass().getSimpleName());
            if (val.getClass().getSimpleName().startsWith("String")) {
                if (((String)val).equalsIgnoreCase("red")) return 0;
            }
            else total += processObject(val);
        }
        return total;
    }

    private static int accumulateArray(JSONArray array) {
        int total = 0;
        for (Object elm : array) {
            total += processObject(elm);
        }
        return total;
    }

    private static int processObject(Object elm) {
        int total = 0;
        if (elm.getClass().getSimpleName().startsWith("JSONArray")) {
            total += accumulateArray((JSONArray)elm);
        }
        else if (elm.getClass().getSimpleName().startsWith("JSONObject")) {
            total += accumulateObject((JSONObject)elm);
        }
        else if (elm.getClass().getSimpleName().startsWith("Integer")) {
            total += ((Integer)elm);
        }
        return total;
    }
}
