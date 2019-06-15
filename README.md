# Work stealing executor service test

Can switch between using fxed thread pool and work stealing in the main application java

Using the fixed thread pool will get stuck once all the available threads have been used. The work stealing executor will continue creating children threads and doesn't seem to suffer the same fate.

Another thread should terminate everything after 10 seconds.
