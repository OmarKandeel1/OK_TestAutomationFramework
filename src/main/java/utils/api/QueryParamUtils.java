package utils.api;

import java.util.Map;

public class QueryParamUtils {
    public static Map<String, Object> pagination(int limit, int offset) {
        return Map.of(
                "limit", limit,
                "offset", offset
        );
    }
}
