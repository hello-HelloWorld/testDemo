package mapReduce.wordCount;

/*
 * @author: sunxiaoxiong
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/*
 * 需求： 统计过程中对每一个 maptask 的输出进行局部汇总，以减小网络传输量即采用Combiner 功能。
 * */
public class WordcountCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //1.汇总
        int count = 0;
        for (IntWritable value : values) {
            count += value.get();
        }
        //2,写出
        context.write(key, new IntWritable(count));
    }
}
