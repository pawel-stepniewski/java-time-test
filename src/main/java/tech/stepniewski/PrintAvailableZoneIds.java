package tech.stepniewski;

import java.time.ZoneId;

public class PrintAvailableZoneIds {

    public static void main(String[] args) {
        System.out.println(ZoneId.getAvailableZoneIds());
    }
}
