SOLRDEMO
========

Quick [Play 2](http://www.playframework.org/) demo using [SOLR](http://lucene.apache.org/solr/)

### to get this up in intelliJ:
install plugins: play 2, scala, sbt
play compile
play idea
open directory as project

### start app:
play run
point your browser to (http://localhost:9000)

### Add 1000 docs to index
http://localhost:9000/addmany

### Add 1 doc to index
http://localhost:9000/add

### Clear index
http://localhost:9000/removeall

### Persistence
to persist index on disk:
`solr/conf/solrconfig.xml`:
    `<directoryFactory name="DirectoryFactory" class="${solr.directoryFactory:solr.StandardDirectoryFactory}"/>`

to have index in memory:
`solr/conf/solrconfig.xml`:
  `<directoryFactory name="DirectoryFactory" class="org.apache.solr.core.RAMDirectoryFactory"/>`
