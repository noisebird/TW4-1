import com.tw.AquireReader;
import com.tw.Library;
import com.tw.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjie on 2018/4/9.
 */
public class Main {
    public static void main(String[] args) {
        Library library =new Library(new HashMap<String, Student>(),new AquireReader());
        library.enter();
    }
}
