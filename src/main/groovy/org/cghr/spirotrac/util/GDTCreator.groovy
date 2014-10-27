package org.cghr.spirotrac.util

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Template
import groovy.transform.TupleConstructor
import org.apache.commons.lang3.StringEscapeUtils

/**
 * Created by ravitej on 27/10/14.
 */
@TupleConstructor
class GDTCreator {

    String gdtDirectoryPath
    String gdtTemplateFilePath
    Handlebars handlebars
    int constantToSentenceLength
    String filename


    void createGDTFile(Map context) {

        Template template = handlebars.compile(gdtTemplateFilePath)
        String gdtData = StringEscapeUtils.unescapeHtml4(template.apply(context))
        String output = gdtData.replaceAll("LLL", gdtData.size() + constantToSentenceLength + "")
        new File(gdtDirectoryPath + filename).setText(output)
    }


}
