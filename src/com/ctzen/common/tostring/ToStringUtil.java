package com.ctzen.common.tostring;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * toString Utilities.
 *
 * @author cchang
 */
public abstract class ToStringUtil {

    public static final String NULL_STRING = "<null>";

    private ToStringUtil() {
    }

    public static String toString(final Object o) {
        return o == null ? NULL_STRING : o.toString();
    }

    public static String toString(final Collection<?> c, final boolean unwrap1) {
        if (c == null) {
            return NULL_STRING;
        }
        if (unwrap1 && c.size() == 1) {
            return toString(c.iterator().next());
        }
        final List<String> strings = new LinkedList<>();
        for (final Object o: c) {
            strings.add(toString(o));
        }
        return "[" + StringUtils.join(strings, ",") + "]";
    }

    public static String toString(final Collection<?> c) {
        return toString(c, false);
    }

    public static String toString(final Enumeration<?> e, final boolean unwrap1) {
        return e == null ? NULL_STRING : toString(Collections.list(e), unwrap1);
    }

    public static String toString(final Enumeration<?> e) {
        return toString(e, false);
    }

    public static String toString(final Object[] a, final boolean unwrap1) {
        return a == null ? NULL_STRING : toString(Arrays.asList(a), unwrap1);
    }

    public static String toString(final Object[] a) {
        return toString(a, false);
    }

    public static String toString(final Map<?, ?> m, final ToStringStyle style) {
        if (m == null) {
            return NULL_STRING;
        }
        final ToStringBuilder tsb = new ToStringBuilder(m, style);
        for (Map.Entry<?, ?> e: m.entrySet()) {
            tsb.append(e.getKey().toString(), e.getValue());
        }
        return tsb.toString();
    }

    public static String toString(final Map<?, ?> m) {
        return toString(m, ToStringStyle.DEFAULT_STYLE);
    }

}
