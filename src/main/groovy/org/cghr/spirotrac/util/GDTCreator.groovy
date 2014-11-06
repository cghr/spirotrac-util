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
    String gdtFilename
    String outputGDTFilename


    void createGDTFile(Map context) {

        Template template = handlebars.compile(gdtTemplateFilePath)
        String gdtData = StringEscapeUtils.unescapeHtml4(template.apply(transformData(context)))
        String output = gdtData.replaceAll("LLL", gdtData.size() + constantToSentenceLength + "")
        new File(gdtDirectoryPath + gdtFilename).setText(output)
    }

    String getOutputGDTData() {

        new File(gdtDirectoryPath + outputGDTFilename).text
    }

    Map transformData(Map data) {

        [pid   : data.pid,
         dob   : data.dob.replaceAll("-", ""),
         height: transformHeightOrWeight(data.height.toInteger()),
         weight: transformHeightOrWeight(data.weight.toInteger()),
         gender: transformGender(data.gender)
        ]

    }

    String transformHeightOrWeight(Integer value) {
        value < 100 ? ('0' + value) : value
    }

    String transformGender(String gender) {
        gender == 'Male' ? '01' : '02'
    }


}
