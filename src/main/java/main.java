import com.tw.CommandReader;
import com.tw.Library;
import com.tw.Student;

import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws Exception {
        Library library = new Library(new CommandReader(), new ArrayList<Student>());
        library.initLibrary();
    }
}
