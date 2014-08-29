package monentrevue.search.impl;

import monentrevue.bean.Interview;
import monentrevue.bean.Question;
import monentrevue.search.InterviewSearchService;
import monentrevue.search.SearchException;
import monentrevue.search.lucene.FieldIdentifier;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User Session Memory implementation
 * This usually should be one service implementation per session since it is stateful in order to keep the solr Ram Directory.
 * Date: 8/27/14
 * Time: 10:13 AM
 *
 * @author jsanca
 */
public class SessionMemoryInterviewSearchService implements InterviewSearchService {

    public static final String QUESTION_TEXT = "text";
    public static final String QUESTION_TYPE = "type";
    public static final String QUESTION_NAME_ID = "nameId";
    private RAMDirectory ramDirectory = null;

    /**
     * Do the index of an interview.
     * In addition the current interview will be ready to be queried.
     *
     * @param interview Interview
     */
    @Override
    public void indexInterview(final Interview interview) {

        Document document = null;
        IndexWriter writer = null;

        if (null != interview) {

            try {

                if (null == this.ramDirectory) {

                    this.ramDirectory =
                            new RAMDirectory();
                }

                writer =
                        this.createWriter();

                for (Question question: interview.getQuestions()) {

                    document =
                            this.createDocument(question);

                    writer.addDocument(document);
                }
            } catch (IOException e) {

                throw new SearchException(e);
            } finally {

                if (null != writer) {

                    try {

                        writer.close();
                    } catch (IOException e) {

                        // quiet.
                    }
                }
            }
        }
    } // E:O:F:indexInterview.

    protected IndexWriter createWriter() {

        IndexWriter writer = null;
        Analyzer analyzer = null;
        IndexWriterConfig config = null;

        try {

            analyzer =
                    new StandardAnalyzer(Version.LUCENE_44);

            config =
                    new IndexWriterConfig(Version.LUCENE_44, analyzer);

            writer =
                    new IndexWriter(this.ramDirectory,
                            config);
        } catch (IOException e) {

            throw new SearchException(e);
        }

        return writer;
    } // checkWriter.


    /**
     * Do a search over the current session indexes
     * <p/>
     * The interview objects returned will contain only the information stored in the index,
     * probably not all of them.
     *
     * @param searchTerm String
     * @return List of Interview
     */
    @Override
    public List<Question> search(final String searchTerm) {

        List<Question> interviewList =
                Collections.EMPTY_LIST;
        DirectoryReader directoryReader = null;
        IndexSearcher indexSearcher = null;
        QueryParser parser = null;
        Analyzer analyzer = null;
        ScoreDoc[] hits = null;
        Document hitDoc = null;
        TopDocs topDocs = null;

        if (null != this.ramDirectory) {

            try {

                analyzer =
                        new StandardAnalyzer(Version.LUCENE_44);
                directoryReader = DirectoryReader.open(this.ramDirectory);
                indexSearcher = new IndexSearcher(directoryReader);

                // Parse a simple query that searches for "text":
                parser  = new QueryParser(Version.LUCENE_44, QUESTION_TEXT, analyzer);
                topDocs = indexSearcher.search(parser.parse(searchTerm), null, 1000);
                hits    = topDocs.scoreDocs;
                interviewList =
                        new ArrayList<Question>();

                // Iterate through the results:
                for (int i = 0; i < hits.length; i++) {

                    hitDoc = indexSearcher.doc(hits[i].doc);
                    interviewList.add(this.convert(hitDoc));
                }
            }  catch (IOException e) {

                throw new SearchException(e);
            } catch (ParseException e) {

                throw new SearchException(e);
            } finally {

                if (null != directoryReader) {

                    try {

                        directoryReader.close();
                    } catch (IOException e) {
                        // quiet
                    }
                }
            }
        }

        return interviewList;
    } // search.

    private Question convert(Document hitDoc) {

        final Question question = new Question();

        question.setNameId (hitDoc.get(QUESTION_NAME_ID));
        question.setType   (hitDoc.get(QUESTION_TYPE));
        question.setText(hitDoc.get(QUESTION_TEXT));

        return question;
    } // convert.

    protected Document createDocument(final Question question) {

        final Document document
                = new Document();

        // we index by type (not tokenized) and text, aka the question (full tokenized)
        document.add(new FieldIdentifier(QUESTION_NAME_ID, question.getNameId()));
        document.add(new StringField    (QUESTION_TYPE,   question.getType(), Field.Store.YES));
        document.add(new TextField      (QUESTION_TEXT,   question.getText(), Field.Store.YES));

        return document;
    } // createDocument.
} // E:O:F:SessionMemoryInterviewSearchService.
