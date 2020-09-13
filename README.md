<!--
    Licensed to the Apache Software Foundation (ASF) under one or more
    contributor license agreements.  See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    The ASF licenses this file to You under the Apache License, Version 2.0
    the "License"); you may not use this file except in compliance with
    the License.  You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 -->

# Apache Lucene and Solr

Apache Lucene is a high-performance, full featured text search engine library
written in Java.

Apache Solr is an enterprise search platform written using Apache Lucene.
Major features include full-text search, index replication and sharding, and
result faceting and highlighting.

[![Build Status](https://builds.apache.org/view/L/view/Lucene/job/Lucene-Artifacts-master/badge/icon?subject=Lucene)](https://builds.apache.org/view/L/view/Lucene/job/Lucene-Artifacts-master/) [![Build Status](https://builds.apache.org/view/L/view/Lucene/job/Solr-Artifacts-master/badge/icon?subject=Solr)](https://builds.apache.org/view/L/view/Lucene/job/Solr-Artifacts-master/)

### How to run the search engine in linux

Build the docker image from source code

# docker build -t search-engine-lucene-solr:6.6.7.

Run the docker image

# docker run -it search-engine-lucene-solr:6.6.7

To index all local files with standard analyzer, it to tokenized all words and filter stop-words

# ant -f lucene/demo/build.xml \
# -Ddocs=lucene/demo/data/wiki-small/en/articles/ run-html-indexing-demo

Run the search engine

# ant -f lucene/demo/build.xml run-html-indexing-demo

To index all local files with custom analyzer and customized ranking functions (term frequency-inverse document frequency)

# ant -f lucene/demo/build.xml \
# -Ddocs=lucene/demo/data/wiki-small/en/articles/ run-tfidf-indexing

Run the search engine

# ant -f lucene/demo/build.xml run-tfidf-search


## Development/IDEs

Ant can be used to generate project files compatible with most common IDEs.
Run the ant command corresponding to your IDE of choice before attempting to
import Lucene/Solr.

- *Eclipse* - `ant eclipse` (See [this](https://cwiki.apache.org/confluence/display/solr/HowToConfigureEclipse) for details)
- *IntelliJ* - `ant idea` (See [this](https://cwiki.apache.org/confluence/display/lucene/HowtoConfigureIntelliJ) for details)
- *Netbeans* - `ant netbeans` (See [this](https://cwiki.apache.org/confluence/display/lucene/HowtoConfigureNetbeans) for details)


## Contributing

Please review the [Contributing to Solr
Guide](https://cwiki.apache.org/confluence/display/solr/HowToContribute) for information on
contributing.

## Discussion and Support

- [Users Mailing List](http://lucene.apache.org/solr/community.html#solr-user-list-solr-userluceneapacheorg)
- [Developers Mailing List](http://lucene.apache.org/solr/community.html#developer-list-devluceneapacheorg)
- [Lucene Issue Tracker](https://issues.apache.org/jira/browse/LUCENE)
- [Solr Issue Tracker](https://issues.apache.org/jira/browse/SOLR)
- IRC: `#solr` and `#solr-dev` on freenode.net
