package jtime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class B_ParseToJavaTimeTest {

    @Test
    @DisplayName("Parsing String to poor type is dropping redundant data")
    public void parsingStringToPoorTypeIsDropRedundantData() {
        final String toParse = "2021-12-03T10:15:30Z";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

        assertDoesNotThrow(() -> formatter.parse(toParse, Instant::from));
        assertDoesNotThrow(() -> LocalTime.parse(toParse, formatter));
        assertDoesNotThrow(() -> LocalDate.parse(toParse, formatter));
        assertDoesNotThrow(() -> LocalDateTime.parse(toParse, formatter));
        assertDoesNotThrow(() -> OffsetDateTime.parse(toParse, formatter));
        assertDoesNotThrow(() -> ZonedDateTime.parse(toParse, formatter));

        assertEquals(LocalTime.parse(toParse, formatter), LocalTime.of(10, 15, 30, 0));
        assertEquals(LocalDate.parse(toParse, formatter), LocalDate.of(2021, 12, 3));
        assertEquals(LocalDateTime.parse(toParse, formatter), LocalDateTime.of(2021, 12, 3, 10, 15, 30, 0));
        assertEquals(OffsetDateTime.parse(toParse, formatter), OffsetDateTime.of(2021, 12, 3, 10, 15, 30, 0, ZoneOffset.of("Z")));
        assertEquals(ZonedDateTime.parse(toParse, formatter), ZonedDateTime.of(2021, 12, 3, 10, 15, 30, 0, ZoneId.of("Z")));
    }

    @Test
    @DisplayName("Parsing poor String to broad type throws exception")
    public void parsingPoorStringToBroadTypeThrowsException() {
        final String toParse = "2021-12-03T10:15:30";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        assertThrows(DateTimeParseException.class, () -> formatter.parse(toParse, Instant::from));
        assertThrows(DateTimeParseException.class, () -> OffsetDateTime.parse(toParse, formatter));
        assertThrows(DateTimeParseException.class, () -> ZonedDateTime.parse(toParse, formatter));
    }

    @Test
    @DisplayName("ZoneOffset can be keep by ZonedDateTime object")
    public void zoneOffsetCanBeKeepByZonedDateTime() {
        final String toParse = "2021-12-03T10:15:30-08";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

        assertDoesNotThrow(() -> ZonedDateTime.parse(toParse, formatter));
        assertEquals(ZonedDateTime.parse(toParse, formatter), ZonedDateTime.of(2021, 12, 3, 10, 15, 30, 0, ZoneOffset.of("-08")));
    }

    @Test
    @DisplayName("Parsing to Instant requires ZoneOffset or ZoneId")
    public void parsingToInstantRequiresZoneOffsetOrZoneId() {
        final String toParse = "2021-12-03T10:15:30";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        assertThrows(DateTimeParseException.class, () -> formatter.parse(toParse, Instant::from));
        assertDoesNotThrow(() -> LocalDateTime.parse(toParse, formatter).toInstant(ZoneOffset.UTC));
    }

    @Test
    @DisplayName("Parsing String with unmatched formatter throws exception")
    public void parsingStringWithUnmatchedFormatterThrowsException() {
        final String toParse = "2021-12-03T10:15:30Z";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        assertThrows(DateTimeParseException.class, () -> formatter.parse(toParse));
    }
}
