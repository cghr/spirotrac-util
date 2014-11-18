package org.cghr.spirotrac.util

import com.google.gson.Gson
import groovy.transform.TupleConstructor

/**
 * Created by ravitej on 6/11/14.
 */
@TupleConstructor
class GDTReader {

    String outputGDTFilePath
    Map labels
    List testParams
    boolean removeOnRead


    String getGDTData() {

        String currentParameter = ""
        Map result = [:]

        def file = new File(outputGDTFilePath)
        file.eachLine {
            String line ->
                testParams.each {
                    if (line.contains(it))
                        currentParameter = it
                }
                if (line.contains(labels.actual))
                    result.put("$currentParameter" + "_actual", (line - labels.actual))

                if (line.contains(labels.type))
                    result.put("$currentParameter" + "_type", (line - labels.type))

                if (line.contains(labels.predicted))
                    result.put("$currentParameter" + "_predicted", (line - labels.predicted))

        }
        if (removeOnRead)
            removeGeneratedGDTFile(outputGDTFilePath)

        new Gson().toJson(result)
    }


    void removeGeneratedGDTFile(filePath) {

        new File(filePath).delete()


    }

}
