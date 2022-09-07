// Plugin Klaza para Moodle - Server - Colors.kt
// Copyright (C) 2022 Klaza Team

// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.

// This code is a adaptation of the code of "shakram02" on stackoverflow (https://stackoverflow.com/a/45444716)

package klaza.klaza_server.classes

open class Colors {

    companion object {

        // Reset
        val RESET = "\u001b[0m" // Text Reset

        // Regular Colors
        val BLACK = "\u001b[0;30m" // BLACK
        val RED = "\u001b[0;31m" // RED
        val GREEN = "\u001b[0;32m" // GREEN
        val YELLOW = "\u001b[0;33m" // YELLOW
        val BLUE = "\u001b[0;34m" // BLUE
        val PURPLE = "\u001b[0;35m" // PURPLE
        val CYAN = "\u001b[0;36m" // CYAN
        val WHITE = "\u001b[0;37m" // WHITE


        // Bold
        val BLACK_BOLD = "\u001b[1;30m" // BLACK
        val RED_BOLD = "\u001b[1;31m" // RED
        val GREEN_BOLD = "\u001b[1;32m" // GREEN
        val YELLOW_BOLD = "\u001b[1;33m" // YELLOW
        val BLUE_BOLD = "\u001b[1;34m" // BLUE
        val PURPLE_BOLD = "\u001b[1;35m" // PURPLE
        val CYAN_BOLD = "\u001b[1;36m" // CYAN
        val WHITE_BOLD = "\u001b[1;37m" // WHITE


        // Underline
        val BLACK_UNDERLINED = "\u001b[4;30m" // BLACK
        val RED_UNDERLINED = "\u001b[4;31m" // RED
        val GREEN_UNDERLINED = "\u001b[4;32m" // GREEN
        val YELLOW_UNDERLINED = "\u001b[4;33m" // YELLOW
        val BLUE_UNDERLINED = "\u001b[4;34m" // BLUE
        val PURPLE_UNDERLINED = "\u001b[4;35m" // PURPLE
        val CYAN_UNDERLINED = "\u001b[4;36m" // CYAN
        val WHITE_UNDERLINED = "\u001b[4;37m" // WHITE


        // Background
        val BLACK_BACKGROUND = "\u001b[40m" // BLACK
        val RED_BACKGROUND = "\u001b[41m" // RED
        val GREEN_BACKGROUND = "\u001b[42m" // GREEN
        val YELLOW_BACKGROUND = "\u001b[43m" // YELLOW
        val BLUE_BACKGROUND = "\u001b[44m" // BLUE
        val PURPLE_BACKGROUND = "\u001b[45m" // PURPLE
        val CYAN_BACKGROUND = "\u001b[46m" // CYAN
        val WHITE_BACKGROUND = "\u001b[47m" // WHITE


        // High Intensity
        val BLACK_BRIGHT = "\u001b[0;90m" // BLACK
        val RED_BRIGHT = "\u001b[0;91m" // RED
        val GREEN_BRIGHT = "\u001b[0;92m" // GREEN
        val YELLOW_BRIGHT = "\u001b[0;93m" // YELLOW
        val BLUE_BRIGHT = "\u001b[0;94m" // BLUE
        val PURPLE_BRIGHT = "\u001b[0;95m" // PURPLE
        val CYAN_BRIGHT = "\u001b[0;96m" // CYAN
        val WHITE_BRIGHT = "\u001b[0;97m" // WHITE


        // Bold High Intensity
        val BLACK_BOLD_BRIGHT = "\u001b[1;90m" // BLACK
        val RED_BOLD_BRIGHT = "\u001b[1;91m" // RED
        val GREEN_BOLD_BRIGHT = "\u001b[1;92m" // GREEN
        val YELLOW_BOLD_BRIGHT = "\u001b[1;93m" // YELLOW
        val BLUE_BOLD_BRIGHT = "\u001b[1;94m" // BLUE
        val PURPLE_BOLD_BRIGHT = "\u001b[1;95m" // PURPLE
        val CYAN_BOLD_BRIGHT = "\u001b[1;96m" // CYAN
        val WHITE_BOLD_BRIGHT = "\u001b[1;97m" // WHITE


        // High Intensity backgrounds
        val BLACK_BACKGROUND_BRIGHT = "\u001b[0;100m" // BLACK
        val RED_BACKGROUND_BRIGHT = "\u001b[0;101m" // RED
        val GREEN_BACKGROUND_BRIGHT = "\u001b[0;102m" // GREEN
        val YELLOW_BACKGROUND_BRIGHT = "\u001b[0;103m" // YELLOW
        val BLUE_BACKGROUND_BRIGHT = "\u001b[0;104m" // BLUE
        val PURPLE_BACKGROUND_BRIGHT = "\u001b[0;105m" // PURPLE
        val CYAN_BACKGROUND_BRIGHT = "\u001b[0;106m" // CYAN
        val WHITE_BACKGROUND_BRIGHT = "\u001b[0;107m" // WHITE

    }

}