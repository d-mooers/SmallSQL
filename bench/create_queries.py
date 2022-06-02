
import sys, os


def create_queries():
    '''
    Simply opens each query file and combines them
    into one query file
    '''
    query_loc = 'queries'
    queries = ''
    for f in os.listdir(query_loc):
        with open(os.path.join(query_loc, f), 'r') as fin:
            queries += fin.read()
    
    with open('queries.sql', 'w') as fout:
        fout.write(queries)

if __name__ == '__main__':
    create_queries()
