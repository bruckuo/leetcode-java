package com.neu.x.leetcode.redisLock;

import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: guojiang.xiong
 * @date: 2019-10-12
 * @time: 11:06
 */
@Data
public class StoreKey implements Serializable {
    private String category;
    private Object[] params;

    public StoreKey(String category, Object... params) {
        this.category = category;
        this.params = params;
    }

    public static StoreKey valueOf(String category, Object... params) {
        return new StoreKey(category, params);
    }

    public List<Object> getParamsAsList() {
        return this.params == null ? Collections.emptyList() : Arrays.asList(this.params);
    }

    @Override
    public String toString() {
        return (new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)).append(this.category).append(this.params).toString();
    }

    @Override
    public int hashCode() {
        return (new HashCodeBuilder(17, 37)).append(this.category).append(this.params).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StoreKey) {
            StoreKey sk = (StoreKey) obj;
            return (new EqualsBuilder()).append(this.category, sk.category).append(this.params, sk.params).isEquals();
        } else {
            return false;
        }
    }
}