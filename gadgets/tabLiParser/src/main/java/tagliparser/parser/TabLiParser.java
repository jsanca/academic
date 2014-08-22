package tagliparser.parser;

import tagliparser.bean.Item;
import tagliparser.converter.Converter;
import tagliparser.converter.StringConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser an InputStream with text with the following format
 * <p/>
 * item
 * child1
 * child2
 * child2.1
 * child3
 * <p/>
 * where the space between the children and parent can be 4 spaces or tab
 * <p/>
 * Date: 8/21/14
 * Time: 5:25 PM
 *
 * @author jsanca
 */
public class TabLiParser implements Serializable {

    public final static TabLiParser INSTANCE = new TabLiParser();

    private final static String SEPARATOR_4_SPACES = "    ";
    private final static String SEPARATOR_TAB = "\t";

    public List<Item<String>> parser(final InputStream inputStream) {

        return parser(inputStream, StringConverter.INSTANCE);
    } // parser

    public <T> List<Item<T>> parser(final InputStream inputStream, final Converter<T> converter) {

        return parser(new InputStreamReader(inputStream), converter);
    } // parser

    public <T> List<Item<T>> parser(final Reader reader, final Converter<T> converter) {

        final List<Item<T>> itemList =
                new ArrayList<Item<T>>();
        BufferedReader bufferedReader = null;
        String line = null;
        final int immediateChildLevel = 2;
        Item<T> currentItem = null;

        try {

            bufferedReader = new BufferedReader(reader);

            while ((line = bufferedReader.readLine()) != null) {

                if ((null != currentItem) && this.isChild(line, immediateChildLevel)) {

                    line = this.parserChildren
                            (line, immediateChildLevel,
                                    bufferedReader, currentItem, converter);

                    if (null != line) {

                        currentItem = this.processLine(line, itemList, converter, currentItem);
                    }
                } else {

                    currentItem = this.processLine(line, itemList, converter, currentItem);
                }
            }
        } catch (Exception e) {

            // throw an exception
        }

        return itemList;
    } // parser

    private <T> Item<T> processLine(final String line,
                                    final List<Item<T>> itemList,
                                    final Converter<T> converter,
                                    Item<T> currentItem) {

        T value = converter.convert(line);

        if (null != value) {

            currentItem = new Item<T>();
            currentItem.setValue(value);
            itemList.add(currentItem);
        }

        return currentItem;
    } // processLine.

    private <T> String parserChildren(String line,
                                      final int currentLevel,
                                      final BufferedReader bufferedReader,
                                      final Item<T> parentItem,
                                      final Converter<T> converter) throws IOException {

        final List<Item<T>> itemList =
                new ArrayList<Item<T>>();
        Item<T> currentItem = null;
        T value = null;
        final int immediateChildLevel = currentLevel + 1;

        do {

            if ((null != currentItem) && this.isChild(line, immediateChildLevel)) {

                line = this.parserChildren
                        (line, immediateChildLevel,
                                bufferedReader, currentItem, converter);

                if (null != line) {

                    if (this.isNotParentLine(line, currentLevel)) {

                        currentItem = this.processLine(line, itemList, converter, currentItem);
                    } else {

                        break;
                    }
                }
            } else {

                currentItem = this.processLine(line, itemList, converter, currentItem);
            }
        } while ((line = bufferedReader.readLine()) != null &&
                this.isNotParentLine(line, currentLevel));

        if (itemList.size() > 0) {

            parentItem.setList(itemList);
        }

        return line;
    } // parserChildren.

    private boolean isNotParentLine(final String line, int currentLevel) {

        boolean isNotParentLine = true;
        StringBuilder spaces = null;
        StringBuilder tabs = null;

        if (line.trim().length() > 0) {

            while (currentLevel > 1) {

                spaces = new StringBuilder();
                tabs = new StringBuilder();

                --currentLevel;

                // fill the expected separator for a parent
                for (int i = 1; i < currentLevel; ++i) {

                    spaces.append(SEPARATOR_4_SPACES);
                    tabs.append(SEPARATOR_TAB);
                }

                // if after separator the next char is not a space or tab, it is parent
                if (line.startsWith(spaces.toString())) {

                    if ((line.charAt(spaces.length()) != ' ') &&
                        (line.charAt(spaces.length()) != '\t')) {

                        isNotParentLine = false; break;
                    }
                } else if (line.startsWith(tabs.toString())) {

                    if ((line.charAt(tabs.length()) != ' ') &&
                            (line.charAt(tabs.length()) != '\t')) {

                        isNotParentLine = false; break;
                    }
                }
            }
        }

        return isNotParentLine;
    } // isNotParentLine.

    private boolean isChild(final String line, final int level) {

        final StringBuilder spaces = new StringBuilder();
        final StringBuilder tabs = new StringBuilder();

        for (int i = 1; i < level; ++i) {

            spaces.append(SEPARATOR_4_SPACES);
            tabs.append(SEPARATOR_TAB);
        }

        return (line.trim().length() > 0) &&  // not blank line
                (line.startsWith(spaces.toString())
                        || line.startsWith(tabs.toString()));
    } // isChild.

} // E:O:F:TagLiParser.
