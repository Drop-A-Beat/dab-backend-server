package com.side.dab.helper;

public class QueryDslHelper {

    public static String transformToLikeString(String target, boolean isFront, boolean isBack){

        if(isFront){
            target = "%" + target;
        }

        if(isBack){
            target = target + "%";
        }

        return target;
    }
}
