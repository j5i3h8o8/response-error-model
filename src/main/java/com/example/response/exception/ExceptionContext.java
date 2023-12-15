package com.example.response.exception;

import java.util.HashMap;
import java.util.Map;

public class ExceptionContext {
    static public final ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);
}
