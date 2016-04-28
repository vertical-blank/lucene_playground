
println args[0]

@Grab('org.apache.lucene:lucene-core:6.0.0')
@Grab('org.apache.lucene:lucene-analyzers-kuromoji:6.0.0')
@Grab('org.apache.lucene:lucene-analyzers-common:6.0.0')
@Grab('org.apache.lucene:lucene-queryparser:6.0.0')

import org.apache.lucene.document.*
import org.apache.lucene.index.*
import org.apache.lucene.search.*
import org.apache.lucene.store.*
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.ja.JapaneseAnalyzer
import org.apache.lucene.queryparser.classic.QueryParser

def reader = DirectoryReader.open(
	new SimpleFSDirectory(new File("index").toPath())
)

def searcher = new IndexSearcher(reader)

def analyzer = new JapaneseAnalyzer()
def parser = new QueryParser("content", analyzer)
def query = parser.parse(args[0])

def hits = searcher.search(query, 1000).scoreDocs

hits.each { println searcher.doc(it.doc)  }

reader.close()

