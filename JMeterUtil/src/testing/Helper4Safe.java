package testing;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class Helper4Safe {
    public Helper4Safe() {
    }

    public static String signature(String tokenKey, Map<String, String> paramHash) {
        String returnSign = null;
        List<String> keyList = new ArrayList();
        Set<String> keySet = paramHash.keySet();
        keyList.addAll(keySet);
        Collections.sort(keyList, new SpellComparator());
        StringBuilder paramStrBufer = new StringBuilder();
        Iterator var7 = keyList.iterator();

        String tokenMD5;
        while(var7.hasNext()) {
            tokenMD5 = (String)var7.next();
            paramStrBufer.append("&").append(tokenMD5).append("=").append((String)paramHash.get(tokenMD5));
        }

        if (paramStrBufer.length() > 0) {
            paramStrBufer.deleteCharAt(0);
        }

        tokenMD5 = getMD5Str(paramStrBufer.toString() + tokenKey, 32);
        return tokenMD5;
    }

    public static String getMD5Str(String str, int length) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();

        for(int i = 0; i < byteArray.length; ++i) {
            if (Integer.toHexString(255 & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(255 & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(255 & byteArray[i]));
            }
        }

        if (length == 32) {
            return md5StrBuff.toString();
        } else if (length == 16) {
            return md5StrBuff.substring(8, 24).toString();
        } else {
            return str;
        }
    }
}