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
    @DisplayName("java.sql.Time#toInstant() method throws UnsupportedOperationException.")
    public void sqlTimeToInstantThrowsException() {
        Date sqlTime = new java.sql.Time(Instant.now().toEpochMilli());
        assertThrows(UnsupportedOperationException.class, sqlTime::toInstant);
    }

    @Test
    @DisplayName("java.sql.Timestamps#toInstant() does not throw Exception.")
    public void sqlTimestampToInstantThrowsException() {
        Date sqlTimestamp = new java.sql.Timestamp(Instant.now().toEpochMilli());
        assertDoesNotThrow(sqlTimestamp::toInstant);
    }
}
