package edu.uchicago.gerber._04interfaces.P0_3.P9_1;
import java.time.LocalTime;
public class Clock {
        public String getHours() {
            String currentTime = LocalTime.now().toString();
            return currentTime.substring(0,2);
        }

        public String getMinutes() {
            String currentTime = LocalTime.now().toString();
            return currentTime.substring(3,5);
        }

        public String getTime() {
            return getHours()+":"+getMinutes();
        }
}
