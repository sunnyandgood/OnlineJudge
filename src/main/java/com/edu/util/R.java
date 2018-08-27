package com.edu.util;

import java.util.HashMap;

public class R extends HashMap<String,Object> {
    public static R ok(){
        R r = new R();
        r.put("code","200");
        r.put("message","success");
        return r;
    }

    public static R ok(String message){
        R r = new R();
        r.put("code","200");
        r.put("message",message);
        return r;
    }

    public static R error(){
        R r = new R();
        r.put("code","500");
        r.put("message","error");
        return r;
    }

    public static R error(String message){
        R r = new R();
        r.put("code","500");
        r.put("message",message);
        return r;
    }

    public R put(String key, Object value) {
        super.put(key,value);
        return this;
    }
}
