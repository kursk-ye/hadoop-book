import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

public class FileSystemDoubleCat {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri) , conf);
        FSDataInputStream input = null;
        try{
            input = fs.open(new Path(uri));
            IOUtils.copyBytes(input , System.out, 4096, false);
            input.seek(0);
            IOUtils.copyBytes(input , System.out, 4096 , false);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            IOUtils.closeStream(input);
        }
    }
}
