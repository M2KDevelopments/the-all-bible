/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bible.enums;

/**
 *
 * @author MARTIN-PC
 */
public enum EnumBibleBook { 
    GENESIS("GENESIS"),
    EXODUS("EXODUS"),
    LEVITICUS("LEVITICUS"),
    NUMBERS("NUMBERS"),
    DEUTERONOMY("DEUTERONOMY"),
    JUSHUA("JOSHUA"),
    JUDGES("JUDGES"),
    RUTH("RUTH"),
    SAMUEL1("1 SAMUEL"),
    SAMUEL2("2 SAMUEL"),
    KINGS1("1 KINGS"),
    KINGS2("2 KINGS"),
    CHRONICLES1("1 CHRONICLES"),
    CHRONICLES2("2 CHRONICLES"),
    EZRA("EZRA"),
    NEHEMIAH("NEHEMIAH"),
    ESTHER("ESTHER"),
    JOB("JOB"),
    PSALMS("PSALMS"),
    PROVERBS("PROVERBS"),
    ECCLESIASTES("ECCLESIASTES"),
    SONGS_OF_SONGS("SONG OF SOLOMON"),//Song of Solomon
    ISAIAH("ISAIAH"),
    JEREMIAH("JEREMIAH"),
    LAMENTATIONS("LAMENTATIONS"),
    EZEKIEL("EZEKIEL"),
    DANIEL("DANIEL"),
    HOSEA("HOSEA"),
    JOEL("JOEL"),
    AMOS("AMOS"),
    OBADIAH("OBADIAH"),
    JONAH("JONAH"),
    MICAH("MICAH"),
    NAHUM("NAHUM"),
    HABAKKUK("HABAKKUK"),
    ZEPHANIAH("ZEPHANIAH"), 
    HAGGAI("HAGGAI"),
    ZECHANIAH("ZECHARIAH"), //Zechariah
    MALACHI("MALACHI"),
    MATTHEW("MATTHEW"),
    MARK("MARK"),
    LUKE("LUKE"),
    JOHN("JOHN"),
    ACTS("ACTS"),
    ROMANS("ROMANS"),
    CORINTHIANS1("1 CORINTHIANS"),
    CORINTHIANS2("2 CORINTHIANS"),
    GALATIONS("GALATIANS"),
    EPHESIANS("EPHESIANS"),
    PHILIPPIANS("PHILIPPIANS"),
    COLOSSIANS("COLOSSIANS"),
    THESSALONIANS1("1 THESSALONIANS"),
    THESSALONIANS2("2 THESSALONIANS"),
    TIMOTHY1("1 TIMOTHY"),
    TIMOTHY2("2 TIMOTHY"),
    TITUS("TITUS"),
    PHILEMON("PHILEMON"),
    HEBREWS("HEBREWS"),
    JAMES("JAMES"),
    PETER1("1 PETER"),
    PETER2("2 PETER"),
    JOHN1("1 JOHN"),
    JOHN2("2 JOHN"),
    JOHN3("3 JOHN"),
    JUDE("JUDE"),
    REVELATION("REVELATION");

    private final String book;

    EnumBibleBook(String book) {
        this.book = book;
    }

    public String getBook() {
        return this.book;
    }

    public String toString() {
        return book;
    }

}
