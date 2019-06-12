package com.bk.framework.extension.neo4j;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BK
 * @version V2.0
 * @description: Neo4j扩展工具类
 * @date 2019/4/30 9:57
 */
@Slf4j
public class Neo4jExtUtils {
    /**
     * 正则匹配CQL中的变量
     */
    private static final String RELATION_PATTERN = "(\\[((:[{([\\w]+)}|\\w+])?|(:[\\w]+[\\|:[\\w]]*)?)?([*[{\\d|\\w+}?]?|[.. ]?|[{\\d|\\w+}]]?|(\\{([\\w]+)}))+])";

    /**
     * 正则匹配 换行、制表符
     */
    private static Pattern CQL_PATTERN = Pattern.compile("\\s{2,}|\t|\r|\n");

    /**
     * 处理Query注解上sql的动态关系和层级
     *
     * @param cql   cql
     * @param param 参数map
     * @return
     */
    public static String processCqlRelation(String cql, Map<String, Object> param) {
        if (StringUtils.isBlank(cql)) {
            return "";
        }
        if (param == null) {
            param = Maps.newHashMap();
        }
        cql = format(cql);
        Matcher matcher = Pattern.compile(RELATION_PATTERN).matcher(cql);
        Map<String, String> matchedMap = Maps.newHashMap();
        while (matcher.find()) {
            String matcherStr = matcher.group(0);
            if (log.isDebugEnabled()) {
                log.debug(">>>>>find " + matcherStr);
            }
            String reg = "(\\w+)";
            Pattern p = Pattern.compile(reg);
            Matcher ma = p.matcher(matcherStr);
            String value = matcherStr.replace(" ", "");
            while (ma.find()) {
                String maStr = ma.group(0);
                value = StringUtils.replace(value, "{" + maStr + "}", String.valueOf(param.get(maStr)));
                matchedMap.put(matcherStr, value);
            }
        }
        for (String s : matchedMap.keySet()) {
            cql = StringUtils.replace(cql, s, matchedMap.get(s));
        }
        log.info("cql:" + cql + ", parameter : " + JSONObject.toJSONString(param));
        return cql;
    }

    /**
     * 格式化SQL，替换“换行”、“制表符”为空格
     *
     * @param cql
     * @return
     */
    private static String format(String cql) {
        String result = "";
        if (null != cql && !cql.isEmpty()) {
            Matcher m = CQL_PATTERN.matcher(cql);
            result = m.replaceAll(" ");
        }
        return result;
    }

    public static void main(String[] args) {
        String cql = "match (l:LOCATION)-[:{1}] -[:hasLoc|:nearby|:lower|:upper*{2}..{3}] -[:{1}*{2}..{3}]-[*..{3}]-[:{1}*{3}..]-[:{1}*{2}]->(rs:LOCATION) where ID(l)={id} return rs";
        Map<String, Object> param = Maps.newHashMap();
        param.put("0", "100");
        param.put("1", "hasLoc");
        param.put("2", "upper");
        param.put("3", "lower");
        param.put("id", "100");
        param.put("relationShip", "hasLoc");
        param.put("deviceId", "hasLoc");
        System.out.println(processCqlRelation(cql, param));
    }
}
