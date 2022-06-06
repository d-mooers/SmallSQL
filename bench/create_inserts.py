import sys, os
import re
import random


created_keys = {}

table_order = [
    'region',
    'nation',
    'part',
    'supplier',
    'partsupp',
    'customer',
    'orders',
    'lineitem'
]

key_constraints = {
    'region': {
        'keys': [],
        'max': 5,
        'updates': 0},

    'nation': {
        'keys': [('region', ['n_regionkey'], ['r_regionkey'])],
        'max': 15,
        'updates': 0},
    'part': {
        'keys': [],
        'max': 30,
        'updates': 10},
    'supplier': {
        'keys': [('nation', ['s_nationkey'], ['n_nationkey'])],
        'max':15, 
        'updates': 20},
    'partsupp': {
        'keys': [
            ('part', ['ps_partkey'], ['p_partkey']),
            ('supplier', ['ps_suppkey'], ['s_suppkey'])],
        'max': 130,
        'updates': 40},
    'customer': {
        'keys': [('nation', ['c_nationkey'], ['n_nationkey'])],
        'max': 40,
        'updates': 10},
    'orders': {
        'keys': [('customer', ['o_custkey'], ['c_custkey'])],
        'max': 30, 
        'updates': 40},
    'lineitem': {
        'keys': [
            ('orders', ['l_orderkey'], ['o_orderkey']),
            ('partsupp', ['l_partkey', 'l_suppkey'], ['ps_partkey', 'ps_suppkey'])],
        'max': 110, 
        'updates': 16}}



def get_to_save(table, column_list):
    '''
    Given a table name and a column list, return the indices
    of the columns to save
    '''
    column_indices = {}
    save_ind = []
    for i, column in enumerate(column_list):
        column_indices[column] = i
    for table in key_constraints.keys():
        for constraint in key_constraints[table]['keys']:
            for for_constraint in constraint[2]:
                if for_constraint in column_indices:
                    save_ind.append(column_indices[for_constraint])
    return save_ind

def get_reference(table, columns):
    '''
    Get a mapping of the columns that need to be referenced from table
    '''
    mapping = []
    for table2, self_fields, ref_fields in key_constraints[table]['keys']:
        mapping.append((table2, [columns.index(f) for f in self_fields], ref_fields))
    return mapping

def get_columns(table_name, ddl_text):
    '''
    Returns the column names for a particular table
    given the ddl text
    '''
    table_pattern = r'CREATE\s+TABLE\s+' + table_name + \
            '(.*?)' + '\);'
    column_pattern = r'\s+(\w+)\s+\w+.*?,'

    not_columns = set([
        'PRIMARY',
        'FOREIGN', 
        'REFERENCES',
        'ON',
        'NOT'])

    table_text = re.search(table_pattern, ddl_text, re.DOTALL).group()
    columns = re.findall(column_pattern, table_text)
    return [c for c in columns if c not in not_columns]


def get_ddl_contents():
    '''
    Get the contents of the ddl file
    '''
    ddl_loc = 'tpch_ddl.sql'
    with open(ddl_loc, 'r') as fin:
        return fin.read()

def get_free_cols(columns, save_cols, reference_cols):
    '''
    Returns the columns that are not part of any
    keys
    '''
    safe_columns = {}
    for i, column in enumerate(columns):
        if i not in save_cols:
            in_ref = False
            for t, c_i, r_c in reference_cols:
                if i in c_i:
                    in_ref = True
                    break
            if not in_ref:
                safe_columns[i] = column
    return safe_columns


def create_inserts_tbl(name, ddl, insert_dir):
    '''
    This creates insert statements for a particular table
    and it also creates update statements for the same
    table
    '''
    columns = get_columns(name, ddl)
    insert_file = os.path.join(insert_dir, name + '.tbl')
    inserts = ''
    updates = ''
    save_cols = get_to_save(name, columns)
    reference_cols = get_reference(name, columns)
    free_cols = get_free_cols(columns, save_cols, reference_cols)
    with open(insert_file, 'r') as fin:
        lines = fin.readlines()
    num_inserts = 0
    num_updates = 0

    created_keys[name] = {}
    for sc in save_cols:
        created_keys[name][columns[sc]] = set([])

    for line in lines:
        if num_inserts > key_constraints[name]['max']:
            break


        tokens = []
        for token in line.strip().split('|'):
            if len(token) == 0:
                continue
            try:
                float(token)
                tokens.append(token)
                continue
            except ValueError:
                pass
            try:
                int(token)
                tokens.append(token)
                continue
            except ValueError:
                pass
            tokens.append("'" + token + "'")

        # Tokens should equal columns
        if len(tokens) != len(columns):
            continue

        # Check referential integrity
        consistant = True
        for t, c_i, t_ref in reference_cols:
            if not all([tokens[c_i[i]] in created_keys[t][t_ref[i]] for i in range(len(c_i))]):
                consistant = False
        if not consistant:
            continue

        # Add reference cols
        for sc in save_cols:
            created_keys[name][columns[sc]].add(tokens[sc])

        # Create update
        #if True:
        if num_updates < key_constraints[name]['updates']:
            update_col = random.choice(list(free_cols.keys()))
            updates += 'UPDATE ' + name + ' SET ' + \
                    free_cols[update_col] + ' = ' + tokens[update_col] + \
                    ' WHERE ' + columns[0] + ' = ' + tokens[0] + ';\n'
            num_updates += 1

            

        inserts += 'INSERT INTO ' + name + \
                ' (' + ', '.join(columns) + ') ' \
                'VALUES ('
        inserts += ', '.join(tokens) + ');\n'
        num_inserts += 1
    return inserts, updates 


def create_inserts(ddl):
    '''
    Create inserts and write them to inserts.txt
    '''
    insert_dir = 'tbl'
    inserts = ''
    updates = ''
    for name in table_order:
        t_inserts, t_updates = create_inserts_tbl(name, ddl, insert_dir)
        inserts += t_inserts
        updates += t_updates 
    with open('inserts.sql', 'w') as fout:
        fout.write(inserts)
    with open('updates.sql', 'w') as fout:
        fout.write(updates)


if __name__ == '__main__':
    ddl = get_ddl_contents()
    create_inserts(ddl)

