package com.zng.library.loadmore;

import java.util.Collection;

/**
 * 集合工具类
 */
public class CollectionUtil{

    /**
     * @Description: 判断List集合是否为空
     * @param list
     * @return
     */
    public static boolean listIsNull(Collection<? extends Object> list){
        if(null == list || list.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * @Description: 判断List集合是否为空
     * @param list
     * @return
     */
    public static boolean listIsNotNull(Collection<? extends Object> list){
        if(null != list && list.size() > 0){
            return true;
        }
        return false;
    }

    /**
     * 清除集合
     */
    public static void clearList(Collection<? extends Object> list){
        if(list != null){
            list.clear();
            list = null;
        }
    }
}
