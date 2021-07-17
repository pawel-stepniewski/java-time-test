package jtime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JavaTimeTest {

    @Test
    @DisplayName("java.sql.Date#toInstant() method throws UnsupportedOperationException.")
    public void sqlDateToInstantThrowsException() {
        Date sqlDate = new java.sql.Date(Instant.now().toEpochMilli());
        assertThrows(UnsupportedOperationException.class, sqlDate::toInstant);
    }

}
