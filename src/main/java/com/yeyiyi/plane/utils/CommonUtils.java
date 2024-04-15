package com.yeyiyi.plane.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.yeyiyi.plane.entity.GameUser;

/**
 * @author YeYiYi
 * @date 2024/3/21 10:54
 * @description
 */
public class CommonUtils {
    private static final SymmetricCrypto CRY = SmUtil.sm4("yeyiyi-taikule!!".getBytes(CharsetUtil.CHARSET_UTF_8));
    private static final String _ = "_";

    public static String getUserId(String serverId,String gameName){
        long now = System.currentTimeMillis();
        return CRY.encryptHex(serverId+_+gameName+_+now,CharsetUtil.CHARSET_UTF_8);
    }


    public static GameUser getUserInfo(String userId){
        GameUser user = null;
        try {
            String decryptStr = CRY.decryptStr(userId);
            String[] str = decryptStr.split(_);
            user = new GameUser();
            user.setUserId(userId);
            user.setGameName(str[1]);
            user.setServerId(str[0]);
            user.setLoginTime(Long.parseLong(str[2]));
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

}
