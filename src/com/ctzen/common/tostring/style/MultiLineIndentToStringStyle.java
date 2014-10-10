package com.ctzen.common.tostring.style;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * {@link ToStringStyle} that supports multi-level indents.
 *
 * @author cchang
 */
public class MultiLineIndentToStringStyle extends ToStringStyle {

    private static final long serialVersionUID = 1L;

    /**
     * Use this to get pre-configured {@link MultiLineIndentToStringStyle}
     *
     * @param level indent level, minimum 1.
     * @return
     */
    public static MultiLineIndentToStringStyle get(final int level) {
        if (level <= INSTANCES.length) {
            return INSTANCES[level - 1];
        }
        return new MultiLineIndentToStringStyle(level);
    }

    public static final MultiLineIndentToStringStyle STYLE = new MultiLineIndentToStringStyle(1);
    public static final MultiLineIndentToStringStyle STYLE_2 = new MultiLineIndentToStringStyle(2);
    public static final MultiLineIndentToStringStyle STYLE_3 = new MultiLineIndentToStringStyle(3);
    public static final MultiLineIndentToStringStyle STYLE_4 = new MultiLineIndentToStringStyle(4);
    public static final MultiLineIndentToStringStyle STYLE_5 = new MultiLineIndentToStringStyle(5);
    public static final MultiLineIndentToStringStyle STYLE_6 = new MultiLineIndentToStringStyle(6);
    public static final MultiLineIndentToStringStyle STYLE_7 = new MultiLineIndentToStringStyle(7);
    public static final MultiLineIndentToStringStyle STYLE_8 = new MultiLineIndentToStringStyle(8);
    public static final MultiLineIndentToStringStyle STYLE_9 = new MultiLineIndentToStringStyle(9);

    private static final MultiLineIndentToStringStyle[] INSTANCES = {
        STYLE,
        STYLE_2,
        STYLE_3,
        STYLE_4,
        STYLE_5,
        STYLE_6,
        STYLE_7,
        STYLE_8,
        STYLE_9
    };

    public static final String DEFAULT_INDENT = "\t";

    /**
     * Default constructor.<br>
     * <br>
     * Indent by 1 {@value #DEFAULT_INDENT}
     */
    public MultiLineIndentToStringStyle() {
        this(1, DEFAULT_INDENT);
    }

    /**
     * Indent by 1 {@code indent}
     *
     * @param indent    indent string
     */
    public MultiLineIndentToStringStyle(final String indent) {
        this(1, indent);
    }

    /**
     * Indent by {@code level} {@value #DEFAULT_INDENT}
     *
     * @param level indent level, minimum 1.
     */
    public MultiLineIndentToStringStyle(final int level) {
        this(level, DEFAULT_INDENT);
    }

    /**
     * Indent by {@code level} {@code indent}
     *
     * @param level     indent level, minimum 1.
     * @param indent    indent string
     */
    public MultiLineIndentToStringStyle(final int level, final String indent) {
        this.level = level;
        this.indent = indent;
        setContentStart("[");
        setFieldSeparator(SystemUtils.LINE_SEPARATOR + StringUtils.repeat(indent, level));
        setFieldSeparatorAtStart(true);
        setContentEnd(SystemUtils.LINE_SEPARATOR + StringUtils.repeat(indent, level - 1) + "]");
    }

    private final int level;

    /**
     * @return  indent level
     */
    public int getLevel() {
        return level;
    }

    private final String indent;

    public String getIndent() {
        return indent;
    }

}
