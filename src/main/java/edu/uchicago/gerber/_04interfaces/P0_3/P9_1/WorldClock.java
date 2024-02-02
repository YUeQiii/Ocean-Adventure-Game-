package edu.uchicago.gerber._04interfaces.P0_3.P9_1;

public class WorldClock extends Clock {


        private final int offset; // Time zone offset

        public WorldClock(int offset) {
            this.offset = offset;
        }

        @Override
        public String getHours() {
            int adjustedHour = Integer.parseInt(super.getHours()) + offset;

            // Adjust for going over 24 hours or going negative
            if (adjustedHour >= 24) {
                adjustedHour -= 24;
            } else if (adjustedHour < 0) {
                adjustedHour += 24;
            }

            return String.valueOf(adjustedHour);
        }

        // We don't need to override getMinutes as minutes remain the same.

}
