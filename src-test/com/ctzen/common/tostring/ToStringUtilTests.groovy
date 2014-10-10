package com.ctzen.common.tostring

import groovy.transform.CompileStatic

import org.apache.commons.lang3.builder.ToStringStyle;
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

/**
 * @author cchang
 */
@CompileStatic
@Test
class ToStringUtilTests {

    @DataProvider(name = 'objectToStringData')
    private Object[][] objectToStringData() {
        [
            [ null, ToStringUtil.NULL_STRING ],
            [ 'foo', 'foo' ],
        ] as Object[][]
    }

    @Test(dataProvider = 'objectToStringData')
    void objectToString(final Object value, final String expected) {
        assert expected == ToStringUtil.toString(value)
    }

    @DataProvider(name = 'collectionToStringData')
    private Object[][] collectionToStringData() {
        [
            [ null, ToStringUtil.NULL_STRING, null ],
            [ [], '[]', null ],
            [ [ 1 ], '[1]', '1' ],
            [ [ 1, 2, 3 ], '[1,2,3]', null ],
            [ [ 1, null, 3 ], '[1,<null>,3]', null ],
        ] as Object[][]
    }

    @Test(dataProvider = 'collectionToStringData')
    void collectionToString(final Collection<?> value, final String expected, final String expectedUnwrapped) {
        assert expected == ToStringUtil.toString(value)
        assert expected == ToStringUtil.toString(value, false)
        if (expectedUnwrapped == null) {
            assert expected == ToStringUtil.toString(value, true)
        }
        else {
            assert expectedUnwrapped == ToStringUtil.toString(value, true)
        }
    }

    @Test(dataProvider = 'collectionToStringData')
    void arrayToString(final Collection<?> value, final String expected, final String expectedUnwrapped) {
        final Object[] array = value == null ? null : value.toArray()
        assert expected == ToStringUtil.toString(array)
        assert expected == ToStringUtil.toString(array, false)
        if (expectedUnwrapped == null) {
            assert expected == ToStringUtil.toString(array, true)
        }
        else {
            assert expectedUnwrapped == ToStringUtil.toString(array, true)
        }
    }

    @Test(dataProvider = 'collectionToStringData')
    void enumerationToString(final Collection<?> value, final String expected, final String expectedUnwrapped) {
        assert expected == ToStringUtil.toString(value == null ? null : Collections.enumeration(value))
        assert expected == ToStringUtil.toString(value == null ? null : Collections.enumeration(value), false)
        if (expectedUnwrapped == null) {
            assert expected == ToStringUtil.toString(value == null ? null : Collections.enumeration(value), true)
        }
        else {
            assert expectedUnwrapped == ToStringUtil.toString(value == null ? null : Collections.enumeration(value), true)
        }
    }

    @DataProvider(name = 'mapToStringData')
    private Object[][] mapToStringData() {
        [
            [ null, ToStringUtil.NULL_STRING ],
            [ [:], '[]' ],
            [ [foo:'bar'], '[foo=bar]' ],
            [ [foo:'bar', baz:'qux'], '[foo=bar,baz=qux]' ],
            [ [foo:'bar', baz:null], '[foo=bar,baz=<null>]' ],
        ] as Object[][]
    }

    @Test(dataProvider = 'mapToStringData')
    void mapToString(final Map<?,?> value, final String expected) {
        String foo = ToStringUtil.toString(value, ToStringStyle.SHORT_PREFIX_STYLE)
        assert ToStringUtil.toString(value).endsWith(expected)
        assert ToStringUtil.toString(value, ToStringStyle.SHORT_PREFIX_STYLE).endsWith(expected)
    }

}
