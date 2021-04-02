package com.demo.util.common.log;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author dp
 * @date 2021/3/19 3:40 下午
 */
@Data
public class LogInfo {

    private List<LogPair> pairList;

    @Override
    public String toString() {
        StringBuilder logStr = new StringBuilder();
        if (CollectionUtils.isEmpty(pairList)) {
            return logStr.toString();
        }
        for (int i = 0; i < pairList.size(); i++) {
            logStr.append(pairList.get(i).toString());
            if (i < pairList.size() - 1) {
                logStr.append("||");
            }
        }
        return logStr.toString();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class LogPair {

        private String key;

        private Object value;

        @Override
        public String toString() {
            return key + "=" + value.toString();
        }
    }

    public LogInfo append(String pairKey, Object pairValue) {
        if (CollectionUtils.isEmpty(pairList)) {
            pairList = Lists.newArrayList();
        }
        pairList.add(new LogPair(pairKey, pairValue));
        return this;
    }
}

