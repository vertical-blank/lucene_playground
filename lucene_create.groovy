@Grab('org.apache.lucene:lucene-core:6.0.0')
@Grab('org.apache.lucene:lucene-analyzers-kuromoji:6.0.0')
@Grab('org.apache.lucene:lucene-analyzers-common:6.0.0')

import org.apache.lucene.document.*
import org.apache.lucene.index.*
import org.apache.lucene.store.*
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.ja.JapaneseAnalyzer

def writer = new IndexWriter(
	new SimpleFSDirectory(new File("index").toPath()), 
	new IndexWriterConfig(new JapaneseAnalyzer())
)

def docs = []
new File('texts').listFiles().each {
	def doc = new Document()
	doc.add( new StoredField('path', it.name) )
	doc.add( new TextField('content', new FileReader(it)) )

	docs.add doc
}

writer.addDocuments(docs)
writer.close()

