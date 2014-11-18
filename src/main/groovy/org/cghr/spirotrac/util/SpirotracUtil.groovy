package org.cghr.spirotrac.util

import groovy.transform.TupleConstructor

/**
 * Created by ravitej on 28/10/14.
 */
@TupleConstructor
class SpirotracUtil {

    String launchCommand

    void launch() {

        def thread = Thread.start {

            launchCommand.execute()
        }

    }

}
