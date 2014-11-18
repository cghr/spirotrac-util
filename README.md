Spirotrac-util is a micro web-services module to control,inject and retrieve data from the Spirotrac application.

What it can do:
===============
* Launch the spirotrac in gdt mode by injecting a given patient data.
* Reads the data once spirotrac dumps the patient data is dumped to GDT file

How it Works:
=============

* Spirotrac software runs in GDT mode,which expects a file to be created in a required directory to import once it's invoked in the GDT mode.
* Spirotrac reads the data available in the GDT file in the required directory and prepares the software for lung function test
* Once the test is done,spirotrac has to be closed to dump all the test results to a file.
* Spirotrac-util listens for creation of new files in the directory and loads it and makes the data available as a web service 

Tasks Done by Spirotrac-Util:
==============================
* Creates a GDT file with given data in a required directory as per the spec
* Reads the output results file and makes the data available at a web service end point.

Tasks done by Spirotrac Software:
=================================

* Reads the data created by Spirotrac-util and prepares the application for testing lung function.
* Once closed,dumps all the data to  a GDT output file which can be read by third-party softwares.

