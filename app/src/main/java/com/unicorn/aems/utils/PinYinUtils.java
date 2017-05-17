package com.unicorn.aems.utils;

import com.unicorn.aems.app.dagger.App;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import javax.inject.Inject;

@App
public class PinYinUtils {

    private HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

    @Inject
    public PinYinUtils() {
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    // todo 多音字问题
    public String getInitial(String str) {
        char c = str.charAt(0);
        try {
            return PinyinHelper.toHanyuPinyinStringArray(c, format)[0].substring(0, 1);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            // do nothing
        }
        return "";
    }

}
