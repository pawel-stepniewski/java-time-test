package jtime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class C_ComparingJavaTimeObjectsTest {

    @Test
    @DisplayName("OffsetDateTime#toLocalDateTime drops ZoneOffset")
    public void toLocalDateTimeDropsZoneOffset() {
        OffsetDateTime offsetDateTime1 = OffsetDateTime.of(2021, 11, 10, 10, 11, 12, 0, ZoneOffset.of("+01"));
        OffsetDateTime offsetDateTime2 = OffsetDateTime.of(2021, 11, 10, 10, 11, 12, 0, ZoneOffset.of("+05"));
        assertEquals(offsetDateTime1.toLocalDateTime(), offsetDateTime2.toLocalDateTime());
    }

    @Test
    @DisplayName("point in times should be compared with Instant objects")
    public void pointInTimesShouldBeComparedWithInstantObjects() {
        OffsetDateTime offset1 = OffsetDateTime.of(2021, 11, 10, 10, 11, 12, 0, ZoneOffset.of("+01"));
        OffsetDateTime offset2 = OffsetDateTime.of(2021, 11, 10, 15, 11, 12, 0, ZoneOffset.of("+06"));
        OffsetDateTime offset3 = OffsetDateTime.of(2021, 11, 10, 10, 11, 12, 0, ZoneOffset.of("+01"));
        assertEquals(offset1.toInstant(), offset2.toInstant());
        assertNotEquals(offset1, offset2);
        assertEquals(offset1, offset3);
    }

    @Test
    @DisplayName("ZonedDateTime can switch ZoneOffset")
    public void zonedDateTimeCanSwitchZoneOffset() {
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(2020, 10, 25, 2, 0, 0, 0, ZoneId.of("Europe/Warsaw"));
        assertEquals(zonedDateTime1.plusHours(1).toLocalDateTime(), zonedDateTime1.toLocalDateTime());
        assertNotEquals(zonedDateTime1.plusHours(1).getOffset(), zonedDateTime1.getOffset());
    }
}
