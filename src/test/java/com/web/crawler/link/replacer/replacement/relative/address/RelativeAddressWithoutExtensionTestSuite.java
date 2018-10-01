package com.web.crawler.link.replacer.replacement.relative.address;

import com.web.crawler.model.Address;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
//TODO refactor this test to parameterized test
public class RelativeAddressWithoutExtensionTestSuite {
/**
    //Given
    private String testAddress1 = "https://www.testCase.org/";
    private String testAddress2 = "https://www.testCase.org/domains/reserved/";
    private String testCase1 = "about/";
    private String testCase2 = "/domains";
    private String testCase3 = "/domains/root";
    private String testCase4 = "/domains/root/db";
    private String testCase5 = "/domains/idn-tables";
    private String testCase6 = "/time-zones";
    private String testCase7 = "http://www.icann.org/";
    private String testCase8 = "/_css/2015.1/screen.css";
    private String testCase9 = "about";
    private String testCase10 = "about.html";
    private String testCase11 = "/domains/root/db";
    private Address addressTestCase1;
    private Address addressTestCase2;
    private Address addressTestCase3;
    private Address addressTestCase4;
    private Address addressTestCase5;
    private Address addressTestCase6;
    private Address addressTestCase7;
    private Address addressTestCase8;
    private Address addressTestCase9;
    private Address addressTestCase10;
    private Address addressTestCase11;
    private RelativeAddressWithoutExtension relativeAddressWithoutExtension;

    @Before
    public void setUp() throws Exception {

        addressTestCase1 = new Address("", "href=\"", testCase1);
        addressTestCase2 = new Address("", "src=\"", testCase2);
        addressTestCase3 = new Address("", "href=\"", testCase3);
        addressTestCase4 = new Address("", "href=\"", testCase4);
        addressTestCase5 = new Address("", "href=\"", testCase5);
        addressTestCase6 = new Address("", "href=\"", testCase6);
        addressTestCase7 = new Address("", "href=\"", testCase7);
        addressTestCase8 = new Address("", "src=\"", testCase8);
        addressTestCase9 = new Address("", "href=\"", testCase9);
        addressTestCase10 = new Address("", "href=\"", testCase10);
        addressTestCase11 = new Address("", "href=\"", testCase11);

        relativeAddressWithoutExtension = new RelativeAddressWithoutExtension();
    }

    @Test
    public void shouldBuildLocalLinksWithExtension() {

        //When
        String result1 = relativeAddressWithoutExtension.replace(addressTestCase1, testAddress1);
        String result2 = relativeAddressWithoutExtension.replace(addressTestCase2, testAddress1);
        String result3 = relativeAddressWithoutExtension.replace(addressTestCase3, testAddress1);
        String result4 = relativeAddressWithoutExtension.replace(addressTestCase4, testAddress1);
        String result5 = relativeAddressWithoutExtension.replace(addressTestCase5, testAddress1);
        String result6 = relativeAddressWithoutExtension.replace(addressTestCase6, testAddress1);
        String result9 = relativeAddressWithoutExtension.replace(addressTestCase9, testAddress1);
        String result11 = relativeAddressWithoutExtension.replace(addressTestCase11, testAddress1);

        //Then
        assertEquals("href=\"about.html\"", result1);
        assertEquals("src=\"domains.html\"", result2);
        assertEquals("href=\"domains/root.html\"", result3);
        assertEquals("href=\"domains/root/db.html\"", result4);
        assertEquals("href=\"domains/idn-tables.html\"", result5);
        assertEquals("href=\"time-zones.html\"", result6);
        assertEquals("href=\"about.html\"", result9);
        assertEquals("href=\"domains/root/db.html\"", result11);

    }

    @Test
    public void shouldBuildLocalLinksWithReferenceToMainFolderAndWithExtension() {

        //When
        //When
        String result1 = relativeAddressWithoutExtension.replace(addressTestCase1, testAddress2);
        String result2 = relativeAddressWithoutExtension.replace(addressTestCase2, testAddress2);
        String result3 = relativeAddressWithoutExtension.replace(addressTestCase3, testAddress2);
        String result4 = relativeAddressWithoutExtension.replace(addressTestCase4, testAddress2);
        String result5 = relativeAddressWithoutExtension.replace(addressTestCase5, testAddress2);
        String result6 = relativeAddressWithoutExtension.replace(addressTestCase6, testAddress2);
        String result9 = relativeAddressWithoutExtension.replace(addressTestCase9, testAddress2);
        String result11 = relativeAddressWithoutExtension.replace(addressTestCase11, testAddress2);

        //Then
        assertEquals("href=\"../../about.html\"", result1);
        assertEquals("src=\"../../domains.html\"", result2);
        assertEquals("href=\"../../domains/root.html\"", result3);
        assertEquals("href=\"../../domains/root/db.html\"", result4);
        assertEquals("href=\"../../domains/idn-tables.html\"", result5);
        assertEquals("href=\"../../time-zones.html\"", result6);
        assertEquals("href=\"../../about.html\"", result9);
        assertEquals("href=\"../../domains/root/db.html\"", result11);
    }

    @Test
    public void shouldSupportOperation() {

        //When
        boolean result1 = relativeAddressWithoutExtension.supports(testCase1);
        boolean result2 = relativeAddressWithoutExtension.supports(testCase2);
        boolean result3 = relativeAddressWithoutExtension.supports(testCase3);
        boolean result4 = relativeAddressWithoutExtension.supports(testCase4);
        boolean result5 = relativeAddressWithoutExtension.supports(testCase5);
        boolean result6 = relativeAddressWithoutExtension.supports(testCase6);
        boolean result7 = relativeAddressWithoutExtension.supports(testCase7);
        boolean result8 = relativeAddressWithoutExtension.supports(testCase8);
        boolean result9 = relativeAddressWithoutExtension.supports(testCase9);
        boolean result10 = relativeAddressWithoutExtension.supports(testCase10);
        boolean result11 = relativeAddressWithoutExtension.supports(testCase11);

        //Then
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
        assertTrue(result5);
        assertTrue(result6);
        assertFalse(result7);
        assertFalse(result8);
        assertTrue(result9);
        assertFalse(result10);
        assertTrue(result11);
    }
    */
}