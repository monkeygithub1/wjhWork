package testing;

import java.util.Comparator;

class SpellComparator implements Comparator {
    SpellComparator() {
    }

    public int compare(Object o1, Object o2) {
        try {
            String s1 = new String(o1.toString().getBytes("GB2312"), "ISO-8859-1");
            String s2 = new String(o2.toString().getBytes("GB2312"), "ISO-8859-1");
            return s1.compareTo(s2);
        } catch (Exception var5) {
            var5.printStackTrace();
            return 0;
        }
    }
}