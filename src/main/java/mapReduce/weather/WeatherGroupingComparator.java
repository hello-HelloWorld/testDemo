package mapReduce.weather;

/*
 * @author: sunxiaoxiong
 */

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class WeatherGroupingComparator extends WritableComparator {
    public WeatherGroupingComparator() {
        super(Weather.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        //年月正向排序
        Weather aweather = (Weather) a;
        Weather bweather = (Weather) b;

        //按年份正序排列
        int result = Integer.compare(aweather.getYear(), bweather.getYear());

        if (result == 0) {
            //如果同一年，按照月份正序排列
            result = Integer.compare(aweather.getMonth(), bweather.getMonth());
        }
        return result;
    }
}
