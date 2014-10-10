package com.ctzen.common.tostring.style

import groovy.transform.CompileStatic

import org.apache.commons.lang3.builder.ToStringBuilder
import org.testng.annotations.Test

/**
 * @author cchang
 */
@CompileStatic
@Test
class MultiLineIndentToStringStyleTests {

    void predefinedStyles() {
        assert MultiLineIndentToStringStyle.STYLE == MultiLineIndentToStringStyle.get(1)
        assert 1 == MultiLineIndentToStringStyle.STYLE.level
        assert MultiLineIndentToStringStyle.DEFAULT_INDENT == MultiLineIndentToStringStyle.STYLE.indent
        assert MultiLineIndentToStringStyle.STYLE_5 == MultiLineIndentToStringStyle.get(5)
        assert 5 == MultiLineIndentToStringStyle.STYLE_5.level
        assert MultiLineIndentToStringStyle.DEFAULT_INDENT == MultiLineIndentToStringStyle.STYLE_5.indent
        assert MultiLineIndentToStringStyle.STYLE_9 == MultiLineIndentToStringStyle.get(9)
        assert 9 == MultiLineIndentToStringStyle.STYLE_9.level
        assert MultiLineIndentToStringStyle.DEFAULT_INDENT == MultiLineIndentToStringStyle.STYLE_9.indent
    }

    void nonPredefinedStyle() {
        final MultiLineIndentToStringStyle level = MultiLineIndentToStringStyle.get(10)
        assert 10 == level.level
        assert MultiLineIndentToStringStyle.DEFAULT_INDENT == level.indent
    }

    void constructors() {
        final MultiLineIndentToStringStyle defa = new MultiLineIndentToStringStyle()
        assert 1 == defa.level
        assert MultiLineIndentToStringStyle.DEFAULT_INDENT == defa.indent
        final MultiLineIndentToStringStyle level = new MultiLineIndentToStringStyle(10)
        assert 10 == level.level
        assert MultiLineIndentToStringStyle.DEFAULT_INDENT == level.indent
        final MultiLineIndentToStringStyle indent = new MultiLineIndentToStringStyle('    ')
        assert 1 == indent.level
        assert '    ' == indent.indent
        final MultiLineIndentToStringStyle levelIndent = new MultiLineIndentToStringStyle(20, '  ')
        assert 20 == levelIndent.level
        assert '  ' == levelIndent.indent
    }

    void testStyle() {
        assert ToStringBuilder.reflectionToString(new TestObject(foo: 123, bar: 'hello'), MultiLineIndentToStringStyle.STYLE)
            .toString()
            .endsWith("[\n\tfoo=123\n\tbar=hello\n]")
    }

    void testStyle2() {
        assert ToStringBuilder.reflectionToString(new TestObject(foo: 123, bar: 'hello'), MultiLineIndentToStringStyle.STYLE_2)
            .toString()
            .endsWith("[\n\t\tfoo=123\n\t\tbar=hello\n\t]")
    }

}
