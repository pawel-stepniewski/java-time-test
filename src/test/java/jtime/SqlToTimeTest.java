package jtime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SqlToTimeTest {

    @Test
    @DisplayName("java.sql.Date#toInstant() method throws UnsupportedOperationException.")
    public void sqlDateToInstantThrowsException() {
        Date sqlDate = new java.sql.Date(Instant.now().toEpochMilli());
        assertThrows(UnsupportedOperationException.class, sqlDate::toInstant);
    }

    @Test
    @DisplayName("java.sql.Time#toInstant() does not throw Exception.")
    public void sqlTimeToInstantThrowsException() {
        Instant now = Instant.now();
        Date sqlTime = new java.sql.Time(now.toEpochMilli());
        assertThrows(UnsupportedOperationException.class, sqlTime::toInstant);
    }

    @Test
    @DisplayName("java.sql.Timestamps#toInstant() does not throw Exception.")
    public void sqlTimestampToInstantThrowsException() {
        Instant now = Instant.now();
        Date sqlTimestamp = new java.sql.Timestamp(now.toEpochMilli());
        assertDoesNotThrow(sqlTimestamp::toInstant);
    }
}
