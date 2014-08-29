package monentrevue.reader;

import monentrevue.bean.Choice;
import monentrevue.bean.Interview;
import monentrevue.bean.Question;
import tagliparser.bean.Item;
import tagliparser.converter.StringConverter;
import tagliparser.parser.TabLiParser;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a .q document with the following format:
 *
 * id: {id}
 * title: {title}
 * note: {note}
 *
 * # comment
 * {nameId:string}) {text:string} | {type:string} | {note:string}
 *     {text:string} | {scoreSuggested:int} | {note:string} | xxx # correct choice
 *     {text:string} | {scoreSuggested:int} | {note:string}  # wrong choice
 *     {text:string} | {scoreSuggested:int} | {note:string} | xxx # correct choice
 *     {text:string} | {scoreSuggested:int} | {note:string} # wrong choice
 *     {text:string} | {scoreSuggested:int} | {note:string} # wrong choice
 *
 * Date: 8/22/14
 * Time: 12:00 AM
 * @author jsanca
 */
public class TabLiInterviewReader implements InterviewReader {

    private static TabLiParser tabLibParser =
            TabLiParser.INSTANCE;


    private static final String NAME_ID_SEPARATOR = "\\)";// new String(new char[] {'',')'});

    private static final String DEFAULT_QUESTION_TYPE = "free";

    /**
     * Parse the interview from a reader.
     *
     * @param reader
     * @return
     */
    @Override
    public Interview read(final Reader reader) {

        final Interview interview = new Interview();
        List<Item<String>> items = null;
        String line = null;
        boolean eval = false;

        items =
            tabLibParser.parser(reader, StringConverter.INSTANCE);

        for (Item<String> item : items) {

            line = item.getValue();

            // if is not a comment
            if (!this.isComment(line)) {

                eval = false;
                eval |= this.evalNameId(interview, line);
                eval |= this.evalTitle(interview, line);
                eval |= this.evalNote(interview, line);

                if (!eval) {

                    this.evalQuestion(interview, item);
                }
            }
        }

        return interview;
    } // read.

    private void evalQuestion(final Interview interview,
                              final Item<String> item) {

        final String questionLine = item.getValue();

        final Question question =
                this.evalQuestionLine(interview, questionLine);

        if (null != item.getList()) {

            this.evalQuestionChoices(question, item.getList());
        }
    } // evalQuestion.

    private void evalQuestionChoices(final Question question,
                                     final List<Item<String>> list) {

        Choice choice = null;
        List<Choice> choiceList =
                new ArrayList<Choice>();

        for (Item<String> choiceItem : list) {

            choice =
                this.evalQuestionChoiceLine
                        (choiceItem.getValue());

            choiceList.add(choice);
        }

        question.setChoices(choiceList);
    } // evalQuestionChoices.

    private Choice evalQuestionChoiceLine(String choiceLine) {

        String [] questionArray = null;
        final Choice choice = new Choice();

        choiceLine =
                this.cleanComment(choiceLine);

        questionArray =
                choiceLine.split("\\|");

        if (4 == questionArray.length) {

            choice.setValid
                    ("xxx".equalsIgnoreCase(questionArray[3].trim()));
        }

        if (3 <= questionArray.length) {

            choice.setNote(questionArray[2].trim());
        }

        if (2 <= questionArray.length) {

            try {

                choice.setSuggestedScore
                    (Integer.parseInt
                            (questionArray[1].trim()));
            } catch (NumberFormatException e) {

                choice.setSuggestedScore(0);
            }
        }

        if (1 <= questionArray.length) {

            choice.setText(questionArray[0].trim());
        }

        return choice;
    } // evalQuestionChoiceLine.

    private Question evalQuestionLine(final Interview interview,
                                  String questionLine) {

        String [] questionArray = null;
        final Question question = new Question();

        questionLine =
                this.cleanComment(questionLine);

        questionArray =
                questionLine.split("\\|");

        if (3 == questionArray.length) {

            question.setNote(questionArray[2].trim());
        }

        if (2 <= questionArray.length) {

            question.setType(questionArray[1].trim());
        } else {

            question.setType(DEFAULT_QUESTION_TYPE);
        }

        if (1 <= questionArray.length) {

            questionArray =
                    questionArray[0].split(NAME_ID_SEPARATOR);

            if (2 == questionArray.length) {

                question.setNameId(questionArray[0].trim());
                question.setText(questionArray[1].trim());
            } else if (1 == questionArray.length) {

                question.setText(questionArray[0].trim());
            }
        }

        interview.addQuestion(question);

        return question;
    } // evalQuestion.

    private boolean evalNote(final Interview interview, final String line) {

        boolean eval = false;
        String [] nameIdArray = null;

        if (line.startsWith("note:")) {

            nameIdArray = line.split(":");
            if ((null != nameIdArray) &&
                    (nameIdArray.length == 2)) {

                interview.setNote
                        (this.cleanComment(nameIdArray[1]));

                eval = true;
            }
        }

        return eval;
    } // evalNote.

    private boolean evalTitle(final Interview interview, final String line) {

        boolean eval = false;
        String [] nameIdArray = null;

        if (line.startsWith("title:")) {

            nameIdArray = line.split(":");
            if ((null != nameIdArray) &&
                    (nameIdArray.length == 2)) {

                interview.setTitle
                        (this.cleanComment(nameIdArray[1]));

                eval = true;
            }
        }

        return eval;
    } // evalTitle.

    private boolean evalNameId(final Interview interview, final String line) {

        boolean eval = false;
        String [] nameIdArray = null;

        if (line.startsWith("id:")) {

            nameIdArray = line.split(":");
            if ((null != nameIdArray) &&
                    (nameIdArray.length == 2)) {

                interview.setNameId
                        (this.cleanComment(nameIdArray[1]));

                eval = true;
            }
        }

        return eval;
    } // evalNameId

    private String cleanComment (String line) {

        int indexOf = line.indexOf('#');

        if (indexOf > 0) {

            line =
                line.substring(0, indexOf);
        }

        return line.trim();
    } // cleanComment.

    private boolean isComment (final String line) {

        return (line.startsWith("#"));
    } // isComment
} // E:O:F:TabLiInterviewReader.
