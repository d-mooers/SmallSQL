package smallsql.database;

/** Represents the access type of a field being used. */

enum AccessType {
    SELECT,
    WHERE,
    INSERTION,
    DELETION,
    JOIN,
    UPDATE,
    GROUPBY,
    HAVING,
    ORDERBY,
    NULL
}