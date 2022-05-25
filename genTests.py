

from random import randint, choices


def genSelection(field):
    return f'stat.execute("SELECT {field} FROM " + TABLE_NAME + " WHERE {field} = {randint(0, 1000)}");'

def genAssertion(fieldName, expectedAccesses):
    return f'assertTrue(tracker.getSelections(TABLE_NAME, "{fieldName}") == {expectedAccesses});'

# accesses: {field: string, valueCheck: string}
def genString(testnumber, accesses):
    selections = "\n\t        ".join([genSelection(access) for access in accesses])
    accessCounts = {}
    for field in accesses:
        accessCounts[field] = accessCounts.get(field, 0) + 1
    assertions = "\n\t        ".join([genAssertion(key, accessCounts.get(key)) for key in accessCounts.keys()])

    return """
        @Test
        public void test_{0}() throws Exception {1}

            {2}

            FieldTracker tracker = con.getFieldTracker();
            {3}
        {4}
    """.format(testnumber, "{", selections, assertions, "}")

def main():
    file = "./generated-tests.txt"

    fields = [chr(x + ord('a')) for x in range(4)]
    queries = "\n\n".join([genString(i, choices(fields, k=10)) for i in range(100)])
    with open(file, 'w') as fp:
        fp.write(queries)

main()