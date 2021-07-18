package jtime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class A_ToInstantMethodTest {

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
    public void sqlTimestampToInstantDoesNotThrowException() {
        Date sqlTimestamp = new java.sql.Timestamp(Instant.now().toEpochMilli());
        assertDoesNotThrow(sqlTimestamp::toInstant);
    }

    @Test
    @DisplayName("java.util.Date#toInstant() does not throw Exception.")
    public void utilDateToInstantDoesNotThrowException() {
        Instant now = Instant.now();

        assertDoesNotThrow(Date.from(now)::toInstant);

//      All of the following use static method of parent class java.util.Date.
//      So result of this calls is always java.util.Date not a class from java.sql package
        assertDoesNotThrow(java.sql.Time.from(now)::toInstant);
        assertDoesNotThrow(java.sql.Date.from(now)::toInstant);
        assertDoesNotThrow(java.sql.Timestamp.from(now)::toInstant);
    }
}
