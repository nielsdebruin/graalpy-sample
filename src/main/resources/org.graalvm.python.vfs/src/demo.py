import polyglot

class Dataset:
    def __init__(self, rows, column_names):
        self.rows = rows
        self.column_names = column_names

    def get_column_names(self):
        return self.column_names

    def get_rows(self):
        return self.rows

    def get_stats(self):
        return {"row_count": len(self.rows), "column_count": len(self.column_names)}


class CSVParser:
    def parse(self, data: str):
        rows = [row.split(",") for row in data.split("\n")]
        column_names = rows[0]
        rows = rows[1:]
        return Dataset(rows, column_names)


polyglot.export_value(CSVParser, "CSVParser")
polyglot.export_value(Dataset, "Dataset")
