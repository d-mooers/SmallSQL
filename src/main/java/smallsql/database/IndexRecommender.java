package smallsql.database;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class IndexRecommender {
    
    protected ArrayList<Field> fields;
    // list of list of strings, first val is table name and second val is field name
    protected ArrayList<String[]> recommendedIndexes;
    private SSConnection con;

    IndexRecommender(SSConnection con) {
        this.con = con;
        this.recommendedIndexes = new ArrayList<>();
    }

    IndexRecommender(SSConnection con, ArrayList<Field> fields) {
        this.con = con;
        this.fields = fields;
        this.recommendedIndexes = new ArrayList<>();
    }

    public abstract ArrayList<String[]> recommendIndex();

    protected Boolean containsIndex(Field field) {
        Table table;
        try {
            table = (Table) con.getDatabase(true).getTableView(con, field.getTableName());
        } catch (SQLException e) {
            throw new Error("Database not found.");
        }
        IndexDescriptions indexes = table.indexes;
        for (int i = 0; i < indexes.size(); i++) {
            IndexDescription index = indexes.get(i);
            if (index.getColumns().get(0).equals(field.getFieldName())) {
                return true;
            }
        }
        return false;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public ArrayList<Field> getFields() {
        return this.fields;
    }

    public ArrayList<String[]> getRecommendedIndexes() {
        this.recommendedIndexes.sort((f1, f2) -> {
            double score_1 = Double.parseDouble(f1[2]);
            double score_2 = Double.parseDouble(f2[2]);

            if (score_1 > score_2) return -1;
            return 1;
        });
        return this.recommendedIndexes;
    }

    // Create up to max indexes.
    public ArrayList<String[]> createRecommendedIndexes(int max) throws SQLException, Exception {
        this.recommendIndex();
        ArrayList<String[]> indexes = this.getRecommendedIndexes();

        Database db = this.con.getDatabase(false);

        if (max < 0 || max > indexes.size()) {
            max = indexes.size();
        }
        for (int i = 0; i < max; i++) {
            String[] rec = indexes.get(i);
            String table = rec[0];
            String field = rec[1];
            String name = table + "_" + field;
            Expressions expressions = new Expressions();
            Strings columns = new Strings();
            expressions.add(new ExpressionName(field));
            columns.add(field);
            IndexDescription indexDesc = new IndexDescription(
                    name, table, SQLTokenizer.INDEX, expressions, columns
            );
            Table tableView = (Table)db.getTableView(con, table);
            indexDesc.create(con, db, tableView);
            tableView.indexes.add(indexDesc);
        }

        return indexes;
    }
}
