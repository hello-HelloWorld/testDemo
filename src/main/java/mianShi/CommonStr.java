package mianShi;

import java.util.ArrayList;
import java.util.Arrays;

public class CommonStr {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("flower", "flowd", "floaf"));
        String commonStr = getCom(list);
        System.out.println(commonStr);

        String[] strs = {"flower", "flowd", "floaf"};
        String com = getCom2(strs);
        System.out.println(com);
    }

    private static String getCom(ArrayList<String> list) {
        String str1 = list.get(0);
        for (int i =1;i< str1.length();  i++) {
            String substr = str1.substring(0, i);
            for (int j = 1; j < list.size(); j++) {
                String str2 = list.get(j);
                if (!str2.startsWith(substr)) {
                    return str1.substring(0, i-1);
                }
            }
        }
        return "";
    }

    public static String getCom2(String[] strs) {
        int len = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < len; i++) {
            char tmp = strs[0].charAt(i);//每次分别比对第一个单词的每一位
            for (int j = 1; j < count; j++) {
                if ((i == strs[j].length()) || (strs[j].charAt(i) != tmp)) {//若以后单词长度短于第一个单词长度或者相同位单词不同
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
