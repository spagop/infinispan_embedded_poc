#----------------------------------------------
# updates the war's MANIFEST.MF file to include
# the infinispan dependency
#----------------------------------------------
cd target
printf "%s\n" "Dependencies: org.infinispan" >> MANIFEST.MF
jar -umf MANIFEST.MF dg-perf-test.war
rm MANIFEST.MF
