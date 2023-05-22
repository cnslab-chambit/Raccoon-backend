package kwu.raccooncommon.utils;

import lombok.AllArgsConstructor;

import java.text.BreakIterator;

@AllArgsConstructor
public final class RaccoonStringUtils {
    public static long getLetterLength(String s){
        return getCharacterBoundary(s) - get4ByteEmoji(s);
    }
    public static long get4ByteEmoji(String s){
        return s.chars()
                .filter(i -> Character.isSurrogate((char) i ))
                .count()/4;
    }
    public static long getCharacterBoundary(String s){
        BreakIterator it = BreakIterator.getCharacterInstance();
        it.setText(s);
        long count = 0;
        while (it.next() != BreakIterator.DONE) {
            count++;
        }
        return count;
    }
}
