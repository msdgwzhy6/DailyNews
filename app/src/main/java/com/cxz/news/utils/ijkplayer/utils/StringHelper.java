/*
 * Copyright (C) 2012 YIXIA.COM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cxz.news.utils.ijkplayer.utils;

import android.util.Log;

import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
    public static String join(Object[] elements, CharSequence separator) {
        return join(Arrays.asList(elements), separator);
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n|&#160;");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String join(Iterable<? extends Object> elements, CharSequence separator) {
        StringBuilder builder = new StringBuilder();

        if (elements != null) {
            Iterator<? extends Object> iter = elements.iterator();
            if (iter.hasNext()) {
                builder.append(String.valueOf(iter.next()));
                while (iter.hasNext()) {
                    builder.append(separator).append(String.valueOf(iter.next()));
                }
            }
        }

        return builder.toString();
    }

    public static String fixLastSlash(String str) {
        String res = str == null ? "/" : str.trim() + "/";
        if (res.length() > 2 && res.charAt(res.length() - 2) == '/') {
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    public static int convertToInt(String str) throws NumberFormatException {
        int s, e;
        for (s = 0; s < str.length(); s++)
            if (Character.isDigit(str.charAt(s))) {
                break;
            }
        for (e = str.length(); e > 0; e--)
            if (Character.isDigit(str.charAt(e - 1))) {
                break;
            }
        if (e > s) {
            try {
                return Integer.parseInt(str.substring(s, e));
            } catch (NumberFormatException ex) {
                Log.e("convertToInt", String.valueOf(ex));
                throw new NumberFormatException();
            }
        } else {
            throw new NumberFormatException();
        }
    }

    public static String generateTime(long time, boolean total) {
        /*int totalSeconds = Math.round(time / 1000.0f);*/

        int totalSeconds;

        if (total) {
            totalSeconds = Math.round(time / 1000.0f);
        } else {
            totalSeconds = (int) Math.ceil(time / 1000.0f);
        }

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }

    public static int getDuration(String time) {

        final String[] split = time.split(":");

        if (split.length == 2) {
            return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        } else if (split.length == 3) {
            return Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2]);
        }

        return 0;
    }


}
