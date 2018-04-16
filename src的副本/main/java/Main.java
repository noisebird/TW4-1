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
        Map<String,Student> student1s=new HashMap<String,Student>();
        AquireReader aquireReader=new AquireReader();
        Library library1=new Library(student1s,aquireReader);
        library1.enter();
    }
}
