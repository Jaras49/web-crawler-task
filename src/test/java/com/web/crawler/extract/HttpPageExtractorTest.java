package com.web.crawler.extract;

import com.web.crawler.model.Address;
import com.web.crawler.model.Page;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class HttpPageExtractorTest {

    @Test
    public void shouldExtractPage() throws URISyntaxException, IOException {

        //Given
        String link = "http://example.com/";

        URL resource = this.getClass().getClassLoader().getResource("html/index.html");

        String body = Files.readAllLines(Paths.get(resource.toURI())).stream()
                .collect(Collectors.joining("\n"));

        Page page = new Page(new Address(link), body);
        PageExtractor pageExtractor = new HttpPageExtractor();

        //When
        Page result = pageExtractor.extractPage(link);

        //Then
        assertEquals(page.getAddress(), result.getAddress());
        assertEquals(page.getBody(), result.getBody());
        assertEquals(page, result);
    }
}